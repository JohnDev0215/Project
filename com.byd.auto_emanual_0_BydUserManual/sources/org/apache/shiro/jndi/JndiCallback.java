package org.apache.shiro.jndi;

import javax.naming.Context;
import javax.naming.NamingException;

/* JADX INFO: loaded from: classes.dex */
public interface JndiCallback {
    Object doInContext(Context context) throws NamingException;
}
