package com.amazon.podcast.interaction;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/* JADX INFO: loaded from: classes5.dex */
class GzipRequestInterceptor implements Interceptor {
    GzipRequestInterceptor() {
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request.body() == null) {
            return chain.proceed(request);
        }
        return chain.proceed(request.newBuilder().header("Content-Encoding", "gzip, amz-1.0").method(request.method(), getRequestBody(getGzipRequestBody(request.body()))).build());
    }

    private RequestBody getRequestBody(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return new RequestBody() { // from class: com.amazon.podcast.interaction.GzipRequestInterceptor.1
            @Override // okhttp3.RequestBody
            /* JADX INFO: renamed from: contentType */
            public MediaType getContentType() {
                return requestBody.getContentType();
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return buffer.getSize();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(final BufferedSink sink) throws IOException {
                sink.write(buffer.snapshot());
            }
        };
    }

    private RequestBody getGzipRequestBody(final RequestBody body) {
        return new RequestBody() { // from class: com.amazon.podcast.interaction.GzipRequestInterceptor.2
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return -1L;
            }

            @Override // okhttp3.RequestBody
            /* JADX INFO: renamed from: contentType */
            public MediaType getContentType() {
                return body.getContentType();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(final BufferedSink sink) throws IOException {
                BufferedSink bufferedSinkBuffer = Okio.buffer(new GzipSink(sink));
                body.writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
            }
        };
    }
}
