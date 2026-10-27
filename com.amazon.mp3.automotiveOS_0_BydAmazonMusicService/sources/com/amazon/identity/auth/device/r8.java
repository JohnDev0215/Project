package com.amazon.identity.auth.device;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class r8 {
    private AtomicBoolean mHasBeenCalled = new AtomicBoolean(false);

    public abstract void finish(Bundle bundle);

    public void onFinish(Bundle bundle) {
        if (this.mHasBeenCalled.compareAndSet(false, true)) {
            finish(bundle);
        }
    }
}
