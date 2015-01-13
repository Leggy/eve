package org.leggy.btc.missioncalculator.exceptions;

public class AccessMaskException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccessMaskException(){
        super();
    }
	
    public AccessMaskException(String s){
        super(s);
    }
}
