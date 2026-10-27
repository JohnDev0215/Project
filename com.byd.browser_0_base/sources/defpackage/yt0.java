package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import defpackage.vb2;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: HandlerDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class yt0 {
    private static volatile Choreographer choreographer;

    static {
        Object objV;
        try {
            objV = new wt0(a(Looper.getMainLooper()));
        } catch (Throwable th) {
            objV = a8.v(th);
        }
        if (objV instanceof vb2.a) {
            objV = null;
        }
    }

    public static final Handler a(Looper looper) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        if (objInvoke != null) {
            return (Handler) objInvoke;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.os.Handler");
    }
}
