package K;

import N0.C0411f;
import S0.InterfaceC0702n;
import java.util.List;

/* JADX INFO: renamed from: K.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0353m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f3338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N0.L f3339b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f3340e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3341f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a1.b f3342g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final InterfaceC0702n f3343h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f3344i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public N0.q f3345j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public a1.k f3346k;

    public C0353m0(C0411f c0411f, N0.L l, int i7, int i10, boolean z6, int i11, a1.b bVar, InterfaceC0702n interfaceC0702n, List list) {
        this.f3338a = c0411f;
        this.f3339b = l;
        this.c = i7;
        this.d = i10;
        this.f3340e = z6;
        this.f3341f = i11;
        this.f3342g = bVar;
        this.f3343h = interfaceC0702n;
        this.f3344i = list;
        if (i7 <= 0) {
            throw new IllegalArgumentException("no maxLines");
        }
        if (i10 <= 0) {
            throw new IllegalArgumentException("no minLines");
        }
        if (i10 > i7) {
            throw new IllegalArgumentException("minLines greater than maxLines");
        }
    }

    public final void a(a1.k kVar) {
        N0.q qVar = this.f3345j;
        if (qVar == null || kVar != this.f3346k || qVar.c()) {
            this.f3346k = kVar;
            qVar = new N0.q(this.f3338a, P8.n.T(this.f3339b, kVar), this.f3344i, this.f3342g, this.f3343h);
        }
        this.f3345j = qVar;
    }
}
