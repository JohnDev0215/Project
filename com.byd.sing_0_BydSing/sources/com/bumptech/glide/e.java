package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.m.j;
import com.bumptech.glide.m.k;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.SingleRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: RequestBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class e<TranscodeType> extends com.bumptech.glide.request.a<e<TranscodeType>> implements Cloneable {
    protected static final com.bumptech.glide.request.e B = new com.bumptech.glide.request.e().e(h.f2786c).H(Priority.LOW).M(true);
    private final Context C;
    private final f D;
    private final Class<TranscodeType> E;
    private final b F;
    private final d G;
    private g<?, ? super TranscodeType> H;
    private Object I;
    private List<com.bumptech.glide.request.d<TranscodeType>> J;
    private e<TranscodeType> K;
    private e<TranscodeType> L;
    private Float M;
    private boolean N = true;
    private boolean O;
    private boolean P;

    /* JADX INFO: compiled from: RequestBuilder.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2655b;

        static {
            int[] iArr = new int[Priority.values().length];
            f2655b = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2655b[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2655b[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2655b[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f2654a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2654a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2654a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2654a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2654a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2654a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2654a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2654a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @SuppressLint({"CheckResult"})
    protected e(b bVar, f fVar, Class<TranscodeType> cls, Context context) {
        this.F = bVar;
        this.D = fVar;
        this.E = cls;
        this.C = context;
        this.H = fVar.q(cls);
        this.G = bVar.i();
        Y(fVar.o());
        a(fVar.p());
    }

    private com.bumptech.glide.request.c T(com.bumptech.glide.request.h.d<TranscodeType> dVar, com.bumptech.glide.request.d<TranscodeType> dVar2, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        return U(new Object(), dVar, dVar2, null, this.H, aVar.q(), aVar.n(), aVar.m(), aVar, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private com.bumptech.glide.request.c U(Object obj, com.bumptech.glide.request.h.d<TranscodeType> dVar, com.bumptech.glide.request.d<TranscodeType> dVar2, RequestCoordinator requestCoordinator, g<?, ? super TranscodeType> gVar, Priority priority, int i, int i2, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        RequestCoordinator requestCoordinator2;
        RequestCoordinator bVar;
        if (this.L != null) {
            bVar = new com.bumptech.glide.request.b(obj, requestCoordinator);
            requestCoordinator2 = bVar;
        } else {
            requestCoordinator2 = null;
            bVar = requestCoordinator;
        }
        com.bumptech.glide.request.c cVarV = V(obj, dVar, dVar2, bVar, gVar, priority, i, i2, aVar, executor);
        if (requestCoordinator2 == null) {
            return cVarV;
        }
        int iN = this.L.n();
        int iM = this.L.m();
        if (k.r(i, i2) && !this.L.E()) {
            iN = aVar.n();
            iM = aVar.m();
        }
        e<TranscodeType> eVar = this.L;
        com.bumptech.glide.request.b bVar2 = requestCoordinator2;
        bVar2.p(cVarV, eVar.U(obj, dVar, dVar2, bVar2, eVar.H, eVar.q(), iN, iM, this.L, executor));
        return bVar2;
    }

    private com.bumptech.glide.request.c V(Object obj, com.bumptech.glide.request.h.d<TranscodeType> dVar, com.bumptech.glide.request.d<TranscodeType> dVar2, RequestCoordinator requestCoordinator, g<?, ? super TranscodeType> gVar, Priority priority, int i, int i2, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        e<TranscodeType> eVar = this.K;
        if (eVar == null) {
            if (this.M == null) {
                return f0(obj, dVar, dVar2, aVar, requestCoordinator, gVar, priority, i, i2, executor);
            }
            com.bumptech.glide.request.g gVar2 = new com.bumptech.glide.request.g(obj, requestCoordinator);
            gVar2.o(f0(obj, dVar, dVar2, aVar, gVar2, gVar, priority, i, i2, executor), f0(obj, dVar, dVar2, aVar.clone().L(this.M.floatValue()), gVar2, gVar, X(priority), i, i2, executor));
            return gVar2;
        }
        if (this.P) {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        g<?, ? super TranscodeType> gVar3 = eVar.N ? gVar : eVar.H;
        Priority priorityQ = eVar.z() ? this.K.q() : X(priority);
        int iN = this.K.n();
        int iM = this.K.m();
        if (k.r(i, i2) && !this.K.E()) {
            iN = aVar.n();
            iM = aVar.m();
        }
        com.bumptech.glide.request.g gVar4 = new com.bumptech.glide.request.g(obj, requestCoordinator);
        com.bumptech.glide.request.c cVarF0 = f0(obj, dVar, dVar2, aVar, gVar4, gVar, priority, i, i2, executor);
        this.P = true;
        e<TranscodeType> eVar2 = this.K;
        com.bumptech.glide.request.c cVarU = eVar2.U(obj, dVar, dVar2, gVar4, gVar3, priorityQ, iN, iM, eVar2, executor);
        this.P = false;
        gVar4.o(cVarF0, cVarU);
        return gVar4;
    }

    private Priority X(Priority priority) {
        int i = a.f2655b[priority.ordinal()];
        if (i == 1) {
            return Priority.NORMAL;
        }
        if (i == 2) {
            return Priority.HIGH;
        }
        if (i == 3 || i == 4) {
            return Priority.IMMEDIATE;
        }
        throw new IllegalArgumentException("unknown priority: " + q());
    }

    @SuppressLint({"CheckResult"})
    private void Y(List<com.bumptech.glide.request.d<Object>> list) {
        Iterator<com.bumptech.glide.request.d<Object>> it = list.iterator();
        while (it.hasNext()) {
            R((com.bumptech.glide.request.d) it.next());
        }
    }

    private <Y extends com.bumptech.glide.request.h.d<TranscodeType>> Y a0(Y y, com.bumptech.glide.request.d<TranscodeType> dVar, com.bumptech.glide.request.a<?> aVar, Executor executor) {
        j.d(y);
        if (!this.O) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        com.bumptech.glide.request.c cVarT = T(y, dVar, aVar, executor);
        com.bumptech.glide.request.c cVarG = y.g();
        if (cVarT.d(cVarG) && !c0(aVar, cVarG)) {
            if (!((com.bumptech.glide.request.c) j.d(cVarG)).isRunning()) {
                cVarG.h();
            }
            return y;
        }
        this.D.n(y);
        y.j(cVarT);
        this.D.w(y, cVarT);
        return y;
    }

    private boolean c0(com.bumptech.glide.request.a<?> aVar, com.bumptech.glide.request.c cVar) {
        return !aVar.y() && cVar.j();
    }

    private e<TranscodeType> e0(Object obj) {
        this.I = obj;
        this.O = true;
        return this;
    }

    private com.bumptech.glide.request.c f0(Object obj, com.bumptech.glide.request.h.d<TranscodeType> dVar, com.bumptech.glide.request.d<TranscodeType> dVar2, com.bumptech.glide.request.a<?> aVar, RequestCoordinator requestCoordinator, g<?, ? super TranscodeType> gVar, Priority priority, int i, int i2, Executor executor) {
        Context context = this.C;
        d dVar3 = this.G;
        return SingleRequest.x(context, dVar3, obj, this.I, this.E, aVar, i, i2, priority, dVar, dVar2, this.J, requestCoordinator, dVar3.e(), gVar.b(), executor);
    }

    public e<TranscodeType> R(com.bumptech.glide.request.d<TranscodeType> dVar) {
        if (dVar != null) {
            if (this.J == null) {
                this.J = new ArrayList();
            }
            this.J.add(dVar);
        }
        return this;
    }

    @Override // com.bumptech.glide.request.a
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public e<TranscodeType> a(com.bumptech.glide.request.a<?> aVar) {
        j.d(aVar);
        return (e) super.a(aVar);
    }

    @Override // com.bumptech.glide.request.a
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public e<TranscodeType> clone() {
        e<TranscodeType> eVar = (e) super.clone();
        eVar.H = eVar.H.clone();
        return eVar;
    }

    public <Y extends com.bumptech.glide.request.h.d<TranscodeType>> Y Z(Y y) {
        return (Y) b0(y, null, com.bumptech.glide.m.e.b());
    }

    <Y extends com.bumptech.glide.request.h.d<TranscodeType>> Y b0(Y y, com.bumptech.glide.request.d<TranscodeType> dVar, Executor executor) {
        return (Y) a0(y, dVar, this, executor);
    }

    public e<TranscodeType> d0(Object obj) {
        return e0(obj);
    }
}
