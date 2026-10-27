package com.byd.vrassistant.livedata;

import android.util.Log;
import androidx.lifecycle.Observer;

/* JADX INFO: loaded from: classes.dex */
public abstract class SafeObserver<T> implements Observer<T> {
    private static final String TAG = "NEU_FATAL";

    public abstract void onSafeChanged(T t);

    @Override // androidx.lifecycle.Observer
    public void onChanged(T t) {
        try {
            onSafeChanged(t);
        } catch (Exception e) {
            Log.e(TAG, "SafeObserver onChanged: ", e);
        }
    }
}
