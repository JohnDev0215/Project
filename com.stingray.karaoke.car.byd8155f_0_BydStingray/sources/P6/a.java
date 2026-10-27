package P6;

import H0.b;
import S0.G;
import T1.h;
import T1.k;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import e2.C1208p;
import e2.L;
import g1.AbstractC1274a;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h8.AbstractC1386c;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import k4.C1684b;
import k4.m;
import k4.q;
import k4.r;
import k4.w;
import l5.C1778f;
import l9.l;
import n0.C1886o;
import s2.j;
import x1.AbstractC2671j;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class a implements h, r, j, A4.j {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f5412m;

    public /* synthetic */ a(Context context, int i7, boolean z6) {
        this.l = i7;
        this.f5412m = context;
    }

    @Override // k4.r
    public q A(w wVar) {
        switch (this.l) {
            case 4:
                return new C1684b(this.f5412m, this);
            case 5:
                return new C1684b(this.f5412m, this);
            case 6:
                return new C1684b(this.f5412m, this);
            case 7:
                return new m(this.f5412m, 0);
            case 8:
                return new C1684b(this.f5412m, wVar.b(Integer.class, AssetFileDescriptor.class));
            case 9:
                return new C1684b(this.f5412m, wVar.b(Integer.class, InputStream.class));
            case 10:
                return new m(this.f5412m, 1);
            default:
                return new m(this.f5412m, 2);
        }
    }

    @Override // T1.h
    public void a(AbstractC1274a abstractC1274a) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new T1.a("EmojiCompatInitializer", 0));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new k(this, abstractC1274a, threadPoolExecutor, 0));
    }

    @Override // s2.j
    public s2.k b(b bVar) {
        Context context;
        int i7 = AbstractC1343A.f15195a;
        if (i7 < 23 || (i7 < 31 && ((context = this.f5412m) == null || i7 < 28 || !context.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen")))) {
            return new C1886o(9).b(bVar);
        }
        int iG = L.g(((C1208p) bVar.o).f14316n);
        AbstractC1346c.t("DMCodecAdapterFactory", "Creating an asynchronous MediaCodec adapter for track type " + AbstractC1343A.z(iG));
        return new C1778f(iG).b(bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object c(S0.G r13, s7.AbstractC2410c r14) throws java.lang.Throwable {
        /*
            r12 = this;
            boolean r0 = r14 instanceof S0.C0689a
            if (r0 == 0) goto L13
            r0 = r14
            S0.a r0 = (S0.C0689a) r0
            int r1 = r0.f7820p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7820p = r1
            goto L18
        L13:
            S0.a r0 = new S0.a
            r0.<init>(r12, r14)
        L18:
            java.lang.Object r14 = r0.f7819n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f7820p
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            S0.G r13 = r0.f7818m
            P6.a r12 = r0.l
            h8.AbstractC1386c.u(r14)
            goto L7b
        L2e:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L36:
            h8.AbstractC1386c.u(r14)
            return r14
        L3a:
            h8.AbstractC1386c.u(r14)
            boolean r14 = r13 instanceof S0.G
            if (r14 == 0) goto L86
            r0.l = r12
            r0.f7818m = r13
            r0.f7820p = r3
            S8.i r14 = new S8.i
            q7.c r0 = h6.e.k(r0)
            r14.<init>(r4, r0)
            r14.s()
            int r6 = r13.f7808a
            S0.b r9 = new S0.b
            r9.<init>(r14, r13)
            java.lang.ThreadLocal r0 = x1.AbstractC2671j.f23708a
            android.content.Context r5 = r12.f5412m
            boolean r0 = r5.isRestricted()
            if (r0 == 0) goto L69
            r0 = -4
            r9.a(r0)
            goto L74
        L69:
            android.util.TypedValue r7 = new android.util.TypedValue
            r7.<init>()
            r8 = 0
            r10 = 0
            r11 = 0
            x1.AbstractC2671j.b(r5, r6, r7, r8, r9, r10, r11)
        L74:
            java.lang.Object r14 = r14.q()
            if (r14 != r1) goto L7b
            return r1
        L7b:
            android.graphics.Typeface r14 = (android.graphics.Typeface) r14
            S0.z r13 = r13.d
            android.content.Context r12 = r12.f5412m
            android.graphics.Typeface r12 = x3.AbstractC2673a.V(r14, r13, r12)
            return r12
        L86:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "Unknown font type: "
            r14.<init>(r0)
            r14.append(r13)
            java.lang.String r13 = r14.toString()
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: P6.a.c(S0.G, s7.c):java.lang.Object");
    }

    public Typeface d(G g7) {
        Object objF;
        Typeface typefaceA;
        if (!(g7 instanceof G)) {
            return null;
        }
        int i7 = g7.f7810e;
        boolean zJ = l.J(i7, 0);
        Context context = this.f5412m;
        if (zJ) {
            typefaceA = AbstractC2671j.a(context, g7.f7808a);
            A7.m.c(typefaceA);
        } else {
            if (!l.J(i7, 1)) {
                if (l.J(i7, 2)) {
                    throw new UnsupportedOperationException("Unsupported Async font load path");
                }
                throw new IllegalArgumentException("Unknown loading type " + ((Object) l.Z(g7.f7810e)));
            }
            try {
                objF = AbstractC2671j.a(context, g7.f7808a);
                A7.m.c(objF);
            } catch (Throwable th) {
                objF = AbstractC1386c.f(th);
            }
            typefaceA = (Typeface) (objF instanceof m7.k ? null : objF);
        }
        return AbstractC2673a.V(typefaceA, g7.d, context);
    }

    @Override // A4.j
    public Object get() {
        return (ConnectivityManager) this.f5412m.getSystemService("connectivity");
    }

    public a(Context context, int i7) {
        this.l = i7;
        switch (i7) {
            case 2:
                this.f5412m = context.getApplicationContext();
                break;
            default:
                this.f5412m = context.getApplicationContext();
                break;
        }
    }
}
