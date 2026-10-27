package Q5;

import F0.C0223q;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import java.util.List;
import k6.C1701i;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: Q5.p1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0607p1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LoadingInterface$LoadingState f7011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0591l1 f7012b;
    public final C0591l1 c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f7013e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f7014f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f7015g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f7016h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InterfaceC2772a f7017i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final InterfaceC2772a f7018j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C0599n1 f7019k;
    public final Integer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f7020m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0223q f7021n;
    public C0595m1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1701i f7022p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f7023q;

    public C0607p1(b6.o oVar, boolean z6, String str, boolean z10, boolean z11, boolean z12, C1701i c1701i) {
        this(oVar, z6, (String) oVar.f12276z.getValue(), str, z10, z11, z12, c1701i);
        this.f7023q = z6;
        V8.N n10 = oVar.B;
        List list = (List) ((V8.f0) n10.l).getValue();
        Integer numValueOf = list != null ? Integer.valueOf(list.indexOf(((V8.f0) oVar.f12267D.l).getValue())) : null;
        this.l = (numValueOf == null || numValueOf.intValue() < 0) ? null : numValueOf;
        this.f7020m = ((Boolean) ((V8.f0) oVar.f12269F.l).getValue()).booleanValue() ? null : (List) ((V8.f0) n10.l).getValue();
        this.f7021n = new C0223q(1, oVar, b6.o.class, "onSortingSelected", "onSortingSelected(Lcom/stingray/kca/model/KaraokeItemSortTypeInfo;)V", 0, 7);
    }

    public /* synthetic */ C0607p1(b6.f fVar, String str, boolean z6, C1701i c1701i, int i7) {
        this(fVar, false, (i7 & 4) != 0 ? null : str, null, false, (i7 & 32) != 0, (i7 & 64) != 0 ? false : z6, (i7 & 128) != 0 ? null : c1701i);
    }

    public C0607p1(b6.f fVar, boolean z6, String str, String str2, boolean z10, boolean z11, boolean z12, C1701i c1701i) {
        A7.m.f(fVar, "viewModel");
        this.f7011a = fVar.e();
        this.d = z10;
        this.f7013e = z11;
        this.f7015g = str;
        this.f7016h = str2;
        this.f7014f = z12;
        this.f7012b = new C0591l1(fVar, 0);
        this.c = new C0591l1(fVar, 1);
        this.f7017i = fVar.f12239i;
        this.f7018j = fVar.f12240j;
        this.f7022p = c1701i;
        this.f7023q = z6;
    }
}
