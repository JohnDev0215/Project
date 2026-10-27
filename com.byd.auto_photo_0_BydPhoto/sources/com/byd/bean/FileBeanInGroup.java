package com.byd.bean;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class FileBeanInGroup implements Comparable<FileBeanInGroup> {
    public static final int TYPE_FILE_BEAN = 2;
    public static final int TYPE_GROUP_TITLE = 1;
    private boolean checkedLoc;
    private long date;
    private FileBean fileBean;
    private String groupName;
    private String locationName;
    private FileBean newestBean;
    private int type = 1;

    public FileBeanInGroup(long j) {
        this.date = j;
    }

    public FileBeanInGroup(FileBean fileBean) {
        this.fileBean = fileBean;
        this.date = fileBean.getDate();
    }

    public FileBeanInGroup(String str) {
        this.groupName = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(FileBeanInGroup fileBeanInGroup) {
        FileBean fileBean = this.fileBean;
        return (fileBean == null && fileBeanInGroup.fileBean == null) ? this.date - fileBeanInGroup.date > 0 ? -1 : 1 : fileBean.compareTo(fileBeanInGroup.fileBean);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FileBeanInGroup)) {
            return super.equals(obj);
        }
        FileBeanInGroup fileBeanInGroup = (FileBeanInGroup) obj;
        if (this.type != fileBeanInGroup.type) {
            return false;
        }
        FileBean fileBean = this.fileBean;
        if (fileBean != null) {
            return fileBean.equals(fileBeanInGroup.fileBean);
        }
        String str = this.groupName;
        if (str != null) {
            return str.equals(fileBeanInGroup.groupName);
        }
        return false;
    }

    public long getDate() {
        return this.date;
    }

    public FileBean getFileBean() {
        return this.fileBean;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getLocationName() {
        FileBean fileBean;
        if (this.locationName == null && (fileBean = this.newestBean) != null) {
            this.locationName = fileBean.getAddress();
        }
        return this.locationName;
    }

    public FileBean getNewestBean() {
        return this.newestBean;
    }

    public int getType() {
        return this.type;
    }

    public boolean hasCheckLocation() {
        return this.checkedLoc;
    }

    public boolean hasLocation() {
        FileBean fileBean = this.newestBean;
        if (fileBean == null) {
            return false;
        }
        return (fileBean.getLat() == 0.0d && this.newestBean.getLng() == 0.0d) ? false : true;
    }

    public void setCheckedLoc(boolean z) {
        this.checkedLoc = z;
    }

    public void setLocationName(String str) {
        this.locationName = str;
        this.checkedLoc = true;
    }

    public void setNewestBean(Context context, FileBean fileBean) {
        this.newestBean = fileBean;
    }
}
