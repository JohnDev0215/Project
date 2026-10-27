package org.apache.http.cookie;

/* JADX INFO: loaded from: classes7.dex */
public interface ClientCookie extends Cookie {
    boolean containsAttribute(String str);

    String getAttribute(String str);

    @Override // org.apache.http.cookie.Cookie
    /* synthetic */ int getVersion();
}
