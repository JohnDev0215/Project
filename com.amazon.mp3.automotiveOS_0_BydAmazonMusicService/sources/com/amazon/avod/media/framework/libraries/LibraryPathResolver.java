package com.amazon.avod.media.framework.libraries;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class LibraryPathResolver {
    private final String mCanonicalLibPath;
    private final String mDataLibPath;
    private final String mNativeLibPath;
    private final List<String> mOrderedLibraryPaths;
    private final String mPrivLibPath;

    private LibraryPathResolver(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nonnull List<String> list) {
        this.mNativeLibPath = str;
        this.mDataLibPath = str2;
        this.mCanonicalLibPath = str3;
        this.mPrivLibPath = str4;
        this.mOrderedLibraryPaths = (List) Preconditions.checkNotNull(list, "orderedLibraryPaths");
    }

    public static LibraryPathResolver createResolver(@Nonnull Context context) {
        String str;
        String str2;
        String string;
        Preconditions.checkNotNull(context);
        String canonicalPath = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            str2 = packageInfo.applicationInfo.nativeLibraryDir;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(packageInfo.applicationInfo.dataDir);
                String str3 = File.separator;
                sb.append(str3);
                sb.append("lib");
                string = sb.toString();
                try {
                    str = packageInfo.applicationInfo.dataDir + str3 + "priv-lib";
                    try {
                        canonicalPath = new File(string).getCanonicalPath();
                    } catch (PackageManager.NameNotFoundException e) {
                        e = e;
                        DLog.warnf("LibraryPathResolver: Failed to get the package native library path: %s", e);
                    } catch (IOException e2) {
                        e = e2;
                        DLog.warnf("LibraryPathResolver: Failed to get the canonical path to native library: %s", e);
                    }
                } catch (PackageManager.NameNotFoundException e3) {
                    e = e3;
                    str = null;
                } catch (IOException e4) {
                    e = e4;
                    str = null;
                }
            } catch (PackageManager.NameNotFoundException e5) {
                e = e5;
                str = null;
                string = null;
            } catch (IOException e6) {
                e = e6;
                str = null;
                string = null;
            }
        } catch (PackageManager.NameNotFoundException e7) {
            e = e7;
            str = null;
            str2 = null;
            string = null;
        } catch (IOException e8) {
            e = e8;
            str = null;
            str2 = null;
            string = null;
        }
        String str4 = str;
        String str5 = canonicalPath;
        String str6 = str2;
        String str7 = string;
        ArrayList arrayList = new ArrayList();
        if (str7 != null) {
            arrayList.add(str7);
        }
        if (str5 != null && !arrayList.contains(str5)) {
            arrayList.add(str5);
        }
        if (str6 != null && !arrayList.contains(str6)) {
            arrayList.add(str6);
        }
        if (str4 != null && !arrayList.contains(str4)) {
            arrayList.add(str4);
        }
        if (arrayList.isEmpty()) {
            DLog.warnf("LibraryPathResolver: OrderedLibraryPaths empty, failed to resolve any path");
        }
        return new LibraryPathResolver(str6, str7, str5, str4, arrayList);
    }

    @Nonnull
    public List<String> getOrderedLibraryPaths() {
        return this.mOrderedLibraryPaths;
    }
}
