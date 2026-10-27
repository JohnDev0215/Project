package m5;

import j5.C1552j;
import j5.EnumC1550h;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import l5.AbstractC1776d;
import l5.C1778f;
import l5.C1780h;
import o5.AbstractC2047c;
import q5.C2185a;

/* JADX INFO: renamed from: m5.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1841s implements j5.z {
    public final C1778f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final EnumC1550h f19246m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1780h f19247n;
    public final C1832i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final List f19248p;

    public C1841s(C1778f c1778f, EnumC1550h enumC1550h, C1780h c1780h, C1832i c1832i, List list) {
        this.l = c1778f;
        this.f19246m = enumC1550h;
        this.f19247n = c1780h;
        this.o = c1832i;
        this.f19248p = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void b(Object obj, AccessibleObject accessibleObject) {
        if (Modifier.isStatic(((Member) accessibleObject).getModifiers())) {
            obj = null;
        }
        if (!l5.r.f18664a.a(obj, accessibleObject)) {
            throw new j5.p(android.hardware.bydauto.ac.tsimpl.a.h(AbstractC2047c.d(accessibleObject, true), " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type."));
        }
    }

    public static void c(Class cls, String str, Field field, Field field2) {
        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + str + "'; conflict is caused by fields " + AbstractC2047c.c(field) + " and " + AbstractC2047c.c(field2) + "\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("duplicate-fields"));
    }

    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        Class cls = c2185a.f21253a;
        if (!Object.class.isAssignableFrom(cls)) {
            return null;
        }
        h6.r rVar = AbstractC2047c.f20529a;
        if (!Modifier.isStatic(cls.getModifiers()) && (cls.isAnonymousClass() || cls.isLocalClass())) {
            return new C1552j(2);
        }
        AbstractC1776d.f(this.f19248p);
        return AbstractC2047c.f20529a.m(cls) ? new r(cls, d(mVar, c2185a, cls, true)) : new C1839p(this.l.h(c2185a), d(mVar, c2185a, cls, false));
    }

    /* JADX WARN: Code restructure failed: missing block: B:92:0x01af, code lost:
    
        r11 = new q5.C2185a(l5.AbstractC1776d.j(r1, r12, r12.getGenericSuperclass(), new java.util.HashMap()));
        r12 = r11.f21253a;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01a9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0198  */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r23v0 */
    /* JADX WARN: Type inference failed for: r23v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r23v2 */
    /* JADX WARN: Type inference failed for: r24v0 */
    /* JADX WARN: Type inference failed for: r24v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r24v2 */
    /* JADX WARN: Type inference failed for: r27v0 */
    /* JADX WARN: Type inference failed for: r27v1 */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r27v3 */
    /* JADX WARN: Type inference failed for: r27v4 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r28v0 */
    /* JADX WARN: Type inference failed for: r28v1 */
    /* JADX WARN: Type inference failed for: r28v2 */
    /* JADX WARN: Type inference failed for: r28v3 */
    /* JADX WARN: Type inference failed for: r28v4 */
    /* JADX WARN: Type inference failed for: r32v0, types: [m5.s] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final m5.C1840q d(j5.m r33, q5.C2185a r34, java.lang.Class r35, boolean r36) {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m5.C1841s.d(j5.m, q5.a, java.lang.Class, boolean):m5.q");
    }

    public final boolean e(Field field, boolean z6) {
        boolean z10;
        C1780h c1780h = this.f19247n;
        c1780h.getClass();
        if ((field.getModifiers() & 136) != 0 || field.isSynthetic() || c1780h.b(field.getType(), z6)) {
            z10 = true;
        } else {
            List list = z6 ? c1780h.l : c1780h.f18645m;
            if (!list.isEmpty()) {
                Iterator it = list.iterator();
                if (it.hasNext()) {
                    it.next().getClass();
                    throw new ClassCastException();
                }
            }
            z10 = false;
        }
        return !z10;
    }
}
