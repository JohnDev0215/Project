package androidx.compose.ui.input.pointer;

import A7.m;
import E0.V;
import g0.n;
import java.util.Arrays;
import kotlin.Metadata;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/SuspendPointerInputElement;", "LE0/V;", "Ly0/B;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SuspendPointerInputElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f11610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f11611b;
    public final Object[] c;
    public final InterfaceC2785n d;

    public SuspendPointerInputElement(Object obj, Object obj2, InterfaceC2785n interfaceC2785n, int i7) {
        obj2 = (i7 & 2) != 0 ? null : obj2;
        this.f11610a = obj;
        this.f11611b = obj2;
        this.c = null;
        this.d = interfaceC2785n;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuspendPointerInputElement)) {
            return false;
        }
        SuspendPointerInputElement suspendPointerInputElement = (SuspendPointerInputElement) obj;
        if (!m.a(this.f11610a, suspendPointerInputElement.f11610a) || !m.a(this.f11611b, suspendPointerInputElement.f11611b)) {
            return false;
        }
        Object[] objArr = this.c;
        if (objArr != null) {
            Object[] objArr2 = suspendPointerInputElement.c;
            if (objArr2 == null || !Arrays.equals(objArr, objArr2)) {
                return false;
            }
        } else if (suspendPointerInputElement.c != null) {
            return false;
        }
        return this.d == suspendPointerInputElement.d;
    }

    public final int hashCode() {
        Object obj = this.f11610a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.f11611b;
        int iHashCode2 = (iHashCode + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        Object[] objArr = this.c;
        return this.d.hashCode() + ((iHashCode2 + (objArr != null ? Arrays.hashCode(objArr) : 0)) * 31);
    }

    @Override // E0.V
    public final n i() {
        return new C2722B(this.f11610a, this.f11611b, this.c, this.d);
    }

    @Override // E0.V
    public final void l(n nVar) {
        C2722B c2722b = (C2722B) nVar;
        Object obj = c2722b.f24047y;
        Object obj2 = this.f11610a;
        boolean z6 = !m.a(obj, obj2);
        c2722b.f24047y = obj2;
        Object obj3 = c2722b.f24048z;
        Object obj4 = this.f11611b;
        if (!m.a(obj3, obj4)) {
            z6 = true;
        }
        c2722b.f24048z = obj4;
        Object[] objArr = c2722b.f24040A;
        Object[] objArr2 = this.c;
        if (objArr != null && objArr2 == null) {
            z6 = true;
        }
        if (objArr == null && objArr2 != null) {
            z6 = true;
        }
        boolean z10 = (objArr == null || objArr2 == null || Arrays.equals(objArr2, objArr)) ? z6 : true;
        c2722b.f24040A = objArr2;
        if (z10) {
            c2722b.J0();
        }
        c2722b.B = this.d;
    }
}
