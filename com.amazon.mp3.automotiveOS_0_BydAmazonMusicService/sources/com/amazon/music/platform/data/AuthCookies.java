package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import java.net.HttpCookie;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthCookies.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\f\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\t¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\f\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\t8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/platform/data/AuthCookies;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Ljava/net/HttpCookie;", "Lcom/amazon/music/platform/data/Cookie;", "cookies", "Ljava/util/List;", "getCookies", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AuthCookies {
    private final List<HttpCookie> cookies;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AuthCookies) && Intrinsics.areEqual(this.cookies, ((AuthCookies) other).cookies);
    }

    public int hashCode() {
        return this.cookies.hashCode();
    }

    public String toString() {
        return "AuthCookies(cookies=" + this.cookies + ')';
    }

    public AuthCookies(List<HttpCookie> cookies) {
        Intrinsics.checkNotNullParameter(cookies, "cookies");
        this.cookies = cookies;
        PlatformProviderUtilKt.freeze(this);
    }

    public final List<HttpCookie> getCookies() {
        return this.cookies;
    }
}
