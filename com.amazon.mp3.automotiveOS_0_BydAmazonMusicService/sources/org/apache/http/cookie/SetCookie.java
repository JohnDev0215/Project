package org.apache.http.cookie;

import java.util.Date;

/* JADX INFO: loaded from: classes7.dex */
public interface SetCookie extends Cookie {
    @Override // org.apache.http.cookie.Cookie
    /* synthetic */ int getVersion();

    void setComment(String str);

    void setDomain(String str);

    void setExpiryDate(Date date);

    void setPath(String str);

    void setSecure(boolean z);

    void setVersion(int i);
}
