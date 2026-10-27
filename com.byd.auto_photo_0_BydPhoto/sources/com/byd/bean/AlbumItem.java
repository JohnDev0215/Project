package com.byd.bean;

import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public class AlbumItem {
    public static final int ALBUM_TYPE_GROUP_TITLE = 4;
    public static final int ALBUM_TYPE_NONE = 0;
    public static final int ALBUM_TYPE_OTHER = 3;
    public static final int ALBUM_TYPE_SYS = 1;
    public static final int ALBUM_TYPE_USR = 2;
    public static final int TYPE_SOURCE_CONTENT = 1;
    public static final int TYPE_SOURCE_DATABASE = 3;
    public static final int TYPE_SOURCE_EXTERNAL_SDCARD = 4;
    public static final int TYPE_SOURCE_EXTERNAL_UDISK = 5;
    public static final int TYPE_SOURCE_FOLDER = 2;
    public static final int TYPE_SOURCE_NONE = 0;
    private int albumType;
    private boolean canReName;
    private String coverPath;
    private int icon;
    private String name;
    private int num;
    private boolean select;
    private int sourceType;
    private Uri uri;

    public AlbumItem(int i, String str, int i2, Uri uri, int i3, int i4) {
        this.num = -1;
        this.icon = i;
        this.name = str;
        this.num = i2;
        this.uri = uri;
        this.sourceType = i3;
        this.albumType = i4;
    }

    public AlbumItem(int i, String str, Uri uri, int i2, int i3) {
        this(i, str, -1, uri, i2, i3);
    }

    public AlbumItem(String str, int i) {
        this.num = -1;
        this.name = str;
        this.albumType = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AlbumItem)) {
            return super.equals(obj);
        }
        AlbumItem albumItem = (AlbumItem) obj;
        return (this.name + this.uri).equals(albumItem.name + albumItem.uri);
    }

    public int getAlbumType() {
        return this.albumType;
    }

    public String getCoverPath() {
        return this.coverPath;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getName() {
        return this.name;
    }

    public int getNum() {
        return this.num;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public Uri getUri() {
        return this.uri;
    }

    public boolean isCanReName() {
        return this.canReName;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setAlbumType(int i) {
        this.albumType = i;
    }

    public void setCanReName(boolean z) {
        this.canReName = z;
    }

    public void setCoverPath(String str) {
        this.coverPath = str;
    }

    public void setIcon(int i) {
        this.icon = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
