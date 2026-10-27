package spi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class zb {

    @NotNull
    public static final zb a = new zb();

    private zb() {
    }

    public final boolean a(@NotNull String receivePackageName, @NotNull String dirName, @NotNull Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(receivePackageName, "receivePackageName");
        Intrinsics.checkNotNullParameter(dirName, "dirName");
        Intrinsics.checkNotNullParameter(context, "context");
        zb zbVar = a;
        String strD = zbVar.d(context, dirName);
        if (strD.length() == 0) {
            ct.b(d.g("delete work deleteCheckApkAndDir unZipDirectory isEmpty receivePkn:", receivePackageName, " primePkn:", dirName), new Object[0]);
            return false;
        }
        File file = new File(strD);
        if (!file.isDirectory()) {
            ct.b(d.g("delete work deleteCheckApkAndDir unZipDirectory is notDirectory receivePkn:", receivePackageName, " primePkn:", dirName), new Object[0]);
            return false;
        }
        List<File> listF = zbVar.f(file);
        if (listF == null || listF.isEmpty()) {
            ct.b(d.g("delete work deleteCheckApkAndDir fileList isNullOrEmpty receivePkn:", receivePackageName, " primePkn:", dirName), new Object[0]);
            return false;
        }
        Iterator<T> it = listF.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            sr srVar = sr.a;
            String canonicalPath = ((File) next).getCanonicalPath();
            Intrinsics.checkNotNullExpressionValue(canonicalPath, "apkFile.canonicalPath");
            PackageInfo packageInfoA = srVar.a(context, canonicalPath);
            if (Intrinsics.areEqual(packageInfoA != null ? packageInfoA.packageName : null, receivePackageName)) {
                obj = next;
                break;
            }
        }
        File file2 = (File) obj;
        if (file2 == null) {
            ct.b(d.g("delete work deleteCheckApkAndDir findFile isNull receivePkn:", receivePackageName, " primePkn:", dirName), new Object[0]);
            return false;
        }
        boolean zDelete = file2.delete();
        StringBuilder sb = new StringBuilder();
        if (!zDelete) {
            sb.append("delete work deleteCheckApkAndDir findFile.delete() fail ret:");
            sb.append(zDelete);
            sb.append(" receivePkn:");
            sb.append(receivePackageName);
            ct.b(hi.f(sb, " primePkn:", dirName), new Object[0]);
            return true;
        }
        sb.append("delete work deleteCheckApkAndDir findFile.delete() success ret:");
        sb.append(zDelete);
        sb.append(" receivePkn:");
        sb.append(receivePackageName);
        ct.a(hi.f(sb, " primePkn:", dirName), new Object[0]);
        List<File> listF2 = a.f(file);
        if (!(listF2 == null || listF2.isEmpty())) {
            StringBuilder sbH = xi.h("delete work deleteCheckApkAndDir restFileList.size = ", listF2.size(), " not finish yet receivePkn:", receivePackageName, " primePkn:");
            sbH.append(dirName);
            ct.a(sbH.toString(), new Object[0]);
            return false;
        }
        ct.a("delete work deleteCheckApkAndDir restFileList.isNullOrEmpty() fileDir.delete() ret:" + file.delete() + " receivePkn:" + receivePackageName + " primePkn:" + dirName, new Object[0]);
        return true;
    }

    public final boolean b(@NotNull String fileSourceDir) {
        Intrinsics.checkNotNullParameter(fileSourceDir, "fileSourceDir");
        if (fileSourceDir.length() == 0) {
            return false;
        }
        File file = new File(fileSourceDir);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    public final String c(Context context) {
        return e.d(context.getFilesDir().getPath(), File.separator, "download_apks");
    }

    @NotNull
    public final String d(@NotNull Context context, @NotNull String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return TextUtils.isEmpty(packageName) ? "" : e.d(c(context), File.separator, packageName);
    }

    @NotNull
    public final File e(@NotNull Context context, short s, @NotNull String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        File file = new File(c(context));
        if (!file.exists()) {
            file.mkdirs();
        }
        sr srVar = sr.a;
        if (!srVar.k(s)) {
            packageName = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(packageName, "randomUUID().toString()");
            if (StringsKt__StringsKt.contains$default(packageName, "-", false, 2, (Object) null)) {
                packageName = StringsKt__StringsJVMKt.replace$default(packageName, "-", "", false, 4, (Object) null);
            }
        }
        return new File(file.getCanonicalPath(), d.f(packageName, srVar.k(s) ? ".zip" : ".apk"));
    }

    @Nullable
    public final List<File> f(@NotNull File fileDir) {
        Intrinsics.checkNotNullParameter(fileDir, "fileDir");
        File[] fileArrListFiles = fileDir.listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isFile()) {
                arrayList.add(file);
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    public final long g(@NotNull String dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        return new File(dir).length();
    }
}
