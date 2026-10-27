package R5;

import T.C0735d;
import T.C0761q;
import l9.l;
import m7.q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h6.d f7491m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7492n;

    public /* synthetic */ a(h6.d dVar, int i7, int i10) {
        this.l = i10;
        this.f7491m = dVar;
        this.f7492n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.intValue();
                l.i(this.f7491m, c0761q, C0735d.Z(this.f7492n | 1));
                break;
            default:
                num.getClass();
                w0.c.e(this.f7491m, c0761q, C0735d.Z(this.f7492n | 1));
                break;
        }
        return q.f19267a;
    }
}
