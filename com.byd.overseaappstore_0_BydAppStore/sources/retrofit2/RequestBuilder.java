package retrofit2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import spi.cj;
import spi.d;
import spi.e;
import spi.g2;
import spi.gc;
import spi.hf;
import spi.im;
import spi.k2;
import spi.ke;
import spi.mi;
import spi.mm;
import spi.uu;

/* JADX INFO: loaded from: classes2.dex */
final class RequestBuilder {
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final hf baseUrl;
    private mm body;
    private mi contentType;
    private gc.a formBuilder;
    private final boolean hasBody;
    private final ke.a headersBuilder;
    private final String method;
    private cj.a multipartBuilder;
    private String relativeUrl;
    private final im.a requestBuilder = new im.a();
    private hf.a urlBuilder;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Pattern PATH_TRAVERSAL = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    public static class ContentTypeOverridingRequestBody extends mm {
        private final mi contentType;
        private final mm delegate;

        public ContentTypeOverridingRequestBody(mm mmVar, mi miVar) {
            this.delegate = mmVar;
            this.contentType = miVar;
        }

        @Override // spi.mm
        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        @Override // spi.mm
        public mi contentType() {
            return this.contentType;
        }

        @Override // spi.mm
        public void writeTo(k2 k2Var) throws IOException {
            this.delegate.writeTo(k2Var);
        }
    }

    public RequestBuilder(String str, hf hfVar, String str2, ke keVar, mi miVar, boolean z, boolean z2, boolean z3) {
        this.method = str;
        this.baseUrl = hfVar;
        this.relativeUrl = str2;
        this.contentType = miVar;
        this.hasBody = z;
        this.headersBuilder = keVar != null ? keVar.c() : new ke.a();
        if (z2) {
            this.formBuilder = new gc.a();
            return;
        }
        if (z3) {
            cj.a aVar = new cj.a();
            this.multipartBuilder = aVar;
            mi type = cj.f;
            Objects.requireNonNull(aVar);
            Intrinsics.checkNotNullParameter(type, "type");
            if (Intrinsics.areEqual(type.b, "multipart")) {
                aVar.b = type;
                return;
            }
            throw new IllegalArgumentException(("multipart != " + type).toString());
        }
    }

