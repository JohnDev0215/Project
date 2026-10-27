package U8;

import b9.C1015b;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2194h;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: U8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0811b implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9225m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9226n;

    public /* synthetic */ C0811b(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f9225m = obj;
        this.f9226n = obj2;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws IllegalAccessException, InvocationTargetException {
        switch (this.l) {
            case 0:
                X8.a.a((InterfaceC2782k) this.f9225m, this.f9226n, (InterfaceC2194h) obj3);
                break;
            default:
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b9.c.f12335h;
                C1015b c1015b = (C1015b) this.f9226n;
                Object obj4 = c1015b.f12333m;
                b9.c cVar = (b9.c) this.f9225m;
                atomicReferenceFieldUpdater.set(cVar, obj4);
                cVar.e(c1015b.f12333m);
                break;
        }
        return m7.q.f19267a;
    }
}
