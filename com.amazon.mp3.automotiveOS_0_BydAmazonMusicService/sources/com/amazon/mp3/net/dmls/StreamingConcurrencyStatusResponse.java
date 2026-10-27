package com.amazon.mp3.net.dmls;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingConcurrencyStatusResponse implements IStreamingConcurrencyStatusResponse {
    private final long mInactivityThreshold;
    private final StatusCode mStatusCode;
    private final String mStatusMessage;
    private final ArrayList<DMLSStreamingStatusResponse> mStreamingResponseList;

    public StreamingConcurrencyStatusResponse(StatusCode statusCode, String statusMessage, ArrayList<DMLSStreamingStatusResponse> streamingResponseList, long inactivityThreshold) {
        this.mStatusCode = statusCode;
        this.mStatusMessage = statusMessage;
        this.mStreamingResponseList = streamingResponseList;
        this.mInactivityThreshold = inactivityThreshold;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public ArrayList<DMLSStreamingStatusResponse> getStreamingResponseList() {
        return this.mStreamingResponseList;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public long getInactivityThreshold() {
        return this.mInactivityThreshold;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StatusCode: ");
        sb.append(getStatusCode().toString());
        for (DMLSStreamingStatusResponse dMLSStreamingStatusResponse : getStreamingResponseList()) {
            sb.append(", RemoteDevice: ");
            sb.append(dMLSStreamingStatusResponse.getRemoteDeviceName());
            sb.append(", Elapsed time: ");
            sb.append(String.valueOf(dMLSStreamingStatusResponse.getElapsedTimeSeconds()));
        }
        return sb.toString();
    }

    public static StreamingConcurrencyStatusResponse createFromJSON(JSONObject json) throws JSONException {
        ArrayList arrayList = new ArrayList();
        StatusCode fromString = StatusCode.getFromString(json.getString("statusCode"));
        String string = json.getString("statusMessage");
        long j = json.getLong("inactivityThreshold");
        JSONArray jSONArray = json.getJSONArray("streamingStatusResponseList");
        int length = jSONArray.length();
        if (length == 0) {
            arrayList.add(new DMLSStreamingStatusResponse(0L, "", null, null, StreamingStatus.UNDEFINED, 0L));
        } else {
            for (int i = 0; i < length; i++) {
                arrayList.add(DMLSStreamingStatusResponse.createFromJSONObject(jSONArray.getJSONObject(i)));
            }
        }
        return new StreamingConcurrencyStatusResponse(fromString, string, arrayList, j);
    }
}
