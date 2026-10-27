package com.amazon.avod.ads.parser.parsers;

/* JADX INFO: loaded from: classes2.dex */
public enum VastError {
    AD_MULTIPLE_CHILD_TAGS("Ad: There should be exactly 1 child tag for <Ad>"),
    COMPANION_MULTIPLE_CHILD_TAGS("Companion: There should be exactly 1 child tag for <Companion> of either: <StaticResource>, <IFrameResource> or <HTMLResource>"),
    CREATIVE_EXCESS_CHILD_TAGS("Creative: There should be exactly 1 child tag for <Creative>of either <Linear>, <CompanionAds> or <NonLinearAds>"),
    ICON_CLICKS_EXCESS_ICON_CLICK_THROUGH("<IconClicks> should have only 1 child tag of <IconClickThrough>"),
    NONLINEAR_ADS_EXCESS_TRACKING_EVENTS("<NonLinearAds> should have only 1 child tag of <TrackingEvents>");

    private final String mErrorMessage;

    VastError(String str) {
        this.mErrorMessage = str;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }
}
