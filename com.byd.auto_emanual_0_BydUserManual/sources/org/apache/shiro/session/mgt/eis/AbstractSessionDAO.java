package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import org.apache.shiro.config.Ini;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSessionDAO implements SessionDAO {
    private SessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();

    protected abstract Serializable doCreate(Session session);

    protected abstract Session doReadSession(Serializable serializable);

    public SessionIdGenerator getSessionIdGenerator() {
        return this.sessionIdGenerator;
    }

    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        this.sessionIdGenerator = sessionIdGenerator;
    }

    protected Serializable generateSessionId(Session session) {
        SessionIdGenerator sessionIdGenerator = this.sessionIdGenerator;
        if (sessionIdGenerator == null) {
            throw new IllegalStateException("sessionIdGenerator attribute has not been configured.");
        }
        return sessionIdGenerator.generateId(session);
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public Serializable create(Session session) {
        Serializable serializableDoCreate = doCreate(session);
        verifySessionId(serializableDoCreate);
        return serializableDoCreate;
    }

    private void verifySessionId(Serializable serializable) {
        if (serializable == null) {
            throw new IllegalStateException("sessionId returned from doCreate implementation is null.  Please verify the implementation.");
        }
    }

    protected void assignSessionId(Session session, Serializable serializable) {
        ((SimpleSession) session).setId(serializable);
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public Session readSession(Serializable serializable) throws UnknownSessionException {
        Session sessionDoReadSession = doReadSession(serializable);
        if (sessionDoReadSession != null) {
            return sessionDoReadSession;
        }
        throw new UnknownSessionException("There is no session with id [" + serializable + Ini.SECTION_SUFFIX);
    }
}
