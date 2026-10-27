package p;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class yu2 implements ViewTreeObserver.OnPreDrawListener {
    public boolean c;
    public final /* synthetic */ coil.size.a e;
    public final /* synthetic */ ViewTreeObserver k;
    public final /* synthetic */ yp l;

    public yu2(coil.size.a aVar, ViewTreeObserver viewTreeObserver, zp zpVar) {
        this.e = aVar;
        this.k = viewTreeObserver;
        this.l = zpVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        coil.size.a aVar = this.e;
        qd2 qd2VarC = super/*coil.size.a*/.c();
        if (qd2VarC != null) {
            ViewTreeObserver viewTreeObserver = this.k;
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this);
            } else {
                ((h12) aVar).a.getViewTreeObserver().removeOnPreDrawListener(this);
            }
            if (!this.c) {
                this.c = true;
                ((zp) this.l).k(qd2VarC);
            }
        }
        return true;
    }
}
