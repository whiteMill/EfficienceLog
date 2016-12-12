package com.stk.utils;

import android.content.Context;
import android.view.WindowManager;

public class ScreenUtils {

	public static int getDeviceWidth(Context aContext) {
		WindowManager wm = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}

}
