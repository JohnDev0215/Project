package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import java.util.UUID;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public class JavaUuidSessionIdGenerator implements SessionIdGenerator {
    @Override // org.apache.shiro.session.mgt.eis.SessionIdGenerator
    public Serializable generateId(Session session) {
        return UUID.randomUUID().toString();
    }
}
