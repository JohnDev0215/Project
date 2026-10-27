package W3;

import a4.AbstractC0922c;

/* JADX INFO: loaded from: classes.dex */
public final class g implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f9779b;

    public g(String str, int i7, boolean z6) {
        this.f9778a = i7;
        this.f9779b = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        if (jVar.f5124t.f5132a.contains(O3.k.l)) {
            return new Q3.k(this);
        }
        AbstractC0922c.a("Animation contains merge paths but they are disabled.");
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MergePaths{mode=");
        int i7 = this.f9778a;
        sb.append(i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? "null" : "EXCLUDE_INTERSECTIONS" : "INTERSECT" : "SUBTRACT" : "ADD" : "MERGE");
        sb.append('}');
        return sb.toString();
    }
}
