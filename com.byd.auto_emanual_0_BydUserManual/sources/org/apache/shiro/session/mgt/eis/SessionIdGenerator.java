package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public interface SessionIdGenerator {
    Serializable generateId(Session session);
}
