package org.chromium.components.signin.identitymanager;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class IdentityMutator {
    public long a;

    @CalledByNative
    public IdentityMutator(long j) {
        this.a = j;
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }
}
