package com.amazon.avod.playbackclient.subtitle.download;

/* JADX INFO: loaded from: classes2.dex */
public interface SubtitleDownloader {
    void destroy();

    void downloadFinished();

    void initialize();

    void startDownload();
}
