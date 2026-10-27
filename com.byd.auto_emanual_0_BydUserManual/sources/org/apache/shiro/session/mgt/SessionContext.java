package org.apache.shiro.session.mgt;

import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface SessionContext extends Map<String, Object> {
    String getHost();

    Serializable getSessionId();

    void setHost(String str);

    void setSessionId(Serializable serializable);
}
