package org.apache.shiro.subject.support;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadState;

/* JADX INFO: loaded from: classes.dex */
public class SubjectRunnable implements Runnable {
    private final Runnable runnable;
    protected final ThreadState threadState;

    public SubjectRunnable(Subject subject, Runnable runnable) {
        this(new SubjectThreadState(subject), runnable);
    }

    protected SubjectRunnable(ThreadState threadState, Runnable runnable) throws IllegalArgumentException {
        if (threadState == null) {
            throw new IllegalArgumentException("ThreadState argument cannot be null.");
        }
        this.threadState = threadState;
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable argument cannot be null.");
        }
        this.runnable = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.threadState.bind();
            doRun(this.runnable);
        } finally {
            this.threadState.restore();
        }
    }

    protected void doRun(Runnable runnable) {
        runnable.run();
    }
}
