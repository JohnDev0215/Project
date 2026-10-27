package com.loostone.libserver.version2.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class FileRequest extends Request<Boolean> {
    private final String filePath;
    private final Response.Listener<Boolean> listener;

    public FileRequest(int i, String str, String str2, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.filePath = str2;
        this.listener = listener;
    }

    @Override // com.android.volley.Request
    public Response<Boolean> parseNetworkResponse(NetworkResponse networkResponse) {
        FileOutputStream fileOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        byte[] bArr;
        boolean z = false;
        try {
            fileOutputStream = new FileOutputStream(this.filePath);
            byteArrayInputStream = new ByteArrayInputStream(networkResponse.data);
            bArr = new byte[4096];
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            int i = byteArrayInputStream.read(bArr);
            if (i == -1) {
                break;
            }
            fileOutputStream.write(bArr, 0, i);
            return Response.success(Boolean.valueOf(z), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayInputStream.close();
        z = true;
        return Response.success(Boolean.valueOf(z), HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override // com.android.volley.Request
    public void deliverResponse(Boolean bool) {
        this.listener.onResponse(bool);
    }

    public FileRequest(String str, String str2, Response.Listener<Boolean> listener, Response.ErrorListener errorListener) {
        this(0, str, str2, listener, errorListener);
    }
}
