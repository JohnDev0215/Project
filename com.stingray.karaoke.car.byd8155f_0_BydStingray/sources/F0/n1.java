package F0;

import S8.AbstractC0727y;
import S8.C0712i;
import S8.EnumC0726x;
import S8.InterfaceC0710g;
import T.C0767t0;
import android.view.View;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0986y;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public final class n1 implements InterfaceC0986y {
    public final /* synthetic */ X8.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0208i0 f2232m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0767t0 f2233n;
    public final /* synthetic */ A7.z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ View f2234p;

    public n1(X8.c cVar, C0208i0 c0208i0, C0767t0 c0767t0, A7.z zVar, View view) {
        this.l = cVar;
        this.f2232m = c0208i0;
        this.f2233n = c0767t0;
        this.o = zVar;
        this.f2234p = view;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(androidx.lifecycle.A a10, EnumC0980s enumC0980s) throws IllegalAccessException, InvocationTargetException {
        boolean z6;
        int i7 = j1.f2215a[enumC0980s.ordinal()];
        InterfaceC0710g interfaceC0710gT = null;
        if (i7 == 1) {
            AbstractC0727y.w(this.l, null, EnumC0726x.o, new m1(this.o, this.f2233n, a10, this, this.f2234p, null), 1);
            return;
        }
        if (i7 != 2) {
            if (i7 != 3) {
                if (i7 != 4) {
                    return;
                }
                this.f2233n.s();
                return;
            } else {
                C0767t0 c0767t0 = this.f2233n;
                synchronized (c0767t0.f8352b) {
                    c0767t0.f8363q = true;
                }
                return;
            }
        }
        C0208i0 c0208i0 = this.f2232m;
        if (c0208i0 != null) {
            A2.c cVar = (A2.c) c0208i0.f2212n;
            synchronized (cVar.c) {
                try {
                    synchronized (cVar.c) {
                        z6 = cVar.f311b;
                    }
                    if (!z6) {
                        ArrayList arrayList = (ArrayList) cVar.d;
                        cVar.d = (ArrayList) cVar.f312e;
                        cVar.f312e = arrayList;
                        cVar.f311b = true;
                        int size = arrayList.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            ((InterfaceC2189c) arrayList.get(i10)).resumeWith(m7.q.f19267a);
                        }
                        arrayList.clear();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        C0767t0 c0767t02 = this.f2233n;
        synchronized (c0767t02.f8352b) {
            if (c0767t02.f8363q) {
                c0767t02.f8363q = false;
                interfaceC0710gT = c0767t02.t();
            }
        }
        if (interfaceC0710gT != null) {
            ((C0712i) interfaceC0710gT).resumeWith(m7.q.f19267a);
        }
    }
}
