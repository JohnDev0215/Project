package com.amazon.music.platform.playback.data;

import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import kotlin.Metadata;

/* JADX INFO: compiled from: NonBlockingUpsellReason.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/platform/playback/data/NonBlockingUpsellReason;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/Prioritizable;", "priority", "", "(Ljava/lang/String;II)V", "getPriority", "()I", "None", "NotOnDemandTrack", "NotOnDemandAlbum", "NotOnDemandArtist", "NotOnDemandPlaylist", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum NonBlockingUpsellReason implements Prioritizable {
    None(0),
    NotOnDemandTrack(1),
    NotOnDemandAlbum(2),
    NotOnDemandArtist(2),
    NotOnDemandPlaylist(2);

    private final int priority;

    NonBlockingUpsellReason(int i) {
        this.priority = i;
    }

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Prioritizable
    public int getPriority() {
        return this.priority;
    }
}
