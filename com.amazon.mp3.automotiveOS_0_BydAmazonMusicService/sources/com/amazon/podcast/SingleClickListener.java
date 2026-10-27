package com.amazon.podcast;

import android.os.SystemClock;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SingleClickListener implements View.OnClickListener {
    private long lastTimeClicked;
    protected int threshold;

    public abstract void onSingleClick(View v);

    public SingleClickListener() {
        this(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    public SingleClickListener(int minThreshold) {
        this.lastTimeClicked = 0L;
        this.threshold = minThreshold;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - this.lastTimeClicked < this.threshold) {
            return;
        }
        this.lastTimeClicked = SystemClock.elapsedRealtime();
        onSingleClick(v);
    }
}
