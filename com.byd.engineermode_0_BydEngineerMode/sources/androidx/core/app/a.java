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
    private static c f824c;

    /* JADX INFO: renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityCompat.java */
    class RunnableC0012a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String[] f825b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f826c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f827d;

        RunnableC0012a(String[] strArr, Activity activity, int i) {
            this.f825b = strArr;
            this.f826c = activity;
            this.f827d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f825b.length];
            PackageManager packageManager = this.f826c.getPackageManager();
            String packageName = this.f826c.getPackageName();
            int length = this.f825b.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.f825b[i], packageName);
            }
            ((b) this.f826c).onRequestPermissionsResult(this.f827d, this.f825b, iArr);
        }
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface b {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface c {
        boolean a(Activity activity, String[] strArr, int i);

        boolean b(Activity activity, int i, int i2, Intent intent);
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
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
        return f824c;
    }

    public static void j(Activity activity) {
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
    public static void k(Activity activity, String[] strArr, int i) {
        c cVar = f824c;
        if (cVar == null || !cVar.a(activity, strArr, i)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof d) {
                    ((d) activity).b(i);
                }
                activity.requestPermissions(strArr, i);
            } else if (activity instanceof b) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0012a(strArr, activity, i));
            }
        }
    }
}
