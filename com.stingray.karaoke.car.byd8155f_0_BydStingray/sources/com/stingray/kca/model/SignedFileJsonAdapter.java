package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/SignedFileJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/SignedFile;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SignedFileJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13209b;
    public final q c;

    public SignedFileJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13208a = C1489n1.z("uri", "expirationEpoch", "format");
        w wVar = w.l;
        this.f13209b = h10.c(String.class, wVar, "uri");
        this.c = h10.c(Long.TYPE, wVar, "expirationEpoch");
    }

    @Override // E5.q
    public final Object a(u uVar) {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        Long l = null;
        String str2 = null;
        while (uVar.m()) {
            int iF = uVar.F(this.f13208a);
            if (iF != -1) {
                q qVar = this.f13209b;
                if (iF == 0) {
                    str = (String) qVar.a(uVar);
                    if (str == null) {
                        throw f.l("uri", "uri", uVar);
                    }
                } else if (iF == 1) {
                    l = (Long) this.c.a(uVar);
                    if (l == null) {
                        throw f.l("expirationEpoch", "expirationEpoch", uVar);
                    }
                } else if (iF == 2 && (str2 = (String) qVar.a(uVar)) == null) {
                    throw f.l("format", "format", uVar);
                }
            } else {
                uVar.I();
                uVar.K();
            }
        }
        uVar.j();
        if (str == null) {
            throw f.f("uri", "uri", uVar);
        }
        if (l == null) {
            throw f.f("expirationEpoch", "expirationEpoch", uVar);
        }
        long jLongValue = l.longValue();
        if (str2 != null) {
            return new SignedFile(str, jLongValue, str2);
        }
        throw f.f("format", "format", uVar);
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        SignedFile signedFile = (SignedFile) obj;
        m.f(xVar, "writer");
        if (signedFile == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("uri");
        String str = signedFile.f13206a;
        q qVar = this.f13209b;
        qVar.d(xVar, str);
        xVar.k("expirationEpoch");
        this.c.d(xVar, Long.valueOf(signedFile.f13207b));
        xVar.k("format");
        qVar.d(xVar, signedFile.c);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(32, "GeneratedJsonAdapter(SignedFile)");
    }
}
