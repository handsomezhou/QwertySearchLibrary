package com.handsomezhou.qwertysearchdemo.application;

import android.app.Application;
import android.content.Context;

public class QwertySearchApplication extends Application {
	private static Context mContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext=getApplicationContext();
	}
	
	public static Context getContextObject(){
		return mContext;
	}
}
