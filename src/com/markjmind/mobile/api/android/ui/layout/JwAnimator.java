package com.markjmind.mobile.api.android.ui.layout;

import java.util.ArrayList;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * ValueAnimation이 모션에 따라 애니메이션을<br>
 * 표현할수 있게 해준다. 
 * @author 오재웅
 * @phone 010-2898-7850
 * @email markjmind@gmail.com
 * @date 2014. 7. 1.
 */
@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
public class JwAnimator extends SimpleOnGestureListener{
	
	/**
	 * 스크롤 관련 리스너
	 */
	public interface OnScrollListener{
		public void startScroll(View playView,ArrayList<JwMotionAdapter> adapterList);
		public boolean scroll(View view, float x, float y, ArrayList<JwMotionAdapter> adapterList);
		public void endScroll(View playView, ArrayList<JwMotionAdapter> adapterList);
		
	}
	
	// 모션 상태
	public static enum ACTION{
		NONE,SCROLL,FLING
	}
	
	/**
	 * 모션 방향 상수
	 */
	public final static int NONE = 0x00000001;
	public final static int LEFT = 0x00000002;
	public final static int RIGHT = 0x00000004;
	public final static int TOP = 0x0000008;
	public final static int BOTTOM = 0x00000010;
	public final static int HORIZONTAL = LEFT|RIGHT;
	public final static int VERTICALITY = TOP|BOTTOM;
	public final static int ALL = HORIZONTAL|VERTICALITY;
	
	
	private GestureDetector gestureDetector;
	private JwMotionSet motionSet;
	private Context context;
	
	private ACTION currAction=ACTION.NONE;
	private boolean isFirstMove = false;
	private float tempRawX=0f,tempRawY=0f;
	private View playView;
	private OnScrollListener onScrollListener;
	
	
	public JwAnimator(Context context){
		this.context = context;
		init();
		
	} 
	/**
	 * 초기화 함수
	 */
	private void init(){
		motionSet = new JwMotionSet();
	}
	
	/**
	 * 모션에 따라 play할 ValueAnimator 지정한다. 
	 * @param playView ValueAnimator 타겟View(애니메이션할 뷰)
	 * @param valueAnimator ValueAnimator
	 * @param direction 모션 방향
	 * @return
	 */
	public MotionBuilder play(View playView, ValueAnimator valueAnimator, int direction){
		this.playView = playView;
		return motionSet.play(playView, valueAnimator, direction);
	}
	
	/**
	 * 모션에 따라 play할 ObjectAnimator 지정한다. 
	 * @param objectAnimator ObjectAnimator
	 * @param direction 모션 방향
	 * @return
	 */
	public MotionBuilder play(ObjectAnimator objectAnimator, int direction){
		return play((View)objectAnimator.getTarget(),objectAnimator, direction);
	}
	
	/**
	 * 현재 설정된 play 타겟 view를 리턴한다.
	 * @return playView
	 */
	public View getPlayView(){
		return this.playView;
	}
	/**
	 * 애니메이션에 따라 표현되는 모션의 GestureDetector 를 반환한다. 
	 * @return GestureDetector
	 */ 
	public GestureDetector getGestureDetector(){
		if(gestureDetector==null){
			gestureDetector = new GestureDetector(context, this);
		}
		gestureDetector.setIsLongpressEnabled(false);
		return gestureDetector;
	}
	
	/**
	 * 전체 Scroll에 대한 리스너를 정의한다.<br>
	 * 모션에 따른 애니메이션들을 set으로 조합하여 play하는데<br>
	 * 전체 set에 대한 scroll 라이프 사이클에 관한 리스너를 설정한다. 
	 * @param onScrollListener
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener){
		this.onScrollListener = onScrollListener;
	}
	
	/**
	 * 전체 Scroll에 대한 리스너를 반환한다.
	 * @return OnScrollListener
	 */
	public OnScrollListener getOnScrollListener(){
		return this.onScrollListener;
	}
	/**
	 * 개별 애니메이터에 대에 해당하는 index의 onScrollListener를 설정한다.
	 * @param onScrollListener
	 * @param index
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener,int index){
		motionSet.adapterList.get(index).setOnScrollListener(onScrollListener);
	}
	
	/**
	 * 개별 애니메이터에 대에 해당하는 index의 onScrollListener를 반환한다.
	 * @return OnScrollListener
	 */
	public OnScrollListener getOnScrollListener(int index){
		return motionSet.adapterList.get(index).getOnScrollListener();
	}
	
	/**
	 * 애니메이션과 메칭되 TouchListener를 반환한다.
	 * @return OnTouchListener
	 */
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
	
	/**
	 * 애니메이션이 끝났을때 액션에 따른 처리를 한다.
	 */
	private void endingMotion(){
		//TODO 모션이 끝날때 처리
		switch(currAction){
			case SCROLL: {
				if(onScrollListener!=null){
					onScrollListener.endScroll(null, motionSet.adapterList);
				}
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
				adt.scroll(adt.getTargetView(), x, y, motionSet.adapterList);
			}
			if(onScrollListener!=null){
				onScrollListener.scroll(null, x, y, motionSet.adapterList);
			}
		}else{
			if(onScrollListener!=null){
				onScrollListener.startScroll(playView,motionSet.adapterList);
			}
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
