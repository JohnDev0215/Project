package com.android.systemui.statusbar;

/* JADX INFO: loaded from: classes.dex */
public class StatusBean {
    private final int id;
    private boolean isVisibility;
    private int status;

    public StatusBean(int id, boolean isVisibility) {
        this.status = 0;
        this.isVisibility = false;
        this.id = id;
        this.isVisibility = isVisibility;
    }

    public StatusBean(int id) {
        this(id, false);
    }

    public int getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isVisibility() {
        return this.isVisibility;
    }

    public void setVisibility(boolean visibility) {
        this.isVisibility = visibility;
    }

    public String toString() {
        return "StatusBean{id=" + this.id + ", status=" + this.status + ", isVisibility=" + this.isVisibility + '}';
    }
}
