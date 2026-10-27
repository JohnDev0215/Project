package e8;

import A7.m;
import A7.o;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1239d extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C1239d f14489m = new C1239d(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        String str = (String) obj;
        m.f(str, "it");
        return "(raw) ".concat(str);
    }
}
