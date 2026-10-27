package com.amazon.mp3.find.intents;

import android.content.Context;
import com.amazon.mp3.find.util.EntityTrackingIntentUtil;
import com.amazon.music.find.utils.MetricsUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.providers.EntityTrackingIntentServiceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchEntityTrackingIntentService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/find/intents/SearchEntityTrackingIntentService;", "Lcom/amazon/music/views/library/providers/EntityTrackingIntentServiceProvider;", "()V", "processEntityVisit", "", "context", "Landroid/content/Context;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchEntityTrackingIntentService implements EntityTrackingIntentServiceProvider {
    @Override // com.amazon.music.views.library.providers.EntityTrackingIntentServiceProvider
    public void processEntityVisit(Context context, ContentMetadata contentMetadata, PageType pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        if (contentMetadata instanceof PodcastMetadata) {
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(context, (PodcastMetadata) contentMetadata, MetricsUtils.isSearchPageType(pageType));
        }
    }
}
