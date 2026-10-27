package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import java.util.BitSet;
import r5.C2297a;
import r5.C2298b;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public class Q extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        boolean zC;
        BitSet bitSet = new BitSet();
        c2297a.b();
        int iQ = c2297a.Q();
        int i7 = 0;
        while (iQ != 2) {
            int iC = AbstractC2641j.c(iQ);
            if (iC == 5 || iC == 6) {
                int iG = c2297a.G();
                if (iG == 0) {
                    zC = false;
                } else {
                    if (iG != 1) {
                        StringBuilder sbJ = M5.b.j("Invalid bitset value ", iG, ", expected 0 or 1; at path ");
                        sbJ.append(c2297a.q(true));
                        throw new j5.p(sbJ.toString());
                    }
                    zC = true;
                }
            } else {
                if (iC != 7) {
                    throw new j5.p("Invalid bitset value type: " + AbstractC1347d.v(iQ) + "; at path " + c2297a.q(false));
                }
                zC = c2297a.C();
            }
            if (zC) {
                bitSet.set(i7);
            }
            i7++;
            iQ = c2297a.Q();
        }
        c2297a.k();
        return bitSet;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        BitSet bitSet = (BitSet) obj;
        c2298b.g();
        int length = bitSet.length();
        for (int i7 = 0; i7 < length; i7++) {
            c2298b.I(bitSet.get(i7) ? 1L : 0L);
        }
        c2298b.k();
    }
}
