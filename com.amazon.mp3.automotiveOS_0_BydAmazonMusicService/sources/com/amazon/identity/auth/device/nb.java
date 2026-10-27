package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class nb implements fd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public mb f464a = new mb(null);
    public fd b;

    public nb(fd fdVar) {
        this.b = fdVar;
    }

    @Override // com.amazon.identity.auth.device.fd
    public void a(md mdVar) {
        fd fdVar = this.b;
        if (fdVar != null) {
            fdVar.a(mdVar);
        }
        this.f464a.a(mdVar);
    }

    @Override // com.amazon.identity.auth.device.fd
    public boolean b() {
        fd fdVar = this.b;
        return (fdVar != null && fdVar.b()) || this.f464a.d();
    }

    @Override // com.amazon.identity.auth.device.fd
    public ParseError c() {
        fd fdVar = this.b;
        if (fdVar != null) {
            fdVar.c();
        }
        this.f464a.a();
        return a();
    }

    @Override // com.amazon.identity.auth.device.fd
    public Object d() {
        fd fdVar = this.b;
        if (fdVar == null) {
            return null;
        }
        return fdVar.d();
    }

    @Override // com.amazon.identity.auth.device.fd
    public ParseError a(byte[] bArr, int i) {
        fd fdVar = this.b;
        if (fdVar != null && fdVar.b()) {
            this.b.a(bArr, i);
        }
        if (this.f464a.d()) {
            this.f464a.b(bArr, i);
        }
        return a();
    }

    @Override // com.amazon.identity.auth.device.fd
    public ParseError a() {
        fd fdVar = this.b;
        if (fdVar != null && fdVar.a() != ParseError.ParseErrorNoError) {
            return this.b.a();
        }
        return this.f464a.b;
    }
}
