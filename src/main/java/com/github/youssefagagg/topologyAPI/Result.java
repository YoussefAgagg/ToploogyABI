package com.github.youssefagagg.topologyAPI;


/*
 * the result that will be returned by some request queries in the api
 */

public class Result {
	 enum Status{
		SUCCEED,FAIL;
	}
	

	private Status status;

	private String message;
	
	private Topology topology;//null if the status is SUCCEED
	
	//if an exception happend it will return in the Result
	private Exception exception;
	

	public Result(Status status, String message, Topology topology) {
		super();
		this.status = status;
		this.message = message;
		this.topology = topology;
	}

	public Result(Status status, String message, Exception exception) {
		super();
		this.status = status;
		this.message = message;
		this.exception = exception;
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Topology getTopology() {
		return topology;
	}

	public Exception getException() {
		return exception;
	}//null if the status is FAIL



}
