package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class q31 extends tv implements gb0, lz0 {
    public r31 h;

    @Override // defpackage.lz0
    public final yn1 b() {
        return null;
    }

    @Override // defpackage.gb0
    public final void f() {
        boolean z;
        r31 r31VarM = m();
        do {
            Object objN = r31VarM.n();
            if (!(objN instanceof q31)) {
                if (!(objN instanceof lz0) || ((lz0) objN).b() == null) {
                    return;
                }
                k();
                return;
            }
            if (objN != this) {
                return;
            }
            tg0 tg0Var = n3.s;
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = r31.e;
                if (atomicReferenceFieldUpdater.compareAndSet(r31VarM, objN, tg0Var)) {
                    z = true;
                    break;
                } else if (atomicReferenceFieldUpdater.get(r31VarM) != objN) {
                    z = false;
                    break;
                }
            }
        } while (!z);
    }

    @Override // defpackage.lz0
    public final boolean isActive() {
        return true;
    }

    public final r31 m() {
        r31 r31Var = this.h;
        if (r31Var != null) {
            return r31Var;
        }
        ti3 ti3Var = new ti3("lateinit property job has not been initialized");
        i21.f(i21.class.getName(), ti3Var);
        throw ti3Var;
    }

    @Override // kotlinx.coroutines.internal.a
    public final String toString() {
        return getClass().getSimpleName() + '@' + d70.d(this) + "[job@" + d70.d(m()) + ']';
    }
}
