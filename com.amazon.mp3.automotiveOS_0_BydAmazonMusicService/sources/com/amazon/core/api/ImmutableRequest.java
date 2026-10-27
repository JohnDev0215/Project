package com.amazon.core.api;

import com.amazon.core.api.Request;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes2.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "Request", generator = "Immutables")
final class ImmutableRequest extends Request {
    private final Map<Class, Annotation> annotations;
    private final Header header;
    private final String host;
    private final String ipAddress;
    private final Map<String, String> path;
    private final Map<String, String> querystring;

    private ImmutableRequest(Header header, String str, Map<String, String> map, Map<String, String> map2, Map<Class, Annotation> map3, String str2) {
        this.header = header;
        this.host = str;
        this.querystring = map;
        this.path = map2;
        this.annotations = map3;
        this.ipAddress = str2;
    }

    @Override // com.amazon.core.api.Request
    @JsonProperty("header")
    public Header header() {
        return this.header;
    }

    @Override // com.amazon.core.api.Request
    @JsonProperty("host")
    public String host() {
        return this.host;
    }

    @Override // com.amazon.core.api.Request
    @JsonProperty("querystring")
    public Map<String, String> querystring() {
        return this.querystring;
    }

    @Override // com.amazon.core.api.Request
    @JsonProperty("path")
    public Map<String, String> path() {
        return this.path;
    }

    @Override // com.amazon.core.api.Request
    @JsonProperty("annotations")
    public Map<Class, Annotation> annotations() {
        return this.annotations;
    }

    @Override // com.amazon.core.api.Request
    @JsonProperty("ipAddress")
    public String ipAddress() {
        return this.ipAddress;
    }

    public final ImmutableRequest withHeader(Header header) {
        return this.header == header ? this : new ImmutableRequest((Header) requireNonNull(header, "header"), this.host, this.querystring, this.path, this.annotations, this.ipAddress);
    }

    public final ImmutableRequest withHost(String str) {
        String str2 = (String) requireNonNull(str, "host");
        return this.host.equals(str2) ? this : new ImmutableRequest(this.header, str2, this.querystring, this.path, this.annotations, this.ipAddress);
    }

    public final ImmutableRequest withQuerystring(Map<String, ? extends String> map) {
        if (this.querystring == map) {
            return this;
        }
        return new ImmutableRequest(this.header, this.host, createUnmodifiableMap(true, false, map), this.path, this.annotations, this.ipAddress);
    }

    public final ImmutableRequest withPath(Map<String, ? extends String> map) {
        if (this.path == map) {
            return this;
        }
        return new ImmutableRequest(this.header, this.host, this.querystring, createUnmodifiableMap(true, false, map), this.annotations, this.ipAddress);
    }

    public final ImmutableRequest withAnnotations(Map<? extends Class, ? extends Annotation> map) {
        if (this.annotations == map) {
            return this;
        }
        return new ImmutableRequest(this.header, this.host, this.querystring, this.path, createUnmodifiableMap(true, false, map), this.ipAddress);
    }

    public final ImmutableRequest withIpAddress(String str) {
        String str2 = (String) requireNonNull(str, "ipAddress");
        return this.ipAddress.equals(str2) ? this : new ImmutableRequest(this.header, this.host, this.querystring, this.path, this.annotations, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRequest) && equalTo(0, (ImmutableRequest) obj);
    }

    private boolean equalTo(int i, ImmutableRequest immutableRequest) {
        return this.header.equals(immutableRequest.header) && this.host.equals(immutableRequest.host) && this.querystring.equals(immutableRequest.querystring) && this.path.equals(immutableRequest.path) && this.annotations.equals(immutableRequest.annotations) && this.ipAddress.equals(immutableRequest.ipAddress);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.header.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.host.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.querystring.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.path.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.annotations.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + this.ipAddress.hashCode();
    }

    public String toString() {
        return "Request{header=" + this.header + ", host=" + this.host + ", querystring=" + this.querystring + ", path=" + this.path + ", annotations=" + this.annotations + ", ipAddress=" + this.ipAddress + "}";
    }

    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Request", generator = "Immutables")
    static final class Json extends Request {

        @Nullable
        Header header;

        @Nullable
        String host;

        @Nullable
        String ipAddress;

        @Nullable
        Map<String, String> querystring = Collections.emptyMap();

