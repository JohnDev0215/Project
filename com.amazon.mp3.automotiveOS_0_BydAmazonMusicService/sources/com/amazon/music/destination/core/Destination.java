package com.amazon.music.destination.core;

/* JADX INFO: loaded from: classes4.dex */
public class Destination {
    private final boolean isChildInappropriate;
    private final String ref;
    private boolean success;
    private final String tag;

    public Destination(String str, String str2) {
        this.success = true;
        this.ref = str;
        this.tag = str2;
        this.isChildInappropriate = false;
    }

    public Destination(String str, String str2, boolean z) {
        this.success = true;
        this.ref = str;
        this.tag = str2;
        this.isChildInappropriate = z;
    }

    public String getRef() {
        return this.ref;
    }

    public String getTag() {
        return this.tag;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isChildInappropriate() {
        return this.isChildInappropriate;
    }
}
