package com.markjmind.mobile.api.android.ui.layout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.markjmind.mobile.api.android.ui.layout.JwMotion.DIRECTION;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.MoveMotionListener;

@SuppressLint("NewApi")
public class JwMoveMotion implements MoveMotionListener{
	private View view;
	private DIRECTION action;
	private boolean result;
	private float minX;
	private float maxX;
	private float minY;
	private float maxY;
	private DIRECTION magnet;
	private ObjectAnimator moveAnimX;
	private ObjectAnimator moveAnimY;
	private TimeInterpolator interpolator;
	
	public JwMoveMotion(View view){
		this.view = view;
		init();
	}
	
	private void init(){
		action = DIRECTION.ALL;
		magnet = DIRECTION.ALL;
		interpolator = new AccelerateDecelerateInterpolator();
		result = true;
	}
	
	
	public boolean move(float x, float y){
		switch (action) {
	      case ALL:
	    	  setMoveX(x);
	    	  setMoveY(y);
	          break;
	      case LEFT:
	    	  if(x<0)
	  			setMoveX(x);
	          break;
	      case RIGHT:
	    	  if(x>0)
	  			setMoveX(x);
	          break;
	      case TOP:
	    	  if(y<0)
	  			setMoveY(y);
	          break;
	      case BOTTOM:
	    	  if(y>0)
	  			setMoveY(y);
	          break;
	      case HORIZONTAL:
	    	  setMoveX(x);
	          break;
	      case VERTICALITY:
	    	  setMoveY(y);
	          break;
	      case NONE:
	    	  return true;
	    }
		return result;
	}
	
	public JwMoveMotion setResult(boolean result){
		this.result = result;
		return this;
	}
	
	public JwMoveMotion setMoveAction(DIRECTION action){
		this.action = action;
		return this;
	}
	
	public JwMoveMotion setMoveX(float x){
		float moving = view.getX()+x;
		if(moving<=minX){
			view.setX(minX);
			// TODO X축이 0일때 처리
		}else if(moving>=maxX){
			view.setX(maxX);
		}else{
			view.setX(moving);
		}
		return this;
	}
	public JwMoveMotion setMoveY(float y){
		float moving = view.getY()+y;
		if(moving<=minY){
			view.setY(minY);
			// TODO X축이 0일때 처리
		}else if(moving>=maxY){
			view.setY(maxY);
		}else{
			view.setY(moving);
		}
		return this;
	}
	
	
	/**
	 * 자석효과 설정
	 * @param magnet 자석으로 붙일 방향
	 * @return
	 */
	public JwMoveMotion setMagnet(DIRECTION magnet){
		this.magnet = magnet;
		return this;
	}
	
	public void magnet(int duration){
		float currX = view.getX();
		float currY = view.getY();
		boolean right = (maxX-minX)/2<currX;
		boolean bottom = (maxY-minY)/2<currY;
		AnimatorSet animSet = new AnimatorSet();
		moveAnimX = ObjectAnimator.ofFloat(view,"x",currX,minX);
		moveAnimY = ObjectAnimator.ofFloat(view,"y",currY,minY);
		
		if(right){
			moveAnimX.setFloatValues(currX,maxX);
		}
		if(bottom){
			moveAnimY.setFloatValues(currY,maxY);
		}
		moveAnimX.setInterpolator(interpolator);
		moveAnimY.setInterpolator(interpolator);
		moveAnimX.setDuration(duration);
		moveAnimY.setDuration(duration);
		switch (magnet) {
	      case ALL:
	    	  animSet.play(moveAnimX).with(moveAnimY);
	          break;
	      case LEFT:
	          break;
	      case RIGHT:
	          break;
	      case TOP:
	          break;
	      case BOTTOM:
	          break;
	      case HORIZONTAL:
	          break;
	      case VERTICALITY:
	          break;
	      case NONE:
	          return;
	    }
		animSet.start();
	}
	
	public float getMinX() {
		return minX;
	}
	public float getMaxX() {
		return maxX;
	}
	public float getMinY() {
		return minY;
	}
	public float getMaxY() {
		return maxY;
	}
	public JwMoveMotion setSize(float minX,float minY,float maxX,float maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		return this;
	}
    
}
