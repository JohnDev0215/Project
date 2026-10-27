package com.amazon.music.media.auto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Process;
import android.util.Base64;
import com.amazon.music.media.auto.provider.SubDeviceType;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.util.AutoLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: PackageValidator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0003J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\rH\u0002J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J$\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004H\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/media/auto/PackageValidator;", "", "()V", "validCertificates", "", "", "Ljava/util/ArrayList;", "Lcom/amazon/music/media/auto/AllowedMediaBrowserCaller;", "Lkotlin/collections/ArrayList;", "validSubDeviceTypes", "", "Lcom/amazon/music/media/auto/provider/SubDeviceType;", "getPackageInfo", "Landroid/content/pm/PackageInfo;", "callingPackage", "context", "Landroid/content/Context;", "getSignature", "packageInfo", "getSystemSignature", "isCallerAllowed", "", "callingUid", "", "readValidCertificates", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PackageValidator {
    private static final String TAG = PackageValidator.class.getSimpleName();
    private final Map<String, SubDeviceType> validSubDeviceTypes = new LinkedHashMap();
    private final Map<String, ArrayList<AllowedMediaBrowserCaller>> validCertificates = readValidCertificates();

    private final Map<String, ArrayList<AllowedMediaBrowserCaller>> readValidCertificates() {
        HashMap map = new HashMap();
        AllowedMediaBrowserCaller[] allowedMediaBrowserCallerArrValues = AllowedMediaBrowserCaller.values();
        int length = allowedMediaBrowserCallerArrValues.length;
        int i = 0;
        while (i < length) {
            AllowedMediaBrowserCaller allowedMediaBrowserCaller = allowedMediaBrowserCallerArrValues[i];
            i++;
            String strReplace = new Regex("\\s|\\n").replace(allowedMediaBrowserCaller.getCertificate(), "");
            ArrayList arrayList = (ArrayList) map.get(strReplace);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map.put(strReplace, arrayList);
            }
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.verbose(TAG2, "Adding allowed caller: " + allowedMediaBrowserCaller.name() + ", package=" + allowedMediaBrowserCaller.getPackageName() + ", release=" + allowedMediaBrowserCaller.getRelease() + ", certificate=" + strReplace);
            arrayList.add(allowedMediaBrowserCaller);
            this.validSubDeviceTypes.put(allowedMediaBrowserCaller.getPackageName(), allowedMediaBrowserCaller.getSubDeviceType());
        }
        return map;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public final boolean isCallerAllowed(Context context, String callingPackage, int callingUid) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callingPackage, "callingPackage");
        if (1000 == callingUid) {
            SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_SYSTEM_APP_CALLING_ID);
            return true;
        }
        if (Process.myUid() == callingUid) {
            SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.SELF);
            return true;
        }
        try {
            PackageInfo packageInfo = getPackageInfo(callingPackage, context);
            if (packageInfo == null) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.warning(TAG2, "Cannot validate null packageInfo");
                SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_PACKAGE_INFO);
                return false;
            }
            String signature = getSignature(packageInfo);
            if (signature == null) {
                AutoLog autoLog2 = AutoLog.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                autoLog2.warning(TAG3, "Signature of client package " + callingPackage + " is null");
                SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_CLIENT_SIGNATURE);
                return false;
            }
            ArrayList<AllowedMediaBrowserCaller> arrayList = this.validCertificates.get(signature);
            AutoLog autoLog3 = AutoLog.INSTANCE;
            String TAG4 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG4, "TAG");
            autoLog3.debug(TAG4, Intrinsics.stringPlus("isCallerAllowed ", arrayList));
            if (arrayList != null) {
                Iterator<AllowedMediaBrowserCaller> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(callingPackage, it.next().getPackageName())) {
                        AutoLog autoLog4 = AutoLog.INSTANCE;
                        String TAG5 = TAG;
                        Intrinsics.checkNotNullExpressionValue(TAG5, "TAG");
                        autoLog4.debug(TAG5, "isCallerAllowed: found match for caller " + callingPackage + ", returning true");
                        SubDeviceType subDeviceType = this.validSubDeviceTypes.get(callingPackage);
                        if (subDeviceType != null) {
                            SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(subDeviceType);
                            return true;
                        }
                        SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_INVALID_CLIENT_APP);
                        return false;
                    }
                }
            }
            if (Intrinsics.areEqual(getSystemSignature(context), signature) && Intrinsics.areEqual(context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
                SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.ANDROID_AUTOMOTIVE_OS);
                return true;
            }
            String[] strArr = packageInfo.requestedPermissions;
            int[] iArr = packageInfo.requestedPermissionsFlags;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (strArr != null) {
                int length = strArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    String str = strArr[i];
                    i++;
                    int i3 = i2 + 1;
                    if ((iArr[i2] & 2) != 0) {
                        linkedHashSet.add(str);
                    }
                    i2 = i3;
                }
            }
            if (linkedHashSet.contains("android.permission.MEDIA_CONTENT_CONTROL") || linkedHashSet.contains("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
                SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_SYSTEM_APP);
                return true;
            }
            AutoLog autoLog5 = AutoLog.INSTANCE;
            String TAG6 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG6, "TAG");
            autoLog5.debug(TAG6, "Caller has a valid certificate, but its package doesn't match any expected package for the given certificate. Caller's package is " + callingPackage + ". Expected packages as defined in AllowedMediaBrowserCallers are (" + arrayList + "). This caller's certificate is: \n" + ((Object) signature));
            SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_VALID_PACKAGE);
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            AutoLog autoLog6 = AutoLog.INSTANCE;
            String TAG7 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG7, "TAG");
            autoLog6.warning(TAG7, Intrinsics.stringPlus("Package manager can't find package: ", callingPackage), e);
            SubDeviceTypeProvider.INSTANCE.initializeSubDeviceType(SubDeviceType.UNKNOWN_PACKAGE_MANAGER);
            return false;
        }
    }

    private final String getSystemSignature(Context context) {
        PackageInfo packageInfo = getPackageInfo("android", context);
        String signature = packageInfo == null ? null : getSignature(packageInfo);
        if (signature != null) {
            return signature;
        }
        throw new IllegalStateException("Platform signature not found");
    }

    private final String getSignature(PackageInfo packageInfo) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || signatureArr.length != 1) {
            return null;
        }
        return Base64.encodeToString(signatureArr[0].toByteArray(), 2);
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private final PackageInfo getPackageInfo(String callingPackage, Context context) {
        return context.getPackageManager().getPackageInfo(callingPackage, 4160);
    }
}
