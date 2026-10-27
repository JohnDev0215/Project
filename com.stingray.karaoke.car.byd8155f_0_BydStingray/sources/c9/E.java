package c9;

import g1.AbstractC1274a;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import n7.AbstractC1985B;
import r9.AbstractC2312b;
import r9.C2314d;
import r9.InterfaceC2320j;

/* JADX INFO: loaded from: classes.dex */
public final class E extends AbstractC1274a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ y f12498e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ File f12499f;

    public E(y yVar, File file) {
        this.f12498e = yVar;
        this.f12499f = file;
    }

    @Override // g1.AbstractC1274a
    public final long B() {
        return this.f12499f.length();
    }

    @Override // g1.AbstractC1274a
    public final y C() {
        return this.f12498e;
    }

    @Override // g1.AbstractC1274a
    public final void f0(InterfaceC2320j interfaceC2320j) throws IllegalAccessException, IOException, InvocationTargetException {
        C2314d c2314dI = AbstractC2312b.i(this.f12499f);
        try {
            interfaceC2320j.r(c2314dI);
            AbstractC1985B.c(c2314dI, null);
        } finally {
        }
    }
}
