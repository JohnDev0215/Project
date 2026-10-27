package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.os.Looper;
import defpackage.ax0;
import defpackage.na0;
import defpackage.ue;
import java.util.Set;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: InMemoryCachedImageFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qx0 extends ax0 {
    public ax0 b;
    public ue c;
    public final int d;

    public qx0(ax0 ax0Var, na0 na0Var, int i) {
        Runtime runtime = Runtime.getRuntime();
        ue ueVar = new ue(na0Var, Math.min((int) Math.max(1.0f, (runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory())) * 0.125f), i));
        super(ax0Var);
        this.c = ueVar;
        this.b = ax0Var;
        ax0Var.e();
        if (this.b.e() == 0) {
            this.d = 2;
        } else if (this.b.e() == 1) {
            this.d = 3;
        } else {
            this.d = 2;
        }
    }

    public static String f(int i, int i2, boolean z, String str) {
        return str + "/" + (z ? 1 : 0) + "/" + i + "/" + i2;
    }

    @Override // defpackage.ax0
    public final void a() {
        this.c.b().f(-1);
        Looper.myQueue().addIdleHandler(new te());
    }

    @Override // defpackage.ax0
    public final void b() {
        ax0 ax0Var = this.b;
        if (ax0Var != null) {
            ax0Var.b();
            this.b = null;
        }
        ue ueVar = this.c;
        if (ueVar != null) {
            na0.a<ue.a> aVar = ueVar.b;
            Set<na0.a<?>> set = ueVar.c.a;
            if (set.contains(aVar)) {
                aVar.a = null;
                set.remove(aVar);
            }
            ueVar.b = null;
            this.c = null;
        }
    }

    @Override // defpackage.ax0
    public final void c(ax0.a aVar, Callback<od> callback) {
        this.b.c(aVar, callback);
    }

    @Override // defpackage.ax0
    public final void d(ax0.a aVar, Callback<Bitmap> callback) {
        Bitmap bitmapA;
        if (this.c == null) {
            bitmapA = null;
        } else {
            bitmapA = this.c.a(f(aVar.c, aVar.d, aVar.e, aVar.a));
        }
        if (bitmapA == null) {
            this.b.d(aVar, new ct3(this, 5, aVar, callback));
            return;
        }
        this.a.getClass();
        N.Mlt0uwKm(aVar.b, 8);
        callback.onResult(bitmapA);
    }

    @Override // defpackage.ax0
    public final int e() {
        return this.d;
    }
}
