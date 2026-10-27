package org.chromium.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import defpackage.fa1;
import defpackage.i9;
import defpackage.ku2;
import defpackage.uv;
import defpackage.xz;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.PathUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public abstract class PathUtils {
    public static final AtomicBoolean a = new AtomicBoolean();
    public static FutureTask<String[]> b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;

    @SuppressLint({"NewApi"})
    public static void a(int i, String str) {
        try {
            Os.chmod(str, i);
        } catch (Exception unused) {
            Log.e("cr_PathUtils", "Failed to set permissions for path \"" + str + "\"");
        }
    }

    public static String b(int i) {
        if (!b.isDone()) {
            ku2 ku2VarC = ku2.C();
            try {
                b.run();
                ku2VarC.close();
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        try {
            return b.get()[i];
        } catch (Exception e2) {
            throw new uv(e2);
        }
    }

    public static void c() {
        if (a.getAndSet(true)) {
            return;
        }
        e = null;
        f = null;
        c = "chrome";
        d = null;
        b = new FutureTask<>(new Callable() { // from class: fy1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                File file;
                File dir;
                String[] strArr = new String[3];
                Context context = xz.a;
                if (PathUtils.e == null) {
                    file = context.getDir(PathUtils.c, 0);
                    dir = context.getDir("textures", 0);
                } else {
                    file = new File(PathUtils.e, PathUtils.c);
                    file.mkdirs();
                    File file2 = new File(PathUtils.e, "textures");
                    file2.mkdirs();
                    dir = file2;
                }
                File file3 = PathUtils.f != null ? new File(PathUtils.f) : context.getCacheDir();
                if (file3 != null) {
                    if (PathUtils.d != null) {
                        file3 = new File(file3, PathUtils.d);
                    }
                    if (PathUtils.f != null || PathUtils.d != null) {
                        file3.mkdirs();
                        PathUtils.a(1472, strArr[2]);
                    }
                    strArr[2] = file3.getPath();
                }
                String path = file.getPath();
                strArr[0] = path;
                PathUtils.a(448, path);
                strArr[1] = dir.getPath();
                return strArr;
            }
        });
        i9.e.execute(b);
    }

    public static String[] d(List<File> list) {
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            if (file != null && !TextUtils.isEmpty(file.getAbsolutePath())) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @CalledByNative
    public static String[] getAllPrivateDownloadsDirectories() {
        List arrayList = new ArrayList();
        ku2 ku2VarC = ku2.C();
        try {
            File[] externalFilesDirs = xz.a.getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
            if (externalFilesDirs != null) {
                arrayList = Arrays.asList(externalFilesDirs);
            }
            ku2VarC.close();
            return d(arrayList);
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public static String getCacheDirectory() {
        return b(2);
    }

    @CalledByNative
    public static String getDataDirectory() {
        return b(0);
    }

    @CalledByNative
    public static String getDownloadsDirectory() {
        ku2 ku2VarT = ku2.t();
        try {
            String[] allPrivateDownloadsDirectories = getAllPrivateDownloadsDirectories();
            String str = allPrivateDownloadsDirectories.length == 0 ? "" : allPrivateDownloadsDirectories[0];
            ku2VarT.close();
            return str;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public static String[] getExternalDownloadVolumesNames() {
        ArrayList arrayList = new ArrayList();
        for (String str : MediaStore.getExternalVolumeNames(xz.a)) {
            if (!TextUtils.isEmpty(str) && !str.contains("external_primary")) {
                File directory = ((StorageManager) xz.a.getSystemService(StorageManager.class)).getStorageVolume(MediaStore.Files.getContentUri(str)).getDirectory();
                File file = new File(directory, Environment.DIRECTORY_DOWNLOADS);
                if (!file.isDirectory()) {
                    fa1.m("PathUtils", "Download dir missing: %s, parent dir:%s, isDirectory:%s", file.getAbsolutePath(), directory.getAbsolutePath(), Boolean.valueOf(directory.isDirectory()));
                }
                arrayList.add(file);
            }
        }
        return d(arrayList);
    }

    @CalledByNative
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    @CalledByNative
    public static String getNativeLibraryDirectory() {
        ApplicationInfo applicationInfo = xz.a.getApplicationInfo();
        int i = applicationInfo.flags;
        return ((i & 128) != 0 || (i & 1) == 0) ? applicationInfo.nativeLibraryDir : "/system/lib/";
    }

    @CalledByNative
    public static String getThumbnailCacheDirectory() {
        return b(1);
    }
}
