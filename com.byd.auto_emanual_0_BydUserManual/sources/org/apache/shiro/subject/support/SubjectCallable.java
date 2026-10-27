package org.apache.shiro.subject.support;

import java.util.concurrent.Callable;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadState;

/* JADX INFO: loaded from: classes.dex */
public class SubjectCallable<V> implements Callable<V> {
    private final Callable<V> callable;
    protected final ThreadState threadState;

    public SubjectCallable(Subject subject, Callable<V> callable) {
        this(new SubjectThreadState(subject), callable);
    }

    protected SubjectCallable(ThreadState threadState, Callable<V> callable) {
        if (threadState == null) {
            throw new IllegalArgumentException("ThreadState argument cannot be null.");
        }
        this.threadState = threadState;
        if (callable == null) {
            throw new IllegalArgumentException("Callable delegate instance cannot be null.");
        }
        this.callable = callable;
    }

    @Override // java.util.concurrent.Callable
    public V call() throws Exception {
        try {
            this.threadState.bind();
            return doCall(this.callable);
        } finally {
            this.threadState.restore();
        }
    }

    protected V doCall(Callable<V> callable) throws Exception {
        return callable.call();
    }
}
