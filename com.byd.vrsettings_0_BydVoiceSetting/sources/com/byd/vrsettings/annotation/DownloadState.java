package com.byd.vrsettings.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface DownloadState {
    public static final int DOWNLOADING = 1;
    public static final int DOWNLOAD_ERROR = 5;
    public static final int DOWNLOAD_NOT_INSTALL = 6;
    public static final int DownLoaded = 4;
    public static final int INSTALLING = 7;
    public static final int INSTALL_ERROR = 8;
    public static final int NOT_DOWNLOAD = 0;
    public static final int PAUSE = 2;
    public static final int UPDATE = 3;
    public static final int WAIT_INSTALL = 9;
}
