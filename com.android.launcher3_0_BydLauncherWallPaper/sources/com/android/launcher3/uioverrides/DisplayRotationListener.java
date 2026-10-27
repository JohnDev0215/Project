package com.android.launcher3.uioverrides;

import android.content.Context;
import android.view.OrientationEventListener;

/* JADX INFO: loaded from: classes4.dex */
public class DisplayRotationListener extends OrientationEventListener {
    private final Runnable mCallback;

    public DisplayRotationListener(Context context, Runnable callback) {
        super(context);
        this.mCallback = callback;
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        this.mCallback.run();
    }
}
