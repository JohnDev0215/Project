package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdpInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/data/AdpInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "privateKey", "Ljava/lang/String;", "getPrivateKey", "()Ljava/lang/String;", "adpToken", "getAdpToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AdpInfo {
    private final String adpToken;
    private final String privateKey;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdpInfo)) {
            return false;
        }
        AdpInfo adpInfo = (AdpInfo) other;
        return Intrinsics.areEqual(this.privateKey, adpInfo.privateKey) && Intrinsics.areEqual(this.adpToken, adpInfo.adpToken);
    }

    public int hashCode() {
        return (this.privateKey.hashCode() * 31) + this.adpToken.hashCode();
    }

    public String toString() {
        return "AdpInfo(privateKey=" + this.privateKey + ", adpToken=" + this.adpToken + ')';
    }

    public AdpInfo(String privateKey, String adpToken) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(adpToken, "adpToken");
        this.privateKey = privateKey;
        this.adpToken = adpToken;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getPrivateKey() {
        return this.privateKey;
    }

    public final String getAdpToken() {
        return this.adpToken;
    }
}
