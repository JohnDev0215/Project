package com.amazon.avod.ads.http;

import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
class NoopCookieStore implements HttpCookieStore {
    @Override // com.amazon.avod.ads.http.HttpCookieStore
    public String getCookie(URL url) {
        return null;
    }

    @Override // com.amazon.avod.ads.http.HttpCookieStore
    public void setCookie(URL url, String str) {
    }

    NoopCookieStore() {
    }
}
