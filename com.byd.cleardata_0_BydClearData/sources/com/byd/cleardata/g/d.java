package com.byd.cleardata.g;

import android.annotation.SuppressLint;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.byd.cleardata.FileApplication;
import com.byd.cleardata.i.m;
import com.byd.cleardata.i.o;
import com.byd.cleardata.service.FileScanService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
public class d {

    class a implements c.a.e<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.d f2643a;

        a(com.byd.cleardata.d.d dVar) {
            this.f2643a = dVar;
        }

        @Override // c.a.e
        public void a(c.a.d<String> dVar) {
            com.byd.cleardata.i.c.b("getFilesCount start");
            dVar.c(com.byd.cleardata.i.e.x(this.f2643a.B()));
            dVar.a();
        }
    }

    class b implements c.a.e<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.d f2644a;

        b(com.byd.cleardata.d.d dVar) {
            this.f2644a = dVar;
        }

        @Override // c.a.e
        public void a(c.a.d<String> dVar) {
            try {
                String strV = com.byd.cleardata.i.e.v(this.f2644a.w());
                com.byd.cleardata.i.c.b("location = " + strV);
                if (!TextUtils.isEmpty(strV)) {
                    dVar.c(strV);
                }
                dVar.a();
            } catch (Throwable th) {
                th.printStackTrace();
                dVar.b(th);
            }
        }
    }

    class c implements c.a.e<List<com.byd.cleardata.d.b>> {
        c() {
        }

        @Override // c.a.e
        public void a(c.a.d<List<com.byd.cleardata.d.b>> dVar) {
            try {
                List<com.byd.cleardata.d.b> listD = com.byd.cleardata.i.a.d(FileApplication.a(), false);
                if (listD == null || listD.size() <= 0) {
                    listD = new ArrayList<>();
                }
                dVar.c(listD);
                dVar.a();
            } catch (Throwable th) {
                th.printStackTrace();
                dVar.b(th);
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.cleardata.g.d$d, reason: collision with other inner class name */
    class C0093d implements c.a.e<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2645a;

        /* JADX INFO: renamed from: com.byd.cleardata.g.d$d$a */
        class a extends IPackageDataObserver.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.a.d f2646a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ CountDownLatch f2647b;

            a(c.a.d dVar, CountDownLatch countDownLatch) {
                this.f2646a = dVar;
                this.f2647b = countDownLatch;
            }

            public void onRemoveCompleted(String str, boolean z) {
                com.byd.cleardata.i.c.b("onRemoveCompleted b = " + z);
                this.f2646a.c(Boolean.valueOf(z));
                this.f2647b.countDown();
            }
        }

        C0093d(String str) {
            this.f2645a = str;
        }

        @Override // c.a.e
        public void a(c.a.d<Boolean> dVar) throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            try {
                PackageManager packageManager = FileApplication.a().getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(this.f2645a, 0);
                com.byd.cleardata.i.c.b("packageName = " + this.f2645a + " ,pi.packageName = " + packageInfo.packageName);
                packageManager.clearApplicationUserData(packageInfo.packageName, new a(dVar, countDownLatch));
            } catch (Throwable th) {
                th.printStackTrace();
                dVar.c(Boolean.FALSE);
            }
            com.byd.cleardata.i.c.b("clearAppData end");
            countDownLatch.await();
            dVar.a();
        }
    }

    class e implements c.a.e<Boolean> {

        class a extends IPackageDataObserver.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AtomicBoolean f2649a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ CountDownLatch f2650b;

            a(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch) {
                this.f2649a = atomicBoolean;
                this.f2650b = countDownLatch;
            }

            public void onRemoveCompleted(String str, boolean z) {
                if (!z) {
                    this.f2649a.set(false);
                }
                this.f2650b.countDown();
            }
        }

        e() {
        }

        @Override // c.a.e
        public void a(c.a.d<Boolean> dVar) {
            Boolean boolValueOf;
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                PackageManager packageManager = FileApplication.a().getPackageManager();
                List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
                int size = installedPackages.size();
                if (size > 0) {
                    CountDownLatch countDownLatch = new CountDownLatch(size);
                    Iterator<PackageInfo> it = installedPackages.iterator();
                    while (it.hasNext()) {
                        packageManager.deleteApplicationCacheFiles(it.next().packageName, new a(atomicBoolean, countDownLatch));
                    }
                    countDownLatch.await();
                    boolValueOf = Boolean.valueOf(atomicBoolean.get());
                } else {
                    boolValueOf = Boolean.FALSE;
                }
                dVar.c(boolValueOf);
            } catch (Throwable th) {
                th.printStackTrace();
                dVar.c(Boolean.FALSE);
            }
            dVar.a();
        }
    }

    class f implements c.a.e<com.byd.cleardata.d.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f2653b;

        f(String str, String str2) {
            this.f2652a = str;
            this.f2653b = str2;
        }

        @Override // c.a.e
        public void a(c.a.d<com.byd.cleardata.d.d> dVar) {
            com.byd.cleardata.d.d dVar2;
            com.byd.cleardata.i.c.b("create path " + this.f2652a + File.separator + this.f2653b);
            boolean z = false;
            long jCount = 0;
            try {
                try {
                    Stream<Path> list = Files.list(Paths.get(this.f2652a, new String[0]));
                    try {
                        jCount = list.filter(new Predicate() { // from class: com.byd.cleardata.g.a
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return Files.isDirectory((Path) obj, new LinkOption[0]);
                            }
                        }).count();
                        list.close();
                    } finally {
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (jCount < 500) {
                    File fileB = com.byd.cleardata.i.e.b(this.f2652a, this.f2653b);
                    m.b();
                    StringBuilder sb = new StringBuilder();
                    sb.append("create success ");
                    if (fileB != null && fileB.exists()) {
                        z = true;
                    }
                    sb.append(z);
                    com.byd.cleardata.i.c.b(sb.toString());
                    dVar2 = new com.byd.cleardata.d.d(fileB);
                } else {
                    com.byd.cleardata.i.c.b("number of folders exceeds 500");
                    dVar2 = new com.byd.cleardata.d.d();
                }
                dVar.c(dVar2);
            } catch (Throwable th2) {
                com.byd.cleardata.i.c.d("create failed", th2);
                dVar.c(new com.byd.cleardata.d.d());
            }
            dVar.a();
        }
    }

    class g implements c.a.e<com.byd.cleardata.d.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.d f2654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f2655b;

        g(com.byd.cleardata.d.d dVar, String str) {
            this.f2654a = dVar;
            this.f2655b = str;
        }

        @Override // c.a.e
        public void a(c.a.d<com.byd.cleardata.d.d> dVar) {
            String strE = this.f2654a.E();
            File fileK = com.byd.cleardata.i.e.K(this.f2654a.w(), this.f2655b);
            FileScanService.e(strE);
            if (com.byd.cleardata.i.e.h(fileK)) {
                this.f2654a.R(fileK);
                this.f2654a.U(fileK.getName());
                this.f2654a.P(false);
                m.b();
            }
            dVar.c(new com.byd.cleardata.d.d(fileK));
            dVar.a();
        }
    }

    class h implements c.a.e<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2656a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f2657b;

        h(int i, List list) {
            this.f2656a = i;
            this.f2657b = list;
        }

        @Override // c.a.e
        public void a(c.a.d<Boolean> dVar) {
            try {
                o.d(this.f2656a, this.f2657b);
                dVar.c(Boolean.TRUE);
                dVar.a();
            } catch (Throwable th) {
                dVar.b(th);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static c.a.c<com.byd.cleardata.d.d> a(String str, String str2) {
        return c.a.c.c(new f(str, str2));
    }

    @SuppressLint({"NewApi"})
    public static c.a.c<Boolean> b(String str) {
        return c.a.c.c(new e());
    }

    @SuppressLint({"NewApi"})
    public static c.a.c<Boolean> c(String str) {
        return c.a.c.c(new C0093d(str));
    }

    @SuppressLint({"NewApi"})
    public static c.a.c<List<com.byd.cleardata.d.b>> d() {
        return c.a.c.c(new c());
    }

    @SuppressLint({"NewApi"})
    public static c.a.c<String> e(com.byd.cleardata.d.d dVar) {
        return c.a.c.c(new a(dVar));
    }

    public static c.a.c<String> f(com.byd.cleardata.d.d dVar) {
        return c.a.c.c(new b(dVar));
    }

    public static c.a.c<com.byd.cleardata.d.d> g(com.byd.cleardata.d.d dVar, String str) {
        return c.a.c.c(new g(dVar, str));
    }

    public static c.a.c<Boolean> h(int i, List<com.byd.cleardata.d.d> list) {
        return c.a.c.c(new h(i, list));
    }
}
