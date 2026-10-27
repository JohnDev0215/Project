package org.apache.shiro.session.mgt;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public interface ValidatingSession extends Session {
    boolean isValid();

    void validate() throws InvalidSessionException;
}
