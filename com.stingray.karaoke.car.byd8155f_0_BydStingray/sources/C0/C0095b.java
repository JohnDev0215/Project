package C0;

import z7.InterfaceC2785n;

/* JADX INFO: renamed from: C0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0095b extends A7.j implements InterfaceC2785n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0095b f884t = new C0095b(2, C7.a.class, "max", "max(II)I", 1);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return Integer.valueOf(Math.max(((Number) obj).intValue(), ((Number) obj2).intValue()));
    }
}
