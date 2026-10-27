package defpackage;

import android.os.Build;
import android.view.View;
import defpackage.pt3;
import org.chromium.components.browser_ui.widget.InsetObserverView;

/* JADX INFO: compiled from: DeferredIMEWindowInsetApplicationCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e80 implements InsetObserverView.f, InsetObserverView.e {
    public int e = -1;
    public int f;
    public boolean g;
    public ot3 h;
    public InsetObserverView i;
    public final Runnable j;

    public e80(l10 l10Var) {
        this.j = l10Var;
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.e
    public final void c(ot3 ot3Var) {
        if ((ot3Var.a.c() & 8) == 0 || ot3Var != this.h) {
            return;
        }
        this.g = false;
        int i = this.e;
        if (i != -1) {
            this.f = i;
            this.e = -1;
            this.j.run();
        }
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.e
    public final void d(ot3 ot3Var) {
        if ((ot3Var.a.c() & 8) == 0) {
            return;
        }
        this.g = true;
        this.h = ot3Var;
        this.e = -1;
    }

    @Override // defpackage.wr1
    public final pt3 k(View view, pt3 pt3Var) {
        n01 n01VarA = pt3Var.a(8);
        int i = n01VarA.d > 0 ? n01VarA.d - pt3Var.a(2).d : 0;
        if (i < this.f || !this.g) {
            this.f = i;
            this.e = -1;
            this.j.run();
        } else if (i > 0) {
            this.e = i;
        }
        pt3.c bVar = Build.VERSION.SDK_INT >= 30 ? new pt3.b(pt3Var) : new pt3.a(pt3Var);
        bVar.c(8, n01.e);
        return bVar.b();
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.e
    public final void a() {
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.e
    public final void b() {
    }
}
