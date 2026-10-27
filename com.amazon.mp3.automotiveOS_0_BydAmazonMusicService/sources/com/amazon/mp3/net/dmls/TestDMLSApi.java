package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.dmls.ContentResponse;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.config.BitRateSelection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class TestDMLSApi implements DMLSApi {
    private static final String TAG = "TestDMLSApi";
    private static long sInactivityThreshold;
    private static TestDMLSApi sInstance;
    private static StatusCode[] sStatusCache;
    private static int sStatusCodePosition;

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(StatusCode.AUTHENTICATION_ERROR);
        arrayList.add(StatusCode.BAD_REQUEST);
        arrayList.add(StatusCode.CONTENT_NOT_ELIGIBLE);
        arrayList.add(StatusCode.CUSTOMER_NOT_ELIGIBLE);
        arrayList.add(StatusCode.INTERNAL_ERROR);
        arrayList.add(StatusCode.UNKNOWN);
        StatusCode[] statusCodeArr = new StatusCode[arrayList.size()];
        sStatusCache = statusCodeArr;
        arrayList.toArray(statusCodeArr);
        sStatusCodePosition = 0;
        sInactivityThreshold = 14400L;
    }

    static TestDMLSApi getInstance() {
        if (sInstance == null) {
            sInstance = new TestDMLSApi();
        }
        return sInstance;
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public ContentResponse retrieveGetStreamUrlResponse(ContentId contentId) throws DMLSExceptions.DMLSException {
        return retrieveGetStreamUrlResponse(contentId.getType(), contentId.getId());
    }

    public ContentResponse retrieveGetStreamUrlResponse(IdentifierType type, String id) throws DMLSExceptions.DMLSException {
        StatusCode statusCode = StatusCode.SUCCESS;
        Log.debug(TAG, "Generating a Test GetStreamUrls Response");
        ContentId contentId = new ContentId(type, id);
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        arrayList.add("https://adm-vh.akamaihd-staging.net/i/DigitalMusicDeliveryService/Origin/Sample1_,200,.mp4.csmil/master.m3u8");
        ContentResponse contentResponse = new ContentResponse(contentId, jCurrentTimeMillis, statusCode, "This is a test response", arrayList, ContentResponse.Protocol.HLS, StreamingBitrate.AUTO, null);
        DMLSExceptions.validateContentResponse(contentResponse);
        return contentResponse;
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public IStreamingConcurrencyStatusResponse terminateAndUpdateStreamingStatus(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime, String oldDeviceName) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        Log.debug(TAG, "Generating a Test TerminateAndUpdateStreamingStatus Response");
        StatusCode statusCode = StatusCode.SUCCESS;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DMLSStreamingStatusResponse(0L, oldDeviceName, null, null, status, 0L));
        return new StreamingConcurrencyStatusResponse(statusCode, "SUCCESS", arrayList, sInactivityThreshold);
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public IStreamingConcurrencyStatusResponse updateStreamingStatus(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        Log.debug(TAG, "Generating a Test UpdateStreamingStatus Response");
        StatusCode statusCode = StatusCode.SUCCESS;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DMLSStreamingStatusResponse(0L, null, null, null, status, 0L));
        return new StreamingConcurrencyStatusResponse(statusCode, "SUCCESS", arrayList, sInactivityThreshold);
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public List<ContentResponse> retrieveStreamingURLsWithFirstChunkV2(List<ContentId> contentIds, BitRateSelection bitRateSelection, boolean includeSpatial) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        throw new UnsupportedOperationException();
    }
}
