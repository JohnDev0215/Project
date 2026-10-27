package com.amazon.mp3.environment.url;

import com.amazon.mp3.util.Log;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class RESOV2EndpointURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = "RESOV2EndpointURLFactory";

    public RESOV2EndpointURLFactory(int environmentKey) {
        super(environmentKey);
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getRESOV2Endpoint());
        } catch (Exception e) {
            Log.error(TAG, "Can't get resov2 endpoint ", e);
            return null;
        }
    }

    public URL getUrl() {
        try {
            return EndpointsProvider.get().getRESOV2Endpoint();
        } catch (Exception e) {
            Log.error(TAG, "Can't get resov2 Url ", e);
            return null;
        }
    }
}
