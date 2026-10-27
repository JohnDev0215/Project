package h8;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class q extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final q f15623m = new q(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        String str = (String) obj;
        A7.m.f(str, "it");
        if (str.length() <= 1) {
            return str;
        }
        return "L" + str + ';';
    }
}
