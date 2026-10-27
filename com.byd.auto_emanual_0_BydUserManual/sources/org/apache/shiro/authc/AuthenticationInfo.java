package org.apache.shiro.authc;

import java.io.Serializable;
import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public interface AuthenticationInfo extends Serializable {
    Object getCredentials();

    PrincipalCollection getPrincipals();
}
