package T8;

import A7.m;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import h8.AbstractC1386c;
import java.lang.reflect.InvocationTargetException;
import m7.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f8933a = 0;
    private static volatile Choreographer choreographer;

    static {
        Object objF;
        try {
            objF = new d(a(Looper.getMainLooper()));
        } catch (Throwable th) {
            objF = AbstractC1386c.f(th);
        }
        if (objF instanceof k) {
            objF = null;
        }
    }

    public static final Handler a(Looper looper) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        m.d(objInvoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) objInvoke;
    }
}
