package com.github.youssefagagg.topologyAPI;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
/*
 * the result that will be returned by some request queries in the api
 */
@RequiredArgsConstructor
@Getter
public class Result {
	 enum Status{
		SUCCEED,FAIL;
	}
	
	@NonNull
	private Status status;
	@NonNull
	private String message;
	
	@Setter
	private Topology topology;//null if the status is SUCCEED
	
	//if an exception happend it will return in the Result
	@Setter
	private Exception exception;//null if the status is FAIL



}
