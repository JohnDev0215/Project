package com.byd.auto_emanual.util;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class CLickProxyUtil implements View.OnClickListener {
    private static CLickProxyUtil instance;
    private int intervalTime;
    private long lastTime;
    private View.OnClickListener origin;

    public static CLickProxyUtil getInstance(View.OnClickListener origin, int intervalTime) {
        if (instance == null) {
            instance = new CLickProxyUtil(origin, intervalTime);
        }
        return instance;
    }

    private CLickProxyUtil(View.OnClickListener origin) {
        this(origin, 1000);
    }

    private CLickProxyUtil(View.OnClickListener origin, int intervalTime) {
        this.lastTime = 0L;
        this.origin = origin;
        this.intervalTime = intervalTime;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastTime > this.intervalTime) {
            View.OnClickListener onClickListener = this.origin;
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
            this.lastTime = jCurrentTimeMillis;
        }
    }
}
