package com.amazon.mp3.cloudqueue;

import android.content.Context;
import android.webkit.WebSettings;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.cloudqueue.CloudQueueCache;
import com.amazon.mp3.cloudqueue.model.AdEntity;
import com.amazon.mp3.cloudqueue.model.AdInformation;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.QueueMetadata;
import com.amazon.mp3.cloudqueue.model.ServiceTier;
import com.amazon.mp3.cloudqueue.model.SongEntity;
import com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.skiplimit.SkipLimitControllerProvider;
import com.amazon.mp3.util.AdUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.amazon.musicplayqueueservice.client.v2.common.SimplePlayableEntityIdentifier;
import com.amazon.musicplayqueueservice.client.v2.queue.PlayableEntity;
import com.amazon.musicplayqueueservice.client.v2.track.AdMetadata;
import com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase;
import com.amazon.musicplayqueueservice.client.v2.track.SongMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class QueueEntityUtil {
    private static CloudQueueCache.TerminateCloudQueueListener sTerminateCloudQueueListener = new CloudQueueCache.TerminateCloudQueueListener() { // from class: com.amazon.mp3.cloudqueue.QueueEntityUtil$$ExternalSyntheticLambda0
        @Override // com.amazon.mp3.cloudqueue.CloudQueueCache.TerminateCloudQueueListener
        public final void onCloudQueueTerminated() {
            QueueEntityUtil.lambda$static$0();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0() {
        SkipLimitControllerProvider.INSTANCE.getController().onQueueTerminate();
    }

    static List<SynchronizedPlayableEntity> createAll(final List<QueuedEntityMetadataBase> trackItems) {
        Validate.notNull(trackItems, "trackItems can't be null", new Object[0]);
        ArrayList arrayList = new ArrayList();
        Iterator<QueuedEntityMetadataBase> it = trackItems.iterator();
        while (it.hasNext()) {
            arrayList.add(convertMetaDataToEntity(it.next()));
        }
        return arrayList;
    }

    private static SynchronizedPlayableEntity convertMetaDataToEntity(QueuedEntityMetadataBase base) {
        if (base instanceof SongMetadata) {
            return new SongEntity((SongMetadata) base);
        }
        if (base instanceof AdMetadata) {
            return new AdEntity((AdMetadata) base);
        }
        return null;
    }

    static QueueMetadata convertQueueMetadata(com.amazon.musicplayqueueservice.client.v2.queue.QueueMetadata metadata) {
        return new QueueMetadata(ServiceTier.valueOf(metadata.getServiceTier()), metadata.getTitle(), metadata.getImageUrlMap(), metadata.getMaximumNumberOfTracksThatCanBeRequestedPerPage(), metadata.getMaximumTrackLookAhead(), metadata.getNumberOfTracksRemainingBeforeReplenishing());
    }

    public static ConnectionInfoMetadata convertConnectionInfoMetadata(GetConnectionInfoResponse response) {
        return new ConnectionInfoMetadata(response.getIotEndpoint(), response.getIdentityPoolId(), response.getClientId(), response.getRegion());
    }

    static List<PlayableEntity> convertPlayableEntityList(final List<PlayableEntityIdentifier> playableEntityIdentifierList) {
        ArrayList arrayList = new ArrayList();
        for (PlayableEntityIdentifier playableEntityIdentifier : playableEntityIdentifierList) {
            PlayableEntity playableEntity = new PlayableEntity();
            SimplePlayableEntityIdentifier simplePlayableEntityIdentifier = new SimplePlayableEntityIdentifier();
            simplePlayableEntityIdentifier.setIdentifier(playableEntityIdentifier.getIdentifier());
            simplePlayableEntityIdentifier.setIdentifierType(playableEntityIdentifier.getPlayableEntityIdentifierType().name());
            playableEntity.setId(simplePlayableEntityIdentifier);
            arrayList.add(playableEntity);
        }
        return arrayList;
    }

    static FreeTierSequencer createCloudQueueSequencer(final Context context, final PlaybackPageType playbackPageType, final boolean isOnDemandPlayMode, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final String pageToken, final MediaCollectionType collectionType, final boolean isVoiceInitiated, final boolean isShuffled, final String startAtAsin, @Nullable final List<MediaCollectionId> additionalMediaCollectionIds, @Nullable final String queueTitle) {
        Playback playback = Playback.getInstance();
        boolean zIsExplicitLanguageFilterEnabled = AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
        Marketplace homeMarketPlace = AccountDetailUtil.get(context).getHomeMarketPlace();
        CloudQueueFactory cloudQueueFactory = CloudQueueFactory.getInstance(context);
        new LikesServiceProxy(context);
        CloudQueue cloudQueueCreate = CloudQueue.create(homeMarketPlace, cloudQueueFactory.getCloudQueueService(), isOnDemandPlayMode, getAdInformation(context), zIsExplicitLanguageFilterEnabled, null, playableEntityIdentifierList, pageToken, isVoiceInitiated, isShuffled, startAtAsin);
        cloudQueueCreate.registerListener(sTerminateCloudQueueListener);
        FreeTierSequencer freeTierSequencer = new FreeTierSequencer(cloudQueueCreate, cloudQueueFactory.getCloudQueueService(), playback.getPlaybackConfig().getMediaLinkProvider(), playback.getPlaybackConfig().getMediaItemImageUriProvider(), playbackPageType, playback.getPlaybackConfig().getSkipLimitProvider(), context, isVoiceInitiated, true, collectionType, queueTitle);
        if (additionalMediaCollectionIds != null) {
            for (MediaCollectionId mediaCollectionId : additionalMediaCollectionIds) {
                if (mediaCollectionId != null) {
                    freeTierSequencer.getMediaCollectionInfo().addId(mediaCollectionId);
                }
            }
        }
        return freeTierSequencer;
    }

    @WorkerThread
    private static AdInformation getAdInformation(Context context) {
        return new AdInformation(ConnectivityUtil.getIpAddress(context), WebSettings.getDefaultUserAgent(context), AdUtil.getADId(context));
    }
}
