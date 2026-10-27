package org.apache.shiro.session.mgt;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.ProxiedSession;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public class ImmutableProxiedSession extends ProxiedSession {
    public ImmutableProxiedSession(Session session) {
        super(session);
    }

    protected void throwImmutableException() throws InvalidSessionException {
        throw new InvalidSessionException("This session is immutable and read-only - it cannot be altered.  This is usually because the session has been stopped or expired already.");
    }

    @Override // org.apache.shiro.session.ProxiedSession, org.apache.shiro.session.Session
    public void setTimeout(long j) throws InvalidSessionException {
        throwImmutableException();
    }

    @Override // org.apache.shiro.session.ProxiedSession, org.apache.shiro.session.Session
    public void touch() throws InvalidSessionException {
        throwImmutableException();
    }

    @Override // org.apache.shiro.session.ProxiedSession, org.apache.shiro.session.Session
    public void stop() throws InvalidSessionException {
        throwImmutableException();
    }

    @Override // org.apache.shiro.session.ProxiedSession, org.apache.shiro.session.Session
    public void setAttribute(Object obj, Object obj2) throws InvalidSessionException {
        throwImmutableException();
    }

    @Override // org.apache.shiro.session.ProxiedSession, org.apache.shiro.session.Session
    public Object removeAttribute(Object obj) throws InvalidSessionException {
        throwImmutableException();
        throw new InternalError("This code should never execute - please report this as a bug!");
    }
}
