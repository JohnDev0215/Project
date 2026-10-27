package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.avod.media.TimeSpan;
import com.amazon.dmvideodeliveryservice.CustomerInfo;
import com.amazon.dmvideodeliveryservice.DeviceToken;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequest;
import com.amazon.dmvideodeliveryservice.GetPlaybackTokenResponse;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.NetworkUnavailableException;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.config.ClientIdentification;
import com.amazon.music.media.playback.config.NetworkProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.config.VideoStreamingSettingsProvider;
import com.amazon.music.media.playback.metrics.MetricsEvent;
import com.amazon.music.media.playback.metrics.MetricsInfo;
import com.amazon.music.media.playback.metrics.PlaybackEventType;
import com.amazon.music.media.playback.metrics.mts.DefaultMTSEventFactory;
import com.amazon.music.media.playback.metrics.mts.MTSEventFactory;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.player.AbstractPlayer;
import com.amazon.music.media.playback.player.NoOpPrimeVideoFeaturePlayer;
import com.amazon.music.media.playback.player.PlayRequest;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.player.PositionTracker;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.NetworkInfoUtil;
import com.amazon.music.media.playback.util.PlayerUtil;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.media.playback.video.VideoDeliveryProvider;
import com.amazon.music.media.playback.video.VideoDeliveryService;
import com.amazon.music.media.playback.video.VideoPlaybackException;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.video.sdk.FailureErrorCode;
import com.amazon.video.sdk.ObservableFutureCallback;
import com.amazon.video.sdk.PlayerLoader;
import com.amazon.video.sdk.PlayerSdk;
import com.amazon.video.sdk.PlayerSdkConfigData;
import com.amazon.video.sdk.content.CacheConfigData;
import com.amazon.video.sdk.content.CacheContentConfigData;
import com.amazon.video.sdk.content.CachePriority;
import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.player.AdditionalPlayerConfigs;
import com.amazon.video.sdk.player.ContentConfigData;
import com.amazon.video.sdk.player.ContentState;
import com.amazon.video.sdk.player.EventListener;
import com.amazon.video.sdk.player.FeatureConfigs;
import com.amazon.video.sdk.player.PlaybackState;
import com.amazon.video.sdk.player.Player;
import com.amazon.video.sdk.player.PlayerConfigData;
import com.amazon.video.sdk.player.PlayerEvent;
import com.amazon.video.sdk.player.RenderingConfig;
import com.amazon.video.sdk.player.SurfaceHandlingMode;
import com.amazon.video.sdk.player.error.ErrorDomain;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureConfig;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetEvent;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetEventListener;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureConfig;
import com.amazon.video.sdk.stream.StreamFeatureConfig;
import com.amazon.video.sdk.stream.TimedTextCharacterEdgeType;
import com.amazon.video.sdk.stream.TimedTextColor;
import com.amazon.video.sdk.stream.TimedTextConfigData;
import com.amazon.video.sdk.stream.TimedTextFontSize;
import com.amazon.video.sdk.stream.TimedTextFontStyle;
import com.amazon.video.sdk.stream.TimedTextOpacity;
import com.amazon.video.sdk.stream.TimedTextStream;
import com.amazon.video.sdk.stream.TimedTextStreamGroup;
import com.amazon.video.sdk.stream.TimedTextTransitionMode;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/* JADX INFO: compiled from: PrimeVideoPlayer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000ø\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 þ\u00012\u00020\u0001:\u0004þ\u0001ÿ\u0001B1\u0012\b\u0010\u008e\u0001\u001a\u00030\u008d\u0001\u0012\b\u0010û\u0001\u001a\u00030ú\u0001\u0012\b\u0010\u0093\u0001\u001a\u00030\u0092\u0001\u0012\b\u0010\u0096\u0001\u001a\u00030\u0095\u0001¢\u0006\u0006\bü\u0001\u0010ý\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\b\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\t\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0010H\u0002J$\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0002J\b\u0010\u001e\u001a\u00020\u0002H\u0002J\u0016\u0010\"\u001a\u00020\u00022\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J\"\u0010'\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%H\u0002J\b\u0010(\u001a\u00020\u0002H\u0002J\b\u0010)\u001a\u00020\u0002H\u0002J\b\u0010*\u001a\u00020\u0012H\u0002J\u0010\u0010-\u001a\u00020\u00122\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010/\u001a\u00020\u00122\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u00100\u001a\u00020\u00122\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u00101\u001a\u00020\u00122\u0006\u0010,\u001a\u00020+H\u0002J\"\u00105\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u00122\b\u00104\u001a\u0004\u0018\u00010%H\u0002J\u0010\u00107\u001a\u00020\u00022\u0006\u00106\u001a\u00020#H\u0002J\u0018\u0010:\u001a\u00020\u00022\u0006\u00108\u001a\u00020\r2\u0006\u00109\u001a\u00020\rH\u0002J\u0012\u0010;\u001a\u00020\u00022\b\u00104\u001a\u0004\u0018\u00010%H\u0002J\u0012\u0010<\u001a\u00020\u00022\b\u00104\u001a\u0004\u0018\u00010%H\u0002J\b\u0010=\u001a\u00020\u0002H\u0002J\u0012\u0010>\u001a\u00020\u00022\b\u00104\u001a\u0004\u0018\u00010%H\u0002J4\u0010A\u001a\u00020\u00022\b\u00104\u001a\u0004\u0018\u00010%2\u0006\u0010?\u001a\u00020\r2\u0006\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\u00122\b\u0010@\u001a\u0004\u0018\u00010#H\u0002J\b\u0010B\u001a\u00020\u0002H\u0002J\b\u0010C\u001a\u00020\u0002H\u0002J\b\u0010E\u001a\u00020DH\u0002J$\u0010I\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00122\b\u00104\u001a\u0004\u0018\u00010%2\b\u0010H\u001a\u0004\u0018\u00010GH\u0016J\b\u0010J\u001a\u00020\u0002H\u0014J\b\u0010K\u001a\u00020\u0002H\u0014JD\u0010R\u001a\u00020\u00022\u0006\u0010M\u001a\u00020L2\u0006\u0010N\u001a\u00020L2\u0006\u0010O\u001a\u00020L2\u0006\u0010P\u001a\u00020L2\u0006\u0010Q\u001a\u00020L2\b\u00104\u001a\u0004\u0018\u00010%2\b\u0010H\u001a\u0004\u0018\u00010GH\u0014J\u001a\u0010T\u001a\u00020\u00022\b\u0010S\u001a\u0004\u0018\u00010\u00062\b\u00104\u001a\u0004\u0018\u00010%J$\u0010U\u001a\u00020\u00022\b\u00104\u001a\u0004\u0018\u00010%2\b\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010\u0013\u001a\u00020\u0012H\u0014J\u001c\u0010V\u001a\u00020\u00022\b\u00104\u001a\u0004\u0018\u00010%2\b\u0010H\u001a\u0004\u0018\u00010GH\u0016J$\u0010X\u001a\u00020\u00022\u0006\u0010W\u001a\u00020\r2\b\u00104\u001a\u0004\u0018\u00010%2\b\u0010H\u001a\u0004\u0018\u00010GH\u0014J2\u0010[\u001a\u00020\u00022\u0006\u0010Y\u001a\u00020L2\u0006\u0010Z\u001a\u00020\r2\u0006\u00104\u001a\u00020%2\b\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u000e\u0010\\\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u0012J\u000e\u0010_\u001a\u00020\u00022\u0006\u0010^\u001a\u00020]J\u0010\u0010b\u001a\u00020\u00022\u0006\u0010a\u001a\u00020`H\u0014J\u0012\u0010e\u001a\u00020\r2\b\u0010d\u001a\u0004\u0018\u00010cH\u0016J.\u0010f\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u00104\u001a\u0004\u0018\u00010%2\b\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010\u0013\u001a\u00020\u0012H\u0014J\u0006\u0010h\u001a\u00020gJ\u0006\u0010j\u001a\u00020iJ\u001a\u0010l\u001a\u00020\u00022\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020kJ\u001a\u0010m\u001a\u00020\u00022\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020kJ\u0018\u0010p\u001a\u00020\u00022\u0006\u0010o\u001a\u00020n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u000e\u0010r\u001a\u00020\u00022\u0006\u0010q\u001a\u00020\rJ\u000e\u0010t\u001a\u00020\u00022\u0006\u0010s\u001a\u00020\rJ\u000e\u0010v\u001a\u00020\u00022\u0006\u0010u\u001a\u00020\rJ\u000e\u0010x\u001a\u00020\u00022\u0006\u0010w\u001a\u00020\rJ\f\u0010y\u001a\b\u0012\u0004\u0012\u00020\r0\u0010J\u000e\u0010{\u001a\u00020\u00022\u0006\u0010z\u001a\u00020\rJ\u0006\u0010|\u001a\u00020\rJ\u0006\u0010}\u001a\u00020\rJ\u0006\u0010~\u001a\u00020\u0002J\u0006\u0010\u007f\u001a\u00020\rJ\u0015\u0010\u0080\u0001\u001a\u00020\u00022\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001fJ\u001c\u0010\u0085\u0001\u001a\u00020\u00022\b\u0010\u0082\u0001\u001a\u00030\u0081\u0001H\u0000¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u0012\u0010\u0088\u0001\u001a\u00020\u0002H\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0012\u0010\u008a\u0001\u001a\u00020\u0002H\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u0087\u0001J\u0017\u0010'\u001a\u00020\u00022\u0007\u0010$\u001a\u00030\u008b\u00012\u0006\u0010&\u001a\u00020%J\u0019\u0010\u008c\u0001\u001a\u00020\u00022\u0006\u00104\u001a\u00020%2\b\u0010H\u001a\u0004\u0018\u00010GR\u001d\u0010\u008e\u0001\u001a\u00030\u008d\u00018\u0006¢\u0006\u0010\n\u0006\b\u008e\u0001\u0010\u008f\u0001\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0018\u0010\u0096\u0001\u001a\u00030\u0095\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R*\u0010\u0099\u0001\u001a\u00030\u0098\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0099\u0001\u0010\u009a\u0001\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001\"\u0006\b\u009d\u0001\u0010\u009e\u0001R)\u0010\u009f\u0001\u001a\u00020n8\u0000@\u0000X\u0080.¢\u0006\u0018\n\u0006\b\u009f\u0001\u0010 \u0001\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R<\u0010¦\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020k0¥\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b¦\u0001\u0010§\u0001\u001a\u0006\b¨\u0001\u0010©\u0001\"\u0006\bª\u0001\u0010«\u0001R*\u0010\u00ad\u0001\u001a\u00030¬\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b\u00ad\u0001\u0010®\u0001\u001a\u0006\b¯\u0001\u0010°\u0001\"\u0006\b±\u0001\u0010²\u0001R\u0017\u0010q\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bq\u0010³\u0001R\u0019\u0010´\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0001\u0010³\u0001R\u001c\u0010¶\u0001\u001a\u0005\u0018\u00010µ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0001\u0010·\u0001R\u0018\u0010¹\u0001\u001a\u00030¸\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¹\u0001\u0010º\u0001R\u001a\u0010¼\u0001\u001a\u00030»\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0001\u0010½\u0001R+\u0010¾\u0001\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b¾\u0001\u0010¿\u0001\u001a\u0006\bÀ\u0001\u0010Á\u0001\"\u0006\bÂ\u0001\u0010Ã\u0001R\u001c\u0010Å\u0001\u001a\u0005\u0018\u00010Ä\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0001\u0010Æ\u0001R\u001e\u0010È\u0001\u001a\t\u0012\u0004\u0012\u00020\r0Ç\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÈ\u0001\u0010É\u0001R\u001e\u0010Ê\u0001\u001a\t\u0012\u0004\u0012\u00020\r0Ç\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÊ\u0001\u0010É\u0001R\u001c\u0010Ë\u0001\u001a\u0005\u0018\u00010Ä\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bË\u0001\u0010Æ\u0001R\u0019\u0010^\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b^\u0010Ì\u0001R\u0018\u0010Î\u0001\u001a\u00030Í\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÎ\u0001\u0010Ï\u0001R\u0019\u0010Ð\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÐ\u0001\u0010³\u0001R\u0019\u0010Ñ\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÑ\u0001\u0010Ò\u0001R\u0019\u0010Ó\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÓ\u0001\u0010Ò\u0001R\u0019\u0010Ô\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010Ò\u0001R\u0019\u0010Õ\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÕ\u0001\u0010Ò\u0001R\u001b\u0010Ö\u0001\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÖ\u0001\u0010×\u0001R+\u0010Ø\u0001\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bØ\u0001\u0010×\u0001\u001a\u0006\bÙ\u0001\u0010Ú\u0001\"\u0006\bÛ\u0001\u0010Ü\u0001R'\u0010ß\u0001\u001a\n\u0012\u0005\u0012\u00030Þ\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bß\u0001\u0010à\u0001\u001a\u0006\bá\u0001\u0010â\u0001R'\u0010ã\u0001\u001a\n\u0012\u0005\u0012\u00030\u0081\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bã\u0001\u0010à\u0001\u001a\u0006\bä\u0001\u0010â\u0001R'\u0010æ\u0001\u001a\n\u0012\u0005\u0012\u00030å\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bæ\u0001\u0010à\u0001\u001a\u0006\bç\u0001\u0010â\u0001R'\u0010é\u0001\u001a\n\u0012\u0005\u0012\u00030è\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bé\u0001\u0010à\u0001\u001a\u0006\bê\u0001\u0010â\u0001R'\u0010ì\u0001\u001a\n\u0012\u0005\u0012\u00030ë\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bì\u0001\u0010à\u0001\u001a\u0006\bí\u0001\u0010â\u0001R'\u0010ï\u0001\u001a\n\u0012\u0005\u0012\u00030î\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bï\u0001\u0010à\u0001\u001a\u0006\bð\u0001\u0010â\u0001R'\u0010ó\u0001\u001a\n\u0012\u0005\u0012\u00030ò\u00010ñ\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bó\u0001\u0010ô\u0001\u001a\u0006\bõ\u0001\u0010ö\u0001R'\u0010ø\u0001\u001a\n\u0012\u0005\u0012\u00030÷\u00010Ý\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\bø\u0001\u0010à\u0001\u001a\u0006\bù\u0001\u0010â\u0001¨\u0006\u0080\u0002"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "Lcom/amazon/music/media/playback/player/AbstractPlayer;", "", "setDefaultPVPlayerConfig", "Lcom/amazon/video/sdk/player/FeatureConfigs;", "getFeatureConfigs", "Lcom/amazon/music/media/playback/player/PlayRequest;", "request", "handleSkipWhileInitialBufferingMetrics", "sendPlayBackInitiatedMetrics", "Lcom/amazon/video/sdk/player/RenderingConfig;", "renderingConfig", "setPVParentViewUpdate", "", "canSeek", "toggleCanSeek", "Lrx/Observable;", "getPlayerConfigObservable", "", "requestTime", "Lcom/amazon/music/media/playback/PlaybackException$PlaybackError;", "internalPlaybackError", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "videoPlaybackError", "setStreamingError", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;", "fetchPlaybackToken", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenRequest;", "createPlaybackTokenRequest", "cacheNextVideo", "handlePlaybackErrors", "", "", "pidList", "cachePIDs", "Lcom/amazon/music/media/playback/PlaybackException;", "error", "Lcom/amazon/music/media/playback/ChangeReason;", "reason", "setError", "onPlaybackStarted", "setPlayRequestStatusOnPlaybackResumedAfterSeek", "computeDurationOffset", "Lcom/amazon/music/media/playback/metrics/MetricsInfo;", "info", "getResumedPlaybackDelay", "getInitialPlaybackDelay", "getTimeMediaItemKnown", "getTimePlaybackPlaying", "getTimePlaybackExpected", "prevSeekPosition", "currentSeekPosition", "changeReason", "sendTrackScrubMetrics", "playbackException", "sendStreamingErrorMetrics", "previousValue", "newValue", "sendCaptionsToggleMetrics", "sendStartPlaybackMetrics", "sendPlaybackCompletedMetrics", "resetMetricsDuration", "sendPlaybackStreamedMetrics", "isSeeking", "exception", "sendPlaybackTerminatedMetrics", "setPlaybackInitiated", "setMTSNetworkProvider", "Lcom/amazon/music/media/playback/config/NetworkProvider;", "buildMTSNetworkProvider", "positionMillis", "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "seek", "onConnect", "onDisconnect", "", "oldIndex", "newIndex", "startChangesIndex", "numOldItems", "numNewItems", "onQueueChanged", "oldRequest", "handleToggleMetrics", "doPlay", "startPlayback", "willResumeImmediately", "doPause", "startIndex", "shouldPlay", "setIndex", "trackPlayAssetForPosition", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer$TrickPlayListener;", "trickPlayListener", "setTrickPlayListener", "", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "onCurrentVolumeChanged", "Landroid/net/Uri;", "uri", "canPlay", "terminate", "Lcom/amazon/video/sdk/player/ContentState;", "getContentState", "Lcom/amazon/video/sdk/player/PlaybackState;", "getPlaybackState", "Lkotlin/Function1;", "addOnPlaybackStartedListener", "removeOnPlaybackStartedListener", "Lcom/amazon/video/sdk/player/PlayerConfigData;", "playerConfig", "setPlayerConfig", "isForeground", "setPlayerVisibility", "unsubscribePlaybackObservers", "destroy", "value", "showCaptions", "isSet", "markPlayerConfigSet", "getPlayerReadyObservable", "isReady", "markPlayerReady", "hasCaptions", "isShowingCaptions", "onConnectivityLost", "isValidPlaybackToken", "cacheVideos", "Lcom/amazon/video/sdk/player/PlayerEvent$ContentStateChange;", "newContentStateChange", "updatePlayerReadySubject$DMMPlayer_release", "(Lcom/amazon/video/sdk/player/PlayerEvent$ContentStateChange;)V", "updatePlayerReadySubject", "registerPlayerListeners$DMMPlayer_release", "()V", "registerPlayerListeners", "unregisterPlayerListeners$DMMPlayer_release", "unregisterPlayerListeners", "Lcom/amazon/music/media/playback/concurrency/ConcurrencyException;", "setPlayRequestStatusOnPlaybackStarted", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/media/playback/config/VideoStreamingSettingsProvider;", "streamingProvider", "Lcom/amazon/music/media/playback/config/VideoStreamingSettingsProvider;", "Lcom/amazon/music/media/playback/video/VideoDeliveryService;", "videoDeliveryService", "Lcom/amazon/music/media/playback/video/VideoDeliveryService;", "Lcom/amazon/video/sdk/PlayerSdk;", "pvPlayerSdk", "Lcom/amazon/video/sdk/PlayerSdk;", "getPvPlayerSdk", "()Lcom/amazon/video/sdk/PlayerSdk;", "setPvPlayerSdk", "(Lcom/amazon/video/sdk/PlayerSdk;)V", "playerConfigData", "Lcom/amazon/video/sdk/player/PlayerConfigData;", "getPlayerConfigData$DMMPlayer_release", "()Lcom/amazon/video/sdk/player/PlayerConfigData;", "setPlayerConfigData$DMMPlayer_release", "(Lcom/amazon/video/sdk/player/PlayerConfigData;)V", "", "onPlaybackStartedListeners", "Ljava/util/List;", "getOnPlaybackStartedListeners$DMMPlayer_release", "()Ljava/util/List;", "setOnPlaybackStartedListeners$DMMPlayer_release", "(Ljava/util/List;)V", "Lcom/amazon/video/sdk/player/Player;", "playerFeature", "Lcom/amazon/video/sdk/player/Player;", "getPlayerFeature$DMMPlayer_release", "()Lcom/amazon/video/sdk/player/Player;", "setPlayerFeature$DMMPlayer_release", "(Lcom/amazon/video/sdk/player/Player;)V", "Z", "isPlaybackInitiated", "Lcom/amazon/video/sdk/player/ContentConfigData;", "contentConfig", "Lcom/amazon/video/sdk/player/ContentConfigData;", "Ljava/util/concurrent/ExecutorService;", "executorService", "Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "timelineEnded", "Ljava/util/concurrent/atomic/AtomicBoolean;", "playbackTokenResponse", "Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;", "getPlaybackTokenResponse", "()Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;", "setPlaybackTokenResponse", "(Lcom/amazon/dmvideodeliveryservice/GetPlaybackTokenResponse;)V", "Lrx/Subscription;", "configSetSubscription", "Lrx/Subscription;", "Lrx/subjects/BehaviorSubject;", "configSetSubject", "Lrx/subjects/BehaviorSubject;", "playerReadySubject", "playerReadySubscription", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer$TrickPlayListener;", "Lcom/amazon/video/sdk/stream/TimedTextConfigData;", "timedTextConfigData", "Lcom/amazon/video/sdk/stream/TimedTextConfigData;", "wasSeeking", "lastSeekPosition", "J", "totalDurationPlayedSeconds", "durationOffsetSeconds", "durationSeconds", "videoRenderingConfig", "Lcom/amazon/video/sdk/player/RenderingConfig;", "startRenderingConfig", "getStartRenderingConfig", "()Lcom/amazon/video/sdk/player/RenderingConfig;", "setStartRenderingConfig", "(Lcom/amazon/video/sdk/player/RenderingConfig;)V", "Lcom/amazon/video/sdk/player/EventListener;", "Lcom/amazon/video/sdk/player/PlayerEvent$PlaybackStateChange;", "playbackStateChangeListener", "Lcom/amazon/video/sdk/player/EventListener;", "getPlaybackStateChangeListener$DMMPlayer_release", "()Lcom/amazon/video/sdk/player/EventListener;", "contentStateChangeListener", "getContentStateChangeListener$DMMPlayer_release", "Lcom/amazon/video/sdk/player/PlayerEvent$TimeDataChange;", "timeDataChangeListener", "getTimeDataChangeListener$DMMPlayer_release", "Lcom/amazon/video/sdk/player/PlayerEvent$PlayerError;", "playerErrorListener", "getPlayerErrorListener$DMMPlayer_release", "Lcom/amazon/video/sdk/player/PlayerEvent$ContentError;", "contentErrorListener", "getContentErrorListener$DMMPlayer_release", "Lcom/amazon/video/sdk/player/PlayerEvent$TimelineChange;", "timelineChangeListener", "getTimelineChangeListener$DMMPlayer_release", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEventListener;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent$VideoPreviewChange;", "videoPreviewEventListener", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEventListener;", "getVideoPreviewEventListener$DMMPlayer_release", "()Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEventListener;", "Lcom/amazon/video/sdk/player/PlayerEvent$TimelineEnded;", "timelineEndedListener", "getTimelineEndedListener$DMMPlayer_release", "Lcom/amazon/video/sdk/PlayerSdkConfigData;", "playerSdkConfig", "<init>", "(Landroid/content/Context;Lcom/amazon/video/sdk/PlayerSdkConfigData;Lcom/amazon/music/media/playback/config/VideoStreamingSettingsProvider;Lcom/amazon/music/media/playback/video/VideoDeliveryService;)V", "Companion", "TrickPlayListener", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
public class PrimeVideoPlayer extends AbstractPlayer {
    private static volatile boolean isFetchingPlaybackToken;
    private final BehaviorSubject<Boolean> configSetSubject;
    private Subscription configSetSubscription;
    private ContentConfigData contentConfig;
    private final EventListener<PlayerEvent.ContentError> contentErrorListener;
    private final EventListener<PlayerEvent.ContentStateChange> contentStateChangeListener;
    private final Context context;
    private long durationOffsetSeconds;
    private long durationSeconds;
    private final ExecutorService executorService;
    private volatile boolean isForeground;
    private boolean isPlaybackInitiated;
    private long lastSeekPosition;
    private List<Function1<Unit, Unit>> onPlaybackStartedListeners;
    private final EventListener<PlayerEvent.PlaybackStateChange> playbackStateChangeListener;
    private volatile transient GetPlaybackTokenResponse playbackTokenResponse;
    public PlayerConfigData playerConfigData;
    private final EventListener<PlayerEvent.PlayerError> playerErrorListener;
    private Player playerFeature;
    private final BehaviorSubject<Boolean> playerReadySubject;
    private Subscription playerReadySubscription;
    private PlayerSdk pvPlayerSdk;
    private RenderingConfig startRenderingConfig;
    private final VideoStreamingSettingsProvider streamingProvider;
    private final EventListener<PlayerEvent.TimeDataChange> timeDataChangeListener;
    private final TimedTextConfigData timedTextConfigData;
    private final EventListener<PlayerEvent.TimelineChange> timelineChangeListener;
    private AtomicBoolean timelineEnded;
    private final EventListener<PlayerEvent.TimelineEnded> timelineEndedListener;
    private long totalDurationPlayedSeconds;
    private TrickPlayListener trickPlayListener;
    private final VideoDeliveryService videoDeliveryService;
    private final VideoPreviewAssetEventListener<VideoPreviewAssetEvent.VideoPreviewChange> videoPreviewEventListener;
    private RenderingConfig videoRenderingConfig;
    private boolean wasSeeking;

    /* JADX INFO: compiled from: PrimeVideoPlayer.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer$TrickPlayListener;", "", "didReceiveTrickPlayImage", "", ContextMappingConstants.IMAGE, "Landroid/graphics/drawable/Drawable;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface TrickPlayListener {
        void didReceiveTrickPlayImage(Drawable image);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canPlay(Uri uri) {
        return true;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onCurrentVolumeChanged(float volume) {
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onQueueChanged(int oldIndex, int newIndex, int startChangesIndex, int numOldItems, int numNewItems, ChangeReason changeReason, ControlSource controlSource) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimeVideoPlayer(Context context, PlayerSdkConfigData playerSdkConfig, VideoStreamingSettingsProvider streamingProvider, VideoDeliveryService videoDeliveryService) {
        super(context, "PV_PLAYER", null, true, true, false);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playerSdkConfig, "playerSdkConfig");
        Intrinsics.checkNotNullParameter(streamingProvider, "streamingProvider");
        Intrinsics.checkNotNullParameter(videoDeliveryService, "videoDeliveryService");
        this.context = context;
        this.streamingProvider = streamingProvider;
        this.videoDeliveryService = videoDeliveryService;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        this.pvPlayerSdk = new NoOpPrimePlayerSdk(applicationContext);
        this.onPlaybackStartedListeners = new ArrayList();
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.executorService = executorServiceNewSingleThreadExecutor;
        this.timelineEnded = new AtomicBoolean(false);
        BehaviorSubject<Boolean> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create()");
        this.configSetSubject = behaviorSubjectCreate;
        BehaviorSubject<Boolean> behaviorSubjectCreate2 = BehaviorSubject.create(Boolean.TRUE);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate2, "create(true)");
        this.playerReadySubject = behaviorSubjectCreate2;
        TimedTextColor timedTextColor = TimedTextColor.WHITE;
        TimedTextOpacity timedTextOpacity = TimedTextOpacity.NORMAL;
        TimedTextFontSize timedTextFontSize = TimedTextFontSize.SMALL;
        TimedTextCharacterEdgeType timedTextCharacterEdgeType = TimedTextCharacterEdgeType.NONE;
        TimedTextFontStyle timedTextFontStyle = TimedTextFontStyle.DEFAULT;
        TimedTextColor timedTextColor2 = TimedTextColor.BLACK;
        this.timedTextConfigData = new TimedTextConfigData(timedTextColor, timedTextOpacity, timedTextFontSize, timedTextCharacterEdgeType, timedTextFontStyle, timedTextColor2, timedTextColor2, TimedTextTransitionMode.NONE);
        PlayerLoader.INSTANCE.getSdk(playerSdkConfig).addListener(new ObservableFutureCallback<PlayerSdk>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer.1
            @Override // com.amazon.video.sdk.ObservableFutureCallback
            public void onSuccess(@Nullable PlayerSdk playerSdk) {
                Intrinsics.checkNotNullParameter(playerSdk, "playerSdk");
                PrimeVideoPlayer.this.setPvPlayerSdk(playerSdk);
            }

            @Override // com.amazon.video.sdk.ObservableFutureCallback
            public void onFailure(Throwable t, FailureErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                ((AbstractPlayer) PrimeVideoPlayer.this).logger.error("FATAL Exception creating PlayerSdk " + t + " with failureErrorCode " + errorCode);
                PrimeVideoPlayer.setStreamingError$default(PrimeVideoPlayer.this, Clock.now(), PlaybackException.PlaybackError.VideoPlayerInit, null, 4, null);
            }
        }, executorServiceNewSingleThreadExecutor);
        this.playerFeature = new NoOpPrimeVideoFeaturePlayer();
        this.playbackStateChangeListener = new EventListener<PlayerEvent.PlaybackStateChange>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$playbackStateChangeListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.PlaybackStateChange t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ((AbstractPlayer) this.this$0).logger.debug(Intrinsics.stringPlus("PlaybackStateChange ", t));
                if (t.getPlaybackState() == PlaybackState.Playing) {
                    if (this.this$0.getPlayStatus() != PlayStatus.RENDERING) {
                        this.this$0.onPlaybackStarted();
                    }
                    this.this$0.cacheNextVideo();
                } else {
                    PlayStatus playStatus = this.this$0.getPlayStatus();
                    PlayStatus playStatus2 = PlayStatus.USER_PAUSED;
                    if (playStatus != playStatus2 && this.this$0.getPlayStatus() != PlayStatus.SYSTEM_PAUSED) {
                        this.this$0.setPlayStatus(playStatus2, null, ChangeReason.PLAY_PAUSE, ControlSource.NONE, Clock.now());
                    }
                    if (this.this$0.streamingProvider.isStreamingRestricted()) {
                        PrimeVideoPlayer.setStreamingError$default(this.this$0, Clock.now(), PlaybackException.PlaybackError.NetworkFailure, null, 4, null);
                    }
                }
                PlayerUtil.INSTANCE.updateStreamingStatus(this.this$0.getPlaybackConfig(), t.getPlaybackState(), "", false);
            }
        };
        this.contentStateChangeListener = new EventListener<PlayerEvent.ContentStateChange>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$contentStateChangeListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.ContentStateChange t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ((AbstractPlayer) this.this$0).logger.debug(Intrinsics.stringPlus("ContentStateChange ", t));
                ((AbstractPlayer) this.this$0).logger.debug("AMDB 2) contentStateChange " + t + " ; threadId: " + Thread.currentThread().getId());
                this.this$0.updatePlayerReadySubject$DMMPlayer_release(t);
                if (t.getContentState() == ContentState.Ready && this.this$0.getPlayerFeature().getPlaybackState() == PlaybackState.Playing && this.this$0.getPlayStatus() != PlayStatus.RENDERING) {
                    this.this$0.onPlaybackStarted();
                    return;
                }
                if (t.getContentState() == ContentState.Unloaded) {
                    if (this.this$0.isLoopMediaItem()) {
                        this.this$0.startPlayback(ChangeReason.PLAY_PAUSE, ControlSource.NONE);
                        return;
                    }
                    PrimeVideoPlayer primeVideoPlayer = this.this$0;
                    ChangeReason changeReason = ChangeReason.MEDIA_ITEM_END;
                    primeVideoPlayer.updatePlayStatus(changeReason, null);
                    if (this.this$0.timelineEnded.compareAndSet(true, false)) {
                        PrimeVideoPlayer primeVideoPlayer2 = this.this$0;
                        primeVideoPlayer2.setCurrentIndex(primeVideoPlayer2.getCurrentIndex() + 1, null, changeReason, Clock.now());
                        return;
                    }
                    return;
                }
                if (t.getContentState() == ContentState.Waiting) {
                    if (this.this$0.getPlayStatus() != PlayStatus.USER_PAUSED) {
                        this.this$0.setPlayStatus(PlayStatus.LOADING, null, ChangeReason.MEDIA_ITEM_LOADED, ControlSource.NONE, Clock.now());
                        return;
                    }
                    return;
                }
                if (t.getContentState() == ContentState.Error) {
                    PlaybackError contentError = this.this$0.getPlayerFeature().getContentError();
                    Logger logger = ((AbstractPlayer) this.this$0).logger;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Video Player is in an error state. ErrorCode: ");
                    sb.append(contentError == null ? null : Integer.valueOf(contentError.getErrorCode()));
                    sb.append("  ErrorDescription: ");
                    sb.append(contentError == null ? null : Integer.valueOf(contentError.getErrorDescription()));
                    sb.append("  ErrorDomain: ");
                    sb.append(contentError == null ? null : contentError.getErrorDomain());
                    sb.append("  ErrorMessage: ");
                    sb.append(contentError != null ? Integer.valueOf(contentError.getErrorMessage()) : null);
                    logger.error(sb.toString());
                    this.this$0.updatePlayStatus(ChangeReason.ERROR, ControlSource.NONE);
                }
            }
        };
        this.timeDataChangeListener = new EventListener<PlayerEvent.TimeDataChange>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$timeDataChangeListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.TimeDataChange timeDataChange) {
                PlayRequest currentPlayRequest;
                PositionTracker positionTracker;
                Intrinsics.checkNotNullParameter(timeDataChange, "timeDataChange");
                if (this.this$0.getCurrentPlayRequest().getPlayRequestStatus() != PlayRequestStatus.TERMINATED && this.this$0.getCurrentPlayRequest().getPlayRequestStatus() != PlayRequestStatus.NOT_STARTED && this.this$0.getPlayerFeature().getPlaybackState() == PlaybackState.Playing && timeDataChange.getTimeData().getCurrentPosition() != 0 && (currentPlayRequest = this.this$0.getCurrentPlayRequest()) != null && (positionTracker = currentPlayRequest.getPositionTracker()) != null) {
                    positionTracker.setPosition(timeDataChange.getTimeData().getCurrentPosition());
                }
                if (!this.this$0.wasSeeking || timeDataChange.getTimeData().getCurrentPosition() == this.this$0.lastSeekPosition) {
                    return;
                }
                this.this$0.lastSeekPosition = 0L;
                this.this$0.setPlayRequestStatusOnPlaybackResumedAfterSeek();
            }
        };
        this.playerErrorListener = new EventListener<PlayerEvent.PlayerError>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$playerErrorListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.PlayerError t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ((AbstractPlayer) this.this$0).logger.debug(Intrinsics.stringPlus("PlayerError ", t.getPlaybackError()));
                if (t.getPlaybackError().getErrorDomain() == ErrorDomain.Network) {
                    this.this$0.setStreamingError(Clock.now(), PlaybackException.PlaybackError.NetworkFailure, t.getPlaybackError());
                    if (this.this$0.getPlayerFeature().getPlaybackState() == PlaybackState.Playing) {
                        ((AbstractPlayer) this.this$0).logger.error(Intrinsics.stringPlus("AMDB calling playerFeature.pause() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
                        this.this$0.getPlayerFeature().pause();
                        return;
                    }
                    return;
                }
                this.this$0.setStreamingError(Clock.now(), PlaybackException.PlaybackError.VideoPlayerGeneric, t.getPlaybackError());
                this.this$0.handlePlaybackErrors();
            }
        };
        this.contentErrorListener = new EventListener<PlayerEvent.ContentError>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$contentErrorListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.ContentError t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ((AbstractPlayer) this.this$0).logger.debug(Intrinsics.stringPlus("ContentError ", t.getPlaybackError()));
                this.this$0.setStreamingError(Clock.now(), PlaybackException.PlaybackError.VideoPlayerContent, t.getPlaybackError());
            }
        };
        this.timelineChangeListener = new EventListener<PlayerEvent.TimelineChange>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$timelineChangeListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.TimelineChange t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ((AbstractPlayer) this.this$0).logger.debug(Intrinsics.stringPlus("TimelineChange ", t));
            }
        };
        this.videoPreviewEventListener = new VideoPreviewAssetEventListener<VideoPreviewAssetEvent.VideoPreviewChange>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$videoPreviewEventListener$1
            @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetEventListener
            public void on(VideoPreviewAssetEvent.VideoPreviewChange videoPreviewAssetChange) {
                PrimeVideoPlayer.TrickPlayListener trickPlayListener;
                Intrinsics.checkNotNullParameter(videoPreviewAssetChange, "videoPreviewAssetChange");
                if (videoPreviewAssetChange.getVideoPreviewAsset() == null || videoPreviewAssetChange.getVideoPreviewAsset().getImage() == null || (trickPlayListener = this.this$0.trickPlayListener) == null) {
                    return;
                }
                trickPlayListener.didReceiveTrickPlayImage(videoPreviewAssetChange.getVideoPreviewAsset().getImage());
            }
        };
        this.timelineEndedListener = new EventListener<PlayerEvent.TimelineEnded>() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$timelineEndedListener$1
            @Override // com.amazon.video.sdk.player.EventListener
            public void on(PlayerEvent.TimelineEnded t) {
                Intrinsics.checkNotNullParameter(t, "t");
                ((AbstractPlayer) this.this$0).logger.debug(Intrinsics.stringPlus("TimelineEnded ", t));
                this.this$0.timelineEnded.set(true);
                if (this.this$0.isLoopMediaItem()) {
                    PrimeVideoPlayer primeVideoPlayer = this.this$0;
                    ChangeReason changeReason = ChangeReason.MEDIA_ITEM_END;
                    primeVideoPlayer.sendPlaybackCompletedMetrics(changeReason);
                    PrimeVideoPlayer primeVideoPlayer2 = this.this$0;
                    primeVideoPlayer2.setPlayRequestStatus(primeVideoPlayer2.getCurrentPlayRequest(), PlayRequestStatus.NOT_STARTED, 0L, Clock.now(), -1.0f, null, changeReason, ControlSource.NONE);
                    this.this$0.notifyRepeatingMediaItem();
                    return;
                }
                if (this.this$0.getPlayerFeature().getPlaybackState() == PlaybackState.Playing) {
                    this.this$0.sendPlaybackTerminatedMetrics(ChangeReason.MEDIA_ITEM_END, false, 0L, 0L, null);
                }
            }
        };
    }

    public final PlayerSdk getPvPlayerSdk() {
        return this.pvPlayerSdk;
    }

    public final void setPvPlayerSdk(PlayerSdk playerSdk) {
        Intrinsics.checkNotNullParameter(playerSdk, "<set-?>");
        this.pvPlayerSdk = playerSdk;
    }

    public final PlayerConfigData getPlayerConfigData$DMMPlayer_release() {
        PlayerConfigData playerConfigData = this.playerConfigData;
        if (playerConfigData != null) {
            return playerConfigData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playerConfigData");
        return null;
    }

    public final void setPlayerConfigData$DMMPlayer_release(PlayerConfigData playerConfigData) {
        Intrinsics.checkNotNullParameter(playerConfigData, "<set-?>");
        this.playerConfigData = playerConfigData;
    }

    /* JADX INFO: renamed from: getPlayerFeature$DMMPlayer_release, reason: from getter */
    public final Player getPlayerFeature() {
        return this.playerFeature;
    }

    public final GetPlaybackTokenResponse getPlaybackTokenResponse() {
        return this.playbackTokenResponse;
    }

    public final RenderingConfig getStartRenderingConfig() {
        return this.startRenderingConfig;
    }

    public final void setStartRenderingConfig(RenderingConfig renderingConfig) {
        this.startRenderingConfig = renderingConfig;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void seek(long positionMillis, ChangeReason changeReason, ControlSource controlSource) {
        PositionTracker positionTracker;
        try {
            PositionTracker positionTracker2 = getCurrentPlayRequest().getPositionTracker();
            long checkpointPosition = positionTracker2 == null ? 0L : positionTracker2.getCheckpointPosition();
            this.lastSeekPosition = positionMillis;
            setPlayRequestStatus(getCurrentPlayRequest(), getCurrentPlayRequest().getPlayRequestStatus().onSeek(), positionMillis, Clock.now(), -1.0f, null, changeReason, controlSource);
            PlayRequest currentPlayRequest = getCurrentPlayRequest();
            if (currentPlayRequest != null && (positionTracker = currentPlayRequest.getPositionTracker()) != null) {
                positionTracker.setPosition(positionMillis);
            }
            this.playerFeature.seek(positionMillis);
            this.wasSeeking = true;
            sendTrackScrubMetrics(checkpointPosition, positionMillis, changeReason);
            updatePlayStatus(changeReason, controlSource);
        } catch (Exception e) {
            this.logger.error(Intrinsics.stringPlus("Exception when calling playerFeature.seek(). ", e));
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConnect() {
        this.logger.info("Connected");
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onDisconnect() {
        this.logger.info("Disconnected");
        destroy(true);
    }

    public final void handleToggleMetrics(PlayRequest oldRequest, ChangeReason changeReason) {
        if (changeReason != ChangeReason.TOGGLE || oldRequest == null) {
            return;
        }
        oldRequest.getMetricsSender().sendTrackStreamed(oldRequest.getMetricsHandler().getMetricsInfo(), this.totalDurationPlayedSeconds, changeReason);
        resetMetricsDuration();
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPlay(final ChangeReason changeReason, final ControlSource controlSource, final long requestTime) {
        this.logger.info(Intrinsics.stringPlus("doPlay() - currentRequest: ", getCurrentPlayRequest()));
        if (getCurrentPlayRequest() == null) {
            setPlayStatus(PlayStatus.LOADING, null, changeReason, controlSource, requestTime);
            return;
        }
        if (!isValidPlaybackToken() && !isFetchingPlaybackToken) {
            isFetchingPlaybackToken = true;
            setPlayStatus(PlayStatus.LOADING, null, changeReason, controlSource, requestTime);
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PrimeVideoPlayer.m2172doPlay$lambda1(this.f$0, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PrimeVideoPlayer.m2173doPlay$lambda2(this.f$0, changeReason, controlSource, requestTime, (GetPlaybackTokenResponse) obj);
                }
            });
        } else if (this.streamingProvider.isStreamingRestricted() || !NetworkInfoUtil.hasAnyInternetConnection()) {
            setStreamingError$default(this, requestTime, PlaybackException.PlaybackError.NetworkFailure, null, 4, null);
        } else if (isFetchingPlaybackToken) {
            setPlayStatus(PlayStatus.LOADING, null, changeReason, controlSource, requestTime);
        } else {
            startPlayback(changeReason, controlSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: doPlay$lambda-1, reason: not valid java name */
    public static final void m2172doPlay$lambda1(PrimeVideoPlayer this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        subscriber.onNext(this$0.fetchPlaybackToken());
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: doPlay$lambda-2, reason: not valid java name */
    public static final void m2173doPlay$lambda2(PrimeVideoPlayer this$0, ChangeReason changeReason, ControlSource controlSource, long j, GetPlaybackTokenResponse getPlaybackTokenResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playbackTokenResponse = getPlaybackTokenResponse;
        isFetchingPlaybackToken = false;
        if (!this$0.getPlayStatus().shouldBePlaying() || this$0.getCurrentPlayRequest() == null) {
            return;
        }
        this$0.doPlay(changeReason, controlSource, j);
    }

    public synchronized void startPlayback(final ChangeReason changeReason, final ControlSource controlSource) {
        this.logger.debug("AMDB startPlayback() changeReason: " + changeReason + " controlSource: " + controlSource);
        this.wasSeeking = false;
        this.durationOffsetSeconds = computeDurationOffset();
        Subscription subscription = this.configSetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.configSetSubscription = getPlayerConfigObservable().subscribe(new Action1() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PrimeVideoPlayer.m2174startPlayback$lambda7(this.f$0, changeReason, controlSource, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a9  */
    /* JADX INFO: renamed from: startPlayback$lambda-7, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2174startPlayback$lambda7(final com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r24, com.amazon.music.media.playback.ChangeReason r25, com.amazon.music.media.playback.ControlSource r26, java.lang.Boolean r27) {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer.m2174startPlayback$lambda7(com.amazon.music.media.playback.player.impl.PrimeVideoPlayer, com.amazon.music.media.playback.ChangeReason, com.amazon.music.media.playback.ControlSource, java.lang.Boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlayback$lambda-7$lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m2175startPlayback$lambda7$lambda6$lambda5$lambda4(PrimeVideoPlayer this$0, RenderingConfig renderingConfig, ContentConfigData content, Boolean isPlayerReady) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(content, "$content");
        this$0.logger.debug("AMDB calling playerReadySubscription isPlayerReady = " + isPlayerReady + " ; threadId: " + Thread.currentThread().getId());
        Intrinsics.checkNotNullExpressionValue(isPlayerReady, "isPlayerReady");
        if (isPlayerReady.booleanValue()) {
            this$0.markPlayerReady(false);
            this$0.logger.debug(Intrinsics.stringPlus("AMDB 3) calling playerFeature.start() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
            if (!this$0.isForeground) {
                this$0.videoRenderingConfig = PlayerUtil.INSTANCE.createRenderingConfig(SurfaceHandlingMode.AUDIO_ONLY, null, null, renderingConfig != null ? renderingConfig.getSystemUiFlags() : -1, true);
            }
            RenderingConfig renderingConfig2 = this$0.videoRenderingConfig;
            this$0.startRenderingConfig = renderingConfig2;
            if (renderingConfig2 != null) {
                this$0.getPlayerFeature().start(content, renderingConfig2);
            }
            Subscription subscription = this$0.playerReadySubscription;
            if (subscription == null) {
                return;
            }
            subscription.unsubscribe();
        }
    }

    private final void setDefaultPVPlayerConfig() {
        setPlayerConfigData$DMMPlayer_release(new PlayerConfigData(this.context, getFeatureConfigs(), new AdditionalPlayerConfigs(true)));
        this.logger.debug(Intrinsics.stringPlus("AMDB setDefaultPVPlayerConfig SDK.getPlayerFeature() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
        this.playerFeature = this.pvPlayerSdk.getPlayerFeature(getPlayerConfigData$DMMPlayer_release());
        registerPlayerListeners$DMMPlayer_release();
        markPlayerConfigSet(true);
        markPlayerReady(true);
    }

    private final FeatureConfigs getFeatureConfigs() {
        return new FeatureConfigs() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer.getFeatureConfigs.1
            @Override // com.amazon.video.sdk.player.FeatureConfigs
            public PlaylistFeatureConfig getPlaylist() {
                return null;
            }

            @Override // com.amazon.video.sdk.player.FeatureConfigs
            public StreamFeatureConfig getStream() {
                return null;
            }

            @Override // com.amazon.video.sdk.player.FeatureConfigs
            public VideoPreviewAssetFeatureConfig getVideoPreviewAsset() {
                return new VideoPreviewAssetFeatureConfig() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$getFeatureConfigs$1$videoPreviewAsset$1
                    @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureConfig
                    public boolean getPreloadAssets() {
                        return true;
                    }
                };
            }
        };
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPause(boolean willResumeImmediately, ChangeReason changeReason, ControlSource controlSource) {
        this.wasSeeking = false;
        if (!willResumeImmediately) {
            this.logger.debug(Intrinsics.stringPlus("AMDB calling playerFeature.pause() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
            this.playerFeature.pause();
            Subscription subscription = this.playerReadySubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            Subscription subscription2 = this.configSetSubscription;
            if (subscription2 != null) {
                subscription2.unsubscribe();
            }
        }
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest != null) {
            sendPlaybackTerminatedMetrics(changeReason, false, 0L, 0L, null);
            if ((changeReason == ChangeReason.SWITCHING_PLAYERS || changeReason == ChangeReason.TOGGLE) && getPositionMillis() > 0) {
                sendPlaybackStreamedMetrics(changeReason);
            }
        }
        if (currentPlayRequest == null || !currentPlayRequest.getPlayRequestStatus().shouldBePlaying()) {
            return;
        }
        setPlayRequestStatus(currentPlayRequest, currentPlayRequest.getPlayRequestStatus().onPause(), getPositionMillis(), Clock.now(), -1.0f, null, changeReason, controlSource);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer, com.amazon.music.media.playback.player.Player
    public void setIndex(int startIndex, boolean shouldPlay, ChangeReason changeReason, ControlSource controlSource, long requestTime) {
        Intrinsics.checkNotNullParameter(changeReason, "changeReason");
        setCurrentIndex(startIndex, null, changeReason, requestTime);
        setPlayStatus(PlayStatus.USER_PAUSED, null, changeReason, controlSource, requestTime);
    }

    public final void trackPlayAssetForPosition(long positionMillis) {
        try {
            this.playerFeature.getVideoPreviewAssetFeature().requestVideoPreviewAsset(positionMillis);
        } catch (Exception e) {
            this.logger.error(Intrinsics.stringPlus("Exception when calling requestVideoPreviewAsset. ", e));
        }
    }

    public final void setTrickPlayListener(TrickPlayListener trickPlayListener) {
        Intrinsics.checkNotNullParameter(trickPlayListener, "trickPlayListener");
        this.trickPlayListener = trickPlayListener;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void terminate(PlayRequest request, ChangeReason changeReason, ControlSource controlSource, long requestTime) {
        handleSkipWhileInitialBufferingMetrics(request);
        super.terminate(request, changeReason, controlSource, requestTime);
        sendPlaybackCompletedMetrics(changeReason);
    }

    private final void handleSkipWhileInitialBufferingMetrics(PlayRequest request) {
        if (request != null && request.getPlayRequestStatus() == PlayRequestStatus.PREPARING) {
            sendPlayBackInitiatedMetrics(request);
            sendPlaybackTerminatedMetrics(ChangeReason.NEXT, false, 0L, 0L, null);
        }
    }

    private final void sendPlayBackInitiatedMetrics(PlayRequest request) {
        if (request == null) {
            return;
        }
        MTSEventFactory mTSEventFactory = getPlaybackConfig().getMTSEventFactory();
        MetricsInfo metricsInfo = request.getMetricsHandler().getMetricsInfo();
        Intrinsics.checkNotNullExpressionValue(metricsInfo, "metricsInfo");
        MTSEvent mTSEventCreateStreamingInitiatedEvent = mTSEventFactory.createStreamingInitiatedEvent(metricsInfo, null, Clock.now() - getTimeMediaItemKnown(metricsInfo), 0L, ChangeReason.NEXT);
        Intrinsics.checkNotNullExpressionValue(mTSEventCreateStreamingInitiatedEvent, "eventFactory.createStrea…ip, 0, ChangeReason.NEXT)");
        request.getMetricsSender().sendMetricsEvent(mTSEventCreateStreamingInitiatedEvent);
    }

    public final ContentState getContentState() {
        return this.playerFeature.getContentState();
    }

    public final PlaybackState getPlaybackState() {
        return this.playerFeature.getPlaybackState();
    }

    public final void addOnPlaybackStartedListener(Function1<? super Unit, Unit> onPlaybackStarted) {
        Intrinsics.checkNotNullParameter(onPlaybackStarted, "onPlaybackStarted");
        this.onPlaybackStartedListeners.add(onPlaybackStarted);
    }

    public final void removeOnPlaybackStartedListener(Function1<? super Unit, Unit> onPlaybackStarted) {
        Intrinsics.checkNotNullParameter(onPlaybackStarted, "onPlaybackStarted");
        this.onPlaybackStartedListeners.remove(onPlaybackStarted);
    }

    private final void setPVParentViewUpdate(RenderingConfig renderingConfig) {
        this.videoRenderingConfig = renderingConfig;
        if (renderingConfig == null) {
            return;
        }
        if (Intrinsics.areEqual(renderingConfig, getPlayerFeature().getRenderingConfig())) {
            RenderingConfig startRenderingConfig = getStartRenderingConfig();
            if ((startRenderingConfig == null ? null : startRenderingConfig.getSurfaceHandlingMode()) != SurfaceHandlingMode.AUDIO_ONLY) {
                this.logger.debug(Intrinsics.stringPlus("AMDB ignoring any changes, since the new view is the same as the old ", Long.valueOf(Thread.currentThread().getId())));
                return;
            }
        }
        this.logger.debug(Intrinsics.stringPlus("AMDB calling SDK.changeParentView(); threadId: ", Long.valueOf(Thread.currentThread().getId())));
        getPlayerFeature().setRenderingConfig(renderingConfig);
    }

    public final void setPlayerConfig(PlayerConfigData playerConfig, RenderingConfig renderingConfig) {
        Intrinsics.checkNotNullParameter(playerConfig, "playerConfig");
        if (this.playerFeature instanceof NoOpPrimeVideoFeaturePlayer) {
            destroy(false);
            setPlayerConfigData$DMMPlayer_release(playerConfig);
            this.logger.debug(Intrinsics.stringPlus("AMDB calling SDK.getPlayerFeature() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
            this.playerFeature = this.pvPlayerSdk.getPlayerFeature(getPlayerConfigData$DMMPlayer_release());
            registerPlayerListeners$DMMPlayer_release();
        } else {
            setPlayerConfigData$DMMPlayer_release(playerConfig);
        }
        setPVParentViewUpdate(renderingConfig);
        setPlayerVisibility(true);
        markPlayerConfigSet(true);
        markPlayerReady(true);
    }

    public final synchronized void setPlayerVisibility(boolean isForeground) {
        this.logger.debug("AMDB calling SDK.setPlayerVisibility(" + isForeground + ") ; threadId: " + Thread.currentThread().getId());
        this.isForeground = isForeground;
        toggleCanSeek(this.playerFeature.getIsVideoTrackRecreationSupported() || isForeground);
    }

    private final void toggleCanSeek(boolean canSeek) {
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        MediaItem mediaItem = currentPlayRequest == null ? null : currentPlayRequest.getMediaItem();
        if (mediaItem == null) {
            return;
        }
        mediaItem.setCanSeekOverride(canSeek);
    }

    public final void destroy(boolean unsubscribePlaybackObservers) {
        this.logger.debug(Intrinsics.stringPlus("AMDB calling playerFeature.destroy() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
        unregisterPlayerListeners$DMMPlayer_release();
        if (unsubscribePlaybackObservers) {
            Subscription subscription = this.configSetSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            Subscription subscription2 = this.playerReadySubscription;
            if (subscription2 != null) {
                subscription2.unsubscribe();
            }
        }
        this.videoRenderingConfig = null;
        this.playerFeature.destroy();
        this.playerFeature = new NoOpPrimeVideoFeaturePlayer();
        markPlayerConfigSet(false);
    }

    public final void showCaptions(boolean value) {
        boolean zIsShowingCaptions = isShowingCaptions();
        this.logger.debug(Intrinsics.stringPlus("AMDB calling playerFeature.setTimedTextConfig() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
        this.playerFeature.getStreamFeature().setTimedTextConfig(this.timedTextConfigData);
        this.logger.debug("AMDB calling playerFeature.showCaptions(" + value + ") ; threadId: " + Thread.currentThread().getId());
        TimedTextStreamGroup timedTextStreams = this.playerFeature.getStreamFeature().getTimedTextStreams();
        if (timedTextStreams != null) {
            timedTextStreams.showCaptions(value);
        }
        sendCaptionsToggleMetrics(zIsShowingCaptions, value);
    }

    private final Observable<Boolean> getPlayerConfigObservable() {
        Observable<Boolean> observableObserveOn = this.configSetSubject.observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "configSetSubject.observe…dSchedulers.mainThread())");
        return observableObserveOn;
    }

    public final void markPlayerConfigSet(boolean isSet) {
        this.configSetSubject.onNext(Boolean.valueOf(isSet));
    }

    public final synchronized Observable<Boolean> getPlayerReadyObservable() {
        Observable<Boolean> observableObserveOn;
        observableObserveOn = this.playerReadySubject.observeOn(Schedulers.computation());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "playerReadySubject.obser…Schedulers.computation())");
        return observableObserveOn;
    }

    public final synchronized void markPlayerReady(boolean isReady) {
        this.logger.debug("AMDB markPlayerReady " + isReady + " ; threadId: " + Thread.currentThread().getId());
        this.playerReadySubject.onNext(Boolean.valueOf(isReady));
    }

    public final boolean hasCaptions() {
        TimedTextStreamGroup timedTextStreams = this.playerFeature.getStreamFeature().getTimedTextStreams();
        List<TimedTextStream> streams = timedTextStreams == null ? null : timedTextStreams.getStreams();
        boolean z = streams == null || streams.isEmpty();
        if (z && getPlayStatus() == PlayStatus.RENDERING) {
            sendCaptionsToggleMetrics(false, false);
        }
        return !z;
    }

    public final boolean isShowingCaptions() {
        TimedTextStreamGroup timedTextStreams = this.playerFeature.getStreamFeature().getTimedTextStreams();
        if (timedTextStreams == null) {
            return false;
        }
        return timedTextStreams.isShowingCaptions();
    }

    static /* synthetic */ void setStreamingError$default(PrimeVideoPlayer primeVideoPlayer, long j, PlaybackException.PlaybackError playbackError, PlaybackError playbackError2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setStreamingError");
        }
        if ((i & 4) != 0) {
            playbackError2 = null;
        }
        primeVideoPlayer.setStreamingError(j, playbackError, playbackError2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStreamingError(long requestTime, PlaybackException.PlaybackError internalPlaybackError, PlaybackError videoPlaybackError) {
        PlaybackException videoPlaybackException;
        this.logger.error("Streaming error stopping playback - internalPlaybackError: " + internalPlaybackError + " videoPlaybackError = " + videoPlaybackError);
        PlaybackException.Config config = new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).autoRetry(true);
        if (videoPlaybackError != null) {
            config.description(String.valueOf(videoPlaybackError.getErrorCode()));
        }
        PlaybackException.PlaybackError playbackError = PlaybackException.PlaybackError.NetworkFailure;
        if (internalPlaybackError == playbackError) {
            if (NetworkInfoUtil.hasAnyInternetConnection()) {
                playbackError = PlaybackException.PlaybackError.StreamingVideoRestriction;
            }
            config.playbackError(playbackError);
            videoPlaybackException = new NetworkUnavailableException(config);
        } else {
            config.playbackError(internalPlaybackError);
            Intrinsics.checkNotNullExpressionValue(config, "config");
            videoPlaybackException = new VideoPlaybackException(config);
        }
        PlaybackException playbackException = videoPlaybackException;
        if (getCurrentPlayRequest() != null) {
            setPlayRequestStatus(getCurrentPlayRequest(), getCurrentPlayRequest().getPlayRequestStatus().onError(), getCurrentPlayRequest().getPositionTracker().getCurrentPosition(), Clock.now(), -1.0f, playbackException, ChangeReason.ERROR, null);
            sendStreamingErrorMetrics(playbackException);
        }
        setPlayStatus(PlayStatus.USER_PAUSED, playbackException, ChangeReason.ERROR, ControlSource.NONE, requestTime);
    }

    public final void onConnectivityLost() {
        if (this.playerFeature.getPlaybackState() == PlaybackState.Playing) {
            setStreamingError$default(this, Clock.now(), PlaybackException.PlaybackError.NetworkFailure, null, 4, null);
            this.logger.debug(Intrinsics.stringPlus("AMDB calling playerFeature.pause() ; threadId: ", Long.valueOf(Thread.currentThread().getId())));
            this.playerFeature.pause();
        }
    }

    public final boolean isValidPlaybackToken() {
        Date expiration;
        GetPlaybackTokenResponse getPlaybackTokenResponse = this.playbackTokenResponse;
        if (getPlaybackTokenResponse == null || (expiration = getPlaybackTokenResponse.getExpiration()) == null) {
            return false;
        }
        return expiration.after(Calendar.getInstance().getTime());
    }

    private final GetPlaybackTokenResponse fetchPlaybackToken() {
        try {
            this.playbackTokenResponse = new VideoDeliveryProvider(this.videoDeliveryService).getPlaybackToken(createPlaybackTokenRequest());
            this.logger.debug("Fetched new PlaybackToken");
            if (!isValidPlaybackToken()) {
                this.logger.warn("The fresh playbackToken seems to be not valid. This is likely a device date and Time issue. ");
            }
        } catch (VolleyError e) {
            this.logger.error(Intrinsics.stringPlus("Failed fetching the Playback Token ", e));
            setStreamingError$default(this, Clock.now(), PlaybackException.PlaybackError.VideoPlayerToken, null, 4, null);
        }
        return this.playbackTokenResponse;
    }

    private final GetPlaybackTokenRequest createPlaybackTokenRequest() {
        PlaybackConfig playbackConfig = getPlaybackConfig();
        ClientIdentification clientIdentification = playbackConfig == null ? null : playbackConfig.getClientIdentification();
        if (clientIdentification == null) {
            clientIdentification = Playback.getInstance().getPlaybackConfig().getClientIdentification();
            Intrinsics.checkNotNullExpressionValue(clientIdentification, "getInstance().playbackConfig.clientIdentification");
        }
        DeviceToken deviceToken = new DeviceToken();
        deviceToken.setDeviceId(clientIdentification.getDeviceID());
        deviceToken.setDeviceTypeId(clientIdentification.getDeviceTypeID());
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setMarketplaceId(clientIdentification.getMarketplaceID());
        customerInfo.setTerritoryId(clientIdentification.getTerritoryID());
        GetPlaybackTokenRequest getPlaybackTokenRequest = new GetPlaybackTokenRequest();
        getPlaybackTokenRequest.setCustomerInfo(customerInfo);
        getPlaybackTokenRequest.setCustomerId(clientIdentification.getCustomerID());
        getPlaybackTokenRequest.setDeviceToken(deviceToken);
        return getPlaybackTokenRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cacheNextVideo() {
        String playbackToken;
        MediaItemId mediaItemId;
        this.logger.debug("Caching next video");
        PlayRequest playRequest = getPlayRequest(getCurrentIndex() + 1);
        if (playRequest == null) {
            return;
        }
        LocalContentManager localContentManagerFeature = this.pvPlayerSdk.getLocalContentManagerFeature();
        CachePriority cachePriority = CachePriority.HIGH;
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(600.0d);
        Intrinsics.checkNotNullExpressionValue(timeSpanFromSeconds, "fromSeconds(TIME_TO_LIVE_DURATION)");
        CacheConfigData cacheConfigData = new CacheConfigData(cachePriority, timeSpanFromSeconds);
        GetPlaybackTokenResponse getPlaybackTokenResponse = this.playbackTokenResponse;
        String str = (getPlaybackTokenResponse == null || (playbackToken = getPlaybackTokenResponse.getPlaybackToken()) == null) ? "" : playbackToken;
        MediaItem mediaItem = playRequest.getMediaItem();
        String id = (mediaItem == null || (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID)) == null) ? null : mediaItemId.getId();
        if (id == null) {
            return;
        }
        localContentManagerFeature.cache(CollectionsKt__CollectionsJVMKt.listOf(new CacheContentConfigData(new ContentConfigData(str, id, 0L, null, null, null, false, false, 248, null), cacheConfigData, this.playerFeature.getStreamFeature().getAudioStreamPreferences())));
    }

    public final void cacheVideos(final List<String> pidList) {
        Intrinsics.checkNotNullParameter(pidList, "pidList");
        if (!isValidPlaybackToken()) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    PrimeVideoPlayer.m2170cacheVideos$lambda12(this.f$0, pidList);
                }
            });
        } else {
            cachePIDs(pidList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cacheVideos$lambda-12, reason: not valid java name */
    public static final void m2170cacheVideos$lambda12(final PrimeVideoPlayer this$0, final List pidList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pidList, "$pidList");
        final GetPlaybackTokenResponse getPlaybackTokenResponseFetchPlaybackToken = this$0.fetchPlaybackToken();
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.PrimeVideoPlayer$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                PrimeVideoPlayer.m2171cacheVideos$lambda12$lambda11(this.f$0, getPlaybackTokenResponseFetchPlaybackToken, pidList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cacheVideos$lambda-12$lambda-11, reason: not valid java name */
    public static final void m2171cacheVideos$lambda12$lambda11(PrimeVideoPlayer this$0, GetPlaybackTokenResponse getPlaybackTokenResponse, List pidList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pidList, "$pidList");
        this$0.playbackTokenResponse = getPlaybackTokenResponse;
        this$0.cachePIDs(pidList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void handlePlaybackErrors() {
        this.logger.debug("handlePlaybackErrors()");
        destroy(true);
        setPlayerConfig(getPlayerConfigData$DMMPlayer_release(), null);
    }

    private final void cachePIDs(List<String> pidList) {
        String playbackToken;
        LocalContentManager localContentManagerFeature = this.pvPlayerSdk.getLocalContentManagerFeature();
        CachePriority cachePriority = CachePriority.HIGH;
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(600.0d);
        Intrinsics.checkNotNullExpressionValue(timeSpanFromSeconds, "fromSeconds(TIME_TO_LIVE_DURATION)");
        CacheConfigData cacheConfigData = new CacheConfigData(cachePriority, timeSpanFromSeconds);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(pidList, 10));
        for (String str : pidList) {
            GetPlaybackTokenResponse playbackTokenResponse = getPlaybackTokenResponse();
            String str2 = "";
            if (playbackTokenResponse != null && (playbackToken = playbackTokenResponse.getPlaybackToken()) != null) {
                str2 = playbackToken;
            }
            arrayList.add(new ContentConfigData(str2, str, 0L, null, null, null, false, false, 248, null));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new CacheContentConfigData((ContentConfigData) it.next(), cacheConfigData, getPlayerFeature().getStreamFeature().getAudioStreamPreferences()));
        }
        localContentManagerFeature.cache(arrayList2);
    }

    public final void updatePlayerReadySubject$DMMPlayer_release(PlayerEvent.ContentStateChange newContentStateChange) {
        Intrinsics.checkNotNullParameter(newContentStateChange, "newContentStateChange");
        if (newContentStateChange.getContentState() == ContentState.Waiting) {
            markPlayerReady(false);
        } else {
            markPlayerReady(true);
        }
    }

    public final void registerPlayerListeners$DMMPlayer_release() {
        VideoPreviewAssetFeature videoPreviewAssetFeature;
        this.playerFeature.on(PlayerEvent.PlaybackStateChange.class, this.playbackStateChangeListener);
        this.playerFeature.on(PlayerEvent.ContentStateChange.class, this.contentStateChangeListener);
        this.playerFeature.on(PlayerEvent.TimeDataChange.class, this.timeDataChangeListener);
        this.playerFeature.on(PlayerEvent.PlayerError.class, this.playerErrorListener);
        this.playerFeature.on(PlayerEvent.ContentError.class, this.contentErrorListener);
        this.playerFeature.on(PlayerEvent.TimelineChange.class, this.timelineChangeListener);
        this.playerFeature.on(PlayerEvent.TimelineEnded.class, this.timelineEndedListener);
        Player player = this.playerFeature;
        if (player == null || (videoPreviewAssetFeature = player.getVideoPreviewAssetFeature()) == null) {
            return;
        }
        videoPreviewAssetFeature.on(VideoPreviewAssetEvent.VideoPreviewChange.class, this.videoPreviewEventListener);
    }

    public final void unregisterPlayerListeners$DMMPlayer_release() {
        VideoPreviewAssetFeature videoPreviewAssetFeature;
        this.playerFeature.off(PlayerEvent.PlaybackStateChange.class, this.playbackStateChangeListener);
        this.playerFeature.off(PlayerEvent.ContentStateChange.class, this.contentStateChangeListener);
        this.playerFeature.off(PlayerEvent.TimeDataChange.class, this.timeDataChangeListener);
        this.playerFeature.off(PlayerEvent.PlayerError.class, this.playerErrorListener);
        this.playerFeature.off(PlayerEvent.ContentError.class, this.contentErrorListener);
        this.playerFeature.off(PlayerEvent.TimelineChange.class, this.timelineChangeListener);
        this.playerFeature.off(PlayerEvent.TimelineEnded.class, this.timelineEndedListener);
        Player player = this.playerFeature;
        if (player == null || (videoPreviewAssetFeature = player.getVideoPreviewAssetFeature()) == null) {
            return;
        }
        videoPreviewAssetFeature.off(VideoPreviewAssetEvent.VideoPreviewChange.class, this.videoPreviewEventListener);
    }

    public final void setError(ConcurrencyException error, ChangeReason reason) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(reason, "reason");
        setError(getCurrentPlayRequest(), error, reason);
    }

    private final void setError(PlayRequest request, PlaybackException error, ChangeReason reason) {
        this.logger.warn("onError(" + request + ", " + reason + ") - current request: " + getCurrentPlayRequest(), (Throwable) error);
        if (request != getCurrentPlayRequest()) {
            return;
        }
        if (request != null) {
            setPlayRequestStatus(request, request.getPlayRequestStatus().onError(), request.getPositionTracker().getCurrentPosition(), Clock.now(), -1.0f, error, reason, null);
        }
        removeAllPlayRequests();
        setPlayStatus(PlayStatus.USER_PAUSED, error, reason, null, Clock.now());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPlaybackStarted() {
        setPlaybackInitiated();
        ChangeReason changeReason = this.isPlaybackInitiated ? ChangeReason.MEDIA_ITEM_LOADED : ChangeReason.PLAY_PAUSE;
        PlayStatus playStatus = PlayStatus.RENDERING;
        ControlSource controlSource = ControlSource.NONE;
        setPlayStatus(playStatus, null, changeReason, controlSource, Clock.now());
        setPlayRequestStatusOnPlaybackStarted(changeReason, controlSource);
        Iterator<T> it = this.onPlaybackStartedListeners.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(Unit.INSTANCE);
        }
        if (this.wasSeeking) {
            return;
        }
        sendStartPlaybackMetrics(changeReason);
    }

    public final void setPlayRequestStatusOnPlaybackStarted(ChangeReason changeReason, ControlSource controlSource) {
        Intrinsics.checkNotNullParameter(changeReason, "changeReason");
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        PlayRequestStatus playRequestStatus = currentPlayRequest == null ? null : currentPlayRequest.getPlayRequestStatus();
        PlayRequestStatus playRequestStatus2 = PlayRequestStatus.RENDERING;
        if (playRequestStatus != playRequestStatus2) {
            PlayRequest currentPlayRequest2 = getCurrentPlayRequest();
            PositionTracker positionTracker = getCurrentPlayRequest().getPositionTracker();
            setPlayRequestStatus(currentPlayRequest2, playRequestStatus2, positionTracker == null ? 0L : positionTracker.getCurrentPosition(), Clock.now(), -1.0f, null, changeReason, controlSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlayRequestStatusOnPlaybackResumedAfterSeek() {
        ChangeReason changeReason = ChangeReason.TIME_SEEK;
        ControlSource controlSource = ControlSource.APP_UI;
        setPlayRequestStatusOnPlaybackStarted(changeReason, controlSource);
        updatePlayStatus(changeReason, controlSource);
        this.wasSeeking = false;
    }

    private final long computeDurationOffset() {
        PositionTracker positionTracker;
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        long checkpointPosition = (currentPlayRequest == null || (positionTracker = currentPlayRequest.getPositionTracker()) == null) ? 0L : positionTracker.getCheckpointPosition();
        long j = this.durationOffsetSeconds;
        if (j < checkpointPosition) {
            long j2 = checkpointPosition - j;
            this.durationSeconds = j2;
            this.totalDurationPlayedSeconds += j2;
        } else {
            this.durationSeconds = 0L;
        }
        return checkpointPosition;
    }

    private final long getResumedPlaybackDelay(MetricsInfo info) {
        long timePlaybackPlaying = getTimePlaybackPlaying(info);
        long timePlaybackExpected = getTimePlaybackExpected(info);
        if (timePlaybackPlaying == 0 || timePlaybackExpected == 0 || timePlaybackExpected > timePlaybackPlaying) {
            return 0L;
        }
        return timePlaybackPlaying - timePlaybackExpected;
    }

    private final long getInitialPlaybackDelay(MetricsInfo info) {
        long timeMediaItemKnown = getTimeMediaItemKnown(info);
        long timePlaybackPlaying = getTimePlaybackPlaying(info);
        if (timeMediaItemKnown == 0 || timePlaybackPlaying == 0 || timeMediaItemKnown > timePlaybackPlaying) {
            return 0L;
        }
        return timePlaybackPlaying - timeMediaItemKnown;
    }

    private final long getTimeMediaItemKnown(MetricsInfo info) {
        for (MetricsEvent metricsEvent : info.getEventsSnapshot()) {
            if (metricsEvent.getEventType() == PlaybackEventType.EVENT_MEDIA_ITEM_KNOWN) {
                return metricsEvent.getEventTimeMillis();
            }
        }
        return 0L;
    }

    private final long getTimePlaybackPlaying(MetricsInfo info) {
        List<MetricsEvent> eventsSnapshot = info.getEventsSnapshot();
        ListIterator<MetricsEvent> listIterator = eventsSnapshot.listIterator(eventsSnapshot.size());
        while (listIterator.hasPrevious()) {
            MetricsEvent metricsEventPrevious = listIterator.previous();
            if (metricsEventPrevious.getEventType() == PlaybackEventType.EVENT_PLAYBACK_PLAYING) {
                return metricsEventPrevious.getEventTimeMillis();
            }
        }
        return 0L;
    }

    private final long getTimePlaybackExpected(MetricsInfo info) {
        List<MetricsEvent> eventsSnapshot = info.getEventsSnapshot();
        ListIterator<MetricsEvent> listIterator = eventsSnapshot.listIterator(eventsSnapshot.size());
        while (listIterator.hasPrevious()) {
            MetricsEvent metricsEventPrevious = listIterator.previous();
            if (metricsEventPrevious.getEventType() == PlaybackEventType.EVENT_PLAYBACK_EXPECTED) {
                return metricsEventPrevious.getEventTimeMillis();
            }
        }
        return 0L;
    }

    private final void sendTrackScrubMetrics(long prevSeekPosition, long currentSeekPosition, ChangeReason changeReason) {
        try {
            sendPlaybackTerminatedMetrics(changeReason, true, prevSeekPosition, currentSeekPosition, null);
            if (prevSeekPosition > 0 && currentSeekPosition == 0) {
                sendPlaybackStreamedMetrics(changeReason);
            }
            if (getPlayStatus() != PlayStatus.USER_PAUSED) {
                setPlaybackInitiated();
                sendStartPlaybackMetrics(changeReason);
            }
        } catch (Exception e) {
            this.logger.error(Intrinsics.stringPlus("Exception when sending Track Scrub Start Metrics. ", e));
        }
    }

    private final void sendStreamingErrorMetrics(PlaybackException playbackException) {
        setMTSNetworkProvider();
        sendPlaybackTerminatedMetrics(ChangeReason.ERROR, false, 0L, 0L, playbackException);
    }

    private final void sendCaptionsToggleMetrics(boolean previousValue, boolean newValue) {
        try {
            PlayRequest currentPlayRequest = getCurrentPlayRequest();
            if (currentPlayRequest == null) {
                return;
            }
            if (this.isPlaybackInitiated || previousValue != newValue) {
                MTSEvent mTSEventCreatePlaybackStateEvent = getPlaybackConfig().getMTSEventFactory().createPlaybackStateEvent(currentPlayRequest.getMetricsHandler().getMetricsInfo(), null, getPositionMillis(), newValue);
                Intrinsics.checkNotNullExpressionValue(mTSEventCreatePlaybackStateEvent, "eventFactory.createPlayb…positionMillis, newValue)");
                currentPlayRequest.getMetricsSender().sendMetricsEvent(mTSEventCreatePlaybackStateEvent);
            }
        } catch (Exception e) {
            this.logger.error(Intrinsics.stringPlus("Exception when sending Captions Toggle metrics. ", e));
        }
    }

    private final void sendStartPlaybackMetrics(ChangeReason changeReason) {
        MTSEvent mTSEventCreateStreamingInitiatedEvent;
        try {
            PlayRequest currentPlayRequest = getCurrentPlayRequest();
            if (currentPlayRequest == null) {
                return;
            }
            MTSMetricsSender metricsSender = currentPlayRequest.getMetricsSender();
            Intrinsics.checkNotNullExpressionValue(metricsSender, "playRequest.metricsSender");
            MTSEventFactory mTSEventFactory = getPlaybackConfig().getMTSEventFactory();
            MetricsInfo metricsInfo = currentPlayRequest.getMetricsHandler().getMetricsInfo();
            setMTSNetworkProvider();
            if (this.isPlaybackInitiated || changeReason == ChangeReason.RESTART) {
                Intrinsics.checkNotNullExpressionValue(metricsInfo, "metricsInfo");
                mTSEventCreateStreamingInitiatedEvent = mTSEventFactory.createStreamingInitiatedEvent(metricsInfo, null, getInitialPlaybackDelay(metricsInfo), 0L, changeReason);
                Intrinsics.checkNotNullExpressionValue(mTSEventCreateStreamingInitiatedEvent, "{\n                    ev…Reason)\n                }");
            } else {
                Intrinsics.checkNotNullExpressionValue(metricsInfo, "metricsInfo");
                mTSEventCreateStreamingInitiatedEvent = mTSEventFactory.createStreamingResumedEvent(metricsInfo, null, getResumedPlaybackDelay(metricsInfo), 0L, getPositionMillis(), changeReason);
                Intrinsics.checkNotNullExpressionValue(mTSEventCreateStreamingInitiatedEvent, "{\n                    ev…Reason)\n                }");
            }
            metricsSender.sendMetricsEvent(mTSEventCreateStreamingInitiatedEvent);
        } catch (Exception e) {
            this.logger.error(Intrinsics.stringPlus("Exception when sending playback Start Metrics. ", e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendPlaybackCompletedMetrics(ChangeReason changeReason) {
        try {
            if (getPositionMillis() != 0) {
                if (this.playerFeature.getPlaybackState() == PlaybackState.Playing && changeReason != ChangeReason.NEW_SOURCE) {
                    sendPlaybackTerminatedMetrics(changeReason, false, 0L, 0L, null);
                }
                sendPlaybackStreamedMetrics(changeReason);
            }
        } catch (Exception e) {
            this.logger.error(Intrinsics.stringPlus("Failed sending Terminated Metrics ", e));
        }
    }

    private final void resetMetricsDuration() {
        this.durationOffsetSeconds = 0L;
        this.totalDurationPlayedSeconds = 0L;
    }

    private final void sendPlaybackStreamedMetrics(ChangeReason changeReason) {
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            return;
        }
        currentPlayRequest.getMetricsSender().sendTrackStreamed(currentPlayRequest.getMetricsHandler().getMetricsInfo(), this.totalDurationPlayedSeconds, changeReason);
        resetMetricsDuration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendPlaybackTerminatedMetrics(ChangeReason changeReason, boolean isSeeking, long prevSeekPosition, long currentSeekPosition, PlaybackException exception) {
        PositionTracker positionTracker;
        if (isSeeking) {
            long j = prevSeekPosition - this.durationOffsetSeconds;
            this.durationSeconds = j;
            this.totalDurationPlayedSeconds += j;
            this.durationOffsetSeconds = currentSeekPosition;
        } else {
            this.durationOffsetSeconds = computeDurationOffset();
        }
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null) {
            return;
        }
        MetricsInfo metricsInfo = currentPlayRequest.getMetricsHandler().getMetricsInfo();
        long checkpointPosition = 0;
        if (exception != null) {
            MTSMetricsSender metricsSender = currentPlayRequest.getMetricsSender();
            Intrinsics.checkNotNullExpressionValue(metricsSender, "playRequest.metricsSender");
            MTSEventFactory mTSEventFactory = getPlaybackConfig().getMTSEventFactory();
            PositionTracker positionTracker2 = currentPlayRequest.getPositionTracker();
            long checkpointPosition2 = positionTracker2 == null ? 0L : positionTracker2.getCheckpointPosition();
            ChangeReason changeReason2 = ChangeReason.ERROR;
            PlayRequest currentPlayRequest2 = getCurrentPlayRequest();
            if (currentPlayRequest2 != null && (positionTracker = currentPlayRequest2.getPositionTracker()) != null) {
                checkpointPosition = positionTracker.getCheckpointPosition();
            }
            MTSEvent mTSEventCreateStreamingTerminatedEvent = mTSEventFactory.createStreamingTerminatedEvent(metricsInfo, (MediaLink) null, 0L, 0L, checkpointPosition2, 0, 0L, 0.0f, changeReason2, exception, checkpointPosition);
            Intrinsics.checkNotNullExpressionValue(mTSEventCreateStreamingTerminatedEvent, "eventFactory.createStrea…                   ?: 0L)");
            metricsSender.sendMetricsEvent(mTSEventCreateStreamingTerminatedEvent);
            return;
        }
        MTSMetricsSender metricsSender2 = currentPlayRequest.getMetricsSender();
        long j2 = this.durationSeconds;
        PositionTracker positionTracker3 = currentPlayRequest.getPositionTracker();
        metricsSender2.sendStreamingTerminated(metricsInfo, null, 0L, 0L, j2, 0, 0L, 0.0f, changeReason, positionTracker3 == null ? 0L : positionTracker3.getCheckpointPosition());
    }

    private final void setPlaybackInitiated() {
        this.isPlaybackInitiated = getPositionMillis() == 0;
    }

    private final void setMTSNetworkProvider() {
        PlaybackConfig playbackConfig = getPlaybackConfig();
        MTSEventFactory mTSEventFactory = playbackConfig == null ? null : playbackConfig.getMTSEventFactory();
        DefaultMTSEventFactory defaultMTSEventFactory = mTSEventFactory instanceof DefaultMTSEventFactory ? (DefaultMTSEventFactory) mTSEventFactory : null;
        if (defaultMTSEventFactory == null) {
            return;
        }
        defaultMTSEventFactory.setNetworkProvider(buildMTSNetworkProvider());
    }

    private final NetworkProvider buildMTSNetworkProvider() {
        NetworkProvider networkProvider = NetworkProvider.UNKNOWN;
        if (NetworkInfoUtil.isWifiConnected()) {
            networkProvider = NetworkProvider.WIFI;
        }
        Intrinsics.checkNotNullExpressionValue(networkProvider, "networkProvider");
        return networkProvider;
    }
}
