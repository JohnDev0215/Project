package Z7;

import K7.K;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B f10959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B f10960b;
    public final Map c;
    public final boolean d;

    public v(B b10, B b11) {
        n7.v vVar = n7.v.l;
        this.f10959a = b10;
        this.f10960b = b11;
        this.c = vVar;
        h6.r.o(new K(this, 23));
        B b12 = B.IGNORE;
        this.d = b10 == b12 && b11 == b12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f10959a == vVar.f10959a && this.f10960b == vVar.f10960b && A7.m.a(this.c, vVar.c);
    }

    public final int hashCode() {
        int iHashCode = this.f10959a.hashCode() * 31;
        B b10 = this.f10960b;
        return this.c.hashCode() + ((iHashCode + (b10 == null ? 0 : b10.hashCode())) * 31);
    }

    public final String toString() {
        return "Jsr305Settings(globalLevel=" + this.f10959a + ", migrationLevel=" + this.f10960b + ", userDefinedLevelForSpecificAnnotation=" + this.c + ')';
    }
}
