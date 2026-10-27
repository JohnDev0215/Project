package com.byd.view;

import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import b.a.d;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class ViewMotionListener implements View.OnTouchListener {
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private static final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static final int TAP = 3;
    private static final int TAP_CANCEL = 4;
    private static final int TAP_LONG_CLICK = 5;
    private static final int TAP_TIMEOUT = 280;
    protected View actionView;
    private long lastUpTime;
    protected boolean listenLongPress;
    private OnDoubleTapListener onDoubleTapListener;
    protected TapHandler tapHandler;

    public interface OnDoubleTapListener {
        void onActionDown(View view);

        void onDoubleTap(View view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TapHandler extends d<ViewMotionListener> {
        public TapHandler(ViewMotionListener viewMotionListener) {
            super(viewMotionListener);
        }

        @Override // b.a.d
        public void handleMessage(ViewMotionListener viewMotionListener, Message message) {
            int i = message.what;
            if (i == 3) {
                viewMotionListener.removeAllEventAfterDown();
                viewMotionListener.actionView.performClick();
            } else if (i == 4) {
                removeMessages(3);
            } else if (i == 5 && viewMotionListener.listenLongPress) {
                viewMotionListener.removeAllEventAfterDown();
                viewMotionListener.actionView.performLongClick();
                viewMotionListener.onLongPress();
            }
        }
    }

    public ViewMotionListener() {
        this.listenLongPress = true;
        this.tapHandler = new TapHandler(this);
    }

    public ViewMotionListener(OnDoubleTapListener onDoubleTapListener) {
        this();
        this.onDoubleTapListener = onDoubleTapListener;
    }

    protected boolean doEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            onActionDown();
            w.c("==ACTION_DOWN==" + this.actionView.getTag());
            this.tapHandler.removeCallbacksAndMessages(null);
            this.tapHandler.sendEmptyMessageDelayed(3, (long) DOUBLE_TAP_TIMEOUT);
            this.tapHandler.sendEmptyMessageDelayed(5, (long) LONG_PRESS_TIMEOUT);
            this.tapHandler.sendEmptyMessageDelayed(4, 280L);
        } else if (action == 1) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            w.c("==ACTION_UP==" + this.actionView.getTag());
            if (jCurrentTimeMillis - this.lastUpTime <= DOUBLE_TAP_TIMEOUT) {
                removeAllEventAfterDown();
                this.lastUpTime = 0L;
                onDoubleClick();
                OnDoubleTapListener onDoubleTapListener = this.onDoubleTapListener;
                if (onDoubleTapListener != null) {
                    onDoubleTapListener.onDoubleTap(this.actionView);
                }
            } else {
                this.tapHandler.removeMessages(4);
                this.tapHandler.removeMessages(5);
                onActionUp();
            }
            this.lastUpTime = jCurrentTimeMillis;
        } else if (action == 3) {
            w.c("==ACTION_CANCEL==" + this.actionView.getTag());
            removeAllEventAfterDown();
            onActionCancel();
        }
        return true;
    }

    protected void onActionCancel() {
    }

    protected void onActionDown() {
        OnDoubleTapListener onDoubleTapListener = this.onDoubleTapListener;
        if (onDoubleTapListener != null) {
            onDoubleTapListener.onActionDown(this.actionView);
        }
    }

    protected void onActionUp() {
    }

    protected void onDoubleClick() {
    }

    protected void onLongPress() {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!view.isEnabled()) {
            return false;
        }
        if (motionEvent.getPointerCount() <= 1 || motionEvent.getAction() == 3) {
            this.actionView = view;
            return doEvent(motionEvent);
        }
        w.c("more pointer count : " + motionEvent.getPointerCount() + " " + motionEvent.getAction());
        this.tapHandler.removeCallbacksAndMessages(null);
        return false;
    }

    protected void removeAllEventAfterDown() {
        this.tapHandler.removeCallbacksAndMessages(null);
    }

    public void setListenLongPress(boolean z) {
        this.listenLongPress = z;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.onDoubleTapListener = onDoubleTapListener;
    }
}
