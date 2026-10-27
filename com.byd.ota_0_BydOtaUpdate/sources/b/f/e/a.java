package b.f.e;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: compiled from: ActivityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b.f.f.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static InterfaceC0019a f1559c;

    /* JADX INFO: renamed from: b.f.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface InterfaceC0019a {
        boolean a(Activity activity, int i2, int i3, Intent intent);
    }

    public static void g(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static InterfaceC0019a h() {
        return f1559c;
    }

    public static void i(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (b.i(activity)) {
                return;
            }
            activity.recreate();
        }
    }
}
