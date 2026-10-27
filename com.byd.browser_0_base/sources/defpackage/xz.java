package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.preference.PreferenceManager;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: ContextUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class xz {
    public static Context a;

    /* JADX INFO: compiled from: ContextUtils.java */
    public static class a {
        public static final SharedPreferences a;

        static {
            ku2 ku2VarC = ku2.C();
            try {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(xz.a);
                ku2VarC.close();
                a = defaultSharedPreferences;
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
    }

    public static Activity a(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static void b(boolean z) {
        try {
            Activity activityD = d();
            int i = 0;
            boolean z2 = activityD == null || activityD.isInMultiWindowMode();
            Iterator it = ApplicationStatus.b().iterator();
            while (it.hasNext()) {
                ((Activity) it.next()).finish();
            }
            Log.w("ContextUtils", "exitApp: Activity finish");
            if (z) {
                new Handler(Looper.getMainLooper()).postDelayed(new wz(i), z2 ? 200L : 0L);
            }
        } catch (Throwable th) {
            System.exit(-1);
            Log.e("ContextUtils", "exitApp: Throwable  System.exit(-1); " + th);
        }
    }

    public static Context c() {
        return a;
    }

    public static Activity d() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            for (Object obj : ((Map) declaredField.get(objInvoke)).values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new uv("ClassNotFoundException failed", e);
        } catch (IllegalAccessException e2) {
            throw new uv("IllegalAccessException failed", e2);
        } catch (NoSuchFieldException e3) {
            throw new uv("NoSuchFieldException failed", e3);
        } catch (NoSuchMethodException e4) {
            throw new uv("NoSuchMethodException failed", e4);
        } catch (InvocationTargetException e5) {
            throw new uv("InvocationTargetException failed", e5);
        }
    }

    public static boolean e() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Process.isSdkSandbox();
        }
        return false;
    }

    public static void f(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        context.registerReceiver(broadcastReceiver, intentFilter, null, null, 4);
    }

    public static Intent g(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        return context.registerReceiver(broadcastReceiver, intentFilter, null, null, 0);
    }
}
