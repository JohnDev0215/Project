package g.a.a.w2;

import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class f extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f4117c = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Hashtable f4118d = new Hashtable();
    public g.a.a.g s;

    public f(int i2) {
        this.s = new g.a.a.g(i2);
    }

    public static f g(Object obj) {
        if (obj instanceof f) {
            return (f) obj;
        }
        if (obj != null) {
            return i(g.a.a.g.o(obj).p().intValue());
        }
        return null;
    }

    public static f i(int i2) {
        Integer numB = g.a.f.d.b(i2);
        Hashtable hashtable = f4118d;
        if (!hashtable.containsKey(numB)) {
            hashtable.put(numB, new f(i2));
        }
        return (f) hashtable.get(numB);
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.s;
    }

    public BigInteger h() {
        return this.s.p();
    }

    public String toString() {
        int iIntValue = h().intValue();
        return "CRLReason: " + ((iIntValue < 0 || iIntValue > 10) ? "invalid" : f4117c[iIntValue]);
    }
}
