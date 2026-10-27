package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.util.FloatRollingStatisticsAggregator;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: QoeEvaluator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001f\b&\u0018\u00002\u00020\u0001B\u0011\u0012\u0006\u00102\u001a\u000201¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J8\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0004H\u0016J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010 \u001a\u00020\u000eH\u0016J\b\u0010!\u001a\u00020\u000eH\u0016J\b\u0010\"\u001a\u00020\u000eH\u0016J\u0017\u0010&\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u0004H\u0001¢\u0006\u0004\b$\u0010%J\b\u0010'\u001a\u00020\u000eH&J\u000f\u0010*\u001a\u00020\u000eH\u0001¢\u0006\u0004\b(\u0010)J\u0006\u0010+\u001a\u00020\u000eJ\u0017\u0010.\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0016H\u0001¢\u0006\u0004\b,\u0010-J\b\u00100\u001a\u00020/H\u0016R\u0017\u00102\u001a\u0002018\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00068\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010>\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010@\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bB\u0010CR\"\u0010D\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR3\u0010M\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020K0Jj\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020K`L8\u0006¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR*\u0010S\u001a\u00020Q2\u0006\u0010R\u001a\u00020Q8\u0006@DX\u0086\u000e¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\"\u0010Z\u001a\u00020Y8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\"\u0010`\u001a\u00020Y8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b`\u0010[\u001a\u0004\ba\u0010]\"\u0004\bb\u0010_R\u001a\u0010d\u001a\u00020c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bd\u0010e\u001a\u0004\bf\u0010gR\u0014\u0010k\u001a\u00020h8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bi\u0010jR\u0014\u0010m\u001a\u00020h8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bl\u0010jR\u0014\u0010p\u001a\u00020K8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0014\u0010r\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bq\u0010VR\u0014\u0010t\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bs\u0010VR\u0014\u0010v\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bu\u0010VR\u0014\u0010x\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bw\u0010VR\u0014\u0010z\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\by\u0010VR\u0014\u0010|\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b{\u0010VR\u0014\u0010~\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b}\u0010VR\u0015\u0010\u0080\u0001\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u007f\u0010VR\u0016\u0010\u0082\u0001\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010VR\u0016\u0010\u0084\u0001\u001a\u00020Q8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010V¨\u0006\u0087\u0001"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeEvaluator;", "Lcom/amazon/avod/content/urlvending/QoeEvaluatorInterface;", "Lcom/amazon/avod/media/playback/VideoSpecification;", "_videoSpecification", "", "_maxFrontBufferSizeMillis", "Lcom/amazon/avod/media/framework/network/NetworkHistoryManager;", "_networkHistoryManager", "Lcom/amazon/avod/content/urlvending/QoeEventTranslatorInterface;", "_qoeEventTranslator", "Lcom/amazon/avod/content/ContentSessionType;", "contentSessionType", "Lcom/amazon/avod/content/urlvending/ContentUrl;", "_currentContentUrl", "", "begin", "Lcom/amazon/avod/content/smoothstream/quality/QualityLevelClamper;", "_clamper", "setClamper", "Lcom/amazon/avod/content/urlvending/FragmentDownloadInfo;", "downloadInfo", "handleFragmentDownloadedEvent", "", ImagesContract.URL, "latencyInNanos", "handleManifestDownloadedEvent", "", "isVideo", "bufferFullnessInNanos", "handleBufferFullnessChangedEvent", "newContentUrl", "handleCdnSwitchedEvent", "onBufferingStart", "onBufferingStop", "end", "presentationTimeInNanos", "reCalculateQoeAndMayTriggerCdnSwitch$ATVPlayerHeuristics_release", "(J)V", "reCalculateQoeAndMayTriggerCdnSwitch", "reCalculateQoe", "clear$ATVPlayerHeuristics_release", "()V", "clear", "checkNetworkName", "isManifestUrl$ATVPlayerHeuristics_release", "(Ljava/lang/String;)Z", "isManifestUrl", "Lcom/amazon/avod/content/urlvending/QoeDiagnosticInfo;", "getQoeDiagnosticInfo", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "qoeConfig", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "getQoeConfig", "()Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "isStarted", "Z", "networkHistoryManager", "Lcom/amazon/avod/media/framework/network/NetworkHistoryManager;", "videoSpecification", "Lcom/amazon/avod/media/playback/VideoSpecification;", "qoeEventTranslator", "Lcom/amazon/avod/content/urlvending/QoeEventTranslatorInterface;", "maxFrontBufferSizeMillis", "Ljava/lang/Long;", "clamper", "Lcom/amazon/avod/content/smoothstream/quality/QualityLevelClamper;", "currentContentUrl", "Lcom/amazon/avod/content/urlvending/ContentUrl;", "currentNetworkName", "Ljava/lang/String;", "getCurrentNetworkName", "()Ljava/lang/String;", "setCurrentNetworkName", "(Ljava/lang/String;)V", "Ljava/util/HashMap;", "Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "Lkotlin/collections/HashMap;", "qoeAggregators", "Ljava/util/HashMap;", "getQoeAggregators", "()Ljava/util/HashMap;", "", "<set-?>", "qoeValue", "F", "getQoeValue", "()F", "setQoeValue", "(F)V", "Lcom/amazon/avod/media/framework/util/FloatRollingStatisticsAggregator;", "aggregatorForAvgQoeValue", "Lcom/amazon/avod/media/framework/util/FloatRollingStatisticsAggregator;", "getAggregatorForAvgQoeValue$ATVPlayerHeuristics_release", "()Lcom/amazon/avod/media/framework/util/FloatRollingStatisticsAggregator;", "setAggregatorForAvgQoeValue$ATVPlayerHeuristics_release", "(Lcom/amazon/avod/media/framework/util/FloatRollingStatisticsAggregator;)V", "aggregatorForQoeChangeSmoothing", "getAggregatorForQoeChangeSmoothing$ATVPlayerHeuristics_release", "setAggregatorForQoeChangeSmoothing$ATVPlayerHeuristics_release", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;", "cdnSwitchWindow", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;", "getCdnSwitchWindow$ATVPlayerHeuristics_release", "()Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;", "", "getMaxBitrateBps", "()I", "maxBitrateBps", "getMinBitrateBps", "minBitrateBps", "getCurrentQoeAggregator", "()Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "currentQoeAggregator", "getBandwidthChangeInput$ATVPlayerHeuristics_release", "bandwidthChangeInput", "getLatencyChangeInput$ATVPlayerHeuristics_release", "latencyChangeInput", "getManifestLatencyChangeInput$ATVPlayerHeuristics_release", "manifestLatencyChangeInput", "getBandwidthInput$ATVPlayerHeuristics_release", "bandwidthInput", "getLatencyInput$ATVPlayerHeuristics_release", "latencyInput", "getManifestLatencyInput$ATVPlayerHeuristics_release", "manifestLatencyInput", "getBitrateInput$ATVPlayerHeuristics_release", "bitrateInput", "getFrontBufferSizeInput$ATVPlayerHeuristics_release", "frontBufferSizeInput", "getTimeSpentBufferingInput$ATVPlayerHeuristics_release", "timeSpentBufferingInput", "getNumOfBufferingEventInput$ATVPlayerHeuristics_release", "numOfBufferingEventInput", "<init>", "(Lcom/amazon/avod/content/urlvending/QoeConfigInterface;)V", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public abstract class QoeEvaluator implements QoeEvaluatorInterface {
    private FloatRollingStatisticsAggregator aggregatorForAvgQoeValue;
    private FloatRollingStatisticsAggregator aggregatorForQoeChangeSmoothing;
    private final SlidingWindowEventTracker cdnSwitchWindow;
    private QualityLevelClamper clamper;
    private ContentUrl currentContentUrl;
    private String currentNetworkName;
    private boolean isStarted;
    private Long maxFrontBufferSizeMillis;
    private NetworkHistoryManager networkHistoryManager;
    private final HashMap<String, QoeAggregatorInterface> qoeAggregators;
    private final QoeConfigInterface qoeConfig;
    private QoeEventTranslatorInterface qoeEventTranslator;
    private float qoeValue;
    private VideoSpecification videoSpecification;

    public abstract void reCalculateQoe();

    public QoeEvaluator(QoeConfigInterface qoeConfig) {
        Intrinsics.checkNotNullParameter(qoeConfig, "qoeConfig");
        this.qoeConfig = qoeConfig;
        this.currentNetworkName = "init";
        this.qoeAggregators = new HashMap<>();
        this.aggregatorForAvgQoeValue = new FloatRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForAvgQoe());
        this.aggregatorForQoeChangeSmoothing = new FloatRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForQoeChangeSmoothing());
        this.cdnSwitchWindow = new SlidingWindowEventTracker(qoeConfig.getWindowLengthForTrackingCdnSwitchesCausedByQoe(), qoeConfig.getMaxNumOfCdnSwitchesCausedByQoeWithinWindow());
    }

    public final QoeConfigInterface getQoeConfig() {
        return this.qoeConfig;
    }

    public final String getCurrentNetworkName() {
        return this.currentNetworkName;
    }

    public QoeAggregatorInterface getCurrentQoeAggregator() {
        HashMap<String, QoeAggregatorInterface> map = this.qoeAggregators;
        String str = this.currentNetworkName;
        QoeAggregatorInterface qoeAggregator = map.get(str);
        if (qoeAggregator == null) {
            qoeAggregator = new QoeAggregator(getCurrentNetworkName(), getQoeConfig());
            map.put(str, qoeAggregator);
        }
        return qoeAggregator;
    }

    public final float getQoeValue() {
        return this.qoeValue;
    }

    protected final void setQoeValue(float f) {
        this.qoeValue = f;
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void begin(VideoSpecification _videoSpecification, long _maxFrontBufferSizeMillis, NetworkHistoryManager _networkHistoryManager, QoeEventTranslatorInterface _qoeEventTranslator, ContentSessionType contentSessionType, ContentUrl _currentContentUrl) {
        Intrinsics.checkNotNullParameter(_videoSpecification, "_videoSpecification");
        Intrinsics.checkNotNullParameter(_networkHistoryManager, "_networkHistoryManager");
        Intrinsics.checkNotNullParameter(_qoeEventTranslator, "_qoeEventTranslator");
        Intrinsics.checkNotNullParameter(contentSessionType, "contentSessionType");
        Intrinsics.checkNotNullParameter(_currentContentUrl, "_currentContentUrl");
        if (contentSessionType == ContentSessionType.STREAMING && ((this.qoeConfig.getIsQoeEvaluatorEnabledForLive() && _videoSpecification.isLiveStream()) || (this.qoeConfig.getIsQoeEvaluatorEnabledForVOD() && _videoSpecification.getContentType() == ContentType.Feature))) {
            this.videoSpecification = _videoSpecification;
            this.maxFrontBufferSizeMillis = Long.valueOf(_maxFrontBufferSizeMillis);
            this.networkHistoryManager = _networkHistoryManager;
            this.qoeEventTranslator = _qoeEventTranslator;
            this.currentContentUrl = _currentContentUrl;
            this.isStarted = true;
            StringBuilder sb = new StringBuilder();
            sb.append("QoeEvaluator is started for SessionType ");
            sb.append(contentSessionType);
            sb.append(" and ContentType ");
            VideoSpecification videoSpecification = this.videoSpecification;
            if (videoSpecification == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoSpecification");
                videoSpecification = null;
            }
            sb.append(videoSpecification.getContentType());
            DLog.logf(sb.toString());
            return;
        }
        DLog.logf("Skipped starting QoeEvaluator for SessionType " + contentSessionType + " and ContentType " + _videoSpecification.getContentType());
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void setClamper(QualityLevelClamper _clamper) {
        Intrinsics.checkNotNullParameter(_clamper, "_clamper");
        if (this.isStarted) {
            this.clamper = _clamper;
            DLog.logf("QoeEvaluator sets QualityLevelClamper.");
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void handleFragmentDownloadedEvent(FragmentDownloadInfo downloadInfo) {
        Intrinsics.checkNotNullParameter(downloadInfo, "downloadInfo");
        if (downloadInfo.getIsVideo()) {
            checkNetworkName();
            getCurrentQoeAggregator().handleFragmentDownloadedEvent(downloadInfo.getBandwidthBitsPerSecond(), downloadInfo.getTimeToTheFirstByteInNanos(), downloadInfo.getBitrate());
            reCalculateQoeAndMayTriggerCdnSwitch$ATVPlayerHeuristics_release(downloadInfo.getPresentationTimeInNanos());
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void handleManifestDownloadedEvent(String url, long latencyInNanos) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (isManifestUrl$ATVPlayerHeuristics_release(url)) {
            checkNetworkName();
            getCurrentQoeAggregator().handleManifestDownloadedEvent(latencyInNanos);
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void handleBufferFullnessChangedEvent(boolean isVideo, long bufferFullnessInNanos) {
        if (isVideo) {
            checkNetworkName();
            getCurrentQoeAggregator().handleBufferFullnessChangedEvent(bufferFullnessInNanos);
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void handleCdnSwitchedEvent(ContentUrl newContentUrl) {
        Intrinsics.checkNotNullParameter(newContentUrl, "newContentUrl");
        clear$ATVPlayerHeuristics_release();
        this.currentContentUrl = newContentUrl;
        if (newContentUrl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentContentUrl");
            newContentUrl = null;
        }
        DLog.logf("QoeEvaluator receives CdnSwitchedEvent, current CDN is %s", newContentUrl.getCdnName());
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void onBufferingStart() {
        if (this.isStarted) {
            checkNetworkName();
            getCurrentQoeAggregator().onBufferingStart();
            DLog.logf("QoeEvaluator records buffering start");
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void onBufferingStop() {
        if (this.isStarted) {
            checkNetworkName();
            getCurrentQoeAggregator().onBufferingStop();
            DLog.logf("QoeEvaluator records buffering end");
        }
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public void end() {
        this.isStarted = false;
    }

    public final void reCalculateQoeAndMayTriggerCdnSwitch$ATVPlayerHeuristics_release(long presentationTimeInNanos) {
        reCalculateQoe();
        ContentUrl contentUrl = null;
        if (this.qoeEventTranslator != null && this.qoeConfig.getShouldReportQoeDiagnosticEvent()) {
            QoeEventTranslatorInterface qoeEventTranslatorInterface = this.qoeEventTranslator;
            if (qoeEventTranslatorInterface == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qoeEventTranslator");
                qoeEventTranslatorInterface = null;
            }
            qoeEventTranslatorInterface.postQoeEvent(this.qoeValue, getCurrentQoeAggregator());
        }
        this.aggregatorForQoeChangeSmoothing.addSample(this.qoeValue - this.aggregatorForAvgQoeValue.getAverage());
        this.aggregatorForAvgQoeValue.addSample(this.qoeValue);
        if (!this.qoeConfig.getIsQoeCdnSwitchingEnabled() || this.aggregatorForQoeChangeSmoothing.getAverage() >= this.qoeConfig.getThresholdForCdnSwitching() || getCurrentQoeAggregator().getSampleCount() <= this.qoeConfig.getNumOfSamplesBeforeSwitching() || this.cdnSwitchWindow.isEventCountGreaterThanOrEqualToThreshold()) {
            return;
        }
        QoeEventTranslatorInterface qoeEventTranslatorInterface2 = this.qoeEventTranslator;
        if (qoeEventTranslatorInterface2 != null) {
            if (qoeEventTranslatorInterface2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qoeEventTranslator");
                qoeEventTranslatorInterface2 = null;
            }
            ContentUrl contentUrl2 = this.currentContentUrl;
            if (contentUrl2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentContentUrl");
            } else {
                contentUrl = contentUrl2;
            }
            qoeEventTranslatorInterface2.notifyQoeDecrease(contentUrl, presentationTimeInNanos);
        }
        this.cdnSwitchWindow.recordEvent(TimeSpan.now());
        DLog.logf("QoeEvaluator notifies ContentUrlSelector to switch CDN");
    }

    public final float getBandwidthChangeInput$ATVPlayerHeuristics_release() {
        boolean z = getCurrentQoeAggregator().getAvgBandwidthBps() == 0;
        if (z) {
            return 0.0f;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return this.qoeConfig.getBandwidthChangeWeight() * (getCurrentQoeAggregator().getBandwidthChangeBps() / getCurrentQoeAggregator().getAvgBandwidthBps());
    }

    public final float getLatencyChangeInput$ATVPlayerHeuristics_release() {
        boolean z = getCurrentQoeAggregator().getAvgLatencyMillis() == 0;
        if (z) {
            return 0.0f;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return this.qoeConfig.getLatencyChangeWeight() * (getCurrentQoeAggregator().getLatencyChangeMillis() / getCurrentQoeAggregator().getAvgLatencyMillis());
    }

    public final float getManifestLatencyChangeInput$ATVPlayerHeuristics_release() {
        boolean z = getCurrentQoeAggregator().getAvgManifestLatencyMillis() == 0;
        if (z) {
            return 0.0f;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return this.qoeConfig.getManifestLatencyChangeWeight() * (getCurrentQoeAggregator().getManifestLatencyChangeMillis() / getCurrentQoeAggregator().getAvgManifestLatencyMillis());
    }

    public final float getBandwidthInput$ATVPlayerHeuristics_release() {
        return this.qoeConfig.getBandwidthWeight() * getCurrentQoeAggregator().getBandwidthBps();
    }

    public final float getLatencyInput$ATVPlayerHeuristics_release() {
        return this.qoeConfig.getLatencyWeight() * getCurrentQoeAggregator().getLatencyMillis();
    }

    public final float getManifestLatencyInput$ATVPlayerHeuristics_release() {
        return this.qoeConfig.getManifestLatencyWeight() * getCurrentQoeAggregator().getManifestLatencyMillis();
    }

    private final int getMaxBitrateBps() {
        QualityLevelClamper qualityLevelClamper = this.clamper;
        if (qualityLevelClamper == null) {
            return 0;
        }
        return qualityLevelClamper.getHighestPlayableQuality().getBitrate();
    }

    private final int getMinBitrateBps() {
        QualityLevelClamper qualityLevelClamper = this.clamper;
        if (qualityLevelClamper == null) {
            return 0;
        }
        return qualityLevelClamper.getLowestPlayableQuality().getBitrate();
    }

    public final float getBitrateInput$ATVPlayerHeuristics_release() {
        boolean z = getMaxBitrateBps() - getMinBitrateBps() == 0;
        if (z) {
            return 0.0f;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return this.qoeConfig.getBitrateWeight() * ((getCurrentQoeAggregator().getBitrateBps() - getMinBitrateBps()) / (getMaxBitrateBps() - getMinBitrateBps()));
    }

    public final float getFrontBufferSizeInput$ATVPlayerHeuristics_release() {
        Long l = this.maxFrontBufferSizeMillis;
        if (l == null) {
            return 0.0f;
        }
        long jLongValue = l.longValue();
        if (jLongValue != 0) {
            return getQoeConfig().getFrontBufferSizeWeight() * (getCurrentQoeAggregator().getFrontBufferSizeMillis() / jLongValue);
        }
        return 0.0f;
    }

    public final float getTimeSpentBufferingInput$ATVPlayerHeuristics_release() {
        boolean z = this.qoeConfig.getWindowLengthForTrackingBufferingEvent().getTotalMilliseconds() == 0;
        if (z) {
            return 0.0f;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return this.qoeConfig.getTimeSpentBufferingWeight() * (getCurrentQoeAggregator().getTimeSpentBufferingMillis() / this.qoeConfig.getWindowLengthForTrackingBufferingEvent().getTotalMilliseconds());
    }

    public final float getNumOfBufferingEventInput$ATVPlayerHeuristics_release() {
        boolean z = this.qoeConfig.getWindowLengthForTrackingBufferingEvent().getTotalMilliseconds() == 0;
        if (z) {
            return 0.0f;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        return this.qoeConfig.getNumOfBufferingEventWeight() * (getCurrentQoeAggregator().getNumOfBufferingEvent() / this.qoeConfig.getWindowLengthForTrackingBufferingEvent().getTotalMilliseconds());
    }

    public final void clear$ATVPlayerHeuristics_release() {
        this.aggregatorForAvgQoeValue = new FloatRollingStatisticsAggregator(this.qoeConfig.getNumOfSamplesForAvgQoe());
        this.aggregatorForQoeChangeSmoothing = new FloatRollingStatisticsAggregator(this.qoeConfig.getNumOfSamplesForQoeChangeSmoothing());
        this.qoeAggregators.clear();
        DLog.logf("QoeEvaluator is cleared after CDN switched.");
    }

    public final void checkNetworkName() {
        NetworkHistoryManager networkHistoryManager = this.networkHistoryManager;
        if (networkHistoryManager != null) {
            if (networkHistoryManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkHistoryManager");
                networkHistoryManager = null;
            }
            String currentNetworkName = networkHistoryManager.getCurrentNetworkName();
            if (currentNetworkName == null) {
                currentNetworkName = "null";
            }
            if (Intrinsics.areEqual(currentNetworkName, this.currentNetworkName)) {
                return;
            }
            DLog.devf("QoeEvaluator finds network name changed from %s to %s", this.currentNetworkName, currentNetworkName);
            this.currentNetworkName = currentNetworkName;
        }
    }

    public final boolean isManifestUrl$ATVPlayerHeuristics_release(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt__StringsJVMKt.endsWith$default(url, ".mpd", false, 2, null) || StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) ".mpd?", false, 2, (Object) null) || StringsKt__StringsJVMKt.endsWith$default(url, ".ism", false, 2, null) || StringsKt__StringsJVMKt.endsWith$default(url, ".ism?", false, 2, null);
    }

    @Override // com.amazon.avod.content.urlvending.QoeEvaluatorInterface
    public QoeDiagnosticInfo getQoeDiagnosticInfo() {
        boolean z = this.isStarted;
        if (z) {
            return new QoeDiagnosticInfo(z, getCurrentQoeAggregator().getBandwidthBps(), getCurrentQoeAggregator().getLatencyMillis(), this.qoeValue);
        }
        return new QoeDiagnosticInfo(z);
    }
}
