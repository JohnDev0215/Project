package com.spotify.cosmos.cosmonaut;

import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class TypedResponse<T> {
    public static final Companion Companion = new Companion(null);
    private final T body;
    private final Map<String, String> headers;
    private final int status;
    private final String uri;

    public static final class Builder<T> {
        private T body;
        private Map<String, String> headers;
        private Integer status;
        private String uri;

        public final Builder<T> body(T t) {
            this.body = t;
            return this;
        }

        public final TypedResponse<T> build() {
            Integer num = this.status;
            if (num == null) {
                throw new IllegalStateException("status was not set!".toString());
            }
            int iIntValue = num.intValue();
            String str = this.uri;
            if (str == null) {
                throw new IllegalStateException("uri was not set!".toString());
            }
            Map<String, String> map = this.headers;
            if (map != null) {
                return new TypedResponse<>(iIntValue, str, map, this.body);
            }
            throw new IllegalStateException("headers was not set!".toString());
        }

        public final Builder<T> headers(Map<String, String> map) {
            v03.j(map, "headers");
            this.headers = map;
            return this;
        }

        public final Builder<T> status(int i) {
            this.status = Integer.valueOf(i);
            return this;
        }

        public final Builder<T> uri(String str) {
            v03.j(str, "uri");
            this.uri = str;
            return this;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T> Builder<T> builder() {
            return new Builder<>();
        }
    }

    public TypedResponse(int i, String str, Map<String, String> map, T t) {
        v03.j(str, "uri");
        v03.j(map, "headers");
        this.status = i;
        this.uri = str;
        this.headers = map;
        this.body = t;
    }

    public static final <T> Builder<T> builder() {
        return Companion.builder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TypedResponse copy$default(TypedResponse typedResponse, int i, String str, Map map, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = typedResponse.status;
        }
        if ((i2 & 2) != 0) {
            str = typedResponse.uri;
        }
        if ((i2 & 4) != 0) {
            map = typedResponse.headers;
        }
        if ((i2 & 8) != 0) {
            obj = typedResponse.body;
        }
        return typedResponse.copy(i, str, map, obj);
    }

    public final int component1() {
        return this.status;
    }

    public final String component2() {
        return this.uri;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    public final T component4() {
        return this.body;
    }

    public final TypedResponse<T> copy(int i, String str, Map<String, String> map, T t) {
        v03.j(str, "uri");
        v03.j(map, "headers");
        return new TypedResponse<>(i, str, map, t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypedResponse)) {
            return false;
        }
        TypedResponse typedResponse = (TypedResponse) obj;
        return this.status == typedResponse.status && v03.b(this.uri, typedResponse.uri) && v03.b(this.headers, typedResponse.headers) && v03.b(this.body, typedResponse.body);
    }

    public final T getBody() {
        return this.body;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getUri() {
        return this.uri;
    }

    public int hashCode() {
        int iHashCode = (this.headers.hashCode() + hf2.b(this.uri, Integer.hashCode(this.status) * 31, 31)) * 31;
        T t = this.body;
        return iHashCode + (t == null ? 0 : t.hashCode());
    }

    public final Builder<T> toBuilder() {
        return new Builder().status(this.status).uri(this.uri).headers(this.headers).body(this.body);
    }

    public String toString() {
        return "TypedResponse(status=" + this.status + ", uri=" + this.uri + ", headers=" + this.headers + ", body=" + this.body + ')';
    }
}
