package com.markjmind.mobile.api.android.ui.layout;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

/**
 * 
 * @author 오재웅
 * @phone 010-2898-7850
 * @email markjmind@gmail.com
 * @date 2014. 8. 13.
 */
public class MotionBuilder {
	
	public ArrayList<JwMotionAdapter> adapterList = new ArrayList<JwMotionAdapter>();
	private JwMotionAdapter currAdapter;
	
	public MotionBuilder(JwMotionAdapter playAdapter){
		adapterList.clear();
		this.currAdapter = playAdapter;
		adapterList.add(playAdapter);
	}
	
	public MotionBuilder with(JwMotionAdapter adapter){
		adapter.joinDuration = currAdapter.joinDuration;
		this.currAdapter = adapter;
		adapterList.add(currAdapter);
		return this;
	}
	
	public MotionBuilder after(JwMotionAdapter adapter){
		adapter.joinDuration = currAdapter.joinDuration+adapter.duration;
		this.currAdapter = adapter;
		adapterList.add(currAdapter);
		return this;
	}
}
