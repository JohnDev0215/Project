package v1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: ShapeGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8561a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<b> f8562b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f8563c;

    public j(String str, List<b> list, boolean z7) {
        this.f8561a = str;
        this.f8562b = list;
        this.f8563c = z7;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new q1.d(gVar, aVar, this);
    }

    public List<b> b() {
        return this.f8562b;
    }

    public String c() {
        return this.f8561a;
    }

    public boolean d() {
        return this.f8563c;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.f8561a + "' Shapes: " + Arrays.toString(this.f8562b.toArray()) + '}';
    }
}
