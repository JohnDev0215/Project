package com.amazon.mp3.cloudqueue;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.musicplayqueueservice.client.common.AllowedParentalControls;
import com.amazon.musicplayqueueservice.client.v2.RateEntityRequest;
import com.amazon.musicplayqueueservice.model.CustomerInformation;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes3.dex */
final class RateTrackWorker implements Runnable {
    private static final String TAG = RateTrackWorker.class.getSimpleName();
    private final CloudQueueService cloudQueueService;
    private final int millisecondsPlayedBeforeRating;
    private final String queueId;
    private final Rating rating;
    private final String trackId;

    RateTrackWorker(final CloudQueueService cloudQueueService, final String trackId, final String queueId, final Rating rating, final int millisecondsPlayedBeforeRating) {
        this.cloudQueueService = cloudQueueService;
        this.trackId = trackId;
        this.queueId = queueId;
        this.rating = rating;
        this.millisecondsPlayedBeforeRating = millisecondsPlayedBeforeRating;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            RateEntityRequest rateEntityRequest = new RateEntityRequest();
            rateEntityRequest.setEntityReference(this.trackId);
            rateEntityRequest.setQueueId(this.queueId);
            rateEntityRequest.setRating(this.rating.name());
            rateEntityRequest.setDurationPlayedInMilliseconds(this.millisecondsPlayedBeforeRating);
            rateEntityRequest.setUtcTimeStamp(System.currentTimeMillis());
            CustomerInformation customerInformation = new CustomerInformation();
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(AmazonApplication.getApplication());
            customerInformation.setCustomerId(AccountManagerSingleton.get().getUser().getCustomerId());
            customerInformation.setDeviceId(accountCredentialStorage.getDeviceId());
            customerInformation.setDeviceType(accountCredentialStorage.getDeviceType());
            AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
            allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()));
            rateEntityRequest.setAllowedParentalControls(allowedParentalControls);
            rateEntityRequest.setCustomerInfo(customerInformation);
            this.cloudQueueService.rateEntity(rateEntityRequest);
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            Log.error(TAG, "Error when sending rate entity request");
        } catch (VolleyError unused2) {
        }
    }
}
