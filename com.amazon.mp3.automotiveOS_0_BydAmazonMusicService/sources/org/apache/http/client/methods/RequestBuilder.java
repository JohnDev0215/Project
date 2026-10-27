package org.apache.http.client.methods;

import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.message.HeaderGroup;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class RequestBuilder {
    private Charset charset;
    private RequestConfig config;
    private HttpEntity entity;
    private HeaderGroup headergroup;
    private String method;
    private List<NameValuePair> parameters;
    private URI uri;
    private ProtocolVersion version;

    RequestBuilder(String str) {
        this.charset = Consts.UTF_8;
        this.method = str;
    }

    RequestBuilder() {
        this(null);
    }

    public static RequestBuilder copy(HttpRequest httpRequest) {
        Args.notNull(httpRequest, "HTTP request");
        return new RequestBuilder().doCopy(httpRequest);
    }

    private RequestBuilder doCopy(HttpRequest httpRequest) {
        if (httpRequest == null) {
            return this;
        }
        this.method = httpRequest.getRequestLine().getMethod();
        this.version = httpRequest.getRequestLine().getProtocolVersion();
        if (this.headergroup == null) {
            this.headergroup = new HeaderGroup();
        }
        this.headergroup.clear();
        this.headergroup.setHeaders(httpRequest.getAllHeaders());
        this.parameters = null;
        this.entity = null;
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntity entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            ContentType contentType = ContentType.get(entity);
            if (contentType != null && contentType.getMimeType().equals(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())) {
                try {
                    List<NameValuePair> list = URLEncodedUtils.parse(entity);
                    if (!list.isEmpty()) {
                        this.parameters = list;
                    }
                } catch (IOException unused) {
                }
            } else {
                this.entity = entity;
            }
        }
        if (httpRequest instanceof HttpUriRequest) {
            this.uri = ((HttpUriRequest) httpRequest).getURI();
        } else {
            this.uri = URI.create(httpRequest.getRequestLine().getUri());
        }
        if (httpRequest instanceof Configurable) {
            this.config = ((Configurable) httpRequest).getConfig();
        } else {
            this.config = null;
        }
        return this;
    }

    public RequestBuilder setUri(URI uri) {
        this.uri = uri;
        return this;
    }

    public HttpUriRequest build() {
        HttpRequestBase internalRequest;
        URI uriBuild = this.uri;
        if (uriBuild == null) {
            uriBuild = URI.create("/");
        }
        HttpEntity urlEncodedFormEntity = this.entity;
        List<NameValuePair> list = this.parameters;
        if (list != null && !list.isEmpty()) {
            if (urlEncodedFormEntity == null && (FirebasePerformance.HttpMethod.POST.equalsIgnoreCase(this.method) || FirebasePerformance.HttpMethod.PUT.equalsIgnoreCase(this.method))) {
                List<NameValuePair> list2 = this.parameters;
                Charset charset = this.charset;
                if (charset == null) {
                    charset = HTTP.DEF_CONTENT_CHARSET;
                }
                urlEncodedFormEntity = new UrlEncodedFormEntity(list2, charset);
            } else {
                try {
                    uriBuild = new URIBuilder(uriBuild).setCharset(this.charset).addParameters(this.parameters).build();
                } catch (URISyntaxException unused) {
                }
            }
        }
        if (urlEncodedFormEntity == null) {
            internalRequest = new InternalRequest(this.method);
        } else {
            InternalEntityEclosingRequest internalEntityEclosingRequest = new InternalEntityEclosingRequest(this.method);
            internalEntityEclosingRequest.setEntity(urlEncodedFormEntity);
            internalRequest = internalEntityEclosingRequest;
        }
        internalRequest.setProtocolVersion(this.version);
        internalRequest.setURI(uriBuild);
        HeaderGroup headerGroup = this.headergroup;
        if (headerGroup != null) {
            internalRequest.setHeaders(headerGroup.getAllHeaders());
        }
        internalRequest.setConfig(this.config);
        return internalRequest;
    }

    static class InternalRequest extends HttpRequestBase {
        private final String method;

        InternalRequest(String str) {
            this.method = str;
        }

        @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
        public String getMethod() {
            return this.method;
        }
    }

    static class InternalEntityEclosingRequest extends HttpEntityEnclosingRequestBase {
        private final String method;

        InternalEntityEclosingRequest(String str) {
            this.method = str;
        }

        @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
        public String getMethod() {
            return this.method;
        }
    }
}
