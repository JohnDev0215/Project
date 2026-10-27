package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class MemorySessionDAO extends AbstractSessionDAO {
    private static final Logger log = LoggerFactory.getLogger(MemorySessionDAO.class);
    private ConcurrentMap<Serializable, Session> sessions = new ConcurrentHashMap();

    @Override // org.apache.shiro.session.mgt.eis.AbstractSessionDAO
    protected Serializable doCreate(Session session) {
        Serializable serializableGenerateSessionId = generateSessionId(session);
        assignSessionId(session, serializableGenerateSessionId);
        storeSession(serializableGenerateSessionId, session);
        return serializableGenerateSessionId;
    }

    protected Session storeSession(Serializable serializable, Session session) {
        Objects.requireNonNull(serializable, "id argument cannot be null.");
        return this.sessions.putIfAbsent(serializable, session);
    }

    @Override // org.apache.shiro.session.mgt.eis.AbstractSessionDAO
    protected Session doReadSession(Serializable serializable) {
        return this.sessions.get(serializable);
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public void update(Session session) throws UnknownSessionException {
        storeSession(session.getId(), session);
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public void delete(Session session) {
        Objects.requireNonNull(session, "session argument cannot be null.");
        Serializable id = session.getId();
        if (id != null) {
            this.sessions.remove(id);
        }
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public Collection<Session> getActiveSessions() {
        Collection<Session> collectionValues = this.sessions.values();
        if (CollectionUtils.isEmpty(collectionValues)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableCollection(collectionValues);
    }
}
