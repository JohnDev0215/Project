package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.karaoke.car.utils.FontSize;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class A0 implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6245m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6246n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f6247p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6248q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f6249r;

    public /* synthetic */ A0(g0.o oVar, X5.D0 d02, boolean z6, InterfaceC2772a interfaceC2772a, boolean z10, int i7) {
        this.f6245m = oVar;
        this.f6249r = d02;
        this.f6246n = z6;
        this.o = interfaceC2772a;
        this.f6247p = z10;
        this.f6248q = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6248q | 1);
                InterfaceC2772a interfaceC2772a = this.o;
                boolean z6 = this.f6247p;
                l9.d.n(this.f6245m, (X5.D0) this.f6249r, this.f6246n, interfaceC2772a, z6, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6248q | 1);
                boolean z10 = this.f6247p;
                FontSize fontSize = (FontSize) this.f6249r;
                w0.c.x(this.f6245m, this.f6246n, this.o, z10, fontSize, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ A0(g0.o oVar, boolean z6, InterfaceC2772a interfaceC2772a, boolean z10, FontSize fontSize, int i7) {
        this.f6245m = oVar;
        this.f6246n = z6;
        this.o = interfaceC2772a;
        this.f6247p = z10;
        this.f6249r = fontSize;
        this.f6248q = i7;
    }
}
