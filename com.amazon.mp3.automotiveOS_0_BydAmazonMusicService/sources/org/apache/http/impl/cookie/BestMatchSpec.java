package org.apache.http.impl.cookie;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class BestMatchSpec extends DefaultCookieSpec {
    @Override // org.apache.http.impl.cookie.DefaultCookieSpec
    public String toString() {
        return "best-match";
    }

    public BestMatchSpec(String[] strArr, boolean z) {
        super(strArr, z);
    }

    public BestMatchSpec() {
        this(null, false);
    }
}
