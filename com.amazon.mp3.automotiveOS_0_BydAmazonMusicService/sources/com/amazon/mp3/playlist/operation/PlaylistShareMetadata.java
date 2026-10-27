package com.amazon.mp3.playlist.operation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistPrivacyOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/playlist/operation/PlaylistShareMetadata;", "", "title", "", "asin", "artworkUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtworkUrl", "()Ljava/lang/String;", "getAsin", "getTitle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PlaylistShareMetadata {
    private final String artworkUrl;
    private final String asin;
    private final String title;

    public abstract String getArtworkUrl();

    public abstract String getAsin();

    public abstract String getTitle();

    public PlaylistShareMetadata(String title, String str, String str2) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.asin = str;
        this.artworkUrl = str2;
    }
}
