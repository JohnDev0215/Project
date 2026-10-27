package org.apache.shiro.session.mgt;

import java.util.Collection;
import java.util.Date;
import org.apache.shiro.session.InvalidSessionException;

/* JADX INFO: loaded from: classes.dex */
public interface NativeSessionManager extends SessionManager {
    void checkValid(SessionKey sessionKey) throws InvalidSessionException;

    Object getAttribute(SessionKey sessionKey, Object obj) throws InvalidSessionException;

    Collection<Object> getAttributeKeys(SessionKey sessionKey);

    String getHost(SessionKey sessionKey);

    Date getLastAccessTime(SessionKey sessionKey);

    Date getStartTimestamp(SessionKey sessionKey);

    long getTimeout(SessionKey sessionKey) throws InvalidSessionException;

    boolean isValid(SessionKey sessionKey);

    Object removeAttribute(SessionKey sessionKey, Object obj) throws InvalidSessionException;

    void setAttribute(SessionKey sessionKey, Object obj, Object obj2) throws InvalidSessionException;

    void setTimeout(SessionKey sessionKey, long j) throws InvalidSessionException;

    void stop(SessionKey sessionKey) throws InvalidSessionException;

    void touch(SessionKey sessionKey) throws InvalidSessionException;
}
