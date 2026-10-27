package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: DispatchedTask.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class va0<T> extends v63 {
    public int g = -1;

    public abstract b00<T> d();

    public Throwable e(Object obj) {
        rv rvVar = obj instanceof rv ? (rv) obj : null;
        if (rvVar == null) {
            return null;
        }
        return rvVar.a;
    }

    public final void g(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            n3.l(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        i21.b(th);
        a8.H(d().getContext(), new v00("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object h();

    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r12 = this;
            w63 r0 = r12.f
            b00 r1 = r12.d()     // Catch: java.lang.Throwable -> L8f
            ta0 r1 = (defpackage.ta0) r1     // Catch: java.lang.Throwable -> L8f
            b00<T> r2 = r1.i     // Catch: java.lang.Throwable -> L8f
            java.lang.Object r1 = r1.k     // Catch: java.lang.Throwable -> L8f
            m00 r3 = r2.getContext()     // Catch: java.lang.Throwable -> L8f
            java.lang.Object r1 = defpackage.v83.c(r3, r1)     // Catch: java.lang.Throwable -> L8f
            a41 r4 = defpackage.v83.a     // Catch: java.lang.Throwable -> L8f
            r5 = 0
            if (r1 == r4) goto L1e
            gi3 r4 = defpackage.n3.s1(r2, r3)     // Catch: java.lang.Throwable -> L8f
            goto L1f
        L1e:
            r4 = r5
        L1f:
            m00 r6 = r2.getContext()     // Catch: java.lang.Throwable -> L5a
            java.lang.Object r7 = r12.h()     // Catch: java.lang.Throwable -> L5a
            java.lang.Throwable r8 = r12.e(r7)     // Catch: java.lang.Throwable -> L5a
            if (r8 != 0) goto L42
            int r9 = r12.g     // Catch: java.lang.Throwable -> L5a
            r10 = 1
            if (r9 == r10) goto L37
            r11 = 2
            if (r9 != r11) goto L36
            goto L37
        L36:
            r10 = 0
        L37:
            if (r10 == 0) goto L42
            m31$a r9 = m31.a.e     // Catch: java.lang.Throwable -> L5a
            m00$b r6 = r6.get(r9)     // Catch: java.lang.Throwable -> L5a
            m31 r6 = (defpackage.m31) r6     // Catch: java.lang.Throwable -> L5a
            goto L43
        L42:
            r6 = r5
        L43:
            if (r6 == 0) goto L5c
            boolean r9 = r6.isActive()     // Catch: java.lang.Throwable -> L5a
            if (r9 != 0) goto L5c
            java.util.concurrent.CancellationException r6 = r6.R()     // Catch: java.lang.Throwable -> L5a
            r12.b(r7, r6)     // Catch: java.lang.Throwable -> L5a
            vb2$a r6 = defpackage.a8.v(r6)     // Catch: java.lang.Throwable -> L5a
            r2.c(r6)     // Catch: java.lang.Throwable -> L5a
            goto L6d
        L5a:
            r2 = move-exception
            goto L88
        L5c:
            if (r8 == 0) goto L66
            vb2$a r6 = defpackage.a8.v(r8)     // Catch: java.lang.Throwable -> L5a
            r2.c(r6)     // Catch: java.lang.Throwable -> L5a
            goto L6d
        L66:
            java.lang.Object r6 = r12.f(r7)     // Catch: java.lang.Throwable -> L5a
            r2.c(r6)     // Catch: java.lang.Throwable -> L5a
        L6d:
            vi3 r2 = defpackage.vi3.a     // Catch: java.lang.Throwable -> L5a
            if (r4 != 0) goto L87
            defpackage.v83.a(r3, r1)     // Catch: java.lang.Throwable -> L8f
            r0.a()     // Catch: java.lang.Throwable -> L7a
            vi3 r0 = defpackage.vi3.a     // Catch: java.lang.Throwable -> L7a
            goto L7f
        L7a:
            r0 = move-exception
            vb2$a r0 = defpackage.a8.v(r0)
        L7f:
            java.lang.Throwable r0 = defpackage.vb2.a(r0)
            r12.g(r5, r0)
            goto La2
        L87:
            throw r5     // Catch: java.lang.Throwable -> L8f
        L88:
            if (r4 == 0) goto L8b
            throw r5     // Catch: java.lang.Throwable -> L8f
        L8b:
            defpackage.v83.a(r3, r1)     // Catch: java.lang.Throwable -> L8f
            throw r2     // Catch: java.lang.Throwable -> L8f
        L8f:
            r1 = move-exception
            r0.a()     // Catch: java.lang.Throwable -> L96
            vi3 r0 = defpackage.vi3.a     // Catch: java.lang.Throwable -> L96
            goto L9b
        L96:
            r0 = move-exception
            vb2$a r0 = defpackage.a8.v(r0)
        L9b:
            java.lang.Throwable r0 = defpackage.vb2.a(r0)
            r12.g(r1, r0)
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.va0.run():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T f(Object obj) {
        return obj;
    }

    public void b(Object obj, CancellationException cancellationException) {
    }
}
