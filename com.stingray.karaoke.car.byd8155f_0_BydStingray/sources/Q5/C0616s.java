package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.karaoke.car.utils.FontSize;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0616s implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7046m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f7047n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7048p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7049q;

    public /* synthetic */ C0616s(g0.o oVar, String str, boolean z6, boolean z10, int i7) {
        this.f7048p = oVar;
        this.f7049q = str;
        this.f7046m = z6;
        this.f7047n = z10;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.o | 1);
                boolean z6 = this.f7046m;
                boolean z10 = this.f7047n;
                l9.d.p((g0.o) this.f7048p, (String) this.f7049q, z6, z10, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.o | 1);
                boolean z11 = this.f7047n;
                FontSize fontSize = (FontSize) this.f7049q;
                w0.c.y(this.f7046m, (InterfaceC2772a) this.f7048p, z11, fontSize, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0616s(boolean z6, InterfaceC2772a interfaceC2772a, boolean z10, FontSize fontSize, int i7) {
        this.f7046m = z6;
        this.f7048p = interfaceC2772a;
        this.f7047n = z10;
        this.f7049q = fontSize;
        this.o = i7;
    }
}
