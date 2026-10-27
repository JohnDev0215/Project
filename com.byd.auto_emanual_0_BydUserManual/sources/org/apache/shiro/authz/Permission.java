package org.apache.shiro.authz;

/* JADX INFO: loaded from: classes.dex */
public interface Permission {
    boolean implies(Permission permission);
}
