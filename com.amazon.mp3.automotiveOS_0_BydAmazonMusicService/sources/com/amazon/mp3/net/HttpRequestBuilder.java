package com.amazon.mp3.net;

import android.net.Uri;
import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
public class HttpRequestBuilder {
    private static final String TAG = "HttpRequestBuilder";
    private static final Pattern URL_ENCODED_SPACES = Pattern.compile("\\+");
    private Map<String, String> mArguments;
    private String mBody;
    private final String mHost;
    private final String mPath;
    private final int mPort;
    private Map<String, String> mHeaders = new HashMap();
    private boolean mUseHttps = false;
    private int mMethod = -1;
    private int mConnectionTimeoutMillis = 15000;
    private int mReadTimeoutMillis = HardCodedRefreshStrategy.MS_IN_MINUTE;
    private String mUserAgent = UserAgent.getUserAgent();

    public HttpRequestBuilder(String host, int port, String path) {
        this.mHost = host;
        this.mPort = port;
        this.mPath = path;
    }

    public HttpRequestBuilder setMethod(int method) {
        this.mMethod = method;
        return this;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public int getPort() {
        return this.mPort;
    }

    public String getHost() {
        return this.mHost;
    }

    public String getPath() {
        return this.mPath;
    }

    public Uri getRequestUri() {
        return Uri.parse(AbstractHttpClient.createRequestUrl(this).toString());
    }

    public String getUserAgent() {
        return this.mUserAgent;
    }

    public HttpRequestBuilder setArguments(Map<String, String> arguments) {
        this.mArguments = arguments;
        return this;
    }

    public boolean hasArguments() {
        Map<String, String> map = this.mArguments;
        return map != null && map.size() > 0;
    }

    public String buildArguments() {
        return mapToQueryArguments(this.mArguments);
    }

    public HttpRequestBuilder setBody(String body) {
        this.mBody = body;
        return this;
    }

    public String getBody() {
        return this.mBody;
    }

    public boolean hasHeaders() {
        Map<String, String> map = this.mHeaders;
        return map != null && map.size() > 0;
    }

    public HttpRequestBuilder addHeader(String header, String value) {
        this.mHeaders.put(header, value);
        return this;
    }

    public void setHeadersOn(URLConnection connection) {
        for (Map.Entry<String, String> entry : this.mHeaders.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public HttpRequestBuilder setUseHttps(boolean useHttps) {
        this.mUseHttps = useHttps;
        return this;
    }

    public boolean getUseHttps() {
        return this.mUseHttps;
    }

    public HttpRequestBuilder setConnectionTimeout(int connectionTimeoutMillis) {
        this.mConnectionTimeoutMillis = connectionTimeoutMillis;
        return this;
    }

    public int getConnectionTimeout() {
        return this.mConnectionTimeoutMillis;
    }

    public HttpRequestBuilder setReadTimeout(int readTimeoutMillis) {
        this.mReadTimeoutMillis = readTimeoutMillis;
        return this;
    }

    public int getReadTimeout() {
        return this.mReadTimeoutMillis;
    }

    public static HttpRequestBuilder fromUri(Uri uri) {
        boolean zEquals = "https".equals(uri.getScheme().toLowerCase(Locale.US));
        int port = uri.getPort();
        String host = uri.getHost();
        String encodedPath = uri.getEncodedPath();
        if (port == -1) {
            port = zEquals ? 443 : 80;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery != null) {
            for (String str : encodedQuery.split("&")) {
                String[] strArrSplit = str.split("=");
                if (strArrSplit.length > 0) {
                    String str2 = strArrSplit[0];
                    try {
                        linkedHashMap.put(str2, URLDecoder.decode(strArrSplit.length > 1 ? strArrSplit[1] : "", ParserUtil.UTF_8));
                    } catch (UnsupportedEncodingException unused) {
                        Log.error(TAG, "error decoding UTF-8 value for key %s", str2);
                    }
                }
            }
        }
        return new HttpRequestBuilder(host, port, encodedPath).setUseHttps(zEquals).setArguments(linkedHashMap);
    }

    public static String mapToQueryArguments(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                String strReplaceAll = URL_ENCODED_SPACES.matcher(URLEncoder.encode(entry.getValue(), ParserUtil.UTF_8)).replaceAll("%20");
                sb.append(z ? "" : Character.valueOf(Typography.amp));
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(strReplaceAll);
            } catch (UnsupportedEncodingException unused) {
                Log.error(TAG, "error encoding UTF-8 value for key %s", entry.getKey());
            }
            z = false;
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Method [");
        sb.append(this.mMethod);
        sb.append("]\n");
        sb.append("Host [");
        sb.append(this.mHost);
        sb.append("]\n");
        sb.append("Path [");
        sb.append(this.mPath);
        sb.append("]\n");
        sb.append("Port [");
        sb.append(this.mPort);
        sb.append("]\n");
        sb.append("Body [");
        sb.append(this.mBody);
        sb.append("]\n");
        Map<String, String> map = this.mArguments;
        if (map != null && map.size() > 0) {
            for (String str : this.mArguments.keySet()) {
                sb.append("Argument [");
                sb.append(str);
                sb.append('=');
                sb.append(this.mArguments.get(str));
                sb.append("]\n");
            }
        }
        Map<String, String> map2 = this.mHeaders;
        if (map2 != null && map2.size() > 0) {
            for (String str2 : this.mHeaders.keySet()) {
                sb.append("Header [");
                sb.append(str2);
                sb.append('=');
                sb.append(this.mHeaders.get(str2));
                sb.append("]\n");
            }
        }
        return sb.toString();
    }
}
