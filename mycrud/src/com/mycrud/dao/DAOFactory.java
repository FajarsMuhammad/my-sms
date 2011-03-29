package com.mycrud.dao;



public abstract class DAOFactory {
	/**
	 * Factory method for instantiation of concrete factories.
	 */
	@SuppressWarnings("unchecked")
	public static DAOFactory instance(Class factory) {
		try {
			return (DAOFactory) factory.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Couldn't create DAOFactory: " + factory);
		}
	}

	// List DAO Interface
	
	
}
