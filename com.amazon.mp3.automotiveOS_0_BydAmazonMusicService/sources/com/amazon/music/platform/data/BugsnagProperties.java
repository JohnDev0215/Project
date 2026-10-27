package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BugsnagProperties.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/data/BugsnagProperties;", "", "", "component1", "component2", "toString", "", "hashCode", "other", "", "equals", "notifyEndpoint", "Ljava/lang/String;", "getNotifyEndpoint", "()Ljava/lang/String;", "sessionEndpoint", "getSessionEndpoint", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class BugsnagProperties {
    private final String notifyEndpoint;
    private final String sessionEndpoint;

    public BugsnagProperties() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getNotifyEndpoint() {
        return this.notifyEndpoint;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSessionEndpoint() {
        return this.sessionEndpoint;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BugsnagProperties)) {
            return false;
        }
        BugsnagProperties bugsnagProperties = (BugsnagProperties) other;
        return Intrinsics.areEqual(this.notifyEndpoint, bugsnagProperties.notifyEndpoint) && Intrinsics.areEqual(this.sessionEndpoint, bugsnagProperties.sessionEndpoint);
    }

    public int hashCode() {
        return (this.notifyEndpoint.hashCode() * 31) + this.sessionEndpoint.hashCode();
    }

    public String toString() {
        return "BugsnagProperties(notifyEndpoint=" + this.notifyEndpoint + ", sessionEndpoint=" + this.sessionEndpoint + ')';
    }

    public BugsnagProperties(String notifyEndpoint, String sessionEndpoint) {
        Intrinsics.checkNotNullParameter(notifyEndpoint, "notifyEndpoint");
        Intrinsics.checkNotNullParameter(sessionEndpoint, "sessionEndpoint");
        this.notifyEndpoint = notifyEndpoint;
        this.sessionEndpoint = sessionEndpoint;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ BugsnagProperties(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "https://events.app-performance.music.amazon.dev" : str, (i & 2) != 0 ? "https://session.app-performance.music.amazon.dev" : str2);
    }
}
