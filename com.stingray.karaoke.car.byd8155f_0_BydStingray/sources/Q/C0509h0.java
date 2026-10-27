package Q;

import A.C0004b;
import T.C0735d;
import T.C0761q;
import z.C2751b;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0509h0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5903m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5904n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f5905p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f5906q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f5907r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f5908s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ m7.c f5909t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0509h0(g0.o oVar, C0523o0 c0523o0, boolean z6, C.k kVar, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, int i7) {
        super(2);
        this.o = oVar;
        this.f5906q = c0523o0;
        this.f5904n = z6;
        this.f5907r = kVar;
        this.f5908s = interfaceC2786o;
        this.f5909t = interfaceC2786o2;
        this.f5905p = i7;
    }

    /* JADX WARN: Type inference failed for: r6v7, types: [A7.o, z7.a] */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5903m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f5905p | 1);
                InterfaceC2786o interfaceC2786o = (InterfaceC2786o) this.f5908s;
                InterfaceC2786o interfaceC2786o2 = (InterfaceC2786o) this.f5909t;
                AbstractC0517l0.d(this.o, (C0523o0) this.f5906q, this.f5904n, (C.k) this.f5907r, interfaceC2786o, interfaceC2786o2, (C0761q) obj, iZ);
                break;
            case 1:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f5905p | 1);
                b0.b bVar = (b0.b) this.f5909t;
                w.F f10 = (w.F) this.f5906q;
                w.G g7 = (w.G) this.f5907r;
                androidx.compose.animation.a.b(this.f5904n, this.o, f10, g7, (String) this.f5908s, bVar, (C0761q) obj, iZ2);
                break;
            case 2:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f5905p | 1);
                b0.b bVar2 = (b0.b) this.f5909t;
                C0004b c0004b = (C0004b) this.f5908s;
                g0.o oVar = this.o;
                h6.j.d((z.m) this.f5906q, (InterfaceC2772a) this.f5907r, c0004b, oVar, this.f5904n, bVar2, (C0761q) obj, iZ3);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ4 = C0735d.Z(this.f5905p | 1);
                ?? r62 = (A7.o) this.f5909t;
                C2751b c2751b = (C2751b) this.f5907r;
                g0.o oVar2 = this.o;
                z.n.b((String) this.f5906q, this.f5904n, c2751b, oVar2, (InterfaceC2786o) this.f5908s, r62, (C0761q) obj, iZ4);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C0509h0(String str, boolean z6, C2751b c2751b, g0.o oVar, InterfaceC2786o interfaceC2786o, InterfaceC2772a interfaceC2772a, int i7) {
        super(2);
        this.f5906q = str;
        this.f5904n = z6;
        this.f5907r = c2751b;
        this.o = oVar;
        this.f5908s = interfaceC2786o;
        this.f5909t = (A7.o) interfaceC2772a;
        this.f5905p = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0509h0(z.m mVar, InterfaceC2772a interfaceC2772a, C0004b c0004b, g0.o oVar, boolean z6, b0.b bVar, int i7) {
        super(2);
        this.f5906q = mVar;
        this.f5907r = interfaceC2772a;
        this.f5908s = c0004b;
        this.o = oVar;
        this.f5904n = z6;
        this.f5909t = bVar;
        this.f5905p = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0509h0(boolean z6, g0.o oVar, w.F f10, w.G g7, String str, b0.b bVar, int i7) {
        super(2);
        this.f5904n = z6;
        this.o = oVar;
        this.f5906q = f10;
        this.f5907r = g7;
        this.f5908s = str;
        this.f5909t = bVar;
        this.f5905p = i7;
    }
}
