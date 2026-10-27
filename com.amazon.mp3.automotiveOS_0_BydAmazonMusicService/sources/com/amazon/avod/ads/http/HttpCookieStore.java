package com.amazon.avod.ads.http;

import java.net.URL;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface HttpCookieStore {
    @Nullable
    String getCookie(URL url);

    void setCookie(URL url, String str);
}
