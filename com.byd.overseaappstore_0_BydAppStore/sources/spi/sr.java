package spi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class sr {

    @NotNull
    public static final sr a = new sr();

    public static final class a extends Lambda implements Function1<Byte, CharSequence> {
        public static final a a = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final CharSequence invoke(Byte b) {
            byte bByteValue = b.byteValue();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(bByteValue)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            return str;
        }
    }

    private sr() {
    }

    @Nullable
    public final PackageInfo a(@NotNull Context context, @NotNull String apkPath) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apkPath, "apkPath");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(a.f(context).getPackageArchiveInfo(apkPath, 1));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        return (PackageInfo) objM63constructorimpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0015  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(@org.jetbrains.annotations.Nullable android.content.pm.Signature[] r6, @org.jetbrains.annotations.NotNull java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "pkn"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            r1 = 0
            if (r6 == 0) goto L15
            int r2 = r6.length
            if (r2 != 0) goto Lf
            r2 = r0
            goto L10
        Lf:
            r2 = r1
        L10:
            if (r2 == 0) goto L13
            goto L15
        L13:
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            java.lang.String r3 = "1"
            if (r2 != r0) goto L1b
            goto L7a
        L1b:
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L4b
            r6 = r6[r1]     // Catch: java.lang.Throwable -> L4b
            byte[] r6 = r6.toByteArray()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch: java.lang.Throwable -> L4b
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L4b
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L4b
            java.security.cert.Certificate r6 = r0.generateCertificate(r2)     // Catch: java.lang.Throwable -> L4b
            if (r6 == 0) goto L43
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6     // Catch: java.lang.Throwable -> L4b
            java.math.BigInteger r6 = r6.getSerialNumber()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r6 = kotlin.Result.m63constructorimpl(r6)     // Catch: java.lang.Throwable -> L4b
            goto L56
        L43:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L4b
            java.lang.String r0 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L4b
            throw r6     // Catch: java.lang.Throwable -> L4b
        L4b:
            r6 = move-exception
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m63constructorimpl(r6)
        L56:
            java.lang.Throwable r0 = kotlin.Result.m66exceptionOrNullimpl(r6)
            if (r0 == 0) goto L6d
            java.lang.String r0 = r0.getMessage()
            java.lang.String r2 = "getAppSignature error msg:"
            java.lang.String r4 = " useDefault signature:1 pkn:"
            java.lang.String r0 = spi.d.g(r2, r0, r4, r7)
            java.lang.Object[] r2 = new java.lang.Object[r1]
            spi.ct.b(r0, r2)
        L6d:
            boolean r0 = kotlin.Result.m69isFailureimpl(r6)
            if (r0 == 0) goto L74
            r6 = 0
        L74:
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L79
            goto L7a
        L79:
            r3 = r6
        L7a:
            java.lang.String r6 = "getAppSignature signature:"
            java.lang.String r0 = " pkn:"
            java.lang.String r6 = spi.d.g(r6, r3, r0, r7)
            java.lang.Object[] r7 = new java.lang.Object[r1]
            spi.ct.a(r6, r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.sr.b(android.content.pm.Signature[], java.lang.String):java.lang.String");
    }

    @Nullable
    public final String c(@NotNull String packageName, @NotNull Context context) {
        Signature[] apkContentsSigners;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            PackageInfo packageInfo = f(context).getPackageInfo(packageName, 134217728);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "packageManager.getPackag…GET_SIGNING_CERTIFICATES)");
            SigningInfo signingInfo = packageInfo.signingInfo;
            if (signingInfo.hasMultipleSigners()) {
                apkContentsSigners = signingInfo.getApkContentsSigners();
                Intrinsics.checkNotNullExpressionValue(apkContentsSigners, "{\n                signin…entsSigners\n            }");
            } else {
                apkContentsSigners = signingInfo.getApkContentsSigners();
                Intrinsics.checkNotNullExpressionValue(apkContentsSigners, "{\n                signin…entsSigners\n            }");
            }
            byte[] hashBytes = MessageDigest.getInstance("SHA-256").digest(apkContentsSigners[0].toByteArray());
            Intrinsics.checkNotNullExpressionValue(hashBytes, "hashBytes");
            return ArraysKt___ArraysKt.joinToString$default(hashBytes, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) a.a, 30, (Object) null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final int d(@NotNull Context context) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(Integer.valueOf(a.f(context).getPackageInfo(context.getPackageName(), 0).versionCode));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m70isSuccessimpl(objM63constructorimpl)) {
            return 1;
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        Integer num = (Integer) objM63constructorimpl;
        if (num != null) {
            return num.intValue();
        }
        return 1;
    }

    @NotNull
    public final String e(@NotNull Context context) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(a.f(context).getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m70isSuccessimpl(objM63constructorimpl)) {
            return "";
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        String str = (String) objM63constructorimpl;
        return str == null ? "" : str;
    }

    @NotNull
    public final PackageManager f(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        return packageManager;
    }

    @Nullable
    public final ApplicationInfo g(@NotNull Context context, @NotNull String packageName) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(a.f(context).getApplicationInfo(packageName, 8192));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        return (ApplicationInfo) objM63constructorimpl;
    }

    @NotNull
    public final List<ResolveInfo> h(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManagerF = f(context);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> listQueryIntentActivities = packageManagerF.queryIntentActivities(intent, 131072);
        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "getPackageManager(contex…nager.MATCH_ALL\n        )");
        return listQueryIntentActivities;
    }

    @Nullable
    public final PackageInfo i(@NotNull Context context, @NotNull String packageName) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(a.f(context).getPackageInfo(packageName, 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        return (PackageInfo) objM63constructorimpl;
    }

    public final boolean j(@NotNull Context context, @NotNull String packageName) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(a.f(context).getPackageInfo(packageName, 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        return Result.m70isSuccessimpl(objM63constructorimpl);
    }

    public final boolean k(short s) {
        return s == 2;
    }
}
