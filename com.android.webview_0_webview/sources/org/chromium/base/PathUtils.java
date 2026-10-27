package org.chromium.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.system.Os;
import android.text.TextUtils;
import defpackage.AbstractC0279Pk;
import defpackage.C0683dt;
import defpackage.Is;
import defpackage.Pu;
import defpackage.Su;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.PathUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class PathUtils {
    public static final AtomicBoolean a = new AtomicBoolean();
    public static FutureTask b;
    public static String c;
    public static String d;

    public static String a(int i) {
        if (!b.isDone()) {
            C0683dt c0683dtA = C0683dt.A();
            try {
                b.run();
                c0683dtA.close();
            } catch (Throwable th) {
                try {
                    c0683dtA.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
                throw th;
            }
        }
        try {
            return ((String[]) b.get())[i];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void b(String str, String str2) {
        if (a.getAndSet(true)) {
            return;
        }
        c = str;
        d = str2;
        b = new FutureTask(new Callable() { // from class: at
            @Override // java.util.concurrent.Callable
            public Object call() {
                String[] strArr = new String[3];
                Context context = Is.a;
                strArr[0] = context.getDir(PathUtils.c, 0).getPath();
                String str3 = strArr[0];
                try {
                    Os.chmod(str3, 448);
                } catch (Exception unused) {
                    Ts.a("PathUtils", Z7.d("Failed to set permissions for path \"", str3, "\""), new Object[0]);
                }
                strArr[1] = context.getDir("textures", 0).getPath();
                if (context.getCacheDir() != null) {
                    if (PathUtils.d == null) {
                        strArr[2] = context.getCacheDir().getPath();
                    } else {
                        strArr[2] = new File(context.getCacheDir(), PathUtils.d).getPath();
                    }
                }
                return strArr;
            }
        });
        ((Pu) Su.f).execute(b);
    }

    public static String[] getAllPrivateDownloadsDirectories() {
        C0683dt c0683dtA = C0683dt.A();
        try {
            File[] externalFilesDirs = Is.a.getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
            c0683dtA.close();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < externalFilesDirs.length; i++) {
                if (externalFilesDirs[i] != null && !TextUtils.isEmpty(externalFilesDirs[i].getAbsolutePath())) {
                    arrayList.add(externalFilesDirs[i].getAbsolutePath());
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (Throwable th) {
            try {
                c0683dtA.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public static String getCacheDirectory() {
        return a(2);
    }

    public static String getDataDirectory() {
        return a(0);
    }

    public static String getDownloadsDirectory() {
        C0683dt c0683dtX = C0683dt.x();
        try {
            if (BuildInfo.a()) {
                String str = getAllPrivateDownloadsDirectories().length == 0 ? "" : getAllPrivateDownloadsDirectories()[0];
                c0683dtX.close();
                return str;
            }
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            c0683dtX.close();
            return path;
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getNativeLibraryDirectory() {
        ApplicationInfo applicationInfo = Is.a.getApplicationInfo();
        int i = applicationInfo.flags;
        return ((i & 128) != 0 || (i & 1) == 0) ? applicationInfo.nativeLibraryDir : "/system/lib/";
    }

    public static String getThumbnailCacheDirectory() {
        return a(1);
    }
}
