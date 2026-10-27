package B5;

import B2.O;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.lifecycle.ProcessLifecycleOwner;
import io.sentry.C1489n1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: B5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0082f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f679a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f680b;
    public M.t c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f681e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public D5.d f682f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m f683g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f685i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public m f687k;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f684h = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f686j = false;
    public final L l = new L();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f688m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f689n = "api.segment.io/v1";

    public C0082f(Context context, String str) {
        if (!D5.g.f(0, context, "android.permission.INTERNET")) {
            throw new IllegalArgumentException("INTERNET permission is required.");
        }
        this.f679a = (Application) context.getApplicationContext();
        if (str.length() == 0 || D5.g.e(str) == 0) {
            throw new IllegalArgumentException("writeKey must not be empty.");
        }
        this.f680b = str;
    }

    public final C0083g a() {
        C0087k c0087k;
        boolean z6 = false;
        int i7 = 2;
        if (D5.g.g(this.d)) {
            this.d = this.f680b;
        }
        ArrayList arrayList = C0083g.f690A;
        synchronized (arrayList) {
            if (arrayList.contains(this.d)) {
                throw new IllegalStateException("Duplicate analytics client created with tag: " + this.d + ". If you want to use multiple Analytics clients, use a different writeKey or set a tag via the builder during construction.");
            }
            arrayList.add(this.d);
        }
        if (this.c == null) {
            this.c = new M.t(2);
        }
        if (this.f681e == 0) {
            this.f681e = 1;
        }
        if (this.f682f == null) {
            this.f682f = new D5.d(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new C1.m(1));
        }
        if (this.f683g == null) {
            this.f683g = new m();
        }
        if (this.f687k == null) {
            this.f687k = new m();
        }
        C1489n1 c1489n1 = new C1489n1(2);
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this.f680b, this.f683g);
        Application application = this.f679a;
        String str = this.d;
        x xVar = new x(application, M5.b.g("project-settings-plan-", str), str, 0);
        O o = new O(D5.g.d(this.f679a, this.d), i7);
        Application application2 = this.f679a;
        String str2 = this.d;
        x xVar2 = new x(application2, M5.b.g("traits-", str2), str2, 1);
        if (!((SharedPreferences) xVar2.f670b).contains((String) xVar2.c) || xVar2.c() == null) {
            xVar2.d(J.i());
        }
        C1.j jVar = new C1.j("Analytics", this.f681e);
        Application application3 = this.f679a;
        J j3 = (J) xVar2.c();
        synchronized (C0087k.class) {
            c0087k = new C0087k(new D5.f());
            c0087k.i(application3);
            j3.getClass();
            c0087k.f(new J(Collections.unmodifiableMap(new LinkedHashMap(j3))), "traits");
            c0087k.j();
            D5.f fVar = new D5.f();
            fVar.put("name", "analytics-android");
            fVar.put("version", "4.11.3");
            c0087k.f(fVar, "library");
            c0087k.f(Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry(), "locale");
            c0087k.k(application3);
            D5.f fVar2 = new D5.f();
            fVar2.put("name", "Android");
            fVar2.put("version", Build.VERSION.RELEASE);
            c0087k.f(fVar2, "os");
            c0087k.l(application3);
            c0087k.f(UUID.randomUUID().toString(), "instanceId");
            C0087k.n(c0087k, "userAgent", System.getProperty("http.agent"));
            C0087k.n(c0087k, "timezone", TimeZone.getDefault().getID());
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Application application4 = this.f679a;
        try {
            Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            new p(c0087k, countDownLatch, jVar).execute(application4);
        } catch (ClassNotFoundException unused) {
            jVar.k("Not collecting advertising ID because com.google.android.gms.ads.identifier.AdvertisingIdClient was not found on the classpath.", new Object[0]);
            countDownLatch.countDown();
        }
        q qVar = new q(c0087k, D5.g.d(this.f679a, this.d), new CountDownLatch(1));
        String string = qVar.c.getString("device.id", null);
        if (string != null) {
            qVar.b(string);
        } else {
            RunnableC0079c runnableC0079c = new RunnableC0079c(qVar, 2);
            ExecutorService executorService = qVar.f730a;
            executorService.execute(new h5.t(3, qVar, executorService.submit(runnableC0079c), z6));
        }
        ArrayList arrayList2 = new ArrayList(this.f684h.size() + 1);
        arrayList2.add(I.f658n);
        arrayList2.addAll(this.f684h);
        ArrayList arrayList3 = this.f685i;
        return new C0083g(this.f679a, this.f682f, c1489n1, xVar2, c0087k, this.c, jVar, this.d, Collections.unmodifiableList(arrayList2), cVar, xVar, this.f680b, Executors.newSingleThreadExecutor(), this.f686j, countDownLatch, o, this.f687k, (arrayList3 == null || arrayList3.size() == 0) ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(arrayList3)), Collections.emptyMap(), this.l, ProcessLifecycleOwner.f11939s.f11943q, this.f688m, this.f689n);
    }
}
