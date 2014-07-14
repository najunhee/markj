package com.markjmind.mobile.api.android.ui.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;

@SuppressLint("NewApi")
public class JwMotion extends SimpleOnGestureListener{
	private View view;
	private GestureDetector gestureDetector;
	public JwMoveMotion move;
	private MoveMotionListener moveMotionListener;
	private boolean isFirstMove = false;
	private float tempRawX=0f,tempRawY=0f;
    
	public static enum ACTION{
		NONE,
		ALL,
		LEFT,
		RIGHT,
		TOP,
		BOTTOM,
		HORIZONTAL,
		VERTICALITY
	}
	
	
	interface MoveMotionListener{
		public void move(float x, float y);
	}
	
	public void setMoveMotionListener(MoveMotionListener moveMotion){
		this.moveMotionListener = moveMotion;
	}
	/**
     * fling 감지 수치
     */
    private float flingSensing = 0.3f;
    

	public JwMotion(View view){
		this.view = view;
		move = new JwMoveMotion(view);
		setMoveMotionListener(move);
	}
	
	public GestureDetector getGestureDetector(){
		if(gestureDetector==null){
			gestureDetector = new GestureDetector(view.getContext(), this);
		}
		gestureDetector.setIsLongpressEnabled(false);
		return gestureDetector;
	}
	
	public OnTouchListener getOnTouchListener(){
		OnTouchListener otl = new OnTouchListener() {
			@Override
			public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
				getGestureDetector().onTouchEvent(paramMotionEvent);
				return true;
			}
		};
		return otl;
	}
	
	
	
	 @Override
     public boolean onDown(MotionEvent ev) {
		 isFirstMove = true;
         return super.onDown(ev);
     }

    @Override
     public boolean onSingleTapUp(MotionEvent ev) {
         return super.onSingleTapUp(ev);
     }

    @Override
     public boolean onSingleTapConfirmed(MotionEvent ev) {
         return super.onSingleTapConfirmed(ev);
     }

    @Override
     public boolean onDoubleTap(MotionEvent ev) {
         Log.w("TEST", "onDoubleTap = "+ev.toString());
         return super.onDoubleTap(ev);
     }

    @Override
     public boolean onDoubleTapEvent(MotionEvent ev) {
         return super.onDoubleTapEvent(ev);
     }

	@Override
	public void onShowPress(MotionEvent e) {
		super.onShowPress(e);
	}
	
	@Override
	public void onLongPress(MotionEvent e) {
		super.onLongPress(e);
	}
	
    @Override
     public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    	if(moveMotionListener!=null){
    		if(!isFirstMove){
    			float x = e2.getRawX()-tempRawX;
    			float y = e2.getRawY()-tempRawY;
    			moveMotionListener.move(x, y);
    		}
    		tempRawX = e2.getRawX();
			tempRawY = e2.getRawY();
    	}
    	isFirstMove = false;
        return true;
     }


    @Override
     public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    	float distanceX = e2.getX()-e1.getX();
		float distanceY = e2.getY()-e1.getY();
		
		if(velocityX>velocityY){
			if(Math.abs(distanceX)<flingSensing){
				return Hfling(distanceX>0,Math.abs(distanceX),velocityX);
			}
		}else{
			if(Math.abs(distanceY)<flingSensing){
				return Vfling(distanceY>0,Math.abs(distanceY),velocityY);
			}
		}
         return true;
    }
    
    public boolean Hfling(boolean isLeft, float distanceX,float velocityX){
    	return true;
    }
    
    public boolean Vfling(boolean isDown, float distanceY,float velocityX){
    	return true;
    }
    
}
