package org.apache.shiro.session.mgt;

import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public class SimpleSessionFactory implements SessionFactory {
    @Override // org.apache.shiro.session.mgt.SessionFactory
    public Session createSession(SessionContext sessionContext) {
        String host;
        if (sessionContext != null && (host = sessionContext.getHost()) != null) {
            return new SimpleSession(host);
        }
        return new SimpleSession();
    }
}
