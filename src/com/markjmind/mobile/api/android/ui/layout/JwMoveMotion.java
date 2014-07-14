package com.markjmind.mobile.api.android.ui.layout;

import android.annotation.SuppressLint;
import android.drm.DrmStore.Action;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

import com.markjmind.mobile.api.android.ui.layout.JwMotion.ACTION;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.MoveMotionListener;

@SuppressLint("NewApi")
public class JwMoveMotion implements MoveMotionListener{
	private View view;
	
	private ACTION action;
	
	public JwMoveMotion(View view){
		this.view = view;
		action = ACTION.ALL;
	}
	
	public void move(float x, float y){
		switch (action) {
	      case ALL:
	    	  allMove(x,y);
	          break;
	      case LEFT:
	    	  leftMove(x);
	          break;
	      case RIGHT:
	    	  rightMove(x);
	          break;
	      case TOP:
	    	  topMove(x);
	          break;
	      case BOTTOM:
	    	  bottomMove(x);
	          break;
	      case HORIZONTAL:
	    	  horizontalMove(x);
	          break;
	      case VERTICALITY:
	    	  verticalityMove(x);
	          break;
	    }
	}
	
	public void setMoveAction(ACTION action){
		this.action = action;
	}
	
	private void allMove(float x, float y){
		view.setX(view.getX()+x);
		view.setY(view.getY()+y);
	}
	
	private void leftMove(float x){
		if(x<0)
			view.setX(view.getX()+x);
	}
	private void rightMove(float x){
		if(x>0)
			view.setX(view.getX()+x);
	}
	private void topMove(float y){
		if(y<0)
			view.setY(view.getY()+y);
	}
	private void bottomMove(float y){
		if(y>0)
			view.setY(view.getY()+y);
	}
	private void horizontalMove(float x){
		view.setX(view.getX()+x);
	}
	private void verticalityMove(float y){
		view.setY(view.getY()+y);
	}
}
