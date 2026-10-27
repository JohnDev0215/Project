package c9;

import java.util.ArrayList;
import java.util.Set;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: c9.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1049j {
    public static final C1049j c = new C1049j(AbstractC1998m.D0(new ArrayList()), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f12569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h6.i f12570b;

    public C1049j(Set set, h6.i iVar) {
        this.f12569a = set;
        this.f12570b = iVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1049j) {
            C1049j c1049j = (C1049j) obj;
            if (A7.m.a(c1049j.f12569a, this.f12569a) && A7.m.a(c1049j.f12570b, this.f12570b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.f12569a.hashCode() + 1517) * 41;
        h6.i iVar = this.f12570b;
        return iHashCode + (iVar != null ? iVar.hashCode() : 0);
    }
}
