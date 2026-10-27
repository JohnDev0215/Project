package p;

import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.flow.internal.AbortFlowException;

/* JADX INFO: loaded from: classes.dex */
public final class hv0 implements fv0 {
    public final /* synthetic */ Ref$ObjectRef c;

    public hv0(Ref$ObjectRef ref$ObjectRef) {
        this.c = ref$ObjectRef;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        this.c.element = obj;
        throw new AbortFlowException(this);
    }
}
