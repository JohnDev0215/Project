package defpackage;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.TransactionTooLargeException;
import android.util.Log;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: PackageManagerUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class wt1 {
    public static final Intent a = new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.fromParts("http", "", null));

    public static boolean a(Intent intent, int i) {
        return !c(intent, i).isEmpty();
    }

    public static void b(RuntimeException runtimeException, Intent intent) {
        if (!(runtimeException instanceof NullPointerException) && !(runtimeException.getCause() instanceof TransactionTooLargeException)) {
            throw runtimeException;
        }
        Log.e("cr_PackageManagerUtils", "Could not resolve Activity for intent " + intent.toString(), runtimeException);
    }

    public static List<ResolveInfo> c(Intent intent, int i) {
        try {
            ku2 ku2VarT = ku2.t();
            try {
                List<ResolveInfo> listQueryIntentActivities = xz.a.getPackageManager().queryIntentActivities(intent, i);
                ku2VarT.close();
                return listQueryIntentActivities;
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (RuntimeException e) {
            b(e, intent);
            return Collections.emptyList();
        }
    }

    public static ResolveInfo d(Intent intent, int i) {
        try {
            ku2 ku2VarC = ku2.C();
            try {
                ResolveInfo resolveInfoResolveActivity = xz.a.getPackageManager().resolveActivity(intent, i);
                ku2VarC.close();
                return resolveInfoResolveActivity;
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (RuntimeException e) {
            b(e, intent);
            return null;
        }
    }

    public static ResolveInfo e() {
        return d(a, 65536);
    }
}
