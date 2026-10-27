package com.amazon.mp3.net;

import android.net.Uri;
import com.amazon.mp3.library.service.sync.SSLHandshakeUtil;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractHttpClient<T> {
    private volatile boolean mCancel;
    private volatile int mStatusCode;
    private final String mTAG = getClass().getSimpleName();

    public static class CanceledException extends HttpClientException {
    }

    public static class EmptyResponseException extends IncompleteResultException {
    }

    public static class InvalidResultException extends HttpClientException {
    }

    public static class NoWifiException extends FailedException {
    }

    public abstract T getResult() throws InvalidResultException, IncompleteResultException;

    protected void onCanceled() {
    }

    protected abstract void onDataReceived(byte[] data, int length) throws HttpClientException;

    protected void onFailed(Exception exception) {
    }

    protected void onFinished() {
    }

    protected boolean onProcessRawResponse(HttpURLConnection httpConnection, InputStream responseStream) throws HttpClientException {
        return false;
    }

    protected void onStarted() {
    }

    protected boolean validateHttpStatusCode(int status) {
        return status == 200;
    }

    protected void validateResponse() throws ServiceException {
    }

    protected boolean wantsRawResponse() {
        return false;
    }

    public static class HttpClientException extends Exception {
        public HttpClientException() {
        }

        public HttpClientException(Exception cause) {
            super(cause);
        }
    }

    public static class FailedException extends HttpClientException {
        public FailedException() {
        }

        public FailedException(Exception cause) {
            super(cause);
        }
    }

    public static class IncompleteResultException extends HttpClientException {
        public IncompleteResultException() {
        }

        public IncompleteResultException(Exception cause) {
            super(cause);
        }
    }

    public static class UnexpectedHttpStatusException extends HttpClientException {
        private final int mStatusCode;

        public UnexpectedHttpStatusException(int statusCode) {
            this.mStatusCode = statusCode;
        }

        public int getHttpStatus() {
            return this.mStatusCode;
        }
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public synchronized AbstractHttpClient<T> execute(Uri uri) throws FailedException, CanceledException, UnexpectedHttpStatusException {
        return execute(HttpRequestBuilder.fromUri(uri));
    }

    public static StringBuilder createRequestUrl(HttpRequestBuilder builder) {
        String strBuildArguments;
        StringBuilder sb = new StringBuilder();
        sb.append(builder.getUseHttps() ? "https://" : "http://");
        sb.append(builder.getHost());
        if (!builder.getPath().startsWith("/")) {
            sb.append(JsonPointer.SEPARATOR);
        }
        sb.append(builder.getPath());
        if (builder.hasArguments() && (strBuildArguments = builder.buildArguments()) != null && strBuildArguments.length() > 0) {
            sb.append('?');
            sb.append(strBuildArguments);
        }
        return sb;
    }

    public synchronized AbstractHttpClient<T> execute(HttpRequestBuilder requestBuilder) throws FailedException, CanceledException, UnexpectedHttpStatusException {
        HttpURLConnection httpURLConnection;
        int i = 0;
        this.mCancel = false;
        StringBuilder sbCreateRequestUrl = createRequestUrl(requestBuilder);
        try {
            try {
                httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(sbCreateRequestUrl.toString()).openConnection()));
                setConnectionOptions(httpURLConnection, requestBuilder);
                httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, requestBuilder.getUserAgent());
                if (requestBuilder.hasHeaders()) {
                    requestBuilder.setHeadersOn(httpURLConnection);
                }
                int method = requestBuilder.getMethod();
                String body = requestBuilder.getBody();
                if (body != null && body.length() > 0) {
                    i = 1;
                }
                if (method == -1) {
                    method = i;
                }
                if (method == 1 && i != 0) {
                    try {
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.getOutputStream().write(body.getBytes(ParserUtil.UTF_8));
                    } catch (SSLHandshakeException e) {
                        SSLHandshakeUtil.captureAdditionalDebugLogs(e, httpURLConnection.getURL().toString());
                        MetricsLogger.sendSSLHandShakeFailedEvent(httpURLConnection.getURL().toString(), e);
                        onFailed(e);
                        throw new FailedException(e);
                    } catch (IOException e2) {
                        onFailed(e2);
                        throw new FailedException(e2);
                    }
                }
            } catch (IOException e3) {
                Log.warning(this.mTAG, "IOException");
                Log.debugBuildOnly(this.mTAG, "IOException on url: %s", sbCreateRequestUrl);
                ConnectivityUtil.logConnectivityDebugInfo();
                onFailed(e3);
                throw new FailedException(e3);
            }
        } catch (MalformedURLException e4) {
            Log.error(this.mTAG, "Malformed url");
            Log.debugBuildOnly(this.mTAG, "Malformed url: %s", sbCreateRequestUrl);
            onFailed(e4);
            throw new FailedException(e4);
        }
        return execute(httpURLConnection);
    }

    protected boolean cancelRequested() {
        return this.mCancel;
    }

    protected void setConnectionOptions(HttpURLConnection connection, HttpRequestBuilder builder) {
        if (connection instanceof HttpsURLConnection) {
        }
        connection.setConnectTimeout(builder.getConnectionTimeout());
        connection.setReadTimeout(builder.getReadTimeout());
        connection.setUseCaches(false);
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0112: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:53:0x0112 */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.mp3.net.AbstractHttpClient<T> execute(java.net.HttpURLConnection r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.net.AbstractHttpClient.execute(java.net.HttpURLConnection):com.amazon.mp3.net.AbstractHttpClient");
    }

    private void throwIfCanceled() throws CanceledException {
        if (this.mCancel) {
            throw new CanceledException();
        }
    }
}
