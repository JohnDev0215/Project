package com.amazon.mp3.cloudqueue;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.cloudqueue.CloudQueueNoNextException;
import com.amazon.mp3.cloudqueue.model.AdInformation;
import com.amazon.mp3.cloudqueue.model.ResponseStatus;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.musicplayqueueservice.client.common.AllowedParentalControls;
import com.amazon.musicplayqueueservice.client.v2.queue.AdContext;
import com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksRequestV1;
import com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksResponse;
import com.amazon.musicplayqueueservice.model.CustomerInformation;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
final class ContinueCloudQueueWorker implements Runnable {
    private static final String TAG = ContinueCloudQueueWorker.class.getSimpleName();
    private final AdInformation adInformation;
    private final ChangeReason changeReason;
    private final CloudQueueCache cloudQueueCache;
    private final CloudQueueService cloudQueueService;

    ContinueCloudQueueWorker(final CloudQueueCache cloudQueueCache, final CloudQueueService cloudQueueService, final AdInformation adInformation, final ChangeReason changeReason) {
        this.cloudQueueCache = cloudQueueCache;
        this.cloudQueueService = cloudQueueService;
        this.adInformation = adInformation;
        this.changeReason = changeReason;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.cloudQueueCache.onCacheWorkerActive();
        try {
            if (this.cloudQueueCache.getPageToken() == null) {
                throw new CloudQueueNoNextException(CloudQueueNoNextException.Reason.REACH_END_OF_QUEUE);
            }
            GetNextTracksRequestV1 getNextTracksRequestV1 = new GetNextTracksRequestV1();
            boolean z = true;
            getNextTracksRequestV1.setNumberOfTracks(1);
            getNextTracksRequestV1.setPageToken(this.cloudQueueCache.getPageToken());
            AdContext adContext = new AdContext();
            adContext.setAdvertiserId(this.adInformation.getGoogleAdId());
            adContext.setDeviceFamily("MOBILE");
            adContext.setUserAgent(this.adInformation.getUserAgent());
            if (this.changeReason == ChangeReason.NEXT) {
                HashMap map = new HashMap();
                map.put("trackSkippedForward", "true");
                adContext.setAdditionalMetadata(map);
            }
            getNextTracksRequestV1.setAdContext(adContext);
            AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
            if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
                z = false;
            }
            allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(z));
            getNextTracksRequestV1.setAllowedParentalControls(allowedParentalControls);
            CustomerInformation customerInformation = new CustomerInformation();
            customerInformation.setCustomerId(AccountDetailUtil.get().getCustomerId());
            customerInformation.setDeviceId(AccountCredentialStorage.get().getDeviceId());
            customerInformation.setDeviceType(AccountCredentialStorage.get().getDeviceType());
            getNextTracksRequestV1.setCustomerInfo(customerInformation);
            getNextTracksRequestV1.setIsVoiceInitiatedRequest(this.cloudQueueCache.getIsVoiceInitiated().booleanValue());
            GetNextTracksResponse trackForCloudQueue = this.cloudQueueService.getTrackForCloudQueue(getNextTracksRequestV1);
            ResponseStatus responseStatusValueOf = ResponseStatus.valueOf(trackForCloudQueue.getResponseStatus());
            if (responseStatusValueOf != ResponseStatus.SUCCESS) {
                throw new CloudQueueNoNextException(responseStatusValueOf);
            }
            if (CollectionUtils.isEmpty(trackForCloudQueue.getQueuedEntityMetadataList())) {
                throw new CloudQueueNoNextException(CloudQueueNoNextException.Reason.QUEUE_TERMINATED);
            }
            this.cloudQueueCache.onCacheUpdated(trackForCloudQueue.getNextPageToken(), QueueEntityUtil.createAll(trackForCloudQueue.getQueuedEntityMetadataList()));
        } catch (Exception e) {
            e = e;
            Log.error(TAG, "get next tracks api error " + e.toString(), e);
            if (!(e instanceof CloudQueueNoNextException)) {
                e = new CloudQueueNoNextException(e);
            }
            this.cloudQueueCache.interrupt((CloudQueueNoNextException) e);
        }
    }
}
