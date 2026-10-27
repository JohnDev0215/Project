package com.byd.music.protocol.client;

/* JADX INFO: loaded from: classes5.dex */
public interface IProtocolMusicPlayObserver {
    boolean infoUpdate(int i, String str, String str2, String str3);

    boolean playingStatusUpdate(boolean z);

    boolean processUpdate(int i);
}
