package com.markjmind.mobile.api.android.ui.layout;

import java.util.ArrayList;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;

import com.markjmind.mobile.api.android.ui.layout.JwAnimator.DIRECTION;


@SuppressLint("NewApi") 
public class JwMotionSet {
	ArrayList<JwMotionAdapter> adapterList = new ArrayList<JwMotionAdapter>();
	MotionBuilder builder;
	DIRECTION direction;
	long maxDuration=0;
	
	public MotionBuilder play(View targetView, ValueAnimator valueAnimator, DIRECTION direction){
		JwMotionAdapter adapter = new JwMotionAdapter(targetView,valueAnimator,direction);
		this.direction = direction;
		builder = new MotionBuilder(this, adapter);
		return builder;
	}
	public MotionBuilder play(ObjectAnimator objectAnimator, DIRECTION direction){
		return this.play((View)objectAnimator.getTarget(), objectAnimator, direction);
	}
}
