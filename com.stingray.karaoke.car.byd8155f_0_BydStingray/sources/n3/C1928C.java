package n3;

import java.util.ArrayList;
import u.C2459G;

/* JADX INFO: renamed from: n3.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1928C extends z {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final P f19794g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f19795h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f19796i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1928C(P p10, String str, String str2) {
        super(p10.b(h6.j.l(C1929D.class)), str2);
        A7.m.f(p10, "provider");
        A7.m.f(str, "startDestination");
        this.f19796i = new ArrayList();
        this.f19794g = p10;
        this.f19795h = str;
    }

    public final C1927B i() {
        int iHashCode;
        C1927B c1927b = (C1927B) super.c();
        ArrayList<y> arrayList = this.f19796i;
        A7.m.f(arrayList, "nodes");
        for (y yVar : arrayList) {
            if (yVar != null) {
                int i7 = yVar.f19917q;
                String str = yVar.f19918r;
                if (i7 == 0 && str == null) {
                    throw new IllegalArgumentException("Destinations must have an id or route. Call setId(), setRoute(), or include an android:id or app:route in your navigation XML.");
                }
                String str2 = c1927b.f19918r;
                if (str2 != null && A7.m.a(str, str2)) {
                    throw new IllegalArgumentException(("Destination " + yVar + " cannot have the same route as graph " + c1927b).toString());
                }
                if (i7 == c1927b.f19917q) {
                    throw new IllegalArgumentException(("Destination " + yVar + " cannot have the same id as graph " + c1927b).toString());
                }
                C2459G c2459g = c1927b.f19790u;
                y yVar2 = (y) c2459g.c(i7);
                if (yVar2 == yVar) {
                    continue;
                } else {
                    if (yVar.f19914m != null) {
                        throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
                    }
                    if (yVar2 != null) {
                        yVar2.f19914m = null;
                    }
                    yVar.f19914m = c1927b;
                    c2459g.e(yVar.f19917q, yVar);
                }
            }
        }
        String str3 = this.f19795h;
        if (str3 == null) {
            if (((String) this.c) != null) {
                throw new IllegalStateException("You must set a start destination route");
            }
            throw new IllegalStateException("You must set a start destination id");
        }
        if (str3 == null) {
            iHashCode = 0;
        } else {
            if (str3.equals(c1927b.f19918r)) {
                throw new IllegalArgumentException(("Start destination " + str3 + " cannot use the same route as the graph " + c1927b).toString());
            }
            if (Q8.h.M0(str3)) {
                throw new IllegalArgumentException("Cannot have an empty start destination route");
            }
            iHashCode = "android-app://androidx.navigation/".concat(str3).hashCode();
        }
        c1927b.f19791v = iHashCode;
        c1927b.f19793x = str3;
        return c1927b;
    }
}
