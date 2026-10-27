package org.apache.shiro.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/* JADX INFO: loaded from: classes.dex */
public class PermissionUtils {
    public static Set<Permission> resolveDelimitedPermissions(String str, PermissionResolver permissionResolver) {
        return resolvePermissions(toPermissionStrings(str), permissionResolver);
    }

    public static Set<String> toPermissionStrings(String str) {
        String[] strArrSplit = StringUtils.split(str);
        if (strArrSplit == null || strArrSplit.length <= 0) {
            return null;
        }
        return new LinkedHashSet(Arrays.asList(strArrSplit));
    }

    public static Set<Permission> resolvePermissions(Collection<String> collection, PermissionResolver permissionResolver) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection.size());
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(permissionResolver.resolvePermission(it.next()));
        }
        return linkedHashSet;
    }
}
