package com.byd.music.protocol;

import com.byd.music.protocol.client.IProtocolMusicPlayObserver;

/* JADX INFO: loaded from: classes5.dex */
public class ProtocolUtils {
    public static int MUSIC_UPDATE_TYPE_INFO = 10002;
    public static int MUSIC_UPDATE_TYPE_PLAYINGSTATUS = 10003;
    public static int MUSIC_UPDATE_TYPE_PROGRESS = 10001;
    private static final ProtocolUtils instances = new ProtocolUtils();
    IProtocolMusicPlayObserver iProtocolMusicPlayObserver;

    public static ProtocolUtils getInstance() {
        return instances;
    }

    public void setMusicPlayObserver(IProtocolMusicPlayObserver iProtocolMusicPlayObserver) {
        this.iProtocolMusicPlayObserver = iProtocolMusicPlayObserver;
    }

    public void updateProcess(int i) {
        IProtocolMusicPlayObserver iProtocolMusicPlayObserver = this.iProtocolMusicPlayObserver;
        if (iProtocolMusicPlayObserver != null) {
            iProtocolMusicPlayObserver.processUpdate(i);
        }
    }

    public void updateInfo(int i, String str, String str2, String str3) {
        IProtocolMusicPlayObserver iProtocolMusicPlayObserver = this.iProtocolMusicPlayObserver;
        if (iProtocolMusicPlayObserver != null) {
            iProtocolMusicPlayObserver.infoUpdate(i, str, str2, str3);
        }
    }

    public void updatePlayingStatus(boolean z) {
        IProtocolMusicPlayObserver iProtocolMusicPlayObserver = this.iProtocolMusicPlayObserver;
        if (iProtocolMusicPlayObserver != null) {
            iProtocolMusicPlayObserver.playingStatusUpdate(z);
        }
    }
}
