package A6;

import A9.k;
import B5.m;
import Q8.h;
import android.util.Base64;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes.dex */
public final class f extends m {
    @Override // B5.m
    public final HttpURLConnection g(String str) {
        boolean zM0 = h.M0("https://segment-middleware.stingray.com/api");
        if (zM0) {
            return super.g(str);
        }
        if (zM0) {
            throw new k();
        }
        HttpURLConnection httpURLConnectionC = m.c("https://segment-middleware.stingray.com/api/v1/import");
        byte[] bytes = "rFuQox9HMsCZgrJgQCYNgiK6zH7CH6ED:".getBytes(Q8.a.f7211a);
        A7.m.e(bytes, "getBytes(...)");
        httpURLConnectionC.setRequestProperty("Authorization", "Basic " + Base64.encodeToString(bytes, 2));
        httpURLConnectionC.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnectionC.setDoOutput(true);
        httpURLConnectionC.setChunkedStreamingMode(0);
        return httpURLConnectionC;
    }
}
