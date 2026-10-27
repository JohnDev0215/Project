package defpackage;

import defpackage.di1;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: UsageStatsConsentDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ck3 implements di1.a {
    public final /* synthetic */ dk3 e;

    public ck3(dk3 dk3Var) {
        this.e = dk3Var;
    }

    @Override // di1.a
    public final void b(int i) {
        dk3 dk3Var = this.e;
        dk3Var.e.onResult(Boolean.FALSE);
        dk3Var.b.a();
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        fk3 fk3VarB = fk3.b();
        boolean z = i == 0;
        dk3 dk3Var = this.e;
        if (z) {
            boolean z2 = !dk3Var.d;
            fk3VarB.getClass();
            Object obj = ThreadUtils.a;
            oj2.k(fk3VarB.a).e("usage_stats_reporting.enabled", z2);
            if (fk3VarB.h != z2) {
                fk3VarB.h = z2;
                fk3VarB.g.getClass();
                s62.c(null);
                if (!z2) {
                    zw2 zw2Var = fk3VarB.d;
                    zw2Var.getClass();
                    zw2Var.c.f(new xj(2)).g(new se3(9, fk3VarB));
                    ob3 ob3Var = fk3VarB.e;
                    ob3Var.getClass();
                    ob3Var.a.f(new xj(3)).g(new jb1(28, fk3VarB));
                }
                z7.d0(!z2 ? 1 : 0);
            }
        }
        dk3Var.e.onResult(Boolean.valueOf(z));
        dk3Var.b.a();
    }
}
