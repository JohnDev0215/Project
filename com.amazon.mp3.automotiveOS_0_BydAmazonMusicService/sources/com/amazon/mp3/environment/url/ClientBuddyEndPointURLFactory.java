package com.amazon.mp3.environment.url;

import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class ClientBuddyEndPointURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = CirrusURLFactory.class.getSimpleName();

    public ClientBuddyEndPointURLFactory(int environmentKey) {
        super(environmentKey, "cb");
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getClientbuddyEndpoint());
        } catch (Exception e) {
            Log.error(TAG, "Can't get clientbuddy endpoint " + e);
            return null;
        }
    }
}
