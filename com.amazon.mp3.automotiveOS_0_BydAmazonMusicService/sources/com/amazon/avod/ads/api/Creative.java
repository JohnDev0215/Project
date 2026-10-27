package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdCreativeHolderNode;
import com.amazon.avod.ads.api.internal.AdInfoNode;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastTracking;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Creative {
    private static final Set<VastTrackingEventType> TIME_BASED_EVENTS = Sets.newHashSet(VastTrackingEventType.firstQuartile, VastTrackingEventType.midpoint, VastTrackingEventType.thirdQuartile, VastTrackingEventType.progress);
    private final String mCreativeId;
    private final AdHttpClient mHttpClient;
    private final AdCreativeHolderNode mParent;
    private final List<VastTracking> mTrackingEvents;

    @Nonnull
    public abstract CreativeType getCreativeType();

    public abstract boolean isDisplayable();

    protected Creative(AdCreativeHolderNode adCreativeHolderNode, AdHttpClient adHttpClient, @Nullable List<VastTracking> list, String str) {
        this.mParent = adCreativeHolderNode;
        this.mHttpClient = adHttpClient;
        this.mTrackingEvents = (List) MoreObjects.firstNonNull(list, ImmutableList.of());
        this.mCreativeId = str;
    }

    public void sendTrackingEvent(final VastTrackingEventType vastTrackingEventType, final Duration duration, final URI uri, final String str) throws Exception {
        getAdInfoNode().callOnParentsAndThrow(new AdInfoNode.ThrowingParentCaller<AdNetworkException>() { // from class: com.amazon.avod.ads.api.Creative.1
            @Override // com.amazon.avod.ads.api.internal.AdInfoNode.ThrowingParentCaller
            public void call(AdInfoNode adInfoNode) throws AdNetworkException {
                if (adInfoNode == null || adInfoNode.getCreativeHolder() == null) {
                    return;
                }
                Iterator<Creative> it = adInfoNode.getCreativeHolder().getCreatives(Creative.this.getCreativeType()).iterator();
                while (it.hasNext()) {
                    for (VastTracking vastTracking : it.next().getTrackingEvents()) {
                        if (vastTracking.getEventType() == vastTrackingEventType) {
                            Creative.this.getHttpClient().sendVastBeacon(vastTracking.getUri(), duration, uri, str);
                        }
                    }
                }
            }
        });
    }

    public List<Beacon> getTimeBasedBeacons() {
        final LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        getAdInfoNode().callOnParents(new AdInfoNode.ParentCaller() { // from class: com.amazon.avod.ads.api.Creative.2
            @Override // com.amazon.avod.ads.api.internal.AdInfoNode.ParentCaller
            public void call(AdInfoNode adInfoNode) {
                if (adInfoNode.getCreativeHolder() == null) {
                    return;
                }
                Iterator<Creative> it = adInfoNode.getCreativeHolder().getCreatives(Creative.this.getCreativeType()).iterator();
                while (it.hasNext()) {
                    for (VastTracking vastTracking : it.next().getTrackingEvents()) {
                        if (Creative.TIME_BASED_EVENTS.contains(vastTracking.getEventType())) {
                            linkedListNewLinkedList.add(new Beacon(vastTracking, Creative.this.mHttpClient));
                        }
                    }
                }
            }
        });
        return linkedListNewLinkedList;
    }

    @Nullable
    public String getCreativeId() {
        return this.mCreativeId;
    }

    @Nonnull
    public AdInfo getAdInfo() {
        return this.mParent.getParent();
    }

    @Nonnull
    protected AdInfoNode getAdInfoNode() {
        return this.mParent.getParent();
    }

    @Nonnull
    protected AdHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    @Nonnull
    protected List<VastTracking> getTrackingEvents() {
        return this.mTrackingEvents;
    }
}
