package org.apache.shiro.subject.support;

import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.util.ThreadState;

/* JADX INFO: loaded from: classes.dex */
public class SubjectThreadState implements ThreadState {
    private Map<Object, Object> originalResources;
    private transient SecurityManager securityManager;
    private final Subject subject;

    public SubjectThreadState(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject argument cannot be null.");
        }
        this.subject = subject;
        SecurityManager securityManager = subject instanceof DelegatingSubject ? ((DelegatingSubject) subject).getSecurityManager() : null;
        this.securityManager = securityManager == null ? ThreadContext.getSecurityManager() : securityManager;
    }

    protected Subject getSubject() {
        return this.subject;
    }

    @Override // org.apache.shiro.util.ThreadState
    public void bind() {
        SecurityManager securityManager = this.securityManager;
        if (securityManager == null) {
            securityManager = ThreadContext.getSecurityManager();
        }
        this.originalResources = ThreadContext.getResources();
        ThreadContext.remove();
        ThreadContext.bind(this.subject);
        if (securityManager != null) {
            ThreadContext.bind(securityManager);
        }
    }

    @Override // org.apache.shiro.util.ThreadState
    public void restore() {
        ThreadContext.remove();
        if (CollectionUtils.isEmpty(this.originalResources)) {
            return;
        }
        ThreadContext.setResources(this.originalResources);
    }

    @Override // org.apache.shiro.util.ThreadState
    public void clear() {
        ThreadContext.remove();
    }
}
