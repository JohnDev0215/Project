package com.amazon.music.find.metrics;

import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchMetricsProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/find/metrics/SearchMetricsProvider;", "", "isOffline", "", "()Z", "viewType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "getViewType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchMetricsProvider {
    ViewType getViewType();

    boolean isOffline();
}
