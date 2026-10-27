package S5;

import T.C0735d;
import T.C0761q;
import com.stingray.karaoke.car.utils.FontSize;
import g1.AbstractC1274a;
import n6.C1966G;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8049m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8050n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8051p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f8052q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f8053r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f8054s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f8055t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f8056u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ m7.c f8057v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ m7.c f8058w;

    public /* synthetic */ s(g0.o oVar, String str, C1966G c1966g, boolean z6, FontSize fontSize, boolean z10, int i7, int i10, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, int i11) {
        this.f8054s = oVar;
        this.f8049m = str;
        this.f8055t = c1966g;
        this.f8050n = z6;
        this.f8056u = fontSize;
        this.o = z10;
        this.f8051p = i7;
        this.f8052q = i10;
        this.f8057v = interfaceC2782k;
        this.f8058w = interfaceC2782k2;
        this.f8053r = i11;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).intValue();
                C7.a.q(this.f8049m, (String) this.f8054s, (String) this.f8055t, this.f8050n, this.o, this.f8051p, this.f8052q, (InterfaceC2772a) this.f8056u, (InterfaceC2772a) this.f8057v, (InterfaceC2772a) this.f8058w, (C0761q) obj, C0735d.Z(this.f8053r | 1));
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f8053r | 1);
                C1966G c1966g = (C1966G) this.f8055t;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f8057v;
                InterfaceC2782k interfaceC2782k2 = (InterfaceC2782k) this.f8058w;
                AbstractC1274a.l((g0.o) this.f8054s, this.f8049m, c1966g, this.f8050n, (FontSize) this.f8056u, this.o, this.f8051p, this.f8052q, interfaceC2782k, interfaceC2782k2, (C0761q) obj, iZ);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ s(String str, String str2, String str3, boolean z6, boolean z10, int i7, int i10, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, int i11) {
        this.f8049m = str;
        this.f8054s = str2;
        this.f8055t = str3;
        this.f8050n = z6;
        this.o = z10;
        this.f8051p = i7;
        this.f8052q = i10;
        this.f8056u = interfaceC2772a;
        this.f8057v = interfaceC2772a2;
        this.f8058w = interfaceC2772a3;
        this.f8053r = i11;
    }
}
