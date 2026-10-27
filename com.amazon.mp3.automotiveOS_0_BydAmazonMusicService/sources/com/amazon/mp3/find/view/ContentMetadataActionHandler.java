package com.amazon.mp3.find.view;

import com.amazon.music.find.viewmodels.SearchAction;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentMetadataActionHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J&\u0010\u0006\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/find/view/ContentMetadataActionHandler;", "", "canHandle", "", "action", "Lcom/amazon/music/find/viewmodels/SearchAction;", "handle", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "contentMetadataList", "", "positionToStart", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContentMetadataActionHandler {
    boolean canHandle(SearchAction action);

    void handle(ContentMetadata contentMetadata, InteractionType interactionType);

    void handle(List<? extends ContentMetadata> contentMetadataList, int positionToStart, InteractionType interactionType);
}
