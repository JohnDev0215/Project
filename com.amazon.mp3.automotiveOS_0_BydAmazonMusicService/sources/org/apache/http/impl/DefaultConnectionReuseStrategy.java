package org.apache.http.impl;

import com.google.firebase.perf.FirebasePerformance;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes7.dex */
public class DefaultConnectionReuseStrategy implements ConnectionReuseStrategy {
    public static final DefaultConnectionReuseStrategy INSTANCE = new DefaultConnectionReuseStrategy();

    /* JADX WARN: Code restructure failed: missing block: B:31:0x008e, code lost:
    
        if (java.lang.Integer.parseInt(r9[0].getValue()) < 0) goto L32;
     */
    @Override // org.apache.http.ConnectionReuseStrategy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean keepAlive(org.apache.http.HttpResponse r8, org.apache.http.protocol.HttpContext r9) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.DefaultConnectionReuseStrategy.keepAlive(org.apache.http.HttpResponse, org.apache.http.protocol.HttpContext):boolean");
    }

    private boolean canResponseHaveBody(HttpRequest httpRequest, HttpResponse httpResponse) {
        int statusCode;
        return ((httpRequest != null && httpRequest.getRequestLine().getMethod().equalsIgnoreCase(FirebasePerformance.HttpMethod.HEAD)) || (statusCode = httpResponse.getStatusLine().getStatusCode()) < 200 || statusCode == 204 || statusCode == 304 || statusCode == 205) ? false : true;
    }
}
