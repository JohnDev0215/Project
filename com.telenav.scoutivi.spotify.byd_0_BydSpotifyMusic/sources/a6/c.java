package a6;

import android.os.Handler;
import android.os.Looper;
import g5.l;
import g5.m;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* JADX INFO: compiled from: HandlerDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f139a;

    static {
        Object objA;
        try {
            l.a aVar = l.f6557e;
            objA = l.a(new a(a(Looper.getMainLooper(), true), null, 2, null));
        } catch (Throwable th) {
            l.a aVar2 = l.f6557e;
            objA = l.a(m.a(th));
        }
        f139a = (b) (l.c(objA) ? null : objA);
    }

    public static final Handler a(Looper looper, boolean z6) throws IllegalAccessException, InvocationTargetException {
        if (!z6) {
            return new Handler(looper);
        }
        Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        Objects.requireNonNull(objInvoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) objInvoke;
    }
}
