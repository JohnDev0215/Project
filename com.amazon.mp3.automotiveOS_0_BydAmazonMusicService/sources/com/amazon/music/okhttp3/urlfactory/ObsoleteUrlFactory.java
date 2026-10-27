package com.amazon.music.okhttp3.urlfactory;

import UpsellInterface.v1_0.PlaybackMode;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketPermission;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.security.AccessControlException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Pipe;
import okio.Timeout;

/* JADX INFO: loaded from: classes4.dex */
public final class ObsoleteUrlFactory implements URLStreamHandlerFactory, Cloneable {
    static final Comparator<String> FIELD_NAME_COMPARATOR;
    static final Set<String> METHODS = new LinkedHashSet(Arrays.asList(FirebasePerformance.HttpMethod.OPTIONS, FirebasePerformance.HttpMethod.GET, FirebasePerformance.HttpMethod.HEAD, FirebasePerformance.HttpMethod.POST, FirebasePerformance.HttpMethod.PUT, FirebasePerformance.HttpMethod.DELETE, FirebasePerformance.HttpMethod.TRACE, FirebasePerformance.HttpMethod.PATCH));
    static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT;
    static final TimeZone UTC;
    private OkHttpClient client;

    static {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        UTC = timeZone;
        ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();
        STANDARD_DATE_FORMAT = threadLocal;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(timeZone);
        threadLocal.set(simpleDateFormat);
        FIELD_NAME_COMPARATOR = new Comparator() { // from class: com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ObsoleteUrlFactory.lambda$static$0((String) obj, (String) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
    }

    public ObsoleteUrlFactory(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    @NonNull
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ObsoleteUrlFactory m2179clone() {
        return new ObsoleteUrlFactory(this.client);
    }

    public HttpURLConnection open(URL url) {
        return open(url, this.client.proxy());
    }

    HttpURLConnection open(URL url, @Nullable Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient okHttpClientBuild = this.client.newBuilder().proxy(proxy).build();
        if (protocol.equals("http")) {
            return new OkHttpURLConnection(url, okHttpClientBuild);
        }
        if (protocol.equals("https")) {
            return new OkHttpsURLConnection(url, okHttpClientBuild);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    @Override // java.net.URLStreamHandlerFactory
    public URLStreamHandler createURLStreamHandler(final String str) {
        if (str.equals("http") || str.equals("https")) {
            return new URLStreamHandler() { // from class: com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory.1
                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url) {
                    return ObsoleteUrlFactory.this.open(url);
                }

                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url, Proxy proxy) {
                    return ObsoleteUrlFactory.this.open(url, proxy);
                }

                @Override // java.net.URLStreamHandler
                protected int getDefaultPort() {
                    if (str.equals("http")) {
                        return 80;
                    }
                    if (str.equals("https")) {
                        return 443;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }

    static String format(Date date) {
        return STANDARD_DATE_FORMAT.get().format(date);
    }

    static boolean permitsRequestBody(String str) {
        return (str.equals(FirebasePerformance.HttpMethod.GET) || str.equals(FirebasePerformance.HttpMethod.HEAD)) ? false : true;
    }

    static boolean hasBody(Response response) {
        if (response.request().method().equals(FirebasePerformance.HttpMethod.HEAD)) {
            return false;
        }
        int iCode = response.code();
        return (((iCode >= 100 && iCode < 200) || iCode == 204 || iCode == 304) && contentLength(response.headers()) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) ? false : true;
    }

    static long contentLength(Headers headers) {
        String str = headers.get(HttpHeader.CONTENT_LENGTH);
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    static String responseSourceHeader(Response response) {
        if (response.networkResponse() == null) {
            if (response.cacheResponse() == null) {
                return PlaybackMode.NONE;
            }
            return "CACHE " + response.code();
        }
        if (response.cacheResponse() == null) {
            return "NETWORK " + response.code();
        }
        return "CONDITIONAL_CACHE " + response.networkResponse().code();
    }

    static String statusLineToString(Response response) {
        StringBuilder sb = new StringBuilder();
        sb.append(response.protocol() == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(response.code());
        sb.append(' ');
        sb.append(response.message());
        return sb.toString();
    }

    static String toHumanReadableAscii(String str) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt > 31 && iCodePointAt < 127) {
                iCharCount += Character.charCount(iCodePointAt);
            } else {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, iCharCount);
                buffer.writeUtf8CodePoint(63);
                while (true) {
                    iCharCount += Character.charCount(iCodePointAt);
                    if (iCharCount < length) {
                        iCodePointAt = str.codePointAt(iCharCount);
                        buffer.writeUtf8CodePoint((iCodePointAt <= 31 || iCodePointAt >= 127) ? 63 : iCodePointAt);
                    } else {
                        return buffer.readUtf8();
                    }
                }
            }
        }
        return str;
    }

    static Map<String, List<String>> toMultimap(Headers headers, @Nullable String str) {
        TreeMap treeMap = new TreeMap(FIELD_NAME_COMPARATOR);
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String strName = headers.name(i);
            String strValue = headers.value(i);
            ArrayList arrayList = new ArrayList();
            List list = (List) treeMap.get(strName);
            if (list != null) {
                arrayList.addAll(list);
            }
            arrayList.add(strValue);
            treeMap.put(strName, Collections.unmodifiableList(arrayList));
        }
        if (str != null) {
            treeMap.put(null, Collections.unmodifiableList(Collections.singletonList(str)));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    static String getSystemProperty(String str, @Nullable String str2) {
        try {
            String property = System.getProperty(str);
            return property != null ? property : str2;
        } catch (AccessControlException unused) {
            return str2;
        }
    }

    static String defaultUserAgent() {
        String systemProperty = getSystemProperty("http.agent", null);
        return systemProperty != null ? toHumanReadableAscii(systemProperty) : "ObsoleteUrlFactory";
    }

    static IOException propagate(Throwable th) throws IOException {
        if (th instanceof IOException) {
            throw ((IOException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        throw new AssertionError();
    }

    static final class OkHttpURLConnection extends HttpURLConnection implements Callback {
        Call call;
        private Throwable callFailure;
        OkHttpClient client;
        boolean connectPending;
        boolean executed;
        long fixedContentLength;
        Handshake handshake;
        private final Object lock;
        final NetworkInterceptor networkInterceptor;
        Response networkResponse;
        Proxy proxy;
        Headers.Builder requestHeaders;
        private Response response;
        Headers responseHeaders;

        OkHttpURLConnection(URL url, OkHttpClient okHttpClient) {
            super(url);
            this.networkInterceptor = new NetworkInterceptor();
            this.requestHeaders = new Headers.Builder();
            this.fixedContentLength = -1L;
            this.lock = new Object();
            this.connectPending = true;
            this.client = okHttpClient;
        }

        @Override // java.net.URLConnection
        public void connect() throws IOException {
            if (this.executed) {
                return;
            }
            Call callBuildCall = buildCall();
            this.executed = true;
            FirebasePerfOkHttpClient.enqueue(callBuildCall, this);
            synchronized (this.lock) {
                while (this.connectPending && this.response == null && this.callFailure == null) {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                Throwable th = this.callFailure;
                if (th != null) {
                    throw ObsoleteUrlFactory.propagate(th);
                }
            }
        }

        @Override // java.net.HttpURLConnection
        public void disconnect() {
            if (this.call == null) {
                return;
            }
            this.networkInterceptor.proceed();
            this.call.cancel();
        }

        @Override // java.net.HttpURLConnection
        public InputStream getErrorStream() {
            try {
                Response response = getResponse(true);
                if (ObsoleteUrlFactory.hasBody(response) && response.code() >= 400) {
                    return response.body().byteStream();
                }
            } catch (IOException unused) {
            }
            return null;
        }

        Headers getHeaders() throws IOException {
            if (this.responseHeaders == null) {
                Response response = getResponse(true);
                this.responseHeaders = response.headers().newBuilder().add("ObsoleteUrlFactory-Selected-Protocol", response.protocol().getProtocol()).add("ObsoleteUrlFactory-Response-Source", ObsoleteUrlFactory.responseSourceHeader(response)).build();
            }
            return this.responseHeaders;
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderField(int i) {
            try {
                Headers headers = getHeaders();
                if (i >= 0 && i < headers.size()) {
                    return headers.value(i);
                }
            } catch (IOException unused) {
            }
            return null;
        }

        @Override // java.net.URLConnection
        public String getHeaderField(String str) {
            String strStatusLineToString;
            try {
                if (str == null) {
                    strStatusLineToString = ObsoleteUrlFactory.statusLineToString(getResponse(true));
                } else {
                    strStatusLineToString = getHeaders().get(str);
                }
                return strStatusLineToString;
            } catch (IOException unused) {
                return null;
            }
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderFieldKey(int i) {
            try {
                Headers headers = getHeaders();
                if (i >= 0 && i < headers.size()) {
                    return headers.name(i);
                }
            } catch (IOException unused) {
            }
            return null;
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getHeaderFields() {
            try {
                return ObsoleteUrlFactory.toMultimap(getHeaders(), ObsoleteUrlFactory.statusLineToString(getResponse(true)));
            } catch (IOException unused) {
                return Collections.emptyMap();
            }
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getRequestProperties() {
            if (((HttpURLConnection) this).connected) {
                throw new IllegalStateException("Cannot access request header fields after connection is set");
            }
            return ObsoleteUrlFactory.toMultimap(this.requestHeaders.build(), null);
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() throws IOException {
            if (!((HttpURLConnection) this).doInput) {
                throw new ProtocolException("This protocol does not support input");
            }
            Response response = getResponse(false);
            if (response.code() >= 400) {
                throw new FileNotFoundException(((HttpURLConnection) this).url.toString());
            }
            return response.body().byteStream();
        }

        @Override // java.net.URLConnection
        public OutputStream getOutputStream() throws IOException {
            OutputStreamRequestBody outputStreamRequestBody = (OutputStreamRequestBody) buildCall().request().body();
            if (outputStreamRequestBody == null) {
                throw new ProtocolException("method does not support a request body: " + ((HttpURLConnection) this).method);
            }
            if (outputStreamRequestBody instanceof StreamedRequestBody) {
                connect();
                this.networkInterceptor.proceed();
            }
            if (outputStreamRequestBody.closed) {
                throw new ProtocolException("cannot write request body after response has been read");
            }
            return outputStreamRequestBody.outputStream;
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public Permission getPermission() {
            int iDefaultPort;
            URL url = getURL();
            String host = url.getHost();
            if (url.getPort() != -1) {
                iDefaultPort = url.getPort();
            } else {
                iDefaultPort = HttpUrl.defaultPort(url.getProtocol());
            }
            if (usingProxy()) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) this.client.proxy().address();
                host = inetSocketAddress.getHostName();
                iDefaultPort = inetSocketAddress.getPort();
            }
            return new SocketPermission(host + ":" + iDefaultPort, "connect, resolve");
        }

        @Override // java.net.URLConnection
        public String getRequestProperty(String str) {
            if (str == null) {
                return null;
            }
            return this.requestHeaders.get(str);
        }

        @Override // java.net.URLConnection
        public void setConnectTimeout(int i) {
            this.client = this.client.newBuilder().connectTimeout(i, TimeUnit.MILLISECONDS).build();
        }

        @Override // java.net.HttpURLConnection
        public void setInstanceFollowRedirects(boolean z) {
            this.client = this.client.newBuilder().followRedirects(z).build();
        }

        @Override // java.net.HttpURLConnection
        public boolean getInstanceFollowRedirects() {
            return this.client.followRedirects();
        }

        @Override // java.net.URLConnection
        public int getConnectTimeout() {
            return this.client.connectTimeoutMillis();
        }

        @Override // java.net.URLConnection
        public void setReadTimeout(int i) {
            this.client = this.client.newBuilder().readTimeout(i, TimeUnit.MILLISECONDS).build();
        }

        @Override // java.net.URLConnection
        public int getReadTimeout() {
            return this.client.readTimeoutMillis();
        }

        private Call buildCall() throws IOException {
            OutputStreamRequestBody bufferedRequestBody;
            Call call = this.call;
            if (call != null) {
                return call;
            }
            boolean z = true;
            ((HttpURLConnection) this).connected = true;
            if (((HttpURLConnection) this).doOutput) {
                if (((HttpURLConnection) this).method.equals(FirebasePerformance.HttpMethod.GET)) {
                    ((HttpURLConnection) this).method = FirebasePerformance.HttpMethod.POST;
                } else if (!ObsoleteUrlFactory.permitsRequestBody(((HttpURLConnection) this).method)) {
                    throw new ProtocolException(((HttpURLConnection) this).method + " does not support writing");
                }
            }
            if (this.requestHeaders.get(HttpHeader.USER_AGENT) == null) {
                this.requestHeaders.add(HttpHeader.USER_AGENT, ObsoleteUrlFactory.defaultUserAgent());
            }
            if (ObsoleteUrlFactory.permitsRequestBody(((HttpURLConnection) this).method)) {
                if (this.requestHeaders.get(HttpHeader.CONTENT_TYPE) == null) {
                    this.requestHeaders.add(HttpHeader.CONTENT_TYPE, "application/x-www-form-urlencoded");
                }
                long j = -1;
                if (this.fixedContentLength == -1 && ((HttpURLConnection) this).chunkLength <= 0) {
                    z = false;
                }
                String str = this.requestHeaders.get(HttpHeader.CONTENT_LENGTH);
                long j2 = this.fixedContentLength;
                if (j2 != -1) {
                    j = j2;
                } else if (str != null) {
                    j = Long.parseLong(str);
                }
                if (z) {
                    bufferedRequestBody = new StreamedRequestBody(j);
                } else {
                    bufferedRequestBody = new BufferedRequestBody(j);
                }
                bufferedRequestBody.timeout.timeout(this.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
            } else {
                bufferedRequestBody = null;
            }
            try {
                HttpUrl httpUrl = HttpUrl.get(getURL().toString());
                if ("gzip".equals(this.requestHeaders.get("Accept-Encoding")) && this.requestHeaders.get("Range") == null) {
                    this.requestHeaders.removeAll("Accept-Encoding");
                }
                Request requestBuild = new Request.Builder().url(httpUrl).headers(this.requestHeaders.build()).method(((HttpURLConnection) this).method, bufferedRequestBody).build();
                OkHttpClient.Builder builderNewBuilder = this.client.newBuilder();
                builderNewBuilder.interceptors().add(UnexpectedException.INTERCEPTOR);
                builderNewBuilder.networkInterceptors().add(this.networkInterceptor);
                builderNewBuilder.dispatcher(new Dispatcher(this.client.dispatcher().executorService()));
                if (!getUseCaches()) {
                    builderNewBuilder.cache(null);
                }
                Call callNewCall = builderNewBuilder.build().newCall(requestBuild);
                this.call = callNewCall;
                return callNewCall;
            } catch (IllegalArgumentException e) {
                MalformedURLException malformedURLException = new MalformedURLException();
                malformedURLException.initCause(e);
                throw malformedURLException;
            }
        }

        private Response getResponse(boolean z) throws IOException {
            Response response;
            synchronized (this.lock) {
                Response response2 = this.response;
                if (response2 != null) {
                    return response2;
                }
                Throwable th = this.callFailure;
                if (th != null) {
                    if (!z || (response = this.networkResponse) == null) {
                        throw ObsoleteUrlFactory.propagate(th);
                    }
                    return response;
                }
                Call callBuildCall = buildCall();
                this.networkInterceptor.proceed();
                OutputStreamRequestBody outputStreamRequestBody = (OutputStreamRequestBody) callBuildCall.request().body();
                if (outputStreamRequestBody != null) {
                    outputStreamRequestBody.outputStream.close();
                }
                if (this.executed) {
                    synchronized (this.lock) {
                        while (this.response == null && this.callFailure == null) {
                            try {
                                try {
                                    this.lock.wait();
                                } catch (InterruptedException unused) {
                                    Thread.currentThread().interrupt();
                                    throw new InterruptedIOException();
                                }
                            } finally {
                            }
                        }
                    }
                } else {
                    this.executed = true;
                    try {
                        onResponse(callBuildCall, FirebasePerfOkHttpClient.execute(callBuildCall));
                    } catch (IOException e) {
                        onFailure(callBuildCall, e);
                    }
                }
                synchronized (this.lock) {
                    Throwable th2 = this.callFailure;
                    if (th2 != null) {
                        throw ObsoleteUrlFactory.propagate(th2);
                    }
                    Response response3 = this.response;
                    if (response3 != null) {
                        return response3;
                    }
                    throw new AssertionError();
                }
            }
        }

        @Override // java.net.HttpURLConnection
        public boolean usingProxy() {
            if (this.proxy != null) {
                return true;
            }
            Proxy proxy = this.client.proxy();
            return (proxy == null || proxy.type() == Proxy.Type.DIRECT) ? false : true;
        }

        @Override // java.net.HttpURLConnection
        public String getResponseMessage() throws IOException {
            return getResponse(true).message();
        }

        @Override // java.net.HttpURLConnection
        public int getResponseCode() throws IOException {
            return getResponse(true).code();
        }

        @Override // java.net.URLConnection
        public void setRequestProperty(String str, String str2) {
            if (((HttpURLConnection) this).connected) {
                throw new IllegalStateException("Cannot set request property after connection is made");
            }
            if (str == null) {
                throw new NullPointerException("field == null");
            }
            if (str2 == null) {
                return;
            }
            this.requestHeaders.set(str, str2);
        }

        @Override // java.net.URLConnection
        public void setIfModifiedSince(long j) {
            super.setIfModifiedSince(j);
            if (((HttpURLConnection) this).ifModifiedSince != 0) {
                this.requestHeaders.set("If-Modified-Since", ObsoleteUrlFactory.format(new Date(((HttpURLConnection) this).ifModifiedSince)));
            } else {
                this.requestHeaders.removeAll("If-Modified-Since");
            }
        }

        @Override // java.net.URLConnection
        public void addRequestProperty(String str, String str2) {
            if (((HttpURLConnection) this).connected) {
                throw new IllegalStateException("Cannot add request property after connection is made");
            }
            if (str == null) {
                throw new NullPointerException("field == null");
            }
            if (str2 == null) {
                return;
            }
            this.requestHeaders.add(str, str2);
        }

        @Override // java.net.HttpURLConnection
        public void setRequestMethod(String str) throws ProtocolException {
            Set<String> set = ObsoleteUrlFactory.METHODS;
            if (!set.contains(str)) {
                throw new ProtocolException("Expected one of " + set + " but was " + str);
            }
            ((HttpURLConnection) this).method = str;
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(int i) {
            setFixedLengthStreamingMode(i);
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(long j) {
            if (((HttpURLConnection) this).connected) {
                throw new IllegalStateException("Already connected");
            }
            if (((HttpURLConnection) this).chunkLength > 0) {
                throw new IllegalStateException("Already in chunked mode");
            }
            if (j < 0) {
                throw new IllegalArgumentException("contentLength < 0");
            }
            this.fixedContentLength = j;
            ((HttpURLConnection) this).fixedContentLength = (int) Math.min(j, 2147483647L);
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            synchronized (this.lock) {
                boolean z = iOException instanceof UnexpectedException;
                IOException cause = iOException;
                if (z) {
                    cause = iOException.getCause();
                }
                this.callFailure = cause;
                this.lock.notifyAll();
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            synchronized (this.lock) {
                this.response = response;
                this.handshake = response.handshake();
                ((HttpURLConnection) this).url = response.request().url().url();
                this.lock.notifyAll();
            }
        }

        final class NetworkInterceptor implements Interceptor {
            private boolean proceed;

            NetworkInterceptor() {
            }

            public void proceed() {
                synchronized (OkHttpURLConnection.this.lock) {
                    this.proceed = true;
                    OkHttpURLConnection.this.lock.notifyAll();
                }
            }

            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                synchronized (OkHttpURLConnection.this.lock) {
                    OkHttpURLConnection okHttpURLConnection = OkHttpURLConnection.this;
                    okHttpURLConnection.connectPending = false;
                    okHttpURLConnection.proxy = chain.connection().getRoute().proxy();
                    OkHttpURLConnection.this.handshake = chain.connection().getHandshake();
                    OkHttpURLConnection.this.lock.notifyAll();
                    while (!this.proceed) {
                        try {
                            OkHttpURLConnection.this.lock.wait();
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            throw new InterruptedIOException();
                        }
                    }
                }
                if (request.body() instanceof OutputStreamRequestBody) {
                    request = ((OutputStreamRequestBody) request.body()).prepareToSendRequest(request);
                }
                Response responseProceed = chain.proceed(request);
                synchronized (OkHttpURLConnection.this.lock) {
                    OkHttpURLConnection okHttpURLConnection2 = OkHttpURLConnection.this;
                    okHttpURLConnection2.networkResponse = responseProceed;
                    ((HttpURLConnection) okHttpURLConnection2).url = responseProceed.request().url().url();
                }
                return responseProceed;
            }
        }
    }

    static abstract class OutputStreamRequestBody extends RequestBody {
        boolean closed;
        long expectedContentLength;
        OutputStream outputStream;
        Timeout timeout;

        @Override // okhttp3.RequestBody
        @Nullable
        /* JADX INFO: renamed from: contentType */
        public final MediaType getContentType() {
            return null;
        }

        public Request prepareToSendRequest(Request request) throws IOException {
            return request;
        }

        OutputStreamRequestBody() {
        }

        void initOutputStream(final BufferedSink bufferedSink, final long j) {
            this.timeout = bufferedSink.getTimeout();
            this.expectedContentLength = j;
            this.outputStream = new OutputStream() { // from class: com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory.OutputStreamRequestBody.1
                private long bytesReceived;

                @Override // java.io.OutputStream
                public void write(int i) throws IOException {
                    write(new byte[]{(byte) i}, 0, 1);
                }

                @Override // java.io.OutputStream
                public void write(byte[] bArr, int i, int i2) throws IOException {
                    if (OutputStreamRequestBody.this.closed) {
                        throw new IOException("closed");
                    }
                    long j2 = j;
                    if (j2 != -1 && this.bytesReceived + ((long) i2) > j2) {
                        throw new ProtocolException("expected " + j + " bytes but received " + this.bytesReceived + i2);
                    }
                    this.bytesReceived += (long) i2;
                    try {
                        bufferedSink.write(bArr, i, i2);
                    } catch (InterruptedIOException e) {
                        throw new SocketTimeoutException(e.getMessage());
                    }
                }

                @Override // java.io.OutputStream, java.io.Flushable
                public void flush() throws IOException {
                    if (OutputStreamRequestBody.this.closed) {
                        return;
                    }
                    bufferedSink.flush();
                }

                @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    OutputStreamRequestBody.this.closed = true;
                    long j2 = j;
                    if (j2 != -1 && this.bytesReceived < j2) {
                        throw new ProtocolException("expected " + j + " bytes but received " + this.bytesReceived);
                    }
                    bufferedSink.close();
                }
            };
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return this.expectedContentLength;
        }
    }

    static final class BufferedRequestBody extends OutputStreamRequestBody {
        final Buffer buffer;
        long contentLength;

        BufferedRequestBody(long j) {
            Buffer buffer = new Buffer();
            this.buffer = buffer;
            this.contentLength = -1L;
            initOutputStream(buffer, j);
        }

        @Override // com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory.OutputStreamRequestBody, okhttp3.RequestBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override // com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory.OutputStreamRequestBody
        public Request prepareToSendRequest(Request request) throws IOException {
            if (request.header(HttpHeader.CONTENT_LENGTH) != null) {
                return request;
            }
            this.outputStream.close();
            this.contentLength = this.buffer.getSize();
            return request.newBuilder().removeHeader("Transfer-Encoding").header(HttpHeader.CONTENT_LENGTH, Long.toString(this.buffer.getSize())).build();
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) {
            this.buffer.copyTo(bufferedSink.getBufferField(), 0L, this.buffer.getSize());
        }
    }

    static final class StreamedRequestBody extends OutputStreamRequestBody {
        private final Pipe pipe;

        @Override // okhttp3.RequestBody
        public boolean isOneShot() {
            return true;
        }

        StreamedRequestBody(long j) {
            Pipe pipe = new Pipe(8192L);
            this.pipe = pipe;
            initOutputStream(Okio.buffer(pipe.getSink()), j);
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Buffer buffer = new Buffer();
            while (this.pipe.getSource().read(buffer, 8192L) != -1) {
                bufferedSink.write(buffer, buffer.getSize());
            }
        }
    }

    static abstract class DelegatingHttpsURLConnection extends HttpsURLConnection {
        private final HttpURLConnection delegate;

        protected abstract Handshake handshake();

        DelegatingHttpsURLConnection(HttpURLConnection httpURLConnection) {
            super(httpURLConnection.getURL());
            this.delegate = httpURLConnection;
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public String getCipherSuite() {
            Handshake handshake = handshake();
            if (handshake != null) {
                return handshake.cipherSuite().javaName();
            }
            return null;
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public Certificate[] getLocalCertificates() {
            Handshake handshake = handshake();
            if (handshake == null) {
                return null;
            }
            List<Certificate> listLocalCertificates = handshake.localCertificates();
            if (listLocalCertificates.isEmpty()) {
                return null;
            }
            return (Certificate[]) listLocalCertificates.toArray(new Certificate[listLocalCertificates.size()]);
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public Certificate[] getServerCertificates() {
            Handshake handshake = handshake();
            if (handshake == null) {
                return null;
            }
            List<Certificate> listPeerCertificates = handshake.peerCertificates();
            if (listPeerCertificates.isEmpty()) {
                return null;
            }
            return (Certificate[]) listPeerCertificates.toArray(new Certificate[listPeerCertificates.size()]);
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public Principal getPeerPrincipal() {
            Handshake handshake = handshake();
            if (handshake != null) {
                return handshake.peerPrincipal();
            }
            return null;
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public Principal getLocalPrincipal() {
            Handshake handshake = handshake();
            if (handshake != null) {
                return handshake.localPrincipal();
            }
            return null;
        }

        @Override // java.net.URLConnection
        public void connect() throws IOException {
            ((HttpsURLConnection) this).connected = true;
            this.delegate.connect();
        }

        @Override // java.net.HttpURLConnection
        public void disconnect() {
            this.delegate.disconnect();
        }

        @Override // java.net.HttpURLConnection
        public InputStream getErrorStream() {
            return this.delegate.getErrorStream();
        }

        @Override // java.net.HttpURLConnection
        public String getRequestMethod() {
            return this.delegate.getRequestMethod();
        }

        @Override // java.net.HttpURLConnection
        public int getResponseCode() throws IOException {
            return this.delegate.getResponseCode();
        }

        @Override // java.net.HttpURLConnection
        public String getResponseMessage() throws IOException {
            return this.delegate.getResponseMessage();
        }

        @Override // java.net.HttpURLConnection
        public void setRequestMethod(String str) throws ProtocolException {
            this.delegate.setRequestMethod(str);
        }

        @Override // java.net.HttpURLConnection
        public boolean usingProxy() {
            return this.delegate.usingProxy();
        }

        @Override // java.net.HttpURLConnection
        public boolean getInstanceFollowRedirects() {
            return this.delegate.getInstanceFollowRedirects();
        }

        @Override // java.net.HttpURLConnection
        public void setInstanceFollowRedirects(boolean z) {
            this.delegate.setInstanceFollowRedirects(z);
        }

        @Override // java.net.URLConnection
        public boolean getAllowUserInteraction() {
            return this.delegate.getAllowUserInteraction();
        }

        @Override // java.net.URLConnection
        public Object getContent() throws IOException {
            return this.delegate.getContent();
        }

        @Override // java.net.URLConnection
        public Object getContent(Class[] clsArr) throws IOException {
            return this.delegate.getContent(clsArr);
        }

        @Override // java.net.URLConnection
        public String getContentEncoding() {
            return this.delegate.getContentEncoding();
        }

        @Override // java.net.URLConnection
        public int getContentLength() {
            return this.delegate.getContentLength();
        }

        @Override // java.net.URLConnection
        public long getContentLengthLong() {
            return this.delegate.getContentLengthLong();
        }

        @Override // java.net.URLConnection
        public String getContentType() {
            return this.delegate.getContentType();
        }

        @Override // java.net.URLConnection
        public long getDate() {
            return this.delegate.getDate();
        }

        @Override // java.net.URLConnection
        public boolean getDefaultUseCaches() {
            return this.delegate.getDefaultUseCaches();
        }

        @Override // java.net.URLConnection
        public boolean getDoInput() {
            return this.delegate.getDoInput();
        }

        @Override // java.net.URLConnection
        public boolean getDoOutput() {
            return this.delegate.getDoOutput();
        }

        @Override // java.net.URLConnection
        public long getExpiration() {
            return this.delegate.getExpiration();
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderField(int i) {
            return this.delegate.getHeaderField(i);
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getHeaderFields() {
            return this.delegate.getHeaderFields();
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getRequestProperties() {
            return this.delegate.getRequestProperties();
        }

        @Override // java.net.URLConnection
        public void addRequestProperty(String str, String str2) {
            this.delegate.addRequestProperty(str, str2);
        }

        @Override // java.net.URLConnection
        public String getHeaderField(String str) {
            return this.delegate.getHeaderField(str);
        }

        @Override // java.net.URLConnection
        public long getHeaderFieldLong(String str, long j) {
            return this.delegate.getHeaderFieldLong(str, j);
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public long getHeaderFieldDate(String str, long j) {
            return this.delegate.getHeaderFieldDate(str, j);
        }

        @Override // java.net.URLConnection
        public int getHeaderFieldInt(String str, int i) {
            return this.delegate.getHeaderFieldInt(str, i);
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderFieldKey(int i) {
            return this.delegate.getHeaderFieldKey(i);
        }

        @Override // java.net.URLConnection
        public long getIfModifiedSince() {
            return this.delegate.getIfModifiedSince();
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() throws IOException {
            return this.delegate.getInputStream();
        }

        @Override // java.net.URLConnection
        public long getLastModified() {
            return this.delegate.getLastModified();
        }

        @Override // java.net.URLConnection
        public OutputStream getOutputStream() throws IOException {
            return this.delegate.getOutputStream();
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public Permission getPermission() throws IOException {
            return this.delegate.getPermission();
        }

        @Override // java.net.URLConnection
        public String getRequestProperty(String str) {
            return this.delegate.getRequestProperty(str);
        }

        @Override // java.net.URLConnection
        public URL getURL() {
            return this.delegate.getURL();
        }

        @Override // java.net.URLConnection
        public boolean getUseCaches() {
            return this.delegate.getUseCaches();
        }

        @Override // java.net.URLConnection
        public void setAllowUserInteraction(boolean z) {
            this.delegate.setAllowUserInteraction(z);
        }

        @Override // java.net.URLConnection
        public void setDefaultUseCaches(boolean z) {
            this.delegate.setDefaultUseCaches(z);
        }

        @Override // java.net.URLConnection
        public void setDoInput(boolean z) {
            this.delegate.setDoInput(z);
        }

        @Override // java.net.URLConnection
        public void setDoOutput(boolean z) {
            this.delegate.setDoOutput(z);
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(long j) {
            this.delegate.setFixedLengthStreamingMode(j);
        }

        @Override // java.net.URLConnection
        public void setIfModifiedSince(long j) {
            this.delegate.setIfModifiedSince(j);
        }

        @Override // java.net.URLConnection
        public void setRequestProperty(String str, String str2) {
            this.delegate.setRequestProperty(str, str2);
        }

        @Override // java.net.URLConnection
        public void setUseCaches(boolean z) {
            this.delegate.setUseCaches(z);
        }

        @Override // java.net.URLConnection
        public void setConnectTimeout(int i) {
            this.delegate.setConnectTimeout(i);
        }

        @Override // java.net.URLConnection
        public int getConnectTimeout() {
            return this.delegate.getConnectTimeout();
        }

        @Override // java.net.URLConnection
        public void setReadTimeout(int i) {
            this.delegate.setReadTimeout(i);
        }

        @Override // java.net.URLConnection
        public int getReadTimeout() {
            return this.delegate.getReadTimeout();
        }

        @Override // java.net.URLConnection
        public String toString() {
            return this.delegate.toString();
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(int i) {
            this.delegate.setFixedLengthStreamingMode(i);
        }

        @Override // java.net.HttpURLConnection
        public void setChunkedStreamingMode(int i) {
            this.delegate.setChunkedStreamingMode(i);
        }
    }

    static final class OkHttpsURLConnection extends DelegatingHttpsURLConnection {
        private final OkHttpURLConnection delegate;

        OkHttpsURLConnection(URL url, OkHttpClient okHttpClient) {
            this(new OkHttpURLConnection(url, okHttpClient));
        }

        OkHttpsURLConnection(OkHttpURLConnection okHttpURLConnection) {
            super(okHttpURLConnection);
            this.delegate = okHttpURLConnection;
        }

        @Override // com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory.DelegatingHttpsURLConnection
        protected Handshake handshake() {
            OkHttpURLConnection okHttpURLConnection = this.delegate;
            if (okHttpURLConnection.call == null) {
                throw new IllegalStateException("Connection has not yet been established");
            }
            return okHttpURLConnection.handshake;
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            OkHttpURLConnection okHttpURLConnection = this.delegate;
            okHttpURLConnection.client = okHttpURLConnection.client.newBuilder().hostnameVerifier(hostnameVerifier).build();
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public HostnameVerifier getHostnameVerifier() {
            return this.delegate.client.hostnameVerifier();
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory == null) {
                throw new IllegalArgumentException("sslSocketFactory == null");
            }
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers.length == 1) {
                    TrustManager trustManager = trustManagers[0];
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        OkHttpURLConnection okHttpURLConnection = this.delegate;
                        okHttpURLConnection.client = okHttpURLConnection.client.newBuilder().sslSocketFactory(sSLSocketFactory, x509TrustManager).build();
                        return;
                    }
                }
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            } catch (KeyStoreException | NoSuchAlgorithmException e) {
                throw new UnsupportedOperationException("Unable to instantiate X509TrustManager", e);
            }
        }

        @Override // javax.net.ssl.HttpsURLConnection
        public SSLSocketFactory getSSLSocketFactory() {
            return this.delegate.client.sslSocketFactory();
        }
    }

    static final class UnexpectedException extends IOException {
        static final Interceptor INTERCEPTOR = new Interceptor() { // from class: com.amazon.music.okhttp3.urlfactory.ObsoleteUrlFactory$UnexpectedException$$ExternalSyntheticLambda0
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                return ObsoleteUrlFactory.UnexpectedException.lambda$static$0(chain);
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Response lambda$static$0(Interceptor.Chain chain) throws IOException {
            try {
                return chain.proceed(chain.request());
            } catch (Error | RuntimeException e) {
                throw new UnexpectedException(e);
            }
        }

        UnexpectedException(Throwable th) {
            super(th);
        }
    }
}
