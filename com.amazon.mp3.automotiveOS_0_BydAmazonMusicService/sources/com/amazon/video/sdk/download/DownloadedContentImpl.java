package com.amazon.video.sdk.download;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadChangeListener;
import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.download.DownloadedContentEvent;
import com.amazon.video.sdk.download.DownloadedContentState;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadedContentImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001DB)\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\bB\u0010CJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002JI\u0010\u000e\u001a\u00020\u0005\"\b\b\u0000\u0010\b*\u00020\u00072\u0006\u0010\t\u001a\u00028\u00002\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\n2\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\nH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u0003H\u0002R\u0014\u0010\u0013\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010'\u001a\u00060&R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R \u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R \u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010+R \u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010+R \u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010+R \u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010+R \u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010+R \u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010+R \u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010+R \u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u0010+R \u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u0010+R \u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u0010+R \u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u0010+R \u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010+R \u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010+R\u0014\u0010>\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0016\u0010A\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@¨\u0006E"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentImpl;", "Lcom/amazon/video/sdk/download/DownloadedContent;", "Lcom/amazon/video/sdk/download/DownloadIdentifiable;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "newPlaybackDownload", "", "setPlaybackDownload", "Lcom/amazon/video/sdk/download/DownloadedContentEvent;", "E", NotificationCompat.CATEGORY_EVENT, "", "Lcom/amazon/video/sdk/download/DownloadedContentEventListener;", "eventListenerSet", "eventOneTimeListenerSet", "onDownloadedContentEvent", "(Lcom/amazon/video/sdk/download/DownloadedContentEvent;Ljava/util/Set;Ljava/util/Set;)V", "userDownload", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "userDownloadToError", "existingPlaybackDownload", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "centralChangeListener", "Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "playbackDownloadManager", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "currentPlaybackDownload", "getCurrentPlaybackDownload", "()Lcom/amazon/avod/userdownload/PlaybackDownload;", "setCurrentPlaybackDownload", "(Lcom/amazon/avod/userdownload/PlaybackDownload;)V", "Lcom/amazon/avod/userdownload/filter/PlaybackDownloadFilter;", "currentPlaybackDownloadFilter", "Lcom/amazon/avod/userdownload/filter/PlaybackDownloadFilter;", "Lcom/amazon/video/sdk/download/DownloadedContentImpl$DownloadedContentImplChangeListener;", "downloadChangeListener", "Lcom/amazon/video/sdk/download/DownloadedContentImpl$DownloadedContentImplChangeListener;", "", "downloadLicenseRefreshEventListenerSet", "Ljava/util/Set;", "downloadLicenseRefreshOneTimeEventListenerSet", "downloadLicenseRefreshErrorEventListenerSet", "downloadLicenseRefreshErrorOneTimeEventListenerSet", "downloadLicenseSyncEventListenerSet", "downloadLicenseSyncOneTimeEventListenerSet", "downloadLicenseSyncErrorEventListenerSet", "downloadLicenseSyncErrorOneTimeEventListenerSet", "downloadLicenseReleaseEventListenerSet", "downloadLicenseReleaseOneTimeEventListenerSet", "Lcom/amazon/video/sdk/download/DownloadedContentEvent$DownloadedContentStateChange;", "downloadStateChangeEventListenerSet", "downloadStateChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/download/DownloadedContentEvent$DownloadedContentError;", "downloadedContentErrorEventListenerSet", "downloadedContentErrorOneTimeEventListenerSet", "", "getIdentifier", "()Ljava/lang/String;", "identifier", "getError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "error", "<init>", "(Lcom/amazon/avod/userdownload/PlaybackDownload;Landroid/content/Context;Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;)V", "DownloadedContentImplChangeListener", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class DownloadedContentImpl implements DownloadedContent, DownloadIdentifiable {
    private final PlaybackDownloadCentralChangeListener centralChangeListener;
    private final Context context;
    private PlaybackDownload currentPlaybackDownload;
    private PlaybackDownloadFilter currentPlaybackDownloadFilter;
    private final DownloadedContentImplChangeListener downloadChangeListener;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseRefreshErrorEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseRefreshErrorOneTimeEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseRefreshEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseRefreshOneTimeEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseReleaseEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseReleaseOneTimeEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseSyncErrorEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseSyncErrorOneTimeEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseSyncEventListenerSet;
    private final Set<DownloadedContentEventListener<Object>> downloadLicenseSyncOneTimeEventListenerSet;
    private final Set<DownloadedContentEventListener<DownloadedContentEvent.DownloadedContentStateChange>> downloadStateChangeEventListenerSet;
    private final Set<DownloadedContentEventListener<DownloadedContentEvent.DownloadedContentStateChange>> downloadStateChangeOneTimeEventListenerSet;
    private final Set<DownloadedContentEventListener<DownloadedContentEvent.DownloadedContentError>> downloadedContentErrorEventListenerSet;
    private final Set<DownloadedContentEventListener<DownloadedContentEvent.DownloadedContentError>> downloadedContentErrorOneTimeEventListenerSet;
    private final PlaybackDownload existingPlaybackDownload;
    private final PlaybackDownloadManager playbackDownloadManager;

    public DownloadedContentImpl(PlaybackDownload existingPlaybackDownload, Context context, PlaybackDownloadCentralChangeListener centralChangeListener, PlaybackDownloadManager playbackDownloadManager) {
        Intrinsics.checkNotNullParameter(existingPlaybackDownload, "existingPlaybackDownload");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(centralChangeListener, "centralChangeListener");
        Intrinsics.checkNotNullParameter(playbackDownloadManager, "playbackDownloadManager");
        this.existingPlaybackDownload = existingPlaybackDownload;
        this.context = context;
        this.centralChangeListener = centralChangeListener;
        this.playbackDownloadManager = playbackDownloadManager;
        this.currentPlaybackDownload = existingPlaybackDownload;
        DownloadedContentImplChangeListener downloadedContentImplChangeListener = new DownloadedContentImplChangeListener(this);
        this.downloadChangeListener = downloadedContentImplChangeListener;
        this.downloadLicenseRefreshEventListenerSet = new LinkedHashSet();
        this.downloadLicenseRefreshOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadLicenseRefreshErrorEventListenerSet = new LinkedHashSet();
        this.downloadLicenseRefreshErrorOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadLicenseSyncEventListenerSet = new LinkedHashSet();
        this.downloadLicenseSyncOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadLicenseSyncErrorEventListenerSet = new LinkedHashSet();
        this.downloadLicenseSyncErrorOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadLicenseReleaseEventListenerSet = new LinkedHashSet();
        this.downloadLicenseReleaseOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadStateChangeEventListenerSet = new LinkedHashSet();
        this.downloadStateChangeOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadedContentErrorEventListenerSet = new LinkedHashSet();
        this.downloadedContentErrorOneTimeEventListenerSet = new LinkedHashSet();
        setPlaybackDownload(existingPlaybackDownload);
        String asin = existingPlaybackDownload.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "existingPlaybackDownload.asin");
        centralChangeListener.registerListener(asin, downloadedContentImplChangeListener);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DownloadedContentImpl(PlaybackDownload playbackDownload, Context context, PlaybackDownloadCentralChangeListener playbackDownloadCentralChangeListener, PlaybackDownloadManager playbackDownloadManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 8) != 0) {
            playbackDownloadManager = PlaybackDownloads.getInstance().getDownloadManager();
            Intrinsics.checkNotNullExpressionValue(playbackDownloadManager, "getInstance().downloadManager");
        }
        this(playbackDownload, context, playbackDownloadCentralChangeListener, playbackDownloadManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlaybackDownload(PlaybackDownload newPlaybackDownload) {
        if (this.currentPlaybackDownloadFilter == null || !newPlaybackDownload.equals(this.currentPlaybackDownload)) {
            this.currentPlaybackDownloadFilter = PlaybackDownloadFilter.matches(newPlaybackDownload);
        }
        this.currentPlaybackDownload = newPlaybackDownload;
    }

    /* JADX INFO: compiled from: DownloadedContentImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadedContentImpl$DownloadedContentImplChangeListener;", "Lcom/amazon/avod/userdownload/PlaybackDownloadChangeListener;", "Lcom/google/common/collect/ImmutableSet;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "downloads", "", "onDownloadsAvailabilityChanged", "download", "onDownloadAvailabilityChanged", "onDownloadStateChanged", "onDownloadProgressChanged", "<init>", "(Lcom/amazon/video/sdk/download/DownloadedContentImpl;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    private final class DownloadedContentImplChangeListener implements PlaybackDownloadChangeListener {
        final /* synthetic */ DownloadedContentImpl this$0;

        /* JADX INFO: compiled from: DownloadedContentImpl.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DownloadedContentState.values().length];
                iArr[DownloadedContentState.Error.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadProgressChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
        }

        public DownloadedContentImplChangeListener(DownloadedContentImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadsAvailabilityChanged(ImmutableSet<PlaybackDownload> downloads) {
            Intrinsics.checkNotNullParameter(downloads, "downloads");
            DLog.logf("DWNLD_SDK DownloadedContentImplChangeListener onDownloadsAvailabilityChanged %s", downloads);
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadAvailabilityChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            DLog.logf("DWNLD_SDK DownloadedContentImplChangeListener onDownloadAvailabilityChanged %s", download);
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadStateChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            DLog.logf("DWNLD_SDK DownloadedContentImplChangeListener onDownloadStateChanged %s %s", download.getState(), this);
            this.this$0.setPlaybackDownload(download);
            DownloadedContentState.Companion companion = DownloadedContentState.INSTANCE;
            UserDownloadState state = download.getState();
            Intrinsics.checkNotNullExpressionValue(state, "download.state");
            DownloadedContentState downloadedContentStateFromPlaybackDownloadState = companion.fromPlaybackDownloadState(state);
            this.this$0.onDownloadedContentEvent(new DownloadedContentEvent.DownloadedContentStateChange(downloadedContentStateFromPlaybackDownloadState), this.this$0.downloadStateChangeEventListenerSet, this.this$0.downloadStateChangeOneTimeEventListenerSet);
            if (WhenMappings.$EnumSwitchMapping$0[downloadedContentStateFromPlaybackDownloadState.ordinal()] == 1) {
                PlaybackError error = this.this$0.getError();
                if (error != null) {
                    this.this$0.onDownloadedContentEvent(new DownloadedContentEvent.DownloadedContentError(error), this.this$0.downloadedContentErrorEventListenerSet, this.this$0.downloadedContentErrorOneTimeEventListenerSet);
                } else {
                    DLog.warnf("DownloadedContentImpl switched to error state without setting error property: %s", download.getErrorCode());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <E extends DownloadedContentEvent> void onDownloadedContentEvent(E event, Set<DownloadedContentEventListener<E>> eventListenerSet, Set<DownloadedContentEventListener<E>> eventOneTimeListenerSet) {
        DLog.logf("DWNLD_SDK DownloadedContentImpl onDownloadedContentEvent %s", event);
        Iterator<T> it = eventListenerSet.iterator();
        while (it.hasNext()) {
            ((DownloadedContentEventListener) it.next()).on(event);
        }
        Iterator<T> it2 = eventOneTimeListenerSet.iterator();
        while (it2.hasNext()) {
            ((DownloadedContentEventListener) it2.next()).on(event);
        }
        eventOneTimeListenerSet.clear();
    }

    @Override // com.amazon.video.sdk.download.DownloadIdentifiable
    public String getIdentifier() {
        String asin = this.currentPlaybackDownload.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "currentPlaybackDownload.asin");
        return asin;
    }

    public PlaybackError getError() {
        return userDownloadToError(this.currentPlaybackDownload);
    }

    private final PlaybackError userDownloadToError(PlaybackDownload userDownload) {
        MediaErrorCode mediaErrorCodeOrNull = userDownload.getErrorCode().orNull();
        if (mediaErrorCodeOrNull == null) {
            return null;
        }
        return new com.amazon.video.sdk.player.error.PlaybackError(mediaErrorCodeOrNull, this.context);
    }
}
