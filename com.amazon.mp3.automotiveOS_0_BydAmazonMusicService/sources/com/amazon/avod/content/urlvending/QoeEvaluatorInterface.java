package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.playback.VideoSpecification;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: QoeEvaluatorInterface.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0012H&J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u000fH&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0007H&J\b\u0010 \u001a\u00020\u0003H&J\b\u0010!\u001a\u00020\u0003H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H&¨\u0006%"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeEvaluatorInterface;", "", "begin", "", "_videoSpecification", "Lcom/amazon/avod/media/playback/VideoSpecification;", "_maxFrontBufferSizeMillis", "", "_networkHistoryManager", "Lcom/amazon/avod/media/framework/network/NetworkHistoryManager;", "_qoeEventTranslator", "Lcom/amazon/avod/content/urlvending/QoeEventTranslatorInterface;", "contentSessionType", "Lcom/amazon/avod/content/ContentSessionType;", "_currentContentUrl", "Lcom/amazon/avod/content/urlvending/ContentUrl;", "end", "getQoeDiagnosticInfo", "Lcom/amazon/avod/content/urlvending/QoeDiagnosticInfo;", "handleBufferFullnessChangedEvent", "isVideo", "", "bufferFullnessInNanos", "handleCdnSwitchedEvent", "newContentUrl", "handleFragmentDownloadedEvent", "downloadInfo", "Lcom/amazon/avod/content/urlvending/FragmentDownloadInfo;", "handleManifestDownloadedEvent", ImagesContract.URL, "", "latencyInNanos", "onBufferingStart", "onBufferingStop", "setClamper", "_clamper", "Lcom/amazon/avod/content/smoothstream/quality/QualityLevelClamper;", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface QoeEvaluatorInterface {
    void begin(VideoSpecification _videoSpecification, long _maxFrontBufferSizeMillis, NetworkHistoryManager _networkHistoryManager, QoeEventTranslatorInterface _qoeEventTranslator, ContentSessionType contentSessionType, ContentUrl _currentContentUrl);

    void end();

    QoeDiagnosticInfo getQoeDiagnosticInfo();

    void handleBufferFullnessChangedEvent(boolean isVideo, long bufferFullnessInNanos);

    void handleCdnSwitchedEvent(ContentUrl newContentUrl);

    void handleFragmentDownloadedEvent(FragmentDownloadInfo downloadInfo);

    void handleManifestDownloadedEvent(String url, long latencyInNanos);

    void onBufferingStart();

    void onBufferingStop();

    void setClamper(QualityLevelClamper _clamper);
}
