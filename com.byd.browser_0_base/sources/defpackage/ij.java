package defpackage;

import android.graphics.Rect;
import defpackage.kj;

/* JADX INFO: compiled from: BrowserControlsMarginSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class ij extends aq1<Rect> implements kj.a, w80 {
    public final kj h;

    public ij(kj kjVar) {
        this.h = kjVar;
        kjVar.o(this);
        s();
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        s();
    }

    @Override // defpackage.w80
    public final void destroy() {
        this.h.s(this);
    }

    @Override // kj.a
    public final void l(int i) {
        s();
    }

    @Override // kj.a
    public final void n() {
        s();
    }

    public final void s() {
        kj kjVar = this.h;
        r(new Rect(0, kjVar.e() + kjVar.l(), 0, kjVar.g() - kjVar.u()));
    }
}
