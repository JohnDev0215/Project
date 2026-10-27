package org.apache.shiro.concurrent;

import java.util.Objects;
import java.util.concurrent.Executor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/* JADX INFO: loaded from: classes.dex */
public class SubjectAwareExecutor implements Executor {
    private Executor targetExecutor;

    public SubjectAwareExecutor() {
    }

    public SubjectAwareExecutor(Executor executor) {
        Objects.requireNonNull(executor, "target Executor instance cannot be null.");
        this.targetExecutor = executor;
    }

    public Executor getTargetExecutor() {
        return this.targetExecutor;
    }

    public void setTargetExecutor(Executor executor) {
        this.targetExecutor = executor;
    }

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected Runnable associateWithSubject(Runnable runnable) {
        return getSubject().associateWith(runnable);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        getTargetExecutor().execute(associateWithSubject(runnable));
    }
}
