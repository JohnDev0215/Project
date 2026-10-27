package defpackage;

import java.util.ArrayList;
import org.chromium.ui.modelutil.d;
import org.chromium.ui.modelutil.e;

/* JADX INFO: compiled from: DecoratedListItemModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class g70 extends e<Void> implements d.a<Void>, xm2<f81> {
    public final g81 f;
    public final ArrayList g = new ArrayList();

    public g70(g81 g81Var) {
        this.f = g81Var;
        g81Var.g(this);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void b(int i, int i2) {
        r(this.g.size() + i, i2);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void i(int i, int i2, Object obj) {
        q(this.g.size() + i, i2, (Void) obj);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void j(int i, int i2) {
        s(this.g.size() + i, i2);
    }

    @Override // defpackage.xm2
    public final int size() {
        return this.g.size() + this.f.size();
    }

    @Override // defpackage.xm2
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final f81 get(int i) {
        ArrayList arrayList = this.g;
        if (i < arrayList.size()) {
            return (f81) arrayList.get(i);
        }
        return this.f.get(i - arrayList.size());
    }
}
