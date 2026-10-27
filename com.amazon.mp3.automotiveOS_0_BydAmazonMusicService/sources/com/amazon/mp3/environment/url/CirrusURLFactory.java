package com.amazon.mp3.environment.url;

import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = "CirrusURLFactory";

    public CirrusURLFactory(int environmentKey, String currentPath) {
        super(environmentKey, currentPath);
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getCirrusEndpoint());
        } catch (Exception e) {
            Log.error(TAG, "Can't get cirrus endpoint " + e);
            return null;
        }
    }
}
