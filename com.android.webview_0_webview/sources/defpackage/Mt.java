package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.chromium.base.BuildInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Mt {
    public static Method a;

    public static boolean a(Context context, Intent intent, ServiceConnection serviceConnection, int i, Handler handler) {
        if (a == null) {
            a = Context.class.getDeclaredMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, Handler.class, UserHandle.class);
        }
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return ((Boolean) a.invoke(context, intent, serviceConnection, Integer.valueOf(i), handler, Process.myUserHandle())).booleanValue();
    }

    public static boolean b(Context context, Intent intent, ServiceConnection serviceConnection, int i, Handler handler, Executor executor, String str) {
        if (c() && str != null) {
            return C1514st.a(context, intent, i, str, executor, serviceConnection);
        }
        if (Build.VERSION.SDK_INT < 24) {
            return context.bindService(intent, serviceConnection, i);
        }
        try {
            return a(context, intent, serviceConnection, i, handler);
        } catch (ReflectiveOperationException e) {
            try {
                return context.bindService(intent, serviceConnection, i);
            } catch (RuntimeException e2) {
                throw new RuntimeException(e2.getMessage(), e);
            }
        }
    }

    public static boolean c() {
        return BuildInfo.a();
    }
}
