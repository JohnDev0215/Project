package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: ActivityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends androidx.core.content.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static c f1353c;

    /* JADX INFO: renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityCompat.java */
    class RunnableC0040a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String[] f1354b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f1355c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1356d;

        RunnableC0040a(String[] strArr, Activity activity, int i) {
            this.f1354b = strArr;
            this.f1355c = activity;
            this.f1356d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f1354b.length];
            PackageManager packageManager = this.f1355c.getPackageManager();
            String packageName = this.f1355c.getPackageName();
            int length = this.f1354b.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.f1354b[i], packageName);
            }
            ((b) this.f1355c).onRequestPermissionsResult(this.f1356d, this.f1354b, iArr);
        }
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface b {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface c {
        boolean a(Activity activity, int i, int i2, Intent intent);

        boolean b(Activity activity, String[] strArr, int i);
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface d {
        void b(int i);
    }

    public static void i(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static c j() {
        return f1353c;
    }

    public static void k(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (androidx.core.app.b.i(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void l(Activity activity, String[] strArr, int i) {
        c cVar = f1353c;
        if (cVar == null || !cVar.b(activity, strArr, i)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof d) {
                    ((d) activity).b(i);
                }
                activity.requestPermissions(strArr, i);
            } else if (activity instanceof b) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0040a(strArr, activity, i));
            }
        }
    }
}
