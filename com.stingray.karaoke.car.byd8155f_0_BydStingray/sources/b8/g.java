package B8;

import A.F;
import Q7.InterfaceC0652e;
import java.util.Set;
import n7.AbstractC1985B;
import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final Set c = AbstractC1985B.t(C2057b.j(N7.n.c.g()));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E8.j f760b;

    public g(i iVar) {
        A7.m.f(iVar, "components");
        this.f759a = iVar;
        this.f760b = ((E8.l) iVar.f762a).c(new F(this, 4));
    }

    public final InterfaceC0652e a(C2057b c2057b, C0092d c0092d) {
        A7.m.f(c2057b, "classId");
        return (InterfaceC0652e) this.f760b.b(new f(c2057b, c0092d));
    }
}
