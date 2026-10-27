package com.amazon.music.featuregating.weblabs;

import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabCustomerInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", IntlConfiguration.MARKETPLACE, "Ljava/lang/String;", "getMarketplace", "()Ljava/lang/String;", Splash.PARAMS_SESSION_ID, "getSessionId", "directedId", "getDirectedId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class WeblabCustomerInfo {
    private final String directedId;
    private final String marketplace;
    private final String sessionId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeblabCustomerInfo)) {
            return false;
        }
        WeblabCustomerInfo weblabCustomerInfo = (WeblabCustomerInfo) other;
        return Intrinsics.areEqual(this.marketplace, weblabCustomerInfo.marketplace) && Intrinsics.areEqual(this.sessionId, weblabCustomerInfo.sessionId) && Intrinsics.areEqual(this.directedId, weblabCustomerInfo.directedId);
    }

    public int hashCode() {
        int iHashCode = ((this.marketplace.hashCode() * 31) + this.sessionId.hashCode()) * 31;
        String str = this.directedId;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "WeblabCustomerInfo(marketplace=" + this.marketplace + ", sessionId=" + this.sessionId + ", directedId=" + this.directedId + ')';
    }

    public WeblabCustomerInfo(String marketplace, String sessionId, String str) {
        Intrinsics.checkNotNullParameter(marketplace, "marketplace");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        this.marketplace = marketplace;
        this.sessionId = sessionId;
        this.directedId = str;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getMarketplace() {
        return this.marketplace;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getDirectedId() {
        return this.directedId;
    }
}
