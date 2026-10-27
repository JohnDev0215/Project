package org.chromium.components.crash.browser;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import defpackage.zt1;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.base.BuildInfo;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PackagePaths {
    @CalledByNative
    public static String[] makePackagePaths(String str) {
        char[] cArr = zt1.a;
        PackageInfo packageInfoC = zt1.c(9216, BuildInfo.a.a.c);
        ArrayList<String> arrayList = new ArrayList(10);
        arrayList.add(packageInfoC.applicationInfo.sourceDir);
        String[] strArr = packageInfoC.applicationInfo.splitSourceDirs;
        if (strArr != null) {
            Collections.addAll(arrayList, strArr);
        }
        String[] strArr2 = packageInfoC.applicationInfo.sharedLibraryFiles;
        if (strArr2 != null) {
            Collections.addAll(arrayList, strArr2);
        }
        ArrayList arrayList2 = new ArrayList(10);
        File parentFile = new File(packageInfoC.applicationInfo.nativeLibraryDir).getParentFile();
        if (parentFile != null) {
            arrayList2.add(new File(parentFile, str).getPath());
            if (str.startsWith("arm64")) {
                arrayList2.add(new File(parentFile, "arm64").getPath());
            } else if (str.startsWith("arm")) {
                arrayList2.add(new File(parentFile, "arm").getPath());
            }
        }
        for (String str2 : arrayList) {
            if (str2.endsWith(".apk")) {
                arrayList2.add(str2 + "!/lib/" + str);
            }
        }
        arrayList2.add(System.getProperty("java.library.path"));
        arrayList2.add(packageInfoC.applicationInfo.nativeLibraryDir);
        String str3 = File.pathSeparator;
        return new String[]{TextUtils.join(str3, arrayList), TextUtils.join(str3, arrayList2)};
    }
}
