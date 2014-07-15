package com.markjmind.mobile.api.android.ui.layout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.util.Property;
import android.view.View;

@SuppressLint("NewApi") public class JwMotionAnimator {
	private View view;
	public float startX;
	public float startY;
	public float endX;
	public float endY;
	
	public JwMotionAnimator(View view){
		this.view = view;
	}
	
	public void decelerate(){
	
	}
	
}
