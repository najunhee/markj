package com.markjmind.mobile.api.android.ui.layout;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
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
		ObjectAnimator cloudAnim2 = ObjectAnimator.ofFloat(view, "x", -300);
		cloudAnim2.setDuration(3000);
		cloudAnim2.setRepeatCount(0);
//		cloudAnim2.setRepeatMode(ValueAnimator.REVERSE);
		cloudAnim2.start();
	
	}
	class A extends ValueAnimator{
		@Override
		public void setValues(PropertyValuesHolder... values) {
			super.setValues(values);
		}
	}
}
