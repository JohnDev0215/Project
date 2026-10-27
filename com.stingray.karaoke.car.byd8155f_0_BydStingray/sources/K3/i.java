package K3;

import S8.AbstractC0721s;
import android.content.Context;
import android.graphics.Bitmap;
import h2.AbstractC1347d;
import java.util.Arrays;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3542a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3543b;
    public final A3.k c;
    public final Bitmap.Config d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L3.d f3544e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u f3545f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final M3.e f3546g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c9.u f3547h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q f3548i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f3549j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f3550k;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f3551m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f3552n;
    public final b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b f3553p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final AbstractC0721s f3554q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AbstractC0721s f3555r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AbstractC0721s f3556s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final AbstractC0721s f3557t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final A9.b f3558u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final L3.h f3559v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final L3.f f3560w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final o f3561x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final d f3562y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final c f3563z;

    public i(Context context, Object obj, A3.k kVar, Bitmap.Config config, L3.d dVar, u uVar, M3.e eVar, c9.u uVar2, q qVar, boolean z6, boolean z10, boolean z11, boolean z12, b bVar, b bVar2, b bVar3, AbstractC0721s abstractC0721s, AbstractC0721s abstractC0721s2, AbstractC0721s abstractC0721s3, AbstractC0721s abstractC0721s4, A9.b bVar4, L3.h hVar, L3.f fVar, o oVar, d dVar2, c cVar) {
        this.f3542a = context;
        this.f3543b = obj;
        this.c = kVar;
        this.d = config;
        this.f3544e = dVar;
        this.f3545f = uVar;
        this.f3546g = eVar;
        this.f3547h = uVar2;
        this.f3548i = qVar;
        this.f3549j = z6;
        this.f3550k = z10;
        this.l = z11;
        this.f3551m = z12;
        this.f3552n = bVar;
        this.o = bVar2;
        this.f3553p = bVar3;
        this.f3554q = abstractC0721s;
        this.f3555r = abstractC0721s2;
        this.f3556s = abstractC0721s3;
        this.f3557t = abstractC0721s4;
        this.f3558u = bVar4;
        this.f3559v = hVar;
        this.f3560w = fVar;
        this.f3561x = oVar;
        this.f3562y = dVar2;
        this.f3563z = cVar;
    }

    public static h a(i iVar) {
        Context context = iVar.f3542a;
        iVar.getClass();
        return new h(iVar, context);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (A7.m.a(this.f3542a, iVar.f3542a) && this.f3543b.equals(iVar.f3543b) && A7.m.a(this.c, iVar.c) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && this.d == iVar.d && A7.m.a(null, null) && this.f3544e == iVar.f3544e && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(this.f3545f, iVar.f3545f) && A7.m.a(this.f3546g, iVar.f3546g) && A7.m.a(this.f3547h, iVar.f3547h) && this.f3548i.equals(iVar.f3548i) && this.f3549j == iVar.f3549j && this.f3550k == iVar.f3550k && this.l == iVar.l && this.f3551m == iVar.f3551m && this.f3552n == iVar.f3552n && this.o == iVar.o && this.f3553p == iVar.f3553p && A7.m.a(this.f3554q, iVar.f3554q) && A7.m.a(this.f3555r, iVar.f3555r) && A7.m.a(this.f3556s, iVar.f3556s) && A7.m.a(this.f3557t, iVar.f3557t) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(this.f3558u, iVar.f3558u) && this.f3559v.equals(iVar.f3559v) && this.f3560w == iVar.f3560w && this.f3561x.equals(iVar.f3561x) && this.f3562y.equals(iVar.f3562y) && A7.m.a(this.f3563z, iVar.f3563z)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.f3543b.hashCode() + (this.f3542a.hashCode() * 31)) * 31;
        A3.k kVar = this.c;
        int iHashCode2 = (this.f3544e.hashCode() + ((this.d.hashCode() + ((iHashCode + (kVar != null ? kVar.hashCode() : 0)) * 923521)) * 961)) * 29791;
        this.f3545f.getClass();
        return this.f3563z.hashCode() + ((this.f3562y.hashCode() + ((this.f3561x.l.hashCode() + ((this.f3560w.hashCode() + ((this.f3559v.hashCode() + ((this.f3558u.hashCode() + ((this.f3557t.hashCode() + ((this.f3556s.hashCode() + ((this.f3555r.hashCode() + ((this.f3554q.hashCode() + ((this.f3553p.hashCode() + ((this.o.hashCode() + ((this.f3552n.hashCode() + AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d((this.f3548i.f3584a.hashCode() + ((((this.f3546g.hashCode() + ((1 + iHashCode2) * 31)) * 31) + Arrays.hashCode(this.f3547h.l)) * 31)) * 31, 31, this.f3549j), 31, this.f3550k), 31, this.l), 31, this.f3551m)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * (-1807454463))) * 31);
    }
}
