package com.amazon.mp3.cloudqueue;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.cloudqueue.model.AdInformation;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.ResponseStatus;
import com.amazon.mp3.util.Log;
import com.amazon.musicplayqueueservice.client.common.AllowedParentalControls;
import com.amazon.musicplayqueueservice.client.v2.common.IndexIdentifier;
import com.amazon.musicplayqueueservice.client.v2.queue.AdContext;
import com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueRequestV1;
import com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueResponse;
import com.amazon.musicplayqueueservice.client.v2.queue.StartAt;
import com.amazon.musicplayqueueservice.model.CustomerInformation;
import com.android.volley.VolleyError;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class StartCloudQueueWorker implements Runnable {
    private static final String TAG = StartCloudQueueWorker.class.getSimpleName();
    private final CloudQueueCache cloudQueueCache;
    private final CloudQueueService cloudQueueService;
    private final boolean isExplicitFilterEnabled;
    private final boolean isOnDemandPlayMode;
    private final boolean isShuffled;
    private AdInformation mAdInformation;
    private final List<PlayableEntityIdentifier> playableEntityIdentifierList;
    private final String remoteTargetId;
    private final String startAtAsin;

    StartCloudQueueWorker(final CloudQueueCache cloudQueueCache, final AdInformation adInformation, final CloudQueueService cloudQueueService, final boolean isOnDemandPlayMode, final boolean isExplicitFilterEnabled, final String remoteTargetId, final List<PlayableEntityIdentifier> playableEntityIdentifierList, final boolean isShuffled, final String startAtAsin) {
        this.cloudQueueCache = cloudQueueCache;
        this.mAdInformation = adInformation;
        this.cloudQueueService = cloudQueueService;
        this.isOnDemandPlayMode = isOnDemandPlayMode;
        this.isExplicitFilterEnabled = isExplicitFilterEnabled;
        this.remoteTargetId = remoteTargetId;
        this.playableEntityIdentifierList = playableEntityIdentifierList;
        this.isShuffled = isShuffled;
        this.startAtAsin = startAtAsin;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.remoteTargetId != null) {
                return;
            }
            CreateQueueResponse createQueueResponseStartCloudQueue = startCloudQueue();
            ResponseStatus responseStatusValueOf = ResponseStatus.valueOf(createQueueResponseStartCloudQueue.getStatus());
            if (responseStatusValueOf != ResponseStatus.SUCCESS) {
                throw new CloudQueueNoNextException(responseStatusValueOf);
            }
            List<SynchronizedPlayableEntity> listCreateAll = QueueEntityUtil.createAll(createQueueResponseStartCloudQueue.getQueuedEntityMetadataList());
            if (listCreateAll != null && !listCreateAll.isEmpty()) {
                this.cloudQueueCache.setMetricsContext(MetricsContext.from(createQueueResponseStartCloudQueue.getQueuedEntityMetadataList().get(0).getMetricsContext()));
            }
            this.cloudQueueCache.onCacheUpdated(createQueueResponseStartCloudQueue.getQueue().getQueueId(), createQueueResponseStartCloudQueue.getQueue().getPageToken(), QueueEntityUtil.convertQueueMetadata(createQueueResponseStartCloudQueue.getQueue().getQueueMetadata()), listCreateAll);
        } catch (Exception e) {
            e = e;
            Log.error(TAG, "start queue api error " + e.toString(), e);
            if (!(e instanceof CloudQueueNoNextException)) {
                e = new CloudQueueNoNextException(e);
            }
            this.cloudQueueCache.interrupt((CloudQueueNoNextException) e);
        }
    }

    private CreateQueueResponse startCloudQueue() throws VolleyError {
        CreateQueueRequestV1 createQueueRequestV2 = getCreateQueueRequestV2();
        AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()));
        createQueueRequestV2.setAllowedParentalControls(allowedParentalControls);
        AdContext adContext = new AdContext();
        adContext.setAdvertiserId(this.mAdInformation.getGoogleAdId());
        adContext.setDeviceFamily("MOBILE");
        adContext.setUserAgent(this.mAdInformation.getUserAgent());
        CustomerInformation customerInformation = new CustomerInformation();
        customerInformation.setCustomerId(AccountDetailUtil.get().getCustomerId());
        customerInformation.setDeviceId(AccountCredentialStorage.get().getDeviceId());
        customerInformation.setDeviceType(AccountCredentialStorage.get().getDeviceType());
        createQueueRequestV2.setCustomerInfo(customerInformation);
        createQueueRequestV2.setAdContext(adContext);
        createQueueRequestV2.setPlayableEntityList(QueueEntityUtil.convertPlayableEntityList(this.playableEntityIdentifierList));
        createQueueRequestV2.setIsShuffled(Boolean.valueOf(this.isShuffled));
        createQueueRequestV2.setIsVoiceInitiatedRequest(this.cloudQueueCache.getIsVoiceInitiated().booleanValue());
        if (this.isOnDemandPlayMode) {
            createQueueRequestV2.setPlayMode("ON_DEMAND");
        } else {
            createQueueRequestV2.setPlayMode("SIMILAR");
        }
        if (this.startAtAsin != null) {
            StartAt startAt = new StartAt();
            startAt.setIdentifier(IndexIdentifier.builder().withIndex(1).build());
            startAt.setPositionInMs(1);
            startAt.setTrackIdentifier(this.startAtAsin);
            startAt.setTrackIdentifierType("ASIN");
            createQueueRequestV2.setStartAt(startAt);
        }
        return this.cloudQueueService.startCloudQueue(createQueueRequestV2);
    }

    public CreateQueueRequestV1 getCreateQueueRequestV2() {
        return new CreateQueueRequestV1();
    }
}
