package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.WebResponseParser;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class sc extends WebResponseParser<rc> {
    public final sd g;
    public rc h;

    public sc() {
        super("com.amazon.identity.kcpsdk.auth.RenameDeviceResponseParser");
        this.g = new sd();
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        this.g.f547a.write(bArr, 0, (int) j);
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public rc b() {
        return this.h;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0085  */
    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            r9 = this;
            com.amazon.identity.auth.device.sd r0 = r9.g
            org.w3c.dom.Document r0 = r0.a()
            if (r0 != 0) goto Le
            com.amazon.identity.kcpsdk.common.ParseError r0 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorMalformedBody
            r9.a(r0)
            return
        Le:
            org.w3c.dom.Element r0 = r0.getDocumentElement()
            java.lang.String r1 = "Received a null response from the server"
            r2 = 7
            r3 = 0
            if (r0 == 0) goto L85
            java.lang.String r4 = r0.getTagName()
            java.lang.String r5 = "response"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L27
            goto L85
        L27:
            java.lang.String r4 = "error_code"
            org.w3c.dom.Element r4 = com.amazon.identity.auth.device.e.a(r0, r4)
            java.lang.String r5 = "text"
            org.w3c.dom.Element r0 = com.amazon.identity.auth.device.e.a(r0, r5)
            java.lang.String r4 = com.amazon.identity.auth.device.e.a(r4)
            java.lang.String r0 = com.amazon.identity.auth.device.e.a(r0)
            if (r4 != 0) goto L3f
            goto L85
        L3f:
            java.lang.Integer r4 = com.amazon.identity.auth.device.e.j(r4)
            if (r4 != 0) goto L4f
            com.amazon.identity.auth.device.rc r0 = new com.amazon.identity.auth.device.rc
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.INVALID_RESPONSE
            java.lang.String r5 = "No response code found."
            r0.<init>(r4, r1, r2, r5)
            goto L86
        L4f:
            int r5 = r4.intValue()
            if (r5 == 0) goto L77
            r4 = 2
            if (r5 == r4) goto L6c
            r4 = 4
            if (r5 == r4) goto L64
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.INVALID_RESPONSE
            java.lang.String r5 = r4.getErrorMessage()
            r6 = r5
            r5 = r2
            goto L7e
        L64:
            r4 = 5
            com.amazon.identity.auth.device.api.MAPError$AccountError r5 = com.amazon.identity.auth.device.api.MAPError.AccountError.DUPLICATE_DEVICE_NAME
            java.lang.String r6 = r5.getErrorMessage()
            goto L73
        L6c:
            r4 = 3
            com.amazon.identity.auth.device.api.MAPError$CommonError r5 = com.amazon.identity.auth.device.api.MAPError.CommonError.BAD_REQUEST
            java.lang.String r6 = r5.getErrorMessage()
        L73:
            r8 = r5
            r5 = r4
            r4 = r8
            goto L7e
        L77:
            int r4 = r4.intValue()
            r6 = r3
            r5 = r4
            r4 = r6
        L7e:
            com.amazon.identity.auth.device.rc r7 = new com.amazon.identity.auth.device.rc
            r7.<init>(r4, r6, r5, r0)
            r0 = r7
            goto L86
        L85:
            r0 = r3
        L86:
            if (r0 != 0) goto L8f
            com.amazon.identity.auth.device.rc r0 = new com.amazon.identity.auth.device.rc
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.INVALID_RESPONSE
            r0.<init>(r4, r1, r2, r3)
        L8f:
            r9.h = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.sc.c():void");
    }
}
