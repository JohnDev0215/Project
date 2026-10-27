package defpackage;

import android.app.Activity;
import android.view.accessibility.AccessibilityManager;
import defpackage.C0188h0;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;

/* JADX INFO: compiled from: ChromeAccessibilityUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qo extends C0188h0 {
    public static qo f;
    public a e;

    /* JADX INFO: compiled from: ChromeAccessibilityUtil.java */
    public final class a implements ApplicationStatus.c {
        public a() {
        }

        @Override // org.chromium.base.ApplicationStatus.c
        public final void d(Activity activity, int i) {
            boolean zIsEmpty = ApplicationStatus.a.isEmpty();
            qo qoVar = qo.this;
            if (!zIsEmpty) {
                qoVar.getClass();
                if (i == 3) {
                    qoVar.e();
                    return;
                }
                return;
            }
            b<C0188h0.b> bVar = qoVar.c;
            if (bVar != null) {
                bVar.clear();
            }
            if (qoVar.d != null) {
                AccessibilityManager accessibilityManagerB = C0188h0.b();
                accessibilityManagerB.removeAccessibilityStateChangeListener(qoVar.d);
                accessibilityManagerB.removeTouchExplorationStateChangeListener(qoVar.d);
                qoVar.d = null;
            }
            a aVar = qoVar.e;
            if (aVar != null) {
                ApplicationStatus.i(aVar);
                qoVar.e = null;
            }
        }
    }

    public static qo f() {
        if (f == null) {
            f = new qo();
        }
        return f;
    }

    @Override // defpackage.C0188h0
    public final boolean c() {
        if (this.e == null) {
            a aVar = new a();
            this.e = aVar;
            ApplicationStatus.g(aVar);
        }
        return super.c();
    }

    @Override // defpackage.C0188h0
    public final boolean d() {
        if (this.e == null) {
            a aVar = new a();
            this.e = aVar;
            ApplicationStatus.g(aVar);
        }
        return super.d();
    }
}