        @Nullable
        Map<String, String> path = Collections.emptyMap();

        @Nullable
        Map<Class, Annotation> annotations = Collections.emptyMap();

        Json() {
        }

        @JsonProperty("header")
        public void setHeader(Header header) {
            this.header = header;
        }

        @JsonProperty("host")
        public void setHost(String str) {
            this.host = str;
        }

        @JsonProperty("querystring")
        public void setQuerystring(Map<String, String> map) {
            this.querystring = map;
        }

        @JsonProperty("path")
        public void setPath(Map<String, String> map) {
            this.path = map;
        }

        @JsonProperty("annotations")
        public void setAnnotations(Map<Class, Annotation> map) {
            this.annotations = map;
        }

        @JsonProperty("ipAddress")
        public void setIpAddress(String str) {
            this.ipAddress = str;
        }

        @Override // com.amazon.core.api.Request
        public Header header() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.Request
        public String host() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.Request
        public Map<String, String> querystring() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.Request
        public Map<String, String> path() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.Request
        public Map<Class, Annotation> annotations() {
            throw new UnsupportedOperationException();
        }

        @Override // com.amazon.core.api.Request
        public String ipAddress() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRequest fromJson(Json json) {
        Request.Builder builder = new Request.Builder();
        Header header = json.header;
        if (header != null) {
            builder.header(header);
        }
        String str = json.host;
        if (str != null) {
            builder.host(str);
        }
        Map<String, String> map = json.querystring;
        if (map != null) {
            builder.putAllQuerystring(map);
        }
        Map<String, String> map2 = json.path;
        if (map2 != null) {
            builder.putAllPath(map2);
        }
        Map<Class, Annotation> map3 = json.annotations;
        if (map3 != null) {
            builder.putAllAnnotations(map3);
        }
        String str2 = json.ipAddress;
        if (str2 != null) {
            builder.ipAddress(str2);
        }
        return builder.build();
    }

    public static ImmutableRequest copyOf(Request request) {
        if (request instanceof ImmutableRequest) {
            return (ImmutableRequest) request;
        }
        return new Request.Builder().from(request).build();
    }

    @Generated(from = "Request", generator = "Immutables")
    @NotThreadSafe
    public static class Builder {
        private static final long INIT_BIT_HEADER = 1;
        private static final long INIT_BIT_HOST = 2;
        private static final long INIT_BIT_IP_ADDRESS = 4;

        @Nullable
        private Header header;

        @Nullable
        private String host;

        @Nullable
        private String ipAddress;
        private long initBits = 7;
        private Map<String, String> querystring = new LinkedHashMap();
        private Map<String, String> path = new LinkedHashMap();
        private Map<Class, Annotation> annotations = new LinkedHashMap();

        public Builder() {
            if (!(this instanceof Request.Builder)) {
                throw new UnsupportedOperationException("Use: new Request.Builder()");
            }
        }

        public final Request.Builder from(Request request) {
            ImmutableRequest.requireNonNull(request, "instance");
            header(request.header());
            host(request.host());
            putAllQuerystring(request.querystring());
            putAllPath(request.path());
            putAllAnnotations(request.annotations());
            ipAddress(request.ipAddress());
            return (Request.Builder) this;
        }

        @JsonProperty("header")
        public final Request.Builder header(Header header) {
            this.header = (Header) ImmutableRequest.requireNonNull(header, "header");
            this.initBits &= -2;
            return (Request.Builder) this;
        }

        @JsonProperty("host")
        public final Request.Builder host(String str) {
            this.host = (String) ImmutableRequest.requireNonNull(str, "host");
            this.initBits &= -3;
            return (Request.Builder) this;
        }

        public final Request.Builder putQuerystring(String str, String str2) {
            this.querystring.put((String) ImmutableRequest.requireNonNull(str, "querystring key"), (String) ImmutableRequest.requireNonNull(str2, "querystring value"));
            return (Request.Builder) this;
        }

        public final Request.Builder putQuerystring(Map.Entry<String, ? extends String> entry) {
            this.querystring.put((String) ImmutableRequest.requireNonNull(entry.getKey(), "querystring key"), (String) ImmutableRequest.requireNonNull(entry.getValue(), "querystring value"));
            return (Request.Builder) this;
        }

