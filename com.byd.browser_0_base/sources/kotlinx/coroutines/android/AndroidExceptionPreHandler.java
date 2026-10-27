package kotlinx.coroutines.android;

import androidx.annotation.Keep;
import defpackage.AbstractC0199n;
import defpackage.m00;
import defpackage.q00;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: AndroidExceptionPreHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Keep
public final class AndroidExceptionPreHandler extends AbstractC0199n implements q00 {
    private volatile Object _preHandler;

    public AndroidExceptionPreHandler() {
        super(q00.a.e);
        this._preHandler = this;
    }

    private final Method preHandler() {
        Object obj = this._preHandler;
        if (obj != this) {
            return (Method) obj;
        }
        Method method = null;
        try {
            boolean z = false;
            Method declaredMethod = Thread.class.getDeclaredMethod("getUncaughtExceptionPreHandler", new Class[0]);
            if (Modifier.isPublic(declaredMethod.getModifiers())) {
                if (Modifier.isStatic(declaredMethod.getModifiers())) {
                    z = true;
                }
            }
            if (z) {
                method = declaredMethod;
            }
        } catch (Throwable unused) {
        }
        this._preHandler = method;
        return method;
    }

    @Override // defpackage.q00
    public void handleException(m00 m00Var, Throwable th) {
    }
}
