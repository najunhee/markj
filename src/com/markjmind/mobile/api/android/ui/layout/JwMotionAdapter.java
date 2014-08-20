package com.markjmind.mobile.api.android.ui.layout;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;

import com.markjmind.mobile.api.android.ui.layout.JwMotion.FlingMotionListener;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.ScrollMotionListener;

@SuppressLint("NewApi") 
public class JwMotionAdapter implements ScrollMotionListener,FlingMotionListener{
	long joinDuration = 0;
	long startDuration = 0;
	// TODO TOUCH와 똑같이 VIEW가 움직이려면 화면 크게에 맞게 duration이 변경되어야해서
	// 실제 play 되는 Duration과 사용자가 지정한 Duration이 다를수 있는데 이부분에 대한 해결책을 생각해야함
	long playDuration = 0;


	private int direction;
	private ValueAnimator anim;
	private float currX=0f,currY=0f;
	private View tagerView;
	
	private float accelerator;
	
	public JwMotionAdapter(View tagerView, ValueAnimator anim, int direction){
		this.tagerView = tagerView;
		this.anim = anim;
		this.direction = direction;
		
		this.joinDuration = 0;
		this.startDuration = anim.getStartDelay();
		this.playDuration = anim.getDuration();
		
		this.accelerator = 1.0f;
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
		if(direction== JwAnimator.LEFT){
			currX-=x*accelerator;
		}else{
			currX+=x*accelerator;
		}
		if(direction== JwAnimator.TOP){
			currY-=y*accelerator;
		}else{
			currY+=y*accelerator;
		}
		// TODO currMaxDuration을 전역변수로 빼서 연산 줄이기
		long currMaxDuration = playDuration-joinDuration-startDuration;
		long currDurationX = (long)(currX-joinDuration-startDuration);
		long currDurationY = (long)(currY-joinDuration-startDuration);
		
		if(currDurationX<0){
			currDurationX = 0;
		}
		if(currDurationX>currMaxDuration){
			currDurationX = currMaxDuration; 
		}
		if(currDurationY<0){
			currDurationY = 0;
		}
		if(currDurationY>currMaxDuration){
			currDurationY = currMaxDuration; 
		}
		
				
		switch(direction){
			case JwAnimator.ALL:{
				break;
			}case JwAnimator.LEFT:{
				break;
			}case JwAnimator.RIGHT:{
				anim.setCurrentPlayTime(currDurationX);
				break;
			}case JwAnimator.TOP:{
				break;
			}case JwAnimator.BOTTOM:{
				anim.setCurrentPlayTime(currDurationY);
				break;
			}case JwAnimator.HORIZONTAL:{
				break;
			}case JwAnimator.VERTICALITY:{
				break;
			}case JwAnimator.NONE:{
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

	public void setAccelerator(float accelerator){
		this.accelerator = accelerator;
	}
	
	public float getAccelerator(){
		return accelerator;
	}
}
