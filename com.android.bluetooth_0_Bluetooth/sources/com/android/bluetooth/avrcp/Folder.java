package com.android.bluetooth.avrcp;

/* JADX INFO: loaded from: classes.dex */
class Folder implements Cloneable {
    public boolean isPlayable;
    public String mediaId;
    public String title;

    Folder(String i, boolean p, String t) {
        this.mediaId = i;
        this.isPlayable = p;
        this.title = t;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Folder m7clone() {
        return new Folder(this.mediaId, this.isPlayable, this.title);
    }
}
