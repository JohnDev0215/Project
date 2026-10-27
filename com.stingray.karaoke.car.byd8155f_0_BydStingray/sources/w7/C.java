package W7;

import f8.InterfaceC1266b;
import g1.AbstractC1274a;
import java.lang.annotation.Annotation;
import java.util.Collection;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class C extends r implements InterfaceC1266b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A f9869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Annotation[] f9870b;
    public final String c;
    public final boolean d;

    public C(A a10, Annotation[] annotationArr, String str, boolean z6) {
        A7.m.f(annotationArr, "reflectAnnotations");
        this.f9869a = a10;
        this.f9870b = annotationArr;
        this.c = str;
        this.d = z6;
    }

    @Override // f8.InterfaceC1266b
    public final C0840d a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        return AbstractC1274a.H(this.f9870b, c2058c);
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        return AbstractC1274a.K(this.f9870b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(C.class.getName());
        sb.append(": ");
        sb.append(this.d ? "vararg " : "");
        String str = this.c;
        sb.append(str != null ? C2061f.d(str) : null);
        sb.append(": ");
        sb.append(this.f9869a);
        return sb.toString();
    }
}
