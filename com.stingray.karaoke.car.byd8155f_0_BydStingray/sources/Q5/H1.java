package Q5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class H1 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6375n;
    public final /* synthetic */ Object o;

    public /* synthetic */ H1(Object obj, boolean z6, int i7, int i10) {
        this.l = i10;
        this.o = obj;
        this.f6374m = z6;
        this.f6375n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.getClass();
                io.sentry.config.a.G((d6.j) this.o, this.f6374m, c0761q, C0735d.Z(this.f6375n | 1));
                break;
            case 1:
                num.intValue();
                io.sentry.config.a.s((String) this.o, this.f6374m, c0761q, C0735d.Z(this.f6375n | 1));
                break;
            default:
                num.intValue();
                ((C8.d) this.o).k(C0735d.Z(this.f6375n | 1), c0761q, this.f6374m);
                break;
        }
        return m7.q.f19267a;
    }
}
