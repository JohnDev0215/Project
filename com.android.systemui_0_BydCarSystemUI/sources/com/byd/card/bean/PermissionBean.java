package com.byd.card.bean;

/* JADX INFO: loaded from: classes.dex */
public class PermissionBean {
    private String description;
    private boolean isGranted;
    private String permission;

    public PermissionBean() {
    }

    public PermissionBean(String permission, boolean isGranted) {
        this.permission = permission;
        this.isGranted = isGranted;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGranted() {
        return this.isGranted;
    }

    public void setGranted(boolean isGranted) {
        this.isGranted = isGranted;
    }

    public String toString() {
        return "PermissionBean{permission='" + this.permission + "', description='" + this.description + "', isGranted=" + this.isGranted + '}';
    }
}
