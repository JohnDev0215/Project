package com.amazon.mp3.net;

import android.net.Uri;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsonHttpClient extends AbstractHttpClient<JSONObject> {
    private static final String LOGTAG = "JsonHttpClient";
    private JSONObject mResult;
    private volatile boolean mSuccessful = false;
    private Set<Integer> mValidStatusCodes;

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected boolean wantsRawResponse() {
        return true;
    }

    public static class JsonParseException extends AbstractHttpClient.HttpClientException {
        JsonParseException(Exception ex) {
            super(ex);
        }
    }

    public static JSONObject getJson(Uri uri) {
        try {
            return new JsonHttpClient().execute(uri).getResult();
        } catch (AbstractHttpClient.HttpClientException unused) {
            return null;
        }
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    public JSONObject getResult() throws AbstractHttpClient.IncompleteResultException {
        if (!this.mSuccessful) {
            throw new AbstractHttpClient.IncompleteResultException();
        }
        return this.mResult;
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void onDataReceived(byte[] data, int length) throws AbstractHttpClient.HttpClientException {
        throw new RuntimeException("JsonHttpClient.onDataReceived: should never be callled");
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected boolean onProcessRawResponse(HttpURLConnection httpConnection, InputStream responseStream) throws AbstractHttpClient.HttpClientException {
        String string;
        int i;
        int contentLength = httpConnection.getContentLength();
        if (contentLength <= 0) {
            contentLength = 4096;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(contentLength);
        try {
            byte[] bArr = new byte[4096];
            while (!cancelRequested() && (i = responseStream.read(bArr)) != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            }
            if (cancelRequested()) {
                return true;
            }
            try {
                string = byteArrayOutputStream.toString(ParserUtil.UTF_8);
            } catch (UnsupportedEncodingException unused) {
                string = byteArrayOutputStream.toString();
            }
            if (string.isEmpty()) {
                throw new AbstractHttpClient.EmptyResponseException();
            }
            if ("Error calling PAS".equals(string)) {
                string = "{\"error\":\"" + string + "\"}";
            }
            try {
                this.mResult = new JSONObject(string);
                return true;
            } catch (JSONException e) {
                Log.error(LOGTAG, "Failed to parse response to JSON: %s", string);
                throw new JsonParseException(e);
            }
        } catch (IOException e2) {
            throw new AbstractHttpClient.IncompleteResultException(e2);
        }
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void onStarted() {
        this.mSuccessful = false;
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void onFinished() {
        this.mSuccessful = true;
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected boolean validateHttpStatusCode(int status) {
        Set<Integer> set;
        return super.validateHttpStatusCode(status) || ((set = this.mValidStatusCodes) != null && set.contains(Integer.valueOf(status)));
    }

    public void setValidHttpStatusCodes(Set<Integer> statusCodes) {
        this.mValidStatusCodes = statusCodes;
    }
}
