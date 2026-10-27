package a.h.h;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a.e.f<String, Typeface> f744a = new a.e.f<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ExecutorService f745b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f746c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a.e.h<String, ArrayList<a.h.j.a<e>>> f747d;

    public class a implements Callable<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f748a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f749b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ a.h.h.e f750c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f751d;

        public a(String str, Context context, a.h.h.e eVar, int i) {
            this.f748a = str;
            this.f749b = context;
            this.f750c = eVar;
            this.f751d = i;
        }

        @Override // java.util.concurrent.Callable
        public e call() {
            return f.a(this.f748a, this.f749b, this.f750c, this.f751d);
        }
    }

    public class b implements a.h.j.a<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a.h.h.c f752a;

        public b(a.h.h.c cVar) {
            this.f752a = cVar;
        }

        @Override // a.h.j.a
        public void accept(e eVar) {
            this.f752a.a(eVar);
        }
    }

    public class c implements Callable<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f753a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f754b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ a.h.h.e f755c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f756d;

        public c(String str, Context context, a.h.h.e eVar, int i) {
            this.f753a = str;
            this.f754b = context;
            this.f755c = eVar;
            this.f756d = i;
        }

        @Override // java.util.concurrent.Callable
        public e call() {
            return f.a(this.f753a, this.f754b, this.f755c, this.f756d);
        }
    }

    public class d implements a.h.j.a<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f757a;

        public d(String str) {
            this.f757a = str;
        }

        @Override // a.h.j.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(e eVar) {
            synchronized (f.f746c) {
                ArrayList<a.h.j.a<e>> arrayList = f.f747d.get(this.f757a);
                if (arrayList == null) {
                    return;
                }
                f.f747d.remove(this.f757a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).accept(eVar);
                }
            }
        }
    }

    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Typeface f758a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f759b;

        public e(int i) {
            this.f758a = null;
            this.f759b = i;
        }

        @SuppressLint({"WrongConstant"})
        public e(Typeface typeface) {
            this.f758a = typeface;
            this.f759b = 0;
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new j("fonts-androidx", 10));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f745b = threadPoolExecutor;
        f746c = new Object();
        f747d = new a.e.h<>();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static a.h.h.f.e a(java.lang.String r7, android.content.Context r8, a.h.h.e r9, int r10) {
        /*
            a.e.f<java.lang.String, android.graphics.Typeface> r0 = a.h.h.f.f744a
            java.lang.Object r0 = r0.a(r7)
            android.graphics.Typeface r0 = (android.graphics.Typeface) r0
            if (r0 == 0) goto L10
            a.h.h.f$e r7 = new a.h.h.f$e
            r7.<init>(r0)
            return r7
        L10:
            r0 = 0
            a.h.h.g r9 = a.h.h.d.a(r8, r9, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L61
            int r1 = r9.f760a
            r2 = -3
            r3 = 1
            if (r1 == 0) goto L20
            if (r1 == r3) goto L1e
            goto L35
        L1e:
            r1 = -2
            goto L3e
        L20:
            a.h.h.h[] r1 = r9.f761b
            if (r1 == 0) goto L3d
            int r4 = r1.length
            if (r4 != 0) goto L28
            goto L3d
        L28:
            int r3 = r1.length
            r4 = 0
            r5 = r4
        L2b:
            if (r5 >= r3) goto L3c
            r6 = r1[r5]
            int r6 = r6.f766e
            if (r6 == 0) goto L39
            if (r6 >= 0) goto L37
        L35:
            r1 = r2
            goto L3e
        L37:
            r1 = r6
            goto L3e
        L39:
            int r5 = r5 + 1
            goto L2b
        L3c:
            r3 = r4
        L3d:
            r1 = r3
        L3e:
            if (r1 == 0) goto L46
            a.h.h.f$e r7 = new a.h.h.f$e
            r7.<init>(r1)
            return r7
        L46:
            a.h.h.h[] r9 = r9.f761b
            a.h.e.e r1 = a.h.e.c.f724a
            android.graphics.Typeface r8 = r1.b(r8, r0, r9, r10)
            if (r8 == 0) goto L5b
            a.e.f<java.lang.String, android.graphics.Typeface> r9 = a.h.h.f.f744a
            r9.b(r7, r8)
            a.h.h.f$e r7 = new a.h.h.f$e
            r7.<init>(r8)
            return r7
        L5b:
            a.h.h.f$e r7 = new a.h.h.f$e
            r7.<init>(r2)
            return r7
        L61:
            a.h.h.f$e r7 = new a.h.h.f$e
            r8 = -1
            r7.<init>(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.h.f.a(java.lang.String, android.content.Context, a.h.h.e, int):a.h.h.f$e");
    }

    public static Typeface b(Context context, a.h.h.e eVar, int i, Executor executor, a.h.h.c cVar) {
        String str = eVar.f743f + "-" + i;
        Typeface typefaceA = f744a.a(str);
        if (typefaceA != null) {
            cVar.f736b.post(new a.h.h.a(cVar, cVar.f735a, typefaceA));
            return typefaceA;
        }
        b bVar = new b(cVar);
        synchronized (f746c) {
            ArrayList<a.h.j.a<e>> orDefault = f747d.getOrDefault(str, null);
            if (orDefault != null) {
                orDefault.add(bVar);
                return null;
            }
            ArrayList<a.h.j.a<e>> arrayList = new ArrayList<>();
            arrayList.add(bVar);
            f747d.put(str, arrayList);
            f745b.execute(new k(Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler(), new c(str, context, eVar, i), new d(str)));
            return null;
        }
    }

    public static Typeface c(Context context, a.h.h.e eVar, a.h.h.c cVar, int i, int i2) {
        String str = eVar.f743f + "-" + i;
        Typeface typefaceA = f744a.a(str);
        if (typefaceA != null) {
            cVar.f736b.post(new a.h.h.a(cVar, cVar.f735a, typefaceA));
            return typefaceA;
        }
        if (i2 == -1) {
            e eVarA = a(str, context, eVar, i);
            cVar.a(eVarA);
            return eVarA.f758a;
        }
        try {
            try {
                try {
                    try {
                        e eVar2 = (e) f745b.submit(new a(str, context, eVar, i)).get(i2, TimeUnit.MILLISECONDS);
                        cVar.a(eVar2);
                        return eVar2.f758a;
                    } catch (ExecutionException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (InterruptedException e3) {
                    throw e3;
                }
            } catch (TimeoutException unused) {
                throw new InterruptedException("timeout");
            }
        } catch (InterruptedException unused2) {
            cVar.f736b.post(new a.h.h.b(cVar, cVar.f735a, -3));
            return null;
        }
    }
}
