package org.chromium.base;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public interface IntStringCallback {
    @CalledByNative
    void onResult(int i, String str);
}
