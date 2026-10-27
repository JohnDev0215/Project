package com.amazon.music.views.library.providers;

import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J!\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H&¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/providers/PlaybackProvider;", "", "shouldEnableStationPlayback", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "startPlayback", "", "withShuffle", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Boolean;)V", "startStationPlayback", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaybackProvider {
    boolean shouldEnableStationPlayback(ContentMetadata metadata);

    void startPlayback(ContentMetadata metadata, Boolean withShuffle);

    void startStationPlayback(ContentMetadata metadata);

    /* JADX INFO: compiled from: PlaybackProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void startPlayback$default(PlaybackProvider playbackProvider, ContentMetadata contentMetadata, Boolean bool, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startPlayback");
            }
            if ((i & 2) != 0) {
                bool = Boolean.FALSE;
            }
            playbackProvider.startPlayback(contentMetadata, bool);
        }
    }
}
