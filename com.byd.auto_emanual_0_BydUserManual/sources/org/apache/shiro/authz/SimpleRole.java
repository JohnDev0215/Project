package org.apache.shiro.authz;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class SimpleRole implements Serializable {
    protected String name = null;
    protected Set<Permission> permissions;

    public SimpleRole() {
    }

    public SimpleRole(String str) {
        setName(str);
    }

    public SimpleRole(String str, Set<Permission> set) {
        setName(str);
        setPermissions(set);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<Permission> set) {
        this.permissions = set;
    }

    public void add(Permission permission) {
        Set<Permission> permissions = getPermissions();
        if (permissions == null) {
            permissions = new LinkedHashSet<>();
            setPermissions(permissions);
        }
        permissions.add(permission);
    }

    public void addAll(Collection<Permission> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Set<Permission> permissions = getPermissions();
        if (permissions == null) {
            permissions = new LinkedHashSet<>(collection.size());
            setPermissions(permissions);
        }
        permissions.addAll(collection);
    }

    public boolean isPermitted(Permission permission) {
        Set<Permission> permissions = getPermissions();
        if (permissions == null || permissions.isEmpty()) {
            return false;
        }
        Iterator<Permission> it = permissions.iterator();
        while (it.hasNext()) {
            if (it.next().implies(permission)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (getName() != null) {
            return getName().hashCode();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SimpleRole)) {
            return false;
        }
        SimpleRole simpleRole = (SimpleRole) obj;
        return getName() != null ? getName().equals(simpleRole.getName()) : simpleRole.getName() == null;
    }

    public String toString() {
        return getName();
    }
}
