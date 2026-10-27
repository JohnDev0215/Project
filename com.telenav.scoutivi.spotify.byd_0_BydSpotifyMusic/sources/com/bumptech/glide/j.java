package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: RequestBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class j<TranscodeType> extends g2.a<j<TranscodeType>> {
    protected static final g2.f T = new g2.f().e(q1.j.f9023c).S(g.LOW).Z(true);
    private final Context F;
    private final k G;
    private final Class<TranscodeType> H;
    private final c I;
    private final e J;
    private l<?, ? super TranscodeType> K;
    private Object L;
    private List<g2.e<TranscodeType>> M;
    private j<TranscodeType> N;
    private j<TranscodeType> O;
    private Float P;
    private boolean Q = true;
    private boolean R;
    private boolean S;

    /* JADX INFO: compiled from: RequestBuilder.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f3997b;

        static {
            int[] iArr = new int[g.values().length];
            f3997b = iArr;
            try {
                iArr[g.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3997b[g.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3997b[g.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3997b[g.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f3996a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3996a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3996a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3996a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3996a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3996a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3996a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3996a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @SuppressLint({"CheckResult"})
    protected j(c cVar, k kVar, Class<TranscodeType> cls, Context context) {
        this.I = cVar;
        this.G = kVar;
        this.H = cls;
        this.F = context;
        this.K = kVar.r(cls);
        this.J = cVar.i();
        p0(kVar.p());
        a(kVar.q());
    }

    private g2.c k0(h2.h<TranscodeType> hVar, g2.e<TranscodeType> eVar, g2.a<?> aVar, Executor executor) {
        return l0(new Object(), hVar, eVar, null, this.K, aVar.t(), aVar.q(), aVar.p(), aVar, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private g2.c l0(Object obj, h2.h<TranscodeType> hVar, g2.e<TranscodeType> eVar, g2.d dVar, l<?, ? super TranscodeType> lVar, g gVar, int i7, int i8, g2.a<?> aVar, Executor executor) {
        g2.d dVar2;
        g2.d bVar;
        if (this.O != null) {
            bVar = new g2.b(obj, dVar);
            dVar2 = bVar;
        } else {
            dVar2 = null;
            bVar = dVar;
        }
        g2.c cVarM0 = m0(obj, hVar, eVar, bVar, lVar, gVar, i7, i8, aVar, executor);
        if (dVar2 == null) {
            return cVarM0;
        }
        int iQ = this.O.q();
        int iP = this.O.p();
        if (k2.k.s(i7, i8) && !this.O.J()) {
            iQ = aVar.q();
            iP = aVar.p();
        }
        j<TranscodeType> jVar = this.O;
        g2.b bVar2 = dVar2;
        bVar2.q(cVarM0, jVar.l0(obj, hVar, eVar, bVar2, jVar.K, jVar.t(), iQ, iP, this.O, executor));
        return bVar2;
    }

    private g2.c m0(Object obj, h2.h<TranscodeType> hVar, g2.e<TranscodeType> eVar, g2.d dVar, l<?, ? super TranscodeType> lVar, g gVar, int i7, int i8, g2.a<?> aVar, Executor executor) {
        j<TranscodeType> jVar = this.N;
        if (jVar == null) {
            if (this.P == null) {
                return z0(obj, hVar, eVar, aVar, dVar, lVar, gVar, i7, i8, executor);
            }
            g2.i iVar = new g2.i(obj, dVar);
            iVar.q(z0(obj, hVar, eVar, aVar, iVar, lVar, gVar, i7, i8, executor), z0(obj, hVar, eVar, aVar.clone().Y(this.P.floatValue()), iVar, lVar, o0(gVar), i7, i8, executor));
            return iVar;
        }
        if (this.S) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        l<?, ? super TranscodeType> lVar2 = jVar.Q ? lVar : jVar.K;
        g gVarT = jVar.C() ? this.N.t() : o0(gVar);
        int iQ = this.N.q();
        int iP = this.N.p();
        if (k2.k.s(i7, i8) && !this.N.J()) {
            iQ = aVar.q();
            iP = aVar.p();
        }
        g2.i iVar2 = new g2.i(obj, dVar);
        g2.c cVarZ0 = z0(obj, hVar, eVar, aVar, iVar2, lVar, gVar, i7, i8, executor);
        this.S = true;
        j<TranscodeType> jVar2 = this.N;
        g2.c cVarL0 = jVar2.l0(obj, hVar, eVar, iVar2, lVar2, gVarT, iQ, iP, jVar2, executor);
        this.S = false;
        iVar2.q(cVarZ0, cVarL0);
        return iVar2;
    }

    private g o0(g gVar) {
        int i7 = a.f3997b[gVar.ordinal()];
        if (i7 == 1) {
            return g.NORMAL;
        }
        if (i7 == 2) {
            return g.HIGH;
        }
        if (i7 == 3 || i7 == 4) {
            return g.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + t());
    }

    @SuppressLint({"CheckResult"})
    private void p0(List<g2.e<Object>> list) {
        Iterator<g2.e<Object>> it = list.iterator();
        while (it.hasNext()) {
            i0((g2.e) it.next());
        }
    }

    private <Y extends h2.h<TranscodeType>> Y r0(Y y6, g2.e<TranscodeType> eVar, g2.a<?> aVar, Executor executor) {
        k2.j.d(y6);
        if (!this.R) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        g2.c cVarK0 = k0(y6, eVar, aVar, executor);
        g2.c cVarH = y6.h();
        if (cVarK0.g(cVarH) && !u0(aVar, cVarH)) {
            if (!((g2.c) k2.j.d(cVarH)).isRunning()) {
                cVarH.d();
            }
            return y6;
        }
        this.G.o(y6);
        y6.l(cVarK0);
        this.G.y(y6, cVarK0);
        return y6;
    }

    private boolean u0(g2.a<?> aVar, g2.c cVar) {
        return !aVar.B() && cVar.h();
    }

    private j<TranscodeType> y0(Object obj) {
        this.L = obj;
        this.R = true;
        return this;
    }

    private g2.c z0(Object obj, h2.h<TranscodeType> hVar, g2.e<TranscodeType> eVar, g2.a<?> aVar, g2.d dVar, l<?, ? super TranscodeType> lVar, g gVar, int i7, int i8, Executor executor) {
        Context context = this.F;
        e eVar2 = this.J;
        return g2.h.x(context, eVar2, obj, this.L, this.H, aVar, i7, i8, gVar, hVar, eVar, this.M, dVar, eVar2.f(), lVar.b(), executor);
    }

    public j<TranscodeType> A0(l<?, ? super TranscodeType> lVar) {
        this.K = (l) k2.j.d(lVar);
        this.Q = false;
        return this;
    }

    public j<TranscodeType> i0(g2.e<TranscodeType> eVar) {
        if (eVar != null) {
            if (this.M == null) {
                this.M = new ArrayList();
            }
            this.M.add(eVar);
        }
        return this;
    }

    @Override // g2.a
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public j<TranscodeType> a(g2.a<?> aVar) {
        k2.j.d(aVar);
        return (j) super.a(aVar);
    }

    @Override // g2.a
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public j<TranscodeType> c() {
        j<TranscodeType> jVar = (j) super.clone();
        jVar.K = jVar.K.clone();
        return jVar;
    }

    public <Y extends h2.h<TranscodeType>> Y q0(Y y6) {
        return (Y) s0(y6, null, k2.e.b());
    }

    <Y extends h2.h<TranscodeType>> Y s0(Y y6, g2.e<TranscodeType> eVar, Executor executor) {
        return (Y) r0(y6, eVar, this, executor);
    }

    public h2.i<ImageView, TranscodeType> t0(ImageView imageView) {
        g2.a aVarL;
        k2.k.a();
        k2.j.d(imageView);
        if (!I() && G() && imageView.getScaleType() != null) {
            switch (a.f3996a[imageView.getScaleType().ordinal()]) {
                case 1:
                    aVarL = clone().L();
                    break;
                case 2:
                    aVarL = clone().M();
                    break;
                case 3:
                case 4:
                case 5:
                    aVarL = clone().N();
                    break;
                case 6:
                    aVarL = clone().M();
                    break;
                default:
                    aVarL = this;
                    break;
            }
        } else {
            aVarL = this;
        }
        return (h2.i) r0(this.J.a(imageView, this.H), null, aVarL, k2.e.b());
    }

    public j<TranscodeType> v0(Uri uri) {
        return y0(uri);
    }

    public j<TranscodeType> w0(Integer num) {
        return y0(num).a(g2.f.k0(j2.a.c(this.F)));
    }

    public j<TranscodeType> x0(Object obj) {
        return y0(obj);
    }
}
