package defpackage;

import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: FindToolbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class wm0 implements ym0 {
    public final /* synthetic */ xm0 e;

    public wm0(xm0 xm0Var) {
        this.e = xm0Var;
    }

    @Override // defpackage.ym0
    public final void c() {
        Iterator<ym0> it = this.e.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ym0) aVar.next()).c();
            }
        }
    }

    @Override // defpackage.ym0
    public final void d() {
        Iterator<ym0> it = this.e.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ym0) aVar.next()).d();
            }
        }
    }
}