    private static String canonicalizeForPath(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                g2 g2Var = new g2();
                g2Var.Z(str, 0, iCharCount);
                canonicalizeForPath(g2Var, str, iCharCount, length, z);
                return g2Var.L();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    private static void canonicalizeForPath(g2 g2Var, String str, int i, int i2, boolean z) {
        g2 g2Var2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt < 32 || iCodePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                    if (g2Var2 == null) {
                        g2Var2 = new g2();
                    }
                    g2Var2.a0(iCodePointAt);
                    while (!g2Var2.h()) {
                        int i3 = g2Var2.readByte() & 255;
                        g2Var.S(37);
                        char[] cArr = HEX_DIGITS;
                        g2Var.S(cArr[(i3 >> 4) & 15]);
                        g2Var.S(cArr[i3 & 15]);
                    }
                } else {
                    g2Var.a0(iCodePointAt);
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }

    /* JADX WARN: Type inference failed for: r14v0, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    public void addFormField(String name, String value, boolean z) {
        gc.a aVar = this.formBuilder;
        Objects.requireNonNull(aVar);
        if (z) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            ?? r14 = aVar.a;
            hf.b bVar = hf.l;
            r14.add(hf.b.a(bVar, name, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, false, aVar.c, 83));
            aVar.b.add(hf.b.a(bVar, value, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, false, aVar.c, 83));
            return;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        ?? r142 = aVar.a;
        hf.b bVar2 = hf.l;
        r142.add(hf.b.a(bVar2, name, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, false, aVar.c, 91));
        aVar.b.add(hf.b.a(bVar2, value, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, false, aVar.c, 91));
    }

    public void addHeader(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.headersBuilder.a(str, str2);
            return;
        }
        try {
            this.contentType = mi.f.a(str2);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(d.f("Malformed content type: ", str2), e);
        }
    }

    public void addHeaders(ke headers) {
        ke.a aVar = this.headersBuilder;
        Objects.requireNonNull(aVar);
        Intrinsics.checkNotNullParameter(headers, "headers");
        int length = headers.a.length / 2;
        for (int i = 0; i < length; i++) {
            aVar.c(headers.b(i), headers.d(i));
        }
    }

    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.ArrayList, java.util.List<spi.cj$c>] */
    public void addPart(ke keVar, mm body) {
        cj.a aVar = this.multipartBuilder;
        Objects.requireNonNull(aVar);
        Intrinsics.checkNotNullParameter(body, "body");
        Objects.requireNonNull(cj.c.c);
        Intrinsics.checkNotNullParameter(body, "body");
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (!((keVar != null ? keVar.a("Content-Type") : null) == null)) {
            throw new IllegalArgumentException("Unexpected header: Content-Type".toString());
        }
        if (!((keVar != null ? keVar.a("Content-Length") : null) == null)) {
            throw new IllegalArgumentException("Unexpected header: Content-Length".toString());
        }
        cj.c part = new cj.c(keVar, body, defaultConstructorMarker);
        Intrinsics.checkNotNullParameter(part, "part");
        aVar.c.add(part);
    }

    public void addPathParam(String str, String str2, boolean z) {
        if (this.relativeUrl == null) {
            throw new AssertionError();
        }
        String strCanonicalizeForPath = canonicalizeForPath(str2, z);
        String strReplace = this.relativeUrl.replace("{" + str + "}", strCanonicalizeForPath);
        if (PATH_TRAVERSAL.matcher(strReplace).matches()) {
            throw new IllegalArgumentException(d.f("@Path parameters shouldn't perform path traversal ('.' or '..'): ", str2));
        }
        this.relativeUrl = strReplace;
    }

    public void addQueryParam(String name, String str, boolean z) {
        String str2 = this.relativeUrl;
        if (str2 != null) {
            hf.a aVarG = this.baseUrl.g(str2);
            this.urlBuilder = aVarG;
            if (aVarG == null) {
                StringBuilder sbK = e.k("Malformed URL. Base: ");
                sbK.append(this.baseUrl);
                sbK.append(", Relative: ");
                sbK.append(this.relativeUrl);
                throw new IllegalArgumentException(sbK.toString());
            }
            this.relativeUrl = null;
        }
        hf.a aVar = this.urlBuilder;
        Objects.requireNonNull(aVar);
        if (z) {
            Intrinsics.checkNotNullParameter(name, "encodedName");
            if (aVar.g == null) {
                aVar.g = new ArrayList();
            }
            List<String> list = aVar.g;
            Intrinsics.checkNotNull(list);
            hf.b bVar = hf.l;
            list.add(hf.b.a(bVar, name, 0, 0, " \"'<>#&=", true, false, true, false, null, 211));
            List<String> list2 = aVar.g;
            Intrinsics.checkNotNull(list2);
            list2.add(str != null ? hf.b.a(bVar, str, 0, 0, " \"'<>#&=", true, false, true, false, null, 211) : null);
            return;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        if (aVar.g == null) {
            aVar.g = new ArrayList();
        }
        List<String> list3 = aVar.g;
        Intrinsics.checkNotNull(list3);
        hf.b bVar2 = hf.l;
        list3.add(hf.b.a(bVar2, name, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219));
        List<String> list4 = aVar.g;
        Intrinsics.checkNotNull(list4);
        list4.add(str != null ? hf.b.a(bVar2, str, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219) : null);
    }

    public <T> void addTag(Class<T> cls, T t) {
        this.requestBuilder.f(cls, t);
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.ArrayList, java.util.List<spi.cj$c>] */
    public im.a get() {
        hf url;
        hf.a aVar = this.urlBuilder;
        if (aVar != null) {
            url = aVar.a();
        } else {
            hf hfVar = this.baseUrl;
            String link = this.relativeUrl;
            Objects.requireNonNull(hfVar);
            Intrinsics.checkNotNullParameter(link, "link");
            hf.a aVarG = hfVar.g(link);
            url = aVarG != null ? aVarG.a() : null;
            if (url == null) {
                StringBuilder sbK = e.k("Malformed URL. Base: ");
                sbK.append(this.baseUrl);
                sbK.append(", Relative: ");
                sbK.append(this.relativeUrl);
                throw new IllegalArgumentException(sbK.toString());
            }
        }
        mm contentTypeOverridingRequestBody = this.body;
        if (contentTypeOverridingRequestBody == null) {
            gc.a aVar2 = this.formBuilder;
            if (aVar2 != null) {
                contentTypeOverridingRequestBody = new gc(aVar2.a, aVar2.b);
            } else {
                cj.a aVar3 = this.multipartBuilder;
                if (aVar3 != null) {
                    if (!(!aVar3.c.isEmpty())) {
                        throw new IllegalStateException("Multipart body must have at least one part.".toString());
                    }
                    contentTypeOverridingRequestBody = new cj(aVar3.a, aVar3.b, uu.x(aVar3.c));
                } else if (this.hasBody) {
                    contentTypeOverridingRequestBody = mm.create((mi) null, new byte[0]);
                }
            }
        }
        mi miVar = this.contentType;
        if (miVar != null) {
            if (contentTypeOverridingRequestBody != null) {
                contentTypeOverridingRequestBody = new ContentTypeOverridingRequestBody(contentTypeOverridingRequestBody, miVar);
            } else {
                this.headersBuilder.a("Content-Type", miVar.a);
            }
        }
        im.a aVar4 = this.requestBuilder;
        Objects.requireNonNull(aVar4);
        Intrinsics.checkNotNullParameter(url, "url");
        aVar4.a = url;
        aVar4.c(this.headersBuilder.d());
        aVar4.d(this.method, contentTypeOverridingRequestBody);
        return aVar4;
    }

    public void setBody(mm mmVar) {
        this.body = mmVar;
    }

    public void setRelativeUrl(Object obj) {
        this.relativeUrl = obj.toString();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.cj$c>] */
    public void addPart(cj.c part) {
        cj.a aVar = this.multipartBuilder;
        Objects.requireNonNull(aVar);
        Intrinsics.checkNotNullParameter(part, "part");
        aVar.c.add(part);
    }
}
