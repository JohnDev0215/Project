package S7;

import A7.m;
import Q7.InterfaceC0652e;
import java.util.Collection;
import n7.u;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b, d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f8077b = new a(0);
    public static final a c = new a(1);
    public static final a d = new a(2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f8078e = new a(3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8079a;

    public /* synthetic */ a(int i7) {
        this.f8079a = i7;
    }

    @Override // S7.b
    public Collection a(InterfaceC0652e interfaceC0652e) {
        m.f(interfaceC0652e, "classDescriptor");
        return u.l;
    }

    @Override // S7.b
    public Collection b(InterfaceC0652e interfaceC0652e) {
        m.f(interfaceC0652e, "classDescriptor");
        return u.l;
    }

    @Override // S7.b
    public Collection c(InterfaceC0652e interfaceC0652e) {
        m.f(interfaceC0652e, "classDescriptor");
        return u.l;
    }

    @Override // S7.b
    public Collection d(C2061f c2061f, InterfaceC0652e interfaceC0652e) {
        m.f(c2061f, "name");
        m.f(interfaceC0652e, "classDescriptor");
        return u.l;
    }

    @Override // S7.d
    public boolean e(InterfaceC0652e interfaceC0652e, D8.u uVar) {
        switch (this.f8079a) {
            case 1:
                m.f(interfaceC0652e, "classDescriptor");
                return true;
            default:
                m.f(interfaceC0652e, "classDescriptor");
                return !uVar.k().k(e.f8080a);
        }
    }
}
