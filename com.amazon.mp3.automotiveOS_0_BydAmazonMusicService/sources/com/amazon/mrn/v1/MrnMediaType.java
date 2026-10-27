package com.amazon.mrn.v1;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;

/* JADX INFO: compiled from: MrnMediaType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/amazon/mrn/v1/MrnMediaType;", "", "value", "", "isContainer", "", "(Ljava/lang/String;ILjava/lang/String;Z)V", "()Z", "getValue", "()Ljava/lang/String;", "toString", "ARTIST", "MUSIC_TRACK", "MUSIC_ALBUM", "PLAYLIST", "STATION", "QUEUE_ID", "GENRE", "ERA", "MOOD", "ACTIVITY", "INTERNAL_TAG", "THEME", "EPISODE", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum MrnMediaType {
    ARTIST("artist", false),
    MUSIC_TRACK("track", false),
    MUSIC_ALBUM("album", true),
    PLAYLIST(Environment.PLAYLIST_PATH, true),
    STATION("station", true),
    QUEUE_ID("queueid", true),
    GENRE(ParserUtil.GENRE_SEGMENT_NAME, false),
    ERA("era", false),
    MOOD("mood", false),
    ACTIVITY(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, false),
    INTERNAL_TAG("internal", false),
    THEME("theme", false),
    EPISODE("episode", false);

    private final boolean isContainer;
    private final String value;

    MrnMediaType(String str, boolean z) {
        this.value = str;
        this.isContainer = z;
    }

    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: isContainer, reason: from getter */
    public final boolean getIsContainer() {
        return this.isContainer;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
