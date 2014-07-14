package com.markjmind.mobile.api.android.ui.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

public class JwMotionLayout extends FrameLayout{
	private Animation inAnimation;
	private Animation outAnimation;
	
	private JwMotion motion;
	
	
	public JwMotionLayout(Context context) {
		super(context);
		 init();
	}

	public JwMotionLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		 init();
	}

	public JwMotionLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	private void init(){
		this.post(new Runnable() {
			@Override
			public void run() {
				motion = new JwMotion(getChildAt(0));
				setOnTouchListener(motion.getOnTouchListener());
			}
		});
	}

	
	/**
	 * 기존 Animation을 막아놓는다.
	 */
	@Override
	public void setAnimation(Animation animation) {
	}
	/**
	 * 기존 Animation을 막아놓는다.
	 */
	@Override
	public void startAnimation(Animation animation) {
		
	}
	
	public void setInAnimation(){
		
	}
	
	public void setOutAnimation(){
		
	}
	  

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		boolean gestureResult = motion.getGestureDetector().onTouchEvent(event);
//		return gestureResult;
//	}
	
	 private int pixelToDp(float value) {
			return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, getContext().getResources().getDisplayMetrics());
		}
}


