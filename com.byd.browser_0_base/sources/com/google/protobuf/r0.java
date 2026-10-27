package com.google.protobuf;

import defpackage.bh1;
import defpackage.lk;
import defpackage.nu;
import defpackage.wv1;

/* JADX INFO: compiled from: MessageLite.java */
/* JADX INFO: loaded from: classes.dex */
public interface r0 extends bh1 {

    /* JADX INFO: compiled from: MessageLite.java */
    public interface a extends bh1, Cloneable {
        a G(r0 r0Var);

        a L(g gVar, s sVar);

        r0 build();

        r0 d();

        a t(byte[] bArr);
    }

    a e();

    a h();

    lk.f l();

    int m();

    void p(nu nuVar);

    byte[] q();

    wv1<? extends r0> u();
}
