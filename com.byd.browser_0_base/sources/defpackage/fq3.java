package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.StrictMode;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: WebApkValidator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fq3 {
    public static byte[] a;
    public static byte[] b;
    public static PublicKey c;
    public static boolean d;

    public static boolean a(Context context, String str, String str2) {
        Iterator<ResolveInfo> it = e(context, str2, str).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && c(context, activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static ResolveInfo b(Context context, List<ResolveInfo> list) {
        for (ResolveInfo resolveInfo : list) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && c(context, activityInfo.packageName)) {
                return resolveInfo;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x013d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:143:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(android.content.Context r17, java.lang.String r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fq3.c(android.content.Context, java.lang.String):boolean");
    }

    public static String d(Context context, String str) {
        ResolveInfo resolveInfoB = b(context, e(context, str, null));
        if (resolveInfoB != null) {
            return resolveInfoB.activityInfo.packageName;
        }
        return null;
    }

    public static List<ResolveInfo> e(Context context, String str, String str2) {
        Intent intent = null;
        try {
            Intent uri = Intent.parseUri(str, 1);
            uri.addCategory("android.intent.category.BROWSABLE");
            if (str2 != null) {
                uri.setPackage(str2);
            } else {
                uri.setComponent(null);
            }
            Intent selector = uri.getSelector();
            if (selector != null) {
                selector.addCategory("android.intent.category.BROWSABLE");
                selector.setComponent(null);
            }
            intent = uri;
        } catch (Exception unused) {
        }
        if (intent == null) {
            return new LinkedList();
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return context.getPackageManager().queryIntentActivities(intent, 64);
        } catch (Exception unused2) {
            return new LinkedList();
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static boolean f(PackageInfo packageInfo, String str) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null && signatureArr.length == 2 && str.startsWith("org.chromium.webapk")) {
            for (Signature signature : packageInfo.signatures) {
                if (Arrays.equals(a, signature.toByteArray())) {
                    return true;
                }
            }
        }
        return false;
    }
}
