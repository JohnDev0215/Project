package com.byd.card.bean;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PermissionType {
    private List<PermissionBean> permissionBeans;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PermissionBean> getPermissionBeans() {
        return this.permissionBeans;
    }

    public void setPermissionBeans(List<PermissionBean> permissionBeans) {
        this.permissionBeans = permissionBeans;
    }

    public String toString() {
        return "PermissionType{type='" + this.type + "', permissionBeans=" + this.permissionBeans + '}';
    }
}
