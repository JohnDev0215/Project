package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import java.util.Collection;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;

/* JADX INFO: loaded from: classes.dex */
public interface SessionDAO {
    Serializable create(Session session);

    void delete(Session session);

    Collection<Session> getActiveSessions();

    Session readSession(Serializable serializable) throws UnknownSessionException;

    void update(Session session) throws UnknownSessionException;
}
