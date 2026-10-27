package com.amazon.mp3.playlist;

import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AddToPlaylistEventBuilder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/playlist/AddToPlaylistEventBuilder;", "", "()V", "numTracks", "", "getNumTracks", "()I", "setNumTracks", "(I)V", "playlistId", "", "getPlaylistId", "()Ljava/lang/String;", "setPlaylistId", "(Ljava/lang/String;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/metrics/mts/event/definition/flex/FlexEvent;", "withNumTracks", "withPlaylistId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AddToPlaylistEventBuilder {
    private int numTracks;
    public String playlistId;

    public final String getPlaylistId() {
        String str = this.playlistId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistId");
        return null;
    }

    public final void setPlaylistId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.playlistId = str;
    }

    public final int getNumTracks() {
        return this.numTracks;
    }

    public final void setNumTracks(int i) {
        this.numTracks = i;
    }

    public final AddToPlaylistEventBuilder withPlaylistId(String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        setPlaylistId(playlistId);
        return this;
    }

    public final AddToPlaylistEventBuilder withNumTracks(int numTracks) {
        setNumTracks(numTracks);
        return this;
    }

    public final FlexEvent build() {
        FlexEvent flexEventBuild = FlexEvent.builder("addToPlaylist").withFlexStr1(getPlaylistId()).withFlexNum1(Float.valueOf(this.numTracks)).build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(MusicFlexEvents.…\n                .build()");
        return flexEventBuild;
    }
}
