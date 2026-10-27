package com.amazon.mp3.net.dmls;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public interface IStreamingConcurrencyStatusResponse {
    long getInactivityThreshold();

    StatusCode getStatusCode();

    String getStatusMessage();

    ArrayList<DMLSStreamingStatusResponse> getStreamingResponseList();
}
