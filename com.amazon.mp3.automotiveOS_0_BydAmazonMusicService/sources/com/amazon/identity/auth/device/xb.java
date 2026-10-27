package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.auth.DeregisterDeviceErrorType;
import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import org.w3c.dom.Document;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class xb extends WebResponseParser<wb> {
    public final sd g;
    public wb h;

    public xb() {
        super("com.amazon.identity.kcpsdk.auth.DeregisterDeviceResponseParser");
        this.g = new sd();
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        this.g.f547a.write(bArr, 0, (int) j);
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public wb b() {
        return this.h;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void c() {
        Document documentA = this.g.a();
        if (documentA == null) {
            a(ParseError.ParseErrorMalformedBody);
            return;
        }
        wb wbVar = new wb();
        if (ed.a(documentA) != null) {
            wbVar.f650a = new ub(DeregisterDeviceErrorType.DeregisterDeviceErrorTypeFailed);
        }
        this.h = wbVar;
    }
}
