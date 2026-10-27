package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.music.media.playback.config.BitRateSelection;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public interface DMLSApi {
    ContentResponse retrieveGetStreamUrlResponse(ContentId contentId) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException;

    List<ContentResponse> retrieveStreamingURLsWithFirstChunkV2(List<ContentId> contentIds, BitRateSelection bitRateSelection, boolean includeSpatial) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException;

    IStreamingConcurrencyStatusResponse terminateAndUpdateStreamingStatus(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime, String oldDeviceName) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException;

    IStreamingConcurrencyStatusResponse updateStreamingStatus(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException;
}
