package k2;

import android.net.Uri;
import android.util.Base64;
import e2.M;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/* JADX INFO: renamed from: k2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1605f extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m f17657p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public byte[] f17658q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17659r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17660s;

    @Override // k2.h
    public final void close() {
        if (this.f17658q != null) {
            this.f17658q = null;
            p();
        }
        this.f17657p = null;
    }

    @Override // k2.h
    public final long g(m mVar) throws j, M {
        q();
        this.f17657p = mVar;
        Uri uriNormalizeScheme = mVar.f17675a.normalizeScheme();
        String scheme = uriNormalizeScheme.getScheme();
        AbstractC1346c.b("Unsupported scheme: " + scheme, "data".equals(scheme));
        String schemeSpecificPart = uriNormalizeScheme.getSchemeSpecificPart();
        int i7 = AbstractC1343A.f15195a;
        String[] strArrSplit = schemeSpecificPart.split(",", -1);
        if (strArrSplit.length != 2) {
            throw new M("Unexpected URI format: " + uriNormalizeScheme, null, true, 0);
        }
        String str = strArrSplit[1];
        if (strArrSplit[0].contains(";base64")) {
            try {
                this.f17658q = Base64.decode(str, 0);
            } catch (IllegalArgumentException e10) {
                throw new M(M5.b.g("Error while parsing Base64 encoded string: ", str), e10, true, 0);
            }
        } else {
            this.f17658q = URLDecoder.decode(str, StandardCharsets.US_ASCII.name()).getBytes(StandardCharsets.UTF_8);
        }
        byte[] bArr = this.f17658q;
        long length = bArr.length;
        long j3 = mVar.f17678f;
        if (j3 > length) {
            this.f17658q = null;
            throw new j(2008);
        }
        int i10 = (int) j3;
        this.f17659r = i10;
        int length2 = bArr.length - i10;
        this.f17660s = length2;
        long j10 = mVar.f17679g;
        if (j10 != -1) {
            this.f17660s = (int) Math.min(length2, j10);
        }
        r(mVar);
        return j10 != -1 ? j10 : this.f17660s;
    }

    @Override // k2.h
    public final Uri j() {
        m mVar = this.f17657p;
        if (mVar != null) {
            return mVar.f17675a;
        }
        return null;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) {
        if (i10 == 0) {
            return 0;
        }
        int i11 = this.f17660s;
        if (i11 == 0) {
            return -1;
        }
        int iMin = Math.min(i10, i11);
        byte[] bArr2 = this.f17658q;
        int i12 = AbstractC1343A.f15195a;
        System.arraycopy(bArr2, this.f17659r, bArr, i7, iMin);
        this.f17659r += iMin;
        this.f17660s -= iMin;
        e(iMin);
        return iMin;
    }
}
