package N;

import A7.m;
import N0.C0411f;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f4252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0411f f4253b;
    public boolean c = false;
    public d d = null;

    public f(C0411f c0411f, C0411f c0411f2) {
        this.f4252a = c0411f;
        this.f4253b = c0411f2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return m.a(this.f4252a, fVar.f4252a) && m.a(this.f4253b, fVar.f4253b) && this.c == fVar.c && m.a(this.d, fVar.d);
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d((this.f4253b.hashCode() + (this.f4252a.hashCode() * 31)) * 31, 31, this.c);
        d dVar = this.d;
        return iD + (dVar == null ? 0 : dVar.hashCode());
    }

    public final String toString() {
        return "TextSubstitutionValue(original=" + ((Object) this.f4252a) + ", substitution=" + ((Object) this.f4253b) + ", isShowingSubstitution=" + this.c + ", layoutCache=" + this.d + ')';
    }
}
