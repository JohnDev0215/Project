package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class pp2 extends bp2 {
    public final ep2 d;

    public pp2(ip2 ip2Var, cp2 cp2Var, ArrayList arrayList, ep2 ep2Var) {
        super(ip2Var, cp2Var, arrayList);
        this.d = ep2Var;
    }

    @Override // p.bp2
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && v03.b(pp2.class, obj.getClass()) && super.equals(obj)) {
            return v03.b(this.d, ((pp2) obj).d);
        }
        return false;
    }

    @Override // p.bp2
    public final int hashCode() {
        return this.d.hashCode() + (super.hashCode() * 31);
    }

    @Override // p.bp2
    public final String toString() {
        return "interaction = " + this.d + ' ' + super.toString();
    }
}
