package com.byd.music.view;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public class RepeatListener implements View.OnTouchListener {
    private final View.OnClickListener clickListener;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable handlerRunnable = new Runnable() { // from class: com.byd.music.view.RepeatListener.1
        @Override // java.lang.Runnable
        public void run() {
            if (RepeatListener.this.touchedView.isEnabled()) {
                RepeatListener.this.handler.postDelayed(this, RepeatListener.this.repeatTime);
                RepeatListener.this.clickListener.onClick(RepeatListener.this.touchedView);
            } else {
                RepeatListener.this.handler.removeCallbacks(RepeatListener.this.handlerRunnable);
                RepeatListener.this.touchedView.setPressed(false);
                RepeatListener.this.touchedView = null;
            }
        }
    };
    private int initTime;
    private final int repeatTime;
    private View touchedView;

    public RepeatListener(int i, int i2, View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            throw new IllegalArgumentException("Null runnable");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        this.initTime = i;
        this.repeatTime = i2;
        this.clickListener = onClickListener;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.handler.removeCallbacks(this.handlerRunnable);
            this.handler.postDelayed(this.handlerRunnable, this.repeatTime);
            this.touchedView = view;
            view.setPressed(true);
            this.clickListener.onClick(view);
            return true;
        }
        if (action != 1 && action != 3) {
            return false;
        }
        this.handler.removeCallbacks(this.handlerRunnable);
        this.touchedView.setPressed(false);
        this.touchedView = null;
        return true;
    }
}
