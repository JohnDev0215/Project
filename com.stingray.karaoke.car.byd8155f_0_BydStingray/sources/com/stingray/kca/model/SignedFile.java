package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import h2.AbstractC1347d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ.\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\u0005\u001a\u00020\u00042\b\b\u0003\u0010\u0006\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/SignedFile;", "", "", "uri", "", "expirationEpoch", "format", "<init>", "(Ljava/lang/String;JLjava/lang/String;)V", "copy", "(Ljava/lang/String;JLjava/lang/String;)Lcom/stingray/kca/model/SignedFile;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SignedFile {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13206a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f13207b;
    public final String c;

    public SignedFile(@o(name = "uri") String str, @o(name = "expirationEpoch") long j3, @o(name = "format") String str2) {
        m.f(str, "uri");
        m.f(str2, "format");
        this.f13206a = str;
        this.f13207b = j3;
        this.c = str2;
    }

    public final SignedFile copy(@o(name = "uri") String uri, @o(name = "expirationEpoch") long expirationEpoch, @o(name = "format") String format) {
        m.f(uri, "uri");
        m.f(format, "format");
        return new SignedFile(uri, expirationEpoch, format);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignedFile)) {
            return false;
        }
        SignedFile signedFile = (SignedFile) obj;
        return m.a(this.f13206a, signedFile.f13206a) && this.f13207b == signedFile.f13207b && m.a(this.c, signedFile.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + AbstractC1347d.c(this.f13206a.hashCode() * 31, 31, this.f13207b);
    }

    public final String toString() {
        return "SignedFile(uri=" + this.f13206a + ", expirationEpoch=" + this.f13207b + ", format=" + this.c + ")";
    }
}
