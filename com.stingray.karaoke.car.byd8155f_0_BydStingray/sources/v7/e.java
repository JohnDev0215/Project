package V7;

import A7.m;
import B8.l;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import W7.r;
import f8.InterfaceC1267c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f9551b = new e();
    public static final e c = new e();

    @Override // B8.l
    public void a(InterfaceC0652e interfaceC0652e, ArrayList arrayList) {
        m.f(interfaceC0652e, "descriptor");
        throw new IllegalStateException("Incomplete hierarchy for class " + interfaceC0652e.getName() + ", unresolved classes " + arrayList);
    }

    public g b(InterfaceC1267c interfaceC1267c) {
        m.f(interfaceC1267c, "javaElement");
        return new g((r) interfaceC1267c);
    }

    @Override // B8.l
    public void c(InterfaceC0650c interfaceC0650c) {
        m.f(interfaceC0650c, "descriptor");
        throw new IllegalStateException("Cannot infer visibility for " + interfaceC0650c);
    }
}
