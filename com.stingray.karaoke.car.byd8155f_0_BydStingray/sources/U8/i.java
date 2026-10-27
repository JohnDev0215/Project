package U8;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i extends A7.j implements InterfaceC2786o {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final i f9235t = new i(3, l.class, "processResultSelectReceiveCatching", "processResultSelectReceiveCatching(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        l lVar = (l) obj;
        AtomicLongFieldUpdater atomicLongFieldUpdater = l.o;
        lVar.getClass();
        if (obj3 == n.l) {
            obj3 = new q(lVar.v());
        }
        return new s(obj3);
    }
}
