package com.markjmind.mobile.api.android.ui.layout;

import android.view.View;

import com.markjmind.mobile.api.android.ui.layout.JwMotion.ACTION;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.FlingMotionListener;

public class JwFlingMotion implements FlingMotionListener{
	private View view;
	private ACTION action;
	private boolean result;
	private float flingSensing = 0.3f;
	
	public JwFlingMotion(View view){
		this.view = view;
		action = ACTION.ALL;
		result = true;
	}
	@Override
	public boolean fling(float distanceX, float distanceY, float velocityX, float velocityY) {
		switch (action) {
	      case ALL:
	    	  allFling(distanceX,distanceY,velocityX,velocityY);
	          break;
	      case LEFT:
	    	  leftFling(distanceX,velocityX);
	          break;
	      case RIGHT:
	    	  rightFling(distanceX,velocityX);
	          break;
	      case TOP:
	    	  topFling(distanceY,velocityY);
	          break;
	      case BOTTOM:
	    	  bottomFling(distanceY,velocityY);
	          break;
	      case HORIZONTAL:
	    	  horizontalFling(distanceX,velocityX);
	          break;
	      case VERTICALITY:
	    	  verticalityFling(distanceY,velocityY);
	          break;
	      case NONE:
	    	  return true;
	    }
		return result;
	}

	public JwFlingMotion setResult(boolean result){
		this.result = result;
		return this;
	}
	
	public JwFlingMotion setMoveAction(ACTION action){
		this.action = action;
		return this;
	}
	
	private void allFling(float distanceX, float distanceY, float velocityX, float velocityY){
		if(velocityX>velocityY){
			if(Math.abs(distanceX)<=flingSensing){
				//가로
			}
		}else{
			if(Math.abs(distanceY)<=flingSensing){
				//세로
			}
		}
	}
	
	private void horizontalFling(float distanceX, float velocityX){
		if(Math.abs(distanceX)<=flingSensing){
			//가로
		}
	}
	private void leftFling(float distanceX, float velocityX){
		if(Math.abs(distanceX)<=flingSensing){
				//가로
		}
	}
	private void rightFling(float distanceX,float velocityX){
		if(Math.abs(distanceX)<=flingSensing){
			//가로
		}
	}
	private void verticalityFling(float distanceY, float velocityY){
		if(Math.abs(distanceY)<=flingSensing){
			//세로
		}
	}
	private void topFling(float distanceY, float velocityY){
		if(Math.abs(distanceY)<=flingSensing){
			//세로
		}
	}
	private void bottomFling(float distanceY, float velocityY){
		if(Math.abs(distanceY)<=flingSensing){
			//세로
		}
	}
	
}
