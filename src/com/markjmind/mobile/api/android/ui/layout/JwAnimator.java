package com.markjmind.mobile.api.android.ui.layout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
public class JwAnimator extends SimpleOnGestureListener{
	/**
	 * 이동되는 타겟뷰
	 */
//	private View view;
	private GestureDetector gestureDetector;
	private JwMotionSet motionSet;
	private Context context;
	
	public static enum ACTION{
		NONE,SCROLL,FLING
	}
	private ACTION currAction=ACTION.NONE;
	private boolean isFirstMove = false;
	private float tempRawX=0f,tempRawY=0f;
	
	private MotionSize size;
	public static enum TAGET{
		SELF,PARENTS
	}
	
	public final static int NONE = 0x00000001;
	public final static int LEFT = 0x00000002;
	public final static int RIGHT = 0x00000004;
	public final static int TOP = 0x0000008;
	public final static int BOTTOM = 0x00000010;
	public final static int HORIZONTAL = LEFT|RIGHT;
	public final static int VERTICALITY = TOP|BOTTOM;
	public final static int ALL = HORIZONTAL|VERTICALITY;
	
	public JwAnimator(Context context){
		this.context = context;
//		this.view = view;
		init();
	} 
	
	private void init(){
//		view.post(new Runnable() {
//			@Override
//			public void run() {
//				if(size==null){
//					ViewGroup parents = (ViewGroup)view.getParent();
//					setSize(0,0,parents.getWidth()-view.getWidth(),parents.getHeight()-view.getHeight());
//				}
//			}
//		});
		motionSet = new JwMotionSet();
	}
	
	public MotionBuilder play(View targetView, ValueAnimator valueAnimator, int direction){
		return motionSet.play(targetView, valueAnimator, direction);
	}
	
	public MotionBuilder play(ObjectAnimator objectAnimator, int direction){
		return motionSet.play(objectAnimator, direction);
	}
	
	public JwAnimator setSize(MotionSize size){
		this.size = size;
		return this;
	}
	public JwAnimator setSize(float minX,float minY,float maxX,float maxY) {
		this.size = new MotionSize(minX, maxX, minY, maxY);
		return this;
	}
	
	public GestureDetector getGestureDetector(){
		if(gestureDetector==null){
			gestureDetector = new GestureDetector(context, this);
		}
		gestureDetector.setIsLongpressEnabled(false);
		return gestureDetector;
	}
	
	public OnTouchListener getOnTouchListener() {
		OnTouchListener otl = new OnTouchListener() {
			@Override
			public boolean onTouch(View paramView, MotionEvent event) {
				final int action = event.getAction();
				switch (action & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN: {
						break;
					}
					case MotionEvent.ACTION_UP: {
						endingMotion();
						currAction=ACTION.NONE;
						break;
					}
					case MotionEvent.ACTION_MOVE: {
						break;
					}
					case MotionEvent.ACTION_POINTER_DOWN: {
						break;
					}
					case MotionEvent.ACTION_CANCEL: {
						break;
					}
	
					case MotionEvent.ACTION_POINTER_UP: {
						break;
					}
				}
				getGestureDetector().onTouchEvent(event);
				return true;
			}
		};
		return otl;
	}
	private void endingMotion(){
		//TODO 모션이 끝날때 처리
		switch(currAction){
			case SCROLL: {
				
				break;
			}
			case FLING: {
				break;
			}
			case NONE: {
				break;
			}
		}
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
    	currAction = ACTION.SCROLL;
		if(!isFirstMove){
			float x = e2.getRawX()-tempRawX;
			float y = e2.getRawY()-tempRawY;
			for(int i=0;i<motionSet.adapterList.size();i++){
				JwMotionAdapter adt = motionSet.adapterList.get(i);
				adt.scroll(adt.getTargetView(), x, y, size);
			}
		}else{
			//TODO startScroll 처리
		}
		tempRawX = e2.getRawX();
		tempRawY = e2.getRawY();
    	isFirstMove = false;
        return true;
     }
    
    @Override
     public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    	currAction = ACTION.FLING;
    	float distanceX = e2.getX()-e1.getX();
		float distanceY = e2.getY()-e1.getY();
//		if(flingMotionListener!=null){
//			flingMotionListener.fling(distanceX, distanceY, velocityX, velocityY);
//		}
        return true;
    }
}
