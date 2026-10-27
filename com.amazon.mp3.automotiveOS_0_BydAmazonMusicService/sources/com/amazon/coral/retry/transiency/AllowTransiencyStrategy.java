package com.amazon.coral.retry.transiency;

import com.amazon.coral.retry.util.ClassList;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public class AllowTransiencyStrategy implements TransiencyStrategy {
    private final ClassList<Throwable> allowedExceptionCauseTypes;
    private final ClassList<Throwable> allowedExceptionTypes;

    public AllowTransiencyStrategy(Collection<Class<? extends Throwable>> collection, Collection<Class<? extends Throwable>> collection2) {
        this.allowedExceptionTypes = new ClassList<>(collection);
        this.allowedExceptionCauseTypes = new ClassList<>(collection2);
    }

    @Override // com.amazon.coral.retry.transiency.TransiencyStrategy
    public boolean isTransient(Exception exc) {
        return isAllowed(exc) || isCausalChainAllowed(exc);
    }

    private boolean isAllowed(Exception exc) {
        return this.allowedExceptionTypes.isInstanceOfAny(exc);
    }

    private boolean isCausalChainAllowed(Exception exc) {
        return this.allowedExceptionCauseTypes.isInstanceOfAny(ClassList.getCausalChain(exc));
    }
}
