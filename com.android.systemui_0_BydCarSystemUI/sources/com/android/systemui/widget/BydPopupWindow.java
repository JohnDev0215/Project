package com.android.systemui.widget;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class BydPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener, View.OnTouchListener {
    private int delayTime;
    private H handler;
    protected final int what_dismiss;

    public BydPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        this.what_dismiss = 1;
        this.delayTime = 5000;
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
        setFocusable(false);
        setOutsideTouchable(true);
        setOnDismissListener(this);
        setTouchInterceptor(this);
        fitPopupWindowOverStatusBar(true);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        if (this.handler == null) {
            this.handler = new H(Looper.getMainLooper());
        }
        this.handler.sendEmptyMessageDelayed(1, this.delayTime);
        Log.d("BydPopupWindow", "showAtLocation:");
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        if (this.handler != null) {
            Log.d("BydPopupWindow", "onDismiss");
            this.handler.removeMessages(1);
            this.handler = null;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        Log.d("BydPopupWindow", "action:" + action);
        if (action == 0) {
            Log.d("BydPopupWindow", "down-remove");
            removeTimer();
            return false;
        }
        if (action != 1 && action != 3 && action != 4) {
            return false;
        }
        startTimer();
        Log.d("BydPopupWindow", "down-send");
        return false;
    }

    public void removeTimer() {
        H h = this.handler;
        if (h != null) {
            h.removeMessages(1);
        }
    }

    public void startTimer() {
        H h = this.handler;
        if (h != null) {
            h.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, this.delayTime);
        }
    }

    private class H extends Handler {
        public H(Looper mainLooper) {
            super(mainLooper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1 && BydPopupWindow.this.isShowing()) {
                BydPopupWindow.this.dismiss();
            }
        }
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
