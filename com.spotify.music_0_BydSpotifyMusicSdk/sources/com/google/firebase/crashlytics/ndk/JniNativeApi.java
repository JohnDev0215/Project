package com.google.firebase.crashlytics.ndk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import p.cm1;

/* JADX INFO: loaded from: classes.dex */
class JniNativeApi implements cm1 {
    public static final boolean b;
    public static final b c = new b();
    public final Context a;

    static {
        boolean z;
        try {
            System.loadLibrary("crashlytics");
            z = true;
        } catch (UnsatisfiedLinkError e) {
            Log.e("FirebaseCrashlytics", "libcrashlytics could not be loaded. This APK may not have been compiled for this device's architecture. NDK crashes will not be reported to Crashlytics:\n" + e.getLocalizedMessage(), null);
            z = false;
        }
        b = z;
    }

    public JniNativeApi(Context context) {
        this.a = context;
    }

    public static void a(ArrayList arrayList, PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        String[] strArr = applicationInfo.splitSourceDirs;
        if (strArr != null) {
            Collections.addAll(arrayList, strArr);
        }
        String str = applicationInfo.dataDir;
        Object[] objArr = new Object[1];
        objArr[0] = Build.VERSION.SDK_INT >= 28 ? Long.toString(packageInfo.getLongVersionCode()) : Integer.toString(packageInfo.versionCode);
        File file = new File(str, String.format("files/splitcompat/%s/verified-splits", objArr));
        if (!file.exists()) {
            file.getAbsolutePath();
            Log.isLoggable("FirebaseCrashlytics", 3);
            return;
        }
        File[] fileArrListFiles = file.listFiles(c);
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        file.getAbsolutePath();
        Log.isLoggable("FirebaseCrashlytics", 3);
        for (File file2 : fileArrListFiles) {
            file2.getName();
            Log.isLoggable("FirebaseCrashlytics", 3);
            arrayList.add(file2.getAbsolutePath());
        }
    }

    private native boolean nativeInit(String[] strArr, Object obj);

    public final boolean b(AssetManager assetManager, String str) {
        String str2 = Build.CPU_ABI;
        Context context = this.a;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 9216);
            ArrayList<String> arrayList = new ArrayList(10);
            arrayList.add(packageInfo.applicationInfo.sourceDir);
            a(arrayList, packageInfo);
            String[] strArr = packageInfo.applicationInfo.sharedLibraryFiles;
            if (strArr != null) {
                Collections.addAll(arrayList, strArr);
            }
            ArrayList arrayList2 = new ArrayList(10);
            File parentFile = new File(packageInfo.applicationInfo.nativeLibraryDir).getParentFile();
            if (parentFile != null) {
                arrayList2.add(new File(parentFile, str2).getPath());
                if (str2.startsWith("arm64")) {
                    arrayList2.add(new File(parentFile, "arm64").getPath());
                } else if (str2.startsWith("arm")) {
                    arrayList2.add(new File(parentFile, "arm").getPath());
                }
            }
            for (String str3 : arrayList) {
                if (str3.endsWith(".apk")) {
                    arrayList2.add(str3 + "!/lib/" + str2);
                }
            }
            arrayList2.add(System.getProperty("java.library.path"));
            arrayList2.add(packageInfo.applicationInfo.nativeLibraryDir);
            String str4 = File.pathSeparator;
            String strJoin = TextUtils.join(str4, arrayList2);
            return b && nativeInit(new String[]{new String[]{TextUtils.join(str4, arrayList), strJoin}[0], strJoin, str}, assetManager);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("FirebaseCrashlytics", "Unable to compose package paths", e);
            throw new RuntimeException(e);
        }
    }
}
