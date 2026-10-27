package C0;

import E0.C0169t;
import E0.C0170u;
import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class A implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f837b;
    public final /* synthetic */ Map c;
    public final /* synthetic */ B d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ G f838e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f839f;

    public A(int i7, int i10, Map map, B b10, G g7, InterfaceC2782k interfaceC2782k) {
        this.f836a = i7;
        this.f837b = i10;
        this.c = map;
        this.d = b10;
        this.f838e = g7;
        this.f839f = interfaceC2782k;
    }

    @Override // C0.L
    public final Map a() {
        return this.c;
    }

    @Override // C0.L
    public final void b() {
        C0169t c0169t;
        boolean zV = this.d.v();
        InterfaceC2782k interfaceC2782k = this.f839f;
        G g7 = this.f838e;
        if (!zV || (c0169t = ((C0170u) g7.l.f1469H.c).f1704W) == null) {
            interfaceC2782k.b(((C0170u) g7.l.f1469H.c).f1578t);
        } else {
            interfaceC2782k.b(c0169t.f1578t);
        }
    }

    @Override // C0.L
    public final int c() {
        return this.f837b;
    }

    @Override // C0.L
    public final InterfaceC2782k d() {
        return null;
    }

    @Override // C0.L
    public final int e() {
        return this.f836a;
    }
}
