package g.a.a.w2;

import java.text.ParseException;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class x extends g.a.a.l implements g.a.a.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.q f4153c;

    public x(g.a.a.q qVar) {
        if (!(qVar instanceof g.a.a.y) && !(qVar instanceof g.a.a.h)) {
            throw new IllegalArgumentException("unknown object passed to Time");
        }
        this.f4153c = qVar;
    }

    public static x h(Object obj) {
        if (obj == null || (obj instanceof x)) {
            return (x) obj;
        }
        if (obj instanceof g.a.a.y) {
            return new x((g.a.a.y) obj);
        }
        if (obj instanceof g.a.a.h) {
            return new x((g.a.a.h) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f4153c;
    }

    public Date g() {
        try {
            g.a.a.q qVar = this.f4153c;
            return qVar instanceof g.a.a.y ? ((g.a.a.y) qVar).n() : ((g.a.a.h) qVar).p();
        } catch (ParseException e2) {
            throw new IllegalStateException("invalid date string: " + e2.getMessage());
        }
    }

    public String i() {
        g.a.a.q qVar = this.f4153c;
        return qVar instanceof g.a.a.y ? ((g.a.a.y) qVar).o() : ((g.a.a.h) qVar).r();
    }

    public String toString() {
        return i();
    }
}
