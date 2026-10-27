package com.android.volley;

import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkResponse {
    public final byte[] data;
    public final Map<String, String> headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;

    public NetworkResponse(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.headers = map;
        this.notModified = z;
        this.networkTimeMs = j;
    }

    public NetworkResponse(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0L);
    }
}
