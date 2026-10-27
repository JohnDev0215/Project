package com.facebook.react.common;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;

/* JADX INFO: loaded from: classes6.dex */
public class SingleThreadAsserter {

    @Nullable
    private Thread mThread = null;

    public void assertNow() {
        Thread threadCurrentThread = Thread.currentThread();
        if (this.mThread == null) {
            this.mThread = threadCurrentThread;
        }
        Assertions.assertCondition(this.mThread == threadCurrentThread);
    }
}
