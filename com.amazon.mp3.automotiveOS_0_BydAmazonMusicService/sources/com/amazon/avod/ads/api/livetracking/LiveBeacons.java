package com.amazon.avod.ads.api.livetracking;

import com.amazon.avod.ads.api.Beacon;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastOffset;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class LiveBeacons {
    private LiveBeacons() {
    }

    @Nonnull
    public static List<Beacon> getLiveTimeTranslatedBeacons(@Nullable LiveAdTracking liveAdTracking, @Nullable AdHttpClient adHttpClient, @Nonnull Set<String> set) {
        String eventType;
        Preconditions.checkNotNull(set, "liveSupportedAdTrackingEvents");
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        if (adHttpClient != null && liveAdTracking != null && liveAdTracking.getAvails() != null) {
            for (Avails avails : liveAdTracking.getAvails()) {
                if (avails.getAds() != null) {
                    for (Ads ads : avails.getAds()) {
                        if (ads.getTrackingEvents() != null) {
                            for (TrackingEvents trackingEvents : ads.getTrackingEvents()) {
                                if (set.contains(trackingEvents.getEventType())) {
                                    long startTimeInMilliseconds = trackingEvents.getStartTimeInMilliseconds();
                                    if (trackingEvents.getEventType() == null) {
                                        eventType = VastTrackingEventType.unSpecifiedLiveAdTrackingEvent.name();
                                    } else {
                                        eventType = trackingEvents.getEventType();
                                    }
                                    linkedListNewLinkedList.addAll(constructBeacons(startTimeInMilliseconds, eventType, trackingEvents.getBeaconUrls(), adHttpClient));
                                }
                            }
                        }
                    }
                }
            }
        }
        return linkedListNewLinkedList;
    }

    @Nonnull
    private static List<Beacon> constructBeacons(long j, String str, @Nullable List<BeaconUrls> list, @Nonnull AdHttpClient adHttpClient) {
        Preconditions.checkNotNull(adHttpClient, "adHttpClient");
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        if (list == null) {
            return linkedListNewLinkedList;
        }
        Iterator<BeaconUrls> it = list.iterator();
        while (it.hasNext()) {
            try {
                linkedListNewLinkedList.add(new Beacon(new VastTracking((VastTrackingEventType) Enum.valueOf(VastTrackingEventType.class, str), VastOffset.createVastOffset(VastTimeSpan.buildVastTimeSpanFromMilliSeconds(j)), URI.create(it.next().getUrl())), adHttpClient));
            } catch (IllegalArgumentException e) {
                DLog.exceptionf(e, "cant construct beacon for a malformed URI", new Object[0]);
            }
        }
        return linkedListNewLinkedList;
    }
}
