package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class j<TranscodeType> extends com.bumptech.glide.r.a<j<TranscodeType>> implements Cloneable {
    protected static final com.bumptech.glide.r.f B = new com.bumptech.glide.r.f().e(com.bumptech.glide.load.n.j.f2143c).K(g.LOW).Q(true);
    private final Context C;
    private final k D;
    private final Class<TranscodeType> E;
    private final c F;
    private final e G;
    private l<?, ? super TranscodeType> H;
    private Object I;
    private List<com.bumptech.glide.r.e<TranscodeType>> J;
    private j<TranscodeType> K;
    private j<TranscodeType> L;
    private Float M;
    private boolean N = true;
    private boolean O;
    private boolean P;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1950a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f1951b;

        static {
            int[] iArr = new int[g.values().length];
            f1951b = iArr;
            try {
                iArr[g.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1951b[g.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1951b[g.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1951b[g.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f1950a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1950a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1950a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1950a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1950a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1950a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1950a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1950a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @SuppressLint({"CheckResult"})
    protected j(c cVar, k kVar, Class<TranscodeType> cls, Context context) {
        this.F = cVar;
        this.D = kVar;
        this.E = cls;
        this.C = context;
        this.H = kVar.r(cls);
        this.G = cVar.i();
        c0(kVar.p());
        a(kVar.q());
    }

    private com.bumptech.glide.r.c X(com.bumptech.glide.r.j.e<TranscodeType> eVar, com.bumptech.glide.r.e<TranscodeType> eVar2, com.bumptech.glide.r.a<?> aVar, Executor executor) {
        return Y(new Object(), eVar, eVar2, null, this.H, aVar.s(), aVar.p(), aVar.o(), aVar, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private com.bumptech.glide.r.c Y(Object obj, com.bumptech.glide.r.j.e<TranscodeType> eVar, com.bumptech.glide.r.e<TranscodeType> eVar2, com.bumptech.glide.r.d dVar, l<?, ? super TranscodeType> lVar, g gVar, int i, int i2, com.bumptech.glide.r.a<?> aVar, Executor executor) {
        com.bumptech.glide.r.d dVar2;
        com.bumptech.glide.r.d bVar;
        if (this.L != null) {
            bVar = new com.bumptech.glide.r.b(obj, dVar);
            dVar2 = bVar;
        } else {
            dVar2 = null;
            bVar = dVar;
        }
        com.bumptech.glide.r.c cVarZ = Z(obj, eVar, eVar2, bVar, lVar, gVar, i, i2, aVar, executor);
        if (dVar2 == null) {
            return cVarZ;
        }
        int iP = this.L.p();
        int iO = this.L.o();
        if (com.bumptech.glide.t.k.r(i, i2) && !this.L.G()) {
            iP = aVar.p();
            iO = aVar.o();
        }
        j<TranscodeType> jVar = this.L;
        com.bumptech.glide.r.b bVar2 = dVar2;
        bVar2.q(cVarZ, jVar.Y(obj, eVar, eVar2, bVar2, jVar.H, jVar.s(), iP, iO, this.L, executor));
        return bVar2;
    }

    private com.bumptech.glide.r.c Z(Object obj, com.bumptech.glide.r.j.e<TranscodeType> eVar, com.bumptech.glide.r.e<TranscodeType> eVar2, com.bumptech.glide.r.d dVar, l<?, ? super TranscodeType> lVar, g gVar, int i, int i2, com.bumptech.glide.r.a<?> aVar, Executor executor) {
        j<TranscodeType> jVar = this.K;
        if (jVar == null) {
            if (this.M == null) {
                return k0(obj, eVar, eVar2, aVar, dVar, lVar, gVar, i, i2, executor);
            }
            com.bumptech.glide.r.i iVar = new com.bumptech.glide.r.i(obj, dVar);
            iVar.p(k0(obj, eVar, eVar2, aVar, iVar, lVar, gVar, i, i2, executor), k0(obj, eVar, eVar2, aVar.clone().P(this.M.floatValue()), iVar, lVar, b0(gVar), i, i2, executor));
            return iVar;
        }
        if (this.P) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        l<?, ? super TranscodeType> lVar2 = jVar.N ? lVar : jVar.H;
        g gVarS = jVar.B() ? this.K.s() : b0(gVar);
        int iP = this.K.p();
        int iO = this.K.o();
        if (com.bumptech.glide.t.k.r(i, i2) && !this.K.G()) {
            iP = aVar.p();
            iO = aVar.o();
        }
        com.bumptech.glide.r.i iVar2 = new com.bumptech.glide.r.i(obj, dVar);
        com.bumptech.glide.r.c cVarK0 = k0(obj, eVar, eVar2, aVar, iVar2, lVar, gVar, i, i2, executor);
        this.P = true;
        j<TranscodeType> jVar2 = this.K;
        com.bumptech.glide.r.c cVarY = jVar2.Y(obj, eVar, eVar2, iVar2, lVar2, gVarS, iP, iO, jVar2, executor);
        this.P = false;
        iVar2.p(cVarK0, cVarY);
        return iVar2;
    }

    private g b0(g gVar) {
        int i = a.f1951b[gVar.ordinal()];
        if (i == 1) {
            return g.NORMAL;
        }
        if (i == 2) {
            return g.HIGH;
        }
        if (i == 3 || i == 4) {
            return g.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + s());
    }

    @SuppressLint({"CheckResult"})
    private void c0(List<com.bumptech.glide.r.e<Object>> list) {
        Iterator<com.bumptech.glide.r.e<Object>> it = list.iterator();
        while (it.hasNext()) {
            V((com.bumptech.glide.r.e) it.next());
        }
    }

    private <Y extends com.bumptech.glide.r.j.e<TranscodeType>> Y e0(Y y, com.bumptech.glide.r.e<TranscodeType> eVar, com.bumptech.glide.r.a<?> aVar, Executor executor) {
        com.bumptech.glide.t.j.d(y);
        if (!this.O) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        com.bumptech.glide.r.c cVarX = X(y, eVar, aVar, executor);
        com.bumptech.glide.r.c cVarG = y.g();
        if (cVarX.h(cVarG) && !g0(aVar, cVarG)) {
            if (!((com.bumptech.glide.r.c) com.bumptech.glide.t.j.d(cVarG)).isRunning()) {
                cVarG.f();
            }
            return y;
        }
        this.D.o(y);
        y.j(cVarX);
        this.D.z(y, cVarX);
        return y;
    }

    private boolean g0(com.bumptech.glide.r.a<?> aVar, com.bumptech.glide.r.c cVar) {
        return !aVar.A() && cVar.i();
    }

    private j<TranscodeType> j0(Object obj) {
        this.I = obj;
        this.O = true;
        return this;
    }

    private com.bumptech.glide.r.c k0(Object obj, com.bumptech.glide.r.j.e<TranscodeType> eVar, com.bumptech.glide.r.e<TranscodeType> eVar2, com.bumptech.glide.r.a<?> aVar, com.bumptech.glide.r.d dVar, l<?, ? super TranscodeType> lVar, g gVar, int i, int i2, Executor executor) {
        Context context = this.C;
        e eVar3 = this.G;
        return com.bumptech.glide.r.h.y(context, eVar3, obj, this.I, this.E, aVar, i, i2, gVar, eVar, eVar2, this.J, dVar, eVar3.e(), lVar.b(), executor);
    }

    public j<TranscodeType> V(com.bumptech.glide.r.e<TranscodeType> eVar) {
        if (eVar != null) {
            if (this.J == null) {
                this.J = new ArrayList();
            }
            this.J.add(eVar);
        }
        return this;
    }

    @Override // com.bumptech.glide.r.a
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public j<TranscodeType> a(com.bumptech.glide.r.a<?> aVar) {
        com.bumptech.glide.t.j.d(aVar);
        return (j) super.a(aVar);
    }

    @Override // com.bumptech.glide.r.a
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public j<TranscodeType> clone() {
        j<TranscodeType> jVar = (j) super.clone();
        jVar.H = jVar.H.clone();
        return jVar;
    }

    public <Y extends com.bumptech.glide.r.j.e<TranscodeType>> Y d0(Y y) {
        return (Y) f0(y, null, com.bumptech.glide.t.e.b());
    }

    <Y extends com.bumptech.glide.r.j.e<TranscodeType>> Y f0(Y y, com.bumptech.glide.r.e<TranscodeType> eVar, Executor executor) {
        return (Y) e0(y, eVar, this, executor);
    }

    public j<TranscodeType> h0(Uri uri) {
        return j0(uri);
    }

    public j<TranscodeType> i0(Object obj) {
        return j0(obj);
    }
}
