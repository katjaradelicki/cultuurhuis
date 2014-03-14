package be.vdab.dao;

/*
 * Eigen exceptie om in de servlets geen SQLExcepties te hebben die JDBC gebonden zijn.
 */
public class DAOException extends RuntimeException {
private static final long serialVersionUID=1L;
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(String message, Throwable cause){
		super(message,cause);
	}
	
}
