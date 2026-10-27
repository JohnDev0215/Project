package com.amazon.mp3.environment.url;

import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class DmlsURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = "DmlsURLFactory";

    public DmlsURLFactory(int environmentKey, String currentPath) {
        super(environmentKey, currentPath);
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getDMLSEndpoint());
        } catch (Exception e) {
            Log.error(TAG, "Can't get DMLS endpoint " + e);
            return null;
        }
    }
}
