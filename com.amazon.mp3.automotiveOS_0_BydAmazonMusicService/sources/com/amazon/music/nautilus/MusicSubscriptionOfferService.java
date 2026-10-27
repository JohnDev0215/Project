package com.amazon.music.nautilus;

import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersRequest;
import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse;
import com.amazon.musicsubscriptionofferservice.external.RetrieveEligibleSubscriptionOffersCall;
import com.android.volley.VolleyError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MusicSubscriptionOfferService {
    private final Logger LOG = LoggerFactory.getLogger(MusicSubscriptionOfferService.class.getSimpleName());
    private MSOSConfiguration configuration;

    public MusicSubscriptionOfferService(MSOSConfiguration mSOSConfiguration) {
        this.configuration = mSOSConfiguration;
    }

    public RetrieveEligibleSubscriptionOffersRequest buildEligibilityRequest() {
        RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest = new RetrieveEligibleSubscriptionOffersRequest();
        retrieveEligibleSubscriptionOffersRequest.setDeviceId(this.configuration.getDeviceId());
        retrieveEligibleSubscriptionOffersRequest.setDeviceType(this.configuration.getDeviceType());
        retrieveEligibleSubscriptionOffersRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        retrieveEligibleSubscriptionOffersRequest.setFilterQuery(null);
        return retrieveEligibleSubscriptionOffersRequest;
    }

    public RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffers(RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest) {
        try {
            return new RetrieveEligibleSubscriptionOffersCall(this.configuration.getUrl(), retrieveEligibleSubscriptionOffersRequest, this.configuration.getRequestInterceptor()).execute(MSOSMapper.getMapper());
        } catch (VolleyError e) {
            this.LOG.error("Error retrieving eligible subscription offers response", (Throwable) e);
            return null;
        }
    }

    public RetrieveEligibleSubscriptionOffersResponse getSubscriptions() {
        return retrieveEligibleSubscriptionOffers(buildEligibilityRequest());
    }
}
