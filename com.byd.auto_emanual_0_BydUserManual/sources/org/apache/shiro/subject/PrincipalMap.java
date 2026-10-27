package org.apache.shiro.subject;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface PrincipalMap extends PrincipalCollection, Map<String, Object> {
    Object getRealmPrincipal(String str, String str2);

    Map<String, Object> getRealmPrincipals(String str);

    Object removeRealmPrincipal(String str, String str2);

    Object setRealmPrincipal(String str, String str2, Object obj);

    Map<String, Object> setRealmPrincipals(String str, Map<String, Object> map);
}
