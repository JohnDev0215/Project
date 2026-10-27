package p;

import io.reactivex.rxjava3.core.ObservableEmitter;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: loaded from: classes.dex */
public final class jv0 implements fv0 {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ jv0(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        lq2 lq2Var = lq2.a;
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                Object objB = ((com.apollographql.apollo3.internal.a) obj2).b(obj, c00Var);
                return objB == coroutineSingletons ? objB : lq2Var;
            case 1:
                Ref$ObjectRef ref$ObjectRef = (Ref$ObjectRef) obj2;
                if (ref$ObjectRef.element != qo1.a) {
                    throw new IllegalArgumentException("Flow has more than one element".toString());
                }
                ref$ObjectRef.element = obj;
                return lq2Var;
            case 2:
                kotlinx.coroutines.reactive.b bVar = (kotlinx.coroutines.reactive.b) obj2;
                bVar.m.onNext(obj);
                if (kotlinx.coroutines.reactive.b.n.decrementAndGet(bVar) > 0) {
                    zu.a0(bVar.k);
                    return lq2Var;
                }
                zp zpVar = new zp(1, vr2.k(c00Var));
                zpVar.r();
                kotlinx.coroutines.reactive.b.o.set(bVar, zpVar);
                Object objQ = zpVar.q();
                return objQ == coroutineSingletons ? objQ : lq2Var;
            default:
                ((ObservableEmitter) obj2).onNext(obj);
                return lq2Var;
        }
    }
}
