package F8;

import java.util.Iterator;
import n7.AbstractC1998m;
import q8.C2203g;

/* JADX INFO: loaded from: classes.dex */
public abstract class A extends a0 implements I8.d, I8.e {
    @Override // F8.a0
    /* JADX INFO: renamed from: O0, reason: merged with bridge method [inline-methods] */
    public abstract A L0(boolean z6);

    @Override // F8.a0
    /* JADX INFO: renamed from: P0, reason: merged with bridge method [inline-methods] */
    public abstract A N0(H h10);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = k().iterator();
        while (it.hasNext()) {
            String[] strArr = {"[", C2203g.f21280e.x((R7.b) it.next(), null), "] "};
            for (int i7 = 0; i7 < 3; i7++) {
                sb.append(strArr[i7]);
            }
        }
        sb.append(B0());
        if (!o0().isEmpty()) {
            AbstractC1998m.d0(o0(), sb, ", ", (124 & 4) != 0 ? "" : "<", (124 & 8) != 0 ? "" : ">", -1, "...", (124 & 64) != 0 ? null : null);
        }
        if (F0()) {
            sb.append("?");
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
