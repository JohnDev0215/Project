package coil;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import java.io.File;
import kotlin.InitializedLazyImpl;
import kotlin.collections.EmptyList;
import okhttp3.OkHttpClient;
import p.b12;
import p.ew;
import p.g;
import p.g12;
import p.ha0;
import p.hi0;
import p.hu0;
import p.i;
import p.j12;
import p.ju0;
import p.li1;
import p.n31;
import p.os1;
import p.p70;
import p.pg2;
import p.py0;
import p.pz;
import p.sz;
import p.u11;
import p.uv2;
import p.v03;
import p.v81;
import p.w90;
import p.wd0;
import p.z02;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final Context a;
    public final p70 b = g.a;
    public InitializedLazyImpl c = null;
    public hi0 d = null;
    public ew e = null;
    public final n31 f = new n31();

    public a(Application application) {
        this.a = application.getApplicationContext();
    }

    public final b a() {
        Context context = this.a;
        p70 p70Var = this.b;
        v81 v81VarC = kotlin.a.c(new py0() { // from class: coil.ImageLoader$Builder$build$1
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                pg2 wd0Var;
                int i;
                int largeMemoryClass;
                Context context2 = this.this$0.a;
                li1 li1Var = new li1(context2);
                uv2 j12Var = li1Var.c ? new j12() : new u11();
                if (li1Var.b) {
                    double d = li1Var.a;
                    if (d > 0.0d) {
                        Bitmap.Config[] configArr = i.a;
                        try {
                            Object obj = sz.a;
                            Object objB = pz.b(context2, ActivityManager.class);
                            v03.h(objB);
                            ActivityManager activityManager = (ActivityManager) objB;
                            largeMemoryClass = (context2.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
                        } catch (Exception unused) {
                            largeMemoryClass = 256;
                        }
                        double d2 = d * ((double) largeMemoryClass);
                        double d3 = 1024;
                        i = (int) (d2 * d3 * d3);
                    } else {
                        i = 0;
                    }
                    wd0Var = i > 0 ? new g12(i, j12Var) : new wd0(j12Var);
                } else {
                    wd0Var = new wd0(j12Var);
                }
                return new b12(wd0Var, j12Var);
            }
        });
        v81 v81VarC2 = kotlin.a.c(new py0() { // from class: coil.ImageLoader$Builder$build$2
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                z02 z02VarA;
                u11 u11Var = u11.s;
                Context context2 = this.this$0.a;
                synchronized (u11Var) {
                    z02VarA = u11.t;
                    if (z02VarA == null) {
                        w90 w90Var = new w90();
                        w90Var.b = hu0.a;
                        w90Var.c = 0.02d;
                        w90Var.d = 10485760L;
                        w90Var.e = 262144000L;
                        w90Var.f = ha0.c;
                        File fileZ = ju0.z(i.d(context2));
                        String str = os1.e;
                        w90Var.a = u11.A(fileZ);
                        z02VarA = w90Var.a();
                        u11.t = z02VarA;
                    }
                }
                return z02VarA;
            }
        });
        v81 v81VarC3 = this.c;
        if (v81VarC3 == null) {
            v81VarC3 = kotlin.a.c(new py0() { // from class: coil.ImageLoader$Builder$build$3
                @Override // p.py0
                public final Object invoke() {
                    return new OkHttpClient();
                }
            });
        }
        v81 v81Var = v81VarC3;
        hi0 hi0Var = this.d;
        if (hi0Var == null) {
            hi0Var = hi0.f;
        }
        hi0 hi0Var2 = hi0Var;
        ew ewVar = this.e;
        if (ewVar == null) {
            EmptyList emptyList = EmptyList.c;
            ewVar = new ew(emptyList, emptyList, emptyList, emptyList, emptyList);
        }
        return new b(context, p70Var, v81VarC, v81VarC2, v81Var, hi0Var2, ewVar, this.f);
    }
}
