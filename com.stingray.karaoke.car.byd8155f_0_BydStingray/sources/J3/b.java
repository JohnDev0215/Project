package J3;

import A7.m;
import P8.n;
import Q8.h;
import android.graphics.Bitmap;
import c9.C1046g;
import c9.H;
import c9.u;
import c9.y;
import h6.r;
import java.util.regex.Pattern;
import m7.g;
import r9.C;
import r9.D;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3065a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3066b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f3067e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u f3068f;

    public b(D d) {
        g gVar = g.f19257m;
        final int i7 = 0;
        this.f3065a = r.n(gVar, new InterfaceC2772a(this) { // from class: J3.a

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ b f3064m;

            {
                this.f3064m = this;
            }

            @Override // z7.InterfaceC2772a
            public final Object a() {
                b bVar = this.f3064m;
                switch (i7) {
                    case 0:
                        C1046g c1046g = C1046g.f12558n;
                        return AbstractC2673a.R(bVar.f3068f);
                    default:
                        String strD = bVar.f3068f.d("Content-Type");
                        if (strD == null) {
                            return null;
                        }
                        Pattern pattern = y.d;
                        return n.L(strD);
                }
            }
        });
        final int i10 = 1;
        this.f3066b = r.n(gVar, new InterfaceC2772a(this) { // from class: J3.a

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ b f3064m;

            {
                this.f3064m = this;
            }

            @Override // z7.InterfaceC2772a
            public final Object a() {
                b bVar = this.f3064m;
                switch (i10) {
                    case 0:
                        C1046g c1046g = C1046g.f12558n;
                        return AbstractC2673a.R(bVar.f3068f);
                    default:
                        String strD = bVar.f3068f.d("Content-Type");
                        if (strD == null) {
                            return null;
                        }
                        Pattern pattern = y.d;
                        return n.L(strD);
                }
            }
        });
        this.c = Long.parseLong(d.G(Long.MAX_VALUE));
        this.d = Long.parseLong(d.G(Long.MAX_VALUE));
        this.f3067e = Integer.parseInt(d.G(Long.MAX_VALUE)) > 0;
        int i11 = Integer.parseInt(d.G(Long.MAX_VALUE));
        A7.D d10 = new A7.D(5, false);
        for (int i12 = 0; i12 < i11; i12++) {
            String strG = d.G(Long.MAX_VALUE);
            Bitmap.Config config = N3.d.f4575a;
            int iJ0 = h.J0(strG, ':', 0, false, 6);
            if (iJ0 == -1) {
                throw new IllegalArgumentException("Unexpected header: ".concat(strG).toString());
            }
            String strSubstring = strG.substring(0, iJ0);
            m.e(strSubstring, "substring(...)");
            String string = h.g1(strSubstring).toString();
            String strSubstring2 = strG.substring(iJ0 + 1);
            m.e(strSubstring2, "substring(...)");
            d10.k(string, strSubstring2);
        }
        this.f3068f = d10.m();
    }

    public final void a(C c) {
        c.E(this.c);
        c.s(10);
        c.E(this.d);
        c.s(10);
        c.E(this.f3067e ? 1L : 0L);
        c.s(10);
        u uVar = this.f3068f;
        c.E(uVar.size());
        c.s(10);
        int size = uVar.size();
        for (int i7 = 0; i7 < size; i7++) {
            c.D(uVar.l(i7));
            c.D(": ");
            c.D(uVar.s(i7));
            c.s(10);
        }
    }

    public b(H h10) {
        g gVar = g.f19257m;
        final int i7 = 0;
        this.f3065a = r.n(gVar, new InterfaceC2772a(this) { // from class: J3.a

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ b f3064m;

            {
                this.f3064m = this;
            }

            @Override // z7.InterfaceC2772a
            public final Object a() {
                b bVar = this.f3064m;
                switch (i7) {
                    case 0:
                        C1046g c1046g = C1046g.f12558n;
                        return AbstractC2673a.R(bVar.f3068f);
                    default:
                        String strD = bVar.f3068f.d("Content-Type");
                        if (strD == null) {
                            return null;
                        }
                        Pattern pattern = y.d;
                        return n.L(strD);
                }
            }
        });
        final int i10 = 1;
        this.f3066b = r.n(gVar, new InterfaceC2772a(this) { // from class: J3.a

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ b f3064m;

            {
                this.f3064m = this;
            }

            @Override // z7.InterfaceC2772a
            public final Object a() {
                b bVar = this.f3064m;
                switch (i10) {
                    case 0:
                        C1046g c1046g = C1046g.f12558n;
                        return AbstractC2673a.R(bVar.f3068f);
                    default:
                        String strD = bVar.f3068f.d("Content-Type");
                        if (strD == null) {
                            return null;
                        }
                        Pattern pattern = y.d;
                        return n.L(strD);
                }
            }
        });
        this.c = h10.f12522v;
        this.d = h10.f12523w;
        this.f3067e = h10.f12516p != null;
        this.f3068f = h10.f12517q;
    }
}
