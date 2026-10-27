package org.chromium.mojo.system.impl;

import J.N;
import defpackage.ap3;
import defpackage.k00;
import defpackage.tt0;
import defpackage.ut0;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class WatcherImpl implements ap3 {
    public long a = N.MXGgOw9k(this);
    public ap3.a b;

    public final int a(tt0 tt0Var, k00.a aVar, ap3.a aVar2) {
        long j = this.a;
        if (j == 0 || !(tt0Var instanceof ut0)) {
            return 3;
        }
        int iMOmBVeTt = N.MOmBVeTt(this, j, ((ut0) tt0Var).e, aVar.a);
        if (iMOmBVeTt == 0) {
            this.b = aVar2;
        }
        return iMOmBVeTt;
    }

    @CalledByNative
    public final void onHandleReady(int i) {
        this.b.a(i);
    }
}
