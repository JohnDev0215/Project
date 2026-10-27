package p;

import com.spotify.connectivity.productstate.RxProductState;
import java.util.ArrayList;
import java.util.regex.Pattern;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class n32 {
    public static final char[] l = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final Pattern m = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");
    public final String a;
    public final HttpUrl b;
    public String c;
    public HttpUrl.Builder d;
    public final Request.Builder e = new Request.Builder();
    public final Headers.Builder f;
    public MediaType g;
    public final boolean h;
    public final MultipartBody.Builder i;
    public final FormBody.Builder j;
    public RequestBody k;

    public n32(String str, HttpUrl httpUrl, String str2, Headers headers, MediaType mediaType, boolean z, boolean z2, boolean z3) {
        this.a = str;
        this.b = httpUrl;
        this.c = str2;
        this.g = mediaType;
        this.h = z;
        if (headers != null) {
            this.f = headers.c();
        } else {
            this.f = new Headers.Builder();
        }
        if (z2) {
            this.j = new FormBody.Builder();
            return;
        }
        if (z3) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            this.i = builder;
            MediaType mediaType2 = MultipartBody.g;
            v03.j(mediaType2, RxProductState.Keys.KEY_TYPE);
            if (v03.b(mediaType2.b, "multipart")) {
                builder.b = mediaType2;
            } else {
                throw new IllegalArgumentException(("multipart != " + mediaType2).toString());
            }
        }
    }

    public final void a(String str, String str2, boolean z) {
        FormBody.Builder builder = this.j;
        if (!z) {
            builder.a(str, str2);
            return;
        }
        builder.getClass();
        v03.j(str, "name");
        ArrayList arrayList = builder.b;
        HttpUrl.Companion companion = HttpUrl.k;
        arrayList.add(HttpUrl.Companion.a(companion, str, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, false, builder.a, 83));
        builder.c.add(HttpUrl.Companion.a(companion, str2, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, false, builder.a, 83));
    }

    public final void b(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.f.a(str, str2);
            return;
        }
        try {
            MediaType.d.getClass();
            this.g = MediaType.Companion.a(str2);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(u4.x("Malformed content type: ", str2), e);
        }
    }

    public final void c(Headers headers, RequestBody requestBody) {
        MultipartBody.Builder builder = this.i;
        builder.getClass();
        v03.j(requestBody, "body");
        MultipartBody.Part.c.getClass();
        if ((headers != null ? headers.a("Content-Type") : null) != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type".toString());
        }
        if ((headers != null ? headers.a("Content-Length") : null) != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Length".toString());
        }
        builder.c.add(new MultipartBody.Part(headers, requestBody));
    }

    public final void d(String str, String str2, boolean z) {
        String str3 = this.c;
        if (str3 != null) {
            HttpUrl httpUrl = this.b;
            HttpUrl.Builder builderF = httpUrl.f(str3);
            this.d = builderF;
            if (builderF == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + httpUrl + ", Relative: " + this.c);
            }
            this.c = null;
        }
        if (z) {
            HttpUrl.Builder builder = this.d;
            builder.getClass();
            v03.j(str, "encodedName");
            if (builder.g == null) {
                builder.g = new ArrayList();
            }
            ArrayList arrayList = builder.g;
            v03.h(arrayList);
            HttpUrl.Companion companion = HttpUrl.k;
            arrayList.add(HttpUrl.Companion.a(companion, str, 0, 0, " \"'<>#&=", true, false, true, false, null, 211));
            ArrayList arrayList2 = builder.g;
            v03.h(arrayList2);
            arrayList2.add(str2 != null ? HttpUrl.Companion.a(companion, str2, 0, 0, " \"'<>#&=", true, false, true, false, null, 211) : null);
            return;
        }
        HttpUrl.Builder builder2 = this.d;
        builder2.getClass();
        v03.j(str, "name");
        if (builder2.g == null) {
            builder2.g = new ArrayList();
        }
        ArrayList arrayList3 = builder2.g;
        v03.h(arrayList3);
        HttpUrl.Companion companion2 = HttpUrl.k;
        arrayList3.add(HttpUrl.Companion.a(companion2, str, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219));
        ArrayList arrayList4 = builder2.g;
        v03.h(arrayList4);
        arrayList4.add(str2 != null ? HttpUrl.Companion.a(companion2, str2, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219) : null);
    }
}
