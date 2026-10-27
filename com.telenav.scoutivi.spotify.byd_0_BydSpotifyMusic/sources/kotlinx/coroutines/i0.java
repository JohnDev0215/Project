package kotlinx.coroutines;

/* JADX INFO: compiled from: CoroutineScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i0 {
    public static final <R> Object a(r5.p<? super h0, ? super k5.d<? super R>, ? extends Object> pVar, k5.d<? super R> dVar) {
        kotlinx.coroutines.internal.y yVar = new kotlinx.coroutines.internal.y(dVar.getContext(), dVar);
        Object objE = b6.b.e(yVar, yVar, pVar);
        if (objE == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objE;
    }
}
