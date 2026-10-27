package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: ActivityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends androidx.core.content.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static InterfaceC0013a f1183c;

    /* JADX INFO: renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface InterfaceC0013a {
        boolean a(Activity activity, int i2, int i3, Intent intent);
    }

    public static void h(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }

    public static InterfaceC0013a i() {
        return f1183c;
    }

    public static void j(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (c.i(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    public static <T extends View> T k(Activity activity, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) activity.requireViewById(i2);
        }
        T t = (T) activity.findViewById(i2);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Activity");
    }
}
