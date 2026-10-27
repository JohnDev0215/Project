package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdDocument;
import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vmap.VmapAdBreak;
import com.amazon.avod.ads.parser.vmap.VmapTracking;
import com.amazon.avod.ads.parser.vmap.VmapTrackingEventType;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdBreakInfo {
    private AdDocument mDocument;
    private final AdHttpClient mHttpClient;
    private final VmapAdBreak mInnerAdBreak;
    private final Offset mOffset;
    private final Duration mRepeatInterval;

    public AdBreakInfo(VmapAdBreak vmapAdBreak, AdHttpClient adHttpClient) {
        this(vmapAdBreak, adHttpClient, null);
    }

    AdBreakInfo(VmapAdBreak vmapAdBreak, AdHttpClient adHttpClient, AdDocument adDocument) {
        VmapAdBreak vmapAdBreak2 = (VmapAdBreak) Preconditions.checkNotNull(vmapAdBreak);
        this.mInnerAdBreak = vmapAdBreak2;
        this.mHttpClient = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
        this.mDocument = adDocument;
        this.mOffset = new Offset(vmapAdBreak2.getTimeOffset());
        if (vmapAdBreak2.getRepeatAfter() != null) {
            this.mRepeatInterval = new Duration(vmapAdBreak2.getRepeatAfter());
        } else {
            this.mRepeatInterval = null;
        }
    }

    @Nonnull
    public Offset getOffset() {
        return this.mOffset;
    }

    @Nullable
    public String getId() {
        return this.mInnerAdBreak.getBreakId();
    }

    public void sendEvent(VmapTrackingEventType vmapTrackingEventType) throws AdNetworkException {
        for (VmapTracking vmapTracking : this.mInnerAdBreak.getTrackingEvents()) {
            if (vmapTracking.getEventType() == vmapTrackingEventType) {
                this.mHttpClient.sendVmapBeacon(vmapTracking.getUri());
            }
        }
    }

    public void sendError(AdBreakErrorCode adBreakErrorCode, String str) throws AdNetworkException {
        for (VmapTracking vmapTracking : this.mInnerAdBreak.getTrackingEvents()) {
            if (vmapTracking.getEventType() == VmapTrackingEventType.error) {
                this.mHttpClient.sendVmapBeacon(vmapTracking.getUri(), adBreakErrorCode, str);
            }
        }
    }

    @Nullable
    public AdInfo getAdPod() {
        Preconditions.checkState(this.mDocument != null, "Document has not been resolved.");
        return this.mDocument.getAdPod();
    }

    @Nonnull
    public List<? extends AdInfo> getAdBuffet() {
        Preconditions.checkState(this.mDocument != null, "Document has not been resolved.");
        return this.mDocument.getAdBuffet();
    }

    public void retrieveAdInfo() throws AdNetworkException, AdParsingException {
        if (this.mDocument == null && this.mInnerAdBreak.getAdSource() != null) {
            if (this.mInnerAdBreak.getAdSource().getVastData() != null) {
                this.mDocument = new AdDocument(this.mHttpClient, this.mInnerAdBreak.getAdSource().getVastData().getVastDocument());
            } else if (this.mInnerAdBreak.getAdSource().getAdTagURI() != null) {
                this.mDocument = AdDocument.retrieveAdDocument(this.mHttpClient, this.mInnerAdBreak.getAdSource().getAdTagURI().getUri());
            }
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Document resolved", this.mDocument != null).add("Offset", this.mOffset.toString()).add("Repeat Interval", this.mRepeatInterval).toString();
    }
}
