package com.markjmind.mobile.api.android.ui.layout;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
public class JwMotion extends SimpleOnGestureListener{
	/**
	 * 이동되는 타겟뷰
	 */
	private View view;
	private GestureDetector gestureDetector;
	
	public JwMoveMotion move;
	private MoveMotionListener moveMotionListener;
	private boolean isFirstMove = false;
	private float tempRawX=0f,tempRawY=0f;
	
	public JwFlingMotion fling;
	private FlingMotionListener flingMotionListener;
    
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
		public boolean move(float x, float y);
	}
	interface FlingMotionListener{
		public boolean fling(float distanceX, float distanceY,float velocityX, float velocityY);
	}
	
	public JwMotion setMoveMotionListener(MoveMotionListener moveMotion){
		this.moveMotionListener = moveMotion;
		return this;
	}
	public JwMotion setFlingMotionListener(FlingMotionListener flingMotionListener){
		this.flingMotionListener = flingMotionListener;
		return this;
	}
	/**
     * fling 감지 수치
     */
    private float flingSensing = 0.3f;
    

	public JwMotion(View view){
		this.view = view;
		move = new JwMoveMotion(view);
		fling = new JwFlingMotion(view);
		setMoveMotionListener(move);
		setFlingMotionListener(fling);
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
		flingMotionListener.fling(distanceX, distanceY, velocityX, velocityY);
         return true;
    }
    
}
