package com.amazon.music.metrics.mts.event.definition.prime;

import com.amazon.music.metrics.mts.event.types.StreamingConcurrencyResponse;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeConcurrentMusicResponseEvent extends PrimeDialogEvent {
    public PrimeConcurrentMusicResponseEvent(String str, StreamingConcurrencyResponse streamingConcurrencyResponse) {
        super("primeConcurrentMusicResponse", str);
        addEventAttributes(streamingConcurrencyResponse);
    }

    private void addEventAttributes(StreamingConcurrencyResponse streamingConcurrencyResponse) {
        addAttribute("terminationReason", streamingConcurrencyResponse.getMetricValue());
    }
}
