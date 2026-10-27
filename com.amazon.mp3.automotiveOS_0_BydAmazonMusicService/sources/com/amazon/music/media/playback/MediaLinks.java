package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MediaLinks {
    public static final MediaLinks EMPTY = new MediaLinks(Collections.emptyList(), PlaybackAttributes.UNKNOWN);
    private final PlaybackAttributes bestAvailablePlaybackAttributes;
    private final List<MediaLink> links;

    public MediaLinks(@NonNull List<MediaLink> list, @NonNull PlaybackAttributes playbackAttributes) {
        this.links = list;
        this.bestAvailablePlaybackAttributes = playbackAttributes;
    }

    @NonNull
    public List<MediaLink> getLinks() {
        return this.links;
    }

    @NonNull
    public PlaybackAttributes getBestAvailablePlaybackAttributes() {
        return this.bestAvailablePlaybackAttributes;
    }
}
