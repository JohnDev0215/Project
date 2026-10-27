package com.amazon.mp3.playback.start;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.PlayableEntitySeedIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.inputs.PlayableEntitySeedContextInput;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.digitalmusicxp.types.StartAt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.augmentation.CloudQueueAugmentationKt;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.playback.PlaybackExperience;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import com.amazon.music.platform.playback.augmentation.SeedContextAugmentation;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartTracksPlayback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002JD\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000f0\t2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\tH\u0002J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J(\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u008a\u0001\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000f0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020-H\u0007J \u0001\u0010.\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000f0\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\t2\u0006\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020-H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/mp3/playback/start/StartTracksPlayback;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getPlayMode", "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "trackList", "", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "startPosition", "", "getQueueSeedContextInputForMusicTracks", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "Lcom/amazon/mp3/library/item/MusicTrack;", "collectionContentUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "augmentations", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "getQueueSeedContextInputTrackDetails", ContextMappingConstants.PLAY_MODE, "asin", "getUpsellInformation", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "startMusicTracksLocalInteractionPlayback", "", "contentUri", "queueTitle", "shuffle", "", "fullScreen", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "isCustomerSelectedEntity", "controlSource", "Lcom/amazon/music/media/playback/ControlSource;", "additionalMediaCollectionId", "Lcom/amazon/music/media/playback/MediaCollectionId;", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "isUserInitiated", "noOpCloudQueueClientHandler", "Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", "startMusicTracksOnlineInteractionPlayback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StartTracksPlayback {
    public static final StartTracksPlayback INSTANCE = new StartTracksPlayback();
    private static final String TAG = StartTracksPlayback.class.getSimpleName();

    private StartTracksPlayback() {
    }

    @JvmStatic
    @SuppressLint({"CheckResult"})
    public static final void startMusicTracksOnlineInteractionPlayback(final Context context, final Uri contentUri, final String queueTitle, final List<? extends MusicTrack> trackList, final int startPosition, final boolean shuffle, final boolean fullScreen, final PlaybackPageType playbackPageType, boolean isCustomerSelectedEntity, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, final PlaybackListener playbackListener, final List<? extends PlaybackAugmentation> augmentations, final boolean isUserInitiated, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.start.StartTracksPlayback$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartTracksPlayback.m1494startMusicTracksOnlineInteractionPlayback$lambda2(noOpCloudQueueClientHandler, playbackPageType, trackList, startPosition, contentUri, context, augmentations, shuffle, queueTitle, playbackListener, controlSource, fullScreen, additionalMediaCollectionId, isUserInitiated, (CloudQueueAppClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.start.StartTracksPlayback$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartTracksPlayback.m1495startMusicTracksOnlineInteractionPlayback$lambda3(playbackListener, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startMusicTracksOnlineInteractionPlayback$lambda-2, reason: not valid java name */
    public static final void m1494startMusicTracksOnlineInteractionPlayback$lambda2(NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, PlaybackPageType playbackPageType, List trackList, int i, Uri contentUri, Context context, List list, boolean z, String str, PlaybackListener playbackListener, ControlSource controlSource, boolean z2, MediaCollectionId mediaCollectionId, boolean z3, CloudQueueAppClient cloudQueueAppClient) {
        QueueSeedContextInput queueSeedContextInputForMusicTracks;
        String asin;
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        Intrinsics.checkNotNullParameter(trackList, "$trackList");
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        if (cloudQueueAppClient instanceof NoOpCloudQueueAppClient) {
            Log.warning(TAG, "Unable to retrieve cloud queue client, use NoOpCloudQueueClientHandler");
            noOpCloudQueueClientHandler.handlePlayback();
            return;
        }
        if (playbackPageType == PlaybackPageType.TRACK_DETAIL && FMPMFeatureGating.SHOW_TRACK_DETAILS_ARTIST_AS.isEnabled()) {
            StartTracksPlayback startTracksPlayback = INSTANCE;
            PlayModeEnum playModeEnum = PlayModeEnum.AUGMENTED;
            String artistAsin = ((MusicTrack) trackList.get(i)).getArtistAsin();
            Intrinsics.checkNotNullExpressionValue(artistAsin, "trackList[startPosition].artistAsin");
            queueSeedContextInputForMusicTracks = startTracksPlayback.getQueueSeedContextInputTrackDetails(playModeEnum, artistAsin);
        } else {
            queueSeedContextInputForMusicTracks = INSTANCE.getQueueSeedContextInputForMusicTracks(trackList, i, contentUri, context, list);
        }
        QueueSeedContextInput queueSeedContextInput = queueSeedContextInputForMusicTracks;
        UpsellInformation upsellInformation = INSTANCE.getUpsellInformation(trackList, i, context);
        if (upsellInformation != null && UpsellManager.getInstance().isEligibleForUpsellBeforePlayback(upsellInformation)) {
            UpsellManager.getInstance().showUpsellBeforePlayback(context, playbackPageType, upsellInformation, Trigger.TRACK_SHUFFLE_PLAYBACK_UPSELL);
            return;
        }
        StartAt startAtWithEntityAsin = (!((queueSeedContextInput == null ? null : queueSeedContextInput.getPlayMode()) == PlayModeEnum.ON_DEMAND) || (asin = ((MusicTrack) trackList.get(i)).getAsin()) == null) ? null : CloudQueueDigitalXPUtil.getStartAtWithEntityAsin(asin, 0);
        if (queueSeedContextInput != null) {
            PlayModeEnum playMode = queueSeedContextInput.getPlayMode();
            Log.debug(TAG, Intrinsics.stringPlus("queueSeedContextInput play mode = ", playMode));
            PlaybackOptionsInput playBackOptions$default = CloudQueueDigitalXPUtil.getPlayBackOptions$default(playMode, z, null, 4, null);
            CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(cloudQueueAppClient, "cloudQueueAppClient");
            CloudQueuePlaybackUtil.Companion.startOnlineQueueInteraction$default(companion, str, cloudQueueAppClient, context, queueSeedContextInput, playbackPageType, playbackListener, controlSource, contentUri, z2, mediaCollectionId, upsellInformation, z3, startAtWithEntityAsin, playBackOptions$default, null, 16384, null);
            return;
        }
        Log.error(TAG, "Cannot generate QueueSeedContextInput, unable to start online playback for track list");
        if (playbackListener == null) {
            return;
        }
        PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.NULL_QUEUE_SEED_CONTEXT_INPUT, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startMusicTracksOnlineInteractionPlayback$lambda-3, reason: not valid java name */
    public static final void m1495startMusicTracksOnlineInteractionPlayback$lambda3(PlaybackListener playbackListener, Throwable th) {
        Log.error(TAG, "failed to start cloud queue", th);
        if (playbackListener == null) {
            return;
        }
        playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
    }

    @JvmStatic
    @SuppressLint({"CheckResult", "VisibleForTests"})
    public static final void startMusicTracksLocalInteractionPlayback(final Context context, final Uri contentUri, final String queueTitle, final List<? extends MusicTrack> trackList, final int startPosition, final boolean shuffle, final boolean fullScreen, final PlaybackPageType playbackPageType, final boolean isCustomerSelectedEntity, final ControlSource controlSource, final MediaCollectionId additionalMediaCollectionId, final PlaybackListener playbackListener, final boolean isUserInitiated, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
        CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.start.StartTracksPlayback$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartTracksPlayback.m1492startMusicTracksLocalInteractionPlayback$lambda5(noOpCloudQueueClientHandler, trackList, context, queueTitle, startPosition, shuffle, contentUri, playbackPageType, playbackListener, isCustomerSelectedEntity, controlSource, fullScreen, additionalMediaCollectionId, isUserInitiated, (CloudQueueClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.start.StartTracksPlayback$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                StartTracksPlayback.m1493startMusicTracksLocalInteractionPlayback$lambda6(playbackListener, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startMusicTracksLocalInteractionPlayback$lambda-5, reason: not valid java name */
    public static final void m1492startMusicTracksLocalInteractionPlayback$lambda5(NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, List trackList, Context context, String str, int i, boolean z, Uri contentUri, PlaybackPageType playbackPageType, PlaybackListener playbackListener, boolean z2, ControlSource controlSource, boolean z3, MediaCollectionId mediaCollectionId, boolean z4, CloudQueueClient cloudQueueClient) {
        Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
        Intrinsics.checkNotNullParameter(trackList, "$trackList");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(contentUri, "$contentUri");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
        if ((cloudQueueClient instanceof NoOpCloudQueueClient) || !Feature.cloud_queue_content.isEnabled()) {
            noOpCloudQueueClientHandler.handlePlayback();
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = trackList.iterator();
        while (it.hasNext()) {
            String asin = ((MusicTrack) it.next()).getAsin();
            if (asin != null) {
                arrayList.add(asin);
            }
        }
        QueueSeed queueSeedByIds = CloudQueueDigitalXPUtil.getQueueSeedByIds(arrayList);
        CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
        String customerId = AccountDetailUtil.get(context).getCustomerId();
        String str2 = str == null ? "" : str;
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(cloudQueueClient, "cloudQueueClient");
        companion.startLocalQueueInteraction(customerId, str2, cloudQueueClient, context, trackList, i, z, queueSeedByIds, contentUri, playbackPageType, playbackListener, z2, controlSource, z3, mediaCollectionId, z4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startMusicTracksLocalInteractionPlayback$lambda-6, reason: not valid java name */
    public static final void m1493startMusicTracksLocalInteractionPlayback$lambda6(PlaybackListener playbackListener, Throwable th) {
        Log.error(TAG, "failed to start cloud queue", th);
        if (playbackListener == null) {
            return;
        }
        playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
    }

    private final QueueSeedContextInput getQueueSeedContextInputForMusicTracks(List<? extends MusicTrack> trackList, int startPosition, Uri collectionContentUri, Context context, List<? extends PlaybackAugmentation> augmentations) {
        PlayModeEnum playMode;
        QueueSeedContextInput queueSeedContextInputsByAsin$default;
        List arrayList;
        EligibilityUtil eligibilityUtil = new EligibilityUtil(null, 1, 0 == true ? 1 : 0);
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        PlaybackExperience playbackExperience = eligibilityUtil.getPlaybackExperience(collectionContentUri, applicationContext);
        if (playbackExperience == PlaybackExperience.AUGMENTED_SHUFFLE) {
            AbstractItem collectionItem = new CirrusSourceLibraryItemFactory(context).getCollectionItem(collectionContentUri);
            PlayModeEnum playModeEnum = PlayModeEnum.AUGMENTED;
            QueueSeedContextInput queueSeedContextInputByAbstractItem = CloudQueueDigitalXPUtil.getQueueSeedContextInputByAbstractItem(collectionItem, playModeEnum);
            return queueSeedContextInputByAbstractItem == null ? CloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsin$default(CloudQueueDigitalXPUtil.INSTANCE, trackList, null, playModeEnum, null, 10, null) : queueSeedContextInputByAbstractItem;
        }
        if (playbackExperience == PlaybackExperience.ON_DEMAND) {
            playMode = PlayModeEnum.ON_DEMAND;
        } else {
            playMode = getPlayMode(trackList, startPosition);
        }
        PlayModeEnum playModeEnum2 = playMode;
        if (playModeEnum2 != null) {
            queueSeedContextInputsByAsin$default = CloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsin$default(CloudQueueDigitalXPUtil.INSTANCE, trackList, null, playModeEnum2, null, 10, null);
        } else {
            Log.warning(TAG, "Play mode is null, unable to create QueueSeedContextInput for tracks playback");
            queueSeedContextInputsByAsin$default = null;
        }
        if (augmentations == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (PlaybackAugmentation playbackAugmentation : augmentations) {
                SeedContextAugmentation seedContextAugmentation = playbackAugmentation instanceof SeedContextAugmentation ? (SeedContextAugmentation) playbackAugmentation : null;
                if (seedContextAugmentation != null) {
                    arrayList.add(seedContextAugmentation);
                }
            }
        }
        if (arrayList == null) {
            arrayList = CollectionsKt__CollectionsKt.emptyList();
        }
        if (queueSeedContextInputsByAsin$default == null) {
            return null;
        }
        return CloudQueueAugmentationKt.augmentSeedContextWith(queueSeedContextInputsByAsin$default, arrayList);
    }

    private final PlayModeEnum getPlayMode(List<? extends EntityEligibilitiesProvider> trackList, int startPosition) {
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b = 0;
        if (trackList.size() > startPosition) {
            return new EligibilityUtil(cachedCapabilityActionProviderImpl, 1, b == true ? 1 : 0).getPlayMode(trackList.get(startPosition));
        }
        Log.warning(TAG, "Unable to retrieve track from track list, cannot determine play mode for online interaction.");
        return null;
    }

    private final QueueSeedContextInput getQueueSeedContextInputTrackDetails(PlayModeEnum playMode, String asin) {
        return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(asin, PlayableEntitySeedIdTypeEnum.ASIN, null, 4, null), null, null, null, null, null, null, null, null, null, null, null, 65516, null);
    }

    private final UpsellInformation getUpsellInformation(List<? extends EntityEligibilitiesProvider> trackList, int startPosition, Context context) {
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b = 0;
        if (trackList.size() > startPosition) {
            return new EligibilityUtil(cachedCapabilityActionProviderImpl, 1, b == true ? 1 : 0).getNonBlockingUpsell(trackList.get(startPosition), context);
        }
        Log.warning(TAG, "Unable to retrieve track from track list, cannot determine upsell information for online interaction.");
        return null;
    }
}
