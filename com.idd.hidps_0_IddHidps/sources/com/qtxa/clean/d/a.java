package com.qtxa.clean.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import com.qtxa.qtxadefend.w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String i = "auto";
    public static String j = "manual";
    private static a k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ExecutorService f26a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PackageManager f27b;
    private Boolean c = Boolean.FALSE;
    private int d;
    private int e;
    private List<ApplicationInfo> f;
    private List<com.qtxa.clean.a> g;
    private boolean h;

    /* JADX INFO: renamed from: com.qtxa.clean.d.a$a, reason: collision with other inner class name */
    class RunnableC0006a implements Runnable {
        RunnableC0006a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
        }
    }

    class b extends IPackageDataObserver.Stub {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Semaphore f29a;

        b(a aVar, Semaphore semaphore) {
            this.f29a = semaphore;
        }

        @Override // android.content.pm.IPackageDataObserver
        public void onRemoveCompleted(String str, boolean z) {
            this.f29a.release();
        }
    }

    private a(Context context) {
        new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = false;
        this.f27b = context.getPackageManager();
        this.f26a = Executors.newSingleThreadExecutor();
    }

    private void c(boolean z) {
        this.e = 0;
        if (this.f27b != null) {
            Semaphore semaphore = new Semaphore(1, true);
            int size = this.f.size();
            for (int i2 = 1; i2 <= size; i2++) {
                ApplicationInfo applicationInfo = this.f.get(i2 - 1);
                try {
                    semaphore.acquire();
                } catch (Throwable th) {
                    th.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                try {
                    this.f27b.deleteApplicationCacheFiles(applicationInfo.packageName, new b(this, semaphore));
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                if (z) {
                    this.d = (i2 * 100) / size;
                    e("DiskClean:" + applicationInfo.packageName + " for i=" + i2 + " for totalpercent: " + this.d);
                    int i3 = this.e;
                    int i4 = this.d;
                    if (i3 < i4) {
                        k(i4);
                    }
                    this.e = this.d;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        c(false);
        this.c = Boolean.FALSE;
        this.h = true;
    }

    private void e(String str) {
        if (str != null) {
            w.e("DiskClean", str);
        }
    }

    private void f() {
        c(true);
        e("disk Clean over.");
        this.c = Boolean.FALSE;
    }

    public static a g(Context context) {
        if (k == null) {
            synchronized (a.class) {
                k = new a(context);
            }
        }
        return k;
    }

    private void h() {
        PackageManager packageManager = this.f27b;
        if (packageManager != null) {
            for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(128)) {
                if (applicationInfo.packageName.contains("qtxa")) {
                    this.f.add(applicationInfo);
                }
            }
        }
    }

    private void k(int i2) {
        Iterator<com.qtxa.clean.a> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().f(i2);
        }
    }

    public void b(com.qtxa.clean.a aVar) {
        if (aVar == null || this.g.contains(aVar)) {
            return;
        }
        this.g.add(aVar);
    }

    public void i(double d) {
    }

    public void j(String str) {
        Boolean bool = Boolean.TRUE;
        List<ApplicationInfo> list = this.f;
        if (list != null && list.size() < 1) {
            h();
        }
        if (this.c.booleanValue()) {
            e("is cleanning, stop this unnecessary clean.");
            return;
        }
        if (str != null && str.equals(j)) {
            this.c = bool;
            f();
        } else if (str == null || !str.equals(i)) {
            e("start clean flag is null.");
        } else {
            if (this.h) {
                return;
            }
            this.f26a.submit(new RunnableC0006a());
            this.c = bool;
        }
    }
}
