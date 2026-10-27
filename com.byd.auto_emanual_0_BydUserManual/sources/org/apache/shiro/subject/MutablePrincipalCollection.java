package org.apache.shiro.subject;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface MutablePrincipalCollection extends PrincipalCollection {
    void add(Object obj, String str);

    void addAll(Collection collection, String str);

    void addAll(PrincipalCollection principalCollection);

    void clear();
}
