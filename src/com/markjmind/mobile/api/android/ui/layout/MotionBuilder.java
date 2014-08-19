package com.markjmind.mobile.api.android.ui.layout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;

/**
 * 
 * @author 오재웅
 * @phone 010-2898-7850
 * @email markjmind@gmail.com
 * @date 2014. 8. 13.
 */
@SuppressLint("NewApi") 
public class MotionBuilder {
	private JwMotionSet set;
	private JwMotionAdapter currAdapter;
	
	public MotionBuilder(JwMotionSet set, JwMotionAdapter playAdapter){
		this.set = set;
		set.adapterList.clear();
		currAdapter = playAdapter;
		set.maxDuration = playAdapter.startDuration+playAdapter.duration;
		set.adapterList.add(playAdapter);
	}
	
	public MotionBuilder with(View targetView, ValueAnimator valueAnimator){
		JwMotionAdapter adapter = new JwMotionAdapter(targetView,valueAnimator,set.direction);
		adapter.joinDuration = currAdapter.joinDuration;
		currAdapter = adapter;
		if(set.maxDuration<adapter.startDuration+adapter.duration){
			set.maxDuration = adapter.startDuration+adapter.duration;
		}
		set.adapterList.add(currAdapter);
		return this;
	}
	public MotionBuilder with(ObjectAnimator objectAnimator){
		View targetView = (View)objectAnimator.getTarget();
		return with(targetView, objectAnimator);
	}
	
	public MotionBuilder after(View targetView, ValueAnimator valueAnimator){
		JwMotionAdapter adapter = new JwMotionAdapter(targetView,valueAnimator,set.direction);
		adapter.joinDuration = set.maxDuration;
		currAdapter = adapter;
		set.maxDuration += adapter.startDuration+adapter.duration;
		set.adapterList.add(currAdapter);
		return this;
	}
	
	public MotionBuilder after(ObjectAnimator objectAnimator){
		View targetView = (View)objectAnimator.getTarget();
		return after(targetView, objectAnimator);
	}
	
}
