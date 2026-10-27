package P5;

import E.C0147d;
import H7.H;
import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5358m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f5359n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f5360p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Integer f5361q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ b0.b f5362r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f5363s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f5364t;

    public /* synthetic */ h(C0147d c0147d, p pVar, String str, g0.o oVar, Integer num, boolean z6, b0.b bVar, int i7) {
        this.f5364t = c0147d;
        this.f5358m = pVar;
        this.f5359n = str;
        this.o = oVar;
        this.f5361q = num;
        this.f5360p = z6;
        this.f5362r = bVar;
        this.f5363s = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f5363s | 1);
                p pVar = this.f5358m;
                b0.b bVar = this.f5362r;
                H.g((C0147d) this.f5364t, pVar, this.f5359n, this.o, this.f5361q, this.f5360p, bVar, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f5363s | 1);
                p pVar2 = this.f5358m;
                b0.b bVar2 = this.f5362r;
                H.h(pVar2, this.f5359n, this.o, (g0.o) this.f5364t, this.f5360p, this.f5361q, bVar2, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ h(p pVar, String str, g0.o oVar, g0.o oVar2, boolean z6, Integer num, b0.b bVar, int i7) {
        this.f5358m = pVar;
        this.f5359n = str;
        this.o = oVar;
        this.f5364t = oVar2;
        this.f5360p = z6;
        this.f5361q = num;
        this.f5362r = bVar;
        this.f5363s = i7;
    }
}
