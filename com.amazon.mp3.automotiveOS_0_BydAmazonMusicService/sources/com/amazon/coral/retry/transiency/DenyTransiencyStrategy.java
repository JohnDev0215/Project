package com.amazon.coral.retry.transiency;

import com.amazon.coral.retry.util.ClassList;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public class DenyTransiencyStrategy implements TransiencyStrategy {
    private ClassList<Throwable> classList;

    public DenyTransiencyStrategy(Collection<Class<? extends Throwable>> collection) {
        this.classList = new ClassList<>(collection);
    }

    @Override // com.amazon.coral.retry.transiency.TransiencyStrategy
    public boolean isTransient(Exception exc) {
        return !this.classList.isInstanceOfAny(exc);
    }
}
