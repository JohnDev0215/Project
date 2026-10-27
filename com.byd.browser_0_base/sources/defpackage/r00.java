package defpackage;

import java.lang.Thread;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CoroutineExceptionHandlerImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class r00 {
    public static final List<q00> a;

    static {
        Iterator itO = C0175b.o();
        i21.e(itO, "<this>");
        ri2 ri2Var = new ri2(itO);
        boolean z = ri2Var instanceof mx;
        li2 mxVar = ri2Var;
        if (!z) {
            mxVar = new mx(ri2Var);
        }
        a = qi2.k0(mxVar);
    }

    public static final void a(m00 m00Var, Throwable th) {
        Throwable runtimeException;
        Iterator<q00> it = a.iterator();
        while (it.hasNext()) {
            try {
                it.next().handleException(m00Var, th);
            } catch (Throwable th2) {
                Thread threadCurrentThread = Thread.currentThread();
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = threadCurrentThread.getUncaughtExceptionHandler();
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    n3.l(runtimeException, th);
                }
                uncaughtExceptionHandler.uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        try {
            n3.l(th, new q90(m00Var));
            vi3 vi3Var = vi3.a;
        } catch (Throwable th3) {
            a8.v(th3);
        }
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }
}
