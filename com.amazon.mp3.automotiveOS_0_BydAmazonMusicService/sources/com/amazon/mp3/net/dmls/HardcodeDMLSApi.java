package com.amazon.mp3.net.dmls;

import com.amazon.mp3.util.Log;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class HardcodeDMLSApi extends DefaultDMLSApi {
    private static final String TAG = "HardcodeDMLSApi";
    private static HardcodeDMLSApi sInstance;

    static HardcodeDMLSApi getInstance() {
        if (sInstance == null) {
            sInstance = new HardcodeDMLSApi();
        }
        return sInstance;
    }

    @Override // com.amazon.mp3.net.dmls.DefaultDMLSApi
    protected JSONObject createGetStreamUrlsRequest(List<ContentId> contentIds) throws JSONException {
        Log.info(TAG, "Using Customer ID[%s] to generate Streaming Request", "AWOUGPEAEN173");
        JSONObject jSONObjectCreateGetStreamUrlsRequest = super.createGetStreamUrlsRequest(contentIds);
        jSONObjectCreateGetStreamUrlsRequest.put("customerId", "AWOUGPEAEN173");
        return jSONObjectCreateGetStreamUrlsRequest;
    }
}
