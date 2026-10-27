package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
interface AudioSourceCallbacks extends NativeCallback {
    void ackCallback(int i, int i2);

    int microphoneRequestCallback(boolean z, boolean z2, boolean z3, int i);
}
