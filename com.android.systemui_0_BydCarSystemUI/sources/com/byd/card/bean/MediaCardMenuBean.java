package com.byd.card.bean;

/* JADX INFO: loaded from: classes.dex */
public class MediaCardMenuBean {
    private boolean checked;
    private int mediaSource;
    private int mode;
    private String name;
    private String packageName;

    public MediaCardMenuBean(String packageName, int mode, boolean checked) {
        this.packageName = packageName;
        this.mode = mode;
        this.checked = checked;
        this.mediaSource = -1;
    }

    public MediaCardMenuBean(String packageName, int mode, boolean checked, int localType) {
        this.packageName = packageName;
        this.mode = mode;
        this.checked = checked;
        this.mediaSource = localType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getMediaSource() {
        return this.mediaSource;
    }

    public void setMediaSource(int mediaSource) {
        this.mediaSource = mediaSource;
    }

    public String toString() {
        return "MediaCardMenuBean{name='" + this.name + "', packageName='" + this.packageName + "', mode=" + this.mode + ", checked=" + this.checked + ", mediaSource=" + this.mediaSource + '}';
    }
}
