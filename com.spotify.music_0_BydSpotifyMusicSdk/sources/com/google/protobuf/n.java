package com.google.protobuf;

import p.t02;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    public abstract o a(Object obj);

    public abstract void b(Object obj);

    public final boolean c(Object obj, t02 t02Var) throws InvalidProtocolBufferException {
        e eVar = (e) t02Var;
        int i = eVar.b;
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            eVar.x(0);
            ((o) obj).f(i2 << 3, Long.valueOf(eVar.a.s()));
            return true;
        }
        if (i3 == 1) {
            eVar.x(1);
            ((o) obj).f((i2 << 3) | 1, Long.valueOf(eVar.a.p()));
            return true;
        }
        if (i3 == 2) {
            ((o) obj).f((i2 << 3) | 2, eVar.e());
            return true;
        }
        if (i3 != 3) {
            if (i3 == 4) {
                return false;
            }
            if (i3 != 5) {
                throw InvalidProtocolBufferException.d();
            }
            eVar.x(5);
            ((o) obj).f((i2 << 3) | 5, Integer.valueOf(eVar.a.o()));
            return true;
        }
        o oVar = new o();
        int i4 = i2 << 3;
        int i5 = i4 | 4;
        while (eVar.a() != Integer.MAX_VALUE && c(oVar, eVar)) {
        }
        if (i5 != eVar.b) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
        oVar.e = false;
        ((o) obj).f(i4 | 3, oVar);
        return true;
    }

    public abstract void d(Object obj, Object obj2);
}
