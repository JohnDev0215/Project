package org.apache.shiro.session.mgt;

import java.io.Serializable;
import java.util.Map;
import org.apache.shiro.util.MapContext;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSessionContext extends MapContext implements SessionContext {
    private static final String HOST = DefaultSessionContext.class.getName() + ".HOST";
    private static final String SESSION_ID = DefaultSessionContext.class.getName() + ".SESSION_ID";
    private static final long serialVersionUID = -1424160751361252966L;

    public DefaultSessionContext() {
    }

    public DefaultSessionContext(Map<String, Object> map) {
        super(map);
    }

    @Override // org.apache.shiro.session.mgt.SessionContext
    public String getHost() {
        return (String) getTypedValue(HOST, String.class);
    }

    @Override // org.apache.shiro.session.mgt.SessionContext
    public void setHost(String str) {
        if (StringUtils.hasText(str)) {
            put(HOST, (Object) str);
        }
    }

    @Override // org.apache.shiro.session.mgt.SessionContext
    public Serializable getSessionId() {
        return (Serializable) getTypedValue(SESSION_ID, Serializable.class);
    }

    @Override // org.apache.shiro.session.mgt.SessionContext
    public void setSessionId(Serializable serializable) {
        nullSafePut(SESSION_ID, serializable);
    }
}
