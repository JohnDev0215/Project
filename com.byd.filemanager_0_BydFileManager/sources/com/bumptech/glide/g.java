package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.o.j;
import com.bumptech.glide.o.k;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.SingleRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: RequestBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class g<TranscodeType> extends com.bumptech.glide.request.a<g<TranscodeType>> implements Cloneable {
    protected static final com.bumptech.glide.request.e B = new com.bumptech.glide.request.e().f(com.bumptech.glide.load.engine.h.f3023c).O(Priority.LOW).U(true);
    private final Context C;
    private final h D;
    private final Class<TranscodeType> E;
    private final c F;
    private final e G;
    private i<?, ? super TranscodeType> H;
    private Object I;
    private List<com.bumptech.glide.request.d<TranscodeType>> J;
    private g<TranscodeType> K;
    private g<TranscodeType> L;
    private Float M;
    private boolean N = true;
    private boolean O;
    private boolean P;

    /* JADX INFO: compiled from: RequestBuilder.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2858a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2859b;

        static {
            int[] iArr = new int[Priority.values().length];
            f2859b = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2859b[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2859b[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2859b[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f2858a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2858a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2858a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2858a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2858a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2858a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2858a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2858a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @SuppressLint({"CheckResult"})
    protected g(c cVar, h hVar, Class<TranscodeType> cls, Context context) {
        this.F = cVar;
        this.D = hVar;
        this.E = cls;
        this.C = context;
        this.H = hVar.r(cls);
        this.G = cVar.i();
        h0(hVar.p());
        a(hVar.q());
    }

    private com.bumptech.glide.request.c c0(com.bumptech.glide.request.h.e<TranscodeType> eVar, com.bumptech.glide.request.d<TranscodeType> dVar, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        return d0(new Object(), eVar, dVar, null, this.H, aVar.w(), aVar.t(), aVar.s(), aVar, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private com.bumptech.glide.request.c d0(Object obj, com.bumptech.glide.request.h.e<TranscodeType> eVar, com.bumptech.glide.request.d<TranscodeType> dVar, RequestCoordinator requestCoordinator, i<?, ? super TranscodeType> iVar, Priority priority, int i, int i2, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        RequestCoordinator requestCoordinator2;
        RequestCoordinator bVar;
        if (this.L != null) {
            bVar = new com.bumptech.glide.request.b(obj, requestCoordinator);
            requestCoordinator2 = bVar;
        } else {
            requestCoordinator2 = null;
            bVar = requestCoordinator;
        }
        com.bumptech.glide.request.c cVarE0 = e0(obj, eVar, dVar, bVar, iVar, priority, i, i2, aVar, executor);
        if (requestCoordinator2 == null) {
            return cVarE0;
        }
        int iT = this.L.t();
        int iS = this.L.s();
        if (k.r(i, i2) && !this.L.K()) {
            iT = aVar.t();
            iS = aVar.s();
        }
        g<TranscodeType> gVar = this.L;
        com.bumptech.glide.request.b bVar2 = requestCoordinator2;
        bVar2.q(cVarE0, gVar.d0(obj, eVar, dVar, bVar2, gVar.H, gVar.w(), iT, iS, this.L, executor));
        return bVar2;
    }

    private com.bumptech.glide.request.c e0(Object obj, com.bumptech.glide.request.h.e<TranscodeType> eVar, com.bumptech.glide.request.d<TranscodeType> dVar, RequestCoordinator requestCoordinator, i<?, ? super TranscodeType> iVar, Priority priority, int i, int i2, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        g<TranscodeType> gVar = this.K;
        if (gVar == null) {
            if (this.M == null) {
                return p0(obj, eVar, dVar, aVar, requestCoordinator, iVar, priority, i, i2, executor);
            }
            com.bumptech.glide.request.g gVar2 = new com.bumptech.glide.request.g(obj, requestCoordinator);
            gVar2.p(p0(obj, eVar, dVar, aVar, gVar2, iVar, priority, i, i2, executor), p0(obj, eVar, dVar, aVar.clone().T(this.M.floatValue()), gVar2, iVar, g0(priority), i, i2, executor));
            return gVar2;
        }
        if (this.P) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        i<?, ? super TranscodeType> iVar2 = gVar.N ? iVar : gVar.H;
        Priority priorityW = gVar.F() ? this.K.w() : g0(priority);
        int iT = this.K.t();
        int iS = this.K.s();
        if (k.r(i, i2) && !this.K.K()) {
            iT = aVar.t();
            iS = aVar.s();
        }
        com.bumptech.glide.request.g gVar3 = new com.bumptech.glide.request.g(obj, requestCoordinator);
        com.bumptech.glide.request.c cVarP0 = p0(obj, eVar, dVar, aVar, gVar3, iVar, priority, i, i2, executor);
        this.P = true;
        g<TranscodeType> gVar4 = this.K;
        com.bumptech.glide.request.c cVarD0 = gVar4.d0(obj, eVar, dVar, gVar3, iVar2, priorityW, iT, iS, gVar4, executor);
        this.P = false;
        gVar3.p(cVarP0, cVarD0);
        return gVar3;
    }

    private Priority g0(Priority priority) {
        int i = a.f2859b[priority.ordinal()];
        if (i == 1) {
            return Priority.NORMAL;
        }
        if (i == 2) {
            return Priority.HIGH;
        }
        if (i == 3 || i == 4) {
            return Priority.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + w());
    }

    @SuppressLint({"CheckResult"})
    private void h0(List<com.bumptech.glide.request.d<Object>> list) {
        Iterator<com.bumptech.glide.request.d<Object>> it = list.iterator();
        while (it.hasNext()) {
            a0((com.bumptech.glide.request.d) it.next());
        }
    }

    private <Y extends com.bumptech.glide.request.h.e<TranscodeType>> Y j0(Y y, com.bumptech.glide.request.d<TranscodeType> dVar, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        j.d(y);
        if (!this.O) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        com.bumptech.glide.request.c cVarC0 = c0(y, dVar, aVar, executor);
        com.bumptech.glide.request.c cVarG = y.g();
        if (cVarC0.h(cVarG) && !l0(aVar, cVarG)) {
            if (!((com.bumptech.glide.request.c) j.d(cVarG)).isRunning()) {
                cVarG.f();
            }
            return y;
        }
        this.D.o(y);
        y.j(cVarC0);
        this.D.z(y, cVarC0);
        return y;
    }

    private boolean l0(com.bumptech.glide.request.a<?> aVar, com.bumptech.glide.request.c cVar) {
        return !aVar.E() && cVar.i();
    }

    private g<TranscodeType> o0(Object obj) {
        this.I = obj;
        this.O = true;
        return this;
    }

    private com.bumptech.glide.request.c p0(Object obj, com.bumptech.glide.request.h.e<TranscodeType> eVar, com.bumptech.glide.request.d<TranscodeType> dVar, com.bumptech.glide.request.a<?> aVar, RequestCoordinator requestCoordinator, i<?, ? super TranscodeType> iVar, Priority priority, int i, int i2, Executor executor) {
        Context context = this.C;
        e eVar2 = this.G;
        return SingleRequest.y(context, eVar2, obj, this.I, this.E, aVar, i, i2, priority, eVar, dVar, this.J, requestCoordinator, eVar2.e(), iVar.b(), executor);
    }

    public g<TranscodeType> a0(com.bumptech.glide.request.d<TranscodeType> dVar) {
        if (dVar != null) {
            if (this.J == null) {
                this.J = new ArrayList();
            }
            this.J.add(dVar);
        }
        return this;
    }

    @Override // com.bumptech.glide.request.a
    /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] */
    public g<TranscodeType> a(com.bumptech.glide.request.a<?> aVar) {
        j.d(aVar);
        return (g) super.a(aVar);
    }

    @Override // com.bumptech.glide.request.a
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public g<TranscodeType> clone() {
        g<TranscodeType> gVar = (g) super.clone();
        gVar.H = gVar.H.clone();
        return gVar;
    }

    public <Y extends com.bumptech.glide.request.h.e<TranscodeType>> Y i0(Y y) {
        return (Y) k0(y, null, com.bumptech.glide.o.e.b());
    }

    <Y extends com.bumptech.glide.request.h.e<TranscodeType>> Y k0(Y y, com.bumptech.glide.request.d<TranscodeType> dVar, Executor executor) {
        return (Y) j0(y, dVar, this, executor);
    }

    public g<TranscodeType> m0(Uri uri) {
        return o0(uri);
    }

    public g<TranscodeType> n0(Object obj) {
        return o0(obj);
    }
}
