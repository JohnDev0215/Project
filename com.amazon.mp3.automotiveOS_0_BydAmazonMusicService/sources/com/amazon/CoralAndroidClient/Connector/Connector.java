package com.amazon.CoralAndroidClient.Connector;

import android.util.Log;
import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.ClientResponse;
import com.amazon.CoralAndroidClient.ClientBase.Unmarshaller;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class Connector {
    private HttpURLConnectionFactory mHttpURLConnectionFactory;
    private int mReadTimeout = 0;
    private int mConnectTimeout = 0;
    private int mMaxRetry = 5;
    private int mMaxRedirect = 10;
    private String mEndpoint = "";
    private URL mEndpointURL = null;
    private Proxy mProxy = null;
    private Set<TransmissionFilter> mTransmissionFilters = null;
    private ClientRequestEncoder mClientRequestEncoder = null;
    private SimpleDateFormat mAmzDateFormat = new SimpleDateFormat("EEE, d MMM yyyy k:m:s ZZZZ", Locale.getDefault());

    public static Connector create() throws NativeException {
        Connector connector = new Connector();
        connector.setMaxRedirect(10);
        connector.setMaxRetry(5);
        connector.setClientRequestEncoder(new ClientRequestJsonEncoder(ParserUtil.UTF_8));
        connector.setHttpURLConnectionFactory(new DefaultHttpURLConnectionFactory());
        return connector;
    }

    public ClientResponse send(ClientRequest clientRequest, Unmarshaller unmarshaller) throws NativeException {
        String str;
        if (this.mEndpointURL == null || (str = this.mEndpoint) == null || str.length() == 0) {
            throw new NativeException("empty endpoint");
        }
        beforeSend(clientRequest);
        ClientRequestEncoder clientRequestEncoder = this.mClientRequestEncoder;
        if (clientRequestEncoder == null) {
            throw new NativeException("ClientRequestEncoder is null.");
        }
        byte[] bArrEncode = clientRequestEncoder.encode(clientRequest);
        URL urlDealWithRedirect = this.mEndpointURL;
        int i = this.mMaxRetry;
        int i2 = this.mMaxRedirect;
        ClientResponse clientResponse = null;
        HttpURLConnection httpURLConnection = null;
        while (true) {
            if (i < 0) {
                throw new NativeException(String.format("exceed max retry times %d", Integer.valueOf(this.mMaxRetry)));
            }
            if (i2 < 0) {
                throw new NativeException(String.format("exceed max redirect times %d", Integer.valueOf(this.mMaxRedirect)));
            }
            try {
                try {
                    httpURLConnection = getHttpURLConnection(urlDealWithRedirect, this.mProxy);
                    doSend(httpURLConnection, clientRequest, bArrEncode);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode != 301 && responseCode != 302) {
                        if (responseCode != 400 && responseCode != 200) {
                            throw new NativeException(String.format("receive invalid HTTP response code %d", Integer.valueOf(responseCode)));
                        }
                        String strTranslateResponseDataToString = translateResponseDataToString(httpURLConnection, doReceive(httpURLConnection, responseCode));
                        boolean z = responseCode == 200;
                        httpURLConnection.disconnect();
                        if (strTranslateResponseDataToString == null) {
                            strTranslateResponseDataToString = "";
                        }
                        if (unmarshaller != null) {
                            if (z) {
                                clientResponse = new ClientResponse(unmarshaller.UnmarshalOutput(strTranslateResponseDataToString));
                            } else {
                                clientResponse = new ClientResponse(unmarshaller.UnmarshalException(strTranslateResponseDataToString));
                            }
                        }
                        afterSend(clientRequest, clientResponse);
                        return clientResponse;
                    }
                    i2--;
                    urlDealWithRedirect = dealWithRedirect(httpURLConnection, responseCode);
                } catch (UnsupportedEncodingException e) {
                    throw new NativeException(e);
                } catch (IOException e2) {
                    i--;
                    if (i < 0) {
                        throw new NativeException(e2);
                    }
                    if (httpURLConnection != null) {
                    }
                }
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        }
    }

    public void setConnectTimeout(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mConnectTimeout = i;
    }

    public int getConnectTimeout() {
        return this.mConnectTimeout;
    }

    public void setReadTimeout(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mReadTimeout = i;
    }

    public int getReadTimeout() {
        return this.mReadTimeout;
    }

    public void setEndpoint(URL url) {
        this.mEndpointURL = url;
        if (url != null) {
            this.mEndpoint = url.toString();
        } else {
            this.mEndpoint = "";
        }
    }

    public void setMaxRetry(int i) {
        if (i < 0) {
            i = 5;
        }
        this.mMaxRetry = i;
    }

    public int getMaxRetry() {
        return this.mMaxRetry;
    }

    public void setMaxRedirect(int i) {
        if (i < 0) {
            i = 10;
        }
        this.mMaxRedirect = i;
    }

    public int getMaxRedirect() {
        return this.mMaxRedirect;
    }

    public void setProxy(Proxy proxy) {
        this.mProxy = proxy;
    }

    public void setTransmissionFilterSet(Set<TransmissionFilter> set) {
        this.mTransmissionFilters = set;
    }

    public void setClientRequestEncoder(ClientRequestEncoder clientRequestEncoder) {
        this.mClientRequestEncoder = clientRequestEncoder;
    }

    public void setHttpURLConnectionFactory(HttpURLConnectionFactory httpURLConnectionFactory) {
        this.mHttpURLConnectionFactory = httpURLConnectionFactory;
    }

    protected void beforeSend(ClientRequest clientRequest) {
        Set<TransmissionFilter> set = this.mTransmissionFilters;
        if (set == null) {
            return;
        }
        Iterator<TransmissionFilter> it = set.iterator();
        while (it.hasNext()) {
            it.next().beforeSend(clientRequest);
        }
    }

    protected void afterSend(ClientRequest clientRequest, ClientResponse clientResponse) {
        Set<TransmissionFilter> set = this.mTransmissionFilters;
        if (set == null) {
            return;
        }
        Iterator<TransmissionFilter> it = set.iterator();
        while (it.hasNext()) {
            it.next().afterSend(clientRequest, clientResponse);
        }
    }

    protected HttpURLConnection getHttpURLConnection(URL url, Proxy proxy) throws NativeException {
        HttpURLConnectionFactory httpURLConnectionFactory = this.mHttpURLConnectionFactory;
        if (httpURLConnectionFactory == null) {
            throw new NativeException("HttpURLConnectionFactory is null.");
        }
        HttpURLConnection httpURLConnectionCreate = httpURLConnectionFactory.create(url, proxy);
        httpURLConnectionCreate.setInstanceFollowRedirects(false);
        httpURLConnectionCreate.setReadTimeout(this.mReadTimeout);
        httpURLConnectionCreate.setConnectTimeout(this.mConnectTimeout);
        return httpURLConnectionCreate;
    }

    protected void doSend(HttpURLConnection httpURLConnection, ClientRequest clientRequest, byte[] bArr) throws Throwable {
        httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        httpURLConnection.addRequestProperty("Content-Encoding", "amz-1.0");
        httpURLConnection.addRequestProperty(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
        httpURLConnection.addRequestProperty("X-Amz-Target", clientRequest.getOperationName());
        Map<String, String> headers = clientRequest.getHeaders();
        if (headers == null || !headers.containsKey("X-Amz-Date")) {
            httpURLConnection.addRequestProperty("X-Amz-Date", this.mAmzDateFormat.format(new Date()));
        }
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                try {
                    httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                } catch (NullPointerException e) {
                    Log.e("Connector", "null additional header", e);
                }
            }
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(httpURLConnection.getOutputStream());
            try {
                bufferedOutputStream2.write(bArr);
                bufferedOutputStream2.flush();
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e2) {
                    Log.e("Connector", "close getOutputStream() with exception", e2);
                }
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e3) {
                        Log.e("Connector", "close getOutputStream() with exception", e3);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected URL dealWithRedirect(HttpURLConnection httpURLConnection, int i) throws NativeException {
        URL url;
        String headerField = httpURLConnection.getHeaderField(HttpHeader.LOCATION);
        if (headerField == null) {
            throw new NativeException(String.format("redirect without location, status code %d", Integer.valueOf(i)));
        }
        try {
            if (headerField.startsWith("/")) {
                url = new URL(this.mEndpointURL, headerField);
            } else {
                url = new URL(headerField);
            }
            if (i == 301) {
                this.mEndpointURL = url;
                this.mEndpoint = url.toString();
            }
            return url;
        } catch (MalformedURLException e) {
            throw new NativeException(e);
        }
    }

    protected byte[] doReceive(HttpURLConnection httpURLConnection, int i) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            if (i == 200) {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            } else {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getErrorStream());
            }
            bufferedInputStream2 = bufferedInputStream;
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                int i2 = bufferedInputStream2.read(bArr);
                if (i2 <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i2);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                bufferedInputStream2.close();
            } catch (IOException e) {
                if (i == 200) {
                    Log.e("Connector", "close getInputStream() with exception", e);
                } else {
                    Log.e("Connector", "close getErrorStream() with exception", e);
                }
            }
            return byteArray;
        } catch (Throwable th) {
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException e2) {
                    if (i == 200) {
                        Log.e("Connector", "close getInputStream() with exception", e2);
                    } else {
                        Log.e("Connector", "close getErrorStream() with exception", e2);
                    }
                }
            }
            throw th;
        }
    }

    protected String translateResponseDataToString(HttpURLConnection httpURLConnection, byte[] bArr) throws UnsupportedEncodingException {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        String strSubstring = ParserUtil.UTF_8;
        String headerField = httpURLConnection.getHeaderField(HttpHeader.CONTENT_TYPE);
        if (headerField == null || headerField.length() == 0) {
            return new String(bArr, ParserUtil.UTF_8);
        }
        if (!headerField.startsWith("application/json")) {
            throw new UnsupportedEncodingException(String.format("only support application/json, content-type is %s", headerField));
        }
        int iIndexOf = headerField.indexOf("charset=");
        if (iIndexOf != -1) {
            strSubstring = headerField.substring(iIndexOf + 8);
        }
        return new String(bArr, strSubstring);
    }
}
