package com.sms.controller.master;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;

public class ServerController extends GenericForwardComposer implements
		SerialPortEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3963809959399120391L;

	private static final Logger logger = Logger
			.getLogger(ServerController.class);

	private Combobox cmbPort;
	private Textbox txtManufacture;
	private Textbox txtProvider;
	private Textbox txtModel;

	Enumeration portList = null;
	CommPortIdentifier portId = null;
	String portName = null;
	SerialPort port = null;

	InputStream input = null;
	OutputStream output = null;

	// varialbel parameter serialport
	String nilaiPort;
	int nilaiData = 0;
	int nilaiStop = 0;
	int nilaiParity = 0;
	int nilaiFlow = 0;
	int nilaiBaud = 0;
	int i = 0;
	int statusServer = 0;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		findPortList();
	}

	// search port in computer
	public void findPortList() {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			// ------------if serial port--------------
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				portName = portId.getName();
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(portName);
				comboitem.setLabel(portName);
				comboitem.setParent(cmbPort);
			}
		}
	}
	
	 public void dapatNilaiParameter(){
	        portName = ""+cmbPort.getSelectedItem().getValue();
	        nilaiBaud = 9600;
	        String Data = "8";/*""+cmbDataBits.getSelectedItem();*/
	        String Stop = "1";/*""+cmbStopBits.getSelectedItem();*/
	        String Parity ="None"; /*""+cmbParity.getSelectedItem();*/
	        String Flow ="None"; /*""+cmbFlowControl.getSelectedItem();*/
	        Parity.toUpperCase();
	        Flow.toUpperCase();
	        
	//-----------untk pilihan komponen cmbdataBits----------------
	        if(Data.equals("5")){
	            nilaiData = SerialPort.DATABITS_5;
	        }
	        if(Data.equals("6")){
	            nilaiData = SerialPort.DATABITS_6;
	        }
	        if(Data.equals("7")) {
	            nilaiData = SerialPort.DATABITS_7;
	        }
	        if(Data.equals("8")) {
	            nilaiData = SerialPort.DATABITS_8;
	        }
	        
	        //------- Untuk pilihan komponen pilihStop---------------
	        if(Stop.equals("1")) {
	            nilaiStop = SerialPort.STOPBITS_1;
	        }
	        if(Stop.equals("1,5")) {
	            nilaiStop = SerialPort.STOPBITS_1_5;
	        }
	        if(Stop.equals("2")) {
	            nilaiStop = SerialPort.STOPBITS_2;
	        }
	        
	        // ----------Untul pilihan komponen pilihParity
	        if(Parity.equals("EVEN")) {
	            nilaiParity = SerialPort.PARITY_EVEN;
	        }
	        if(Parity.equals("ODD")) {
	            nilaiParity = SerialPort.PARITY_ODD;
	        }
	        if(Parity.equals("NONE")) {
	            nilaiParity = SerialPort.PARITY_NONE;
	        }
	        if(Parity.equals("MARK")) {
	            nilaiParity = javax.comm.SerialPort.PARITY_MARK;
	        }
	        if(Parity.equals("SPACE")) {
	            nilaiParity = SerialPort.PARITY_SPACE;
	        }
	        
	        //-------- Untuk pilihan komponen pilihFlow
	        if(Flow.equals("NONE")) {
	            nilaiFlow = SerialPort.FLOWCONTROL_NONE;
	        }
	        if(Flow.equals("HARDWARE")) {
	            nilaiFlow = SerialPort.FLOWCONTROL_RTSCTS_IN |
	                        SerialPort.FLOWCONTROL_RTSCTS_OUT;
	        }
	        if(Flow.equals("XOFF/XON")) {
	            nilaiFlow = SerialPort.FLOWCONTROL_XONXOFF_IN |
	                         SerialPort.FLOWCONTROL_XONXOFF_OUT;
	        }
	    }

	int bufferOffset = 0;
	byte[] bacaBuffer = new byte[100000];
	int n;

	public void setTerminal(final String nilaiPort, final int nilaiBaud,
			final int nilaiData, final int nilaiStop, final int nilaiParity,
			final int nilaiFlow) {
		i = 0; // 25 juni 08
		// respon("Server Sedang melakukan pencarian port");
		// --------- mencari daftar port-----------------
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			// -------just serial port------------------
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				// -------jika nama port sama dengan port yang
				// dipilih----------------
				if (portId.getName().equals(nilaiPort)) {
					try {
						// ----buka serialport-----------------------
						port = (SerialPort) portId.open("SMS", 5000);
						logger.info("Server has success open port " + portName);
						// respon.select(i);
					} catch (PortInUseException piu) {
						logger.error("Port " + nilaiPort + " in use");
						
					}
				}
			}
		}
		// -------------mencoba mendapatkan inputStream dan
		// OutputStream-----------
		try {
			input = port.getInputStream();
			output = port.getOutputStream();
		} catch (IOException ioe) {
			// respon("Kesalahan pada "+ioe,200);
			logger.error("wrong at " + ioe);
		}

		// ------------mencoba melakukan pengaturan port
		// serial------------------
		try {
			// --------mengatur parameter port serial------------------
			port.setSerialPortParams(nilaiBaud, nilaiData, nilaiStop,
					nilaiParity);
			port.setFlowControlMode(nilaiFlow);
			// --------menampilkan data yang diperoleh
			port.notifyOnDataAvailable(true);
			// --------cetak ke layar jika pengaturan berhasil------------------
			// respon("Server melakukan hubungan ke Port :"+ namaPort);
			// respon("Server Berhasil terhubung ke Port :"+namaPort,200);
			// respon("Server Sedang Melakukan pengaturan terminal",200);
			// respon.select(i);
			Log.info("Server melakukan hubungan ke Port :"+ portName);
			Log.info("Server Berhasil terhubung ke Port :"+ portName);
			Log.info("Server Sedang Melakukan pengaturan terminal");

			kirimAT("AT" + "\15", 1250); // Apakah terminal telah siap
			kirimAT("AT+CGMI" + "\15", 1250); // Merk Handphone
			kirimAT("AT+CGMM" + "\15", 1250); // Tipe/model handphone
			kirimAT("AT+CGSN" + "\15", 1250); // imei
			kirimAT("AT+CSCA?" + "\15", 1250); // Nomor SMS center
			kirimAT("AT+CBC" + "\15", 1250); //
			kirimAT("AT+CSQ" + "\15", 1250); //

			kirimAT("AT+CPMS=SM" + "\15", 1250); // aktifkan memory sim
			// kirimAT("AT+CPMS=\"SM\"" +"\15",1250);

			kirimAT("AT+CPMS=ME" + "\15", 1250);// aktifkan memory telepon
			// kirimAT("AT+CPMS=\"ME\""+"\15",1250);
			kirimAT("AT+CNMI=2,3,2,1,0" + "\15", 1250); // membaca pesan secara
			// Otomatis
			// kirimAT("AT+CNMI=1,1,2,2,1" + "\15", 2000); // membaca pesan
			// secara Otomatis
			kirimAT("AT+COPS?" + "\15", 1250);// layanan provider kartu sim
			kirimAT("AT+CMGF=0" + "\15", 1250); // Menetapkan Format PDU Mode
			kirimAT("AT+CSCS=\"GSM\"" + "\15", 1250); // Menetapkan Encoding
			kirimAT("AT+CMGL=0" + "\15", 1250); // Membaca pesan yang belum
			// dibaca yang ada di dalam
			// Inbox
			kirimAT("AT+CMGL=1" + "\15", 1250); // Membaca pesan yang belum
			// dibaca yang ada di dalam
			// Inbox
			//comment kon for awhile jarot 2011-03-04
			//kon = true; 
			 statusServer = 2;
			 // lblNyambung.setText("Sedang Terhubung");
			// lblNyambung.setForeground(Color.BLUE);
			// btnSambung.setEnabled(false);
		} catch (UnsupportedCommOperationException u) {
			logger.error("Wrong at " + u);
		}

		try {
			port.addEventListener(this);
		} catch (TooManyListenersException tmle) {
			logger.error("Wrong at " + tmle);
		}
	}// end setTerminal

	@Override
	public void serialEvent(SerialPortEvent event) {
		try {
			// ---------apabila ada respon dari termonal lakukan pembacaan
			while ((n = input.available()) > 0) {
				n = input.read(bacaBuffer, bufferOffset, n);
				bufferOffset += n;
				// -------Jika ada respon "\15"(line feed car ret)-----
				if ((bacaBuffer[bufferOffset - 1] == 10)
						&& (bacaBuffer[bufferOffset - 2] == 13)) {
					String buffer = new String(bacaBuffer, 0, bufferOffset - 2);
					// ----berikan kemetode terimaAT
					terimaAt(buffer);
					bufferOffset = 0;
				}
			}
		} catch (IOException e) {
			;
		}
	}

	private void kirimAT(String atCmd, int delay) {
		Boolean tungguDelay = new Boolean(true);
		boolean getDelay = false;
		// --------- Membuat antrian proses
		synchronized (tungguDelay) {
			try {
				// --------- Menulis AT Commmand
				output.write((atCmd).getBytes());
				output.flush();// Hapus OutputStream
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				tungguDelay.wait(delay);
			} catch (Exception e) {
				getDelay = true;
			}
		} // Akhir syncronized
	}

	// ---------- BALIK KARAKTER ---------------
	static int panjangKarakter = 0;
	static StringBuffer stringBuffer = null;

	public static String balikKarakter(String karakter) {
		panjangKarakter = karakter.length();
		stringBuffer = new StringBuffer(panjangKarakter);
		for (int i = 0; (i + 1) < panjangKarakter; i = i + 2) {
			stringBuffer.append(karakter.charAt(i + 1));
			stringBuffer.append(karakter.charAt(i));
		}
		return new String(stringBuffer);
	}

	// -----------rubah ke hexa
	private static char[] hexa;
	private static char[] karakter;

	private static String rubahKeHexa(int a) {
		char[] hexa = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };

		karakter = new char[2];
		// ------- Mengambil hanya 8 bit 255d = 11111111 b
		a = a & 255;
		// ------- hasil pembagian dengan 16
		karakter[0] = hexa[a / 16];
		// -------sisa hasil pembagian dengan 16
		karakter[1] = hexa[a % 16];

		return new String(karakter);
	}

	// /--------DELAPAN KE TUJUH BIT -----------////
	static char[] gsmToAsciiMap; // GSM ==> ASCII

	private static String delapanKeTujuhBit(String pesan, int msglen) {
		int i, o, r = 0, rlen = 0, olen = 0, charcnt = 0;
		StringBuffer msg = new StringBuffer(160);
		int pesanlen = pesan.length();
		String ostr;
		char c;

		// -------- pengulangan hingga nilai terpenuhi
		// -------- i + 1 < pesanlen dan charcnt < msglen
		for (i = 0; ((i + 1) < pesanlen) && (charcnt < msglen); i = i + 2) {
			// mengambil dua digit Hexadesimal
			ostr = pesan.substring(i, i + 2);
			o = Integer.parseInt(ostr, 16);
			// berikan nilai olen = 8
			olen = 8;

			// --------geser posisi semua bit ke kiri sebanyak rlen bit
			o <<= rlen;
			o |= r; // -------berikan sisa bit dari o ke r
			olen += rlen; // ----------- olen = olen + rlen

			c = (char) (o & 127); // ----- mendapatkan nilai o menjadi 7 bit
			o >>>= 7; // --------- geser posis bit ke kanan sebanyak 7 bit
			olen -= 7;

			r = o; // ----- menaruh sisa bit dari o ke r.
			rlen = olen;

			c = gsmToAsciiMap[c]; // ----- rubah ke Text (kode ASCII)
			msg.append(c); // ------tambahkan ke msg
			charcnt++; // nilai charcnt ditambahkan 1

			// jika rlen >= 7
			if (rlen >= 7) {
				c = (char) (r & 127);
				r >>>= 7;
				rlen -= 7;
				msg.append(c);
				charcnt++;
			}
		} // Akhir for
		if ((rlen > 0) && (charcnt < msglen)) {
			msg.append((char) r);
		}
		return msg.toString();
	}// -----AKHIR DELAPAN KE TUJUH BIT

	private static char[] asciiToGsmMap; // ASCII ==> GSM

	private static String tujuhKeDelapanBit(String pesan) {
		StringBuffer msg = new StringBuffer(pesan);
		StringBuffer encmsg = new StringBuffer(2 * 160);
		int bb = 0, bblen = 0, i;
		char o = 0, c = 0, tc;

		for (i = 0; i < msg.length() || bblen >= 8; i++) {
			if (i < msg.length()) {
				c = msg.charAt(i);
				tc = asciiToGsmMap[c];

				c = tc;

				c &= ~(1 << 7);
				bb |= (c << bblen);
				bblen += 7;
			}

			while (bblen >= 8) {
				o = (char) (bb & 255);
				encmsg.append(rubahKeHexa(o));
				bb >>>= 8;
				bblen -= 8;
			}
		}
		if ((bblen > 0)) {
			encmsg.append(rubahKeHexa(bb));
		}
		return encmsg.toString();
	}

	static {
		final int lastindex = 255;
		gsmToAsciiMap = new char[lastindex + 1];
		asciiToGsmMap = new char[lastindex + 1];
		int i;

		for (i = 0; i <= lastindex; i++) {
			gsmToAsciiMap[i] = asciiToGsmMap[i] = (char) i;
		}
	}

	// /-------------PDU TERIMA SMS---------------///
	String infoSmsc = null;
	int nilaiSmsc = 0;
	int nomorSmsc = 0;
	String panjangNoTlp = null;
	int nilaiPanjangNoTlp = 0;
	int nilaiNoTlp = 0;
	String noTlp = null;
	String dapatNoTlp = null;
	String panjangPesan = null;
	int nilaiPanjangPesan = 0;
	String pesanPDU = null;
	String pesan = null;

	public void pduTerimaSms(String smspdu) {
		int i = 0;
		try {
			// ----------mengambil nilai panjang informasi SMSC
			infoSmsc = smspdu.substring(i, 2);
			nilaiSmsc = Integer.parseInt(infoSmsc, 16);
			// ----------format nomor dan nomor MSC dibuang
			i = i + 4;
			nomorSmsc = i + (nilaiSmsc * 2) - 2;
			// ---------nilai pdu type yang dibuang
			i = nomorSmsc + 2;
			// ---------mengambil panjang nomor telepon pengirim
			panjangNoTlp = smspdu.substring(i, i + 2);
			nilaiPanjangNoTlp = Integer.parseInt(panjangNoTlp, 16);
			// -----------format nomor pengirim dibuang
			i = i + 4;
			nilaiNoTlp = i + nilaiPanjangNoTlp + nilaiPanjangNoTlp % 2;
			// ------------no tlp pengirim'
			noTlp = smspdu.substring(i, nilaiNoTlp);
			dapatNoTlp = balikKarakter(noTlp);
			i = nilaiNoTlp;
			// -------------nilai PID, DCS dan SCTS dibuang
			i = i + 18;
			// ------------mengambil panjang pesan sms
			panjangPesan = smspdu.substring(i, i + 2);
			nilaiPanjangPesan = Integer.parseInt(panjangPesan, 16);
			i = i + 2;
			pesanPDU = smspdu.substring(i, smspdu.length());
			pesan = delapanKeTujuhBit(pesanPDU, nilaiPanjangPesan);
		} catch (Exception e) {

		}
	}

	// /-------------pdu kirim sms---------------- ///
	private static StringBuffer pesanPDUKirim = null;
	private static int panjangNotlpTujuan = 0;
	private static int panjangPesanKirim = 0;
	private static String PduPesan = null;

	private static String PduKirimSms(String notlp, String pesan) {
		pesanPDUKirim = new StringBuffer(320); // 320 = 160 * 2 (panjang max)
		// Tambahkan nilai PDU Type ---> Default = 11
		pesanPDUKirim.append("11");
		// Tambahkan nilai MR ---> Default = 00
		pesanPDUKirim.append("00");
		// Tambahkan nilai panjang nomor pengirim
		panjangNotlpTujuan = notlp.length();
		pesanPDUKirim.append(rubahKeHexa(panjangNotlpTujuan));
		// Tambah nilai format no. telepon --> format internasional = 91
		pesanPDUKirim.append("91");
		// Tambahkan nilai nomor telepon pengirim
		// Jika panjang notlp adalah ganjil
		if ((notlp.length() % 2) == 1) {
			notlp = balikKarakter(notlp + "F");
		}
		// Jika panjang notlp adalah genap
		else {
			notlp = balikKarakter(notlp);
		}
		pesanPDUKirim.append(notlp);
		// tambahkan nilai PID ---> Default = 00
		pesanPDUKirim.append("00");
		// tambahkan nilai DCS ---> Default = 00
		pesanPDUKirim.append("00");
		// tambahkan nilai VP = 4 hari ---> AA h
		pesanPDUKirim.append("AA");
		panjangPesanKirim = pesan.length();
		PduPesan = tujuhKeDelapanBit(pesan);
		pesanPDUKirim.append(rubahKeHexa(panjangPesanKirim));
		pesanPDUKirim.append(PduPesan);

		return new String(pesanPDUKirim);
	}

	// /----------------terima AT--------------
	String[] hasil;
	private String[] center;
	int Index;
	int panjangPDU;
	int PDU = 0;
	String respons;
	StringTokenizer st;
	boolean merek = false;
	boolean seri = false;
	boolean imei = false;

	public void terimaAt(String buffer) {
		// -------------menguraikan buffer berdasarkan karakter crlf
		st = new StringTokenizer(buffer, "\r\n");
		while (st.hasMoreTokens()) {
			// ------------mengambil token yang ada pada objek
			respons = st.nextToken();
			//respon(respons, 200);
			// respon.select(i);
			
			// ------------memproses respon yang diterima
			try {
				// ----------- jika ada telepon yang masuk
				if (respons.startsWith("RING")) {
					kirimAT("ATH0" + "\15", 100);// diputuskan
				}
				// --------jika ada pesan baru yang masuk
				else if (respons.startsWith("+CMTI:")) {
					java.util.regex.Pattern pattern = java.util.regex.Pattern
							.compile(",");// AWAL ","
					hasil = pattern.split(respons.trim());
					Index = Integer.parseInt(hasil[1].trim());
					// kirimAT("AT+CPMS=" + hasil[0].trim() + "\15", 1250);
					kirimAT("AT+CMGR=" + Index + "\15", 1250);// baca pesan baru
																// yang masuk
				}
				// --------------jika ada pesan baru yang dibaca
				else if (respons.startsWith("+CMGR:")) {
					PDU = 1;
				}

				// Membaca Pesan Inbox yang belum dibaca
				else if (respons.startsWith("+CMGL")) {
					java.util.regex.Pattern pattern = java.util.regex.Pattern
							.compile(":");
					hasil = pattern.split(respons.trim());
					pattern = java.util.regex.Pattern.compile(",");
					hasil = pattern.split(hasil[1].trim());
					Index = Integer.parseInt(hasil[0].trim());
					PDU = 1;
				} // Akhir if "+CMGL"

				else if (PDU == 1) {
					prosesTerimaSms(Index, respons.trim());
					PDU = 0;
				} else {
				}
				
				logger.debug("Respone  ==>"+respons.trim());
				if (merek) {
					logger.debug("Respone manufacture ==>"+respons.trim());
					txtManufacture.setValue(respons.trim());
					merek = false;
				} else if (seri) {
					txtModel.setValue(respons.trim());
					seri = false;
				} 
//				else if (imei) {
//					//txtimei.setText(respons.trim());
//					imei = false;
//				}

				if (respons.startsWith("+COPS:")) {
					java.util.regex.Pattern pattern = java.util.regex.Pattern
							.compile(",");
					String[] hasil = pattern.split(respons.trim());
					String cbb = hasil[2].trim();
					//comment for a while jarot 2011-03-04
					//txtsms.setText(cbb);

				} else if (respons.startsWith("AT+CGMI")) {
					merek = true;
				} else if (respons.startsWith("AT+CGMM")) {
					seri = true;
				} else if (respons.startsWith("AT+CGSN")) {
					imei = true;
				}

			} catch (Exception e) {
			}
		}// akhir while
	}

	// /--------
	// prosesTerimaSms-------------------------------------------------
	public void prosesTerimaSms(int Index, String Pdu) {
		try {
			// ----------rubah dari format pdu menjadi format text
			pduTerimaSms(Pdu);
		} catch (Exception e) {
		}

		// ----------jika nomor telepon pengirim diakhiri dengan "F"
		if (dapatNoTlp.endsWith("F")) {
			// ---------buang karakter F
			dapatNoTlp = dapatNoTlp.substring(0, dapatNoTlp.length() - 1);
		}
		if (dapatNoTlp.startsWith("0")) {
			dapatNoTlp = "62" + dapatNoTlp.substring(1);
		}
		// hapus pesan yang telah dibaca
		kirimAT("AT+CMGD=" + Index + "\15", 1250);
		//tulisDataTabelSmsMasuk(dapatNoTlp, pesan);
		//tampilSmsMasuk();
		
	}

	// ------------------------- PROSES KIRIM SMS
	// -------------------------------
	public void prosesKirimSms(/* String id, */String notlp, String pesan) {
		try {
			//tulisStatusData(notlp, "Telah Dikirim");// ganti notlp ma id
			// mengubah pesan menjadi format pdu
			String pesanPDUKirim = PduKirimSms(notlp.trim(), pesan.trim());
			// proses mengirim pesan
			kirimAT("AT+CMGS=" + (pesanPDUKirim.length() / 2) + "\15", 1250);
			kirimAT("00" + pesanPDUKirim, 2500);// kirim pesan format pdu
			kirimAT("\032", 1000);// CTRL + Z
			//tampilSmsKeluar();
			//respon("Kirim ke : +" + notlp, 200);
			//respon("Isi :" + pesan, 200);
			//respon("Status :Telah Dikirim", 200);
			// respon.select(i);
			// berikan waktu sleep untuk terminal agar siap kembali untk
			// mengirim pesan
			Thread.currentThread().sleep(10000);
		} catch (Exception e) {
		}
	}

	public void onClick$btnConnect(Event event) {
		Thread connect = new Thread(){
			public void run(){
				dapatNilaiParameter();
				setTerminal(portName, nilaiBaud, nilaiData, nilaiStop, nilaiParity, nilaiFlow);
			}
		};
		connect.start();
	}
}
