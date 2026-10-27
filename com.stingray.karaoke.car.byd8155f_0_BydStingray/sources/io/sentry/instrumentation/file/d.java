package io.sentry.instrumentation.file;

import d5.C1077F;
import d5.I;
import e2.BinderC1198f;
import e2.F;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import k3.BinderC1658n0;
import k3.C1628b0;
import k3.E1;
import k3.InterfaceC1655m;
import k3.X;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements a, X {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16562m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f16563n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f16564p;

    public /* synthetic */ d(Object obj, Object obj2, int i7, int i10, int i11) {
        this.l = i11;
        this.f16564p = obj;
        this.f16562m = obj2;
        this.f16563n = i7;
        this.o = i10;
    }

    @Override // io.sentry.instrumentation.file.a
    public Object call() throws IOException {
        switch (this.l) {
            case 0:
                return Integer.valueOf(((e) this.f16564p).l.read((byte[]) this.f16562m, this.f16563n, this.o));
            default:
                FileOutputStream fileOutputStream = ((g) this.f16564p).l;
                byte[] bArr = (byte[]) this.f16562m;
                int i7 = this.f16563n;
                int i10 = this.o;
                fileOutputStream.write(bArr, i7, i10);
                return Integer.valueOf(i10);
        }
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        C1628b0 c1628b0 = (C1628b0) this.f16564p;
        c1628b0.getClass();
        C1077F c1077fU = I.u();
        int i10 = 0;
        while (true) {
            List list = (List) this.f16562m;
            if (i10 >= list.size()) {
                break;
            }
            c1077fU.a(((F) list.get(i10)).d(true));
            i10++;
        }
        BinderC1198f binderC1198f = new BinderC1198f(c1077fU.h());
        E1 e12 = c1628b0.l;
        e12.getClass();
        int iR = e12.f17758a.r();
        int i11 = this.f16563n;
        int i12 = this.o;
        if (iR >= 2) {
            interfaceC1655m.H(c1628b0.c, i7, i11, i12, binderC1198f);
            return;
        }
        BinderC1658n0 binderC1658n0 = c1628b0.c;
        interfaceC1655m.A0(binderC1658n0, i7, i12, binderC1198f);
        interfaceC1655m.U0(binderC1658n0, i7, i11, i12);
    }
}
