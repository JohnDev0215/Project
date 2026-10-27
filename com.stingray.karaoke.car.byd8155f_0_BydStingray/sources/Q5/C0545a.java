package Q5;

import Q.C0540x0;
import T.C0735d;
import T.C0761q;
import a6.C0928a;
import c6.C1034a;
import g1.AbstractC1274a;
import g6.C1306d;
import h6.C1382a;
import i6.C1415d;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0545a implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6679m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6680n;

    public /* synthetic */ C0545a(int i7, int i10, Object obj) {
        this.l = i10;
        this.f6680n = obj;
        this.f6679m = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).intValue();
        switch (i7) {
            case 0:
                com.bumptech.glide.d.b((C1382a) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 1:
                AbstractC1274a.d((C0928a) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 2:
                l9.d.f((C1034a) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 3:
                l9.l.h((h6.b) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 4:
                P8.n.c((h6.h) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 5:
                com.bumptech.glide.d.h((f6.g) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 6:
                l9.l.n((f6.o) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 7:
                AbstractC1274a.q((f6.p) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 8:
                l9.l.x((C0540x0) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 9:
                w0.c.B((C1415d) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            case 10:
                l9.d.r((C1306d) this.f6680n, c0761q, C0735d.Z(this.f6679m | 1));
                break;
            default:
                ((io.sentry.hints.i) this.f6680n).d(C0735d.Z(this.f6679m | 1), c0761q);
                break;
        }
        return m7.q.f19267a;
    }
}
