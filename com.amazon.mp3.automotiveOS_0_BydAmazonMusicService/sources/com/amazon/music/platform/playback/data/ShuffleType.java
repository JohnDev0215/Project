package com.amazon.music.platform.playback.data;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import kotlin.Metadata;

/* JADX INFO: compiled from: ShuffleType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0017\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/playback/data/ShuffleType;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/Prioritizable;", "value", "", "priority", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getPriority", "()I", "getValue", "()Ljava/lang/String;", "INLINE", "MIXED", PlaybackMode.NONE, "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum ShuffleType implements Prioritizable {
    INLINE("INLINE", 0),
    MIXED("MIXED", 10),
    NONE(PlaybackMode.NONE, 20);

    private final int priority;
    private final String value;

    ShuffleType(String str, int i) {
        this.value = str;
        this.priority = i;
    }

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Prioritizable
    public int getPriority() {
        return this.priority;
    }

    public final String getValue() {
        return this.value;
    }
}
