package com.amazon.mp3.playback.start;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.types.StartAt;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackExceptions;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: StartCollectionPlayback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000fH\u0002J,\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J<\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\rH\u0002J¢\u0001\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\rH\u0007J¨\u0001\u0010-\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010\u001b\u001a\u00020\r2\u0006\u0010,\u001a\u00020\rH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/amazon/mp3/playback/start/StartCollectionPlayback;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getQueueSeedContextInput", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "context", "Landroid/content/Context;", "contentUri", "Landroid/net/Uri;", "isRefinementFilterApplied", "", "augmentations", "", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "trackList", "getQueueSeedContextInputByTrackList", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "collectionEntity", "Lcom/amazon/mp3/library/item/AbstractItem;", "getUpsellInformation", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "startPosition", "", "isCustomerSelectedEntity", "startLocalInteractionPlayback", "", "queueTitle", "shuffle", "fullScreen", "sortOrder", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "controlSource", "Lcom/amazon/music/media/playback/ControlSource;", "additionalMediaCollectionId", "Lcom/amazon/music/media/playback/MediaCollectionId;", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "noOpCloudQueueClientHandler", "Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", "isUserInitiated", "startOnlineInteractionPlayback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StartCollectionPlayback {
    public static final StartCollectionPlayback INSTANCE = new StartCollectionPlayback();
    private static final String TAG = StartCollectionPlayback.class.getSimpleName();

    private StartCollectionPlayback() {
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [T, com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation] */
    @JvmStatic
    @SuppressLint({"CheckResult"})
    public static final void startOnlineInteractionPlayback(final Context context, final Uri contentUri, final String queueTitle, List<? extends Object> trackList, int startPosition, final boolean shuffle, final boolean fullScreen, final PlaybackPageType playbackPageType, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, boolean isRefinementFilterApplied, List<? extends PlaybackAugmentation> augmentations, final PlaybackListener playbackListener, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, boolean isCustomerSelectedEntity, final boolean isUserInitiated) {
        final StartAt startAtWithEntityAsin;
        final PlaybackListener playbackListener2;
        Object orNull;
        String asin;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        StartCollectionPlayback startCollectionPlayback = INSTANCE;
        final QueueSeedContextInput queueSeedContextInput = startCollectionPlayback.getQueueSeedContextInput(context, contentUri, isRefinementFilterApplied, augmentations, trackList);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = startCollectionPlayback.getUpsellInformation(trackList, startPosition, context, contentUri, isCustomerSelectedEntity);
        Disposable disposableSubscribe = null;
        if (queueSeedContextInput == null) {
            playbackListener2 = playbackListener;
        } else {
            Log.debug(TAG, Intrinsics.stringPlus("queueSeedContextInput play mode = ", queueSeedContextInput.getPlayMode()));
            final boolean z = queueSeedContextInput.getPlayMode() == PlayModeEnum.ON_DEMAND;
            if (!z || !isCustomerSelectedEntity || trackList == null || (orNull = CollectionsKt___CollectionsKt.getOrNull(trackList, startPosition)) == null) {
                startAtWithEntityAsin = null;
            } else {
                if (orNull instanceof MusicTrack) {
                    asin = ((MusicTrack) orNull).getAsin();
                } else {
                    asin = orNull instanceof PrimeTrack ? ((PrimeTrack) orNull).getAsin() : null;
                }
                startAtWithEntityAsin = asin == null ? null : CloudQueueDigitalXPUtil.getStartAtWithEntityAsin(asin, 0);
            }
            Observable<CloudQueueAppClient> observableSubscribeOn = CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribeOn(Schedulers.io());
            Consumer<? super CloudQueueAppClient> consumer = new Consumer() { // from class: com.amazon.mp3.playback.start.StartCollectionPlayback$$ExternalSyntheticLambda0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    StartCollectionPlayback.m1474startOnlineInteractionPlayback$lambda4$lambda2(z, noOpCloudQueueClientHandler, queueSeedContextInput, context, objectRef, shuffle, queueTitle, queueSeedContextInput, playbackPageType, playbackListener, controlSource, contentUri, fullScreen, additionalMediaCollectionId, isUserInitiated, startAtWithEntityAsin, (CloudQueueAppClient) obj);
                }
            };
            playbackListener2 = playbackListener;
            disposableSubscribe = observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: com.amazon.mp3.playback.start.StartCollectionPlayback$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    StartCollectionPlayback.m1475startOnlineInteractionPlayback$lambda4$lambda3(playbackListener2, (Throwable) obj);
                }
            });
        }
        if (disposableSubscribe == null) {
            Log.error(TAG, "queue seed is null, unable to start cloud queue online playback");
            if (playbackListener2 == null) {
                return;
            }
            PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener2, PlaybackError.NULL_QUEUE_SEED_CONTEXT_INPUT, null, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: startOnlineInteractionPlayback$lambda-4$lambda-2, reason: not valid java name */
    public static final void m1474startOnlineInteractionPlayback$lambda4$lambda2(boolean z, NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, QueueSeedContextInput queueSeed, Context context, Ref.ObjectRef upsellInformation, boolean z2, String str, QueueSeedContextInput queueSeedContextInput, PlaybackPageType playbackPageType, PlaybackListener playbackListener, ControlSource controlSource, Uri contentUri, boolean z3, MediaCollectionId mediaCollectionId, boolean z4, StartAt startAt, CloudQueueAppClient cloudQueueAppClient) {
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
        Intrinsics.checkNotNullParameter(queueSeed, "$queueSeed");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(upsellInformation, "$upsellInformation");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        if (cloudQueueAppClient instanceof NoOpCloudQueueAppClient) {
            String str2 = TAG;
            Log.warning(str2, "unable to retrieve cloud queue app client, using NoOpCloudQueueAppClient");
            if (z) {
                noOpCloudQueueClientHandler.handlePlayback();
                return;
            }
            Log.error(str2, "Cannot play " + queueSeed.getPlayMode() + " content without using Cloud Queue");
            StartPlaybackUtil.INSTANCE.displayPlaybackError(context, PlaybackError.CLOUDQUEUE_APP_ERROR.name(), "Cannot play " + queueSeed.getPlayMode() + " content without using Cloud Queue", null, PlaybackExceptions.Exceptions.PLAYBACK_EXCEPTION);
            return;
        }
        CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
        UpsellInformation upsellInformation2 = (UpsellInformation) upsellInformation.element;
        PlaybackOptionsInput playBackOptions$default = CloudQueueDigitalXPUtil.getPlayBackOptions$default(queueSeed.getPlayMode(), z2, null, 4, null);
        Intrinsics.checkNotNullExpressionValue(cloudQueueAppClient, "cloudQueueAppClient");
        CloudQueuePlaybackUtil.Companion.startOnlineQueueInteraction$default(companion, str, cloudQueueAppClient, context, queueSeedContextInput, playbackPageType, playbackListener, controlSource, contentUri, z3, mediaCollectionId, upsellInformation2, z4, startAt, playBackOptions$default, null, 16384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startOnlineInteractionPlayback$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1475startOnlineInteractionPlayback$lambda4$lambda3(PlaybackListener playbackListener, Throwable th) {
        Log.error(TAG, "failed to start cloud queue app", th);
        if (playbackListener == null) {
            return;
        }
        playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
    }

    @JvmStatic
    @SuppressLint({"CheckResult", "VisibleForTests"})
    public static final void startLocalInteractionPlayback(final Context context, final Uri contentUri, final String queueTitle, final List<? extends Object> trackList, final int startPosition, final boolean shuffle, final boolean fullScreen, final String sortOrder, final PlaybackPageType playbackPageType, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, final boolean isCustomerSelectedEntity, final boolean isRefinementFilterApplied, final PlaybackListener playbackListener, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, final boolean isUserInitiated) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.start.StartCollectionPlayback$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartCollectionPlayback.m1472startLocalInteractionPlayback$lambda6(noOpCloudQueueClientHandler, isRefinementFilterApplied, trackList, context, contentUri, queueTitle, startPosition, shuffle, playbackPageType, playbackListener, isCustomerSelectedEntity, controlSource, fullScreen, additionalMediaCollectionId, isUserInitiated, sortOrder, (CloudQueueClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.start.StartCollectionPlayback$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartCollectionPlayback.m1473startLocalInteractionPlayback$lambda7(playbackListener, context, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076  */
    /* JADX INFO: renamed from: startLocalInteractionPlayback$lambda-6, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1472startLocalInteractionPlayback$lambda6(com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler r17, boolean r18, java.util.List r19, android.content.Context r20, android.net.Uri r21, java.lang.String r22, int r23, boolean r24, com.amazon.music.metrics.mts.event.types.PlaybackPageType r25, com.amazon.mp3.playback.PlaybackListener r26, boolean r27, com.amazon.music.media.playback.ControlSource r28, boolean r29, com.amazon.music.media.playback.MediaCollectionId r30, boolean r31, java.lang.String r32, com.amazon.digitalmusicxp.clients.CloudQueueClient r33) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.start.StartCollectionPlayback.m1472startLocalInteractionPlayback$lambda6(com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler, boolean, java.util.List, android.content.Context, android.net.Uri, java.lang.String, int, boolean, com.amazon.music.metrics.mts.event.types.PlaybackPageType, com.amazon.mp3.playback.PlaybackListener, boolean, com.amazon.music.media.playback.ControlSource, boolean, com.amazon.music.media.playback.MediaCollectionId, boolean, java.lang.String, com.amazon.digitalmusicxp.clients.CloudQueueClient):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startLocalInteractionPlayback$lambda-7, reason: not valid java name */
    public static final void m1473startLocalInteractionPlayback$lambda7(PlaybackListener playbackListener, Context context, Throwable th) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Log.error(TAG, "failed to start cloud queue", th);
        if (playbackListener != null) {
            playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
        }
        StartPlaybackUtil.INSTANCE.displayPlaybackError(context, PlaybackError.CLOUDQUEUE_APP_ERROR.name(), Intrinsics.stringPlus("failed to start cloud queue ", th.getMessage()), null, PlaybackExceptions.Exceptions.PLAYBACK_EXCEPTION);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.digitalmusicxp.inputs.QueueSeedContextInput getQueueSeedContextInput(android.content.Context r3, android.net.Uri r4, boolean r5, java.util.List<? extends com.amazon.music.platform.playback.augmentation.PlaybackAugmentation> r6, java.util.List<? extends java.lang.Object> r7) {
        /*
            r2 = this;
            com.amazon.mp3.playback.state.ContentPlaybackUtils r0 = com.amazon.mp3.playback.state.ContentPlaybackUtils.INSTANCE
            android.net.Uri r4 = r0.removeTracksFromUriIfNeeded(r4)
            com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory r0 = new com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory
            r0.<init>(r3)
            com.amazon.mp3.library.item.AbstractItem r3 = r0.getItem(r4)
            r4 = 1
            r0 = 0
            if (r3 != 0) goto L15
            r1 = r0
            goto L1e
        L15:
            com.amazon.mp3.util.EligibilityUtil r1 = new com.amazon.mp3.util.EligibilityUtil
            r1.<init>(r0, r4, r0)
            com.amazon.digitalmusicxp.enums.PlayModeEnum r1 = r1.getPlayMode(r3)
        L1e:
            if (r1 == 0) goto L40
            if (r5 == 0) goto L33
            if (r7 == 0) goto L2c
            boolean r5 = r7.isEmpty()
            if (r5 == 0) goto L2b
            goto L2c
        L2b:
            r4 = 0
        L2c:
            if (r4 != 0) goto L33
            com.amazon.digitalmusicxp.inputs.QueueSeedContextInput r3 = r2.getQueueSeedContextInputByTrackList(r7, r1, r3)
            goto L48
        L33:
            com.amazon.digitalmusicxp.inputs.QueueSeedContextInput r4 = com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil.getQueueSeedContextInputByAbstractItem(r3, r1)
            if (r4 != 0) goto L3e
            com.amazon.digitalmusicxp.inputs.QueueSeedContextInput r3 = r2.getQueueSeedContextInputByTrackList(r7, r1, r3)
            goto L48
        L3e:
            r3 = r4
            goto L48
        L40:
            java.lang.String r3 = com.amazon.mp3.playback.start.StartCollectionPlayback.TAG
            java.lang.String r4 = "Play mode is null, unable to generate queue seed context input"
            com.amazon.mp3.util.Log.warning(r3, r4)
            r3 = r0
        L48:
            if (r6 != 0) goto L4b
            goto L70
        L4b:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r5 = r6.iterator()
        L54:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L6f
            java.lang.Object r6 = r5.next()
            com.amazon.music.platform.playback.augmentation.PlaybackAugmentation r6 = (com.amazon.music.platform.playback.augmentation.PlaybackAugmentation) r6
            boolean r7 = r6 instanceof com.amazon.music.platform.playback.augmentation.SeedContextAugmentation
            if (r7 == 0) goto L67
            com.amazon.music.platform.playback.augmentation.SeedContextAugmentation r6 = (com.amazon.music.platform.playback.augmentation.SeedContextAugmentation) r6
            goto L68
        L67:
            r6 = r0
        L68:
            if (r6 != 0) goto L6b
            goto L54
        L6b:
            r4.add(r6)
            goto L54
        L6f:
            r0 = r4
        L70:
            if (r0 != 0) goto L76
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L76:
            com.amazon.digitalmusicxp.inputs.QueueSeedContextInput r4 = com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil.createOnlineQueueSeedWithSeedContext(r3, r0)
            if (r4 != 0) goto L7d
            goto L7e
        L7d:
            r3 = r4
        L7e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.start.StartCollectionPlayback.getQueueSeedContextInput(android.content.Context, android.net.Uri, boolean, java.util.List, java.util.List):com.amazon.digitalmusicxp.inputs.QueueSeedContextInput");
    }

    private final UpsellInformation getUpsellInformation(List<? extends Object> trackList, int startPosition, Context context, Uri contentUri, boolean isCustomerSelectedEntity) {
        int i = 1;
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        if (isCustomerSelectedEntity) {
            if (!(trackList == null || trackList.isEmpty()) && trackList.size() > startPosition) {
                Object obj = trackList.get(startPosition);
                if (obj instanceof EntityEligibilitiesProvider) {
                    return new EligibilityUtil(cachedCapabilityActionProviderImpl, i, b3 == true ? 1 : 0).getNonBlockingUpsell((EntityEligibilitiesProvider) obj, context);
                }
                Log.warning(TAG, "Unable to retrieve track from track list, cannot determine upsell information for online interaction.");
                return null;
            }
        }
        return new EligibilityUtil(b2 == true ? 1 : 0, i, b == true ? 1 : 0).getNonBlockingUpsell(contentUri, context);
    }

    private final QueueSeedContextInput getQueueSeedContextInputByTrackList(List<? extends Object> trackList, PlayModeEnum playMode, AbstractItem collectionEntity) {
        if (trackList == null || trackList.isEmpty()) {
            return null;
        }
        Object objFirstOrNull = CollectionsKt___CollectionsKt.firstOrNull(trackList);
        if (objFirstOrNull instanceof MusicTrack) {
            CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil = CloudQueueDigitalXPUtil.INSTANCE;
            if (trackList != null) {
                return CloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsin$default(cloudQueueDigitalXPUtil, trackList, null, playMode, collectionEntity, 2, null);
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<com.amazon.mp3.library.item.MusicTrack>");
        }
        if (!(objFirstOrNull instanceof PrimeTrack)) {
            return null;
        }
        CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil2 = CloudQueueDigitalXPUtil.INSTANCE;
        if (trackList != null) {
            return CloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsin$default(cloudQueueDigitalXPUtil2, null, trackList, playMode, collectionEntity, 1, null);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<com.amazon.mp3.prime.browse.metadata.PrimeTrack>");
    }
}
