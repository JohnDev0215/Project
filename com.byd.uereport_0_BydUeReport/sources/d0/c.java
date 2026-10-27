package d0;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: BundleCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: compiled from: BundleCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Method f4279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static boolean f4280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static Method f4281c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static boolean f4282d;

        public static IBinder a(Bundle bundle, String str) {
            if (!f4280b) {
                try {
                    Method method = Bundle.class.getMethod("getIBinder", String.class);
                    f4279a = method;
                    method.setAccessible(true);
                } catch (NoSuchMethodException e8) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", e8);
                }
                f4280b = true;
            }
            Method method2 = f4279a;
            if (method2 != null) {
                try {
                    return (IBinder) method2.invoke(bundle, str);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e9) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", e9);
                    f4279a = null;
                }
            }
            return null;
        }

        public static void b(Bundle bundle, String str, IBinder iBinder) {
            if (!f4282d) {
                try {
                    Method method = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
                    f4281c = method;
                    method.setAccessible(true);
                } catch (NoSuchMethodException e8) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", e8);
                }
                f4282d = true;
            }
            Method method2 = f4281c;
            if (method2 != null) {
                try {
                    method2.invoke(bundle, str, iBinder);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e9) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", e9);
                    f4281c = null;
                }
            }
        }
    }

    public static IBinder a(Bundle bundle, String str) {
        return Build.VERSION.SDK_INT >= 18 ? bundle.getBinder(str) : a.a(bundle, str);
    }

    public static void b(Bundle bundle, String str, IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 18) {
            bundle.putBinder(str, iBinder);
        } else {
            a.b(bundle, str, iBinder);
        }
    }
}
