package W3;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9797a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f9798b;
    public final boolean c;

    public m(String str, List list, boolean z6) {
        this.f9797a = str;
        this.f9798b = list;
        this.c = z6;
    }

    @Override // W3.b
    public final Q3.c a(O3.j jVar, O3.a aVar, X3.b bVar) {
        return new Q3.d(jVar, bVar, this, aVar);
    }

    public final String toString() {
        return "ShapeGroup{name='" + this.f9797a + "' Shapes: " + Arrays.toString(this.f9798b.toArray()) + '}';
    }
}
