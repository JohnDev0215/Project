package org.apache.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class ProxiedSession implements Session {
    protected final Session delegate;

    public ProxiedSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Target session to proxy cannot be null.");
        }
        this.delegate = session;
    }

    @Override // org.apache.shiro.session.Session
    public Serializable getId() {
        return this.delegate.getId();
    }

    @Override // org.apache.shiro.session.Session
    public Date getStartTimestamp() {
        return this.delegate.getStartTimestamp();
    }

    @Override // org.apache.shiro.session.Session
    public Date getLastAccessTime() {
        return this.delegate.getLastAccessTime();
    }

    @Override // org.apache.shiro.session.Session
    public long getTimeout() throws InvalidSessionException {
        return this.delegate.getTimeout();
    }

    @Override // org.apache.shiro.session.Session
    public void setTimeout(long j) throws InvalidSessionException {
        this.delegate.setTimeout(j);
    }

    @Override // org.apache.shiro.session.Session
    public String getHost() {
        return this.delegate.getHost();
    }

    @Override // org.apache.shiro.session.Session
    public void touch() throws InvalidSessionException {
        this.delegate.touch();
    }

    @Override // org.apache.shiro.session.Session
    public void stop() throws InvalidSessionException {
        this.delegate.stop();
    }

    @Override // org.apache.shiro.session.Session
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return this.delegate.getAttributeKeys();
    }

    @Override // org.apache.shiro.session.Session
    public Object getAttribute(Object obj) throws InvalidSessionException {
        return this.delegate.getAttribute(obj);
    }

    @Override // org.apache.shiro.session.Session
    public void setAttribute(Object obj, Object obj2) throws InvalidSessionException {
        this.delegate.setAttribute(obj, obj2);
    }

    @Override // org.apache.shiro.session.Session
    public Object removeAttribute(Object obj) throws InvalidSessionException {
        return this.delegate.removeAttribute(obj);
    }
}
