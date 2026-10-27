package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class mb<T> extends WebResponseParser<T> {
    public static final String m = "com.amazon.identity.auth.device.mb";
    public final sd g;
    public final WebResponseParser<T> h;
    public ParseError i;
    public ParseError j;
    public boolean k;
    public boolean l;

    public mb(WebResponseParser<T> webResponseParser) {
        super("com.amazon.identity.kcpsdk.auth.AuthenticatedResponseHandler");
        this.g = new sd();
        this.h = null;
        ParseError parseError = ParseError.ParseErrorNoError;
        this.i = parseError;
        this.j = parseError;
        this.k = true;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        WebResponseParser<T> webResponseParser;
        ParseError parseError = this.j;
        ParseError parseError2 = ParseError.ParseErrorNoError;
        if (parseError == parseError2 && this.l) {
            this.g.f547a.write(bArr, 0, (int) j);
        }
        if (this.i == parseError2 && (webResponseParser = this.h) != null && webResponseParser.d()) {
            this.i = this.h.b(bArr, j);
        }
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public T b() {
        WebResponseParser<T> webResponseParser = this.h;
        if (webResponseParser == null) {
            return null;
        }
        return webResponseParser.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            r4 = this;
            boolean r0 = r4.l
            if (r0 == 0) goto L63
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.j
            com.amazon.identity.kcpsdk.common.ParseError r1 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorNoError
            r2 = 0
            if (r0 == r1) goto Lc
            goto L60
        Lc:
            com.amazon.identity.auth.device.sd r0 = r4.g
            org.w3c.dom.Document r0 = r0.a()
            if (r0 != 0) goto L15
            goto L60
        L15:
            com.amazon.identity.auth.device.dd r1 = com.amazon.identity.auth.device.ed.a(r0)
            if (r1 == 0) goto L21
            com.amazon.identity.kcpsdk.common.FIRSErrorType r1 = r1.f284a
            com.amazon.identity.kcpsdk.common.FIRSErrorType r3 = com.amazon.identity.kcpsdk.common.FIRSErrorType.FIRSErrorTypeInternalError
            if (r1 == r3) goto L54
        L21:
            org.w3c.dom.Element r0 = r0.getDocumentElement()
            if (r0 == 0) goto L51
            java.lang.String r1 = r0.getTagName()
            java.lang.String r3 = "Errors"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L34
            goto L51
        L34:
            java.lang.String r1 = "Error"
            org.w3c.dom.Element r0 = com.amazon.identity.auth.device.e.a(r0, r1)
            if (r0 != 0) goto L3d
            goto L51
        L3d:
            java.lang.String r1 = "Code"
            org.w3c.dom.Element r0 = com.amazon.identity.auth.device.e.a(r0, r1)
            if (r0 != 0) goto L46
            goto L51
        L46:
            java.lang.String r0 = r0.getTextContent()
            java.lang.String r1 = "DEVICE_MASTER_SERVICE_ERROR"
            boolean r0 = r1.equals(r0)
            goto L52
        L51:
            r0 = r2
        L52:
            if (r0 == 0) goto L60
        L54:
            java.lang.String r0 = com.amazon.identity.auth.device.mb.m
            java.lang.String r0 = com.amazon.identity.auth.device.ga.a(r0)
            java.lang.String r1 = "AuthenticatedResponseHandler: endParse: Server returned an authentication error. You can ignore any malformed response errors from the inner parser."
            android.util.Log.i(r0, r1)
            goto L61
        L60:
            r2 = 1
        L61:
            r4.k = r2
        L63:
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.i
            com.amazon.identity.kcpsdk.common.ParseError r1 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorNoError
            if (r0 != r1) goto L7b
            com.amazon.identity.kcpsdk.common.WebResponseParser<T> r0 = r4.h
            if (r0 == 0) goto L7b
            boolean r0 = r0.d()
            if (r0 == 0) goto L7b
            com.amazon.identity.kcpsdk.common.WebResponseParser<T> r0 = r4.h
            com.amazon.identity.kcpsdk.common.ParseError r0 = r0.a()
            r4.i = r0
        L7b:
            boolean r0 = r4.k
            if (r0 == 0) goto L82
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.i
            goto L84
        L82:
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.j
        L84:
            r4.a(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.mb.c():void");
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public boolean d() {
        WebResponseParser<T> webResponseParser = this.h;
        return (webResponseParser != null && webResponseParser.d()) || this.l;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public boolean b(md mdVar) {
        WebResponseParser<T> webResponseParser = this.h;
        if (webResponseParser != null) {
            webResponseParser.a(mdVar);
        }
        long j = mdVar.c;
        String str = mdVar.f436a.get("content-type".toLowerCase(Locale.US));
        boolean z = j == 500 || (j == 200 && (str == null || "text/xml".equals(str)));
        this.l = z;
        if (z) {
            ga.c(m, "AuthenticatedResponseHandler: beginParse: Response could be an authentication error. Will try to parse as an authentication error. Status Code: %d Content-Type: %s", Long.valueOf(j), str);
        }
        return true;
    }
}
