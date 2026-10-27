package org.chromium.chrome.browser.offlinepages.prefetch;

import J.N;
import android.content.Context;
import defpackage.a73;
import defpackage.du;
import defpackage.fc;
import defpackage.fk1;
import defpackage.h90;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PrefetchBackgroundTask extends fk1 {
    public long f;
    public fc.a g;
    public boolean h = true;
    public boolean i;

    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        this.g = duVar;
        this.i = a73Var.b.getBoolean("limitlessPrefetching");
        h90 h90VarA = h90.a(context);
        if ((!h90VarA.c && (h90VarA.a || h90VarA.b >= 50)) || this.i) {
            boolean z = this.i;
            int i = h90VarA.d;
            if (!z ? h90VarA.e || i != 2 : i == 6) {
                return 0;
            }
        }
        return 1;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        if (this.f != 0) {
            return;
        }
        N.M_yiIXuu(this);
    }

    @CalledByNative
    public void doneProcessing(boolean z) {
        this.h = z;
        this.g.b(z);
        setNativeTask(0L);
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return this.h;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        long j = this.f;
        return j == 0 ? this.h : N.MgpkLR_Z(j, this);
    }

    @Override // defpackage.fk1
    public final boolean h() {
        return true;
    }

    @CalledByNative
    public void setNativeTask(long j) {
        this.f = j;
    }
}
