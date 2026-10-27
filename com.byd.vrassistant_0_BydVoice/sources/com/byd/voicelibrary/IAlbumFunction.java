package com.byd.voicelibrary;

/* JADX INFO: loaded from: classes.dex */
public interface IAlbumFunction {
    public static final int INDEX_ALBUM = 1;

    @Deprecated
    public static final int INDEX_DVR_LOCK = 3;

    @Deprecated
    public static final int INDEX_DVR_NOMAL = 2;
    public static final int INDEX_PHOTO = 0;
    public static final int TYPE_ALL = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;

    void closeAlbum();

    void openAlbum(int i);

    void resizeVideoPlayer(boolean z);

    void search(String str, int i, int i2);

    void seekToPosition(int i, boolean z);
}
