package com.amazon.mp3.environment.url;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CloudPlayerURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = CirrusURLFactory.class.getSimpleName();

    public CloudPlayerURLFactory() {
        super(R.string.setting_key_dev_cirrus_endpoint, "cirrus");
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getCloudplayerEndpoint());
        } catch (Exception e) {
            Log.error(TAG, "Can't get cloudplayer endpoint " + e);
            return null;
        }
    }
}
