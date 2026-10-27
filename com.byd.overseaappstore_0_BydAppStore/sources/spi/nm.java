package spi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestCoordinator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class nm<TranscodeType> extends i1<nm<TranscodeType>> {
    public final Context A;
    public final qm B;
    public final Class<TranscodeType> C;
    public final com.bumptech.glide.a D;
    public final com.bumptech.glide.c E;

    @NonNull
    public xt<?, ? super TranscodeType> F;

    @Nullable
    public Object G;

    @Nullable
    public List<pm<TranscodeType>> H;

    @Nullable
    public nm<TranscodeType> I;

    @Nullable
    public nm<TranscodeType> J;
    public boolean K;
    public boolean L;
    public boolean M;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Priority.values().length];
            b = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static {
    }

    @SuppressLint({"CheckResult"})
    public nm(@NonNull com.bumptech.glide.a aVar, qm qmVar, Class<TranscodeType> cls, Context context) {
        tm tmVar;
        this.K = true;
        this.D = aVar;
        this.B = qmVar;
        this.C = cls;
        this.A = context;
        com.bumptech.glide.c cVar = qmVar.a.c;
        xt xtVar = cVar.f.get(cls);
        if (xtVar == null) {
            for (Map.Entry<Class<?>, xt<?, ?>> entry : cVar.f.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    xtVar = (xt) entry.getValue();
                }
            }
        }
        this.F = xtVar == null ? com.bumptech.glide.c.k : xtVar;
        this.E = aVar.c;
        Iterator<pm<Object>> it = qmVar.i.iterator();
        while (it.hasNext()) {
            A((pm) it.next());
        }
        synchronized (qmVar) {
            tmVar = qmVar.j;
        }
        a(tmVar);
    }

    @SuppressLint({"CheckResult"})
    public nm(Class<TranscodeType> cls, nm<?> nmVar) {
        this(nmVar.D, nmVar.B, cls, nmVar.A);
        this.G = nmVar.G;
        this.L = nmVar.L;
        a(nmVar);
    }

    @NonNull
    @CheckResult
    public nm<TranscodeType> A(@Nullable pm<TranscodeType> pmVar) {
        if (this.v) {
            return clone().A(pmVar);
        }
        if (pmVar != null) {
            if (this.H == null) {
                this.H = new ArrayList();
            }
            this.H.add(pmVar);
        }
        r();
        return this;
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public nm<TranscodeType> a(@NonNull i1<?> i1Var) {
        Objects.requireNonNull(i1Var, "Argument must not be null");
        return (nm) super.a(i1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.jm C(java.lang.Object r19, spi.rs r20, @androidx.annotation.Nullable com.bumptech.glide.request.RequestCoordinator r21, spi.xt r22, com.bumptech.glide.Priority r23, int r24, int r25, spi.i1 r26, java.util.concurrent.Executor r27) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.nm.C(java.lang.Object, spi.rs, com.bumptech.glide.request.RequestCoordinator, spi.xt, com.bumptech.glide.Priority, int, int, spi.i1, java.util.concurrent.Executor):spi.jm");
    }

    @Override // spi.i1
    @CheckResult
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public nm<TranscodeType> clone() {
        nm<TranscodeType> nmVar = (nm) super.clone();
        nmVar.F = nmVar.F.a();
        if (nmVar.H != null) {
            nmVar.H = new ArrayList(nmVar.H);
        }
        nm<TranscodeType> nmVar2 = nmVar.I;
        if (nmVar2 != null) {
            nmVar.I = nmVar2.clone();
        }
        nm<TranscodeType> nmVar3 = nmVar.J;
        if (nmVar3 != null) {
            nmVar.J = nmVar3.clone();
        }
        return nmVar;
    }

    @NonNull
    public final Priority E(@NonNull Priority priority) {
        int i = a.b[priority.ordinal()];
        if (i == 1) {
            return Priority.NORMAL;
        }
        if (i == 2) {
            return Priority.HIGH;
        }
        if (i == 3 || i == 4) {
            return Priority.IMMEDIATE;
        }
        StringBuilder sbK = e.k("unknown priority: ");
        sbK.append(this.d);
        throw new IllegalArgumentException(sbK.toString());
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.HashSet, java.util.Set<spi.jm>] */
    public final rs F(@NonNull rs rsVar, i1 i1Var, Executor executor) {
        Objects.requireNonNull(rsVar, "Argument must not be null");
        if (!this.L) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        jm jmVarC = C(new Object(), rsVar, null, this.F, i1Var.d, i1Var.k, i1Var.j, i1Var, executor);
        jm jmVarH = rsVar.h();
        if (jmVarC.c(jmVarH)) {
            if (!(!i1Var.i && jmVarH.j())) {
                Objects.requireNonNull(jmVarH, "Argument must not be null");
                if (!jmVarH.isRunning()) {
                    jmVarH.i();
                }
                return rsVar;
            }
        }
        this.B.m(rsVar);
        rsVar.e(jmVarC);
        qm qmVar = this.B;
        synchronized (qmVar) {
            qmVar.f.a.add(rsVar);
            um umVar = qmVar.d;
            umVar.a.add(jmVarC);
            if (umVar.c) {
                jmVarC.clear();
                if (Log.isLoggable("RequestTracker", 2)) {
                    Log.v("RequestTracker", "Paused, delaying request");
                }
                umVar.b.add(jmVarC);
            } else {
                jmVarC.i();
            }
        }
        return rsVar;
    }

    @NonNull
    public final jv<ImageView, TranscodeType> G(@NonNull ImageView imageView) {
        i1 i1VarK;
        jv<ImageView, TranscodeType> t7Var;
        tu.a();
        Objects.requireNonNull(imageView, "Argument must not be null");
        if (!i1.i(this.a, 2048) && this.n && imageView.getScaleType() != null) {
            switch (a.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    i1VarK = clone().k();
                    break;
                case 2:
                case 6:
                    i1VarK = clone().l();
                    break;
                case 3:
                case 4:
                case 5:
                    i1VarK = clone().m();
                    break;
                default:
                    i1VarK = this;
                    break;
            }
        } else {
            i1VarK = this;
        }
        com.bumptech.glide.c cVar = this.E;
        Class<TranscodeType> cls = this.C;
        Objects.requireNonNull(cVar.c);
        if (Bitmap.class.equals(cls)) {
            t7Var = new v1(imageView);
        } else {
            if (!Drawable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Unhandled class: " + cls + ", try .as*(Class).transcode(ResourceTranscoder)");
            }
            t7Var = new t7(imageView);
        }
        F(t7Var, i1VarK, pa.a);
        return t7Var;
    }

    @NonNull
    @CheckResult
    public nm<TranscodeType> H(@Nullable Drawable drawable) {
        return K(drawable).a(tm.A(i6.a));
    }

    @NonNull
    @CheckResult
    public nm<TranscodeType> I(@Nullable Object obj) {
        return K(obj);
    }

    @NonNull
    @CheckResult
    public nm<TranscodeType> J(@Nullable String str) {
        return K(str);
    }

    @NonNull
    public final nm<TranscodeType> K(@Nullable Object obj) {
        if (this.v) {
            return clone().K(obj);
        }
        this.G = obj;
        this.L = true;
        r();
        return this;
    }

    public final jm L(Object obj, rs rsVar, i1 i1Var, RequestCoordinator requestCoordinator, xt xtVar, Priority priority, int i, int i2, Executor executor) {
        Context context = this.A;
        com.bumptech.glide.c cVar = this.E;
        return np.n(context, cVar, obj, this.G, this.C, i1Var, i, i2, priority, rsVar, this.H, requestCoordinator, cVar.g, xtVar.a, executor);
    }
}
