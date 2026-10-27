package org.chromium.components.crash.browser;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import defpackage.As;
import defpackage.Is;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class PackagePaths {
    public static String[] makePackagePaths(String str) {
        try {
            PackageInfo packageInfo = Is.a.getPackageManager().getPackageInfo(As.a.c, 9216);
            ArrayList<String> arrayList = new ArrayList(10);
            arrayList.add(packageInfo.applicationInfo.sourceDir);
            if (packageInfo.applicationInfo.splitSourceDirs != null) {
                Collections.addAll(arrayList, packageInfo.applicationInfo.splitSourceDirs);
            }
            if (packageInfo.applicationInfo.sharedLibraryFiles != null) {
                Collections.addAll(arrayList, packageInfo.applicationInfo.sharedLibraryFiles);
            }
            ArrayList arrayList2 = new ArrayList(10);
            File parentFile = new File(packageInfo.applicationInfo.nativeLibraryDir).getParentFile();
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
            arrayList2.add(packageInfo.applicationInfo.nativeLibraryDir);
            return new String[]{TextUtils.join(File.pathSeparator, arrayList), TextUtils.join(File.pathSeparator, arrayList2)};
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
