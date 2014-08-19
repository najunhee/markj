package com.markjmind.mobile.api.android.ui.layout;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;

import com.markjmind.mobile.api.android.ui.layout.JwAnimator.DIRECTION;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.FlingMotionListener;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.ScrollMotionListener;

@SuppressLint("NewApi") 
public class JwMotionAdapter implements ScrollMotionListener,FlingMotionListener{
	long joinDuration = 0;
	long startDuration = 0;
	long duration = 0;

	private DIRECTION direction;
	private ValueAnimator anim;
	private float currX=0f,currY=0f;
	private View tagerView;
	
	public JwMotionAdapter(View tagerView, ValueAnimator anim, DIRECTION direction){
		this.tagerView = tagerView;
		this.anim = anim;
		this.direction = direction;
		
		this.joinDuration = 0;
		this.startDuration = anim.getStartDelay();
		this.duration = anim.getDuration();
	}
	@Override
	public boolean fling(float distanceX, float distanceY, float velocityX, float velocityY) {
		return true;
	}

	@Override
	public void startScroll(View view, MotionSize motionSize) {
		currX=0f;
		currY=0f;
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
				anim.setCurrentPlayTime((long)currX);
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
				return false;
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
	
	public View getTargetView(){
		return tagerView;
	}

}
