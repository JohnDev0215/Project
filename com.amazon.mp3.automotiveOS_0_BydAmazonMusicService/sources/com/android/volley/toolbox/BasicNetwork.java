package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;

/* JADX INFO: loaded from: classes5.dex */
public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b9, code lost:
    
        throw new java.io.IOException();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.android.volley.toolbox.BasicNetwork] */
    @Override // com.android.volley.Network
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r24) throws com.android.volley.VolleyError {
        /*
            Method dump skipped, instruction units count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > SLOW_REQUEST_THRESHOLD_MS) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(timeoutMs)));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry == null) {
            return;
        }
        String str = entry.etag;
        if (str != null) {
            map.put("If-None-Match", str);
        }
        if (entry.lastModified > 0) {
            map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
        }
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws ServerError, IOException {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            byte[] buf = this.mPool.getBuf(1024);
            while (true) {
                int i = content.read(buf);
                if (i == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(buf, 0, i);
            }
            byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException unused) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header header : headerArr) {
            BasicHeader basicHeader = (BasicHeader) header;
            treeMap.put(basicHeader.getName(), basicHeader.getValue());
        }
        return treeMap;
    }
}
