package Q5;

import Q.AbstractC0535v;
import T.C0732b0;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class T implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f6564m;

    public T(int i7, C0732b0 c0732b0) {
        this.l = i7;
        this.f6564m = c0732b0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else if (this.f6564m.i() == this.l) {
            AbstractC0535v.a(l9.l.W(R.drawable.ic_checkmark, c0761q, 0), null, androidx.compose.foundation.layout.c.l(g0.l.f14684a, m6.c.f19252a.y(c0761q)), l6.d.c(c0761q).f18696b, c0761q, 48, 0);
        }
        return m7.q.f19267a;
    }
}
