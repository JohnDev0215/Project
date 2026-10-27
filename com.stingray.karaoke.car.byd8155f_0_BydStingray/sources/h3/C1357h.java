package h3;

import B2.L;
import e2.C1207o;
import e2.C1208p;
import h2.AbstractC1346c;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: h3.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1357h implements InterfaceC1358i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f15350b;
    public long c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f15351e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f15352f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f15353g;

    public C1357h(List list) {
        this.f15349a = 0;
        this.f15352f = list;
        this.f15353g = new L[list.size()];
        this.c = -9223372036854775807L;
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        switch (this.f15349a) {
            case 0:
                this.f15350b = false;
                this.c = -9223372036854775807L;
                break;
            default:
                this.f15350b = false;
                this.c = -9223372036854775807L;
                break;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void c(h2.t tVar) {
        boolean z6;
        boolean z10;
        switch (this.f15349a) {
            case 0:
                if (this.f15350b) {
                    if (this.d == 2) {
                        if (tVar.a() == 0) {
                            z10 = false;
                        } else {
                            if (tVar.v() != 32) {
                                this.f15350b = false;
                            }
                            this.d--;
                            z10 = this.f15350b;
                        }
                        if (!z10) {
                        }
                    }
                    if (this.d == 1) {
                        if (tVar.a() == 0) {
                            z6 = false;
                        } else {
                            if (tVar.v() != 0) {
                                this.f15350b = false;
                            }
                            this.d--;
                            z6 = this.f15350b;
                        }
                        if (!z6) {
                        }
                    }
                    int i7 = tVar.f15242b;
                    int iA = tVar.a();
                    for (L l : (L[]) this.f15353g) {
                        tVar.H(i7);
                        l.a(tVar, iA, 0);
                    }
                    this.f15351e += iA;
                }
                break;
            default:
                AbstractC1346c.k((L) this.f15353g);
                if (this.f15350b) {
                    int iA2 = tVar.a();
                    int i10 = this.f15351e;
                    if (i10 < 10) {
                        int iMin = Math.min(iA2, 10 - i10);
                        byte[] bArr = tVar.f15241a;
                        int i11 = tVar.f15242b;
                        h2.t tVar2 = (h2.t) this.f15352f;
                        System.arraycopy(bArr, i11, tVar2.f15241a, this.f15351e, iMin);
                        if (this.f15351e + iMin == 10) {
                            tVar2.H(0);
                            if (73 == tVar2.v() && 68 == tVar2.v() && 51 == tVar2.v()) {
                                tVar2.I(3);
                                this.d = tVar2.u() + 10;
                            } else {
                                AbstractC1346c.z("Id3Reader", "Discarding invalid ID3 tag");
                                this.f15350b = false;
                            }
                        }
                    }
                    int iMin2 = Math.min(iA2, this.d - this.f15351e);
                    ((L) this.f15353g).a(tVar, iMin2, 0);
                    this.f15351e += iMin2;
                    break;
                }
                break;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
        int i7;
        switch (this.f15349a) {
            case 0:
                if (this.f15350b) {
                    AbstractC1346c.j(this.c != -9223372036854775807L);
                    for (L l : (L[]) this.f15353g) {
                        l.b(this.c, 1, this.f15351e, 0, null);
                    }
                    this.f15350b = false;
                }
                break;
            default:
                AbstractC1346c.k((L) this.f15353g);
                if (this.f15350b && (i7 = this.d) != 0 && this.f15351e == i7) {
                    AbstractC1346c.j(this.c != -9223372036854775807L);
                    ((L) this.f15353g).b(this.c, 1, this.d, 0, null);
                    this.f15350b = false;
                    break;
                }
                break;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        switch (this.f15349a) {
            case 0:
                if ((i7 & 4) != 0) {
                    this.f15350b = true;
                    this.c = j3;
                    this.f15351e = 0;
                    this.d = 2;
                    break;
                }
                break;
            default:
                if ((i7 & 4) != 0) {
                    this.f15350b = true;
                    this.c = j3;
                    this.d = 0;
                    this.f15351e = 0;
                    break;
                }
                break;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        switch (this.f15349a) {
            case 0:
                int i7 = 0;
                while (true) {
                    L[] lArr = (L[]) this.f15353g;
                    if (i7 < lArr.length) {
                        G g7 = (G) ((List) this.f15352f).get(i7);
                        h10.a();
                        h10.b();
                        L lU = tVar.u(h10.d, 3);
                        C1207o c1207o = new C1207o();
                        h10.b();
                        c1207o.f14237a = h10.f15293e;
                        c1207o.f14246m = e2.L.l("application/dvbsubs");
                        c1207o.f14248p = Collections.singletonList(g7.f15290b);
                        c1207o.d = g7.f15289a;
                        lU.d(new C1208p(c1207o));
                        lArr[i7] = lU;
                        i7++;
                    }
                    break;
                }
                break;
            default:
                h10.a();
                h10.b();
                L lU2 = tVar.u(h10.d, 5);
                this.f15353g = lU2;
                C1207o c1207o2 = new C1207o();
                h10.b();
                c1207o2.f14237a = h10.f15293e;
                c1207o2.f14246m = e2.L.l("application/id3");
                lU2.d(new C1208p(c1207o2));
                break;
        }
    }

    public C1357h() {
        this.f15349a = 1;
        this.f15352f = new h2.t(10);
        this.c = -9223372036854775807L;
    }
}
