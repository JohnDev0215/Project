package c9;

import java.util.regex.Pattern;
import r9.AbstractC2312b;
import r9.InterfaceC2321k;

/* JADX INFO: renamed from: c9.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1042c extends B3.m {
    public final e9.e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f12545m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f12546n;
    public final r9.D o;

    public C1042c(e9.e eVar, String str, String str2) {
        this.l = eVar;
        this.f12545m = str;
        this.f12546n = str2;
        this.o = AbstractC2312b.c(new B3.b((r9.J) eVar.f14504n.get(1), this));
    }

    @Override // B3.m
    public final long i() {
        String str = this.f12546n;
        if (str == null) {
            return -1L;
        }
        byte[] bArr = d9.b.f13645a;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    @Override // B3.m
    public final y j() {
        String str = this.f12545m;
        if (str == null) {
            return null;
        }
        Pattern pattern = y.d;
        return P8.n.L(str);
    }

    @Override // B3.m
    public final InterfaceC2321k v() {
        return this.o;
    }
}
