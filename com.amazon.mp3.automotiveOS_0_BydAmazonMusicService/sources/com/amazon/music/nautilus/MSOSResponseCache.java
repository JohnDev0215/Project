package com.amazon.music.nautilus;

import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public class MSOSResponseCache {
    private static AtomicReference<RetrieveEligibleSubscriptionOffersResponse> mCachedMsosResponse = new AtomicReference<>();

    RetrieveEligibleSubscriptionOffersResponse getCachedMsosResponse() {
        return mCachedMsosResponse.get();
    }

    void setCachedMsosResponse(RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse) {
        mCachedMsosResponse.set(retrieveEligibleSubscriptionOffersResponse);
    }
}
