package org.apache.shiro.mgt;

import org.apache.shiro.subject.Subject;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSessionStorageEvaluator implements SessionStorageEvaluator {
    private boolean sessionStorageEnabled = true;

    @Override // org.apache.shiro.mgt.SessionStorageEvaluator
    public boolean isSessionStorageEnabled(Subject subject) {
        return !(subject == null || subject.getSession(false) == null) || isSessionStorageEnabled();
    }

    public boolean isSessionStorageEnabled() {
        return this.sessionStorageEnabled;
    }

    public void setSessionStorageEnabled(boolean z) {
        this.sessionStorageEnabled = z;
    }
}
