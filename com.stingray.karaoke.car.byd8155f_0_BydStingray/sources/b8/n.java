package B8;

import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n8.f f788b;
    public final n8.f c;
    public final n8.f d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2057b f790f;

    public n(Object obj, n8.f fVar, n8.f fVar2, n8.f fVar3, String str, C2057b c2057b) {
        A7.m.f(str, "filePath");
        this.f787a = obj;
        this.f788b = fVar;
        this.c = fVar2;
        this.d = fVar3;
        this.f789e = str;
        this.f790f = c2057b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f787a.equals(nVar.f787a) && A7.m.a(this.f788b, nVar.f788b) && A7.m.a(this.c, nVar.c) && this.d.equals(nVar.d) && A7.m.a(this.f789e, nVar.f789e) && this.f790f.equals(nVar.f790f);
    }

    public final int hashCode() {
        int iHashCode = this.f787a.hashCode() * 31;
        n8.f fVar = this.f788b;
        int iHashCode2 = (iHashCode + (fVar == null ? 0 : fVar.hashCode())) * 31;
        n8.f fVar2 = this.c;
        return this.f790f.hashCode() + M5.b.c(this.f789e, (this.d.hashCode() + ((iHashCode2 + (fVar2 != null ? fVar2.hashCode() : 0)) * 31)) * 31, 31);
    }

    public final String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.f787a + ", compilerVersion=" + this.f788b + ", languageVersion=" + this.c + ", expectedVersion=" + this.d + ", filePath=" + this.f789e + ", classId=" + this.f790f + ')';
    }
}
