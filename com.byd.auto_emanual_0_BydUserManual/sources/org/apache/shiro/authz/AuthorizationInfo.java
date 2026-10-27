package org.apache.shiro.authz;

import java.io.Serializable;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface AuthorizationInfo extends Serializable {
    Collection<Permission> getObjectPermissions();

    Collection<String> getRoles();

    Collection<String> getStringPermissions();
}
