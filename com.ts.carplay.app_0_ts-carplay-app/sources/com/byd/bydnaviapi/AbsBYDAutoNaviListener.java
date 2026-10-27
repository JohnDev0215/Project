package com.byd.bydnaviapi;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoNaviListener implements IBYDAutoListener {
    private static final String TAG = "AbsBYDAutoNaviListener";

    public AbsBYDAutoNaviListener() {
        Log.d(TAG, TAG);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent ibydAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String s) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bydAutoEventValue) {
    }

    public void onInsUiChanged(int uiState) {
    }

    public void onMapFocusChanged(int mapType) {
    }

    public void onINSNavDisplayMode(int value) {
    }
}
