package p;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import java.lang.reflect.InvocationTargetException;
import kotlin.Result;

/* JADX INFO: loaded from: classes.dex */
public abstract class k11 {
    private static volatile Choreographer choreographer;

    static {
        Object objB;
        try {
            objB = new kotlinx.coroutines.android.a(a(Looper.getMainLooper()));
        } catch (Throwable th) {
            objB = kotlin.a.b(th);
        }
        if (objB instanceof Result.Failure) {
            objB = null;
        }
    }

    public static final Handler a(Looper looper) throws IllegalAccessException, InvocationTargetException {
        if (Build.VERSION.SDK_INT < 28) {
            try {
                return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
            } catch (NoSuchMethodException unused) {
                return new Handler(looper);
            }
        }
        Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        v03.g(objInvoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) objInvoke;
    }
}
