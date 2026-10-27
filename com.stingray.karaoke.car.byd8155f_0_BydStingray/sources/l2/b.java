package L2;

import B2.N;
import e2.K;
import h2.t;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import l9.d;

/* JADX INFO: loaded from: classes.dex */
public final class b extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f3999b;

    @Override // l9.d
    public final K O(K2.a aVar, ByteBuffer byteBuffer) {
        switch (this.f3999b) {
            case 0:
                if (byteBuffer.get() != 116) {
                    return null;
                }
                N n10 = new N(byteBuffer.limit(), byteBuffer.array());
                int i7 = 12;
                n10.u(12);
                int iF = (n10.f() + n10.i(12)) - 4;
                n10.u(44);
                n10.v(n10.i(12));
                n10.u(16);
                ArrayList arrayList = new ArrayList();
                while (n10.f() < iF) {
                    n10.u(48);
                    int i10 = n10.i(8);
                    n10.u(4);
                    int iF2 = n10.f() + n10.i(i7);
                    String str = null;
                    String str2 = null;
                    while (n10.f() < iF2) {
                        int i11 = n10.i(8);
                        int i12 = n10.i(8);
                        int iF3 = n10.f() + i12;
                        if (i11 == 2) {
                            int i13 = n10.i(16);
                            n10.u(8);
                            if (i13 == 3) {
                                while (n10.f() < iF3) {
                                    int i14 = n10.i(8);
                                    Charset charset = StandardCharsets.US_ASCII;
                                    byte[] bArr = new byte[i14];
                                    n10.l(i14, bArr);
                                    String str3 = new String(bArr, charset);
                                    int i15 = n10.i(8);
                                    for (int i16 = 0; i16 < i15; i16++) {
                                        n10.v(n10.i(8));
                                    }
                                    str = str3;
                                }
                            }
                        } else if (i11 == 21) {
                            Charset charset2 = StandardCharsets.US_ASCII;
                            byte[] bArr2 = new byte[i12];
                            n10.l(i12, bArr2);
                            str2 = new String(bArr2, charset2);
                        }
                        n10.r(iF3 * 8);
                    }
                    n10.r(iF2 * 8);
                    if (str != null && str2 != null) {
                        arrayList.add(new a(i10, str.concat(str2)));
                    }
                    i7 = 12;
                }
                if (arrayList.isEmpty()) {
                    return null;
                }
                return new K(arrayList);
            default:
                t tVar = new t(byteBuffer.limit(), byteBuffer.array());
                String strQ = tVar.q();
                strQ.getClass();
                String strQ2 = tVar.q();
                strQ2.getClass();
                return new K(new M2.a(strQ, strQ2, tVar.p(), tVar.p(), Arrays.copyOfRange(tVar.f15241a, tVar.f15242b, tVar.c)));
        }
    }
}
