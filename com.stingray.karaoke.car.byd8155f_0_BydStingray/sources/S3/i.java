package S3;

import C0.InterfaceC0103j;
import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import java.util.Map;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ Map f7915A;
    public final /* synthetic */ int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ boolean f7916C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final /* synthetic */ int f7917D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final /* synthetic */ int f7918E;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7919m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ O3.a f7920n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0.o f7921p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f7922q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f7923r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f7924s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f7925t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f7926u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ boolean f7927v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ g0.c f7928w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0103j f7929x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ boolean f7930y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ boolean f7931z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(O3.a aVar, InterfaceC2772a interfaceC2772a, g0.o oVar, boolean z6, boolean z10, boolean z11, boolean z12, int i7, boolean z13, g0.c cVar, InterfaceC0103j interfaceC0103j, boolean z14, boolean z15, Map map, int i10, boolean z16, int i11, int i12, int i13) {
        super(2);
        this.f7919m = i13;
        this.f7920n = aVar;
        this.o = interfaceC2772a;
        this.f7921p = oVar;
        this.f7922q = z6;
        this.f7923r = z10;
        this.f7924s = z11;
        this.f7925t = z12;
        this.f7926u = i7;
        this.f7927v = z13;
        this.f7928w = cVar;
        this.f7929x = interfaceC0103j;
        this.f7930y = z14;
        this.f7931z = z15;
        this.f7915A = map;
        this.B = i10;
        this.f7916C = z16;
        this.f7917D = i11;
        this.f7918E = i12;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7919m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f7917D | 1);
                int iZ2 = C0735d.Z(this.f7918E);
                boolean z6 = this.f7931z;
                Map map = this.f7915A;
                AbstractC0904a.g(this.f7920n, this.o, this.f7921p, this.f7922q, this.f7923r, this.f7924s, this.f7925t, this.f7926u, this.f7927v, this.f7928w, this.f7929x, this.f7930y, z6, map, this.B, this.f7916C, (C0761q) obj, iZ, iZ2);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f7917D | 1);
                int iZ4 = C0735d.Z(this.f7918E);
                g0.o oVar = this.f7921p;
                boolean z10 = this.f7931z;
                Map map2 = this.f7915A;
                AbstractC0904a.g(this.f7920n, this.o, oVar, this.f7922q, this.f7923r, this.f7924s, this.f7925t, this.f7926u, this.f7927v, this.f7928w, this.f7929x, this.f7930y, z10, map2, this.B, this.f7916C, (C0761q) obj, iZ3, iZ4);
                break;
        }
        return m7.q.f19267a;
    }
}
