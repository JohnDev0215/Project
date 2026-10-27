package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;

/* JADX INFO: compiled from: internal.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0006"}, d2 = {"Lokhttp3/Cookie;", "cookie", "", "forObsoleteRfc2965", "", "cookieToString", "okhttp"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "Internal")
public final class Internal {
    public static final String cookieToString(Cookie cookie, boolean z) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return cookie.toString$okhttp(z);
    }
}
