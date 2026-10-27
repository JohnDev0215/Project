package com.android.systemui.plugins.statusbar.phone;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.annotations.ProvidesInterface;

/* JADX INFO: loaded from: classes.dex */
@ProvidesInterface(action = NavGesture.ACTION, version = 1)
public interface NavGesture extends Plugin {
    public static final String ACTION = "com.android.systemui.action.PLUGIN_NAV_GESTURE";
    public static final int VERSION = 1;

    public interface GestureHelper {
        default void destroy() {
        }

        void onDarkIntensityChange(float f);

        void onDraw(Canvas canvas);

        boolean onInterceptTouchEvent(MotionEvent motionEvent);

        void onLayout(boolean z, int i, int i2, int i3, int i4);

        void onNavigationButtonLongPress(View view);

        boolean onTouchEvent(MotionEvent motionEvent);

        void setBarState(boolean z, boolean z2);
    }

    GestureHelper getGestureHelper();
}
