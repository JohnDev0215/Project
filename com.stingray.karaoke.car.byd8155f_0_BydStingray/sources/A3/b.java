package A3;

import C0.InterfaceC0103j;
import T.C0735d;
import T.C0761q;
import coil.compose.AsyncImagePainter;
import com.stingray.karaoke.car.utils.FontSize;
import g1.AbstractC1274a;
import n0.C1883l;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f378m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f379n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f380p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f381q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f382r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f383s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f384t;

    public /* synthetic */ b(g0.o oVar, AsyncImagePainter asyncImagePainter, g0.c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l, boolean z6, int i7) {
        this.f378m = oVar;
        this.f381q = asyncImagePainter;
        this.f382r = cVar;
        this.f383s = interfaceC0103j;
        this.o = f10;
        this.f384t = c1883l;
        this.f379n = z6;
        this.f380p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f380p | 1);
                AsyncImagePainter asyncImagePainter = (AsyncImagePainter) this.f381q;
                C1883l c1883l = (C1883l) this.f384t;
                boolean z6 = this.f379n;
                p.c(this.f378m, asyncImagePainter, (g0.c) this.f382r, (InterfaceC0103j) this.f383s, this.o, c1883l, z6, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f380p | 1);
                float f10 = this.o;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f384t;
                AbstractC1274a.b(this.f378m, (String) this.f381q, (FontSize) this.f382r, this.f379n, (InterfaceC2772a) this.f383s, f10, interfaceC2782k, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ b(g0.o oVar, String str, FontSize fontSize, boolean z6, InterfaceC2772a interfaceC2772a, float f10, InterfaceC2782k interfaceC2782k, int i7) {
        this.f378m = oVar;
        this.f381q = str;
        this.f382r = fontSize;
        this.f379n = z6;
        this.f383s = interfaceC2772a;
        this.o = f10;
        this.f384t = interfaceC2782k;
        this.f380p = i7;
    }
}
