package com.android.permissioncontroller.permission.ui.auto;

/* JADX INFO: loaded from: classes8.dex */
public class PermissionItem {
    private boolean mCheckStatus;
    private String mDetailMessage;
    private String mGroupPermissionName;
    private int mImageId;
    private int mImgBgId;
    private String mPermissionName;

    public PermissionItem(String groupPermissionName, String permissionName, String detailMessage, int imageId, boolean checkStatus) {
        this.mGroupPermissionName = groupPermissionName;
        this.mPermissionName = permissionName;
        this.mDetailMessage = detailMessage;
        this.mImageId = imageId;
        this.mCheckStatus = checkStatus;
    }

    public String getName() {
        return this.mGroupPermissionName;
    }

    public String getPermissionName() {
        return this.mPermissionName;
    }

    public String getDetailMessage() {
        return this.mDetailMessage;
    }

    public int getImageId() {
        return this.mImageId;
    }

    public boolean getCheckStatus() {
        return this.mCheckStatus;
    }
}
