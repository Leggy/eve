package org.leggy.eveapi.resources;

public class MissionReportException extends Exception {

	private static final long serialVersionUID = 1L;

	public MissionReportException(){
        super();
    }
	
    public MissionReportException(String s){
        super(s);
    }
}
