package com.android.launcher3.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import com.android.launcher3.home.pageutil.CountTimer;

/* JADX INFO: loaded from: classes4.dex */
public class BaseDispatchTouchActivity extends Activity {
    private static final long COUNTDOWN_INTERVAL = 1000;
    private static final long MILLIS_IN_FUTURE = 3000;
    protected CountTimer mCountTimerView;
    private float mRawDX;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void timeStart() {
        new Handler(getMainLooper()).post(new Runnable() { // from class: com.android.launcher3.home.BaseDispatchTouchActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (BaseDispatchTouchActivity.this.mCountTimerView != null) {
                    BaseDispatchTouchActivity.this.mCountTimerView.onFinish();
                }
            }
        });
    }

    private void init() {
        this.mCountTimerView = new CountTimer(3000L, 1000L, this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mRawDX = ev.getRawX();
        } else if (ev.getAction() == 1) {
            this.mCountTimerView.start();
        } else if (ev.getAction() != 2) {
            this.mCountTimerView.cancel();
        } else if (Math.abs(ev.getRawX() - this.mRawDX) > 50.0f) {
            this.mCountTimerView.onActionMove();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mCountTimerView.cancel();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        timeStart();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }
}
