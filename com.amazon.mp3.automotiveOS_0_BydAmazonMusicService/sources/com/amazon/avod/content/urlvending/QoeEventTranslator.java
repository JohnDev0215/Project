package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.ContentEventBufferFullnessChanged;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.manifest.acquisition.events.NewContentDownloadedEvent;
import com.amazon.avod.media.TimeSpan;
import com.google.common.eventbus.Subscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QoeEventTranslator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\nJ\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0018\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\u000e\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeEventTranslator;", "Lcom/amazon/avod/content/urlvending/QoeEventTranslatorInterface;", "qoeEvaluator", "Lcom/amazon/avod/content/urlvending/QoeEvaluatorInterface;", "(Lcom/amazon/avod/content/urlvending/QoeEvaluatorInterface;)V", "contentEventBus", "Lcom/amazon/avod/content/event/ContentManagementEventBus;", "contentUrlSelector", "Lcom/amazon/avod/content/urlvending/ContentUrlSelector;", "begin", "", "_contentUrlSelector", "end", "handleBufferFullnessChangedEvent", "bufferFullness", "Lcom/amazon/avod/content/event/ContentEventBufferFullnessChanged;", "handleCdnSwitchedEvent", "cdnSwitchedEvent", "Lcom/amazon/avod/content/event/CdnSwitchedEvent;", "handleFragmentDownloadedEvent", "fragment", "Lcom/amazon/avod/content/event/ContentEventFragmentDownloaded;", "handleManifestDownloadedEvent", "manifestDownloadInfo", "Lcom/amazon/avod/content/smoothstream/manifest/acquisition/events/NewContentDownloadedEvent;", "notifyQoeDecrease", "contentUrl", "Lcom/amazon/avod/content/urlvending/ContentUrl;", "presentationTimeInNanos", "", "postQoeEvent", "qoeValue", "", "qoeAggregator", "Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "registerForEvents", "_contentEventBus", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QoeEventTranslator implements QoeEventTranslatorInterface {
    private ContentManagementEventBus contentEventBus;
    private ContentUrlSelector contentUrlSelector;
    private final QoeEvaluatorInterface qoeEvaluator;

    public QoeEventTranslator(QoeEvaluatorInterface qoeEvaluator) {
        Intrinsics.checkNotNullParameter(qoeEvaluator, "qoeEvaluator");
        this.qoeEvaluator = qoeEvaluator;
    }

    public final void registerForEvents(ContentManagementEventBus _contentEventBus) {
        Intrinsics.checkNotNullParameter(_contentEventBus, "_contentEventBus");
        this.contentEventBus = _contentEventBus;
        if (_contentEventBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentEventBus");
            _contentEventBus = null;
        }
        _contentEventBus.registerEventBusHandler(this);
    }

    public final void begin(ContentUrlSelector _contentUrlSelector) {
        Intrinsics.checkNotNullParameter(_contentUrlSelector, "_contentUrlSelector");
        this.contentUrlSelector = _contentUrlSelector;
    }

    public final void end() {
        this.qoeEvaluator.end();
        ContentManagementEventBus contentManagementEventBus = this.contentEventBus;
        if (contentManagementEventBus != null) {
            if (contentManagementEventBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentEventBus");
                contentManagementEventBus = null;
            }
            contentManagementEventBus.unregisterEventBusHandler(this);
        }
    }

    @Subscribe
    public final void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.qoeEvaluator.handleFragmentDownloadedEvent(new FragmentDownloadInfo(fragment.getFragmentInformationHolder().isVideo(), fragment.getBandwidthBitsPerSecond(), fragment.getTimeToTheFirstByteInNanos(), fragment.getFragmentInformationHolder().getBitrate(), fragment.getFragmentInformationHolder().getPresentationTimeInNanos()));
    }

    @Subscribe
    public final void handleManifestDownloadedEvent(NewContentDownloadedEvent manifestDownloadInfo) {
        Intrinsics.checkNotNullParameter(manifestDownloadInfo, "manifestDownloadInfo");
        QoeEvaluatorInterface qoeEvaluatorInterface = this.qoeEvaluator;
        String url = manifestDownloadInfo.getContentUrl().getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "manifestDownloadInfo.contentUrl.url");
        qoeEvaluatorInterface.handleManifestDownloadedEvent(url, manifestDownloadInfo.getDownloadStatistics().getLatencyInNanos());
    }

    @Subscribe
    public final void handleBufferFullnessChangedEvent(ContentEventBufferFullnessChanged bufferFullness) {
        Intrinsics.checkNotNullParameter(bufferFullness, "bufferFullness");
        this.qoeEvaluator.handleBufferFullnessChangedEvent(bufferFullness.getStream().isVideo(), bufferFullness.getBufferFullnessInNanos());
    }

    @Subscribe
    public final void handleCdnSwitchedEvent(CdnSwitchedEvent cdnSwitchedEvent) {
        Intrinsics.checkNotNullParameter(cdnSwitchedEvent, "cdnSwitchedEvent");
        QoeEvaluatorInterface qoeEvaluatorInterface = this.qoeEvaluator;
        ContentUrl newContentUrl = cdnSwitchedEvent.getNewContentUrl();
        Intrinsics.checkNotNullExpressionValue(newContentUrl, "cdnSwitchedEvent.newContentUrl");
        qoeEvaluatorInterface.handleCdnSwitchedEvent(newContentUrl);
    }

    @Override // com.amazon.avod.content.urlvending.QoeEventTranslatorInterface
    public void postQoeEvent(float qoeValue, QoeAggregatorInterface qoeAggregator) {
        Intrinsics.checkNotNullParameter(qoeAggregator, "qoeAggregator");
        ContentManagementEventBus contentManagementEventBus = this.contentEventBus;
        if (contentManagementEventBus != null) {
            if (contentManagementEventBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentEventBus");
                contentManagementEventBus = null;
            }
            TimeSpan timeSpanNow = TimeSpan.now();
            Intrinsics.checkNotNullExpressionValue(timeSpanNow, "now()");
            contentManagementEventBus.postEvent(new QoeUpdateEvent(timeSpanNow, qoeValue, qoeAggregator));
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEventTranslatorInterface
    public void notifyQoeDecrease(ContentUrl contentUrl, long presentationTimeInNanos) {
        Intrinsics.checkNotNullParameter(contentUrl, "contentUrl");
        ContentUrlSelector contentUrlSelector = this.contentUrlSelector;
        if (contentUrlSelector != null) {
            if (contentUrlSelector == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentUrlSelector");
                contentUrlSelector = null;
            }
            contentUrlSelector.notifyQoeDecrease(contentUrl, presentationTimeInNanos);
        }
    }
}
