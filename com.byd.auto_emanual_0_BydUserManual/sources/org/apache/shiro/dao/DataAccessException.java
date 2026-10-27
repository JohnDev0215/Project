package org.apache.shiro.dao;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataAccessException extends ShiroException {
    public DataAccessException(String str) {
        super(str);
    }

    public DataAccessException(String str, Throwable th) {
        super(str, th);
    }
}
