package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OAuthInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/data/OAuthInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "accessToken", "Ljava/lang/String;", "getAccessToken", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class OAuthInfo {
    private final String accessToken;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof OAuthInfo) && Intrinsics.areEqual(this.accessToken, ((OAuthInfo) other).accessToken);
    }

    public int hashCode() {
        return this.accessToken.hashCode();
    }

    public String toString() {
        return "OAuthInfo(accessToken=" + this.accessToken + ')';
    }

    public OAuthInfo(String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        this.accessToken = accessToken;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getAccessToken() {
        return this.accessToken;
    }
}
