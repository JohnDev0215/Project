package com.amazon.bolthttp;

import com.amazon.bolthttp.policy.NetworkContext;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.URL;
import javax.annotation.Nonnull;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes2.dex */
public interface HttpInterceptor {
    void beforeExecute(@Nonnull Params params, @Nonnull NetworkContext networkContext) throws IOException;

    public static class Params {
        private Headers mHeaders;
        private URL mUrl;

        public Params(@Nonnull URL url, @Nonnull Headers headers) {
            this.mUrl = (URL) Preconditions.checkNotNull(url, ImagesContract.URL);
            this.mHeaders = (Headers) Preconditions.checkNotNull(headers, "headers");
        }

        @Nonnull
        public URL getUrl() {
            return this.mUrl;
        }

        @Nonnull
        public Headers getHeaders() {
            return this.mHeaders;
        }

        @Nonnull
        public Headers.Builder getHeadersBuilder() {
            return this.mHeaders.newBuilder();
        }

        public void setHeaders(@Nonnull Headers headers) {
            this.mHeaders = (Headers) Preconditions.checkNotNull(headers, "headers");
        }
    }
}
