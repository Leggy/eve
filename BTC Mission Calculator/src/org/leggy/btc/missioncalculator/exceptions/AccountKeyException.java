package org.leggy.btc.missioncalculator.exceptions;

public class AccountKeyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AccountKeyException(){
        super();
    }
	
    public AccountKeyException(String s){
        super(s);
    }

}
