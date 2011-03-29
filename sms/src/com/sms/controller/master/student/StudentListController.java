package com.sms.controller.master.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.xel.fn.CommonFns;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.sms.bean.master.Student;
import com.sms.common.constant.PageConstant;
import com.sms.common.constant.StudentConstant;
import com.sms.common.util.ContexHelper;
import com.sms.common.util.ResourceHelper;
import com.sms.controller.CommonSearchComposer;
import com.sms.service.master.StudentService;

public class StudentListController extends CommonSearchComposer {

	/**
	 * Fajar Podolski
	 */
	private static final long serialVersionUID = 2987372181757715044L;
	private static final Logger LOGGER = Logger
			.getLogger(StudentListController.class);

	private Window winStudentList;
	private Paging paging;
	private Listbox cmbSearchColumn;
	private Textbox txtSearchValue;

	private static final StudentService studentService = (StudentService) ContexHelper
			.getBean("studentService");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		for (StudentConstant studentConstant : StudentConstant.values()) {
			Listitem listitem = new Listitem();
			listitem.setValue(studentConstant.getValue());
			listitem.setLabel(studentConstant.getLabel());
			listitem.setParent(cmbSearchColumn);
		}

		search();
	}

	public void onClick$btnAdd(Event event) {
		navigateTo(ResourceHelper.getResources("zul.studentCreate"), null,
				winStudentList);
	}

	public Map<String, Object> getArgs(long id) {
		Map<String, Object> args = new HashMap<String, Object>();
		try {
			Student student = studentService.getStudentById(id);
			args.put("studentId", student.getId());
			args.put("studentNis", student.getNis());
			args.put("studentName", student.getName());
			args.put("studentAddress", student.getAddress());
		} catch (Exception e) {
			LOGGER.error("error getArgs===" + e.getMessage());
		}

		return args;
	}

	public void onClick$btnSearch(Event event) {
		search();
	}

	@Override
	protected ListitemRenderer createListItemRenderer() {
		return new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				Student student = (Student) data;
				final long id = student.getId();
				Listcell lc = new Listcell(student.getNis());
				lc.setParent(item);

				lc = new Listcell(student.getName());
				lc.setParent(item);

				lc = new Listcell(student.getAddress());
				lc.setParent(item);

				Div div = new Div();
				A edit = new A(CommonFns.getLabel("lbl.common.edit"));
				edit.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						String eventName = event.getName();
						if (eventName.equals(Events.ON_CLICK)) {
							navigateTo(ResourceHelper
									.getResources("zul.studentEdit"),
									getArgs(id), winStudentList);
						}
					}
				});
				edit.setParent(div);

				div.appendChild(new Space());

				A delete = new A(CommonFns.getLabel("lbl.common.delete"));
				delete.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						String eventName = event.getName();
						if (eventName.equals(Events.ON_CLICK)) {
							navigateTo(ResourceHelper
									.getResources("zul.studentDelete"),
									getArgs(id), winStudentList);
						}
					}
				});
				delete.setParent(div);

				lc = new Listcell();
				div.setParent(lc);
				lc.setParent(item);
			}
		};
	}

	@Override
	protected void search() {
		String searchColumn = null;
		String searchValue = null;
		List<Object> columnList = null;
		List<Object> valueList = null;

		List<Student> students = new ArrayList<Student>();

		if ((searchValue = txtSearchValue.getValue()) != null
				&& !searchValue.trim().equals("")) {
			searchColumn = (String) cmbSearchColumn.getSelectedItem()
					.getValue();
			columnList = new ArrayList<Object>();
			valueList = new ArrayList<Object>();
			columnList.add(searchColumn);
			valueList.add(searchValue.trim());
		}

		students = studentService.getListStudent(columnList, valueList,
				PageConstant.FIRST_RESULT.getValue(), PageConstant.MAX_RESULTS.getValue());

		paging.setPageSize(PageConstant.MAX_RESULTS.getValue());
		paging.setTotalSize(studentService.getCountAllStudent());
		paging.setParent(divSearchResult);
		paging.addEventListener("onPaging", new OnPagingEventListener());
		paging.setActivePage(0);
		paging.setDetailed(true);

		lsSearchResult.setModel(new ListModelList(students));
		lsSearchResult.setItemRenderer(createListItemRenderer());

	}

	public final class OnPagingEventListener implements EventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			PagingEvent pe = (PagingEvent) event;
			int pageNo = pe.getActivePage();
			int PAGE_SIZE = paging.getPageSize();
			int offs = pageNo * PAGE_SIZE;
			redraw(offs, PAGE_SIZE);
		}
	}

	void redraw(int firstResult, int maxResult) {
		String searchColumn = null;
		String searchValue = null;
		List<Object> columnList = null;
		List<Object> valueList = null;

		List<Student> students = new ArrayList<Student>();
		lsSearchResult.getItems().clear();

		if ((searchValue = txtSearchValue.getValue()) != null
				&& !searchValue.trim().equals("")) {
			searchColumn = (String) cmbSearchColumn.getSelectedItem()
					.getValue();
			columnList = new ArrayList<Object>();
			valueList = new ArrayList<Object>();
			columnList.add(searchColumn);
			valueList.add(searchValue.trim());
		}
		
		students = studentService.getListStudent(columnList, valueList,
				firstResult, maxResult);

		paging.setTotalSize(studentService.getCountAllStudent());

		lsSearchResult.setModel(new ListModelList(students));
		lsSearchResult.setItemRenderer(createListItemRenderer());
	}

	public void showReport() {
		Map<String, Object> args = new HashMap<String, Object>();

		String reportName = getReportName();

		if (reportName != null) {
			args.put("reportName", reportName);
			// args.put("reportParam", getReportParameter());

			LOGGER.info("show report : " + getReportName());

			navigateTo("WEB-INF/zul/common/reportViewer.zul", args,
					winStudentList);
		}
	}

	public String getReportName() {
		return "WEB-INF/report/myFirstBirt.rptdesign";
	}

	public void onClick$btnPrint(Event event) {
		showReport();
	}

}
