package org.apache.shiro.session.mgt;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;

/* JADX INFO: loaded from: classes.dex */
public interface SessionManager {
    Session getSession(SessionKey sessionKey) throws SessionException;

    Session start(SessionContext sessionContext);
}
