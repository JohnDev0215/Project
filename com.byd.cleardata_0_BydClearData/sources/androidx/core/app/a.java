package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class a extends androidx.core.content.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static c f1170c;

    /* JADX INFO: renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    class RunnableC0036a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String[] f1171b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f1172c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1173d;

        RunnableC0036a(String[] strArr, Activity activity, int i) {
            this.f1171b = strArr;
            this.f1172c = activity;
            this.f1173d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f1171b.length];
            PackageManager packageManager = this.f1172c.getPackageManager();
            String packageName = this.f1172c.getPackageName();
            int length = this.f1171b.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.f1171b[i], packageName);
            }
            ((b) this.f1172c).onRequestPermissionsResult(this.f1173d, this.f1171b, iArr);
        }
    }

    public interface b {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    public interface c {
        boolean a(Activity activity, String[] strArr, int i);

        boolean b(Activity activity, int i, int i2, Intent intent);
    }

    public interface d {
        void b(int i);
    }

    public static void h(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static c i() {
        return f1170c;
    }

    public static void j(Activity activity) {
        if (Build.VERSION.SDK_INT < 28 && androidx.core.app.b.i(activity)) {
            return;
        }
        activity.recreate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void k(Activity activity, String[] strArr, int i) {
        c cVar = f1170c;
        if (cVar == null || !cVar.a(activity, strArr, i)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof d) {
                    ((d) activity).b(i);
                }
                activity.requestPermissions(strArr, i);
            } else if (activity instanceof b) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0036a(strArr, activity, i));
            }
        }
    }

    public static boolean l(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public static void m(Activity activity, Intent intent, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i, bundle);
        } else {
            activity.startActivityForResult(intent, i);
        }
    }
}
