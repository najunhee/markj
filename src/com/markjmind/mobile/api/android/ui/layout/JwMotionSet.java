package com.markjmind.mobile.api.android.ui.layout;

import com.markjmind.mobile.api.android.ui.layout.JwAnimator.DIRECTION;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;


@SuppressLint("NewApi") 
public class JwMotionSet {
	
	private MotionBuilder builder;
	private int currX=0,currY=0;
	
	public MotionBuilder play(ValueAnimator va){
		SetAdapter adapter = new SetAdapter(va,DIRECTION.ALL);
		adapter.joinDuration=0;
		adapter.duration = (int)va.getDuration();
		return builder;
	}
	
	private class SetAdapter extends JwMotionAdapter{
		private DIRECTION direction;
		private ValueAnimator anim;
		
		public SetAdapter(ValueAnimator anim, DIRECTION direction){
			this.anim = anim;
			this.direction = direction;
		}
		@Override
		public boolean fling(float distanceX, float distanceY, float velocityX, float velocityY) {
			return true;
		}

		@Override
		public void startScroll(View view, MotionSize motionSize) {
		}

		@Override
		public boolean scroll(View view, float x, float y, MotionSize motionSize) {
			currX+=x;
			currY+=y;
			switch(direction){
				case ALL:{
					break;
				}case LEFT:{
					break;
				}case RIGHT:{
					break;
				}case TOP:{
					break;
				}case BOTTOM:{
					break;
				}case HORIZONTAL:{
					break;
				}case VERTICALITY:{
					break;
				}case NONE:{
					break;
				}
			}
			return true;
		}

		@Override
		public void endScroll(View view, MotionSize motionSize) {
		}
		
		private int getDurationToMove(){
			return 0;
		}
	}
}