        @JsonProperty("querystring")
        public final Request.Builder querystring(Map<String, ? extends String> map) {
            this.querystring.clear();
            return putAllQuerystring(map);
        }

        public final Request.Builder putAllQuerystring(Map<String, ? extends String> map) {
            for (Map.Entry<String, ? extends String> entry : map.entrySet()) {
                this.querystring.put((String) ImmutableRequest.requireNonNull(entry.getKey(), "querystring key"), (String) ImmutableRequest.requireNonNull(entry.getValue(), "querystring value"));
            }
            return (Request.Builder) this;
        }

        public final Request.Builder putPath(String str, String str2) {
            this.path.put((String) ImmutableRequest.requireNonNull(str, "path key"), (String) ImmutableRequest.requireNonNull(str2, "path value"));
            return (Request.Builder) this;
        }

        public final Request.Builder putPath(Map.Entry<String, ? extends String> entry) {
            this.path.put((String) ImmutableRequest.requireNonNull(entry.getKey(), "path key"), (String) ImmutableRequest.requireNonNull(entry.getValue(), "path value"));
            return (Request.Builder) this;
        }

        @JsonProperty("path")
        public final Request.Builder path(Map<String, ? extends String> map) {
            this.path.clear();
            return putAllPath(map);
        }

        public final Request.Builder putAllPath(Map<String, ? extends String> map) {
            for (Map.Entry<String, ? extends String> entry : map.entrySet()) {
                this.path.put((String) ImmutableRequest.requireNonNull(entry.getKey(), "path key"), (String) ImmutableRequest.requireNonNull(entry.getValue(), "path value"));
            }
            return (Request.Builder) this;
        }

        public final Request.Builder putAnnotations(Class cls, Annotation annotation) {
            this.annotations.put((Class) ImmutableRequest.requireNonNull(cls, "annotations key"), (Annotation) ImmutableRequest.requireNonNull(annotation, "annotations value"));
            return (Request.Builder) this;
        }

        public final Request.Builder putAnnotations(Map.Entry<? extends Class, ? extends Annotation> entry) {
            this.annotations.put((Class) ImmutableRequest.requireNonNull(entry.getKey(), "annotations key"), (Annotation) ImmutableRequest.requireNonNull(entry.getValue(), "annotations value"));
            return (Request.Builder) this;
        }

        @JsonProperty("annotations")
        public final Request.Builder annotations(Map<? extends Class, ? extends Annotation> map) {
            this.annotations.clear();
            return putAllAnnotations(map);
        }

        public final Request.Builder putAllAnnotations(Map<? extends Class, ? extends Annotation> map) {
            for (Map.Entry<? extends Class, ? extends Annotation> entry : map.entrySet()) {
                this.annotations.put((Class) ImmutableRequest.requireNonNull(entry.getKey(), "annotations key"), (Annotation) ImmutableRequest.requireNonNull(entry.getValue(), "annotations value"));
            }
            return (Request.Builder) this;
        }

        @JsonProperty("ipAddress")
        public final Request.Builder ipAddress(String str) {
            this.ipAddress = (String) ImmutableRequest.requireNonNull(str, "ipAddress");
            this.initBits &= -5;
            return (Request.Builder) this;
        }

        public ImmutableRequest build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRequest(this.header, this.host, ImmutableRequest.createUnmodifiableMap(false, false, this.querystring), ImmutableRequest.createUnmodifiableMap(false, false, this.path), ImmutableRequest.createUnmodifiableMap(false, false, this.annotations), this.ipAddress);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("header");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("host");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("ipAddress");
            }
            return "Cannot build Request, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> Map<K, V> createUnmodifiableMap(boolean z, boolean z2, Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return Collections.emptyMap();
        }
        if (size == 1) {
            Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
            K key = next.getKey();
            V value = next.getValue();
            if (z) {
                requireNonNull(key, "key");
                requireNonNull(value, "value");
            }
            if (z2 && (key == null || value == null)) {
                return Collections.emptyMap();
            }
            return Collections.singletonMap(key, value);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
        if (z2 || z) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                K key2 = entry.getKey();
                V value2 = entry.getValue();
                if (z2) {
                    if (key2 == null || value2 == null) {
                    }
                } else if (z) {
                    requireNonNull(key2, "key");
                    requireNonNull(value2, "value");
                }
                linkedHashMap.put(key2, value2);
            }
        } else {
            linkedHashMap.putAll(map);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
