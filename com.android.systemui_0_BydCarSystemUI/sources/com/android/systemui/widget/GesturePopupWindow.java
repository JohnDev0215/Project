package com.android.systemui.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class GesturePopupWindow extends PopupWindow {
    public GesturePopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        init();
    }

    public final void fitPopupWindowOverStatusBar(boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Field declaredField = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                declaredField.setAccessible(true);
                declaredField.set(this, Boolean.valueOf(needFullScreen));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void init() {
        setWindowLayoutType(2008);
        fitPopupWindowOverStatusBar(true);
    }
}
