package g0;

import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h f14680m = new h(2);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        String str = (String) obj;
        m mVar = (m) obj2;
        if (str.length() == 0) {
            return mVar.toString();
        }
        return str + ", " + mVar;
    }
}
