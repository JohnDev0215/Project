package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class xc implements fd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebResponseParser f663a;

    public xc(WebResponseParser webResponseParser) {
        this.f663a = webResponseParser;
    }

    @Override // com.amazon.identity.auth.device.fd
    public void a(md mdVar) {
        WebResponseParser webResponseParser = this.f663a;
        if (webResponseParser != null) {
            webResponseParser.a(mdVar);
        }
    }

    @Override // com.amazon.identity.auth.device.fd
    public boolean b() {
        WebResponseParser webResponseParser = this.f663a;
        if (webResponseParser != null) {
            return webResponseParser.d();
        }
        return false;
    }

    @Override // com.amazon.identity.auth.device.fd
    public ParseError c() {
        WebResponseParser webResponseParser = this.f663a;
        if (webResponseParser != null) {
            return webResponseParser.a();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.fd
    public Object d() {
        WebResponseParser webResponseParser = this.f663a;
        if (webResponseParser != null) {
            return webResponseParser.b();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.fd
    public ParseError a(byte[] bArr, int i) {
        WebResponseParser webResponseParser = this.f663a;
        if (webResponseParser != null) {
            return webResponseParser.b(bArr, i);
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.fd
    public ParseError a() {
        WebResponseParser webResponseParser = this.f663a;
        if (webResponseParser != null) {
            return webResponseParser.b;
        }
        return null;
    }
}
