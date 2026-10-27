package org.apache.shiro.session.mgt;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSessionKey implements SessionKey, Serializable {
    private Serializable sessionId;

    public DefaultSessionKey() {
    }

    public DefaultSessionKey(Serializable serializable) {
        this.sessionId = serializable;
    }

    public void setSessionId(Serializable serializable) {
        this.sessionId = serializable;
    }

    @Override // org.apache.shiro.session.mgt.SessionKey
    public Serializable getSessionId() {
        return this.sessionId;
    }
}
