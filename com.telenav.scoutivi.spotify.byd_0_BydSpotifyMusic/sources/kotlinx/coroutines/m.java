package kotlinx.coroutines;

/* JADX INFO: compiled from: CancellableContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final void a(i<?> iVar, v0 v0Var) {
        iVar.d(new w0(v0Var));
    }

    public static final <T> k<T> b(k5.d<? super T> dVar) {
        if (!(dVar instanceof kotlinx.coroutines.internal.h)) {
            return new k<>(dVar, 2);
        }
        k<T> kVarL = ((kotlinx.coroutines.internal.h) dVar).l();
        if (kVarL != null) {
            if (!kVarL.H()) {
                kVarL = null;
            }
            if (kVarL != null) {
                return kVarL;
            }
        }
        return new k<>(dVar, 2);
    }

    public static final void c(i<?> iVar, kotlinx.coroutines.internal.o oVar) {
        iVar.d(new a2(oVar));
    }
}
