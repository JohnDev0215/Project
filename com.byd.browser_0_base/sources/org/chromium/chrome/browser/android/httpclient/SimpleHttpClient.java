package org.chromium.chrome.browser.android.httpclient;

import defpackage.C0175b;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class SimpleHttpClient {

    public static class HttpResponse {
    }

    @CalledByNative
    public static HttpResponse createHttpResponse(int i, int i2, byte[] bArr, String[] strArr, String[] strArr2) {
        HashMap map = new HashMap();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (map.containsKey(strArr[i3])) {
                map.put(strArr[i3], C0175b.k((String) map.get(strArr[i3]), "\n", strArr2[i3]));
            } else {
                map.put(strArr[i3], strArr2[i3]);
            }
        }
        return new HttpResponse();
    }
}
