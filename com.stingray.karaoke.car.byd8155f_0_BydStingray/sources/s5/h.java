package S5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8008m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8009n;

    public /* synthetic */ h(String str, int i7, int i10) {
        this.l = i10;
        this.f8008m = str;
        this.f8009n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).intValue();
        switch (i7) {
            case 0:
                io.sentry.config.a.H(this.f8008m, c0761q, C0735d.Z(this.f8009n | 1));
                break;
            case 1:
                io.sentry.config.a.l(this.f8008m, c0761q, C0735d.Z(this.f8009n | 1));
                break;
            default:
                h6.q.j(this.f8008m, c0761q, C0735d.Z(this.f8009n | 1));
                break;
        }
        return m7.q.f19267a;
    }
}
