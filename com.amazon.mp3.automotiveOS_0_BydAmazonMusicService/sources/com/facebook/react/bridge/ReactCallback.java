package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
interface ReactCallback {
    @DoNotStrip
    void decrementPendingJSCalls();

    @DoNotStrip
    void incrementPendingJSCalls();

    @DoNotStrip
    void onBatchComplete();
}
