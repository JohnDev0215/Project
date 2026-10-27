package com.amazon.avod.qos.internal.metrics;

import com.amazon.avod.qos.metadata.QOSEventName;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'AppLoad' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public final class QoSMetric {
    private static final /* synthetic */ QoSMetric[] $VALUES;
    public static final QoSMetric AmountDownloaded;
    public static final QoSMetric AppLoad;
    public static final QoSMetric AudioBitrateChange;
    public static final QoSMetric CacheStatus;
    public static final QoSMetric ConnectionError;
    public static final QoSMetric DownloadCancelled;
    public static final QoSMetric DownloadCompletion;
    public static final QoSMetric DownloadError;
    public static final QoSMetric DownloadException;
    public static final QoSMetric DownloadPaused;
    public static final QoSMetric DownloadQueued;
    public static final QoSMetric DownloadSession;
    public static final QoSMetric DownloadStart;
    public static final QoSMetric DownloadTimeout;
    public static final QoSMetric DrmAcquired;
    public static final QoSMetric DrmAcquisitionError;
    public static final QoSMetric DrmDownloadError;
    public static final QoSMetric DrmPlaybackError;
    public static final QoSMetric DrmRequest;
    public static final QoSMetric Fullscreen;
    public static final QoSMetric Information;
    public static final QoSMetric Metric;
    public static final QoSMetric MultiTrackAudio;
    public static final QoSMetric PlaybackBuffering;
    public static final QoSMetric PlaybackError;
    public static final QoSMetric PlaybackSession;
    public static final QoSMetric PlaybackStart;
    public static final QoSMetric PlaybackStop;
    public static final QoSMetric PlaybackUpdate;
    public static final QoSMetric PurchaseTimeout;
    public static final QoSMetric ReachedEndOfDownloadedRegion;
    public static final QoSMetric ReadyToWatch;
    public static final QoSMetric RendererStatus;
    public static final QoSMetric RetryConnection;
    public static final QoSMetric SeekTableError;
    public static final QoSMetric SmoothStreaming;
    public static final QoSMetric StreamSwitch;
    public static final QoSMetric StreamingBitrateChange;
    public static final QoSMetric TimedText;
    public static final QoSMetric TryConnection;
    private QoSMetricType eventGroup;
    private QOSEventName eventName;

    public static QoSMetric valueOf(String str) {
        return (QoSMetric) Enum.valueOf(QoSMetric.class, str);
    }

    public static QoSMetric[] values() {
        return (QoSMetric[]) $VALUES.clone();
    }

    static {
        QoSMetricType qoSMetricType = QoSMetricType.Miscellaneous;
        QoSMetric qoSMetric = new QoSMetric("AppLoad", 0, qoSMetricType, QOSEventName.AppLoad);
        AppLoad = qoSMetric;
        QoSMetricType qoSMetricType2 = QoSMetricType.Download;
        QoSMetric qoSMetric2 = new QoSMetric("AmountDownloaded", 1, qoSMetricType2, QOSEventName.AmountDownloaded);
        AmountDownloaded = qoSMetric2;
        QoSMetricType qoSMetricType3 = QoSMetricType.Playback;
        QoSMetric qoSMetric3 = new QoSMetric("CacheStatus", 2, qoSMetricType3, QOSEventName.WhisperCache);
        CacheStatus = qoSMetric3;
        QoSMetric qoSMetric4 = new QoSMetric("ConnectionError", 3, qoSMetricType2, QOSEventName.HTTPError);
        ConnectionError = qoSMetric4;
        QoSMetric qoSMetric5 = new QoSMetric("DownloadCancelled", 4, qoSMetricType2, QOSEventName.CancelDownload);
        DownloadCancelled = qoSMetric5;
        QoSMetric qoSMetric6 = new QoSMetric("DownloadCompletion", 5, qoSMetricType2, QOSEventName.StopDownload);
        DownloadCompletion = qoSMetric6;
        QOSEventName qOSEventName = QOSEventName.Error;
        QoSMetric qoSMetric7 = new QoSMetric("DownloadError", 6, qoSMetricType2, qOSEventName);
        DownloadError = qoSMetric7;
        QoSMetric qoSMetric8 = new QoSMetric("DownloadPaused", 7, qoSMetricType2, QOSEventName.PauseDownload);
        DownloadPaused = qoSMetric8;
        QoSMetric qoSMetric9 = new QoSMetric("DownloadQueued", 8, qoSMetricType2, QOSEventName.QueueDownload);
        DownloadQueued = qoSMetric9;
        QoSMetric qoSMetric10 = new QoSMetric("DownloadTimeout", 9, qoSMetricType2, QOSEventName.HTTPTimeout);
        DownloadTimeout = qoSMetric10;
        QoSMetric qoSMetric11 = new QoSMetric("DownloadException", 10, qoSMetricType2, qOSEventName);
        DownloadException = qoSMetric11;
        QoSMetric qoSMetric12 = new QoSMetric("DownloadStart", 11, qoSMetricType2, QOSEventName.StartDownload);
        DownloadStart = qoSMetric12;
        QoSMetric qoSMetric13 = new QoSMetric("DownloadSession", 12, qoSMetricType2, QOSEventName.DownloadSession);
        DownloadSession = qoSMetric13;
        QOSEventName qOSEventName2 = QOSEventName.DRMLicense;
        QoSMetric qoSMetric14 = new QoSMetric("DrmAcquired", 13, qoSMetricType2, qOSEventName2);
        DrmAcquired = qoSMetric14;
        QoSMetric qoSMetric15 = new QoSMetric("DrmAcquisitionError", 14, qoSMetricType2, qOSEventName);
        DrmAcquisitionError = qoSMetric15;
        QoSMetric qoSMetric16 = new QoSMetric("DrmDownloadError", 15, qoSMetricType2, qOSEventName);
        DrmDownloadError = qoSMetric16;
        QoSMetric qoSMetric17 = new QoSMetric("DrmPlaybackError", 16, qoSMetricType3, qOSEventName);
        DrmPlaybackError = qoSMetric17;
        QoSMetric qoSMetric18 = new QoSMetric("DrmRequest", 17, qoSMetricType2, qOSEventName2);
        DrmRequest = qoSMetric18;
        QoSMetric qoSMetric19 = new QoSMetric("PlaybackBuffering", 18, qoSMetricType3, QOSEventName.Buffering);
        PlaybackBuffering = qoSMetric19;
        QoSMetric qoSMetric20 = new QoSMetric("PlaybackError", 19, qoSMetricType3, qOSEventName);
        PlaybackError = qoSMetric20;
        QoSMetric qoSMetric21 = new QoSMetric("PlaybackStart", 20, qoSMetricType3, QOSEventName.StartPlayback);
        PlaybackStart = qoSMetric21;
        QoSMetric qoSMetric22 = new QoSMetric("PlaybackUpdate", 21, qoSMetricType3, QOSEventName.AmountStreamed);
        PlaybackUpdate = qoSMetric22;
        QOSEventName qOSEventName3 = QOSEventName.Information;
        QoSMetric qoSMetric23 = new QoSMetric("RendererStatus", 22, qoSMetricType, qOSEventName3);
        RendererStatus = qoSMetric23;
        QoSMetric qoSMetric24 = new QoSMetric("PlaybackStop", 23, qoSMetricType3, QOSEventName.StopPlayback);
        PlaybackStop = qoSMetric24;
        QoSMetric qoSMetric25 = new QoSMetric("ReachedEndOfDownloadedRegion", 24, qoSMetricType3, QOSEventName.ReachedEndOfDownloadedRegion);
        ReachedEndOfDownloadedRegion = qoSMetric25;
        QoSMetric qoSMetric26 = new QoSMetric("ReadyToWatch", 25, qoSMetricType2, QOSEventName.ReadyToWatch);
        ReadyToWatch = qoSMetric26;
        QoSMetric qoSMetric27 = new QoSMetric("RetryConnection", 26, qoSMetricType2, QOSEventName.RetryConnection);
        RetryConnection = qoSMetric27;
        QoSMetric qoSMetric28 = new QoSMetric("PlaybackSession", 27, qoSMetricType3, QOSEventName.PlaybackSession);
        PlaybackSession = qoSMetric28;
        QoSMetric qoSMetric29 = new QoSMetric("PurchaseTimeout", 28, qoSMetricType, qOSEventName);
        PurchaseTimeout = qoSMetric29;
        QoSMetric qoSMetric30 = new QoSMetric("SmoothStreaming", 29, qoSMetricType, QOSEventName.SmoothStream);
        SmoothStreaming = qoSMetric30;
        QoSMetric qoSMetric31 = new QoSMetric("StreamingBitrateChange", 30, qoSMetricType3, QOSEventName.StreamingBitrateChange);
        StreamingBitrateChange = qoSMetric31;
        QoSMetric qoSMetric32 = new QoSMetric("TryConnection", 31, qoSMetricType2, QOSEventName.TryConnection);
        TryConnection = qoSMetric32;
        QoSMetric qoSMetric33 = new QoSMetric("SeekTableError", 32, qoSMetricType2, qOSEventName);
        SeekTableError = qoSMetric33;
        QoSMetric qoSMetric34 = new QoSMetric("TimedText", 33, qoSMetricType3, QOSEventName.TimedText);
        TimedText = qoSMetric34;
        QoSMetric qoSMetric35 = new QoSMetric("Metric", 34, qoSMetricType, QOSEventName.Metric);
        Metric = qoSMetric35;
        QoSMetric qoSMetric36 = new QoSMetric("MultiTrackAudio", 35, qoSMetricType3, QOSEventName.MultiTrackAudio);
        MultiTrackAudio = qoSMetric36;
        QoSMetric qoSMetric37 = new QoSMetric("StreamSwitch", 36, qoSMetricType3, QOSEventName.StreamSwitch);
        StreamSwitch = qoSMetric37;
        QoSMetric qoSMetric38 = new QoSMetric("AudioBitrateChange", 37, qoSMetricType3, QOSEventName.AudioBitrateChange);
        AudioBitrateChange = qoSMetric38;
        QoSMetric qoSMetric39 = new QoSMetric("Fullscreen", 38, qoSMetricType3, QOSEventName.Fullscreen);
        Fullscreen = qoSMetric39;
        QoSMetric qoSMetric40 = new QoSMetric("Information", 39, qoSMetricType, qOSEventName3);
        Information = qoSMetric40;
        $VALUES = new QoSMetric[]{qoSMetric, qoSMetric2, qoSMetric3, qoSMetric4, qoSMetric5, qoSMetric6, qoSMetric7, qoSMetric8, qoSMetric9, qoSMetric10, qoSMetric11, qoSMetric12, qoSMetric13, qoSMetric14, qoSMetric15, qoSMetric16, qoSMetric17, qoSMetric18, qoSMetric19, qoSMetric20, qoSMetric21, qoSMetric22, qoSMetric23, qoSMetric24, qoSMetric25, qoSMetric26, qoSMetric27, qoSMetric28, qoSMetric29, qoSMetric30, qoSMetric31, qoSMetric32, qoSMetric33, qoSMetric34, qoSMetric35, qoSMetric36, qoSMetric37, qoSMetric38, qoSMetric39, qoSMetric40};
    }

    private QoSMetric(String str, int i, QoSMetricType qoSMetricType, QOSEventName qOSEventName) {
        this.eventGroup = qoSMetricType;
        this.eventName = qOSEventName;
    }

    public QoSMetricType getEventGroup() {
        return this.eventGroup;
    }

    public QOSEventName getEventName() {
        return this.eventName;
    }
}
