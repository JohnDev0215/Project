package com.amazon.avod.ads.api;

import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastOffsetType;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.net.URI;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Beacon {
    private static final Map<VastTrackingEventType, Offset> DEFAULT_OFFSETS;
    private final AdHttpClient mHttpClient;
    private final VastTracking mVastEvent;

    static {
        EnumMap enumMapNewEnumMap = Maps.newEnumMap(VastTrackingEventType.class);
        DEFAULT_OFFSETS = enumMapNewEnumMap;
        enumMapNewEnumMap.put(VastTrackingEventType.start, new Offset(VastOffsetType.START));
        enumMapNewEnumMap.put(VastTrackingEventType.firstQuartile, new Offset(0.25f));
        enumMapNewEnumMap.put(VastTrackingEventType.midpoint, new Offset(0.5f));
        enumMapNewEnumMap.put(VastTrackingEventType.thirdQuartile, new Offset(0.75f));
        enumMapNewEnumMap.put(VastTrackingEventType.complete, new Offset(VastOffsetType.END));
    }

    public Beacon(VastTracking vastTracking, AdHttpClient adHttpClient) {
        this.mVastEvent = (VastTracking) Preconditions.checkNotNull(vastTracking);
        this.mHttpClient = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
    }

    @Nonnull
    public VastTrackingEventType getEventType() {
        return this.mVastEvent.getEventType();
    }

    @Nonnull
    public VastTracking getEvent() {
        return this.mVastEvent;
    }

    @Nullable
    public Offset getOffset() {
        if (this.mVastEvent.getOffset() != null) {
            return new Offset(this.mVastEvent.getOffset());
        }
        Map<VastTrackingEventType, Offset> map = DEFAULT_OFFSETS;
        if (map.containsKey(this.mVastEvent.getEventType())) {
            return map.get(this.mVastEvent.getEventType());
        }
        return null;
    }

    public void sendEvent(Duration duration, URI uri, String str) throws AdNetworkException {
        DLog.devf("Sending BeaconType: = %s , uri = %s", this.mVastEvent.getEventType(), this.mVastEvent.getUri().toString());
        this.mHttpClient.sendVastBeacon(this.mVastEvent.getUri(), duration, uri, (AdInfoErrorCode) null, str);
    }

    public void pingEvent() throws AdNetworkException {
        DLog.devf("Sending BeaconType: = %s , uri = %s", this.mVastEvent.getEventType(), this.mVastEvent.getUri().toString());
        this.mHttpClient.sendSimpleBeacon(this.mVastEvent.getUri());
    }
}
