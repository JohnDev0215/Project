package h;

import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.A;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0986y;

/* JADX INFO: renamed from: h.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1335g implements InterfaceC0986y {
    public final /* synthetic */ x l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f15097m;

    public /* synthetic */ C1335g(x xVar, n nVar) {
        this.l = xVar;
        this.f15097m = nVar;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        x xVar = this.l;
        n nVar = this.f15097m;
        if (enumC0980s == EnumC0980s.ON_CREATE) {
            OnBackInvokedDispatcher onBackInvokedDispatcherA = C1336h.f15098a.a(nVar);
            A7.m.f(onBackInvokedDispatcherA, "invoker");
            xVar.f15146e = onBackInvokedDispatcherA;
            xVar.d(xVar.f15148g);
        }
    }
}
