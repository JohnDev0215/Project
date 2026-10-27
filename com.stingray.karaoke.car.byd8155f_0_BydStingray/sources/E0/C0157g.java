package E0;

import l0.InterfaceC1718i;

/* JADX INFO: renamed from: E0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0157g implements InterfaceC1718i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0157g f1667a = new C0157g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Boolean f1668b;

    @Override // l0.InterfaceC1718i
    public final boolean a() {
        Boolean bool = f1668b;
        if (bool != null) {
            return bool.booleanValue();
        }
        l9.d.i0("canFocus is read before it is written");
        throw null;
    }

    @Override // l0.InterfaceC1718i
    public final void b(boolean z6) {
        f1668b = Boolean.valueOf(z6);
    }
}
