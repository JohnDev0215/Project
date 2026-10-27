package spi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.graphics.drawable.Drawable;
import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class zp implements aq {

    @NotNull
    public final Context a;

    public zp(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
    }

    @Override // spi.aq
    @SuppressLint({"QueryPermissionsNeeded"})
    @NotNull
    public final ApiDataBody<List<SoftwareDeviceInstalledRsp>> a() throws PackageManager.NameNotFoundException {
        sr srVar = sr.a;
        PackageManager packageManagerF = srVar.f(this.a);
        List<ResolveInfo> listH = srVar.h(this.a);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listH) {
            ct.a(d.f("deviceApp pkn:", resolveInfo.activityInfo.packageName), new Object[0]);
            sr srVar2 = sr.a;
            Context context = this.a;
            String str = resolveInfo.activityInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "resolveInfo.activityInfo.packageName");
            ApplicationInfo applicationInfoG = srVar2.g(context, str);
            if (applicationInfoG != null) {
                arrayList.add(applicationInfoG);
            }
        }
        if (arrayList.isEmpty()) {
            ct.b("deviceApp fetchSoftwareList deviceSoftwareList is isNullOrEmpty()", new Object[0]);
            return new ApiDataBody<>(-1, null, null, 6, null);
        }
        sr srVar3 = sr.a;
        Context context2 = this.a;
        Objects.requireNonNull(srVar3);
        Intrinsics.checkNotNullParameter(context2, "context");
        String packageName = context2.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (true ^ Intrinsics.areEqual(((ApplicationInfo) obj).packageName, packageName)) {
                arrayList2.add(obj);
            }
        }
        ArrayList<ApplicationInfo> arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            sr srVar4 = sr.a;
            int i = ((ApplicationInfo) obj2).flags;
            Objects.requireNonNull(srVar4);
            if (!((i & 1) != 0)) {
                arrayList3.add(obj2);
            }
        }
        if (arrayList3.isEmpty()) {
            ct.b("deviceApp fetchSoftwareNotSystemList targetList is isNullOrEmpty()", new Object[0]);
            return new ApiDataBody<>(-1, null, null, 6, null);
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.b(arrayList3));
        for (ApplicationInfo applicationInfo : arrayList3) {
            PackageInfo packageInfo = packageManagerF.getPackageInfo(applicationInfo.packageName, 134217728);
            sr srVar5 = sr.a;
            SigningInfo signingInfo = packageInfo.signingInfo;
            Signature[] apkContentsSigners = signingInfo != null ? signingInfo.getApkContentsSigners() : null;
            String str2 = applicationInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str2, "it.packageName");
            String strB = srVar5.b(apkContentsSigners, str2);
            int i2 = packageInfo.versionCode;
            Drawable drawableLoadIcon = applicationInfo.loadIcon(packageManagerF);
            String string = packageManagerF.getApplicationLabel(applicationInfo).toString();
            String packageName2 = applicationInfo.packageName;
            String sourceDir = applicationInfo.sourceDir;
            long length = new File(applicationInfo.sourceDir).length();
            String versionName = packageInfo.versionName;
            Intrinsics.checkNotNullExpressionValue(drawableLoadIcon, "loadIcon(packageManager)");
            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
            Intrinsics.checkNotNullExpressionValue(versionName, "versionName");
            Intrinsics.checkNotNullExpressionValue(sourceDir, "sourceDir");
            arrayList4.add(new SoftwareDeviceInstalledRsp(drawableLoadIcon, string, packageName2, strB, i2, versionName, sourceDir, length));
        }
        return new ApiDataBody<>(0, null, arrayList4, 3, null);
    }

    @Override // spi.aq
    @SuppressLint({"QueryPermissionsNeeded"})
    @NotNull
    public final ApiDataBody<List<SoftwareDeviceInstalledRsp>> b() throws PackageManager.NameNotFoundException {
        Object objM63constructorimpl;
        sr srVar = sr.a;
        PackageManager packageManagerF = srVar.f(this.a);
        List<ResolveInfo> listH = srVar.h(this.a);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listH) {
            ct.a(d.f("deviceApp pkn:", resolveInfo.activityInfo.packageName), new Object[0]);
            sr srVar2 = sr.a;
            Context context = this.a;
            String str = resolveInfo.activityInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "resolveInfo.activityInfo.packageName");
            ApplicationInfo applicationInfoG = srVar2.g(context, str);
            if (applicationInfoG != null) {
                arrayList.add(applicationInfoG);
            }
        }
        if (arrayList.isEmpty()) {
            ct.b("deviceApp fetchSoftwareList deviceSoftwareList is isNullOrEmpty()", new Object[0]);
            return new ApiDataBody<>(-1, null, null, 6, null);
        }
        sr srVar3 = sr.a;
        Context context2 = this.a;
        Objects.requireNonNull(srVar3);
        Intrinsics.checkNotNullParameter(context2, "context");
        String packageName = context2.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (true ^ Intrinsics.areEqual(((ApplicationInfo) obj).packageName, packageName)) {
                arrayList2.add(obj);
            }
        }
        ArrayList<ApplicationInfo> arrayList3 = new ArrayList();
        Iterator it = arrayList2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ApplicationInfo applicationInfo = (ApplicationInfo) next;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM63constructorimpl = Result.m63constructorimpl(packageManagerF.getPackageInfo(applicationInfo.packageName, 134217728));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM66exceptionOrNullimpl = Result.m66exceptionOrNullimpl(objM63constructorimpl);
            if (thM66exceptionOrNullimpl != null) {
                ct.b(e.d("deviceApp fetchSoftwareList error packageInfo message = ", thM66exceptionOrNullimpl.getMessage(), " "), new Object[0]);
            }
            if ((Result.m69isFailureimpl(objM63constructorimpl) ? null : objM63constructorimpl) != null) {
                arrayList3.add(next);
            }
        }
        if (arrayList3.isEmpty()) {
            ct.b("deviceApp fetchSoftwareList targetList is isNullOrEmpty()", new Object[0]);
            return new ApiDataBody<>(-1, null, null, 6, null);
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.b(arrayList3));
        for (ApplicationInfo applicationInfo2 : arrayList3) {
            PackageInfo packageInfo = packageManagerF.getPackageInfo(applicationInfo2.packageName, 134217728);
            sr srVar4 = sr.a;
            SigningInfo signingInfo = packageInfo.signingInfo;
            Signature[] apkContentsSigners = signingInfo != null ? signingInfo.getApkContentsSigners() : null;
            String str2 = applicationInfo2.packageName;
            Intrinsics.checkNotNullExpressionValue(str2, "it.packageName");
            String strB = srVar4.b(apkContentsSigners, str2);
            int i = packageInfo.versionCode;
            Drawable drawableLoadIcon = applicationInfo2.loadIcon(packageManagerF);
            String string = packageManagerF.getApplicationLabel(applicationInfo2).toString();
            String packageName2 = applicationInfo2.packageName;
            String sourceDir = applicationInfo2.sourceDir;
            long length = new File(applicationInfo2.sourceDir).length();
            String versionName = packageInfo.versionName;
            Intrinsics.checkNotNullExpressionValue(drawableLoadIcon, "loadIcon(packageManager)");
            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
            Intrinsics.checkNotNullExpressionValue(versionName, "versionName");
            Intrinsics.checkNotNullExpressionValue(sourceDir, "sourceDir");
            arrayList4.add(new SoftwareDeviceInstalledRsp(drawableLoadIcon, string, packageName2, strB, i, versionName, sourceDir, length));
        }
        return new ApiDataBody<>(0, null, arrayList4, 3, null);
    }
}
