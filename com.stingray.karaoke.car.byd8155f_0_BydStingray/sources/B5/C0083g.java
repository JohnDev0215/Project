package B5;

import B2.O;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Pair;
import io.sentry.C1489n1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: B5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0083g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D5.d f694b;
    public final C1489n1 c;
    public final List d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f695e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final M.t f696f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final x f697g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0087k f698h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C1.j f699i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f700j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f701k;
    public final m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final x f702m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final m f703n;
    public final C0086j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public y f704p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f705q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f706r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final long f707s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final CountDownLatch f708t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ExecutorService f709u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final O f710v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ConcurrentHashMap f711w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f712x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public LinkedHashMap f713y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final HandlerC0078b f692z = new HandlerC0078b(Looper.getMainLooper());

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final ArrayList f690A = new ArrayList(1);
    public static volatile C0083g B = null;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final z f691C = new z();

    public C0083g(Application application, D5.d dVar, C1489n1 c1489n1, x xVar, C0087k c0087k, M.t tVar, C1.j jVar, String str, List list, io.sentry.internal.debugmeta.c cVar, x xVar2, String str2, ExecutorService executorService, boolean z6, CountDownLatch countDownLatch, O o, m mVar, List list2, Map map, L l, A9.b bVar, boolean z10, String str3) {
        m mVar2 = m.f725a;
        this.f711w = new ConcurrentHashMap();
        this.f693a = application;
        this.f694b = dVar;
        this.c = c1489n1;
        this.f697g = xVar;
        this.f698h = c0087k;
        this.f696f = tVar;
        this.f699i = jVar;
        this.f700j = str;
        this.f701k = cVar;
        this.l = mVar2;
        this.f702m = xVar2;
        this.f705q = str2;
        this.f706r = 20;
        this.f707s = 30000L;
        this.f708t = countDownLatch;
        this.f710v = o;
        this.f712x = list;
        this.f709u = executorService;
        this.f703n = mVar;
        this.d = list2;
        this.f695e = map;
        SharedPreferences sharedPreferencesD = D5.g.d(application, str);
        if (sharedPreferencesD.getBoolean("namespaceSharedPreferences", true)) {
            SharedPreferences sharedPreferences = application.getSharedPreferences("analytics-android", 0);
            SharedPreferences.Editor editorEdit = sharedPreferencesD.edit();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    editorEdit.putString(key, (String) value);
                } else if (value instanceof Set) {
                    editorEdit.putStringSet(key, (Set) value);
                } else if (value instanceof Integer) {
                    editorEdit.putInt(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    editorEdit.putLong(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    editorEdit.putFloat(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
                }
            }
            editorEdit.apply();
            sharedPreferencesD.edit().putBoolean("namespaceSharedPreferences", false).apply();
        }
        executorService.submit(new RunnableC0080d(0, this, l, str3));
        jVar.k("Created analytics client for project with tag:%s.", str);
        Boolean boolValueOf = Boolean.valueOf(z6);
        Boolean bool = Boolean.FALSE;
        PackageInfo packageInfoC = c(application);
        Boolean boolValueOf2 = Boolean.valueOf(z10);
        C0086j c0086j = new C0086j();
        c0086j.f719r = new AtomicBoolean(false);
        c0086j.f720s = new AtomicInteger(1);
        c0086j.f721t = new AtomicBoolean(false);
        c0086j.l = this;
        c0086j.f715m = executorService;
        c0086j.f716n = boolValueOf;
        c0086j.o = bool;
        c0086j.f717p = bool;
        c0086j.f718q = packageInfoC;
        c0086j.f723v = boolValueOf2;
        c0086j.f722u = new AtomicBoolean(false);
        this.o = c0086j;
        application.registerActivityLifecycleCallbacks(c0086j);
        if (z10) {
            A2.m mVar3 = new A2.m(this, 1, bVar);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                mVar3.run();
            } else {
                f692z.post(mVar3);
            }
        }
    }

    public static PackageInfo c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new AssertionError("Package not found: " + context.getPackageName());
        }
    }

    public static C0083g h(Context context) {
        if (B == null) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            synchronized (C0083g.class) {
                if (B == null) {
                    int identifier = context.getResources().getIdentifier("analytics_write_key", "string", context.getPackageName());
                    C0082f c0082f = new C0082f(context, identifier != 0 ? context.getResources().getString(identifier) : null);
                    try {
                        if ((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0) {
                            c0082f.f681e = 2;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                    B = c0082f.a();
                }
            }
        }
        return B;
    }

    public final y a() {
        C1.j jVar = this.f699i;
        try {
            y yVar = (y) this.f694b.submit(new CallableC0077a(this, 0)).get();
            this.f702m.d(yVar);
            return yVar;
        } catch (InterruptedException e10) {
            jVar.l(e10, "Thread interrupted while fetching settings.", new Object[0]);
            return null;
        } catch (ExecutionException e11) {
            jVar.l(e11, "Unable to fetch settings. Retrying in %s ms.", 60000L);
            return null;
        }
    }

    public final void b(C5.a aVar) {
        C1.j jVar = this.f699i;
        CountDownLatch countDownLatch = this.f708t;
        try {
            countDownLatch.await(15L, TimeUnit.SECONDS);
        } catch (InterruptedException e10) {
            jVar.l(e10, "Thread interrupted while waiting for advertising ID.", new Object[0]);
        }
        if (countDownLatch.getCount() == 1) {
            jVar.k("Advertising ID may not be collected because the API did not respond within 15 seconds.", new Object[0]);
        }
        C0087k c0087k = this.f698h;
        C0087k c0087k2 = new C0087k(new LinkedHashMap(c0087k.l.size()));
        c0087k2.putAll(c0087k);
        M.t tVar = this.f696f;
        tVar.getClass();
        c0087k2.putAll(new LinkedHashMap((ConcurrentHashMap) tVar.f4151m));
        C0087k c0087k3 = new C0087k(Collections.unmodifiableMap(new LinkedHashMap(c0087k2)));
        aVar.c = Collections.unmodifiableMap(new LinkedHashMap(c0087k3));
        String strC = ((J) c0087k3.d(J.class, "traits")).c("anonymousId");
        D5.g.b(strC, "anonymousId");
        aVar.f1000f = strC;
        LinkedHashMap linkedHashMap = new LinkedHashMap((ConcurrentHashMap) tVar.l);
        if (!D5.g.h(linkedHashMap)) {
            if (aVar.d == null) {
                aVar.d = new LinkedHashMap();
            }
            aVar.d.putAll(linkedHashMap);
        }
        aVar.f1001g = false;
        String strC2 = ((J) c0087k3.d(J.class, "traits")).c("userId");
        if (D5.g.g(aVar.f999e) && !D5.g.g(strC2)) {
            D5.g.b(strC2, "userId");
            aVar.f999e = strC2;
        }
        C5.d dVarA = aVar.a();
        if (((SharedPreferences) this.f710v.f527m).getBoolean("opt-out", false)) {
            return;
        }
        jVar.t("Created payload %s.", dVarA);
        new u(0, dVarA, this.d, new O(this, 1)).r(dVarA);
    }

    public final void d(H7.H h10) {
        for (Map.Entry entry : this.f713y.entrySet()) {
            String str = (String) entry.getKey();
            long jNanoTime = System.nanoTime();
            h10.N(str, (C5.g) entry.getValue(), this.f704p);
            long jNanoTime2 = System.nanoTime() - jNanoTime;
            long millis = TimeUnit.NANOSECONDS.toMillis(jNanoTime2);
            C1489n1 c1489n1 = this.c;
            c1489n1.getClass();
            Pair pair = new Pair(str, Long.valueOf(millis));
            H h11 = (H) c1489n1.f16624m;
            h11.sendMessage(h11.obtainMessage(2, pair));
            this.f699i.k("Ran %s on integration %s in %d ns.", h10, str, Long.valueOf(jNanoTime2));
        }
    }

    public final void e(H7.H h10) {
        this.f709u.submit(new h5.t(2, this, h10, false));
    }

    public final void f(String str, z zVar) {
        if (D5.g.g(str)) {
            throw new IllegalArgumentException("either category or name must be provided.");
        }
        this.f709u.submit(new RunnableC0081e(this, zVar, new Date(), str, 1));
    }

    public final void g(String str, z zVar) {
        if (D5.g.g(str)) {
            throw new IllegalArgumentException("event must not be null or empty.");
        }
        this.f709u.submit(new RunnableC0081e(this, zVar, new Date(), str, 0));
    }
}
