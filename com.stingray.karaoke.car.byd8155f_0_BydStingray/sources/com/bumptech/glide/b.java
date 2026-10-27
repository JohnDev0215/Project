package com.bumptech.glide;

import A4.q;
import B2.C0064d;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.text.TextUtils;
import android.util.Log;
import h4.C1364f;
import h4.InterfaceC1359a;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import j4.ThreadFactoryC1541b;
import j4.ThreadFactoryC1542c;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import l9.n;
import n4.v;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ComponentCallbacks2 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static volatile b f12664s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static volatile boolean f12665t;
    public final InterfaceC1359a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i4.e f12666m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e f12667n;
    public final F.l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final t4.c f12668p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final n f12669q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f12670r = new ArrayList();

    public b(Context context, g4.l lVar, i4.e eVar, InterfaceC1359a interfaceC1359a, F.l lVar2, t4.c cVar, n nVar, C8.d dVar, C2465e c2465e, List list, ArrayList arrayList, AbstractC1386c abstractC1386c, S6.e eVar2) {
        this.l = interfaceC1359a;
        this.o = lVar2;
        this.f12666m = eVar;
        this.f12668p = cVar;
        this.f12669q = nVar;
        this.f12667n = new e(context, lVar2, new t4.e(this, arrayList, abstractC1386c), new m6.b(), dVar, c2465e, list, lVar, eVar2);
    }

    public static b a(Context context) {
        GeneratedAppGlideModule generatedAppGlideModule;
        if (f12664s == null) {
            try {
                generatedAppGlideModule = (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(Context.class).newInstance(context.getApplicationContext().getApplicationContext());
            } catch (ClassNotFoundException unused) {
                if (Log.isLoggable("Glide", 5)) {
                    h6.n.t("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
                }
                generatedAppGlideModule = null;
            } catch (IllegalAccessException e10) {
                throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e10);
            } catch (InstantiationException e11) {
                throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e11);
            } catch (NoSuchMethodException e12) {
                throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e12);
            } catch (InvocationTargetException e13) {
                throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e13);
            }
            synchronized (b.class) {
                if (f12664s == null) {
                    if (f12665t) {
                        throw new IllegalStateException("Glide has been called recursively, this is probably an internal library error!");
                    }
                    f12665t = true;
                    try {
                        b(context, generatedAppGlideModule);
                        f12665t = false;
                    } catch (Throwable th) {
                        f12665t = false;
                        throw th;
                    }
                }
            }
        }
        return f12664s;
    }

    public static void b(Context context, GeneratedAppGlideModule generatedAppGlideModule) {
        S6.c cVar;
        InterfaceC1359a eVar;
        C2465e c2465e = new C2465e(0);
        S6.c cVar2 = new S6.c(3);
        C8.d dVar = new C8.d(18);
        Context applicationContext = context.getApplicationContext();
        Collections.emptyList();
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                if (Log.isLoggable("ManifestParser", 2)) {
                    Log.v("ManifestParser", "Got app info metadata: " + applicationInfo.metaData);
                }
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        AbstractC1387d.n(str);
                        throw null;
                    }
                }
                if (Log.isLoggable("ManifestParser", 3)) {
                    Log.d("ManifestParser", "Finished loading Glide modules");
                }
            } else if (Log.isLoggable("ManifestParser", 3)) {
                Log.d("ManifestParser", "Got null app info metadata");
            }
        } catch (PackageManager.NameNotFoundException e10) {
            if (Log.isLoggable("ManifestParser", 6)) {
                h6.n.i("ManifestParser", "Failed to parse glide modules", e10);
            }
        }
        if (generatedAppGlideModule != null && !new HashSet().isEmpty()) {
            new HashSet();
            Iterator it = arrayList.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator it2 = arrayList.iterator();
            if (it2.hasNext()) {
                it2.next().getClass();
                throw new ClassCastException();
            }
        }
        Iterator it3 = arrayList.iterator();
        if (it3.hasNext()) {
            it3.next().getClass();
            throw new ClassCastException();
        }
        ThreadFactoryC1541b threadFactoryC1541b = new ThreadFactoryC1541b();
        if (j4.e.f17013n == 0) {
            j4.e.f17013n = Math.min(4, Runtime.getRuntime().availableProcessors());
        }
        int i7 = j4.e.f17013n;
        if (TextUtils.isEmpty(BYDAutoMultimediaDevice.PARAM_SOURCE)) {
            throw new IllegalArgumentException("Name must be non-null and non-empty, but given: source");
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        j4.e eVar2 = new j4.e(new ThreadPoolExecutor(i7, i7, 0L, timeUnit, new PriorityBlockingQueue(), new ThreadFactoryC1542c(threadFactoryC1541b, BYDAutoMultimediaDevice.PARAM_SOURCE, false)));
        ThreadFactoryC1541b threadFactoryC1541b2 = new ThreadFactoryC1541b();
        if (TextUtils.isEmpty("disk-cache")) {
            throw new IllegalArgumentException("Name must be non-null and non-empty, but given: disk-cache");
        }
        j4.e eVar3 = new j4.e(new ThreadPoolExecutor(1, 1, 0L, timeUnit, new PriorityBlockingQueue(), new ThreadFactoryC1542c(threadFactoryC1541b2, "disk-cache", true)));
        if (j4.e.f17013n == 0) {
            j4.e.f17013n = Math.min(4, Runtime.getRuntime().availableProcessors());
        }
        int i10 = j4.e.f17013n >= 4 ? 2 : 1;
        ThreadFactoryC1541b threadFactoryC1541b3 = new ThreadFactoryC1541b();
        if (TextUtils.isEmpty("animation")) {
            throw new IllegalArgumentException("Name must be non-null and non-empty, but given: animation");
        }
        j4.e eVar4 = new j4.e(new ThreadPoolExecutor(i10, i10, 0L, timeUnit, new PriorityBlockingQueue(), new ThreadFactoryC1542c(threadFactoryC1541b3, "animation", true)));
        C0064d c0064d = new C0064d(new R3.c(applicationContext));
        n nVar = new n(11);
        int i11 = c0064d.f556b;
        if (i11 > 0) {
            cVar = cVar2;
            eVar = new C1364f(i11);
        } else {
            cVar = cVar2;
            eVar = new Y4.e(22);
        }
        F.l lVar = new F.l(c0064d.d);
        i4.e eVar5 = new i4.e(c0064d.c);
        g4.l lVar2 = new g4.l(eVar5, new e7.f(applicationContext), eVar3, eVar2, new j4.e(new ThreadPoolExecutor(0, Integer.MAX_VALUE, j4.e.f17012m, timeUnit, new SynchronousQueue(), new ThreadFactoryC1542c(new ThreadFactoryC1541b(), "source-unlimited", false))), eVar4);
        List listEmptyList = Collections.emptyList();
        S6.e eVar6 = new S6.e(cVar);
        t4.c cVar3 = new t4.c();
        new HashMap();
        File file = v.d;
        b bVar = new b(applicationContext, lVar2, eVar5, eVar, lVar, cVar3, nVar, dVar, c2465e, listEmptyList, arrayList, generatedAppGlideModule, eVar6);
        applicationContext.registerComponentCallbacks(bVar);
        f12664s = bVar;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        q.a();
        this.f12666m.f(0L);
        this.l.z();
        F.l lVar = this.o;
        synchronized (lVar) {
            lVar.c(0);
        }
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i7) {
        long j3;
        q.a();
        synchronized (this.f12670r) {
            try {
                Iterator it = this.f12670r.iterator();
                while (it.hasNext()) {
                    ((l) it.next()).getClass();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        i4.e eVar = this.f12666m;
        eVar.getClass();
        if (i7 >= 40) {
            eVar.f(0L);
        } else if (i7 >= 20 || i7 == 15) {
            synchronized (eVar) {
                j3 = eVar.f426a;
            }
            eVar.f(j3 / 2);
        }
        this.l.b(i7);
        F.l lVar = this.o;
        synchronized (lVar) {
            if (i7 >= 40) {
                synchronized (lVar) {
                    lVar.c(0);
                }
            } else if (i7 >= 20 || i7 == 15) {
                lVar.c(lVar.f1902a / 2);
            }
        }
    }
}
