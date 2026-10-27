package com.qtxa.qtxadefend;

import android.content.pm.ApplicationInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private u f129b;
    List<m> d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f128a = null;
    private final Object c = new Object();

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static j f130a = new j();
    }

    public static j a() {
        return a.f130a;
    }

    private m f(ApplicationInfo applicationInfo) {
        return new m(applicationInfo);
    }

    public void b(u uVar) {
        this.f129b = uVar;
        this.f128a = new n(uVar.c());
    }

    boolean c(String str, File file) {
        synchronized (this.c) {
            try {
                try {
                    w.d("onInstall1");
                    if (!this.f128a.c(str, file)) {
                        w.d("invalid sign : " + file.getAbsolutePath());
                        return true;
                    }
                } catch (Exception e) {
                    w.d("onInstall3" + e.toString());
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    boolean d(String str) {
        synchronized (this.c) {
            try {
                try {
                    if (!this.f128a.c(str, null)) {
                        w.d("invalid sign : " + str);
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (com.qtxa.qtxadefend.a.j().d()) {
                    return false;
                }
                w.d("onRunPackage " + str + " apk(hash) saved.");
                this.f129b.f().d(str, true);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    boolean e(String str) {
        synchronized (this.c) {
            try {
                try {
                    if (!this.f128a.c(str, null) || com.qtxa.qtxadefend.a.j().d()) {
                        return false;
                    }
                    w.d("valid sign for onUninstall so block: " + str);
                    return true;
                } catch (IOException unused) {
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    List<m> g(int i) {
        try {
            if (this.d == null) {
                synchronized (j.class) {
                    this.d = new ArrayList();
                    for (ApplicationInfo applicationInfo : this.f129b.c().getPackageManager().getInstalledApplications(0)) {
                        if ((applicationInfo.flags & i) == 0) {
                            this.d.add(f(applicationInfo));
                        } else {
                            this.d.add(new m(applicationInfo));
                        }
                    }
                }
            }
        } catch (Exception unused) {
            this.d = null;
        }
        return this.d;
    }
}
