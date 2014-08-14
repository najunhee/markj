package com.markjmind.mobile.api.android.ui.layout;

import android.view.View;

import com.markjmind.mobile.api.android.ui.layout.JwMotion.FlingMotionListener;
import com.markjmind.mobile.api.android.ui.layout.JwMotion.ScrollMotionListener;

public class JwMotionAdapter implements ScrollMotionListener,FlingMotionListener{
	public int joinDuration = 0;
	public int duration = 0;

	@Override
	public boolean fling(float distanceX, float distanceY, float velocityX, float velocityY) {
		return true;
	}

	@Override
	public void startScroll(View view, MotionSize motionSize) {
	}

	@Override
	public boolean scroll(View view, float x, float y, MotionSize motionSize) {
		return true;
	}

	@Override
	public void endScroll(View view, MotionSize motionSize) {
	}

}
