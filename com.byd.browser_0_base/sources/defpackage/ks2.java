package defpackage;

import android.content.Context;
import defpackage.ms2;
import org.chromium.base.b;

/* JADX INFO: compiled from: StartupPaintPreviewHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ks2 {
    public static boolean e;
    public final long a;
    public final fj b;
    public final nw2<e91> c;
    public final b<ms2.a> d = new b<>();

    public ks2(g3 g3Var, long j, fj fjVar, a23 a23Var, boolean z, ls lsVar) {
        this.a = j;
        this.b = fjVar;
        this.c = lsVar;
        vj1 vj1Var = vj1.h;
        Context context = g3Var.i.get();
        vj1Var.getClass();
        if (vj1.a(context) || z) {
            e = false;
        }
        a23Var.m(new is2(a23Var, g3Var));
    }
}
