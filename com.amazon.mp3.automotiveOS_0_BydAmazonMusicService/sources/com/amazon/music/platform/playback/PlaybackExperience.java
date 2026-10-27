package com.amazon.music.platform.playback;

import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackExperience.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/playback/PlaybackExperience;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/Prioritizable;", "priority", "", "(Ljava/lang/String;II)V", "getPriority", "()I", "ON_DEMAND", "INLINE_SHUFFLE", "AUGMENTED_SHUFFLE", "STATION_FROM_ANYTHING", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum PlaybackExperience implements Prioritizable {
    ON_DEMAND(0),
    INLINE_SHUFFLE(10),
    AUGMENTED_SHUFFLE(20),
    STATION_FROM_ANYTHING(30);

    private final int priority;

    PlaybackExperience(int i) {
        this.priority = i;
    }

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Prioritizable
    public int getPriority() {
        return this.priority;
    }
}
