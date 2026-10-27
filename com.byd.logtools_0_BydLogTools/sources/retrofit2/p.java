package retrofit2;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: compiled from: RequestBuilder.java */
/* JADX INFO: loaded from: classes.dex */
final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f5292a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Pattern f5293b = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f5294c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HttpUrl f5295d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    private String f5296e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    private HttpUrl.Builder f5297f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Request.Builder f5298g = new Request.Builder();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Headers.Builder f5299h;

    @Nullable
    private MediaType i;
    private final boolean j;

    @Nullable
    private MultipartBody.Builder k;

    @Nullable
    private FormBody.Builder l;

    @Nullable
    private RequestBody m;

    /* JADX INFO: compiled from: RequestBuilder.java */
    private static class a extends RequestBody {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final RequestBody f5300a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final MediaType f5301b;

        a(RequestBody requestBody, MediaType mediaType) {
            this.f5300a = requestBody;
            this.f5301b = mediaType;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.f5300a.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return this.f5301b;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.f5300a.writeTo(bufferedSink);
        }
    }

    p(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable Headers headers, @Nullable MediaType mediaType, boolean z, boolean z2, boolean z3) {
        this.f5294c = str;
        this.f5295d = httpUrl;
        this.f5296e = str2;
        this.i = mediaType;
        this.j = z;
        if (headers != null) {
            this.f5299h = headers.newBuilder();
        } else {
            this.f5299h = new Headers.Builder();
        }
        if (z2) {
            this.l = new FormBody.Builder();
        } else if (z3) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            this.k = builder;
            builder.setType(MultipartBody.FORM);
        }
    }

    private static String i(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, iCharCount);
                j(buffer, str, iCharCount, length, z);
                return buffer.readUtf8();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    private static void j(Buffer buffer, String str, int i, int i2, boolean z) {
        Buffer buffer2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(iCodePointAt);
                    while (!buffer2.exhausted()) {
                        int i3 = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        char[] cArr = f5292a;
                        buffer.writeByte((int) cArr[(i3 >> 4) & 15]);
                        buffer.writeByte((int) cArr[i3 & 15]);
                    }
                } else {
                    buffer.writeUtf8CodePoint(iCodePointAt);
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }

    void a(String str, String str2, boolean z) {
        if (z) {
            this.l.addEncoded(str, str2);
        } else {
            this.l.add(str, str2);
        }
    }

    void b(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.f5299h.add(str, str2);
            return;
        }
        try {
            this.i = MediaType.get(str2);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("Malformed content type: " + str2, e2);
        }
    }

    void c(Headers headers) {
        this.f5299h.addAll(headers);
    }

    void d(Headers headers, RequestBody requestBody) {
        this.k.addPart(headers, requestBody);
    }

    void e(MultipartBody.Part part) {
        this.k.addPart(part);
    }

    void f(String str, String str2, boolean z) {
        if (this.f5296e == null) {
            throw new AssertionError();
        }
        String strI = i(str2, z);
        String strReplace = this.f5296e.replace("{" + str + "}", strI);
        if (!f5293b.matcher(strReplace).matches()) {
            this.f5296e = strReplace;
            return;
        }
        throw new IllegalArgumentException("@Path parameters shouldn't perform path traversal ('.' or '..'): " + str2);
    }

    void g(String str, @Nullable String str2, boolean z) {
        String str3 = this.f5296e;
        if (str3 != null) {
            HttpUrl.Builder builderNewBuilder = this.f5295d.newBuilder(str3);
            this.f5297f = builderNewBuilder;
            if (builderNewBuilder == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.f5295d + ", Relative: " + this.f5296e);
            }
            this.f5296e = null;
        }
        if (z) {
            this.f5297f.addEncodedQueryParameter(str, str2);
        } else {
            this.f5297f.addQueryParameter(str, str2);
        }
    }

    <T> void h(Class<T> cls, @Nullable T t) {
        this.f5298g.tag(cls, t);
    }

    Request.Builder k() {
        HttpUrl httpUrlResolve;
        HttpUrl.Builder builder = this.f5297f;
        if (builder != null) {
            httpUrlResolve = builder.build();
        } else {
            httpUrlResolve = this.f5295d.resolve(this.f5296e);
            if (httpUrlResolve == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.f5295d + ", Relative: " + this.f5296e);
            }
        }
        RequestBody aVar = this.m;
        if (aVar == null) {
            FormBody.Builder builder2 = this.l;
            if (builder2 != null) {
                aVar = builder2.build();
            } else {
                MultipartBody.Builder builder3 = this.k;
                if (builder3 != null) {
                    aVar = builder3.build();
                } else if (this.j) {
                    aVar = RequestBody.create((MediaType) null, new byte[0]);
                }
            }
        }
        MediaType mediaType = this.i;
        if (mediaType != null) {
            if (aVar != null) {
                aVar = new a(aVar, mediaType);
            } else {
                this.f5299h.add("Content-Type", mediaType.toString());
            }
        }
        return this.f5298g.url(httpUrlResolve).headers(this.f5299h.build()).method(this.f5294c, aVar);
    }

    void l(RequestBody requestBody) {
        this.m = requestBody;
    }

    void m(Object obj) {
        this.f5296e = obj.toString();
    }
}
