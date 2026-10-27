package com.visioncritical.touchpointkit.models;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointTrigger.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0010\u001b\u001a\u00020\u0007\u0012\u0006\u0010\u001d\u001a\u00020\u0007\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020%\u0012\u0006\u0010*\u001a\u00020\u0007\u0012\u0006\u0010,\u001a\u00020\u0003¢\u0006\u0004\b.\u0010/J\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\t\u0010\b\u001a\u00020\u0007HÖ\u0001R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR(\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u001d\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001e\u0010\fR\u0017\u0010\u001f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b$\u0010\"R\u0017\u0010&\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010*\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b*\u0010\n\u001a\u0004\b+\u0010\fR\u0017\u0010,\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b,\u0010 \u001a\u0004\b-\u0010\"¨\u00060"}, d2 = {"Lcom/visioncritical/touchpointkit/models/TouchPointTrigger;", "", "other", "", "equals", "", "hashCode", "", "toString", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "captionText", "getCaptionText", "distributionUrl", "getDistributionUrl", "distributionID", "getDistributionID", "", "Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;", "screenComponents", "[Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;", "getScreenComponents", "()[Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;", "setScreenComponents", "([Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;)V", "backgroundColor", "getBackgroundColor", "textColor", "getTextColor", "useBannerStyling", "Z", "getUseBannerStyling", "()Z", "alwaysShow", "getAlwaysShow", "", "delayInMS", "J", "getDelayInMS", "()J", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "getType", "showOnAllScreens", "getShowOnAllScreens", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;Ljava/lang/String;Ljava/lang/String;ZZJLjava/lang/String;Z)V", "touchpointkit_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TouchPointTrigger {
    private final boolean alwaysShow;
    private final String backgroundColor;
    private final String captionText;
    private final long delayInMS;
    private final String distributionID;
    private final String distributionUrl;
    private final String id;
    private TouchPointScreenComponent[] screenComponents;
    private final boolean showOnAllScreens;
    private final String textColor;
    private final String type;
    private final boolean useBannerStyling;

    public String toString() {
        return "TouchPointTrigger(id=" + this.id + ", captionText=" + this.captionText + ", distributionUrl=" + this.distributionUrl + ", distributionID=" + this.distributionID + ", screenComponents=" + Arrays.toString(this.screenComponents) + ", backgroundColor=" + this.backgroundColor + ", textColor=" + this.textColor + ", useBannerStyling=" + this.useBannerStyling + ", alwaysShow=" + this.alwaysShow + ", delayInMS=" + this.delayInMS + ", type=" + this.type + ", showOnAllScreens=" + this.showOnAllScreens + ')';
    }

    public TouchPointTrigger(String id, String captionText, String distributionUrl, String distributionID, TouchPointScreenComponent[] screenComponents, String backgroundColor, String textColor, boolean z, boolean z2, long j, String type, boolean z3) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(captionText, "captionText");
        Intrinsics.checkNotNullParameter(distributionUrl, "distributionUrl");
        Intrinsics.checkNotNullParameter(distributionID, "distributionID");
        Intrinsics.checkNotNullParameter(screenComponents, "screenComponents");
        Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
        Intrinsics.checkNotNullParameter(textColor, "textColor");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = id;
        this.captionText = captionText;
        this.distributionUrl = distributionUrl;
        this.distributionID = distributionID;
        this.screenComponents = screenComponents;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.useBannerStyling = z;
        this.alwaysShow = z2;
        this.delayInMS = j;
        this.type = type;
        this.showOnAllScreens = z3;
    }

    public final String getId() {
        return this.id;
    }

    public final String getCaptionText() {
        return this.captionText;
    }

    public final String getDistributionUrl() {
        return this.distributionUrl;
    }

    public final String getDistributionID() {
        return this.distributionID;
    }

    public final TouchPointScreenComponent[] getScreenComponents() {
        return this.screenComponents;
    }

    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    public final String getTextColor() {
        return this.textColor;
    }

    public final boolean getAlwaysShow() {
        return this.alwaysShow;
    }

    public final long getDelayInMS() {
        return this.delayInMS;
    }

    public final String getType() {
        return this.type;
    }

    public final boolean getShowOnAllScreens() {
        return this.showOnAllScreens;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(TouchPointTrigger.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            TouchPointTrigger touchPointTrigger = (TouchPointTrigger) other;
            return Intrinsics.areEqual(this.id, touchPointTrigger.id) && Intrinsics.areEqual(this.captionText, touchPointTrigger.captionText) && Intrinsics.areEqual(this.distributionUrl, touchPointTrigger.distributionUrl) && Intrinsics.areEqual(this.distributionID, touchPointTrigger.distributionID) && Arrays.equals(this.screenComponents, touchPointTrigger.screenComponents) && Intrinsics.areEqual(this.backgroundColor, touchPointTrigger.backgroundColor) && Intrinsics.areEqual(this.textColor, touchPointTrigger.textColor) && this.useBannerStyling == touchPointTrigger.useBannerStyling && this.alwaysShow == touchPointTrigger.alwaysShow && this.delayInMS == touchPointTrigger.delayInMS && Intrinsics.areEqual(this.type, touchPointTrigger.type) && this.showOnAllScreens == touchPointTrigger.showOnAllScreens;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.visioncritical.touchpointkit.models.TouchPointTrigger");
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.captionText.hashCode()) * 31) + this.distributionUrl.hashCode()) * 31) + this.distributionID.hashCode()) * 31) + Arrays.hashCode(this.screenComponents)) * 31) + this.backgroundColor.hashCode()) * 31) + this.textColor.hashCode()) * 31) + Boolean.hashCode(this.useBannerStyling)) * 31) + Boolean.hashCode(this.alwaysShow)) * 31) + Long.hashCode(this.delayInMS)) * 31) + this.type.hashCode()) * 31) + Boolean.hashCode(this.showOnAllScreens);
    }
}
