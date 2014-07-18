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
import com.markjmind.mobile.api.android.ui.layout.JwMotion.ScrollMotionListener;

@SuppressLint("NewApi")
public class JwMoveMotion implements ScrollMotionListener{
	private DIRECTION action;
	private boolean result;
	private DIRECTION magnet;
	private ObjectAnimator moveAnimX;
	private ObjectAnimator moveAnimY;
	private TimeInterpolator interpolator;
	
	public JwMoveMotion(){
		init();
	}
	
	private void init(){
		action = DIRECTION.ALL;
		magnet = DIRECTION.ALL;
		interpolator = new AccelerateDecelerateInterpolator();
		result = true;
	}
	
	
	public boolean scroll(View view, float x, float y, MotionSize size){
		switch (action) {
	      case ALL:
	    	  setMoveX(view,x,size);
	    	  setMoveY(view,y,size);
	          break;
	      case LEFT:
	    	  if(x<0)
	  			setMoveX(view,x,size);
	          break;
	      case RIGHT:
	    	  if(x>0)
	  			setMoveX(view,x,size);
	          break;
	      case TOP:
	    	  if(y<0)
	  			setMoveY(view,y,size);
	          break;
	      case BOTTOM:
	    	  if(y>0)
	  			setMoveY(view,y,size);
	          break;
	      case HORIZONTAL:
	    	  setMoveX(view,x,size);
	          break;
	      case VERTICALITY:
	    	  setMoveY(view,y,size);
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
	
	public JwMoveMotion setMoveX(View view,float x, MotionSize size){
		float moving = view.getX()+x;
		if(moving<=size.minX){
			view.setX(size.minX);
			// TODO X축이 0일때 처리
		}else if(moving>=size.maxX){
			view.setX(size.maxX);
		}else{
			view.setX(moving);
		}
		return this;
	}
	public JwMoveMotion setMoveY(View view,float y, MotionSize size){
		float moving = view.getY()+y;
		if(moving<=size.minY){
			view.setY(size.minY);
			// TODO X축이 0일때 처리
		}else if(moving>=size.maxY){
			view.setY(size.maxY);
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
	
	public void magnet(View view,int duration, MotionSize size){
		float currX = view.getX();
		float currY = view.getY();
		boolean right = (size.maxX-size.minX)/2<currX;
		boolean bottom = (size.maxY-size.minY)/2<currY;
		AnimatorSet animSet = new AnimatorSet();
		moveAnimX = ObjectAnimator.ofFloat(view,"x",currX,size.minX);
		moveAnimY = ObjectAnimator.ofFloat(view,"y",currY,size.minY);
		
		if(right){
			moveAnimX.setFloatValues(currX,size.maxX);
		}
		if(bottom){
			moveAnimY.setFloatValues(currY,size.maxY);
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
	

	@Override
	public void endScroll(View view, MotionSize size) {
		magnet(view,300,size);
	}
    
}
