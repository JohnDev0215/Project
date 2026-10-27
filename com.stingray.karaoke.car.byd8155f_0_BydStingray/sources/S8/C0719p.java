package S8;

import java.util.concurrent.CancellationException;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: S8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0719p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0709f f8127b;
    public final InterfaceC2786o c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f8128e;

    public C0719p(Object obj, InterfaceC0709f interfaceC0709f, InterfaceC2786o interfaceC2786o, Object obj2, Throwable th) {
        this.f8126a = obj;
        this.f8127b = interfaceC0709f;
        this.c = interfaceC2786o;
        this.d = obj2;
        this.f8128e = th;
    }

    public static C0719p a(C0719p c0719p, InterfaceC0709f interfaceC0709f, CancellationException cancellationException, int i7) {
        Object obj = c0719p.f8126a;
        if ((i7 & 2) != 0) {
            interfaceC0709f = c0719p.f8127b;
        }
        InterfaceC0709f interfaceC0709f2 = interfaceC0709f;
        InterfaceC2786o interfaceC2786o = c0719p.c;
        Object obj2 = c0719p.d;
        Throwable th = cancellationException;
        if ((i7 & 16) != 0) {
            th = c0719p.f8128e;
        }
        c0719p.getClass();
        return new C0719p(obj, interfaceC0709f2, interfaceC2786o, obj2, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0719p)) {
            return false;
        }
        C0719p c0719p = (C0719p) obj;
        return A7.m.a(this.f8126a, c0719p.f8126a) && A7.m.a(this.f8127b, c0719p.f8127b) && A7.m.a(this.c, c0719p.c) && A7.m.a(this.d, c0719p.d) && A7.m.a(this.f8128e, c0719p.f8128e);
    }

    public final int hashCode() {
        Object obj = this.f8126a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        InterfaceC0709f interfaceC0709f = this.f8127b;
        int iHashCode2 = (iHashCode + (interfaceC0709f == null ? 0 : interfaceC0709f.hashCode())) * 31;
        InterfaceC2786o interfaceC2786o = this.c;
        int iHashCode3 = (iHashCode2 + (interfaceC2786o == null ? 0 : interfaceC2786o.hashCode())) * 31;
        Object obj2 = this.d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f8128e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.f8126a + ", cancelHandler=" + this.f8127b + ", onCancellation=" + this.c + ", idempotentResume=" + this.d + ", cancelCause=" + this.f8128e + ')';
    }

    public /* synthetic */ C0719p(Object obj, InterfaceC0709f interfaceC0709f, InterfaceC2786o interfaceC2786o, CancellationException cancellationException, int i7) {
        this(obj, (i7 & 2) != 0 ? null : interfaceC0709f, (i7 & 4) != 0 ? null : interfaceC2786o, (Object) null, (i7 & 16) != 0 ? null : cancellationException);
    }
}
