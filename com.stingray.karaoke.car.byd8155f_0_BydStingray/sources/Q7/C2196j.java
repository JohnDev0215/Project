package q7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import m7.k;
import r7.EnumC2303a;
import s7.InterfaceC2411d;

/* JADX INFO: renamed from: q7.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2196j implements InterfaceC2189c, InterfaceC2411d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21258m = AtomicReferenceFieldUpdater.newUpdater(C2196j.class, Object.class, "result");
    public final InterfaceC2189c l;
    private volatile Object result;

    public C2196j(InterfaceC2189c interfaceC2189c, EnumC2303a enumC2303a) {
        this.l = interfaceC2189c;
        this.result = enumC2303a;
    }

    public final Object a() throws Throwable {
        Object obj = this.result;
        EnumC2303a enumC2303a = EnumC2303a.f21872m;
        if (obj == enumC2303a) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21258m;
            EnumC2303a enumC2303a2 = EnumC2303a.l;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, enumC2303a, enumC2303a2)) {
                if (atomicReferenceFieldUpdater.get(this) != enumC2303a) {
                    obj = this.result;
                }
            }
            return EnumC2303a.l;
        }
        if (obj == EnumC2303a.f21873n) {
            return EnumC2303a.l;
        }
        if (obj instanceof k) {
            throw ((k) obj).l;
        }
        return obj;
    }

    @Override // s7.InterfaceC2411d
    public final InterfaceC2411d getCallerFrame() {
        InterfaceC2189c interfaceC2189c = this.l;
        if (interfaceC2189c instanceof InterfaceC2411d) {
            return (InterfaceC2411d) interfaceC2189c;
        }
        return null;
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return this.l.getContext();
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this.result;
            EnumC2303a enumC2303a = EnumC2303a.f21872m;
            if (obj2 == enumC2303a) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21258m;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, enumC2303a, obj)) {
                    if (atomicReferenceFieldUpdater.get(this) != enumC2303a) {
                        break;
                    }
                }
                return;
            }
            EnumC2303a enumC2303a2 = EnumC2303a.l;
            if (obj2 != enumC2303a2) {
                throw new IllegalStateException("Already resumed");
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f21258m;
            EnumC2303a enumC2303a3 = EnumC2303a.f21873n;
            while (!atomicReferenceFieldUpdater2.compareAndSet(this, enumC2303a2, enumC2303a3)) {
                if (atomicReferenceFieldUpdater2.get(this) != enumC2303a2) {
                    break;
                }
            }
            this.l.resumeWith(obj);
            return;
        }
    }

    public final String toString() {
        return "SafeContinuation for " + this.l;
    }
}
