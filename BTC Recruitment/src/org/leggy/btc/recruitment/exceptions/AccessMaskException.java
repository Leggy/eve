package org.leggy.btc.recruitment.exceptions;

public class AccessMaskException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccessMaskException(){
        super();
    }
	
    public AccessMaskException(String s){
        super(s);
    }
}
