package A;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class M extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f70m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ V f71n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ M(V v10, int i7) {
        super(0);
        this.f70m = i7;
        this.f71n = v10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f70m) {
            case 0:
                U8.l lVar = this.f71n.f123E;
                if (lVar != null) {
                    lVar.p(C0045w.f292a);
                }
                return m7.q.f19267a;
            default:
                return Boolean.valueOf(!this.f71n.R0());
        }
    }
}
