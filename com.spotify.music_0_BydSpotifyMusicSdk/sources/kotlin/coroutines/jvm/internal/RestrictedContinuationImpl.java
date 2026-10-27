package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.EmptyCoroutineContext;
import p.c00;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class RestrictedContinuationImpl extends BaseContinuationImpl {
    public RestrictedContinuationImpl(c00 c00Var) {
        super(c00Var);
        if (c00Var != null && c00Var.getContext() != EmptyCoroutineContext.c) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext".toString());
        }
    }

    @Override // p.c00
    public final y10 getContext() {
        return EmptyCoroutineContext.c;
    }
}
