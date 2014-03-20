package org.leggy.btc.recruitment.exceptions;

public class ConnectionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConnectionException(){
        super();
    }
	
    public ConnectionException(String s){
        super(s);
    }

}
