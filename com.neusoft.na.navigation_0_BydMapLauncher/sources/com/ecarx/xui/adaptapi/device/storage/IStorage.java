package com.ecarx.xui.adaptapi.device.storage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IStorage {
    public static final int TOTAL_SIZE = 0;
    public static final int TYPE_APP = 2;
    public static final int TYPE_DOC = 7;
    public static final int TYPE_MAP = 6;
    public static final int TYPE_MOVIE = 4;
    public static final int TYPE_MUSIC = 5;
    public static final int TYPE_OTHER = 16;
    public static final int TYPE_PICTURE = 3;
    public static final int TYPE_REMAIN = 17;
    public static final int TYPE_SYSTEM = 1;

    public interface IStorageWatcher {
        void onSizeChanged(int i, long j);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    long getSize(int i);

    void releaseStorageWatcher(IStorageWatcher iStorageWatcher);

    void startStorageWatcher(int[] iArr, IStorageWatcher iStorageWatcher);
}
