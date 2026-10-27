package com.amazon.video.sdk.player;

import android.view.Surface;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.connectivity.ConnectivityChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.connectivity.NetworkType;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.core.CachedPresentationCallback;
import com.amazon.avod.core.RothkoPlayer;
import com.amazon.avod.fsm.BlockingStateMachine;
import com.amazon.avod.fsm.StateBase;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdEnabledVideoPresentation;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.EmptyAdPlan;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.events.AloysiusCyclicEventMasterController;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.service.cache.PlayerResourcesCache;
import com.amazon.avod.media.service.cache.PlayerResourcesCacheRequest;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackContentEventListener;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.PlaybackTimeDataEventListener;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.playbackclient.presentation.CachedVideoPresentation;
import com.amazon.avod.playbackclient.presentation.LimitedInstancePresentationCache;
import com.amazon.avod.playbackclient.presentation.PrepareFailedException;
import com.amazon.avod.playbackclient.presentation.PresentationCache;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.PVDownloadsPlayerShim;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SDKPlayerLogger;
import com.amazon.avod.util.Throwables2;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.video.sdk.PlayerSdkConfig;
import com.amazon.video.sdk.avod.playbackclient.debug.DebugDialogFeature;
import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.player.PlayerEvent;
import com.amazon.video.sdk.player.PlayerImpl;
import com.amazon.video.sdk.player.error.ErrorType;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.player.fsm.Triggers;
import com.amazon.video.sdk.player.fsm.Triggers.Load;
import com.amazon.video.sdk.player.fsm.Triggers.Play;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureFactory;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureFactoryImpl;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureImpl;
import com.amazon.video.sdk.player.timeline.ContentType;
import com.amazon.video.sdk.player.timeline.PlayableRange;
import com.amazon.video.sdk.player.timeline.PlayableRangeData;
import com.amazon.video.sdk.player.timeline.TimeData;
import com.amazon.video.sdk.player.timeline.TimeDataImpl;
import com.amazon.video.sdk.player.timeline.TimeUnit;
import com.amazon.video.sdk.player.timeline.Timeline;
import com.amazon.video.sdk.player.timeline.TimelineDelegate;
import com.amazon.video.sdk.player.timeline.TimelineImpl;
import com.amazon.video.sdk.player.timeline.TimelineItem;
import com.amazon.video.sdk.player.timeline.TimelineItemData;
import com.amazon.video.sdk.player.util.UninitializedInstance;
import com.amazon.video.sdk.player.util.VideoPlayerUtils;
import com.amazon.video.sdk.player.util.VideoSpecUtil;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureImpl;
import com.amazon.video.sdk.stream.StreamFeatureImpl;
import com.amazon.video.sdk.stream.TimedTextStreamGroup;
import com.amazon.video.sdk.stream.util.MultiTrackAudioUtils;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.perf.plugin.util.AsmString;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: PlayerImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000î\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0010\u0018\u0000 º\u00022\u00020\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u00020\u0005:*»\u0002¼\u0002º\u0002½\u0002¾\u0002¿\u0002À\u0002Á\u0002Â\u0002Ã\u0002Ä\u0002Å\u0002Æ\u0002Ç\u0002È\u0002É\u0002Ê\u0002Ë\u0002Ì\u0002Í\u0002Î\u0002BÓ\u0001\u0012\u0006\u0010?\u001a\u00020>\u0012\u0006\u0010D\u001a\u00020C\u0012\u0006\u0010I\u001a\u00020H\u0012\b\b\u0002\u0010L\u001a\u00020K\u0012\b\b\u0002\u0010O\u001a\u00020N\u0012\b\b\u0002\u0010R\u001a\u00020Q\u0012\b\b\u0002\u0010U\u001a\u00020T\u0012\n\b\u0002\u0010³\u0002\u001a\u00030\u0081\u0002\u0012\n\b\u0002\u0010´\u0002\u001a\u00030ü\u0001\u0012\n\b\u0002\u0010µ\u0002\u001a\u00030\u0086\u0002\u0012\n\b\u0002\u0010·\u0002\u001a\u00030¶\u0002\u0012\b\b\u0002\u0010X\u001a\u00020W\u0012\b\b\u0002\u0010[\u001a\u00020Z\u0012\b\b\u0002\u0010^\u001a\u00020]\u0012\b\b\u0002\u0010a\u001a\u00020`\u0012\b\b\u0002\u0010d\u001a\u00020c\u0012\b\b\u0002\u0010g\u001a\u00020f\u0012\b\b\u0002\u0010j\u001a\u00020i\u0012\b\b\u0002\u0010l\u001a\u00020\u000f\u0012\b\b\u0002\u0010o\u001a\u00020n¢\u0006\u0006\b¸\u0002\u0010¹\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\bH\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016J.\u0010\"\u001a\u00020\b\"\b\b\u0000\u0010\u001d*\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0016J.\u0010#\u001a\u00020\b\"\b\b\u0000\u0010\u001d*\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0016J\b\u0010$\u001a\u00020\bH\u0016J\u0014\u0010'\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040%JG\u0010,\u001a\u00020\b\"\b\b\u0000\u0010\u001d*\u00020\u001c2\u0006\u0010(\u001a\u00028\u00002\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 0)2\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 0)¢\u0006\u0004\b,\u0010-J\u0010\u00100\u001a\u00020\b2\b\u0010/\u001a\u0004\u0018\u00010.J\u0006\u00101\u001a\u00020\bJ\u0006\u00102\u001a\u00020\bJ\u000e\u00105\u001a\u00020\b2\u0006\u00104\u001a\u000203J\u0006\u00106\u001a\u00020\bJ\"\u0010<\u001a\u00020\n2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\n2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010:J\b\u0010=\u001a\u00020\u000fH\u0016R\u0017\u0010?\u001a\u00020>8\u0006¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u0017\u0010D\u001a\u00020C8\u0006¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010U\u001a\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010VR\u0014\u0010X\u001a\u00020W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010j\u001a\u00020i8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010l\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bo\u0010pR\u0014\u0010r\u001a\u00020q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\br\u0010sR\u0014\u0010u\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bu\u0010vR\u0018\u0010x\u001a\u00060wR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010yR\u0018\u0010{\u001a\u00060zR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|R\u0018\u0010~\u001a\u00060}R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u001c\u0010\u0081\u0001\u001a\u00070\u0080\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001c\u0010\u0084\u0001\u001a\u00070\u0083\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001c\u0010\u0087\u0001\u001a\u00070\u0086\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\u001c\u0010\u008a\u0001\u001a\u00070\u0089\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u001c\u0010\u008d\u0001\u001a\u00070\u008c\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001c\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u008f\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u001c\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0092\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001c\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0095\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001a\u0010\u0099\u0001\u001a\u00030\u0098\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001c\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009b\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u001b\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009e\u0001\u0010\u009f\u0001R\u001a\u0010¡\u0001\u001a\u00030 \u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¡\u0001\u0010¢\u0001R\u001c\u0010¤\u0001\u001a\u0005\u0018\u00010£\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¤\u0001\u0010¥\u0001R\u0016\u0010¦\u0001\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b¦\u0001\u0010vR\u001c\u0010¨\u0001\u001a\u0005\u0018\u00010§\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¨\u0001\u0010©\u0001R\u001c\u0010ª\u0001\u001a\u0005\u0018\u00010§\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bª\u0001\u0010©\u0001R\u001a\u0010¬\u0001\u001a\u00030«\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¬\u0001\u0010\u00ad\u0001R\u001a\u0010¯\u0001\u001a\u00030®\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¯\u0001\u0010°\u0001R\u001a\u0010²\u0001\u001a\u00030±\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b²\u0001\u0010³\u0001R\u001a\u0010µ\u0001\u001a\u00030´\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R\u001c\u0010¸\u0001\u001a\u0005\u0018\u00010·\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¸\u0001\u0010¹\u0001R\u001c\u0010»\u0001\u001a\u0005\u0018\u00010º\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b»\u0001\u0010¼\u0001R,\u0010¿\u0001\u001a\u00030½\u00012\b\u0010¾\u0001\u001a\u00030½\u00018\u0002@BX\u0082\u000e¢\u0006\u0010\n\u0006\b¿\u0001\u0010À\u0001\"\u0006\bÁ\u0001\u0010Â\u0001R2\u0010Ä\u0001\u001a\u00030Ã\u00018\u0006@\u0006X\u0087\u000e¢\u0006 \n\u0006\bÄ\u0001\u0010Å\u0001\u0012\u0006\bÊ\u0001\u0010Ë\u0001\u001a\u0006\bÆ\u0001\u0010Ç\u0001\"\u0006\bÈ\u0001\u0010É\u0001R+\u0010Ì\u0001\u001a\u0004\u0018\u00010\u00168\u0010@\u0010X\u0090\u000e¢\u0006\u0018\n\u0006\bÌ\u0001\u0010Í\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001\"\u0006\bÐ\u0001\u0010Ñ\u0001R\u0016\u0010Ò\u0001\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\bÒ\u0001\u0010vR\u0016\u0010Ó\u0001\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\bÓ\u0001\u0010vR\u0016\u0010Ô\u0001\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\bÔ\u0001\u0010vR\u0018\u0010Ö\u0001\u001a\u00030Õ\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÖ\u0001\u0010×\u0001R$\u0010Ù\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030Ø\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÙ\u0001\u0010Ú\u0001R$\u0010Û\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030Ø\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÛ\u0001\u0010Ú\u0001R$\u0010Ý\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030Ü\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÝ\u0001\u0010Ú\u0001R$\u0010Þ\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030Ü\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÞ\u0001\u0010Ú\u0001R$\u0010à\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ß\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bà\u0001\u0010Ú\u0001R$\u0010á\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ß\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bá\u0001\u0010Ú\u0001R$\u0010ã\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030â\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bã\u0001\u0010Ú\u0001R$\u0010ä\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030â\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bä\u0001\u0010Ú\u0001R$\u0010æ\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030å\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bæ\u0001\u0010Ú\u0001R$\u0010ç\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030å\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bç\u0001\u0010Ú\u0001R$\u0010é\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030è\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bé\u0001\u0010Ú\u0001R$\u0010ê\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030è\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bê\u0001\u0010Ú\u0001R$\u0010ì\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ë\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bì\u0001\u0010Ú\u0001R$\u0010í\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ë\u00010 0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bí\u0001\u0010Ú\u0001R\u0018\u0010ï\u0001\u001a\u00030î\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bï\u0001\u0010ð\u0001R\u001c\u0010ò\u0001\u001a\u00070ñ\u0001R\u00020\u00008\u0002X\u0082\u0004¢\u0006\b\n\u0006\bò\u0001\u0010ó\u0001R\"\u0010õ\u0001\u001a\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030)0ô\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bõ\u0001\u0010Ú\u0001R\u0019\u0010ö\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bö\u0001\u0010÷\u0001R\u0018\u0010ù\u0001\u001a\u00030ø\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bù\u0001\u0010ú\u0001R\u0018\u0010û\u0001\u001a\u00030ø\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bû\u0001\u0010ú\u0001R \u0010ý\u0001\u001a\u00030ü\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bý\u0001\u0010þ\u0001\u001a\u0006\bÿ\u0001\u0010\u0080\u0002R \u0010\u0082\u0002\u001a\u00030\u0081\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0082\u0002\u0010\u0083\u0002\u001a\u0006\b\u0084\u0002\u0010\u0085\u0002R \u0010\u0087\u0002\u001a\u00030\u0086\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0087\u0002\u0010\u0088\u0002\u001a\u0006\b\u0089\u0002\u0010\u008a\u0002R \u0010\u008c\u0002\u001a\u00030\u008b\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u008c\u0002\u0010\u008d\u0002\u001a\u0006\b\u008e\u0002\u0010\u008f\u0002R$\u0010\u0091\u0002\u001a\u00070\u0090\u0002R\u00020\u00008\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0091\u0002\u0010\u0092\u0002\u001a\u0006\b\u0093\u0002\u0010\u0094\u0002R4\u0010\u0096\u0002\u001a\u00030\u0095\u00022\b\u0010\u0096\u0002\u001a\u00030\u0095\u00028\u0016@VX\u0096\u000e¢\u0006\u0018\n\u0006\b\u0096\u0002\u0010\u0097\u0002\u001a\u0006\b\u0098\u0002\u0010\u0099\u0002\"\u0006\b\u009a\u0002\u0010\u009b\u0002R\u0019\u0010\u009c\u0002\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009c\u0002\u0010\u009d\u0002R\u0017\u00109\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b9\u0010÷\u0001R\u0016\u0010\u009e\u0002\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u009e\u0002\u0010vR\u0016\u0010\u009f\u0002\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u009f\u0002\u0010vR!\u0010 \u0002\u001a\u00020\n8B@\u0002X\u0082\u000e¢\u0006\u0010\n\u0006\b \u0002\u0010÷\u0001\u001a\u0006\b¡\u0002\u0010¢\u0002R\u001a\u0010¥\u0002\u001a\u0005\u0018\u00010§\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b£\u0002\u0010¤\u0002R\u0018\u0010¨\u0002\u001a\u00030®\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b¦\u0002\u0010§\u0002R\u0018\u0010«\u0002\u001a\u00030«\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b©\u0002\u0010ª\u0002R\u0017\u0010¬\u0002\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¬\u0002\u0010\u00ad\u0002R\u0017\u0010®\u0002\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\b\u001a\u0006\b®\u0002\u0010\u00ad\u0002R(\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b¯\u0002\u0010°\u0002\"\u0006\b±\u0002\u0010²\u0002¨\u0006Ï\u0002"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl;", "Lcom/amazon/video/sdk/player/Player;", "Lcom/amazon/avod/fsm/BlockingStateMachine;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState$Type;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "Lcom/amazon/avod/core/RothkoPlayer;", "Lcom/amazon/video/sdk/player/RenderingConfig;", "renderingConfig", "", "updateRenderingConfig", "", "position", "initializeTimeData", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "getPlayableRange", "", "isFatal", "checkVideoPlayerInitialized", "Lcom/amazon/avod/media/playback/VideoPresentation;", "presentation", "setPresentationAndAttachListeners", "unload", "Lcom/amazon/video/sdk/player/ContentConfig;", "content", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "pause", PageUriUtils.DO_PLAY_VALUE, "seek", "Lcom/amazon/video/sdk/player/PlayerEvent;", "E", "Ljava/lang/Class;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/video/sdk/player/EventListener;", "callback", DebugKt.DEBUG_PROPERTY_VALUE_ON, DebugKt.DEBUG_PROPERTY_VALUE_OFF, "destroy", "Lcom/amazon/avod/fsm/Trigger;", "trigger", "doTriggerAsync", "playerEvent", "", "playbackEventListenerSet", "playbackEventOneTimeListenerSet", "onPlayerEvent", "(Lcom/amazon/video/sdk/player/PlayerEvent;Ljava/util/Set;Ljava/util/Set;)V", "Lcom/amazon/avod/media/error/MediaErrorCode;", "errorCode", "handleMediaError", "reportInteractionPlay", "reportInteractionStop", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter$Type;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "reportEvent", "reportPlaybackExit", "Lcom/amazon/video/sdk/player/timeline/ContentType;", "contentType", "currentPosition", "", "adId", "getTimelineItemIndex", "doVerboseLogging", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "sdkConfig", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "getSdkConfig", "()Lcom/amazon/video/sdk/PlayerSdkConfig;", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "Lcom/amazon/video/sdk/player/PlayerConfig;", "getConfig", "()Lcom/amazon/video/sdk/player/PlayerConfig;", "Lcom/amazon/video/sdk/content/LocalContentManager;", "localContentManager", "Lcom/amazon/video/sdk/content/LocalContentManager;", "Ljava/util/concurrent/ExecutorService;", "executor", "Ljava/util/concurrent/ExecutorService;", "Lcom/amazon/avod/playbackclient/presentation/PresentationCache;", "presentationCache", "Lcom/amazon/avod/playbackclient/presentation/PresentationCache;", "Lcom/amazon/video/sdk/player/timeline/TimelineDelegate;", "timelineDelegate", "Lcom/amazon/video/sdk/player/timeline/TimelineDelegate;", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;", "aloysiusInteractionReporter", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;", "Lcom/amazon/avod/media/events/AloysiusCyclicEventMasterController;", "aloysiusCyclicEventMasterController", "Lcom/amazon/avod/media/events/AloysiusCyclicEventMasterController;", "Lcom/amazon/avod/userdownload/PVDownloadManagerPlayerShim;", "downloadManager", "Lcom/amazon/avod/userdownload/PVDownloadManagerPlayerShim;", "Lcom/amazon/avod/connectivity/NetworkConnectionManager;", "networkConnectionManager", "Lcom/amazon/avod/connectivity/NetworkConnectionManager;", "Lcom/amazon/video/sdk/avod/playbackclient/debug/DebugDialogFeature;", "debugDialogFeature", "Lcom/amazon/video/sdk/avod/playbackclient/debug/DebugDialogFeature;", "Lcom/amazon/avod/media/service/cache/PlayerResourcesCache;", "playerResourcesCache", "Lcom/amazon/avod/media/service/cache/PlayerResourcesCache;", "Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "videoSpecUtil", "Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "mtaUtils", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "isSDKPlayer", "Z", "Lcom/amazon/video/sdk/player/util/VideoPlayerUtils;", "videoPlayerUtils", "Lcom/amazon/video/sdk/player/util/VideoPlayerUtils;", "Ljava/util/concurrent/CountDownLatch;", "terminationLatch", "Ljava/util/concurrent/CountDownLatch;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isPlayerDestroyed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Lcom/amazon/video/sdk/player/PlayerImpl$IdleState;", "idleState", "Lcom/amazon/video/sdk/player/PlayerImpl$IdleState;", "Lcom/amazon/video/sdk/player/PlayerImpl$LoadingState;", "loadingState", "Lcom/amazon/video/sdk/player/PlayerImpl$LoadingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayingState;", "playingState", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PausedState;", "pausedState", "Lcom/amazon/video/sdk/player/PlayerImpl$PausedState;", "Lcom/amazon/video/sdk/player/PlayerImpl$SeekingState;", "seekingState", "Lcom/amazon/video/sdk/player/PlayerImpl$SeekingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$UnloadingState;", "unloadingState", "Lcom/amazon/video/sdk/player/PlayerImpl$UnloadingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$DestroyedState;", "destroyedState", "Lcom/amazon/video/sdk/player/PlayerImpl$DestroyedState;", "Lcom/amazon/video/sdk/player/PlayerImpl$ErrorState;", "errorState", "Lcom/amazon/video/sdk/player/PlayerImpl$ErrorState;", "Lcom/amazon/avod/media/playback/VideoSpecification;", "videoSpecification", "Lcom/amazon/avod/media/playback/VideoSpecification;", "Lcom/amazon/avod/media/playback/VideoOptions;", "videoOptions", "Lcom/amazon/avod/media/playback/VideoOptions;", "Ljava/io/File;", "storagePath", "Ljava/io/File;", "Lcom/amazon/avod/media/playback/VideoPlayer;", "videoPlayer", "Lcom/amazon/avod/media/playback/VideoPlayer;", "Lcom/amazon/avod/playbackclient/presentation/CachedVideoPresentation;", "cachedVideoPresentation", "Lcom/amazon/avod/playbackclient/presentation/CachedVideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "Lcom/amazon/avod/media/ads/internal/state/AdPlanUpdateListener;", "adPlanUpdateListener", "Lcom/amazon/avod/media/ads/internal/state/AdPlanUpdateListener;", "videoRenderingSettingsSetOnPresentation", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "currentPlayerError", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "currentContentError", "Lcom/amazon/video/sdk/player/PlaybackState;", "currentPlaybackState", "Lcom/amazon/video/sdk/player/PlaybackState;", "Lcom/amazon/video/sdk/player/ContentState;", "currentContentState", "Lcom/amazon/video/sdk/player/ContentState;", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "currentTimeData", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "Lcom/amazon/avod/media/ads/AdPlan;", "currentAdPlan", "Lcom/amazon/avod/media/ads/AdPlan;", "Lcom/amazon/avod/media/ads/AdClip;", "currentAdClip", "Lcom/amazon/avod/media/ads/AdClip;", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusPlaybackReporter;", "aloysiusPlaybackReporter", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusPlaybackReporter;", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "value", "currentTimeline", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "setCurrentTimeline", "(Lcom/amazon/video/sdk/player/timeline/Timeline;)V", "Lcom/amazon/video/sdk/player/PlayerImpl$ClientPlaybackState;", "externalPlaybackState", "Lcom/amazon/video/sdk/player/PlayerImpl$ClientPlaybackState;", "getExternalPlaybackState", "()Lcom/amazon/video/sdk/player/PlayerImpl$ClientPlaybackState;", "setExternalPlaybackState", "(Lcom/amazon/video/sdk/player/PlayerImpl$ClientPlaybackState;)V", "getExternalPlaybackState$annotations", "()V", "currentContent", "Lcom/amazon/video/sdk/player/ContentConfig;", "getCurrentContent$AmazonAndroidVideoPlayer_release", "()Lcom/amazon/video/sdk/player/ContentConfig;", "setCurrentContent$AmazonAndroidVideoPlayer_release", "(Lcom/amazon/video/sdk/player/ContentConfig;)V", "isAdBreakPlayed", "playbackOpenSent", "isPlaying", "Ljava/util/concurrent/atomic/AtomicLong;", "seekPosition", "Ljava/util/concurrent/atomic/AtomicLong;", "Lcom/amazon/video/sdk/player/PlayerEvent$PlaybackStateChange;", "playbackStateChangeEventListenerSet", "Ljava/util/Set;", "playbackStateChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/PlayerEvent$ContentStateChange;", "playbackContentStateChangeEventListenerSet", "playbackContentStateChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/PlayerEvent$TimeDataChange;", "playbackTimeDateChangeEventListenerSet", "playbackTimeDateChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/PlayerEvent$PlayerError;", "playerErrorEventListenerSet", "playerErrorOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/PlayerEvent$ContentError;", "contentErrorEventListenerSet", "contentErrorOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/PlayerEvent$TimelineChange;", "playbackTimelineChangeEventListenerSet", "playbackTimelineChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/PlayerEvent$TimelineEnded;", "playbackTimelineEndedEventListenerSet", "playbackTimelineEndedOneTimeEventListenerSet", "Lcom/amazon/avod/connectivity/ConnectivityChangeListener;", "connectivityChangeListener", "Lcom/amazon/avod/connectivity/ConnectivityChangeListener;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerVideoPresentationEventListener;", "videoPresentationEventListener", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerVideoPresentationEventListener;", "", "eventListenerSetSet", "mainContentOffsetMs", "J", "Ljava/lang/Object;", "checkingTimeDataMutex", "Ljava/lang/Object;", "mainContentOffsetMsUpdateMutex", "Lcom/amazon/video/sdk/stream/StreamFeatureImpl;", "streamFeature", "Lcom/amazon/video/sdk/stream/StreamFeatureImpl;", "getStreamFeature", "()Lcom/amazon/video/sdk/stream/StreamFeatureImpl;", "Lcom/amazon/video/sdk/player/VolumeFeatureImpl;", "volumeFeature", "Lcom/amazon/video/sdk/player/VolumeFeatureImpl;", "getVolumeFeature", "()Lcom/amazon/video/sdk/player/VolumeFeatureImpl;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureImpl;", "videoPreviewAssetFeature", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureImpl;", "getVideoPreviewAssetFeature", "()Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureImpl;", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl;", "playlistFeature", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl;", "getPlaylistFeature", "()Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl;", "Lcom/amazon/video/sdk/player/PlayerImpl$AdPlaybackFeatureImpl;", "adPlaybackFeature", "Lcom/amazon/video/sdk/player/PlayerImpl$AdPlaybackFeatureImpl;", "getAdPlaybackFeature", "()Lcom/amazon/video/sdk/player/PlayerImpl$AdPlaybackFeatureImpl;", "", "playbackRate", "F", "getPlaybackRate", "()F", "setPlaybackRate", "(F)V", "innerRenderingConfig", "Lcom/amazon/video/sdk/player/RenderingConfig;", "seekOverride", "updateCurrentTimelineItemIndex", "currentTimelineItemIndex", "getCurrentTimelineItemIndex", "()J", "getContentError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "contentError", "getContentState", "()Lcom/amazon/video/sdk/player/ContentState;", "contentState", "getPlaybackState", "()Lcom/amazon/video/sdk/player/PlaybackState;", TetheredMessageKey.playbackState, "isPlaybackRateAdjustmentSupported", "()Z", "isVideoTrackRecreationSupported", "getRenderingConfig", "()Lcom/amazon/video/sdk/player/RenderingConfig;", "setRenderingConfig", "(Lcom/amazon/video/sdk/player/RenderingConfig;)V", "_volumeFeature", "_streamFeature", "_videoPreviewAssetFeature", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureFactory;", "_playlistFeatureFactory", "<init>", "(Lcom/amazon/video/sdk/PlayerSdkConfig;Lcom/amazon/video/sdk/player/PlayerConfig;Lcom/amazon/video/sdk/content/LocalContentManager;Ljava/util/concurrent/ExecutorService;Lcom/amazon/avod/playbackclient/presentation/PresentationCache;Lcom/amazon/video/sdk/player/timeline/TimelineDelegate;Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;Lcom/amazon/video/sdk/player/VolumeFeatureImpl;Lcom/amazon/video/sdk/stream/StreamFeatureImpl;Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureImpl;Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureFactory;Lcom/amazon/avod/media/events/AloysiusCyclicEventMasterController;Lcom/amazon/avod/userdownload/PVDownloadManagerPlayerShim;Lcom/amazon/avod/connectivity/NetworkConnectionManager;Lcom/amazon/video/sdk/avod/playbackclient/debug/DebugDialogFeature;Lcom/amazon/avod/media/service/cache/PlayerResourcesCache;Lcom/amazon/video/sdk/player/util/VideoSpecUtil;Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;ZLcom/amazon/video/sdk/player/util/VideoPlayerUtils;)V", "Companion", "AdPlaybackFeatureImpl", "ClientPlaybackState", "DestroyedState", "ErrorState", "IdleState", "LoadingState", "PausedState", "PlaybackAdEventListener", "PlaybackAdPlanUpdateListener", "PlaybackAdStateChangeListenerImpl", "PlaybackAudioTrackChangeListenerImpl", "PlaybackContentStateChangeEventListenerImpl", "PlaybackSessionBufferEventListenerImpl", "PlaybackStateEventListenerImpl", "PlaybackTimeDataChangeEventListenerImpl", "PlayerFeatureState", "PlayerVideoPresentationEventListener", "PlayingState", "SeekingState", "UnloadingState", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class PlayerImpl extends BlockingStateMachine<PlayerFeatureState.Type, Triggers.Type> implements Player, RothkoPlayer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AdPlanUpdateListener adPlanUpdateListener;
    private final AdPlaybackFeatureImpl adPlaybackFeature;
    private final AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController;
    private final AloysiusInteractionReporter aloysiusInteractionReporter;
    private AloysiusPlaybackReporter aloysiusPlaybackReporter;
    private CachedVideoPresentation cachedVideoPresentation;
    private final Object checkingTimeDataMutex;
    private final PlayerConfig config;
    private final ConnectivityChangeListener connectivityChangeListener;
    private final Set<EventListener<PlayerEvent.ContentError>> contentErrorEventListenerSet;
    private final Set<EventListener<PlayerEvent.ContentError>> contentErrorOneTimeEventListenerSet;
    private AdClip currentAdClip;
    private AdPlan currentAdPlan;
    private ContentConfig currentContent;
    private PlaybackError currentContentError;
    private ContentState currentContentState;
    private PlaybackState currentPlaybackState;
    private PlaybackError currentPlayerError;
    private long currentPosition;
    private TimeData currentTimeData;
    private Timeline currentTimeline;
    private long currentTimelineItemIndex;
    private final DebugDialogFeature debugDialogFeature;
    private final DestroyedState destroyedState;
    private final PVDownloadManagerPlayerShim downloadManager;
    private final ErrorState errorState;
    private final Set<Set<?>> eventListenerSetSet;
    private final ExecutorService executor;
    private ClientPlaybackState externalPlaybackState;
    private final IdleState idleState;
    private RenderingConfig innerRenderingConfig;
    private final AtomicBoolean isAdBreakPlayed;
    private final AtomicBoolean isPlayerDestroyed;
    private final AtomicBoolean isPlaying;
    private final boolean isSDKPlayer;
    private final LoadingState loadingState;
    private final LocalContentManager localContentManager;
    private long mainContentOffsetMs;
    private final Object mainContentOffsetMsUpdateMutex;
    private final MultiTrackAudioUtils mtaUtils;
    private final NetworkConnectionManager networkConnectionManager;
    private final PausedState pausedState;
    private final Set<EventListener<PlayerEvent.ContentStateChange>> playbackContentStateChangeEventListenerSet;
    private final Set<EventListener<PlayerEvent.ContentStateChange>> playbackContentStateChangeOneTimeEventListenerSet;
    private final AtomicBoolean playbackOpenSent;
    private float playbackRate;
    private final Set<EventListener<PlayerEvent.PlaybackStateChange>> playbackStateChangeEventListenerSet;
    private final Set<EventListener<PlayerEvent.PlaybackStateChange>> playbackStateChangeOneTimeEventListenerSet;
    private final Set<EventListener<PlayerEvent.TimeDataChange>> playbackTimeDateChangeEventListenerSet;
    private final Set<EventListener<PlayerEvent.TimeDataChange>> playbackTimeDateChangeOneTimeEventListenerSet;
    private final Set<EventListener<PlayerEvent.TimelineChange>> playbackTimelineChangeEventListenerSet;
    private final Set<EventListener<PlayerEvent.TimelineChange>> playbackTimelineChangeOneTimeEventListenerSet;
    private final Set<EventListener<PlayerEvent.TimelineEnded>> playbackTimelineEndedEventListenerSet;
    private final Set<EventListener<PlayerEvent.TimelineEnded>> playbackTimelineEndedOneTimeEventListenerSet;
    private final Set<EventListener<PlayerEvent.PlayerError>> playerErrorEventListenerSet;
    private final Set<EventListener<PlayerEvent.PlayerError>> playerErrorOneTimeEventListenerSet;
    private final PlayerResourcesCache playerResourcesCache;
    private final PlayingState playingState;
    private final PlaylistFeatureImpl playlistFeature;
    private final PresentationCache presentationCache;
    private final PlayerSdkConfig sdkConfig;
    private final AtomicBoolean seekOverride;
    private final AtomicLong seekPosition;
    private final SeekingState seekingState;
    private File storagePath;
    private final StreamFeatureImpl streamFeature;
    private final CountDownLatch terminationLatch;
    private final TimelineDelegate timelineDelegate;
    private final UnloadingState unloadingState;
    private final AtomicBoolean updateCurrentTimelineItemIndex;
    private VideoOptions videoOptions;
    private VideoPlayer videoPlayer;
    private final VideoPlayerUtils videoPlayerUtils;
    private VideoPresentation videoPresentation;
    private final PlayerVideoPresentationEventListener videoPresentationEventListener;
    private final VideoPreviewAssetFeatureImpl videoPreviewAssetFeature;
    private VideoRenderingSettings videoRenderingSettings;
    private final AtomicBoolean videoRenderingSettingsSetOnPresentation;
    private final VideoSpecUtil videoSpecUtil;
    private VideoSpecification videoSpecification;
    private final VolumeFeatureImpl volumeFeature;

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$ClientPlaybackState;", "", "(Ljava/lang/String;I)V", "Uninitialized", "Playing", "Pausing", "Paused", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ClientPlaybackState {
        Uninitialized,
        Playing,
        Pausing,
        Paused
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[PlayerFeatureState.Type.values().length];
            iArr[PlayerFeatureState.Type.PLAYING.ordinal()] = 1;
            iArr[PlayerFeatureState.Type.PAUSED.ordinal()] = 2;
            iArr[PlayerFeatureState.Type.IDLE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SurfaceHandlingMode.values().length];
            iArr2[SurfaceHandlingMode.CREATE.ordinal()] = 1;
            iArr2[SurfaceHandlingMode.ATTACH.ordinal()] = 2;
            iArr2[SurfaceHandlingMode.AUDIO_ONLY.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ErrorType.values().length];
            iArr3[ErrorType.PlayerError.ordinal()] = 1;
            iArr3[ErrorType.ContentError.ordinal()] = 2;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @Override // com.amazon.avod.fsm.BlockingStateMachine
    public boolean doVerboseLogging() {
        return true;
    }

    public final PlayerSdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PlayerImpl(PlayerSdkConfig playerSdkConfig, PlayerConfig playerConfig, LocalContentManager localContentManager, ExecutorService executorService, PresentationCache presentationCache, TimelineDelegate timelineDelegate, AloysiusInteractionReporter aloysiusInteractionReporter, VolumeFeatureImpl volumeFeatureImpl, StreamFeatureImpl streamFeatureImpl, VideoPreviewAssetFeatureImpl videoPreviewAssetFeatureImpl, PlaylistFeatureFactory playlistFeatureFactory, AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController, PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim, NetworkConnectionManager networkConnectionManager, DebugDialogFeature debugDialogFeature, PlayerResourcesCache playerResourcesCache, VideoSpecUtil videoSpecUtil, MultiTrackAudioUtils multiTrackAudioUtils, boolean z, VideoPlayerUtils videoPlayerUtils, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ExecutorService executorService2;
        PresentationCache presentationCache2;
        AloysiusInteractionReporter aloysiusInteractionReporter2;
        AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController2;
        PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim2;
        NetworkConnectionManager networkConnectionManager2;
        PlayerResourcesCache playerResourcesCache2;
        if ((i & 8) != 0) {
            ListeningScheduledExecutorService listeningScheduledExecutorServiceListeningDecorator = MoreExecutors.listeningDecorator((ScheduledExecutorService) ScheduledExecutorBuilder.newBuilderFor(INSTANCE, new String[0]).withFixedThreadPoolSize(1).build());
            Intrinsics.checkNotNullExpressionValue(listeningScheduledExecutorServiceListeningDecorator, "listeningDecorator(\n    …PoolSize(1).build()\n    )");
            executorService2 = listeningScheduledExecutorServiceListeningDecorator;
        } else {
            executorService2 = executorService;
        }
        if ((i & 16) != 0) {
            LimitedInstancePresentationCache limitedInstancePresentationCache = LimitedInstancePresentationCache.getInstance();
            Intrinsics.checkNotNullExpressionValue(limitedInstancePresentationCache, "getInstance()");
            presentationCache2 = limitedInstancePresentationCache;
        } else {
            presentationCache2 = presentationCache;
        }
        TimelineDelegate timelineDelegate2 = (i & 32) != 0 ? new TimelineDelegate() : timelineDelegate;
        if ((i & 64) != 0) {
            AloysiusInteractionReporter aloysiusInteractionReporterCreateInteractionReporter = MediaSystem.getInstance().getPlaybackMediaEventReportersFactory().createInteractionReporter();
            Intrinsics.checkNotNullExpressionValue(aloysiusInteractionReporterCreateInteractionReporter, "getInstance().playbackMe…eateInteractionReporter()");
            aloysiusInteractionReporter2 = aloysiusInteractionReporterCreateInteractionReporter;
        } else {
            aloysiusInteractionReporter2 = aloysiusInteractionReporter;
        }
        VolumeFeatureImpl volumeFeatureImpl2 = (i & 128) != 0 ? new VolumeFeatureImpl() : volumeFeatureImpl;
        StreamFeatureImpl streamFeatureImpl2 = (i & 256) != 0 ? new StreamFeatureImpl(playerConfig, aloysiusInteractionReporter2) : streamFeatureImpl;
        VideoPreviewAssetFeatureImpl videoPreviewAssetFeatureImpl2 = (i & 512) != 0 ? new VideoPreviewAssetFeatureImpl(playerConfig) : videoPreviewAssetFeatureImpl;
        PlaylistFeatureFactory playlistFeatureFactoryImpl = (i & 1024) != 0 ? new PlaylistFeatureFactoryImpl(playerConfig, localContentManager, presentationCache2) : playlistFeatureFactory;
        if ((i & 2048) != 0) {
            AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController3 = AloysiusCyclicEventMasterController.getInstance();
            Intrinsics.checkNotNullExpressionValue(aloysiusCyclicEventMasterController3, "getInstance()");
            aloysiusCyclicEventMasterController2 = aloysiusCyclicEventMasterController3;
        } else {
            aloysiusCyclicEventMasterController2 = aloysiusCyclicEventMasterController;
        }
        if ((i & 4096) != 0) {
            PVDownloadManagerPlayerShim playerDownloadManagerProxy = PVDownloadsPlayerShim.getInstance().getPlayerDownloadManagerProxy();
            Intrinsics.checkNotNullExpressionValue(playerDownloadManagerProxy, "getInstance().playerDownloadManagerProxy");
            pVDownloadManagerPlayerShim2 = playerDownloadManagerProxy;
        } else {
            pVDownloadManagerPlayerShim2 = pVDownloadManagerPlayerShim;
        }
        if ((i & 8192) != 0) {
            NetworkConnectionManager networkConnectionManager3 = NetworkConnectionManager.getInstance();
            Intrinsics.checkNotNullExpressionValue(networkConnectionManager3, "getInstance()");
            networkConnectionManager2 = networkConnectionManager3;
        } else {
            networkConnectionManager2 = networkConnectionManager;
        }
        DebugDialogFeature debugDialogFeature2 = (i & 16384) != 0 ? new DebugDialogFeature(playerConfig) : debugDialogFeature;
        if ((32768 & i) != 0) {
            PlayerResourcesCache playerResourcesCache3 = PlayerResourcesCache.getInstance();
            Intrinsics.checkNotNullExpressionValue(playerResourcesCache3, "getInstance()");
            playerResourcesCache2 = playerResourcesCache3;
        } else {
            playerResourcesCache2 = playerResourcesCache;
        }
        this(playerSdkConfig, playerConfig, localContentManager, executorService2, presentationCache2, timelineDelegate2, aloysiusInteractionReporter2, volumeFeatureImpl2, streamFeatureImpl2, videoPreviewAssetFeatureImpl2, playlistFeatureFactoryImpl, aloysiusCyclicEventMasterController2, pVDownloadManagerPlayerShim2, networkConnectionManager2, debugDialogFeature2, playerResourcesCache2, (65536 & i) != 0 ? new VideoSpecUtil() : videoSpecUtil, (131072 & i) != 0 ? new MultiTrackAudioUtils() : multiTrackAudioUtils, (262144 & i) != 0 ? MediaSystemSharedDependencies.getInstance().isSDKPlayer() : z, (i & 524288) != 0 ? new VideoPlayerUtils() : videoPlayerUtils);
    }

    public PlayerImpl(PlayerSdkConfig sdkConfig, PlayerConfig config, LocalContentManager localContentManager, ExecutorService executor, PresentationCache presentationCache, TimelineDelegate timelineDelegate, AloysiusInteractionReporter aloysiusInteractionReporter, VolumeFeatureImpl _volumeFeature, StreamFeatureImpl _streamFeature, VideoPreviewAssetFeatureImpl _videoPreviewAssetFeature, PlaylistFeatureFactory _playlistFeatureFactory, AloysiusCyclicEventMasterController aloysiusCyclicEventMasterController, PVDownloadManagerPlayerShim downloadManager, NetworkConnectionManager networkConnectionManager, DebugDialogFeature debugDialogFeature, PlayerResourcesCache playerResourcesCache, VideoSpecUtil videoSpecUtil, MultiTrackAudioUtils mtaUtils, boolean z, VideoPlayerUtils videoPlayerUtils) {
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(localContentManager, "localContentManager");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(presentationCache, "presentationCache");
        Intrinsics.checkNotNullParameter(timelineDelegate, "timelineDelegate");
        Intrinsics.checkNotNullParameter(aloysiusInteractionReporter, "aloysiusInteractionReporter");
        Intrinsics.checkNotNullParameter(_volumeFeature, "_volumeFeature");
        Intrinsics.checkNotNullParameter(_streamFeature, "_streamFeature");
        Intrinsics.checkNotNullParameter(_videoPreviewAssetFeature, "_videoPreviewAssetFeature");
        Intrinsics.checkNotNullParameter(_playlistFeatureFactory, "_playlistFeatureFactory");
        Intrinsics.checkNotNullParameter(aloysiusCyclicEventMasterController, "aloysiusCyclicEventMasterController");
        Intrinsics.checkNotNullParameter(downloadManager, "downloadManager");
        Intrinsics.checkNotNullParameter(networkConnectionManager, "networkConnectionManager");
        Intrinsics.checkNotNullParameter(debugDialogFeature, "debugDialogFeature");
        Intrinsics.checkNotNullParameter(playerResourcesCache, "playerResourcesCache");
        Intrinsics.checkNotNullParameter(videoSpecUtil, "videoSpecUtil");
        Intrinsics.checkNotNullParameter(mtaUtils, "mtaUtils");
        Intrinsics.checkNotNullParameter(videoPlayerUtils, "videoPlayerUtils");
        this.sdkConfig = sdkConfig;
        this.config = config;
        this.localContentManager = localContentManager;
        this.executor = executor;
        this.presentationCache = presentationCache;
        this.timelineDelegate = timelineDelegate;
        this.aloysiusInteractionReporter = aloysiusInteractionReporter;
        this.aloysiusCyclicEventMasterController = aloysiusCyclicEventMasterController;
        this.downloadManager = downloadManager;
        this.networkConnectionManager = networkConnectionManager;
        this.debugDialogFeature = debugDialogFeature;
        this.playerResourcesCache = playerResourcesCache;
        this.videoSpecUtil = videoSpecUtil;
        this.mtaUtils = mtaUtils;
        this.isSDKPlayer = z;
        this.videoPlayerUtils = videoPlayerUtils;
        this.terminationLatch = new CountDownLatch(1);
        this.isPlayerDestroyed = new AtomicBoolean(false);
        IdleState idleState = new IdleState(this, this);
        this.idleState = idleState;
        LoadingState loadingState = new LoadingState(this, this);
        this.loadingState = loadingState;
        PlayingState playingState = new PlayingState(this, this);
        this.playingState = playingState;
        PausedState pausedState = new PausedState(this, this);
        this.pausedState = pausedState;
        SeekingState seekingState = new SeekingState(this, this);
        this.seekingState = seekingState;
        UnloadingState unloadingState = new UnloadingState(this, this);
        this.unloadingState = unloadingState;
        DestroyedState destroyedState = new DestroyedState(this, this);
        this.destroyedState = destroyedState;
        ErrorState errorState = new ErrorState(this, this);
        this.errorState = errorState;
        this.videoRenderingSettingsSetOnPresentation = new AtomicBoolean(false);
        this.currentTimeline = new TimelineImpl(null, 1, null);
        this.isAdBreakPlayed = new AtomicBoolean(false);
        this.playbackOpenSent = new AtomicBoolean(false);
        this.isPlaying = new AtomicBoolean(false);
        this.seekPosition = new AtomicLong(0L);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.playbackStateChangeEventListenerSet = linkedHashSet;
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        this.playbackStateChangeOneTimeEventListenerSet = linkedHashSet2;
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        this.playbackContentStateChangeEventListenerSet = linkedHashSet3;
        LinkedHashSet linkedHashSet4 = new LinkedHashSet();
        this.playbackContentStateChangeOneTimeEventListenerSet = linkedHashSet4;
        LinkedHashSet linkedHashSet5 = new LinkedHashSet();
        this.playbackTimeDateChangeEventListenerSet = linkedHashSet5;
        LinkedHashSet linkedHashSet6 = new LinkedHashSet();
        this.playbackTimeDateChangeOneTimeEventListenerSet = linkedHashSet6;
        LinkedHashSet linkedHashSet7 = new LinkedHashSet();
        this.playerErrorEventListenerSet = linkedHashSet7;
        LinkedHashSet linkedHashSet8 = new LinkedHashSet();
        this.playerErrorOneTimeEventListenerSet = linkedHashSet8;
        LinkedHashSet linkedHashSet9 = new LinkedHashSet();
        this.contentErrorEventListenerSet = linkedHashSet9;
        LinkedHashSet linkedHashSet10 = new LinkedHashSet();
        this.contentErrorOneTimeEventListenerSet = linkedHashSet10;
        LinkedHashSet linkedHashSet11 = new LinkedHashSet();
        this.playbackTimelineChangeEventListenerSet = linkedHashSet11;
        LinkedHashSet linkedHashSet12 = new LinkedHashSet();
        this.playbackTimelineChangeOneTimeEventListenerSet = linkedHashSet12;
        LinkedHashSet linkedHashSet13 = new LinkedHashSet();
        this.playbackTimelineEndedEventListenerSet = linkedHashSet13;
        LinkedHashSet linkedHashSet14 = new LinkedHashSet();
        this.playbackTimelineEndedOneTimeEventListenerSet = linkedHashSet14;
        this.videoPresentationEventListener = new PlayerVideoPresentationEventListener(this);
        this.eventListenerSetSet = SetsKt__SetsKt.setOf((Object[]) new Set[]{linkedHashSet, linkedHashSet2, linkedHashSet3, linkedHashSet4, linkedHashSet5, linkedHashSet6, linkedHashSet7, linkedHashSet8, linkedHashSet9, linkedHashSet10, linkedHashSet11, linkedHashSet12, linkedHashSet13, linkedHashSet14});
        this.checkingTimeDataMutex = new Object();
        this.mainContentOffsetMsUpdateMutex = new Object();
        this.playbackRate = 1.0f;
        this.innerRenderingConfig = new RenderingConfigData(SurfaceHandlingMode.AUDIO_ONLY, null, null, 0, false, 30, null);
        this.seekOverride = new AtomicBoolean(false);
        this.updateCurrentTimelineItemIndex = new AtomicBoolean(false);
        this.currentTimelineItemIndex = -1L;
        StateBuilder<PlayerFeatureState.Type, Triggers.Type> stateBuilderRegisterTransition = setupState(idleState).registerTransition(Triggers.Type.LOAD, loadingState);
        Triggers.Type type = Triggers.Type.UNLOAD;
        stateBuilderRegisterTransition.registerTransition(type, unloadingState);
        StateBuilder<PlayerFeatureState.Type, Triggers.Type> stateBuilder = setupState(loadingState);
        Triggers.Type type2 = Triggers.Type.PLAY;
        StateBuilder<PlayerFeatureState.Type, Triggers.Type> stateBuilderRegisterTransition2 = stateBuilder.registerTransition(type2, playingState).registerTransition(type, unloadingState);
        Triggers.Type type3 = Triggers.Type.ERROR;
        stateBuilderRegisterTransition2.registerTransition(type3, errorState);
        StateBuilder<PlayerFeatureState.Type, Triggers.Type> stateBuilder2 = setupState(playingState);
        Triggers.Type type4 = Triggers.Type.PAUSE;
        StateBuilder<PlayerFeatureState.Type, Triggers.Type> stateBuilderRegisterTransition3 = stateBuilder2.registerTransition(type4, pausedState);
        Triggers.Type type5 = Triggers.Type.SEEK;
        stateBuilderRegisterTransition3.registerTransition(type5, seekingState).registerTransition(type, unloadingState).registerTransition(type3, errorState);
        setupState(pausedState).registerTransition(type2, playingState).registerTransition(type5, seekingState).registerTransition(type, unloadingState).registerTransition(type3, errorState);
        setupState(seekingState).registerTransition(type2, playingState).registerTransition(type4, pausedState);
        setupState(unloadingState).registerTransition(Triggers.Type.IDLE, idleState).registerTransition(Triggers.Type.DESTROY, destroyedState);
        setupState(destroyedState);
        setupState(errorState).registerTransition(type, unloadingState);
        start(idleState);
        this.externalPlaybackState = ClientPlaybackState.Uninitialized;
        this.videoSpecification = null;
        this.videoOptions = null;
        this.storagePath = null;
        this.cachedVideoPresentation = null;
        this.videoPresentation = null;
        this.adPlanUpdateListener = null;
        setCurrentContent$AmazonAndroidVideoPlayer_release(null);
        this.videoRenderingSettings = new VideoRenderingSettings();
        this.currentPlayerError = null;
        this.currentContentError = null;
        this.currentPlaybackState = PlaybackState.Paused;
        this.currentContentState = ContentState.Unloaded;
        setCurrentTimeline(new TimelineImpl(null, 1, null));
        this.currentTimeData = new TimeDataImpl(null, 0L, null, 0L, 13, null);
        EmptyAdPlan INSTANCE2 = EmptyAdPlan.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(INSTANCE2, "INSTANCE");
        this.currentAdPlan = INSTANCE2;
        this.currentAdClip = null;
        this.aloysiusPlaybackReporter = null;
        this.volumeFeature = _volumeFeature;
        this.streamFeature = _streamFeature;
        this.videoPreviewAssetFeature = _videoPreviewAssetFeature;
        this.playlistFeature = _playlistFeatureFactory.createPlaylistFeature(this);
        this.adPlaybackFeature = new AdPlaybackFeatureImpl(this);
        ConnectivityChangeListener connectivityChangeListener = new ConnectivityChangeListener() { // from class: com.amazon.video.sdk.player.PlayerImpl$$ExternalSyntheticLambda2
            @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
            public final void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
                PlayerImpl.m2483_init_$lambda9(this.f$0, detailedNetworkInfo, detailedNetworkInfo2);
            }
        };
        this.connectivityChangeListener = connectivityChangeListener;
        networkConnectionManager.registerListener(connectivityChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentTimeline(Timeline timeline) {
        this.currentTimeline = timeline;
        this.mainContentOffsetMs = 0L;
        this.updateCurrentTimelineItemIndex.set(true);
    }

    public final ClientPlaybackState getExternalPlaybackState() {
        return this.externalPlaybackState;
    }

    public final void setExternalPlaybackState(ClientPlaybackState clientPlaybackState) {
        Intrinsics.checkNotNullParameter(clientPlaybackState, "<set-?>");
        this.externalPlaybackState = clientPlaybackState;
    }

    /* JADX INFO: renamed from: getCurrentContent$AmazonAndroidVideoPlayer_release, reason: from getter */
    public ContentConfig getCurrentContent() {
        return this.currentContent;
    }

    public void setCurrentContent$AmazonAndroidVideoPlayer_release(ContentConfig contentConfig) {
        this.currentContent = contentConfig;
    }

    @Override // com.amazon.video.sdk.player.Player
    public StreamFeatureImpl getStreamFeature() {
        return this.streamFeature;
    }

    public VolumeFeatureImpl getVolumeFeature() {
        return this.volumeFeature;
    }

    @Override // com.amazon.video.sdk.player.Player
    public VideoPreviewAssetFeatureImpl getVideoPreviewAssetFeature() {
        return this.videoPreviewAssetFeature;
    }

    public PlaylistFeatureImpl getPlaylistFeature() {
        return this.playlistFeature;
    }

    @Override // com.amazon.video.sdk.player.Player
    public PlaybackError getContentError() {
        return this.currentContentError;
    }

    @Override // com.amazon.video.sdk.player.Player
    public ContentState getContentState() {
        return this.currentContentState;
    }

    @Override // com.amazon.video.sdk.player.Player
    public PlaybackState getPlaybackState() {
        return this.currentPlaybackState;
    }

    public boolean isPlaybackRateAdjustmentSupported() {
        VideoType videoType;
        if (!checkVideoPlayerInitialized(false)) {
            return false;
        }
        ContentConfig currentContent = getCurrentContent();
        if (!((currentContent == null || (videoType = currentContent.getVideoType()) == null) ? false : !videoType.isLive$AmazonAndroidVideoPlayer_release())) {
            return false;
        }
        VideoPlayer videoPlayer = this.videoPlayer;
        if (videoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
            videoPlayer = null;
        }
        PlaybackExperienceController playbackExperienceController = videoPlayer.getPlaybackExperienceController();
        return playbackExperienceController == null ? false : playbackExperienceController.isPlaybackSpeedAdjustmentSupported();
    }

    @Override // com.amazon.video.sdk.player.Player
    public float getPlaybackRate() {
        return this.playbackRate;
    }

    @Override // com.amazon.video.sdk.player.Player
    public void setPlaybackRate(float f) {
        SDKPlayerLogger.log("setPlaybackRate(%s)", Float.valueOf(f));
        if (!isPlaybackRateAdjustmentSupported()) {
            DLog.logf("Playback rate adjustment is not supported on the player");
            return;
        }
        float fMax = Math.max(0.5f, Math.min(f, 2.0f));
        VideoPlayer videoPlayer = this.videoPlayer;
        if (videoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
            videoPlayer = null;
        }
        PlaybackExperienceController playbackExperienceController = videoPlayer.getPlaybackExperienceController();
        if (playbackExperienceController != null) {
            playbackExperienceController.setPlaybackSpeed(fMax);
        }
        this.playbackRate = fMax;
    }

    @Override // com.amazon.video.sdk.player.Player
    /* JADX INFO: renamed from: isVideoTrackRecreationSupported */
    public boolean getIsVideoTrackRecreationSupported() {
        VideoType videoType;
        if (!checkVideoPlayerInitialized(false)) {
            return false;
        }
        ContentConfig currentContent = getCurrentContent();
        if (!((currentContent == null || (videoType = currentContent.getVideoType()) == null) ? false : !videoType.isLive$AmazonAndroidVideoPlayer_release())) {
            return false;
        }
        VideoPlayer videoPlayer = this.videoPlayer;
        if (videoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
            videoPlayer = null;
        }
        PlaybackExperienceController playbackExperienceController = videoPlayer.getPlaybackExperienceController();
        return playbackExperienceController == null ? false : playbackExperienceController.isVideoTrackRecreationSupported();
    }

    private final long getCurrentTimelineItemIndex() {
        long currentTimelineItemIndex;
        long j;
        Long lValueOf;
        synchronized (Long.valueOf(this.currentTimelineItemIndex)) {
            if (this.updateCurrentTimelineItemIndex.getAndSet(false)) {
                AdClip adClip = this.currentAdClip;
                Long lValueOf2 = adClip == null ? null : Long.valueOf(getTimelineItemIndex(ContentType.ADVERTISEMENT, 0L, adClip.getAdId()));
                if (lValueOf2 != null) {
                    currentTimelineItemIndex = lValueOf2.longValue();
                } else {
                    synchronized (this.mainContentOffsetMsUpdateMutex) {
                        ContentType contentType = ContentType.FEATURE;
                        if (this.seekOverride.getAndSet(false)) {
                            j = this.seekPosition.get();
                        } else {
                            j = this.currentPosition + this.mainContentOffsetMs;
                        }
                        lValueOf = Long.valueOf(getTimelineItemIndex$default(this, contentType, j, null, 4, null));
                    }
                    currentTimelineItemIndex = lValueOf.longValue();
                }
            } else {
                currentTimelineItemIndex = this.currentTimelineItemIndex;
                if (currentTimelineItemIndex == -1) {
                    currentTimelineItemIndex = this.currentTimeData.getCurrentTimelineItemIndex();
                }
            }
            this.currentTimelineItemIndex = currentTimelineItemIndex;
        }
        return currentTimelineItemIndex;
    }

    @Override // com.amazon.video.sdk.player.Player
    public RenderingConfig getRenderingConfig() {
        return this.innerRenderingConfig;
    }

    @Override // com.amazon.video.sdk.player.Player
    public void setRenderingConfig(final RenderingConfig renderingConfig) {
        Intrinsics.checkNotNullParameter(renderingConfig, "renderingConfig");
        SDKPlayerLogger.log("setRenderingConfig(%s)", renderingConfig);
        this.executor.execute(new Runnable() { // from class: com.amazon.video.sdk.player.PlayerImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws PlaybackException {
                PlayerImpl.m2484_set_renderingConfig_$lambda4(this.f$0, renderingConfig);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _set_renderingConfig_$lambda-4, reason: not valid java name */
    public static final void m2484_set_renderingConfig_$lambda4(PlayerImpl this$0, RenderingConfig renderingConfig) throws PlaybackException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(renderingConfig, "$renderingConfig");
        PlayerFeatureState.Type type = this$0.getCurrentState().get_type();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            this$0.updateRenderingConfig(renderingConfig);
            if (this$0.checkVideoPlayerInitialized(false)) {
                VideoPlayer videoPlayer = this$0.videoPlayer;
                if (videoPlayer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                    videoPlayer = null;
                }
                PlaybackExperienceController playbackExperienceController = videoPlayer.getPlaybackExperienceController();
                if (playbackExperienceController != null) {
                    playbackExperienceController.changeOutputDisplay(this$0.videoRenderingSettings);
                }
            }
            VideoPresentation videoPresentation = this$0.videoPresentation;
            if (videoPresentation == null) {
                return;
            }
            this$0.getStreamFeature().changeParentView(videoPresentation, this$0.videoRenderingSettings);
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Unable to process SDK.Player.setRenderingConfig(%s) since the player is in state: %s, that does not support this operation.", Arrays.copyOf(new Object[]{renderingConfig, type}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        Throwables2.propagateIfWeakMode("SDK.Player.setRenderingConfig", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRenderingConfig(RenderingConfig renderingConfig) {
        VideoRenderingSettings videoRenderingSettings;
        this.innerRenderingConfig = renderingConfig;
        int i = WhenMappings.$EnumSwitchMapping$1[renderingConfig.getSurfaceHandlingMode().ordinal()];
        if (i == 1) {
            ViewGroup parentView = renderingConfig.getParentView();
            ViewGroup viewGroup = parentView != null ? (ViewGroup) Preconditions.checkNotNull(parentView, "RenderingConfig must have a nonnull parentView in CREATE mode", new Object[0]) : null;
            Intrinsics.checkNotNull(viewGroup);
            videoRenderingSettings = new VideoRenderingSettings(viewGroup, renderingConfig.getSystemUiFlags(), false, renderingConfig.getEnableVideoTrackRecreation());
        } else if (i == 2) {
            ViewGroup parentView2 = renderingConfig.getParentView();
            ViewGroup viewGroup2 = parentView2 == null ? null : (ViewGroup) Preconditions.checkNotNull(parentView2, "RenderingConfig must have a nonnull parentView in ATTACH mode", new Object[0]);
            Surface playbackSurface = renderingConfig.getPlaybackSurface();
            Surface surface = playbackSurface != null ? (Surface) Preconditions.checkNotNull(playbackSurface, "RenderingConfig must have a nonnull playbackSurface in ATTACH mode", new Object[0]) : null;
            Intrinsics.checkNotNull(viewGroup2);
            Intrinsics.checkNotNull(surface);
            videoRenderingSettings = new VideoRenderingSettings(viewGroup2, surface);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            videoRenderingSettings = new VideoRenderingSettings(renderingConfig.getEnableVideoTrackRecreation());
        }
        this.videoRenderingSettings = videoRenderingSettings;
        SDKPlayerLogger.log("updateRenderingConfig(%s) generated videoRenderingSettings(%s)", renderingConfig, videoRenderingSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-9, reason: not valid java name */
    public static final void m2483_init_$lambda9(PlayerImpl this$0, DetailedNetworkInfo fromNetwork, DetailedNetworkInfo toNetwork) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fromNetwork, "fromNetwork");
        Intrinsics.checkNotNullParameter(toNetwork, "toNetwork");
        NetworkType networkType = fromNetwork.getNetworkType();
        Intrinsics.checkNotNullExpressionValue(networkType, "fromNetwork.networkType");
        NetworkType networkType2 = toNetwork.getNetworkType();
        Intrinsics.checkNotNullExpressionValue(networkType2, "toNetwork.networkType");
        if (networkType == networkType2 || !this$0.checkVideoPlayerInitialized(false)) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(PlaybackConfig.getInstance().getStreamingQualityForNetwork(networkType2), "getInstance().getStreami…ForNetwork(toNetworkType)");
        VideoPlayer videoPlayer = this$0.videoPlayer;
        if (videoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
            videoPlayer = null;
        }
        PlaybackExperienceController playbackExperienceController = videoPlayer.getPlaybackExperienceController();
        if (playbackExperienceController == null) {
            return;
        }
        playbackExperienceController.setRestrictPlaybackToBufferedContent(!r2.isPresent());
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$Companion;", "", "()V", "PLAYBACK_RATE_MAX", "", "PLAYBACK_RATE_MIN", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void unload() {
        SDKPlayerLogger.log("unload()");
        reportInteractionStop();
        doTriggerAsync(new Triggers.Unload(new Triggers(), null, false));
    }

    @Override // com.amazon.video.sdk.player.Player
    public void start(ContentConfig content, RenderingConfig renderingConfig) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(renderingConfig, "renderingConfig");
        SDKPlayerLogger.log("start(%s, %s)", content, renderingConfig);
        doTriggerAsync(new Triggers.Unload(new Triggers(), content, false));
        doTriggerAsync(new Triggers().new Load(content, renderingConfig, true, null, 8, null));
        doTriggerAsync(new Triggers().new Play(false, 1, null));
    }

    @Override // com.amazon.video.sdk.player.Player
    public void pause() {
        SDKPlayerLogger.log("pause()");
        reportEvent(AloysiusInteractionReporter.Type.Pause);
        doTriggerAsync(new Triggers.Pause(new Triggers(), false));
    }

    @Override // com.amazon.video.sdk.player.Player
    public void play() {
        SDKPlayerLogger.log("play()");
        reportInteractionPlay();
        doTriggerAsync(new Triggers.Play(new Triggers(), false));
    }

    @Override // com.amazon.video.sdk.player.Player
    public void seek(long position) {
        SDKPlayerLogger.log("seek(%s)", Long.valueOf(position));
        long currentPosition = this.currentTimeData.getCurrentPosition();
        if (position > currentPosition) {
            reportEvent(AloysiusInteractionReporter.Type.SeekForward);
        } else if (position < currentPosition) {
            reportEvent(AloysiusInteractionReporter.Type.SeekBackward);
        }
        doTriggerAsync(new Triggers.Seek(new Triggers(), position));
    }

    @Override // com.amazon.video.sdk.player.Player
    public <E extends PlayerEvent> void on(Class<E> event, EventListener<E> callback) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (Intrinsics.areEqual(event, PlayerEvent.PlaybackStateChange.class)) {
            this.playbackStateChangeEventListenerSet.add(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.ContentStateChange.class)) {
            this.playbackContentStateChangeEventListenerSet.add(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.TimeDataChange.class)) {
            this.playbackTimeDateChangeEventListenerSet.add(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.PlayerError.class)) {
            this.playerErrorEventListenerSet.add(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.ContentError.class)) {
            this.contentErrorEventListenerSet.add(callback);
        } else if (Intrinsics.areEqual(event, PlayerEvent.TimelineChange.class)) {
            this.playbackTimelineChangeEventListenerSet.add(callback);
        } else if (Intrinsics.areEqual(event, PlayerEvent.TimelineEnded.class)) {
            this.playbackTimelineEndedEventListenerSet.add(callback);
        }
    }

    @Override // com.amazon.video.sdk.player.Player
    public <E extends PlayerEvent> void off(Class<E> event, EventListener<E> callback) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (Intrinsics.areEqual(event, PlayerEvent.PlaybackStateChange.class)) {
            this.playbackStateChangeEventListenerSet.remove(callback);
            this.playbackStateChangeOneTimeEventListenerSet.remove(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.ContentStateChange.class)) {
            this.playbackContentStateChangeEventListenerSet.remove(callback);
            this.playbackContentStateChangeOneTimeEventListenerSet.remove(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.TimeDataChange.class)) {
            this.playbackTimeDateChangeEventListenerSet.remove(callback);
            this.playbackTimeDateChangeOneTimeEventListenerSet.remove(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.PlayerError.class)) {
            this.playerErrorEventListenerSet.remove(callback);
            this.playerErrorOneTimeEventListenerSet.remove(callback);
            return;
        }
        if (Intrinsics.areEqual(event, PlayerEvent.ContentError.class)) {
            this.contentErrorEventListenerSet.remove(callback);
            this.contentErrorOneTimeEventListenerSet.remove(callback);
        } else if (Intrinsics.areEqual(event, PlayerEvent.TimelineChange.class)) {
            this.playbackTimelineChangeEventListenerSet.remove(callback);
            this.playbackTimelineChangeOneTimeEventListenerSet.remove(callback);
        } else if (Intrinsics.areEqual(event, PlayerEvent.TimelineEnded.class)) {
            this.playbackTimelineEndedEventListenerSet.remove(callback);
            this.playbackTimelineEndedOneTimeEventListenerSet.remove(callback);
        }
    }

    @Override // com.amazon.video.sdk.player.Player
    public void destroy() {
        SDKPlayerLogger.log("destroy()");
        this.isPlayerDestroyed.set(true);
        reportInteractionStop();
        doTriggerAsync(new Triggers.Unload(new Triggers(), null, true));
    }

    public final void doTriggerAsync(final Trigger<Triggers.Type> trigger) {
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        synchronized (this.executor) {
            if (!this.executor.isShutdown()) {
                this.executor.execute(new Runnable() { // from class: com.amazon.video.sdk.player.PlayerImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlayerImpl.m2485doTriggerAsync$lambda11$lambda10(this.f$0, trigger);
                    }
                });
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: doTriggerAsync$lambda-11$lambda-10, reason: not valid java name */
    public static final void m2485doTriggerAsync$lambda11$lambda10(PlayerImpl this$0, Trigger trigger) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(trigger, "$trigger");
        this$0.doTrigger(trigger);
    }

    public final <E extends PlayerEvent> void onPlayerEvent(E playerEvent, Set<EventListener<E>> playbackEventListenerSet, Set<EventListener<E>> playbackEventOneTimeListenerSet) {
        Intrinsics.checkNotNullParameter(playerEvent, "playerEvent");
        Intrinsics.checkNotNullParameter(playbackEventListenerSet, "playbackEventListenerSet");
        Intrinsics.checkNotNullParameter(playbackEventOneTimeListenerSet, "playbackEventOneTimeListenerSet");
        Class<?> cls = playerEvent.getClass();
        if (Intrinsics.areEqual(cls, PlayerEvent.ContentStateChange.class)) {
            PlayerEvent.ContentStateChange contentStateChange = (PlayerEvent.ContentStateChange) playerEvent;
            if (this.currentContentState == contentStateChange.getContentState()) {
                DLog.logf("Ignoring ContentStateChangeEvent %s, there is no change in the current state", this.currentContentState);
                return;
            }
            this.currentContentState = contentStateChange.getContentState();
        } else if (Intrinsics.areEqual(cls, PlayerEvent.PlaybackStateChange.class)) {
            PlayerEvent.PlaybackStateChange playbackStateChange = (PlayerEvent.PlaybackStateChange) playerEvent;
            if (this.currentPlaybackState == playbackStateChange.getPlaybackState()) {
                DLog.logf("Ignoring PlaybackStateChangeEvent %s, there is no change in the current state", this.currentPlaybackState);
                return;
            }
            this.currentPlaybackState = playbackStateChange.getPlaybackState();
        } else if (Intrinsics.areEqual(cls, PlayerEvent.TimeDataChange.class)) {
            PlayerEvent.TimeDataChange timeDataChange = (PlayerEvent.TimeDataChange) playerEvent;
            if (timeDataChange.getTimeData().getCurrentPosition() == this.currentTimeData.getCurrentPosition() && timeDataChange.getTimeData().getCurrentTimelineItemIndex() == this.currentTimeData.getCurrentTimelineItemIndex()) {
                DLog.logf("Ignoring TimeDataChange, there is no change in the PlayerPosition: %s and TimelineItemIndex: %s", Long.valueOf(this.currentTimeData.getCurrentPosition()), Long.valueOf(this.currentTimeData.getCurrentTimelineItemIndex()));
                return;
            }
            this.currentTimeData = timeDataChange.getTimeData();
        }
        synchronized (playbackEventListenerSet) {
            Iterator<T> it = playbackEventListenerSet.iterator();
            while (it.hasNext()) {
                ((EventListener) it.next()).on(playerEvent);
            }
            Unit unit = Unit.INSTANCE;
        }
        synchronized (playbackEventOneTimeListenerSet) {
            Iterator<T> it2 = playbackEventOneTimeListenerSet.iterator();
            while (it2.hasNext()) {
                ((EventListener) it2.next()).on(playerEvent);
            }
            playbackEventOneTimeListenerSet.clear();
            Unit unit2 = Unit.INSTANCE;
        }
    }

    public final void handleMediaError(MediaErrorCode errorCode) {
        if (com.amazon.video.sdk.player.error.PlaybackError.INSTANCE.isErrorCodeFatal(errorCode)) {
            onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Error), this.playbackContentStateChangeEventListenerSet, this.playbackContentStateChangeOneTimeEventListenerSet);
            com.amazon.video.sdk.player.error.PlaybackError playbackErrorImpl = new com.amazon.video.sdk.player.error.PlaybackError(errorCode, this.config.getContext());
            int i = WhenMappings.$EnumSwitchMapping$2[playbackErrorImpl.getErrorType().ordinal()];
            if (i == 1) {
                this.currentPlayerError = playbackErrorImpl;
                onPlayerEvent(new PlayerEvent.PlayerError(playbackErrorImpl), this.playerErrorEventListenerSet, this.playerErrorOneTimeEventListenerSet);
            } else if (i != 2) {
                SDKPlayerLogger.warn("handleMediaError unknown errorType(%s)", playbackErrorImpl.getErrorType());
            } else {
                this.currentContentError = playbackErrorImpl;
                onPlayerEvent(new PlayerEvent.ContentError(playbackErrorImpl), this.contentErrorEventListenerSet, this.contentErrorOneTimeEventListenerSet);
            }
            DLog.errorf("Encountered a fatal error: %s of type: %s", playbackErrorImpl, playbackErrorImpl.getErrorType().name());
            doTrigger(new Triggers.Error(new Triggers()));
            return;
        }
        DLog.logf("Encountered a non-fatal error: %s, dropping the error.", errorCode == null ? null : errorCode.getName());
    }

    public final void reportInteractionPlay() {
        reportEvent(AloysiusInteractionReporter.Type.Play);
        if (!this.isSDKPlayer || this.playbackOpenSent.getAndSet(true)) {
            return;
        }
        this.aloysiusInteractionReporter.reportOpen();
        DLog.logf("Logged Aloysius Sdk Playback event of type Open");
    }

    public final void reportInteractionStop() {
        if (this.videoRenderingSettingsSetOnPresentation.get()) {
            reportEvent(AloysiusInteractionReporter.Type.Stop);
        }
    }

    public final void reportEvent(AloysiusInteractionReporter.Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.aloysiusInteractionReporter.reportEvent(type, AloysiusInteractionReporter.Source.PlayerSDK);
        DLog.logf(Intrinsics.stringPlus("Logged Aloysius Sdk Interaction of type ", type));
    }

    public final void reportPlaybackExit() {
        if (this.isSDKPlayer) {
            AloysiusPlaybackReporter aloysiusPlaybackReporter = this.aloysiusPlaybackReporter;
            if (aloysiusPlaybackReporter != null) {
                aloysiusPlaybackReporter.reportPlaybackExit(null);
            }
            DLog.logf("Logged Aloysius Sdk Playback event of type Exit");
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$AdPlaybackFeatureImpl;", "Lcom/amazon/video/sdk/player/AdPlaybackFeature;", "<init>", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public final class AdPlaybackFeatureImpl implements AdPlaybackFeature {
        final /* synthetic */ PlayerImpl this$0;

        public AdPlaybackFeatureImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0016J\u001d\u0010\u000e\u001a\u00020\u00042\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\rH\u0096\u0001J\b\u0010\u0011\u001a\u00020\u0004H\u0016¨\u0006\u0012"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlayerVideoPresentationEventListener;", "Lcom/amazon/avod/media/playback/VideoPresentationEventListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onCompletion", "", "presentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "onError", "errorCode", "Lcom/amazon/avod/media/error/MediaErrorCode;", "onPrepared", "preparedVideoPresentation", "playbackDataSource", "Lcom/amazon/avod/media/playback/PlaybackDataSource;", "onStarted", "p0", "p1", "onTerminated", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlayerVideoPresentationEventListener implements VideoPresentationEventListener {
        private final /* synthetic */ AbstractVideoPresentationEventListener $$delegate_0;
        final /* synthetic */ PlayerImpl this$0;

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onStarted(@Nonnull VideoPresentation p0, @Nonnull PlaybackDataSource p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            this.$$delegate_0.onStarted(p0, p1);
        }

        public PlayerVideoPresentationEventListener(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.$$delegate_0 = AbstractVideoPresentationEventListener.INSTANCE;
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onPrepared(VideoPresentation preparedVideoPresentation, PlaybackDataSource playbackDataSource) {
            PlaybackMediaEventReporters aloysiusReporter;
            Intrinsics.checkNotNullParameter(preparedVideoPresentation, "preparedVideoPresentation");
            Intrinsics.checkNotNullParameter(playbackDataSource, "playbackDataSource");
            if (this.this$0.videoPresentation == null) {
                this.this$0.setPresentationAndAttachListeners(preparedVideoPresentation);
            }
            PlayerImpl.checkVideoPlayerInitialized$default(this.this$0, false, 1, null);
            PlayerImpl playerImpl = this.this$0;
            VideoPlayer videoPlayer = playerImpl.videoPlayer;
            if (videoPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                videoPlayer = null;
            }
            PlaybackExperienceController playbackExperienceController = videoPlayer.getPlaybackExperienceController();
            playerImpl.aloysiusPlaybackReporter = (playbackExperienceController == null || (aloysiusReporter = playbackExperienceController.getAloysiusReporter()) == null) ? null : aloysiusReporter.getPlaybackReporter();
            PlayerImpl playerImpl2 = this.this$0;
            AdEnabledVideoPresentation adEnabledVideoPresentation = preparedVideoPresentation instanceof AdEnabledVideoPresentation ? (AdEnabledVideoPresentation) preparedVideoPresentation : null;
            AdPlan INSTANCE = adEnabledVideoPresentation == null ? null : adEnabledVideoPresentation.getAdPlan();
            if (INSTANCE == null) {
                INSTANCE = EmptyAdPlan.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
            }
            playerImpl2.currentAdPlan = INSTANCE;
            PlayerImpl playerImpl3 = this.this$0;
            playerImpl3.setCurrentTimeline(playerImpl3.timelineDelegate.updateTimeline$AmazonAndroidVideoPlayer_release(preparedVideoPresentation, this.this$0.currentAdPlan));
            PlayerImpl.initializeTimeData$default(this.this$0, 0L, 1, null);
            this.this$0.getStreamFeature().onPrepared(preparedVideoPresentation, this.this$0.videoRenderingSettings);
            this.this$0.getVolumeFeature().onPrepared(preparedVideoPresentation, this.this$0.videoRenderingSettings);
            this.this$0.getVideoPreviewAssetFeature().onPrepared(preparedVideoPresentation, this.this$0.videoRenderingSettings);
            this.this$0.debugDialogFeature.onPrepared(preparedVideoPresentation, this.this$0.videoRenderingSettings);
            this.this$0.getPlaylistFeature().onPrepared(preparedVideoPresentation, this.this$0.videoRenderingSettings);
            PlayerImpl playerImpl4 = this.this$0;
            playerImpl4.onPlayerEvent(new PlayerEvent.TimelineChange(playerImpl4.currentTimeline), this.this$0.playbackTimelineChangeEventListenerSet, this.this$0.playbackTimelineChangeOneTimeEventListenerSet);
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Ready), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onCompletion(VideoPresentation presentation) {
            Intrinsics.checkNotNullParameter(presentation, "presentation");
            PlayerImpl playerImpl = this.this$0;
            playerImpl.onPlayerEvent(PlayerEvent.TimelineEnded.INSTANCE, playerImpl.playbackTimelineEndedEventListenerSet, this.this$0.playbackTimelineEndedOneTimeEventListenerSet);
            this.this$0.doTriggerAsync(new Triggers.Unload(new Triggers(), null, false));
            Integer currentItemIndex = this.this$0.getPlaylistFeature().get_currentItemIndex();
            if (currentItemIndex == null) {
                return;
            }
            PlayerImpl playerImpl2 = this.this$0;
            int iIntValue = currentItemIndex.intValue();
            if (!playerImpl2.getPlaylistFeature().get_autoplay() || iIntValue >= playerImpl2.getPlaylistFeature().getPlaylist().getItems().size() - 1) {
                return;
            }
            playerImpl2.getPlaylistFeature().play(iIntValue + 1, playerImpl2.getRenderingConfig());
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onError(VideoPresentation presentation, MediaErrorCode errorCode) {
            this.this$0.handleMediaError(errorCode);
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onTerminated() {
            this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Paused), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
            this.this$0.setExternalPlaybackState(ClientPlaybackState.Paused);
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Unloaded), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
            if (this.this$0.isPlayerDestroyed.get()) {
                this.this$0.terminationLatch.countDown();
            }
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0019\u0010\b\u001a\u00020\u00042\u000e\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00060\u0006H\u0096\u0001J\u001c\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0010"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackStateEventListenerImpl;", "Lcom/amazon/avod/playback/PlaybackStateEventListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onPause", "", "playbackEventContext", "Lcom/amazon/avod/playback/PlaybackEventContext;", "onResume", "onSeekEnd", "p0", "kotlin.jvm.PlatformType", "onSeekStart", "targetSeekTime", "Lcom/amazon/avod/media/TimeSpan;", "onStart", "onStop", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackStateEventListenerImpl implements PlaybackStateEventListener {
        private final /* synthetic */ AbstractPlaybackStateEventListener $$delegate_0;
        final /* synthetic */ PlayerImpl this$0;

        @Override // com.amazon.avod.playback.PlaybackStateEventListener
        public void onSeekEnd(PlaybackEventContext p0) {
            this.$$delegate_0.onSeekEnd(p0);
        }

        public PlaybackStateEventListenerImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.$$delegate_0 = AbstractPlaybackStateEventListener.INSTANCE;
        }

        @Override // com.amazon.avod.playback.PlaybackStateEventListener
        public void onStart(PlaybackEventContext playbackEventContext) {
            VideoPlayer videoPlayer = null;
            PlayerImpl.checkVideoPlayerInitialized$default(this.this$0, false, 1, null);
            if (this.this$0.getExternalPlaybackState() == ClientPlaybackState.Uninitialized || this.this$0.getExternalPlaybackState() == ClientPlaybackState.Paused) {
                this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Playing), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
            }
            this.this$0.setExternalPlaybackState(ClientPlaybackState.Playing);
            VolumeFeatureImpl volumeFeature = this.this$0.getVolumeFeature();
            VideoPlayer videoPlayer2 = this.this$0.videoPlayer;
            if (videoPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
            } else {
                videoPlayer = videoPlayer2;
            }
            volumeFeature.setPlaybackExperienceController(videoPlayer.getPlaybackExperienceController());
            this.this$0.aloysiusCyclicEventMasterController.onPlaybackStarted();
        }

        @Override // com.amazon.avod.playback.PlaybackStateEventListener
        public void onResume(PlaybackEventContext playbackEventContext) {
            if (this.this$0.getExternalPlaybackState() == ClientPlaybackState.Paused) {
                this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Playing), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
            }
            this.this$0.setExternalPlaybackState(ClientPlaybackState.Playing);
        }

        @Override // com.amazon.avod.playback.PlaybackStateEventListener
        public void onPause(PlaybackEventContext playbackEventContext) {
            if (this.this$0.getExternalPlaybackState() == ClientPlaybackState.Pausing) {
                this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Paused), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
                this.this$0.setExternalPlaybackState(ClientPlaybackState.Paused);
            }
        }

        @Override // com.amazon.avod.playback.PlaybackStateEventListener
        public void onStop(PlaybackEventContext playbackEventContext) {
            this.this$0.aloysiusCyclicEventMasterController.onPlaybackStopped();
        }

        @Override // com.amazon.avod.playback.PlaybackStateEventListener
        public void onSeekStart(TimeSpan targetSeekTime, PlaybackEventContext playbackEventContext) {
            if (targetSeekTime != null) {
                Object obj = this.this$0.mainContentOffsetMsUpdateMutex;
                PlayerImpl playerImpl = this.this$0;
                synchronized (obj) {
                    playerImpl.seekOverride.set(true);
                    playerImpl.initializeTimeData(targetSeekTime.getTotalMilliseconds());
                    Unit unit = Unit.INSTANCE;
                }
                return;
            }
            DLog.warnf("Provided seek time is null");
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0001J'\u0010\u0005\u001a\u00020\u00042\u0010\u0010\u0006\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackContentStateChangeEventListenerImpl;", "Lcom/amazon/avod/playback/PlaybackContentEventListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onContentDownloaded", "", "onContentMetadataAvailable", "availableVideoResolutions", "", "Lcom/amazon/avod/media/VideoResolution;", "videoFramerateFps", "", "([Lcom/amazon/avod/media/VideoResolution;D)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackContentStateChangeEventListenerImpl implements PlaybackContentEventListener {
        private final /* synthetic */ AbstractPlaybackContentEventListener $$delegate_0;
        final /* synthetic */ PlayerImpl this$0;

        @Override // com.amazon.avod.playback.PlaybackContentEventListener
        public void onContentDownloaded() {
            this.$$delegate_0.onContentDownloaded();
        }

        public PlaybackContentStateChangeEventListenerImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.$$delegate_0 = AbstractPlaybackContentEventListener.INSTANCE;
        }

        @Override // com.amazon.avod.playback.PlaybackContentEventListener
        public void onContentMetadataAvailable(VideoResolution[] availableVideoResolutions, double videoFramerateFps) {
            if (this.this$0.currentContentState != ContentState.Ready) {
                this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Waiting), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
            }
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0011\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0001J&\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackSessionBufferEventListenerImpl;", "Lcom/amazon/avod/playback/PlaybackSessionBufferEventListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onBufferEnd", "", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/avod/playback/PlaybackBufferEventType;", "playbackEventContext", "Lcom/amazon/avod/playback/PlaybackEventContext;", "onBufferProgress", "p0", "", "onBufferStart", "bufferingAnalysis", "Lcom/amazon/avod/playback/BufferingAnalysis;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackSessionBufferEventListenerImpl implements PlaybackSessionBufferEventListener {
        private final /* synthetic */ AbstractPlaybackSessionBufferEventListener $$delegate_0;
        final /* synthetic */ PlayerImpl this$0;

        @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
        public void onBufferProgress(float p0) {
            this.$$delegate_0.onBufferProgress(p0);
        }

        public PlaybackSessionBufferEventListenerImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.$$delegate_0 = AbstractPlaybackSessionBufferEventListener.INSTANCE;
        }

        @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
        public void onBufferStart(PlaybackBufferEventType type, PlaybackEventContext playbackEventContext, BufferingAnalysis bufferingAnalysis) {
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Waiting), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
        }

        @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
        public void onBufferEnd(PlaybackBufferEventType type, PlaybackEventContext playbackEventContext) {
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Ready), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J$\u0010\u0007\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackAudioTrackChangeListenerImpl;", "Lcom/amazon/avod/playback/AudioTrackChangeListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onAudioTrackChangeCompleted", "", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/avod/playback/AudioTrackChangeListener$Status;", "onAudioTrackChangeStarted", "currentTrackId", "Lcom/google/common/base/Optional;", "", "desiredTrackId", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackAudioTrackChangeListenerImpl implements AudioTrackChangeListener {
        final /* synthetic */ PlayerImpl this$0;

        public PlaybackAudioTrackChangeListenerImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.playback.AudioTrackChangeListener
        public void onAudioTrackChangeStarted(Optional<String> currentTrackId, Optional<String> desiredTrackId) {
            Intrinsics.checkNotNullParameter(currentTrackId, "currentTrackId");
            Intrinsics.checkNotNullParameter(desiredTrackId, "desiredTrackId");
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Waiting), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
        }

        @Override // com.amazon.avod.playback.AudioTrackChangeListener
        public void onAudioTrackChangeCompleted(AudioTrackChangeListener.Status status) {
            Intrinsics.checkNotNullParameter(status, "status");
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Ready), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
            if (this.this$0.getExternalPlaybackState() == ClientPlaybackState.Paused) {
                this.this$0.doTriggerAsync(new Triggers.Play(new Triggers(), false));
                this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Playing), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
                this.this$0.setExternalPlaybackState(ClientPlaybackState.Playing);
            }
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackTimeDataChangeEventListenerImpl;", "Lcom/amazon/avod/playback/PlaybackTimeDataEventListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onTimeDataChange", "", "currentPositionInMs", "", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackTimeDataChangeEventListenerImpl implements PlaybackTimeDataEventListener {
        final /* synthetic */ PlayerImpl this$0;

        public PlaybackTimeDataChangeEventListenerImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.playback.PlaybackTimeDataEventListener
        public void onTimeDataChange(long currentPositionInMs) {
            this.this$0.initializeTimeData(currentPositionInMs);
        }
    }

    static /* synthetic */ void initializeTimeData$default(PlayerImpl playerImpl, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initializeTimeData");
        }
        if ((i & 1) != 0) {
            j = 0;
        }
        playerImpl.initializeTimeData(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[Catch: all -> 0x00c8, TryCatch #1 {, blocks: (B:9:0x0010, B:11:0x0021, B:17:0x0048, B:20:0x0053, B:23:0x005b, B:31:0x0084, B:33:0x0088, B:26:0x0077, B:29:0x007e, B:14:0x0040, B:34:0x0090), top: B:59:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void initializeTimeData(long r10) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.video.sdk.player.PlayerImpl.initializeTimeData(long):void");
    }

    public static /* synthetic */ long getTimelineItemIndex$default(PlayerImpl playerImpl, ContentType contentType, long j, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTimelineItemIndex");
        }
        if ((i & 4) != 0) {
            str = null;
        }
        return playerImpl.getTimelineItemIndex(contentType, j, str);
    }

    public final long getTimelineItemIndex(ContentType contentType, long currentPosition, String adId) {
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        int i = 0;
        for (TimelineItem timelineItem : this.currentTimeline.getItems()) {
            int i2 = i + 1;
            if (timelineItem.getContentInfo().getContentType() == contentType) {
                if (contentType == ContentType.FEATURE) {
                    TimelineItemData timelineItemData = timelineItem instanceof TimelineItemData ? (TimelineItemData) timelineItem : null;
                    Long absoluteStartTime = timelineItemData == null ? null : timelineItemData.getAbsoluteStartTime();
                    if (absoluteStartTime == null) {
                        absoluteStartTime = timelineItem.getStartTime();
                    }
                    Long absoluteEndTime = timelineItemData != null ? timelineItemData.getAbsoluteEndTime() : null;
                    if (absoluteEndTime == null) {
                        absoluteEndTime = timelineItem.getEndTime();
                    }
                    if (currentPosition < (absoluteStartTime == null ? Long.MIN_VALUE : absoluteStartTime.longValue())) {
                        continue;
                    } else {
                        if (currentPosition < (absoluteEndTime == null ? Long.MAX_VALUE : absoluteEndTime.longValue())) {
                            return i;
                        }
                    }
                } else if (adId != null && StringsKt__StringsJVMKt.equals(adId, timelineItem.getContentInfo().getTitleId(), true)) {
                    return i;
                }
            }
            i = i2;
        }
        return this.currentTimeData.getCurrentTimelineItemIndex();
    }

    private final PlayableRange getPlayableRange() {
        VideoPlayer videoPlayer = null;
        checkVideoPlayerInitialized$default(this, false, 1, null);
        ContentConfig currentContent = getCurrentContent();
        Intrinsics.checkNotNull(currentContent);
        if (currentContent.getVideoType().isLive$AmazonAndroidVideoPlayer_release()) {
            VideoPlayer videoPlayer2 = this.videoPlayer;
            if (videoPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                videoPlayer2 = null;
            }
            PlaybackExperienceController playbackExperienceController = videoPlayer2.getPlaybackExperienceController();
            if (playbackExperienceController != null) {
                return new PlayableRangeData(TimeUnit.EPOCH_MILLIS, Long.valueOf(playbackExperienceController.getLiveTimeWindowStartMillis()), Long.valueOf(playbackExperienceController.getLiveTimeWindowEndMillis()));
            }
        }
        TimeUnit timeUnit = TimeUnit.CONTENT_MILLIS;
        VideoPlayer videoPlayer3 = this.videoPlayer;
        if (videoPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
        } else {
            videoPlayer = videoPlayer3;
        }
        return new PlayableRangeData(timeUnit, 0L, Long.valueOf(videoPlayer.getDuration()));
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackAdPlanUpdateListener;", "Lcom/amazon/avod/media/ads/internal/state/AdPlanUpdateListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onPlanUpdated", "", "plan", "Lcom/amazon/avod/media/ads/AdPlan;", "onTimelineUpdated", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackAdPlanUpdateListener implements AdPlanUpdateListener {
        final /* synthetic */ PlayerImpl this$0;

        public PlaybackAdPlanUpdateListener(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener
        public void onPlanUpdated(final AdPlan plan) {
            Intrinsics.checkNotNullParameter(plan, "plan");
            ExecutorService executorService = this.this$0.executor;
            final PlayerImpl playerImpl = this.this$0;
            executorService.execute(new Runnable() { // from class: com.amazon.video.sdk.player.PlayerImpl$PlaybackAdPlanUpdateListener$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerImpl.PlaybackAdPlanUpdateListener.m2486onPlanUpdated$lambda0(playerImpl, this, plan);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onPlanUpdated$lambda-0, reason: not valid java name */
        public static final void m2486onPlanUpdated$lambda0(PlayerImpl this$0, PlaybackAdPlanUpdateListener this$1, AdPlan plan) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(plan, "$plan");
            if (!Intrinsics.areEqual(this$0.adPlanUpdateListener, this$1)) {
                DLog.warnf("PlaybackAdPlanUpdateListener ignoring onTimelineUpdated as listener is not attached to player");
                return;
            }
            if (plan.hasPlayableAds()) {
                this$0.currentAdPlan = plan;
                if (this$0.currentTimeline.getItems().size() == 1) {
                    this$0.setCurrentTimeline(this$0.timelineDelegate.updateTimeline$AmazonAndroidVideoPlayer_release(this$0.videoPresentation, this$0.currentAdPlan));
                    PlayerImpl.initializeTimeData$default(this$0, 0L, 1, null);
                    this$0.onPlayerEvent(new PlayerEvent.TimelineChange(this$0.currentTimeline), this$0.playbackTimelineChangeEventListenerSet, this$0.playbackTimelineChangeOneTimeEventListenerSet);
                }
            }
        }

        @Override // com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener
        public void onTimelineUpdated() {
            ExecutorService executorService = this.this$0.executor;
            final PlayerImpl playerImpl = this.this$0;
            executorService.execute(new Runnable() { // from class: com.amazon.video.sdk.player.PlayerImpl$PlaybackAdPlanUpdateListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerImpl.PlaybackAdPlanUpdateListener.m2487onTimelineUpdated$lambda1(playerImpl, this);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onTimelineUpdated$lambda-1, reason: not valid java name */
        public static final void m2487onTimelineUpdated$lambda1(PlayerImpl this$0, PlaybackAdPlanUpdateListener this$1) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            if (Intrinsics.areEqual(this$0.adPlanUpdateListener, this$1)) {
                this$0.setCurrentTimeline(this$0.timelineDelegate.updateTimeline$AmazonAndroidVideoPlayer_release(this$0.videoPresentation, this$0.currentAdPlan));
                PlayerImpl.initializeTimeData$default(this$0, 0L, 1, null);
                this$0.onPlayerEvent(new PlayerEvent.TimelineChange(this$0.currentTimeline), this$0.playbackTimelineChangeEventListenerSet, this$0.playbackTimelineChangeOneTimeEventListenerSet);
                return;
            }
            DLog.warnf("PlaybackAdPlanUpdateListener ignoring onTimelineUpdated as listener is not attached to player");
        }
    }

    static /* synthetic */ boolean checkVideoPlayerInitialized$default(PlayerImpl playerImpl, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkVideoPlayerInitialized");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return playerImpl.checkVideoPlayerInitialized(z);
    }

    private final boolean checkVideoPlayerInitialized(boolean isFatal) {
        boolean z;
        VideoPlayer videoPlayer = this.videoPlayer;
        if (videoPlayer != null) {
            if (videoPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                videoPlayer = null;
            }
            if (!(videoPlayer instanceof UninitializedVideoPlayer)) {
                z = true;
            } else {
                if (isFatal) {
                    throw new UninitializedPropertyAccessException(((UninitializedInstance) videoPlayer).getErrorMessage());
                }
                z = false;
            }
            if (z) {
                return true;
            }
        }
        if (isFatal) {
            throw new UninitializedPropertyAccessException("videoPlayer is in an uninitialized state");
        }
        DLog.warnf("videoPlayer is in an uninitialized state");
        return false;
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackAdEventListener;", "Lcom/amazon/avod/media/ads/AdEnabledVideoEventListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "isSubtitlesEnabled", "", "onAdBreakEnd", "", "onAdBreakError", "adBreak", "Lcom/amazon/avod/media/ads/AdBreak;", "error", "Lcom/amazon/avod/media/ads/AdError;", "onAdClipError", "adClip", "Lcom/amazon/avod/media/ads/AdClip;", "onBeginAdBreak", "onBeginAdClip", "onEndAdBreak", "onEndAdClip", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackAdEventListener implements AdEnabledVideoEventListener {
        private final /* synthetic */ AbstractAdEnabledVideoEventListener $$delegate_0;
        private boolean isSubtitlesEnabled;
        final /* synthetic */ PlayerImpl this$0;

        public PlaybackAdEventListener(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.$$delegate_0 = AbstractAdEnabledVideoEventListener.INSTANCE;
        }

        @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
        public void onBeginAdClip(AdClip adClip) {
            Intrinsics.checkNotNullParameter(adClip, "adClip");
            this.this$0.currentAdClip = adClip;
            this.this$0.updateCurrentTimelineItemIndex.set(true);
            PlayerImpl.initializeTimeData$default(this.this$0, 0L, 1, null);
        }

        @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
        public void onEndAdClip(AdClip adClip) {
            Intrinsics.checkNotNullParameter(adClip, "adClip");
            this.this$0.currentAdClip = null;
        }

        @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
        public void onBeginAdBreak(AdBreak adBreak) {
            TimedTextStreamGroup timedTextStreams;
            Intrinsics.checkNotNullParameter(adBreak, "adBreak");
            this.this$0.doTriggerAsync(new Triggers.Play(new Triggers(), false));
            this.this$0.isAdBreakPlayed.set(adBreak.isPlayed());
            TimedTextStreamGroup timedTextStreams2 = this.this$0.getStreamFeature().getTimedTextStreams();
            boolean zIsShowingCaptions = timedTextStreams2 == null ? false : timedTextStreams2.isShowingCaptions();
            this.isSubtitlesEnabled = zIsShowingCaptions;
            if (zIsShowingCaptions && (timedTextStreams = this.this$0.getStreamFeature().getTimedTextStreams()) != null) {
                timedTextStreams.showCaptions(false);
            }
            this.this$0.aloysiusCyclicEventMasterController.onPlaybackStarted();
        }

        @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
        public void onEndAdBreak(AdBreak adBreak) {
            Intrinsics.checkNotNullParameter(adBreak, "adBreak");
            onAdBreakEnd();
        }

        @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
        public void onAdClipError(AdClip adClip, AdError error) {
            Intrinsics.checkNotNullParameter(adClip, "adClip");
            Intrinsics.checkNotNullParameter(error, "error");
            this.this$0.currentAdClip = null;
        }

        @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
        public void onAdBreakError(AdBreak adBreak, AdError error) {
            Intrinsics.checkNotNullParameter(adBreak, "adBreak");
            Intrinsics.checkNotNullParameter(error, "error");
            onAdBreakEnd();
        }

        private final void onAdBreakEnd() {
            TimedTextStreamGroup timedTextStreams;
            if (!this.isSubtitlesEnabled || (timedTextStreams = this.this$0.getStreamFeature().getTimedTextStreams()) == null) {
                return;
            }
            timedTextStreams.showCaptions(true);
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0001J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlaybackAdStateChangeListenerImpl;", "Lcom/amazon/avod/playback/PlaybackAdStateChangeListener;", "(Lcom/amazon/video/sdk/player/PlayerImpl;)V", "onAdPlaybackCompleted", "", "onAdPlaybackLoaded", "onAdPlaybackPaused", "onAdPlaybackPlaying", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackAdStateChangeListenerImpl implements PlaybackAdStateChangeListener {
        private final /* synthetic */ AbstractPlaybackAdStateChangeListener $$delegate_0;
        final /* synthetic */ PlayerImpl this$0;

        @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
        public void onAdPlaybackCompleted() {
            this.$$delegate_0.onAdPlaybackCompleted();
        }

        public PlaybackAdStateChangeListenerImpl(PlayerImpl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.$$delegate_0 = AbstractPlaybackAdStateChangeListener.INSTANCE;
        }

        @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
        public void onAdPlaybackPaused() {
            if (this.this$0.getExternalPlaybackState() == ClientPlaybackState.Pausing) {
                this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Paused), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
                this.this$0.setExternalPlaybackState(ClientPlaybackState.Paused);
            }
        }

        @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
        public void onAdPlaybackPlaying() {
            if (this.this$0.getExternalPlaybackState() == ClientPlaybackState.Uninitialized || this.this$0.getExternalPlaybackState() == ClientPlaybackState.Paused) {
                this.this$0.onPlayerEvent(new PlayerEvent.PlaybackStateChange(PlaybackState.Playing), this.this$0.playbackStateChangeEventListenerSet, this.this$0.playbackStateChangeOneTimeEventListenerSet);
            }
            this.this$0.setExternalPlaybackState(ClientPlaybackState.Playing);
        }

        @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
        public void onAdPlaybackLoaded() {
            this.this$0.onPlayerEvent(new PlayerEvent.ContentStateChange(ContentState.Ready), this.this$0.playbackContentStateChangeEventListenerSet, this.this$0.playbackContentStateChangeOneTimeEventListenerSet);
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u000bB\u0017\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0004\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "Lcom/amazon/avod/fsm/StateBase;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState$Type;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "getType", "_type", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState$Type;", "Lcom/amazon/video/sdk/player/PlayerImpl;", "player", "<init>", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState$Type;)V", "Type", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static abstract class PlayerFeatureState extends StateBase<Type, Triggers.Type> {
        private final Type _type;

        /* JADX INFO: compiled from: PlayerImpl.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState$Type;", "", "(Ljava/lang/String;I)V", "IDLE", "LOADING", "PLAYING", "PAUSED", "SEEKING", "UNLOADING", "DESTROYED", MediaError.ERROR_TYPE_ERROR, "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public enum Type {
            IDLE,
            LOADING,
            PLAYING,
            PAUSED,
            SEEKING,
            UNLOADING,
            DESTROYED,
            ERROR
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlayerFeatureState(PlayerImpl player, Type _type) {
            super(player);
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(_type, "_type");
            this._type = _type;
        }

        @Override // com.amazon.avod.fsm.State
        /* JADX INFO: renamed from: getType, reason: from getter */
        public Type get_type() {
            return this._type;
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$IdleState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class IdleState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdleState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.IDLE);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) {
            this.this$0.isPlaying.set(false);
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$LoadingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "getLivePlaybackResources", "Lcom/amazon/avod/playbackresource/PlaybackResources;", "content", "Lcom/amazon/video/sdk/player/ContentConfig;", "initialAudioTrackId", "", "sanitizePlaybackEnvelopeUsage", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LoadingState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LoadingState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.LOADING);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) throws ContentException {
            PlaybackResources livePlaybackResources;
            Triggers.Load load = trigger instanceof Triggers.Load ? (Triggers.Load) trigger : null;
            if (load == null) {
                DLog.warnf("Cast error: Trigger cannot be cast to Load");
                return;
            }
            if (load.getIsFollowedByPlaybackStart()) {
                this.this$0.reportInteractionPlay();
            }
            this.this$0.updateRenderingConfig(load.getRenderingConfig());
            ContentConfig contentConfigSanitizePlaybackEnvelopeUsage = sanitizePlaybackEnvelopeUsage(load.getContent());
            if (!Intrinsics.areEqual(contentConfigSanitizePlaybackEnvelopeUsage, this.this$0.getCurrentContent())) {
                PlayerImpl playerImpl = this.this$0;
                playerImpl.videoOptions = playerImpl.videoSpecUtil.createVideoOptions$AmazonAndroidVideoPlayer_release(contentConfigSanitizePlaybackEnvelopeUsage);
                ImmutableList<String> immutableListGenerateAudioTrackIdsFromStreamMatchers = this.this$0.mtaUtils.generateAudioTrackIdsFromStreamMatchers(this.this$0.getStreamFeature().getAudioStreamPreferences());
                String initialAudioTrackId = this.this$0.mtaUtils.getInitialAudioTrackId(contentConfigSanitizePlaybackEnvelopeUsage, immutableListGenerateAudioTrackIdsFromStreamMatchers);
                if (contentConfigSanitizePlaybackEnvelopeUsage.getVideoType().isLive$AmazonAndroidVideoPlayer_release()) {
                    try {
                        livePlaybackResources = getLivePlaybackResources(contentConfigSanitizePlaybackEnvelopeUsage, initialAudioTrackId);
                    } catch (MediaException e) {
                        this.this$0.handleMediaError(e.getErrorCode().getExternalCode());
                        return;
                    }
                } else {
                    livePlaybackResources = null;
                }
                PlayerImpl playerImpl2 = this.this$0;
                playerImpl2.videoSpecification = playerImpl2.videoSpecUtil.createVideoSpec$AmazonAndroidVideoPlayer_release(contentConfigSanitizePlaybackEnvelopeUsage, null, immutableListGenerateAudioTrackIdsFromStreamMatchers, initialAudioTrackId, livePlaybackResources, this.this$0.getSdkConfig().getEPrivacyConsentDataProvider().getEPrivacyConsentData());
            }
            this.this$0.setCurrentContent$AmazonAndroidVideoPlayer_release(contentConfigSanitizePlaybackEnvelopeUsage);
            Optional<PVDownloadPlayerShim> offlineDownloadIfPresent = this.this$0.downloadManager.getOfflineDownloadIfPresent(contentConfigSanitizePlaybackEnvelopeUsage.getTitleId(), contentConfigSanitizePlaybackEnvelopeUsage.getSessionContext());
            Intrinsics.checkNotNullExpressionValue(offlineDownloadIfPresent, "downloadManager.getOffli…, content.sessionContext)");
            PlayerImpl playerImpl3 = this.this$0;
            PVDownloadPlayerShim pVDownloadPlayerShimOrNull = offlineDownloadIfPresent.orNull();
            playerImpl3.storagePath = pVDownloadPlayerShimOrNull != null ? pVDownloadPlayerShimOrNull.getStoragePath() : null;
            if (!load.getIsFollowedByPlaybackStart()) {
                PresentationCache presentationCache = this.this$0.presentationCache;
                VideoSpecification videoSpecification = this.this$0.videoSpecification;
                Intrinsics.checkNotNull(videoSpecification);
                VideoOptions videoOptions = this.this$0.videoOptions;
                Intrinsics.checkNotNull(videoOptions);
                presentationCache.preparePresentationAsync(videoSpecification, videoOptions, this.this$0.storagePath, this.this$0.videoPresentationEventListener);
            } else {
                try {
                    PlaylistFeatureImpl playlistFeature = this.this$0.getPlaylistFeature();
                    ContentConfig currentContent = this.this$0.getCurrentContent();
                    Intrinsics.checkNotNull(currentContent);
                    playlistFeature.registerStickyVideoPresentationEventListener$AmazonAndroidVideoPlayer_release(currentContent, this.this$0.videoPresentationEventListener);
                    if (this.this$0.cachedVideoPresentation == null) {
                        PlayerImpl playerImpl4 = this.this$0;
                        PresentationCache presentationCache2 = playerImpl4.presentationCache;
                        VideoSpecification videoSpecification2 = this.this$0.videoSpecification;
                        Intrinsics.checkNotNull(videoSpecification2);
                        VideoOptions videoOptions2 = this.this$0.videoOptions;
                        Intrinsics.checkNotNull(videoOptions2);
                        playerImpl4.cachedVideoPresentation = presentationCache2.getPresentation(videoSpecification2, videoOptions2, this.this$0.storagePath, this.this$0.videoPresentationEventListener);
                    }
                    CachedPresentationCallback callback = ((Triggers.Load) trigger).getCallback();
                    if (callback != null) {
                        CachedVideoPresentation cachedVideoPresentation = this.this$0.cachedVideoPresentation;
                        Intrinsics.checkNotNull(cachedVideoPresentation);
                        callback.onCachedPresentationCreated(cachedVideoPresentation);
                    }
                    PlayerImpl playerImpl5 = this.this$0;
                    CachedVideoPresentation cachedVideoPresentation2 = playerImpl5.cachedVideoPresentation;
                    Intrinsics.checkNotNull(cachedVideoPresentation2);
                    VideoPresentation presentation = cachedVideoPresentation2.getPresentation();
                    Intrinsics.checkNotNullExpressionValue(presentation, "cachedVideoPresentation!!.presentation");
                    playerImpl5.setPresentationAndAttachListeners(presentation);
                } catch (PrepareFailedException e2) {
                    this.this$0.handleMediaError(e2.getErrorCode());
                    CachedPresentationCallback callback2 = ((Triggers.Load) trigger).getCallback();
                    if (callback2 == null) {
                        return;
                    }
                    callback2.onPresentationError(e2);
                    return;
                }
            }
            this.this$0.isPlaying.set(false);
        }

        private final ContentConfig sanitizePlaybackEnvelopeUsage(ContentConfig content) {
            if (content.getPlaybackEnvelope() == null || !this.this$0.isSDKPlayer) {
                return content;
            }
            DLog.errorf("playbackEnvelope is not supported for non-prime video tenant use, stripping and routing to PRSv1");
            return new ContentConfigData(content.getPlaybackToken(), content.getTitleId(), content.getPosition(), content.getVideoType(), content.getSessionContext(), null, content.getDoNotUseStoredAssets(), content.getShowAds());
        }

        private final PlaybackResources getLivePlaybackResources(ContentConfig content, String initialAudioTrackId) throws ContentException {
            VideoMaterialType videoMaterialTypeFromPlayersContentType = VideoMaterialTypeUtils.fromPlayersContentType(VideoTypeUtilsKt.toPlayersContentType(content.getVideoType()));
            Intrinsics.checkNotNullExpressionValue(videoMaterialTypeFromPlayersContentType, "fromPlayersContentType(t…tType(content.videoType))");
            PlaybackResources validatedResources = this.this$0.playerResourcesCache.getValidatedResources(new PlayerResourcesCacheRequest(content.getTitleId(), videoMaterialTypeFromPlayersContentType, initialAudioTrackId, content.getPlaybackToken(), ConsumptionType.Streaming, content.getPlaybackEnvelope(), content.getSessionContext(), ContentSessionType.STREAMING, true, content.getShowAds(), this.this$0.getSdkConfig().getEPrivacyConsentDataProvider().getEPrivacyConsentData()));
            Intrinsics.checkNotNullExpressionValue(validatedResources, "playerResourcesCache.get…lidatedResources(request)");
            return validatedResources;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPresentationAndAttachListeners(VideoPresentation presentation) {
        this.videoPresentation = presentation;
        Intrinsics.checkNotNull(presentation);
        VideoPlayer player = presentation.getPlayer();
        Intrinsics.checkNotNullExpressionValue(player, "videoPresentation!!.player");
        this.videoPlayer = player;
        if (this.isSDKPlayer) {
            VideoPresentation videoPresentation = this.videoPresentation;
            VideoPlayer videoPlayer = null;
            AdEnabledVideoPresentation adEnabledVideoPresentation = videoPresentation instanceof AdEnabledVideoPresentation ? (AdEnabledVideoPresentation) videoPresentation : null;
            PlaybackAdPlanUpdateListener playbackAdPlanUpdateListener = new PlaybackAdPlanUpdateListener(this);
            this.adPlanUpdateListener = playbackAdPlanUpdateListener;
            if (adEnabledVideoPresentation != null) {
                adEnabledVideoPresentation.addAdPlanUpdateListener(playbackAdPlanUpdateListener);
            }
            if (adEnabledVideoPresentation != null) {
                adEnabledVideoPresentation.setAdEventListener(new PlaybackAdEventListener(this));
            }
            VideoPlayer videoPlayer2 = this.videoPlayer;
            if (videoPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
            } else {
                videoPlayer = videoPlayer2;
            }
            videoPlayer.addListener(new PlaybackStateEventListenerImpl(this));
            videoPlayer.addListener(new PlaybackContentStateChangeEventListenerImpl(this));
            videoPlayer.addListener(new PlaybackSessionBufferEventListenerImpl(this));
            videoPlayer.addListener(new PlaybackAudioTrackChangeListenerImpl(this));
            videoPlayer.addListener(new PlaybackTimeDataChangeEventListenerImpl(this));
            videoPlayer.addListener(new PlaybackAdStateChangeListenerImpl(this));
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PlayingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlayingState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlayingState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.PLAYING);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) {
            VideoPresentation videoPresentation;
            VideoPlayer videoPlayer = null;
            Triggers.Play play = trigger instanceof Triggers.Play ? (Triggers.Play) trigger : null;
            if (play != null) {
                this.this$0.isPlaying.set(true);
                if (play.getOnSeekEnd()) {
                    return;
                }
                PlayerImpl.checkVideoPlayerInitialized$default(this.this$0, false, 1, null);
                if (this.this$0.videoRenderingSettingsSetOnPresentation.compareAndSet(false, true) && (videoPresentation = this.this$0.videoPresentation) != null) {
                    videoPresentation.setRenderingSettings(this.this$0.videoRenderingSettings);
                }
                VideoPlayer videoPlayer2 = this.this$0.videoPlayer;
                if (videoPlayer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                } else {
                    videoPlayer = videoPlayer2;
                }
                videoPlayer.start();
                return;
            }
            DLog.warnf("Cast error: Trigger cannot be cast to Play");
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$PausedState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PausedState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PausedState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.PAUSED);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) {
            VideoPlayer videoPlayer = null;
            Triggers.Pause pause = trigger instanceof Triggers.Pause ? (Triggers.Pause) trigger : null;
            if (pause != null) {
                this.this$0.isPlaying.set(false);
                if (pause.getOnSeekEnd()) {
                    return;
                }
                PlayerImpl.checkVideoPlayerInitialized$default(this.this$0, false, 1, null);
                if (this.this$0.currentPlaybackState == PlaybackState.Playing) {
                    this.this$0.setExternalPlaybackState(ClientPlaybackState.Pausing);
                    VideoPlayer videoPlayer2 = this.this$0.videoPlayer;
                    if (videoPlayer2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                    } else {
                        videoPlayer = videoPlayer2;
                    }
                    videoPlayer.pause();
                    return;
                }
                return;
            }
            DLog.warnf("Cast error: Trigger cannot be cast to Pause");
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$SeekingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class SeekingState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SeekingState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.SEEKING);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) {
            VideoPlayer videoPlayer = null;
            Triggers.Seek seek = trigger instanceof Triggers.Seek ? (Triggers.Seek) trigger : null;
            if (seek == null) {
                DLog.warnf("Cast error: Trigger cannot be cast to Seek");
                return;
            }
            PlayerImpl.checkVideoPlayerInitialized$default(this.this$0, false, 1, null);
            AtomicLong atomicLong = this.this$0.seekPosition;
            long position = seek.getPosition();
            Long start = this.this$0.currentTimeData.getPlayableRange().getStart();
            long jMax = Math.max(position, start == null ? 0L : start.longValue());
            Long end = this.this$0.currentTimeData.getPlayableRange().getEnd();
            atomicLong.set(Math.min(jMax, end == null ? Long.MAX_VALUE : end.longValue()));
            if (this.this$0.currentTimeData.getUnit() == TimeUnit.EPOCH_MILLIS) {
                VideoPlayer videoPlayer2 = this.this$0.videoPlayer;
                if (videoPlayer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                } else {
                    videoPlayer = videoPlayer2;
                }
                videoPlayer.seekToUTC(this.this$0.seekPosition.get());
            } else {
                VideoPlayer videoPlayer3 = this.this$0.videoPlayer;
                if (videoPlayer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoPlayer");
                } else {
                    videoPlayer = videoPlayer3;
                }
                videoPlayer.seekTo(this.this$0.seekPosition.get());
            }
            if (this.this$0.isPlaying.get()) {
                doTrigger(new Triggers.Play(new Triggers(), true));
            } else {
                doTrigger(new Triggers.Pause(new Triggers(), true));
            }
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$UnloadingState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class UnloadingState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnloadingState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.UNLOADING);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0100  */
        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void enter(com.amazon.avod.fsm.Trigger<com.amazon.video.sdk.player.fsm.Triggers.Type> r5) {
            /*
                Method dump skipped, instruction units count: 305
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.video.sdk.player.PlayerImpl.UnloadingState.enter(com.amazon.avod.fsm.Trigger):void");
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$DestroyedState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class DestroyedState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DestroyedState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.DESTROYED);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) throws InterruptedException {
            if ((trigger instanceof Triggers.Destroy ? (Triggers.Destroy) trigger : null) == null) {
                DLog.warnf("Cast error: Trigger cannot be cast to Destroy");
                return;
            }
            DLog.logf("Rothko player entered Destroyed state. Dropping all further calls to the player");
            this.this$0.isPlaying.set(false);
            this.this$0.terminationLatch.await();
            this.this$0.reportPlaybackExit();
            this.this$0.networkConnectionManager.unregisterListener(this.this$0.connectivityChangeListener);
            for (Set set : this.this$0.eventListenerSetSet) {
                synchronized (set) {
                    set.clear();
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX INFO: compiled from: PlayerImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerImpl$ErrorState;", "Lcom/amazon/video/sdk/player/PlayerImpl$PlayerFeatureState;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "(Lcom/amazon/video/sdk/player/PlayerImpl;Lcom/amazon/video/sdk/player/PlayerImpl;)V", "enter", "", "trigger", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ErrorState extends PlayerFeatureState {
        final /* synthetic */ PlayerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ErrorState(PlayerImpl this$0, PlayerImpl player) {
            super(player, PlayerFeatureState.Type.ERROR);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(player, "player");
            this.this$0 = this$0;
        }

        @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
        public void enter(Trigger<Triggers.Type> trigger) {
            this.this$0.isPlaying.set(false);
        }
    }
}
