package com.markjmind.mobile.api.android.ui.layout;

import java.util.ArrayList;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;

import com.markjmind.mobile.api.android.ui.layout.JwAnimator.OnScrollListener;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.FlingMotionListener;

@SuppressLint("NewApi") 
public class JwMotionAdapter implements FlingMotionListener{
	private float density;
	long joinDuration = 0;
	long startDuration = 0;
	// TODO TOUCH와 똑같이 VIEW가 움직이려면 화면 크게에 맞게 duration이 변경되어야해서
	// 실제 play 되는 Duration과 사용자가 지정한 Duration이 다를수 있는데 이부분에 대한 해결책을 생각해야함
	long playDuration = 0;

	private OnScrollListener onScrollListener;
	private int direction;
	private ValueAnimator anim;
	private float currX=0f,currY=0f;
	private View tagerView;
	
	private float accelerator;
	
	private long currDurationMax = 0;
	private long currDurationX = 0;
	private long currDurationY = 0;
	
	public JwMotionAdapter(View tagerView, ValueAnimator anim, int direction){
		this.density = tagerView.getResources().getDisplayMetrics().density;
		this.tagerView = tagerView;
		this.anim = anim;
		this.direction = direction;
		
		this.joinDuration = 0;
		this.startDuration = anim.getStartDelay();
		this.playDuration = anim.getDuration();
		
		this.currDurationMax = playDuration-joinDuration-startDuration;
		this.currDurationX = (long)(currX-joinDuration-startDuration);
		this.currDurationY = (long)(currY-joinDuration-startDuration);
		
		this.accelerator = 1.0f;
	}
	
	public void setOnScrollListener(OnScrollListener onScrollListener){
		this.onScrollListener = onScrollListener;
	}
	
	public OnScrollListener getOnScrollListener(){
		return this.onScrollListener;
	}
	
	@Override
	public boolean fling(float distanceX, float distanceY, float velocityX, float velocityY) {
		return true;
	}

	public void startScroll(View playView, ArrayList<JwMotionAdapter> adapterList) {
		if(onScrollListener!=null){
			onScrollListener.startScroll(playView, adapterList);
		}
	}
	public boolean scroll(View view, float x, float y, ArrayList<JwMotionAdapter> adapterList) {
		if(direction== JwAnimator.LEFT){
			currX-=x*accelerator/density;
		}else{
			currX+=x*accelerator/density;
		}
		if(direction== JwAnimator.TOP){
			currY-=y*accelerator/density;
		}else{
			currY+=y*accelerator/density;
		}
		// TODO currMaxDuration을 전역변수로 빼서 연산 줄이기
		currDurationX = (long)(currX-joinDuration-startDuration);
		currDurationY = (long)(currY-joinDuration-startDuration);
		
		if(currDurationX<0){
			currDurationX = 0;
		}
		if(currDurationX>currDurationMax){
			currDurationX = currDurationMax; 
		}
		if(currDurationY<0){
			currDurationY = 0;
		}
		if(currDurationY>currDurationMax){
			currDurationY = currDurationMax; 
		}
		
				
		switch(direction){
			case JwAnimator.ALL:{
				break;
			}case JwAnimator.LEFT:{
				anim.setCurrentPlayTime(currDurationX);
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
		if(onScrollListener!=null){
			return onScrollListener.scroll(view, currDurationX, currDurationY, adapterList);
		}
		return true;
	}

	public void endScroll(View playView, ArrayList<JwMotionAdapter> adapterList) {
		if(onScrollListener!=null){
			onScrollListener.endScroll(playView, adapterList);
		}
	}
	
	public long getCurrDurationX(){
		return this.currDurationX;
	}
	
	public long getCurrDurationY(){
		return this.currDurationY;
	}
	
	public long getcurrDurationMax(){
		return this.currDurationMax;
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
