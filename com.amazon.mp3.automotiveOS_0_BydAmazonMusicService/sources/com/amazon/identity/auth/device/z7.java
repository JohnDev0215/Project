package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.y7;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z7 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y7.i f702a;
    public final /* synthetic */ u7.a b;
    public final /* synthetic */ y7 c;

    public z7(y7 y7Var, y7.i iVar, u7.a aVar) {
        this.c = y7Var;
        this.f702a = iVar;
        this.b = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.a(this.f702a);
        u7.a aVar = this.b;
        if (aVar != null) {
            ((n) aVar).a();
        }
    }
}
