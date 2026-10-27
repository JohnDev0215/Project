package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class A {
    public static final void a(Throwable th) throws IllegalAccessException, InvocationTargetException {
        try {
            RunnableC0216w runnableC0216w = C0218x.b;
            AccessibilityManagerTouchExplorationStateChangeListenerC0222z.a(th);
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                T.a(runtimeException, th);
                th = runtimeException;
            }
            AccessibilityManagerTouchExplorationStateChangeListenerC0222z.a(th);
        }
    }
}
