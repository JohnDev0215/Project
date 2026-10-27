package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: BindService.java */
/* JADX INFO: loaded from: classes.dex */
public final class qe {
    public static Method a;

    @SuppressLint({"DiscouragedPrivateApi"})
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
        if (str != null) {
            return context.bindIsolatedService(intent, i, str, executor, serviceConnection);
        }
        try {
            return a(context, intent, serviceConnection, i, handler);
        } catch (ReflectiveOperationException e) {
            try {
                return context.bindService(intent, serviceConnection, i);
            } catch (RuntimeException e2) {
                throw new uv(e2.getMessage(), e);
            }
        }
    }
}
