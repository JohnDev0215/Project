package com.amazon.mp3.playback.start;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playback.ExceptionData;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackExceptions;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.playback.augmentation.InteractionPatternAugmentation;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.station.StationItem;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartPlaybackUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bI\u0010JJ®\u0001\u0010 \u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0017\u001a\u00020\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\b2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007JÆ\u0001\u0010$\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\"\u001a\u00020\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\b2\b\b\u0002\u0010#\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007Jv\u0010)\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010&\u001a\u00020%2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007J0\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020*0\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002JL\u0010.\u001a\b\u0012\u0004\u0012\u00020+0-2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\bH\u0002J0\u0010.\u001a\b\u0012\u0004\u0012\u00020+0-2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\bH\u0002J\u001a\u0010.\u001a\u0004\u0018\u00010+2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\bH\u0002J3\u00103\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u001f0/H\u0002JP\u00103\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u000226\u00102\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u001105¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u001f042\u0006\u00106\u001a\u000205H\u0002J\u0018\u00107\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u00106\u001a\u000205H\u0002J\u0010\u00108\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J:\u0010;\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010:\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\rH\u0007J0\u0010?\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00062\b\u0010>\u001a\u0004\u0018\u00010\u00062\u0006\u00106\u001a\u000205R\u001f\u0010A\u001a\n @*\u0004\u0018\u00010\u00060\u00068\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u001d\u0010H\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010D¨\u0006K"}, d2 = {"Lcom/amazon/mp3/playback/start/StartPlaybackUtil;", "", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "contentUri", "", "queueTitle", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "trackList", "", "startPosition", "", "shuffle", "fullScreen", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "isCustomerSelectedEntity", "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "Lcom/amazon/music/media/playback/MediaCollectionId;", "additionalMediaCollectionId", "isSingleTrackSFA", "Lcom/amazon/mp3/playback/PlaybackListener;", "playbackListener", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "augmentations", "isUserInitiated", "Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", "noOpCloudQueueClientHandler", "", "startMusicTracksPlayback", "sortOrder", "isRefinementFilterApplied", "useNoOpCloudQueueClientHandler", "startCollectionPlayback", "Lcom/amazon/music/station/StationItem;", "stationItem", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "upsellInformation", "startStationPlayback", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "Lcom/amazon/music/platform/playback/data/PlayQueueInteractionPattern;", "getQueueInteractionPatternTrackList", "Lio/reactivex/rxjava3/core/Single;", "getQueueInteractionPattern", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "displayDialog", "displayError", "Lkotlin/Function2;", "Lcom/amazon/mp3/playback/PlaybackExceptions$Exceptions;", "exception", "displayPlaybackDialogHelper", "displayNoConnectionErrorHelper", "queueId", "startFullScreen", "startCQPlayback", ParserUtil.TAG_QUERY_PARAM_NAME, NotificationCompat.CATEGORY_MESSAGE, "extraMsg", "displayPlaybackError", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "serviceTier$delegate", "Lkotlin/Lazy;", "getServiceTier", "serviceTier", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StartPlaybackUtil {
    public static final StartPlaybackUtil INSTANCE = new StartPlaybackUtil();
    private static final String TAG = StartPlaybackUtil.class.getSimpleName();

    /* JADX INFO: renamed from: serviceTier$delegate, reason: from kotlin metadata */
    private static final Lazy serviceTier = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$serviceTier$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            ServiceTierEnum serviceTier2 = CloudQueueDigitalXPUtil.INSTANCE.getServiceTier();
            if (serviceTier2 == null) {
                return null;
            }
            return serviceTier2.name();
        }
    });

    /* JADX INFO: compiled from: StartPlaybackUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayQueueInteractionPattern.values().length];
            iArr[PlayQueueInteractionPattern.ONLINE.ordinal()] = 1;
            iArr[PlayQueueInteractionPattern.LOCAL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private StartPlaybackUtil() {
    }

    @JvmStatic
    public static final void startMusicTracksPlayback(final Context context, final Uri contentUri, final String queueTitle, List<? extends MusicTrack> trackList, int startPosition, final boolean shuffle, final boolean fullScreen, final PlaybackPageType playbackPageType, final boolean isCustomerSelectedEntity, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, boolean isSingleTrackSFA, final PlaybackListener playbackListener, final List<? extends PlaybackAugmentation> augmentations, final boolean isUserInitiated, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        if (trackList == null || trackList.isEmpty()) {
            if (!ConnectivityUtil.hasAnyInternetConnection(context)) {
                StartPlaybackUtil startPlaybackUtil = INSTANCE;
                startPlaybackUtil.displayError(context, new C03981(startPlaybackUtil));
                return;
            } else {
                Log.error(TAG, "track list is null, unable to start tracks playback");
                if (playbackListener != null) {
                    PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.EMPTY_TRACK_LIST, null, 2, null);
                }
                INSTANCE.displayPlaybackError(context, PlaybackError.INVALID_CONTENT.name(), "track list is null, unable to start tracks playback", null, PlaybackExceptions.Exceptions.NULL_TRACK_LIST);
                return;
            }
        }
        List<? extends MusicTrack> listSingletonList = isSingleTrackSFA ? Collections.singletonList(trackList.get(startPosition)) : trackList;
        int i = isSingleTrackSFA ? 0 : startPosition;
        final List<? extends MusicTrack> list = listSingletonList;
        final int i2 = i;
        INSTANCE.getQueueInteractionPattern(context, contentUri, listSingletonList, i, augmentations).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StartPlaybackUtil.m1486startMusicTracksPlayback$lambda0(contentUri, context, queueTitle, list, i2, shuffle, fullScreen, playbackPageType, isCustomerSelectedEntity, controlSource, additionalMediaCollectionId, playbackListener, augmentations, isUserInitiated, noOpCloudQueueClientHandler, (PlayQueueInteractionPattern) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StartPlaybackUtil.m1487startMusicTracksPlayback$lambda1(contentUri, playbackListener, context, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.start.StartPlaybackUtil$startMusicTracksPlayback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StartPlaybackUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class C03981 extends FunctionReferenceImpl implements Function1<Context, Unit> {
        C03981(Object obj) {
            super(1, obj, StartPlaybackUtil.class, "displayNoConnectionErrorHelper", "displayNoConnectionErrorHelper(Landroid/content/Context;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Context context) {
            invoke2(context);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Context p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((StartPlaybackUtil) this.receiver).displayNoConnectionErrorHelper(p0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startMusicTracksPlayback$lambda-0, reason: not valid java name */
    public static final void m1486startMusicTracksPlayback$lambda0(Uri contentUri, Context context, String str, List trackList, int i, boolean z, boolean z2, PlaybackPageType playbackPageType, boolean z3, ControlSource controlSource, MediaCollectionId mediaCollectionId, PlaybackListener playbackListener, List list, boolean z4, NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, PlayQueueInteractionPattern queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
        Intrinsics.checkNotNullParameter(queueInteractionPattern, "queueInteractionPattern");
        Log.debug(TAG, "startMusicTracksPlayback for contentUri: " + contentUri + " with queue interaction pattern " + queueInteractionPattern);
        int i2 = WhenMappings.$EnumSwitchMapping$0[queueInteractionPattern.ordinal()];
        if (i2 == 1) {
            Intrinsics.checkNotNullExpressionValue(trackList, "trackList");
            StartTracksPlayback.startMusicTracksOnlineInteractionPlayback(context, contentUri, str, trackList, i, z, z2, playbackPageType, z3, controlSource, mediaCollectionId, playbackListener, list, z4, noOpCloudQueueClientHandler);
        } else {
            if (i2 != 2) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(trackList, "trackList");
            StartTracksPlayback.startMusicTracksLocalInteractionPlayback(context, contentUri, str, trackList, i, z, z2, playbackPageType, z3, controlSource, mediaCollectionId, playbackListener, z4, noOpCloudQueueClientHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startMusicTracksPlayback$lambda-1, reason: not valid java name */
    public static final void m1487startMusicTracksPlayback$lambda1(Uri contentUri, PlaybackListener playbackListener, Context context, Throwable throwable) {
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Log.error(TAG, Intrinsics.stringPlus("startMusicTracksPlayback: Unrecognized Play Queue Interaction Pattern, unable to start playback of ", contentUri), throwable);
        if (playbackListener != null) {
            playbackListener.onPlaybackFailure(PlaybackError.NULL_PLAY_QUEUE_INTERACTION_PATTERN, new Exception(throwable));
        }
        INSTANCE.displayPlaybackError(context, PlaybackError.INITIATE_FAILURE.name(), Intrinsics.stringPlus("Unrecognized Play Queue Interaction Pattern ", throwable.getMessage()), null, PlaybackExceptions.Exceptions.CQ_INTERACTION_PATTERN);
    }

    @JvmStatic
    public static final void startCollectionPlayback(final Context context, final String queueTitle, final Uri contentUri, final List<? extends Object> trackList, final int startPosition, final boolean shuffle, final boolean fullScreen, final String sortOrder, final PlaybackPageType playbackPageType, final boolean isCustomerSelectedEntity, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, final boolean isRefinementFilterApplied, final PlaybackListener playbackListener, final List<? extends PlaybackAugmentation> augmentations, boolean useNoOpCloudQueueClientHandler, final boolean isUserInitiated, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        if (useNoOpCloudQueueClientHandler) {
            Log.debug(TAG, "Falling back to noOpCloudQueueClientHandler for contentUri: " + contentUri + " due to useNoOpCloudQueueClientHandler=true");
            noOpCloudQueueClientHandler.handlePlayback();
            return;
        }
        INSTANCE.getQueueInteractionPattern(context, contentUri, augmentations).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StartPlaybackUtil.m1484startCollectionPlayback$lambda2(contentUri, sortOrder, context, queueTitle, trackList, startPosition, shuffle, fullScreen, playbackPageType, controlSource, additionalMediaCollectionId, isRefinementFilterApplied, augmentations, playbackListener, noOpCloudQueueClientHandler, isCustomerSelectedEntity, isUserInitiated, (PlayQueueInteractionPattern) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StartPlaybackUtil.m1485startCollectionPlayback$lambda3(contentUri, playbackListener, context, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startCollectionPlayback$lambda-2, reason: not valid java name */
    public static final void m1484startCollectionPlayback$lambda2(Uri contentUri, String str, Context context, String str2, List list, int i, boolean z, boolean z2, PlaybackPageType playbackPageType, ControlSource controlSource, MediaCollectionId mediaCollectionId, boolean z3, List list2, PlaybackListener playbackListener, NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, boolean z4, boolean z5, PlayQueueInteractionPattern queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
        Intrinsics.checkNotNullParameter(queueInteractionPattern, "queueInteractionPattern");
        Log.debug(TAG, "startCollectionPlayback for contentUri: " + contentUri + " with interaction=" + queueInteractionPattern + ", sortOrder=" + ((Object) str));
        int i2 = WhenMappings.$EnumSwitchMapping$0[queueInteractionPattern.ordinal()];
        if (i2 == 1) {
            StartCollectionPlayback.startOnlineInteractionPlayback(context, contentUri, str2, list, i, z, z2, playbackPageType, controlSource, mediaCollectionId, z3, list2, playbackListener, noOpCloudQueueClientHandler, z4, z5);
        } else {
            if (i2 != 2) {
                return;
            }
            StartCollectionPlayback.startLocalInteractionPlayback(context, contentUri, str2, list, i, z, z2, str, playbackPageType, controlSource, mediaCollectionId, z4, z3, playbackListener, noOpCloudQueueClientHandler, z5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startCollectionPlayback$lambda-3, reason: not valid java name */
    public static final void m1485startCollectionPlayback$lambda3(Uri contentUri, PlaybackListener playbackListener, Context context, Throwable throwable) {
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Log.error(TAG, Intrinsics.stringPlus("startCollectionPlayback: Unrecognized Play Queue Interaction Pattern, unable to start playback of ", contentUri), throwable);
        if (playbackListener != null) {
            playbackListener.onPlaybackFailure(PlaybackError.NULL_PLAY_QUEUE_INTERACTION_PATTERN, new Exception(throwable));
        }
        INSTANCE.displayPlaybackError(context, PlaybackError.INITIATE_FAILURE.name(), "Unrecognized Play Queue Interaction Pattern, unable to start playback of " + contentUri + " : " + ((Object) throwable.getMessage()), null, PlaybackExceptions.Exceptions.CQ_INTERACTION_PATTERN);
    }

    @JvmStatic
    public static final void startStationPlayback(final Context context, String queueTitle, StationItem stationItem, Uri contentUri, boolean fullScreen, PlaybackPageType playbackPageType, ControlSource controlSource, MediaCollectionId additionalMediaCollectionId, UpsellInformation upsellInformation, PlaybackListener playbackListener, boolean isUserInitiated, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stationItem, "stationItem");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        Log.debug(TAG, "startStationPlayback for station " + ((Object) stationItem.getTitle()) + " with identifier " + ((Object) stationItem.getIdentifier()));
        StartStationPlayback startStationPlayback = StartStationPlayback.INSTANCE;
        StartStationPlayback.startOnlineInteractionPlayback(context, queueTitle, stationItem, contentUri, fullScreen, playbackPageType, controlSource, upsellInformation, additionalMediaCollectionId, playbackListener, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil.startStationPlayback.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                if (!UserSubscriptionUtil.isHawkfire() && UserSubscriptionUtil.getUserSubscription().isSonicRush()) {
                    StartPlaybackUtil.INSTANCE.displayPlaybackError(context, PlaybackError.CLOUDQUEUE_APP_ERROR.name(), "Cannot create CQ playback", null, PlaybackExceptions.Exceptions.NO_MPQS_LEGACY_SUPPORT);
                } else {
                    noOpCloudQueueClientHandler.handlePlayback();
                }
            }
        }, isUserInitiated);
    }

    @SuppressLint({"CheckResult"})
    public final void startCQPlayback(final Context context, final String queueId, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, final boolean startFullScreen, final boolean isUserInitiated) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribe(new io.reactivex.functions.Consumer() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartPlaybackUtil.m1482startCQPlayback$lambda4(context, controlSource, queueId, startFullScreen, additionalMediaCollectionId, isUserInitiated, (CloudQueueAppClient) obj);
            }
        }, new io.reactivex.functions.Consumer() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartPlaybackUtil.m1483startCQPlayback$lambda5(context, controlSource, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startCQPlayback$lambda-4, reason: not valid java name */
    public static final void m1482startCQPlayback$lambda4(Context context, ControlSource controlSource, String queueId, boolean z, MediaCollectionId mediaCollectionId, boolean z2, CloudQueueAppClient cloudQueueAppClient) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        Intrinsics.checkNotNullParameter(queueId, "$queueId");
        if (cloudQueueAppClient instanceof NoOpCloudQueueAppClient) {
            Log.error(TAG, "startCQPlayback : getCloudQueueAppClient returned a no-op client");
            INSTANCE.displayPlaybackError(context, CloudQueueFlexMetricsManager.Event.NO_OP_CQ_CLIENT_VOICE.getValue(), "startCQPlayback : getCloudQueueAppClient returned a no-op client", controlSource.getName(), PlaybackExceptions.Exceptions.PLAYBACK_EXCEPTION);
        } else {
            CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(cloudQueueAppClient, "cloudQueueAppClient");
            CloudQueuePlaybackUtil.Companion.startOnlineQueueInteraction$default(companion, null, cloudQueueAppClient, context, CloudQueueDigitalXPUtil.getQueueSeedContextInput(context, queueId), PlaybackPageType.EXTERNAL, null, controlSource, null, z, mediaCollectionId, null, z2, null, null, null, 28705, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startCQPlayback$lambda-5, reason: not valid java name */
    public static final void m1483startCQPlayback$lambda5(Context context, ControlSource controlSource, Throwable th) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        Log.error(TAG, "startCQPlayback : failed to start cloud queue app", th);
        INSTANCE.displayPlaybackError(context, PlaybackError.CLOUDQUEUE_APP_ERROR.name(), "startCQPlayback : failed to start cloud queue app", controlSource.getName(), PlaybackExceptions.Exceptions.PLAYBACK_EXCEPTION);
    }

    private final PlayQueueInteractionPattern getQueueInteractionPatternTrackList(Context context, Uri contentUri, List<? extends EntityEligibilitiesProvider> trackList, int startPosition) {
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b = 0;
        if (trackList.size() > startPosition) {
            EntityEligibilitiesProvider entityEligibilitiesProvider = trackList.get(startPosition);
            if ((entityEligibilitiesProvider instanceof MusicTrack) && ((MusicTrack) entityEligibilitiesProvider).isVideo()) {
                return PlayQueueInteractionPattern.LOCAL;
            }
            if ((entityEligibilitiesProvider instanceof PrimeTrack) && ((PrimeTrack) entityEligibilitiesProvider).isVideo()) {
                return PlayQueueInteractionPattern.LOCAL;
            }
            return new EligibilityUtil(cachedCapabilityActionProviderImpl, 1, b == true ? 1 : 0).getPlayQueueInteractionPattern(context, contentUri, entityEligibilitiesProvider);
        }
        Log.warning(TAG, "Cannot retrieve selected track, queue interaction pattern = null");
        return null;
    }

    private final Single<PlayQueueInteractionPattern> getQueueInteractionPattern(final Context context, final Uri contentUri, final List<? extends EntityEligibilitiesProvider> trackList, final int startPosition, final List<? extends PlaybackAugmentation> augmentations) {
        Single<PlayQueueInteractionPattern> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                StartPlaybackUtil.m1480getQueueInteractionPattern$lambda7(augmentations, trackList, context, contentUri, startPosition, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …          }\n            }");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueInteractionPattern$lambda-7, reason: not valid java name */
    public static final void m1480getQueueInteractionPattern$lambda7(List list, List list2, Context context, Uri contentUri, int i, SingleEmitter singleEmitter) {
        PlayQueueInteractionPattern playQueueInteractionPattern;
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        StartPlaybackUtil startPlaybackUtil = INSTANCE;
        PlayQueueInteractionPattern queueInteractionPattern = startPlaybackUtil.getQueueInteractionPattern(list);
        if (queueInteractionPattern != null) {
            singleEmitter.onSuccess(queueInteractionPattern);
        }
        if (list2 != null) {
            playQueueInteractionPattern = startPlaybackUtil.getQueueInteractionPatternTrackList(context, contentUri, list2, i);
        } else {
            playQueueInteractionPattern = new EligibilityUtil(null, 1, 0 == true ? 1 : 0).getPlayQueueInteractionPattern(contentUri, context);
        }
        if (playQueueInteractionPattern != null) {
            singleEmitter.onSuccess(playQueueInteractionPattern);
        } else {
            singleEmitter.onError(new Throwable("Play Queue Interaction Pattern is null"));
        }
    }

    private final Single<PlayQueueInteractionPattern> getQueueInteractionPattern(final Context context, final Uri contentUri, final List<? extends PlaybackAugmentation> augmentations) {
        Single<PlayQueueInteractionPattern> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                StartPlaybackUtil.m1481getQueueInteractionPattern$lambda9(augmentations, contentUri, context, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …)\n            }\n        }");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueInteractionPattern$lambda-9, reason: not valid java name */
    public static final void m1481getQueueInteractionPattern$lambda9(List list, Uri contentUri, Context context, SingleEmitter singleEmitter) {
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        PlayQueueInteractionPattern queueInteractionPattern = INSTANCE.getQueueInteractionPattern(list);
        if (queueInteractionPattern != null) {
            singleEmitter.onSuccess(queueInteractionPattern);
        }
        PlayQueueInteractionPattern playQueueInteractionPattern = new EligibilityUtil(null, 1, 0 == true ? 1 : 0).getPlayQueueInteractionPattern(contentUri, context);
        if (playQueueInteractionPattern != null) {
            singleEmitter.onSuccess(playQueueInteractionPattern);
        } else {
            singleEmitter.onError(new Throwable("Play Queue Interaction Pattern is null"));
        }
    }

    private final PlayQueueInteractionPattern getQueueInteractionPattern(List<? extends PlaybackAugmentation> augmentations) {
        PlayQueueInteractionPattern playQueueInteractionPattern = null;
        if (augmentations == null || augmentations.isEmpty()) {
            return null;
        }
        boolean zHasAnyInternetConnection = true ^ ConnectivityUtil.hasAnyInternetConnection(AmazonApplication.getApplication().getApplicationContext());
        ArrayList arrayList = new ArrayList();
        for (PlaybackAugmentation playbackAugmentation : augmentations) {
            InteractionPatternAugmentation interactionPatternAugmentation = playbackAugmentation instanceof InteractionPatternAugmentation ? (InteractionPatternAugmentation) playbackAugmentation : null;
            if (interactionPatternAugmentation != null) {
                arrayList.add(interactionPatternAugmentation);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PlayQueueInteractionPattern playQueueInteractionPatternRetrieveInteractionPattern = ((InteractionPatternAugmentation) it.next()).retrieveInteractionPattern(zHasAnyInternetConnection);
            if (playQueueInteractionPatternRetrieveInteractionPattern != null) {
                playQueueInteractionPattern = playQueueInteractionPatternRetrieveInteractionPattern;
                break;
            }
        }
        if (playQueueInteractionPattern != null) {
            return playQueueInteractionPattern;
        }
        throw new NoSuchElementException("No element of the collection was transformed to a non-null value.");
    }

    public final void displayPlaybackError(Context context, String tag, String msg, String extraMsg, PlaybackExceptions.Exceptions exception) {
        Object next;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Iterator<T> it = PlaybackExceptions.INSTANCE.getExceptionList().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((ExceptionData) next).getException() == exception.getValue()) {
                    break;
                }
            }
        }
        ExceptionData exceptionData = (ExceptionData) next;
        if (exceptionData == null) {
            return;
        }
        if (exceptionData.isEnabled().invoke().booleanValue()) {
            CloudQueueFlexMetricsManager.trackEventV2$default(exceptionData.getEvent(), tag, msg, extraMsg, false, false, 32, null);
        }
        StartPlaybackUtil startPlaybackUtil = INSTANCE;
        startPlaybackUtil.displayError(context, new StartPlaybackUtil$displayPlaybackError$1$1(startPlaybackUtil), exception);
    }

    private final void displayError(Context context, Function1<? super Context, Unit> displayDialog) {
        if ((context instanceof Activity) && ActivityKt.isAvailable((Activity) context)) {
            displayDialog.invoke(context);
            return;
        }
        Activity activity = AndroidUtils.getCurrentActivity();
        if (ActivityKt.isAvailable(activity)) {
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            displayDialog.invoke(activity);
        }
    }

    private final void displayError(Context context, Function2<? super Context, ? super PlaybackExceptions.Exceptions, Unit> displayDialog, PlaybackExceptions.Exceptions exception) {
        if ((context instanceof Activity) && ActivityKt.isAvailable((Activity) context)) {
            displayDialog.mo4invoke(context, exception);
            return;
        }
        Activity activity = AndroidUtils.getCurrentActivity();
        if (ActivityKt.isAvailable(activity)) {
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            displayDialog.mo4invoke(activity, exception);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayPlaybackDialogHelper(final Context context, final PlaybackExceptions.Exceptions exception) {
        ContextKt.runOnUiThread$default(context, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil.displayPlaybackDialogHelper.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                invoke2(context2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(context);
                int messageResourceId = PlaybackExceptions.INSTANCE.getMessageResourceId(exception.getValue());
                bauhausDialogBuilder.setTitle(R.string.dmusic_playback_error_media_player_notification_header);
                bauhausDialogBuilder.setMessage(messageResourceId);
                bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
                bauhausDialogBuilder.create().show();
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayNoConnectionErrorHelper(final Context context) {
        ContextKt.runOnUiThread$default(context, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.playback.start.StartPlaybackUtil.displayNoConnectionErrorHelper.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                invoke2(context2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ConnectivityUtil.checkConnectivityAndShowDialog(context);
            }
        }, 1, null);
    }
}
