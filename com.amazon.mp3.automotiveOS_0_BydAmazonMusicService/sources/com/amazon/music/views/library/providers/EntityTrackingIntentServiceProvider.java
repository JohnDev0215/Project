package com.amazon.music.views.library.providers;

import android.content.Context;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: EntityTrackingIntentServiceProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/providers/EntityTrackingIntentServiceProvider;", "", "processEntityVisit", "", "context", "Landroid/content/Context;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface EntityTrackingIntentServiceProvider {
    void processEntityVisit(Context context, ContentMetadata contentMetadata, PageType pageType);
}
