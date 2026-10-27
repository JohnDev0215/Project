package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public final class B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EnumC0981t f11905a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InterfaceC0986y f11906b;

    public final void a(A a10, EnumC0980s enumC0980s) {
        EnumC0981t enumC0981tA = enumC0980s.a();
        EnumC0981t enumC0981t = this.f11905a;
        A7.m.f(enumC0981t, "state1");
        if (enumC0981tA.compareTo(enumC0981t) < 0) {
            enumC0981t = enumC0981tA;
        }
        this.f11905a = enumC0981t;
        this.f11906b.b(a10, enumC0980s);
        this.f11905a = enumC0981tA;
    }
}
