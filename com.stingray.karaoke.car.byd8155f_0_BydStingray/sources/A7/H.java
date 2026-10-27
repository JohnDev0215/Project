package A7;

import H7.InterfaceC0318c;
import H7.InterfaceC0319d;
import java.util.List;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class H implements H7.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0319d f446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f447b;
    public final int c;

    public H(InterfaceC0319d interfaceC0319d, List list, int i7) {
        m.f(interfaceC0319d, "classifier");
        m.f(list, "arguments");
        this.f446a = interfaceC0319d;
        this.f447b = list;
        this.c = i7;
    }

    @Override // H7.x
    public final List a() {
        return this.f447b;
    }

    @Override // H7.x
    public final boolean b() {
        return (this.c & 1) != 0;
    }

    @Override // H7.x
    public final InterfaceC0319d c() {
        return this.f446a;
    }

    public final String d(boolean z6) {
        String name;
        InterfaceC0319d interfaceC0319d = this.f446a;
        InterfaceC0318c interfaceC0318c = interfaceC0319d instanceof InterfaceC0318c ? (InterfaceC0318c) interfaceC0319d : null;
        Class clsI = interfaceC0318c != null ? n7.z.i(interfaceC0318c) : null;
        if (clsI == null) {
            name = interfaceC0319d.toString();
        } else if ((this.c & 4) != 0) {
            name = "kotlin.Nothing";
        } else if (clsI.isArray()) {
            name = clsI.equals(boolean[].class) ? "kotlin.BooleanArray" : clsI.equals(char[].class) ? "kotlin.CharArray" : clsI.equals(byte[].class) ? "kotlin.ByteArray" : clsI.equals(short[].class) ? "kotlin.ShortArray" : clsI.equals(int[].class) ? "kotlin.IntArray" : clsI.equals(float[].class) ? "kotlin.FloatArray" : clsI.equals(long[].class) ? "kotlin.LongArray" : clsI.equals(double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
        } else if (z6 && clsI.isPrimitive()) {
            m.d(interfaceC0319d, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
            name = n7.z.j((InterfaceC0318c) interfaceC0319d).getName();
        } else {
            name = clsI.getName();
        }
        return name + (this.f447b.isEmpty() ? "" : AbstractC1998m.f0(this.f447b, ", ", "<", ">", new F(this, 0), 24)) + (b() ? "?" : "");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof H) {
            H h10 = (H) obj;
            if (m.a(this.f446a, h10.f446a) && m.a(this.f447b, h10.f447b) && m.a(null, null) && this.c == h10.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + ((this.f447b.hashCode() + (this.f446a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return d(false) + " (Kotlin reflection is not available)";
    }
}
