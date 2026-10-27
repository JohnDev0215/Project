package org.apache.http.cookie;

/* JADX INFO: loaded from: classes7.dex */
public interface SetCookie2 extends SetCookie {
    @Override // org.apache.http.cookie.SetCookie, org.apache.http.cookie.Cookie
    /* synthetic */ int getVersion();

    void setCommentURL(String str);

    void setDiscard(boolean z);

    void setPorts(int[] iArr);

    @Override // org.apache.http.cookie.SetCookie
    /* synthetic */ void setVersion(int i);
}
