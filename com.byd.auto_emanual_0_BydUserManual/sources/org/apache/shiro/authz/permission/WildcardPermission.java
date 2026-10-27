package org.apache.shiro.authz.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class WildcardPermission implements Permission, Serializable {
    protected static final boolean DEFAULT_CASE_SENSITIVE = false;
    protected static final String PART_DIVIDER_TOKEN = ":";
    protected static final String SUBPART_DIVIDER_TOKEN = ",";
    protected static final String WILDCARD_TOKEN = "*";
    private List<Set<String>> parts;

    protected WildcardPermission() {
    }

    public WildcardPermission(String str) {
        this(str, false);
    }

    public WildcardPermission(String str, boolean z) {
        setParts(str, z);
    }

    protected void setParts(String str) {
        setParts(str, false);
    }

    protected void setParts(String str, boolean z) {
        String strClean = StringUtils.clean(str);
        if (strClean == null || strClean.isEmpty()) {
            throw new IllegalArgumentException("Wildcard string cannot be null or empty. Make sure permission strings are properly formatted.");
        }
        if (!z) {
            strClean = strClean.toLowerCase();
        }
        List listAsList = CollectionUtils.asList(strClean.split(PART_DIVIDER_TOKEN));
        this.parts = new ArrayList();
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            Set<String> setAsSet = CollectionUtils.asSet(((String) it.next()).split(","));
            if (setAsSet.isEmpty()) {
                throw new IllegalArgumentException("Wildcard string cannot contain parts with only dividers. Make sure permission strings are properly formatted.");
            }
            this.parts.add(setAsSet);
        }
        if (this.parts.isEmpty()) {
            throw new IllegalArgumentException("Wildcard string cannot contain only dividers. Make sure permission strings are properly formatted.");
        }
    }

    protected List<Set<String>> getParts() {
        return this.parts;
    }

    protected void setParts(List<Set<String>> list) {
        this.parts = list;
    }

    @Override // org.apache.shiro.authz.Permission
    public boolean implies(Permission permission) {
        if (!(permission instanceof WildcardPermission)) {
            return false;
        }
        int i = 0;
        for (Set<String> set : ((WildcardPermission) permission).getParts()) {
            if (getParts().size() - 1 < i) {
                return true;
            }
            Set<String> set2 = getParts().get(i);
            if (!set2.contains(WILDCARD_TOKEN) && !set2.containsAll(set)) {
                return false;
            }
            i++;
        }
        while (i < getParts().size()) {
            if (!getParts().get(i).contains(WILDCARD_TOKEN)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Set<String> set : this.parts) {
            if (sb.length() > 0) {
                sb.append(PART_DIVIDER_TOKEN);
            }
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WildcardPermission) {
            return this.parts.equals(((WildcardPermission) obj).parts);
        }
        return false;
    }

    public int hashCode() {
        return this.parts.hashCode();
    }
}
