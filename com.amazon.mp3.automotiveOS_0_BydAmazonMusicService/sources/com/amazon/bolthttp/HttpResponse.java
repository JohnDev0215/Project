package com.amazon.bolthttp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpResponse<T> {
    private final byte[] mBody;
    private final Headers mHeaders;
    private final Request<T> mRequest;
    private final int mResponseCode;
    private final String mResponseMessage;

    public interface Handler<T> {
        @Nullable
        T process(@Nonnull HttpResponse<T> httpResponse) throws Exception;
    }

    private HttpResponse(Builder<T> builder) {
        this.mRequest = ((Builder) builder).mRequest;
        this.mResponseCode = ((Builder) builder).mResponseCode;
        this.mResponseMessage = ((Builder) builder).mResponseMessage;
        this.mHeaders = ((Builder) builder).mHeadersBuilder.build();
        this.mBody = ((Builder) builder).mBody;
    }

    @Nonnull
    public Request<T> getRequest() {
        return this.mRequest;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    @Nullable
    public String getResponseMessage() {
        return this.mResponseMessage;
    }

    @Nonnull
    public Headers getHeaders() {
        return this.mHeaders;
    }

    @Nullable
    public byte[] getBody() {
        return this.mBody;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private byte[] mBody;
        private final Headers.Builder mHeadersBuilder;
        private Request<T> mRequest;
        private int mResponseCode;
        private String mResponseMessage;

        private Builder() {
            this.mHeadersBuilder = new Headers.Builder();
        }

        public Builder<T> setRequest(@Nonnull Request<T> request) {
            this.mRequest = request;
            return this;
        }

        public Builder<T> setResponseCode(int i) {
            this.mResponseCode = i;
            return this;
        }

        public Builder<T> setResponseMessage(@Nullable String str) {
            this.mResponseMessage = str;
            return this;
        }

        public Builder<T> addHeader(@Nonnull String str, @Nonnull String str2) {
            this.mHeadersBuilder.add(str, str2);
            return this;
        }

        public Builder<T> setBody(@Nullable byte[] bArr) {
            this.mBody = bArr;
            return this;
        }

        public HttpResponse<T> build() {
            if (this.mRequest == null) {
                throw new IllegalStateException("setRequest() never called");
            }
            return new HttpResponse<>(this);
        }
    }
}
