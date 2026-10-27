package org.apache.shiro.subject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface PrincipalCollection extends Iterable, Serializable {
    List asList();

    Set asSet();

    <T> Collection<T> byType(Class<T> cls);

    Collection fromRealm(String str);

    Object getPrimaryPrincipal();

    Set<String> getRealmNames();

    boolean isEmpty();

    <T> T oneByType(Class<T> cls);
}
