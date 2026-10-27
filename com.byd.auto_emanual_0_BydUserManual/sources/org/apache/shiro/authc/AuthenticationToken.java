package org.apache.shiro.authc;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public interface AuthenticationToken extends Serializable {
    Object getCredentials();

    Object getPrincipal();
}
