package com.amazon.mp3.environment.url;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.configuration.models.EndPoint;
import com.amazon.mp3.util.Log;
import com.fasterxml.jackson.core.JsonPointer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
public class EndPointURL {
    private static final String TAG = "com.amazon.mp3.environment.url.EndPointURL";
    protected String mDomainName;
    protected String mId;
    protected ArrayList<String> mPathSegments;
    protected String mPort;
    protected String mProtocol;
    LinkedHashMap<String, String> mQueryParameter;
    protected String mSubdomainPrefix;
    Map<String, String> mUrlParameters;

    protected enum QueryFormat {
        ENCODED,
        DECODED
    }

    protected EndPointURL() {
        this.mId = "US";
        this.mSubdomainPrefix = "www";
        this.mDomainName = "amazon.com";
        this.mPort = "80";
        this.mProtocol = "http";
        this.mPathSegments = new ArrayList<>();
        this.mQueryParameter = new LinkedHashMap<>();
        this.mUrlParameters = new HashMap();
        Log.debug(TAG, "Default values: %s", toString());
    }

    public EndPointURL(String url) {
        try {
            URL url2 = new URL(url);
            this.mDomainName = url2.getHost();
            this.mPort = Integer.toString(url2.getPort());
            this.mProtocol = url2.getProtocol();
            this.mPathSegments = new ArrayList<>();
            appendPath(url2.getPath());
            this.mQueryParameter = new LinkedHashMap<>();
            this.mUrlParameters = new HashMap();
            Log.debug(TAG, toString());
        } catch (MalformedURLException unused) {
            this.mId = null;
            Log.warning(TAG, "The URL is malformed: %s", url);
        }
    }

    public EndPointURL(final URL url) {
        this.mDomainName = url.getHost();
        this.mPort = Integer.toString(url.getPort());
        this.mProtocol = url.getProtocol();
        this.mPathSegments = new ArrayList<>();
        appendPath(url.getPath());
        this.mQueryParameter = new LinkedHashMap<>();
        this.mUrlParameters = new HashMap();
        Log.debug(TAG, toString());
    }

    protected EndPointURL(EndPoint endPoint, String countryCode) {
        if (endPoint != null) {
            this.mId = countryCode;
            this.mSubdomainPrefix = endPoint.subdomainPrefix(countryCode);
            this.mDomainName = endPoint.domainName(countryCode);
            this.mPort = endPoint.port(countryCode);
            this.mProtocol = endPoint.protocol(countryCode);
            this.mPathSegments = new ArrayList<>();
            this.mQueryParameter = new LinkedHashMap<>();
            this.mUrlParameters = endPoint.getUrlParams();
            Log.debug(TAG, toString());
            return;
        }
        this.mId = null;
    }

    static class PathEndPointURL extends EndPointURL {
        public PathEndPointURL() {
        }

        public PathEndPointURL(EndPoint endPoint, String countryCode, String path) {
            super(endPoint, countryCode);
            if (this.mId == null || TextUtils.isEmpty(path)) {
                return;
            }
            appendPath(endPoint.path(path));
        }
    }

    protected static EndPointURL create(EndPointURLFactory factory, EndPoint endPoint, String countryCode) {
        return new PathEndPointURL(endPoint, countryCode, factory.getCurrentPath());
    }

    public String id() {
        return this.mId;
    }

    public String protocol() {
        return this.mProtocol;
    }

    public String port() {
        return this.mPort;
    }

    public String path() {
        if (this.mPathSegments.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.mPathSegments) {
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append(JsonPointer.SEPARATOR);
            }
        }
        return sb.toString();
    }

    public String query(QueryFormat encoded) {
        if (this.mQueryParameter.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.mQueryParameter.entrySet()) {
            String str = encoded == QueryFormat.ENCODED ? Uri.encode(entry.getKey(), null) + "=" + Uri.encode(entry.getValue(), null) : entry.getKey() + '=' + entry.getValue();
            if (sb.length() > 0) {
                sb.append(Typography.amp);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public boolean appendPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        for (String str : path.split("/")) {
            this.mPathSegments.add(str);
        }
        return true;
    }

    public String appendQueryParameter(String key, String value) {
        return this.mQueryParameter.put(key, value);
    }

    public String toHost() {
        return new URLStringBuffer(this).addDomain().toString();
    }

    public String toURL() {
        return new URLStringBuffer(this).addScheme().addDomain().addPort().addPath().addQueryParameter().toString();
    }

    public String toString() {
        return toURL();
    }

    private static class URLStringBuffer {
        private EndPointURL mEndPointURL;
        private StringBuffer mStringBuffer = new StringBuffer();

        public URLStringBuffer(EndPointURL endPointURL) {
            this.mEndPointURL = endPointURL;
        }

        public URLStringBuffer addScheme() {
            StringBuffer stringBuffer = this.mStringBuffer;
            stringBuffer.append(this.mEndPointURL.mProtocol);
            stringBuffer.append("://");
            return this;
        }

        public URLStringBuffer addDomain() {
            if (!TextUtils.isEmpty(this.mEndPointURL.mSubdomainPrefix)) {
                StringBuffer stringBuffer = this.mStringBuffer;
                stringBuffer.append(this.mEndPointURL.mSubdomainPrefix);
                stringBuffer.append('.');
                stringBuffer.append(this.mEndPointURL.mDomainName);
            } else {
                this.mStringBuffer.append(this.mEndPointURL.mDomainName);
            }
            return this;
        }

        public URLStringBuffer addPort() {
            if (!TextUtils.isEmpty(this.mEndPointURL.mPort)) {
                StringBuffer stringBuffer = this.mStringBuffer;
                stringBuffer.append(':');
                stringBuffer.append(this.mEndPointURL.mPort);
            }
            return this;
        }

        public URLStringBuffer addPath() {
            if (!this.mEndPointURL.mPathSegments.isEmpty()) {
                StringBuffer stringBuffer = this.mStringBuffer;
                stringBuffer.append(JsonPointer.SEPARATOR);
                stringBuffer.append(this.mEndPointURL.path());
            }
            return this;
        }

        public URLStringBuffer addQueryParameter() {
            if (!this.mEndPointURL.mQueryParameter.isEmpty()) {
                StringBuffer stringBuffer = this.mStringBuffer;
                stringBuffer.append("/?");
                stringBuffer.append(this.mEndPointURL.query(QueryFormat.ENCODED));
            }
            return this;
        }

        public String toString() {
            return this.mStringBuffer.toString();
        }
    }

    public Map<String, String> getUrlParameters() {
        return this.mUrlParameters;
    }
}
