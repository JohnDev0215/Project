package com.amazon.video.sdk.download;

import android.content.Context;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadChangeListener;
import com.amazon.avod.userdownload.PlaybackDownloadLocationConfig;
import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.download.DownloadQuality;
import com.amazon.video.sdk.player.ContentConfigData;
import com.amazon.video.sdk.player.VideoType;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import com.amazon.video.sdk.stream.TimedTextStreamMatcher;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: DownloadFeatureImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001:\u0003ABCB\u0017\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b?\u0010@J\f\u0010\u0003\u001a\u00060\u0002R\u00020\u0000H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0014\u0010\u0007\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0002R\u00020\u0000H\u0002J\u0014\u0010\b\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0002R\u00020\u0000H\u0002J\u0014\u0010\t\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0002R\u00020\u0000H\u0002J>\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012\"\b\b\u0000\u0010\u000b*\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010H\u0002R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0018\u0010 \u001a\u00060\u001fR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010-R\u001c\u00101\u001a\n 0*\u0004\u0018\u00010/0/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R8\u00105\u001a&\u0012\f\u0012\n 0*\u0004\u0018\u00010404 0*\u0012\u0012\f\u0012\n 0*\u0004\u0018\u00010404\u0018\u000103038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R*\u00109\u001a\u0002072\u0006\u00108\u001a\u0002078\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>¨\u0006D"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeatureImpl;", "Lcom/amazon/video/sdk/download/DownloadFeature;", "Lcom/amazon/video/sdk/download/DownloadFeatureImpl$DownloadsClassifiedResult;", "getAllDownloads", "", "checkForDownloadStateUpdates", "downloads", "updateAllDownloadedContentImpl", "updateAllDownloadOperationImpl", "updateAllDownloadedContent", "Lcom/amazon/video/sdk/download/DownloadIdentifiable;", "T", "", "oldContent", "Ljava/util/HashSet;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "Lkotlin/collections/HashSet;", "newContent", "Lcom/amazon/video/sdk/download/DownloadFeatureImpl$AddRemove;", "getDownloadUpdateAddRemove", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "config", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/video/sdk/download/DownloadOperationImpl;", "allDownloadOperations", "Ljava/util/List;", "Lcom/amazon/video/sdk/download/DownloadedContentImpl;", "allDownloadedContent", "Lcom/amazon/video/sdk/download/DownloadFeatureImpl$DownloadFeatureImplChangeListener;", "downloadChangeListener", "Lcom/amazon/video/sdk/download/DownloadFeatureImpl$DownloadFeatureImplChangeListener;", "Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "centralChangeListener", "Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "downloadManager", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "Lcom/amazon/avod/userdownload/PlaybackDownloadLocationConfig;", "downloadLocationConfig", "Lcom/amazon/avod/userdownload/PlaybackDownloadLocationConfig;", "", "downloadActiveCount", "I", "downloadCompletedCount", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "kotlin.jvm.PlatformType", "downloadExecutorService", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", "Lcom/google/common/collect/ImmutableSet;", "Lcom/amazon/avod/userdownload/UserDownloadState;", "INCOMPLETE_DOWNLOAD_STATES", "Lcom/google/common/collect/ImmutableSet;", "", "value", "minimumDownloadProgressForPlayback", "D", "getMinimumDownloadProgressForPlayback", "()D", "setMinimumDownloadProgressForPlayback", "(D)V", "<init>", "(Lcom/amazon/video/sdk/download/DownloadFeatureConfig;Landroid/content/Context;)V", "AddRemove", "DownloadFeatureImplChangeListener", "DownloadsClassifiedResult", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class DownloadFeatureImpl implements DownloadFeature {
    private final ImmutableSet<UserDownloadState> INCOMPLETE_DOWNLOAD_STATES;
    private final List<DownloadOperationImpl> allDownloadOperations;
    private final List<DownloadedContentImpl> allDownloadedContent;
    private final PlaybackDownloadCentralChangeListener centralChangeListener;
    private final DownloadFeatureConfig config;
    private final Context context;
    private int downloadActiveCount;
    private final DownloadFeatureImplChangeListener downloadChangeListener;
    private int downloadCompletedCount;
    private final ScheduledThreadPoolExecutor downloadExecutorService;
    private final PlaybackDownloadLocationConfig downloadLocationConfig;
    private final PlaybackDownloadManager downloadManager;
    private double minimumDownloadProgressForPlayback;

    public DownloadFeatureImpl(DownloadFeatureConfig config, Context context) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(context, "context");
        this.config = config;
        this.context = context;
        this.allDownloadOperations = new ArrayList();
        this.allDownloadedContent = new ArrayList();
        DownloadFeatureImplChangeListener downloadFeatureImplChangeListener = new DownloadFeatureImplChangeListener(this);
        this.downloadChangeListener = downloadFeatureImplChangeListener;
        PlaybackDownloadCentralChangeListener playbackDownloadCentralChangeListener = new PlaybackDownloadCentralChangeListener();
        this.centralChangeListener = playbackDownloadCentralChangeListener;
        PlaybackDownloadManager downloadManager = PlaybackDownloads.getInstance().getDownloadManager();
        Intrinsics.checkNotNullExpressionValue(downloadManager, "getInstance().downloadManager");
        this.downloadManager = downloadManager;
        PlaybackDownloadLocationConfig locationConfig = PlaybackDownloads.getInstance().getLocationConfig();
        Intrinsics.checkNotNullExpressionValue(locationConfig, "getInstance().locationConfig");
        this.downloadLocationConfig = locationConfig;
        this.downloadExecutorService = ScheduledExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(1).build();
        this.INCOMPLETE_DOWNLOAD_STATES = ImmutableSet.of(UserDownloadState.QUEUEING, UserDownloadState.QUEUED, UserDownloadState.PAUSED, UserDownloadState.WAITING, UserDownloadState.DOWNLOADING);
        downloadManager.addDownloadChangeListener(downloadFeatureImplChangeListener);
        downloadManager.addDownloadChangeListener(playbackDownloadCentralChangeListener);
        updateAllDownloadedContent(getAllDownloads());
    }

    @Override // com.amazon.video.sdk.download.DownloadFeature
    public double getMinimumDownloadProgressForPlayback() {
        return this.minimumDownloadProgressForPlayback;
    }

    @Override // com.amazon.video.sdk.download.DownloadFeature
    public void setMinimumDownloadProgressForPlayback(double d) {
        this.minimumDownloadProgressForPlayback = RangesKt___RangesKt.coerceIn(d, 0.0d, 100.0d);
    }

    /* JADX INFO: compiled from: DownloadFeatureImpl.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeatureImpl$DownloadsClassifiedResult;", "", "(Lcom/amazon/video/sdk/download/DownloadFeatureImpl;)V", "downloadedContent", "Ljava/util/HashSet;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "Lkotlin/collections/HashSet;", "getDownloadedContent", "()Ljava/util/HashSet;", "downloadingContent", "getDownloadingContent", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class DownloadsClassifiedResult {
        private final HashSet<PlaybackDownload> downloadedContent;
        private final HashSet<PlaybackDownload> downloadingContent;
        final /* synthetic */ DownloadFeatureImpl this$0;

        public DownloadsClassifiedResult(DownloadFeatureImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.downloadedContent = new HashSet<>();
            this.downloadingContent = new HashSet<>();
        }

        public final HashSet<PlaybackDownload> getDownloadedContent() {
            return this.downloadedContent;
        }

        public final HashSet<PlaybackDownload> getDownloadingContent() {
            return this.downloadingContent;
        }
    }

    private final DownloadsClassifiedResult getAllDownloads() {
        DownloadsClassifiedResult downloadsClassifiedResult = new DownloadsClassifiedResult(this);
        PlaybackDownloadFilter playbackDownloadFilterAcceptAll = PlaybackDownloadFilter.acceptAll();
        Intrinsics.checkNotNullExpressionValue(playbackDownloadFilterAcceptAll, "acceptAll()");
        ImmutableSet<PlaybackDownload> downloads = PlaybackDownloads.getInstance().getDownloadManager().getDownloads(playbackDownloadFilterAcceptAll);
        Intrinsics.checkNotNullExpressionValue(downloads, "getInstance().downloadMa…Downloads(downloadFilter)");
        UnmodifiableIterator<PlaybackDownload> it = downloads.iterator();
        while (it.hasNext()) {
            PlaybackDownload next = it.next();
            if (this.INCOMPLETE_DOWNLOAD_STATES.contains(next.getState()) || (next.getState() == UserDownloadState.ERROR && !next.isCompleted())) {
                downloadsClassifiedResult.getDownloadingContent().add(next);
            } else if (next.isCompleted()) {
                downloadsClassifiedResult.getDownloadedContent().add(next);
            }
        }
        return downloadsClassifiedResult;
    }

    /* JADX INFO: compiled from: DownloadFeatureImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeatureImpl$DownloadFeatureImplChangeListener;", "Lcom/amazon/avod/userdownload/PlaybackDownloadChangeListener;", "Lcom/google/common/collect/ImmutableSet;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "downloads", "", "onDownloadsAvailabilityChanged", "download", "onDownloadAvailabilityChanged", "onDownloadStateChanged", "onDownloadProgressChanged", "<init>", "(Lcom/amazon/video/sdk/download/DownloadFeatureImpl;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    private final class DownloadFeatureImplChangeListener implements PlaybackDownloadChangeListener {
        final /* synthetic */ DownloadFeatureImpl this$0;

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadProgressChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadsAvailabilityChanged(ImmutableSet<PlaybackDownload> downloads) {
            Intrinsics.checkNotNullParameter(downloads, "downloads");
        }

        public DownloadFeatureImplChangeListener(DownloadFeatureImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadAvailabilityChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            DLog.logf("DWNLD_SDK DownloadFeatureImpl onDownloadAvailabilityChanged %s", download.getState());
            this.this$0.checkForDownloadStateUpdates();
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadStateChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            DLog.logf("DWNLD_SDK DownloadFeatureImpl onDownloadStateChanged %s", download.getState());
            this.this$0.checkForDownloadStateUpdates();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkForDownloadStateUpdates() {
        DownloadsClassifiedResult allDownloads = getAllDownloads();
        int size = allDownloads.getDownloadingContent().size();
        int size2 = allDownloads.getDownloadedContent().size();
        int i = this.downloadActiveCount;
        if (size == i && size2 == this.downloadCompletedCount) {
            return;
        }
        DLog.logf("DWNLD_SDK DownloadFeatureImpl onDownloadStateChanged %d %d %d %d", Integer.valueOf(i), Integer.valueOf(size), Integer.valueOf(this.downloadCompletedCount), Integer.valueOf(size2));
        updateAllDownloadedContent(allDownloads);
    }

    private final void updateAllDownloadedContentImpl(DownloadsClassifiedResult downloads) {
        AddRemove downloadUpdateAddRemove = getDownloadUpdateAddRemove(this.allDownloadedContent, downloads.getDownloadedContent());
        List<PlaybackDownload> toAdd = downloadUpdateAddRemove.getToAdd();
        List toRemove = downloadUpdateAddRemove.getToRemove();
        for (PlaybackDownload playbackDownload : toAdd) {
            Intrinsics.checkNotNull(playbackDownload);
            this.allDownloadedContent.add(new DownloadedContentImpl(playbackDownload, this.context, this.centralChangeListener, null, 8, null));
        }
        this.allDownloadedContent.removeAll(toRemove);
    }

    private final void updateAllDownloadOperationImpl(DownloadsClassifiedResult downloads) {
        AddRemove downloadUpdateAddRemove = getDownloadUpdateAddRemove(this.allDownloadOperations, downloads.getDownloadingContent());
        List<PlaybackDownload> toAdd = downloadUpdateAddRemove.getToAdd();
        List toRemove = downloadUpdateAddRemove.getToRemove();
        for (PlaybackDownload playbackDownload : toAdd) {
            if (playbackDownload != null) {
                String playerSDKPlaybackToken = playbackDownload.getPlayerSDKPlaybackToken();
                Intrinsics.checkNotNull(playerSDKPlaybackToken);
                Intrinsics.checkNotNullExpressionValue(playerSDKPlaybackToken, "download.playerSDKPlaybackToken!!");
                String asin = playbackDownload.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin, "download.asin");
                Map<String, String> playbackSessionContext = playbackDownload.getPlaybackSessionContext();
                Intrinsics.checkNotNullExpressionValue(playbackSessionContext, "download.playbackSessionContext");
                ContentConfigData contentConfigData = new ContentConfigData(playerSDKPlaybackToken, asin, null, VideoType.VOD, playbackSessionContext, null, false, false, 224, null);
                DownloadQuality.Companion companion = DownloadQuality.INSTANCE;
                MediaQuality downloadQuality = playbackDownload.getDownloadQuality();
                Intrinsics.checkNotNullExpressionValue(downloadQuality, "download.downloadQuality");
                DownloadQuality downloadQualityMediaQualityToDownloadQuality = companion.mediaQualityToDownloadQuality(downloadQuality);
                List<AudioStreamMatcher> listFromJSON = AudioStreamMatcher.INSTANCE.fromJSON(playbackDownload.getPlayerSDKAudioStreamMatchers());
                List<TimedTextStreamMatcher> listFromJSON2 = TimedTextStreamMatcher.INSTANCE.fromJSON(playbackDownload.getPlayerSDKTimedTextStreamMatchers());
                double minimumDownloadProgressForPlayback = getMinimumDownloadProgressForPlayback();
                Context context = this.context;
                ScheduledThreadPoolExecutor downloadExecutorService = this.downloadExecutorService;
                Intrinsics.checkNotNullExpressionValue(downloadExecutorService, "downloadExecutorService");
                this.allDownloadOperations.add(new DownloadOperationImpl(contentConfigData, downloadQualityMediaQualityToDownloadQuality, listFromJSON, listFromJSON2, minimumDownloadProgressForPlayback, playbackDownload, context, downloadExecutorService, this.centralChangeListener, null, 512, null));
            }
        }
        this.allDownloadOperations.removeAll(toRemove);
    }

    private final void updateAllDownloadedContent(DownloadsClassifiedResult downloads) {
        this.downloadActiveCount = downloads.getDownloadingContent().size();
        this.downloadCompletedCount = downloads.getDownloadedContent().size();
        updateAllDownloadedContentImpl(downloads);
        updateAllDownloadOperationImpl(downloads);
    }

    /* JADX INFO: compiled from: DownloadFeatureImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0003¢\u0006\u0004\b\u000e\u0010\u000fR*\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeatureImpl$AddRemove;", "T", "", "", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "toAdd", "Ljava/util/List;", "getToAdd", "()Ljava/util/List;", "setToAdd", "(Ljava/util/List;)V", "toRemove", "getToRemove", "setToRemove", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    private static final class AddRemove<T> {
        private List<? extends PlaybackDownload> toAdd;
        private List<? extends T> toRemove;

        public AddRemove(List<? extends PlaybackDownload> toAdd, List<? extends T> toRemove) {
            Intrinsics.checkNotNullParameter(toAdd, "toAdd");
            Intrinsics.checkNotNullParameter(toRemove, "toRemove");
            this.toAdd = toAdd;
            this.toRemove = toRemove;
        }

        public final List<PlaybackDownload> getToAdd() {
            return this.toAdd;
        }

        public final List<T> getToRemove() {
            return this.toRemove;
        }
    }

    private final <T extends DownloadIdentifiable> AddRemove<T> getDownloadUpdateAddRemove(List<T> oldContent, HashSet<PlaybackDownload> newContent) {
        HashMap map = new HashMap();
        for (T t : oldContent) {
            map.put(t.getIdentifier(), t);
        }
        HashMap map2 = new HashMap();
        for (PlaybackDownload download : newContent) {
            String asin = download.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "download.asin");
            Intrinsics.checkNotNullExpressionValue(download, "download");
            map2.put(asin, download);
        }
        Set setKeySet = map2.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "newAsinsHashMap.keys");
        Set setKeySet2 = map.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet2, "existingAsinsHashMap.keys");
        Set setSubtract = CollectionsKt___CollectionsKt.subtract(setKeySet, setKeySet2);
        Set setKeySet3 = map.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet3, "existingAsinsHashMap.keys");
        Set setKeySet4 = map2.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet4, "newAsinsHashMap.keys");
        Set setSubtract2 = CollectionsKt___CollectionsKt.subtract(setKeySet3, setKeySet4);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setSubtract, 10));
        Iterator it = setSubtract.iterator();
        while (it.hasNext()) {
            arrayList.add((PlaybackDownload) map2.get((String) it.next()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setSubtract2, 10));
        Iterator it2 = setSubtract2.iterator();
        while (it2.hasNext()) {
            arrayList2.add((DownloadIdentifiable) map.get((String) it2.next()));
        }
        return new AddRemove<>(arrayList, arrayList2);
    }
}
