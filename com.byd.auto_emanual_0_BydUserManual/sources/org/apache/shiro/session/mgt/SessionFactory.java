package org.apache.shiro.session.mgt;

import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public interface SessionFactory {
    Session createSession(SessionContext sessionContext);
}
