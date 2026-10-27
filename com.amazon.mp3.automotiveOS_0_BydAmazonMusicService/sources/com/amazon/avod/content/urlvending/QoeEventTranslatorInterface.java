package com.amazon.avod.content.urlvending;

import kotlin.Metadata;

/* JADX INFO: compiled from: QoeEventTranslatorInterface.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeEventTranslatorInterface;", "", "notifyQoeDecrease", "", "contentUrl", "Lcom/amazon/avod/content/urlvending/ContentUrl;", "presentationTimeInNanos", "", "postQoeEvent", "qoeValue", "", "qoeAggregator", "Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface QoeEventTranslatorInterface {
    void notifyQoeDecrease(ContentUrl contentUrl, long presentationTimeInNanos);

    void postQoeEvent(float qoeValue, QoeAggregatorInterface qoeAggregator);
}
