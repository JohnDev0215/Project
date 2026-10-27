package p8;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/* JADX INFO: renamed from: p8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2127b implements v {
    static {
        int i7 = C2132g.f20978b;
    }

    public final AbstractC2126a b(ByteArrayInputStream byteArrayInputStream, C2132g c2132g) throws C2142q {
        AbstractC2126a abstractC2126a;
        try {
            int i7 = byteArrayInputStream.read();
            if (i7 == -1) {
                abstractC2126a = null;
            } else {
                if ((i7 & 128) != 0) {
                    i7 &= 127;
                    int i10 = 7;
                    while (true) {
                        if (i10 >= 32) {
                            while (i10 < 64) {
                                int i11 = byteArrayInputStream.read();
                                if (i11 == -1) {
                                    throw C2142q.a();
                                }
                                if ((i11 & 128) != 0) {
                                    i10 += 7;
                                }
                            }
                            throw new C2142q("CodedInputStream encountered a malformed varint.");
                        }
                        int i12 = byteArrayInputStream.read();
                        if (i12 == -1) {
                            throw C2142q.a();
                        }
                        i7 |= (i12 & 127) << i10;
                        if ((i12 & 128) == 0) {
                            break;
                        }
                        i10 += 7;
                    }
                }
                C2130e c2130e = new C2130e(new A4.n(byteArrayInputStream, i7));
                abstractC2126a = (AbstractC2126a) a(c2130e, c2132g);
                try {
                    c2130e.a(0);
                } catch (C2142q e10) {
                    e10.l = abstractC2126a;
                    throw e10;
                }
            }
            if (abstractC2126a == null || abstractC2126a.b()) {
                return abstractC2126a;
            }
            C2142q c2142q = new C2142q(new A9.k().getMessage());
            c2142q.l = abstractC2126a;
            throw c2142q;
        } catch (IOException e11) {
            throw new C2142q(e11.getMessage());
        }
    }
}
