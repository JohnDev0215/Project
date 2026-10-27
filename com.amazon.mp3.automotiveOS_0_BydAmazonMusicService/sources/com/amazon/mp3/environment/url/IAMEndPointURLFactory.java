package com.amazon.mp3.environment.url;

import com.amazon.mp3.util.Log;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class IAMEndPointURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = "IAMEndPointURLFactory";

    public IAMEndPointURLFactory(int environmentKey) {
        super(environmentKey);
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getIAMEndpointUrl());
        } catch (Exception e) {
            Log.error(TAG, "Can't get in app messaging endpoint ", e);
            return null;
        }
    }

    public URL getUrl() {
        try {
            return EndpointsProvider.get().getIAMEndpointUrl();
        } catch (Exception e) {
            Log.error(TAG, "Can't get in app messaging Url ", e);
            return null;
        }
    }
}
