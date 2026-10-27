package defpackage;

import android.app.Activity;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.j;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: v0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountPickerBottomSheetCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0215v0 {
    public final a1 a;
    public final C0219x0 b;
    public final b1 c;
    public final ji d;
    public final a e;

    /* JADX INFO: renamed from: v0$a */
    /* JADX INFO: compiled from: AccountPickerBottomSheetCoordinator.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            if (i != 0) {
                return;
            }
            C0215v0 c0215v0 = C0215v0.this;
            if (i2 == 1) {
                c0215v0.a(10);
            } else if (i2 == 2) {
                c0215v0.a(1);
            } else if (i2 == 3) {
                c0215v0.a(9);
            }
            f1 f1Var = c0215v0.c.a;
            f1Var.g.e(f1Var);
            f1Var.h.j(f1Var);
            C0219x0 c0219x0 = c0215v0.b;
            c0219x0.g.destroy();
            c0219x0.h.e(c0219x0);
            c0219x0.j.j(c0219x0);
            c0219x0.i.d(c0219x0.n);
            c0215v0.d.e(c0215v0.e);
        }
    }

    public C0215v0(WindowAndroid windowAndroid, ji jiVar, c1 c1Var, C0217w0 c0217w0) {
        a aVar = new a();
        this.e = aVar;
        oj2.K(6);
        C0219x0 c0219x0 = new C0219x0(windowAndroid, c1Var, new zx1(27, this), c0217w0);
        this.b = c0219x0;
        a1 a1Var = new a1((Activity) windowAndroid.k().get(), c0219x0);
        this.a = a1Var;
        this.c = new b1(a1Var.h, c0219x0);
        this.d = jiVar;
        j.b(c0219x0.i, a1Var, new ag(18));
        jiVar.g(aVar);
        jiVar.d(a1Var, true);
    }

    public final void a(int i) {
        oj2.K(i);
        if (this.b.i.h(C0221y0.f) == 0) {
            pm2.b.a.c("Chrome.AccountPickerBottomSheet.ConsecutiveActiveDismissalCount");
        }
    }
}
