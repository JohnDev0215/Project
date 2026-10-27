package com.amazon.video.sdk.download;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadChangeListener;
import com.amazon.avod.userdownload.PlaybackDownloadRequest;
import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.amazon.avod.userdownload.reporting.DeletionCause;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.RetryCause;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.video.sdk.download.DownloadOperationEvent;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import com.amazon.video.sdk.stream.TimedTextStreamMatcher;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: DownloadOperationImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0004\u0086\u0001\u0087\u0001Bm\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010(\u001a\u00020'\u0012\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*\u0012\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010*\u0012\u0006\u00100\u001a\u00020!\u0012\b\u00102\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u00105\u001a\u000204\u0012\u0006\u00108\u001a\u000207\u0012\u0006\u0010?\u001a\u00020>\u0012\b\b\u0002\u0010B\u001a\u00020A¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001JI\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u00002\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u00062\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ$\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\nH\u0002J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\nH\u0002J\u0010\u0010 \u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020!H\u0002R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001c\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u001c\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010-R\u0014\u00100\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u0004\u0018\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\"\u00108\u001a\u0002078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR$\u0010D\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u00103\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0018\u0010J\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010M\u001a\u0004\u0018\u00010L8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0018\u0010P\u001a\u00060OR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR \u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020R0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR \u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020R0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010TR \u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020V0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010TR \u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020V0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010TR \u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010TR \u0010[\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010TR \u0010]\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\\0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010TR \u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\\0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010TR \u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010TR \u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010TR \u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010TR \u0010c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010TR \u0010d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010TR \u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010TR \u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010TR \u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010TR \u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010TR \u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010TR$\u0010l\u001a\u00020j2\u0006\u0010k\u001a\u00020j8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bl\u0010m\"\u0004\bn\u0010oR\u001a\u0010q\u001a\u00020p8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bq\u0010r\u001a\u0004\bs\u0010tR\u0018\u0010u\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u00101R\u0016\u0010x\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bx\u0010yR\u0016\u0010{\u001a\u00020z8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b{\u0010|R\u0017\u0010\u0080\u0001\u001a\u0004\u0018\u00010}8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0017\u0010\u0083\u0001\u001a\u00020!8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001¨\u0006\u0088\u0001"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationImpl;", "Lcom/amazon/video/sdk/download/DownloadOperation;", "Lcom/amazon/video/sdk/download/DownloadIdentifiable;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent;", "E", NotificationCompat.CATEGORY_EVENT, "", "Lcom/amazon/video/sdk/download/DownloadOperationEventListener;", "eventListenerSet", "eventOneTimeListenerSet", "", "onDownloadOperationEvent", "(Lcom/amazon/video/sdk/download/DownloadOperationEvent;Ljava/util/Set;Ljava/util/Set;)V", "Lcom/amazon/video/sdk/download/DownloadOperationImpl$DownloadOperationStateTransition;", "transition", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "error", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "newPlaybackDownload", "innerStateChange", "toDeletingState", "toDeletedState", "toErrorState", "setPlaybackDownload", "toPendingStateNewDownload", "download", "processPlaybackDownloadPercentageChange", "processPlaybackDownloadStateChange", "", "modifyUnderlyingState", "toDownloadingState", "toCompletedState", "toPauseState", "", "percent", "isPercentDownloadComplete", "Lcom/amazon/video/sdk/player/ContentConfig;", "config", "Lcom/amazon/video/sdk/player/ContentConfig;", "Lcom/amazon/video/sdk/download/DownloadQuality;", "downloadQuality", "Lcom/amazon/video/sdk/download/DownloadQuality;", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "audioStreamMatchers", "Ljava/util/List;", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "timedTextStreamMatchers", "minimumDownloadProgressForPlayback", "D", "existingPlaybackDownload", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Ljava/util/concurrent/ExecutorService;", "executor", "Ljava/util/concurrent/ExecutorService;", "getExecutor", "()Ljava/util/concurrent/ExecutorService;", "setExecutor", "(Ljava/util/concurrent/ExecutorService;)V", "Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "centralChangeListener", "Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "downloadManager", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "currentPlaybackDownload", "getCurrentPlaybackDownload", "()Lcom/amazon/avod/userdownload/PlaybackDownload;", "setCurrentPlaybackDownload", "(Lcom/amazon/avod/userdownload/PlaybackDownload;)V", "Lcom/amazon/video/sdk/download/DownloadedContentImpl;", "currentDownloadedContent", "Lcom/amazon/video/sdk/download/DownloadedContentImpl;", "Lcom/amazon/avod/userdownload/filter/PlaybackDownloadFilter;", "currentPlaybackDownloadFilter", "Lcom/amazon/avod/userdownload/filter/PlaybackDownloadFilter;", "Lcom/amazon/video/sdk/download/DownloadOperationImpl$DownloadOperationImplChangeListener;", "downloadChangeListener", "Lcom/amazon/video/sdk/download/DownloadOperationImpl$DownloadOperationImplChangeListener;", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadProgress;", "downloadProgressEventListenerSet", "Ljava/util/Set;", "downloadProgressOneTimeEventListenerSet", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadOperationStateChange;", "downloadOperationStateChangeEventListenerSet", "downloadOperationStateChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadOperationError;", "downloadOperationErrorEventListenerSet", "downloadOperationErrorOneTimeEventListenerSet", "Lcom/amazon/video/sdk/download/DownloadOperationEvent$DownloadedContentReadyForPlayback;", "downloadedContentReadyForPlaybackEventListenerSet", "downloadedContentReadyForPlaybackOneTimeEventListenerSet", "", "downloadOperationRefreshLicenseEventListenerSet", "downloadOperationRefreshLicenseOneTimeEventListenerSet", "downloadOperationRefreshLicenseErrorEventListenerSet", "downloadOperationRefreshLicenseErrorOneTimeEventListenerSet", "downloadOperationSyncLicenseEventListenerSet", "downloadOperationSyncLicenseOneTimeEventListenerSet", "downloadOperationSyncLicenseErrorEventListenerSet", "downloadOperationSyncLicenseErrorOneTimeEventListenerSet", "downloadOperationReleaseLicenseEventListenerSet", "downloadOperationReleaseLicenseOneTimeEventListenerSet", "Lcom/amazon/video/sdk/download/DownloadOperationState;", "value", "currentState", "Lcom/amazon/video/sdk/download/DownloadOperationState;", "setCurrentState", "(Lcom/amazon/video/sdk/download/DownloadOperationState;)V", "", "identifier", "Ljava/lang/String;", "getIdentifier", "()Ljava/lang/String;", "currentError", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "currentPercentComplete", "isDownloadedContentReadyForPlaybackReported", "Z", "", "currentFileSize", "J", "Lcom/amazon/video/sdk/download/DownloadedContent;", "getDownloadedContent", "()Lcom/amazon/video/sdk/download/DownloadedContent;", "downloadedContent", "getPercentComplete", "()D", "percentComplete", "<init>", "(Lcom/amazon/video/sdk/player/ContentConfig;Lcom/amazon/video/sdk/download/DownloadQuality;Ljava/util/List;Ljava/util/List;DLcom/amazon/avod/userdownload/PlaybackDownload;Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;)V", "DownloadOperationImplChangeListener", "DownloadOperationStateTransition", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class DownloadOperationImpl implements DownloadOperation, DownloadIdentifiable {
    private final List<AudioStreamMatcher> audioStreamMatchers;
    private final PlaybackDownloadCentralChangeListener centralChangeListener;
    private final ContentConfig config;
    private final Context context;
    private DownloadedContentImpl currentDownloadedContent;
    private PlaybackError currentError;
    private long currentFileSize;
    private double currentPercentComplete;
    private PlaybackDownload currentPlaybackDownload;
    private PlaybackDownloadFilter currentPlaybackDownloadFilter;
    private DownloadOperationState currentState;
    private final DownloadOperationImplChangeListener downloadChangeListener;
    private final PlaybackDownloadManager downloadManager;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadOperationError>> downloadOperationErrorEventListenerSet;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadOperationError>> downloadOperationErrorOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationRefreshLicenseErrorEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationRefreshLicenseErrorOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationRefreshLicenseEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationRefreshLicenseOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationReleaseLicenseEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationReleaseLicenseOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadOperationStateChange>> downloadOperationStateChangeEventListenerSet;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadOperationStateChange>> downloadOperationStateChangeOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationSyncLicenseErrorEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationSyncLicenseErrorOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationSyncLicenseEventListenerSet;
    private final Set<DownloadOperationEventListener<Object>> downloadOperationSyncLicenseOneTimeEventListenerSet;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadProgress>> downloadProgressEventListenerSet;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadProgress>> downloadProgressOneTimeEventListenerSet;
    private final DownloadQuality downloadQuality;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadedContentReadyForPlayback>> downloadedContentReadyForPlaybackEventListenerSet;
    private final Set<DownloadOperationEventListener<DownloadOperationEvent.DownloadedContentReadyForPlayback>> downloadedContentReadyForPlaybackOneTimeEventListenerSet;
    private ExecutorService executor;
    private final PlaybackDownload existingPlaybackDownload;
    private final String identifier;
    private boolean isDownloadedContentReadyForPlaybackReported;
    private final double minimumDownloadProgressForPlayback;
    private final List<TimedTextStreamMatcher> timedTextStreamMatchers;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DownloadOperationImpl.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationImpl$DownloadOperationStateTransition;", "", "(Ljava/lang/String;I)V", "StartTrigger", "PauseTrigger", "ResumeTrigger", "CancelTrigger", "PlaybackDownloadDownloadingEvent", "PlaybackDownloadErrorEvent", "PlaybackDownloadPauseEvent", "PlaybackDownloadQueuedEvent", "PlaybackDownloadCompletedEvent", "PlaybackDownloadDeletedEvent", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    enum DownloadOperationStateTransition {
        StartTrigger,
        PauseTrigger,
        ResumeTrigger,
        CancelTrigger,
        PlaybackDownloadDownloadingEvent,
        PlaybackDownloadErrorEvent,
        PlaybackDownloadPauseEvent,
        PlaybackDownloadQueuedEvent,
        PlaybackDownloadCompletedEvent,
        PlaybackDownloadDeletedEvent
    }

    /* JADX INFO: compiled from: DownloadOperationImpl.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[DownloadOperationState.values().length];
            iArr[DownloadOperationState.Initialized.ordinal()] = 1;
            iArr[DownloadOperationState.Pending.ordinal()] = 2;
            iArr[DownloadOperationState.Downloading.ordinal()] = 3;
            iArr[DownloadOperationState.Paused.ordinal()] = 4;
            iArr[DownloadOperationState.Completed.ordinal()] = 5;
            iArr[DownloadOperationState.Deleting.ordinal()] = 6;
            iArr[DownloadOperationState.Error.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DownloadOperationStateTransition.values().length];
            iArr2[DownloadOperationStateTransition.StartTrigger.ordinal()] = 1;
            iArr2[DownloadOperationStateTransition.ResumeTrigger.ordinal()] = 2;
            iArr2[DownloadOperationStateTransition.CancelTrigger.ordinal()] = 3;
            iArr2[DownloadOperationStateTransition.PlaybackDownloadPauseEvent.ordinal()] = 4;
            iArr2[DownloadOperationStateTransition.PlaybackDownloadQueuedEvent.ordinal()] = 5;
            iArr2[DownloadOperationStateTransition.PlaybackDownloadErrorEvent.ordinal()] = 6;
            iArr2[DownloadOperationStateTransition.PlaybackDownloadDownloadingEvent.ordinal()] = 7;
            iArr2[DownloadOperationStateTransition.PlaybackDownloadCompletedEvent.ordinal()] = 8;
            iArr2[DownloadOperationStateTransition.PauseTrigger.ordinal()] = 9;
            iArr2[DownloadOperationStateTransition.PlaybackDownloadDeletedEvent.ordinal()] = 10;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[UserDownloadState.values().length];
            iArr3[UserDownloadState.DELETE_REQUESTED.ordinal()] = 1;
            iArr3[UserDownloadState.DELETING.ordinal()] = 2;
            iArr3[UserDownloadState.DELETED.ordinal()] = 3;
            iArr3[UserDownloadState.QUEUEING.ordinal()] = 4;
            iArr3[UserDownloadState.QUEUED.ordinal()] = 5;
            iArr3[UserDownloadState.WAITING.ordinal()] = 6;
            iArr3[UserDownloadState.DOWNLOADING.ordinal()] = 7;
            iArr3[UserDownloadState.PAUSED.ordinal()] = 8;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public DownloadOperationImpl(ContentConfig config, DownloadQuality downloadQuality, List<AudioStreamMatcher> list, List<TimedTextStreamMatcher> list2, double d, PlaybackDownload playbackDownload, Context context, ExecutorService executor, PlaybackDownloadCentralChangeListener centralChangeListener, PlaybackDownloadManager downloadManager) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(downloadQuality, "downloadQuality");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(centralChangeListener, "centralChangeListener");
        Intrinsics.checkNotNullParameter(downloadManager, "downloadManager");
        this.config = config;
        this.downloadQuality = downloadQuality;
        this.audioStreamMatchers = list;
        this.timedTextStreamMatchers = list2;
        this.minimumDownloadProgressForPlayback = d;
        this.existingPlaybackDownload = playbackDownload;
        this.context = context;
        this.executor = executor;
        this.centralChangeListener = centralChangeListener;
        this.downloadManager = downloadManager;
        DownloadOperationImplChangeListener downloadOperationImplChangeListener = new DownloadOperationImplChangeListener(this);
        this.downloadChangeListener = downloadOperationImplChangeListener;
        this.downloadProgressEventListenerSet = new LinkedHashSet();
        this.downloadProgressOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationStateChangeEventListenerSet = new LinkedHashSet();
        this.downloadOperationStateChangeOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationErrorEventListenerSet = new LinkedHashSet();
        this.downloadOperationErrorOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadedContentReadyForPlaybackEventListenerSet = new LinkedHashSet();
        this.downloadedContentReadyForPlaybackOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationRefreshLicenseEventListenerSet = new LinkedHashSet();
        this.downloadOperationRefreshLicenseOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationRefreshLicenseErrorEventListenerSet = new LinkedHashSet();
        this.downloadOperationRefreshLicenseErrorOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationSyncLicenseEventListenerSet = new LinkedHashSet();
        this.downloadOperationSyncLicenseOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationSyncLicenseErrorEventListenerSet = new LinkedHashSet();
        this.downloadOperationSyncLicenseErrorOneTimeEventListenerSet = new LinkedHashSet();
        this.downloadOperationReleaseLicenseEventListenerSet = new LinkedHashSet();
        this.downloadOperationReleaseLicenseOneTimeEventListenerSet = new LinkedHashSet();
        this.currentState = DownloadOperationState.Initialized;
        DLog.logf("DWNLD_SDK DownloadOperationImpl ctor %s %s %s %s %s %s", config, downloadQuality, list, list2, Double.valueOf(d), playbackDownload);
        if (playbackDownload != null) {
            setPlaybackDownload(playbackDownload);
            processPlaybackDownloadStateChange(playbackDownload);
            processPlaybackDownloadPercentageChange(playbackDownload);
        }
        centralChangeListener.registerListener(config.getTitleId(), downloadOperationImplChangeListener);
        this.identifier = config.getTitleId();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DownloadOperationImpl(ContentConfig contentConfig, DownloadQuality downloadQuality, List list, List list2, double d, PlaybackDownload playbackDownload, Context context, ExecutorService executorService, PlaybackDownloadCentralChangeListener playbackDownloadCentralChangeListener, PlaybackDownloadManager playbackDownloadManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        PlaybackDownloadManager playbackDownloadManager2;
        if ((i & 512) != 0) {
            PlaybackDownloadManager downloadManager = PlaybackDownloads.getInstance().getDownloadManager();
            Intrinsics.checkNotNullExpressionValue(downloadManager, "getInstance().downloadManager");
            playbackDownloadManager2 = downloadManager;
        } else {
            playbackDownloadManager2 = playbackDownloadManager;
        }
        this(contentConfig, downloadQuality, list, list2, d, playbackDownload, context, executorService, playbackDownloadCentralChangeListener, playbackDownloadManager2);
    }

    private final void setCurrentState(DownloadOperationState downloadOperationState) {
        this.currentState = downloadOperationState;
        onDownloadOperationEvent(new DownloadOperationEvent.DownloadOperationStateChange(downloadOperationState), this.downloadOperationStateChangeEventListenerSet, this.downloadOperationStateChangeOneTimeEventListenerSet);
    }

    /* JADX INFO: compiled from: DownloadOperationImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadOperationImpl$DownloadOperationImplChangeListener;", "Lcom/amazon/avod/userdownload/PlaybackDownloadChangeListener;", "Lcom/google/common/collect/ImmutableSet;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "downloads", "", "onDownloadsAvailabilityChanged", "download", "onDownloadAvailabilityChanged", "onDownloadStateChanged", "onDownloadProgressChanged", "<init>", "(Lcom/amazon/video/sdk/download/DownloadOperationImpl;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    private final class DownloadOperationImplChangeListener implements PlaybackDownloadChangeListener {
        final /* synthetic */ DownloadOperationImpl this$0;

        public DownloadOperationImplChangeListener(DownloadOperationImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadsAvailabilityChanged(ImmutableSet<PlaybackDownload> downloads) {
            Intrinsics.checkNotNullParameter(downloads, "downloads");
            DLog.logf("DWNLD_SDK DownloadOperationImplChangeListener onDownloadsAvailabilityChanged %s", downloads);
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadAvailabilityChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            DLog.logf("DWNLD_SDK DownloadOperationImplChangeListener onDownloadAvailabilityChanged %s", download);
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadStateChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            DLog.logf("DWNLD_SDK DownloadOperationImplChangeListener onDownloadStateChanged %s", download.getState());
            this.this$0.processPlaybackDownloadPercentageChange(download);
            this.this$0.processPlaybackDownloadStateChange(download);
        }

        @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
        public void onDownloadProgressChanged(PlaybackDownload download) {
            Intrinsics.checkNotNullParameter(download, "download");
            this.this$0.processPlaybackDownloadPercentageChange(download);
        }
    }

    private final void innerStateChange(final DownloadOperationStateTransition transition, final PlaybackError error, final PlaybackDownload newPlaybackDownload) {
        ExecutorService executorService = this.executor;
        if (executorService == null) {
            return;
        }
        executorService.execute(new Runnable() { // from class: com.amazon.video.sdk.download.DownloadOperationImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, BoltException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
                DownloadOperationImpl.m2480innerStateChange$lambda2(this.f$0, transition, error, newPlaybackDownload);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX INFO: renamed from: innerStateChange$lambda-2, reason: not valid java name */
    public static final void m2480innerStateChange$lambda2(DownloadOperationImpl this$0, DownloadOperationStateTransition transition, PlaybackError playbackError, PlaybackDownload playbackDownload) throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, BoltException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(transition, "$transition");
        DLog.logf("DWNLD_SDK innerStateChange %s %s %s", this$0.currentState, transition, playbackError);
        switch (WhenMappings.$EnumSwitchMapping$0[this$0.currentState.ordinal()]) {
            case 1:
                switch (WhenMappings.$EnumSwitchMapping$1[transition.ordinal()]) {
                    case 1:
                    case 2:
                        this$0.toPendingStateNewDownload();
                        z = false;
                        break;
                    case 3:
                        this$0.toDeletingState();
                        z = false;
                        break;
                    case 4:
                    case 5:
                        this$0.toPauseState(false);
                        z = false;
                        break;
                    case 6:
                        Intrinsics.checkNotNull(playbackError);
                        this$0.toErrorState(playbackError);
                        z = false;
                        break;
                    case 7:
                        Intrinsics.checkNotNull(playbackDownload);
                        this$0.toDownloadingState(playbackDownload, false);
                        z = false;
                        break;
                    case 8:
                        this$0.toCompletedState();
                        z = false;
                        break;
                    default:
                        z = true;
                        break;
                }
                break;
            case 2:
                int i = WhenMappings.$EnumSwitchMapping$1[transition.ordinal()];
                if (i == 3 || i == 9) {
                    this$0.toDeletingState();
                } else if (i == 5) {
                    Intrinsics.checkNotNull(playbackDownload);
                    this$0.toDownloadingState(playbackDownload, true);
                } else if (i == 6) {
                    Intrinsics.checkNotNull(playbackError);
                    this$0.toErrorState(playbackError);
                } else if (i != 7) {
                    z = true;
                } else {
                    Intrinsics.checkNotNull(playbackDownload);
                    this$0.toDownloadingState(playbackDownload, false);
                }
                z = false;
                break;
            case 3:
                switch (WhenMappings.$EnumSwitchMapping$1[transition.ordinal()]) {
                    case 3:
                        this$0.toDeletingState();
                        z = false;
                        break;
                    case 4:
                        this$0.toPauseState(false);
                        z = false;
                        break;
                    case 5:
                        this$0.toPauseState(false);
                        z = false;
                        break;
                    case 6:
                        Intrinsics.checkNotNull(playbackError);
                        this$0.toErrorState(playbackError);
                        z = false;
                        break;
                    case 7:
                    default:
                        z = true;
                        break;
                    case 8:
                        this$0.toCompletedState();
                        z = false;
                        break;
                    case 9:
                        this$0.toPauseState(true);
                        z = false;
                        break;
                }
                break;
            case 4:
                int i2 = WhenMappings.$EnumSwitchMapping$1[transition.ordinal()];
                if (i2 == 1 || i2 == 2) {
                    PlaybackDownload playbackDownload2 = this$0.currentPlaybackDownload;
                    Intrinsics.checkNotNull(playbackDownload2);
                    this$0.toDownloadingState(playbackDownload2, true);
                } else if (i2 == 3) {
                    this$0.toDeletingState();
                } else if (i2 == 6) {
                    Intrinsics.checkNotNull(playbackError);
                    this$0.toErrorState(playbackError);
                } else if (i2 == 7) {
                    PlaybackDownload playbackDownload3 = this$0.currentPlaybackDownload;
                    Intrinsics.checkNotNull(playbackDownload3);
                    this$0.toDownloadingState(playbackDownload3, false);
                } else if (i2 != 8) {
                    z = true;
                } else {
                    this$0.toCompletedState();
                }
                z = false;
                break;
            case 5:
                int i3 = WhenMappings.$EnumSwitchMapping$1[transition.ordinal()];
                if (i3 == 1 || i3 == 2) {
                    this$0.toCompletedState();
                } else if (i3 != 3) {
                    z = true;
                } else {
                    this$0.toDeletingState();
                }
                z = false;
                break;
            case 6:
                int i4 = WhenMappings.$EnumSwitchMapping$1[transition.ordinal()];
                if (i4 == 6) {
                    Intrinsics.checkNotNull(playbackError);
                    this$0.toErrorState(playbackError);
                } else if (i4 != 10) {
                    z = true;
                } else {
                    this$0.toDeletedState();
                }
                z = false;
                break;
            case 7:
                int i5 = WhenMappings.$EnumSwitchMapping$1[transition.ordinal()];
                if (i5 == 1 || i5 == 2) {
                    this$0.toPendingStateNewDownload();
                } else if (i5 != 3) {
                    z = true;
                } else {
                    this$0.toDeletingState();
                }
                z = false;
                break;
            default:
                z = false;
                break;
        }
        if (z) {
            DLog.warnf("DWNLD_SDK DownloadOperationImpl: innerStateChange from %s to %s ignored", this$0.currentState, transition);
        }
    }

    private final void toDeletingState() {
        PlaybackDownload playbackDownloadOrNull = this.currentPlaybackDownload;
        if (playbackDownloadOrNull == null) {
            playbackDownloadOrNull = this.downloadManager.getDownloadForAsin(this.config.getTitleId(), PlaybackDownloadFilter.acceptAll()).orNull();
        }
        if (playbackDownloadOrNull != null) {
            int i = WhenMappings.$EnumSwitchMapping$2[playbackDownloadOrNull.getState().ordinal()];
            if (i == 1 || i == 2) {
                setCurrentState(DownloadOperationState.Deleting);
            } else if (i == 3) {
                setCurrentState(DownloadOperationState.Deleted);
            } else {
                setCurrentState(DownloadOperationState.Deleting);
                this.downloadManager.delete(playbackDownloadOrNull, DeletionCause.PLAYER_SDK_DELETE);
            }
        } else {
            setCurrentState(DownloadOperationState.Deleted);
        }
        this.currentError = null;
        this.currentPercentComplete = 0.0d;
        this.currentFileSize = 0L;
        this.isDownloadedContentReadyForPlaybackReported = false;
    }

    private final void toDeletedState() {
        this.currentError = null;
        setCurrentState(DownloadOperationState.Deleted);
        this.currentPercentComplete = 0.0d;
        this.currentFileSize = 0L;
        this.isDownloadedContentReadyForPlaybackReported = false;
    }

    private final void toErrorState(PlaybackError error) {
        this.currentError = error;
        setCurrentState(DownloadOperationState.Error);
        onDownloadOperationEvent(new DownloadOperationEvent.DownloadOperationError(error), this.downloadOperationErrorEventListenerSet, this.downloadOperationErrorOneTimeEventListenerSet);
    }

    private final void setPlaybackDownload(PlaybackDownload newPlaybackDownload) {
        if (this.currentPlaybackDownloadFilter == null || !newPlaybackDownload.equals(this.currentPlaybackDownload)) {
            this.currentPlaybackDownloadFilter = PlaybackDownloadFilter.matches(newPlaybackDownload);
        }
        this.currentPlaybackDownload = newPlaybackDownload;
    }

    private final void toPendingStateNewDownload() throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, BoltException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
        Optional<PlaybackDownload> downloadForAsin = this.downloadManager.getDownloadForAsin(this.config.getTitleId(), PlaybackDownloadFilter.acceptAll());
        Intrinsics.checkNotNullExpressionValue(downloadForAsin, "downloadManager.getDownl…wnloadFilter.acceptAll())");
        if (downloadForAsin.isPresent() && downloadForAsin.orNull() != null) {
            DLog.logf("DWNLD_SDK DownloadOperationImpl found existing download with state %s", downloadForAsin.get().getState());
            PlaybackDownload playbackDownload = downloadForAsin.get();
            Intrinsics.checkNotNullExpressionValue(playbackDownload, "existingDownload.get()");
            setPlaybackDownload(playbackDownload);
            PlaybackDownload playbackDownload2 = this.currentPlaybackDownload;
            if ((playbackDownload2 == null ? null : playbackDownload2.getState()) == UserDownloadState.ERROR) {
                DLog.logf("DWNLD_SDK DownloadOperationImpl retrying existing download");
                PlaybackDownloadManager playbackDownloadManager = this.downloadManager;
                PlaybackDownload playbackDownload3 = this.currentPlaybackDownload;
                Intrinsics.checkNotNull(playbackDownload3);
                Optional<PlaybackDownload> optionalRetry = playbackDownloadManager.retry(playbackDownload3, RetryCause.PLAYBACK_SDK_RETRY_INTERNAL);
                Intrinsics.checkNotNullExpressionValue(optionalRetry, "downloadManager.retry(cu…YBACK_SDK_RETRY_INTERNAL)");
                if (optionalRetry.isPresent()) {
                    return;
                }
                toErrorState(new com.amazon.video.sdk.player.error.PlaybackError(StandardErrorCode.DOWNLOAD_UNKNOWN_ERROR, this.context));
                return;
            }
            PlaybackDownload playbackDownload4 = downloadForAsin.get();
            Intrinsics.checkNotNullExpressionValue(playbackDownload4, "existingDownload.get()");
            processPlaybackDownloadStateChange(playbackDownload4);
            PlaybackDownload playbackDownload5 = downloadForAsin.get();
            Intrinsics.checkNotNullExpressionValue(playbackDownload5, "existingDownload.get()");
            processPlaybackDownloadPercentageChange(playbackDownload5);
            PlaybackDownload playbackDownload6 = this.currentPlaybackDownload;
            Intrinsics.checkNotNull(playbackDownload6);
            toDownloadingState(playbackDownload6, true);
            return;
        }
        DLog.logf("DWNLD_SDK DownloadOperationImpl enqueuing new download");
        DownloadEnqueueUserDownloadWrapper downloadEnqueueUserDownloadWrapperEnqueueDownloadOrError = DownloadEnqueueUserDownload.INSTANCE.enqueueDownloadOrError(this.config, this.downloadQuality, this.context, this.audioStreamMatchers, this.timedTextStreamMatchers, this.downloadManager);
        if (downloadEnqueueUserDownloadWrapperEnqueueDownloadOrError.getError() != null) {
            toErrorState(downloadEnqueueUserDownloadWrapperEnqueueDownloadOrError.getError());
            return;
        }
        setCurrentState(DownloadOperationState.Pending);
        PlaybackDownload download = downloadEnqueueUserDownloadWrapperEnqueueDownloadOrError.getDownload();
        Intrinsics.checkNotNull(download);
        setPlaybackDownload(download);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processPlaybackDownloadPercentageChange(PlaybackDownload download) {
        setPlaybackDownload(download);
        this.currentPercentComplete = RangesKt___RangesKt.coerceIn(download.getPercentage(), 0.0d, 100.0d);
        this.currentFileSize = download.getDownloadedFileSize();
        onDownloadOperationEvent(new DownloadOperationEvent.DownloadProgress(this.currentPercentComplete), this.downloadProgressEventListenerSet, this.downloadProgressOneTimeEventListenerSet);
        DLog.logf("DWNLD_SDK DownloadOperationImplChangeListener onDownloadProgressChanged %s %s", Double.valueOf(this.currentPercentComplete), Long.valueOf(this.currentFileSize));
        DownloadedContent downloadedContent = getDownloadedContent();
        if (this.isDownloadedContentReadyForPlaybackReported || !isPercentDownloadComplete(this.currentPercentComplete) || downloadedContent == null || !download.isReadyToWatch()) {
            return;
        }
        onDownloadOperationEvent(new DownloadOperationEvent.DownloadedContentReadyForPlayback(downloadedContent), this.downloadedContentReadyForPlaybackEventListenerSet, this.downloadedContentReadyForPlaybackOneTimeEventListenerSet);
        this.isDownloadedContentReadyForPlaybackReported = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processPlaybackDownloadStateChange(PlaybackDownload download) {
        DLog.logf("DWNLD_SDK processPlaybackDownloadStateChange %s", download.getState());
        setPlaybackDownload(download);
        if (download.getState() == UserDownloadState.ERROR) {
            innerStateChange(DownloadOperationStateTransition.PlaybackDownloadErrorEvent, new com.amazon.video.sdk.player.error.PlaybackError(download.getErrorCode().get(), this.context), download);
            return;
        }
        if (download.getState() == UserDownloadState.PAUSED) {
            innerStateChange(DownloadOperationStateTransition.PlaybackDownloadPauseEvent, null, download);
            return;
        }
        if (download.getState() == UserDownloadState.QUEUEING || download.getState() == UserDownloadState.QUEUED || download.getState() == UserDownloadState.WAITING) {
            innerStateChange(DownloadOperationStateTransition.PlaybackDownloadQueuedEvent, null, download);
            return;
        }
        if (UserDownloadState.WORK_NEEDED_STATES.contains(download.getState())) {
            innerStateChange(DownloadOperationStateTransition.PlaybackDownloadDownloadingEvent, null, download);
            return;
        }
        if (UserDownloadState.COMPLETE_STATES.contains(download.getState())) {
            innerStateChange(DownloadOperationStateTransition.PlaybackDownloadCompletedEvent, null, download);
            return;
        }
        if (download.getState() == UserDownloadState.DELETING || download.getState() == UserDownloadState.DELETE_REQUESTED) {
            innerStateChange(DownloadOperationStateTransition.CancelTrigger, null, null);
        } else {
            if (download.getState() == UserDownloadState.DELETED) {
                innerStateChange(DownloadOperationStateTransition.PlaybackDownloadDeletedEvent, null, null);
                return;
            }
            throw new UnsupportedOperationException();
        }
    }

    private final void toDownloadingState(PlaybackDownload newPlaybackDownload, boolean modifyUnderlyingState) {
        if (modifyUnderlyingState) {
            switch (WhenMappings.$EnumSwitchMapping$2[newPlaybackDownload.getState().ordinal()]) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    this.downloadManager.makeActiveSDK(newPlaybackDownload, MakeActiveCause.PLAYER_SDK_MAKE_ACTIVE);
                    this.downloadManager.resumeSDK(newPlaybackDownload);
                    break;
            }
        }
        setCurrentState(DownloadOperationState.Downloading);
    }

    private final void toCompletedState() {
        setCurrentState(DownloadOperationState.Completed);
    }

    private final void toPauseState(boolean modifyUnderlyingState) {
        if (modifyUnderlyingState) {
            PlaybackDownload playbackDownload = this.currentPlaybackDownload;
            UserDownloadState state = playbackDownload == null ? null : playbackDownload.getState();
            int i = state == null ? -1 : WhenMappings.$EnumSwitchMapping$2[state.ordinal()];
            if (i == 7) {
                PlaybackDownloadManager playbackDownloadManager = this.downloadManager;
                PlaybackDownload playbackDownload2 = this.currentPlaybackDownload;
                Intrinsics.checkNotNull(playbackDownload2);
                playbackDownloadManager.pauseSDK(playbackDownload2);
            } else if (i != 8) {
                PlaybackDownload playbackDownload3 = this.currentPlaybackDownload;
                DLog.logf("DWNLD_SDK DownloadOperationImpl: toPauseState Ignored because we are in state %s", playbackDownload3 != null ? playbackDownload3.getState() : null);
            }
        }
        setCurrentState(DownloadOperationState.Paused);
    }

    public DownloadedContent getDownloadedContent() {
        PlaybackDownload playbackDownloadOrNull;
        if (!isPercentDownloadComplete(this.currentPercentComplete)) {
            return null;
        }
        if (this.currentDownloadedContent == null && (playbackDownloadOrNull = this.downloadManager.getDownloadForAsin(this.config.getTitleId(), PlaybackDownloadFilter.acceptAll()).orNull()) != null) {
            this.currentDownloadedContent = new DownloadedContentImpl(playbackDownloadOrNull, this.context, this.centralChangeListener, this.downloadManager);
        }
        return this.currentDownloadedContent;
    }

    @Override // com.amazon.video.sdk.download.DownloadIdentifiable
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.amazon.video.sdk.download.DownloadOperation
    /* JADX INFO: renamed from: getPercentComplete, reason: from getter */
    public double getCurrentPercentComplete() {
        return this.currentPercentComplete;
    }

    private final boolean isPercentDownloadComplete(double percent) {
        return percent >= this.minimumDownloadProgressForPlayback && percent > 0.0d;
    }

    private final <E extends DownloadOperationEvent> void onDownloadOperationEvent(E event, Set<DownloadOperationEventListener<E>> eventListenerSet, Set<DownloadOperationEventListener<E>> eventOneTimeListenerSet) {
        Iterator<T> it = eventListenerSet.iterator();
        while (it.hasNext()) {
            ((DownloadOperationEventListener) it.next()).on(event);
        }
        Iterator<T> it2 = eventOneTimeListenerSet.iterator();
        while (it2.hasNext()) {
            ((DownloadOperationEventListener) it2.next()).on(event);
        }
        eventOneTimeListenerSet.clear();
    }
}
