package org.chromium.components.signin.identitymanager;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class PrimaryAccountChangeEvent {
    public final int a;

    @CalledByNative
    public PrimaryAccountChangeEvent(int i, int i2) {
        this.a = i;
    }
}
