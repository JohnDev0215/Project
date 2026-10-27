package androidx.work.impl.constraints.controllers;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.b;
import p.ky2;
import p.qy;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public final qy a;

    public a(qy qyVar) {
        v03.j(qyVar, "tracker");
        this.a = qyVar;
    }

    public abstract int a();

    public abstract boolean b(ky2 ky2Var);

    public abstract boolean c(Object obj);

    public final b d() {
        return new b(new ConstraintController$track$1(this, null), EmptyCoroutineContext.c, -2, BufferOverflow.c);
    }
}
