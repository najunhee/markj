package com.markjmind.mobile.api.android.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import android.view.View;
/**
 * 
 * @author 오재웅
 * @email markjmind@gmail.com
 */

public class JwMemberMapper {
	
	@Retention( RetentionPolicy.RUNTIME )
	@Target( ElementType.FIELD )
	public @interface getView
	{
		int value() default -1;
		String click() default "";
	}
	
	@Retention( RetentionPolicy.RUNTIME )
	@Target( ElementType.FIELD )
	public @interface getViewClick
	{
		int value() default -1;
		String click() default "";
	}
	
	@Retention( RetentionPolicy.RUNTIME )
	@Target(ElementType.TYPE)
	public @interface layout
	{
		int value();
	}
	
	@Retention( RetentionPolicy.RUNTIME )
	@Target(ElementType.TYPE)
	public @interface box
	{
		String[] value();
	}
	
	public static String[] injectionBox(Class<?> viewerClass){
		if(viewerClass.isAnnotationPresent(box.class)){
			box par = viewerClass.getAnnotation(box.class);
			return par.value();
		}else{
			throw new JwMapperException("\n["+viewerClass.getName()+"] 해당 Viewer에 @params을 지정하는 annotation의 value가 잘못되었습니다..",null);
		}
	}
	
	public static int injectionLayout(Class<?> viewerClass){
		if(viewerClass.isAnnotationPresent(layout.class)){
			layout lytId = viewerClass.getAnnotation(layout.class);
			return lytId.value();
		}else{
			throw new JwMapperException("\n["+viewerClass.getName()+"] 해당 Viewer에 @layout을 지정하는 annotation의 value가 잘못되었습니다.",null);
		}
		
	}
	
	public static void injectField(JwViewer obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			if(fields[i].isAnnotationPresent(getView.class)){
				getView ab = fields[i].getAnnotation(getView.class);
				int id = ab.value();
				id = setField(obj,id,fields[i]);
				String click = ab.click();
				if(!"".equals(click) && click.length()>0){
					obj.setOnClickListener(click, id);
				}
			}else if(fields[i].isAnnotationPresent(getViewClick.class)){
				getViewClick ab = fields[i].getAnnotation(getViewClick.class);
				int id = ab.value();
				id = setField(obj,id,fields[i]);
				String click = ab.click();
				if(!"".equals(click) && click.length()>0){
				}else{
					click = fields[i].getName();
				}
				obj.setOnClickListener(click, id);
			}
		}
	}
	
	private static int setField(JwViewer obj, int id, Field field){
		if(id==-1){
			id = JwStringID.getID(field.getName(), obj.getActivity().getApplication());
		}
		View v = obj.getView(id);
		if(v==null){
			throw new JwMapperException("\n["+obj.getClass().getName()+"] Field:"+field.getName()+", Filed에 해당하는 ID(Null)가 잘못 지정되었습니다.",null);
		}
		try {
			field.setAccessible(true);
			field.set(obj, v);
		} catch (IllegalArgumentException e) {
			throw new JwMapperException("\n["+obj.getClass().getName()+"] Field:"+field.getName()+", 잘못된 Field에 해당하는 ID를 찾을수 없습니다.",e);
		} catch (IllegalAccessException e) {
			throw new JwMapperException("\n["+obj.getClass().getName()+"] Field:"+field.getName()+", 접근권한이 없는 필드입니다.",e);
		}
		return id;
	}
	
	
	

}
