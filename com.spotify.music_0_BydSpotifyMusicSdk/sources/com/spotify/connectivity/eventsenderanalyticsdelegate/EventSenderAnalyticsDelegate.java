package com.spotify.connectivity.eventsenderanalyticsdelegate;

import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.eventsender.api.EventSenderCoreBridge;

/* JADX INFO: loaded from: classes.dex */
public class EventSenderAnalyticsDelegate implements AnalyticsDelegate {
    private final EventSenderCoreBridge mEventSenderCoreBridge;

    public EventSenderAnalyticsDelegate(EventSenderCoreBridge eventSenderCoreBridge) {
        this.mEventSenderCoreBridge = eventSenderCoreBridge;
    }

    @Override // com.spotify.connectivity.AnalyticsDelegate
    public void logEvent(byte[] bArr, byte[] bArr2) {
        this.mEventSenderCoreBridge.send(bArr, bArr2, null);
    }
}
