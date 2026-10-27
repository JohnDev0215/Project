package org.apache.shiro.session.mgt;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public class DelegatingSession implements Session, Serializable {
    private final SessionKey key;
    private final transient NativeSessionManager sessionManager;
    private Date startTimestamp = null;
    private String host = null;

    public DelegatingSession(NativeSessionManager nativeSessionManager, SessionKey sessionKey) {
        if (nativeSessionManager == null) {
            throw new IllegalArgumentException("sessionManager argument cannot be null.");
        }
        if (sessionKey == null) {
            throw new IllegalArgumentException("sessionKey argument cannot be null.");
        }
        if (sessionKey.getSessionId() == null) {
            throw new IllegalArgumentException("The " + DelegatingSession.class.getName() + " implementation requires that the SessionKey argument returns a non-null sessionId to support the Session.getId() invocations.");
        }
        this.sessionManager = nativeSessionManager;
        this.key = sessionKey;
    }

    @Override // org.apache.shiro.session.Session
    public Serializable getId() {
        return this.key.getSessionId();
    }

    @Override // org.apache.shiro.session.Session
    public Date getStartTimestamp() {
        if (this.startTimestamp == null) {
            this.startTimestamp = this.sessionManager.getStartTimestamp(this.key);
        }
        return this.startTimestamp;
    }

    @Override // org.apache.shiro.session.Session
    public Date getLastAccessTime() {
        return this.sessionManager.getLastAccessTime(this.key);
    }

    @Override // org.apache.shiro.session.Session
    public long getTimeout() throws InvalidSessionException {
        return this.sessionManager.getTimeout(this.key);
    }

    @Override // org.apache.shiro.session.Session
    public void setTimeout(long j) throws InvalidSessionException {
        this.sessionManager.setTimeout(this.key, j);
    }

    @Override // org.apache.shiro.session.Session
    public String getHost() {
        if (this.host == null) {
            this.host = this.sessionManager.getHost(this.key);
        }
        return this.host;
    }

    @Override // org.apache.shiro.session.Session
    public void touch() throws InvalidSessionException {
        this.sessionManager.touch(this.key);
    }

    @Override // org.apache.shiro.session.Session
    public void stop() throws InvalidSessionException {
        this.sessionManager.stop(this.key);
    }

    @Override // org.apache.shiro.session.Session
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return this.sessionManager.getAttributeKeys(this.key);
    }

    @Override // org.apache.shiro.session.Session
    public Object getAttribute(Object obj) throws InvalidSessionException {
        return this.sessionManager.getAttribute(this.key, obj);
    }

    @Override // org.apache.shiro.session.Session
    public void setAttribute(Object obj, Object obj2) throws InvalidSessionException {
        if (obj2 == null) {
            removeAttribute(obj);
        } else {
            this.sessionManager.setAttribute(this.key, obj, obj2);
        }
    }

    @Override // org.apache.shiro.session.Session
    public Object removeAttribute(Object obj) throws InvalidSessionException {
        return this.sessionManager.removeAttribute(this.key, obj);
    }
}
