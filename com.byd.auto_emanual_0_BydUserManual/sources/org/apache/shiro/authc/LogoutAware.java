package org.apache.shiro.authc;

import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public interface LogoutAware {
    void onLogout(PrincipalCollection principalCollection);
}
