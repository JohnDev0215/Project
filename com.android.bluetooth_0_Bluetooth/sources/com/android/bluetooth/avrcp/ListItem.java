package com.android.bluetooth.avrcp;

/* JADX INFO: loaded from: classes.dex */
class ListItem implements Cloneable {
    public Folder folder;
    public boolean isFolder;
    public Metadata song;

    ListItem(Folder f) {
        this.isFolder = false;
        this.isFolder = true;
        this.folder = f;
    }

    ListItem(Metadata s) {
        this.isFolder = false;
        this.isFolder = false;
        this.song = s;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ListItem m8clone() {
        if (this.isFolder) {
            return new ListItem(this.folder.m7clone());
        }
        return new ListItem(this.song.m9clone());
    }
}
