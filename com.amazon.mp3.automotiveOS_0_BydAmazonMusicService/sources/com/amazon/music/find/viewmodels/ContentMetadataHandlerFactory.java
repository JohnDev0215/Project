package com.amazon.music.find.viewmodels;

import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentMetadataHandlerFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J.\u0010\u0002\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/viewmodels/ContentMetadataHandlerFactory;", "", "handle", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "action", "Lcom/amazon/music/find/viewmodels/SearchAction;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "contentMetadataList", "", "positionToStart", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContentMetadataHandlerFactory {
    void handle(ContentMetadata contentMetadata, SearchAction action, InteractionType interactionType);

    void handle(List<? extends ContentMetadata> contentMetadataList, int positionToStart, SearchAction action, InteractionType interactionType);
}
