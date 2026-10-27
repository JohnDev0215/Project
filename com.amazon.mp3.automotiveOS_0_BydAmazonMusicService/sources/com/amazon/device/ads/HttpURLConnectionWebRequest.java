package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class HttpURLConnectionWebRequest extends WebRequest {
    private static final String LOGTAG = "HttpURLConnectionWebRequest";
    private HttpURLConnection connection;

    HttpURLConnectionWebRequest() {
    }

    @Override // com.amazon.device.ads.WebRequest
    protected WebRequest.WebResponse doHttpNetworkCall(URL url) throws Throwable {
        if (this.connection != null) {
            closeConnection();
        }
        try {
            HttpURLConnection httpURLConnectionOpenConnection = openConnection(url);
            this.connection = httpURLConnectionOpenConnection;
            setupRequestProperties(httpURLConnectionOpenConnection);
            try {
                this.connection.connect();
                return prepareResponse(this.connection);
            } catch (SocketTimeoutException e) {
                getLogger().e("Socket timed out while connecting to URL: %s", e.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_TIMEOUT, "Socket timed out while connecting to URL", e);
            } catch (Exception e2) {
                getLogger().e("Problem while connecting to URL: %s", e2.getMessage());
                throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Probem while connecting to URL", e2);
            }
        } catch (IOException e3) {
            getLogger().e("Problem while opening the URL connection: %s", e3.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while opening the URL connection", e3);
        }
    }

    protected HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
    }

    @Override // com.amazon.device.ads.WebRequest
    protected void closeConnection() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            this.connection = null;
        }
    }

    protected void setupRequestProperties(HttpURLConnection httpURLConnection) throws Throwable {
        try {
            httpURLConnection.setRequestMethod(getHttpMethod().name());
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().equals("")) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpURLConnection.setConnectTimeout(getTimeout());
            httpURLConnection.setReadTimeout(getTimeout());
            logUrl(httpURLConnection.getURL().toString());
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod[getHttpMethod().ordinal()];
            if (i == 1) {
                httpURLConnection.setDoOutput(false);
            } else {
                if (i != 2) {
                    return;
                }
                httpURLConnection.setDoOutput(true);
                writePostBody(httpURLConnection);
            }
        } catch (ProtocolException e) {
            getLogger().e("Invalid client protocol: %s", e.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.INVALID_CLIENT_PROTOCOL, "Invalid client protocol", e);
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.HttpURLConnectionWebRequest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod;

        static {
            int[] iArr = new int[WebRequest.HttpMethod.values().length];
            $SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod = iArr;
            try {
                iArr[WebRequest.HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$WebRequest$HttpMethod[WebRequest.HttpMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void writePostBody(HttpURLConnection httpURLConnection) throws Throwable {
        String requestBody;
        StringBuilder sb = new StringBuilder();
        String str = this.requestBody;
        if (str != null) {
            sb.append(str);
        } else {
            HashMap<String, String> map = this.postParameters;
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : this.postParameters.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(WebUtils.getURLEncodedString(entry.getValue()));
                    sb.append("&");
                }
                sb.deleteCharAt(sb.lastIndexOf("&"));
            }
        }
        if (this.logRequestBodyEnabled && getRequestBody() != null) {
            if (!this.logSessionIdEnabled) {
                requestBody = getRequestBody().replaceAll(",\\s*\"\\s*sessionId\\s*\"\\s*:\\s*\".*?\"|\\s*\"\\s*sessionId\\s*\"\\s*:\\s*\".*?\"\\s*,*", "");
            } else {
                requestBody = getRequestBody();
            }
            getLogger().d("Request Body: %s", requestBody);
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(httpURLConnection.getOutputStream(), ParserUtil.UTF_8);
                try {
                    outputStreamWriter2.write(sb.toString());
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException e) {
                        getLogger().e("Problem while closing output stream writer for request body: %s", e.getMessage());
                        throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while closing output stream writer for request body", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    getLogger().e("Problem while creating output steam for request body: %s", e.getMessage());
                    throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while creating output steam for request body", e);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e3) {
                            getLogger().e("Problem while closing output stream writer for request body: %s", e3.getMessage());
                            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "Problem while closing output stream writer for request body", e3);
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected WebRequest.WebResponse prepareResponse(HttpURLConnection httpURLConnection) throws WebRequest.WebRequestException {
        WebRequest.WebResponse webResponse = new WebRequest.WebResponse();
        try {
            webResponse.setHttpStatusCode(httpURLConnection.getResponseCode());
            webResponse.setHttpStatus(httpURLConnection.getResponseMessage());
            if (webResponse.getHttpStatusCode() == 200) {
                try {
                    webResponse.setInputStream(httpURLConnection.getInputStream());
                } catch (IOException e) {
                    getLogger().e("IOException while reading the input stream from response: %s", e.getMessage());
                    throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while reading the input stream from response", e);
                }
            }
            return webResponse;
        } catch (IOException e2) {
            getLogger().e("IOException while getting the response status code: %s", e2.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_FAILURE, "IOException while getting the response status code", e2);
        } catch (IndexOutOfBoundsException e3) {
            getLogger().e("IndexOutOfBoundsException while getting the response status code: %s", e3.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.MALFORMED_URL, "IndexOutOfBoundsException while getting the response status code", e3);
        } catch (SocketTimeoutException e4) {
            getLogger().e("Socket Timeout while getting the response status code: %s", e4.getMessage());
            throw new WebRequest.WebRequestException(WebRequest.WebRequestStatus.NETWORK_TIMEOUT, "Socket Timeout while getting the response status code", e4);
        }
    }

    @Override // com.amazon.device.ads.WebRequest
    protected String getSubLogTag() {
        return LOGTAG;
    }
}
