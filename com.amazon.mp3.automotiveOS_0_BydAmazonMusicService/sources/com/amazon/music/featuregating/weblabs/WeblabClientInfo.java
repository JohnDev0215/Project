package com.amazon.music.featuregating.weblabs;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabClientInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "clientIdentifier", "Ljava/lang/String;", "getClientIdentifier", "()Ljava/lang/String;", "appName", "getAppName", Splash.PARAMS_APP_VERSION, "getAppVersion", "osVersion", "getOsVersion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class WeblabClientInfo {
    private final String appName;
    private final String appVersion;
    private final String clientIdentifier;
    private final String osVersion;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeblabClientInfo)) {
            return false;
        }
        WeblabClientInfo weblabClientInfo = (WeblabClientInfo) other;
        return Intrinsics.areEqual(this.clientIdentifier, weblabClientInfo.clientIdentifier) && Intrinsics.areEqual(this.appName, weblabClientInfo.appName) && Intrinsics.areEqual(this.appVersion, weblabClientInfo.appVersion) && Intrinsics.areEqual(this.osVersion, weblabClientInfo.osVersion);
    }

    public int hashCode() {
        return (((((this.clientIdentifier.hashCode() * 31) + this.appName.hashCode()) * 31) + this.appVersion.hashCode()) * 31) + this.osVersion.hashCode();
    }

    public String toString() {
        return "WeblabClientInfo(clientIdentifier=" + this.clientIdentifier + ", appName=" + this.appName + ", appVersion=" + this.appVersion + ", osVersion=" + this.osVersion + ')';
    }

    public WeblabClientInfo(String clientIdentifier, String appName, String appVersion, String osVersion) {
        Intrinsics.checkNotNullParameter(clientIdentifier, "clientIdentifier");
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        this.clientIdentifier = clientIdentifier;
        this.appName = appName;
        this.appVersion = appVersion;
        this.osVersion = osVersion;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getClientIdentifier() {
        return this.clientIdentifier;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }
}
