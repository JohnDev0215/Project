package org.apache.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public interface Session {
    Object getAttribute(Object obj) throws InvalidSessionException;

    Collection<Object> getAttributeKeys() throws InvalidSessionException;

    String getHost();

    Serializable getId();

    Date getLastAccessTime();

    Date getStartTimestamp();

    long getTimeout() throws InvalidSessionException;

    Object removeAttribute(Object obj) throws InvalidSessionException;

    void setAttribute(Object obj, Object obj2) throws InvalidSessionException;

    void setTimeout(long j) throws InvalidSessionException;

    void stop() throws InvalidSessionException;

    void touch() throws InvalidSessionException;
}
