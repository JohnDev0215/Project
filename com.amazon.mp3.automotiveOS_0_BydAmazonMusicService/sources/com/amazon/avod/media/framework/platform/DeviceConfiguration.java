package com.amazon.avod.media.framework.platform;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import com.amazon.avod.util.DLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceConfiguration {
    private int mScreenHeight;
    private int mScreenWidth;

    public void intialize(Context context) {
        Display display = ((DisplayManager) context.getSystemService("display")).getDisplay(0);
        updateDeviceConfigurationAPI1(display);
        updateDeviceConfigurationAPI15(display);
        DLog.logf("DeviceConfiguration Width=%d, Height=%d", Integer.valueOf(this.mScreenWidth), Integer.valueOf(this.mScreenHeight));
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    private void updateDeviceConfigurationAPI1(Display display) {
        setWidthHeight(display.getWidth(), display.getHeight());
    }

    private void updateDeviceConfigurationAPI15(Display display) {
        Method method;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            method = display.getClass().getMethod("getRealMetrics", DisplayMetrics.class);
        } catch (NoSuchMethodException e) {
            DLog.exceptionf(e, "Could not get %s method using reflection", "getRealMetrics");
            method = null;
        } catch (SecurityException e2) {
            DLog.exceptionf(e2, "Could not get %s method using reflection", "getRealMetrics");
            method = null;
        }
        if (method != null) {
            try {
                method.invoke(display, displayMetrics);
                setWidthHeight(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } catch (IllegalAccessException e3) {
                DLog.exceptionf(e3, "Could not invoke %s method using reflection", "getRealMetrics");
            } catch (IllegalArgumentException e4) {
                DLog.exceptionf(e4, "Could not invoke %s method using reflection", "getRealMetrics");
            } catch (InvocationTargetException e5) {
                DLog.exceptionf(e5, "Could not invoke %s method using reflection", "getRealMetrics");
            }
        }
    }

    private void setWidthHeight(int i, int i2) {
        if (i > i2) {
            this.mScreenWidth = i;
            this.mScreenHeight = i2;
        } else {
            this.mScreenWidth = i2;
            this.mScreenHeight = i;
        }
    }
}
