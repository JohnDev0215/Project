package m5;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.util.HashMap;
import k5.InterfaceC1688b;
import r5.C2297a;

/* JADX INFO: renamed from: m5.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1843u extends j5.y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19250b;
    public final Object c;
    public final Object d;

    public C1843u(j5.m mVar, j5.y yVar, Type type) {
        this.f19249a = 0;
        this.f19250b = mVar;
        this.c = yVar;
        this.d = type;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        switch (this.f19249a) {
            case 0:
                return ((j5.y) this.c).a(c2297a);
            default:
                if (c2297a.Q() == 9) {
                    c2297a.M();
                    return null;
                }
                String strO = c2297a.O();
                Enum r02 = (Enum) ((HashMap) this.f19250b).get(strO);
                return r02 == null ? (Enum) ((HashMap) this.c).get(strO) : r02;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005b  */
    @Override // j5.y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(r5.C2298b r4, java.lang.Object r5) throws java.io.IOException {
        /*
            r3 = this;
            int r0 = r3.f19249a
            switch(r0) {
                case 0: goto L19;
                default: goto L5;
            }
        L5:
            java.lang.Enum r5 = (java.lang.Enum) r5
            if (r5 != 0) goto Lb
            r3 = 0
            goto L15
        Lb:
            java.lang.Object r3 = r3.d
            java.util.HashMap r3 = (java.util.HashMap) r3
            java.lang.Object r3 = r3.get(r5)
            java.lang.String r3 = (java.lang.String) r3
        L15:
            r4.L(r3)
            return
        L19:
            java.lang.Object r0 = r3.d
            java.lang.reflect.Type r0 = (java.lang.reflect.Type) r0
            if (r5 == 0) goto L2c
            boolean r1 = r0 instanceof java.lang.Class
            if (r1 != 0) goto L27
            boolean r1 = r0 instanceof java.lang.reflect.TypeVariable
            if (r1 == 0) goto L2c
        L27:
            java.lang.Class r1 = r5.getClass()
            goto L2d
        L2c:
            r1 = r0
        L2d:
            java.lang.Object r2 = r3.c
            j5.y r2 = (j5.y) r2
            if (r1 == r0) goto L5c
            q5.a r0 = new q5.a
            r0.<init>(r1)
            java.lang.Object r3 = r3.f19250b
            j5.m r3 = (j5.m) r3
            j5.y r3 = r3.c(r0)
            boolean r0 = r3 instanceof m5.AbstractC1838o
            if (r0 != 0) goto L45
            goto L5b
        L45:
            r0 = r2
        L46:
            boolean r1 = r0 instanceof m5.AbstractC1842t
            if (r1 == 0) goto L56
            r1 = r0
            m5.t r1 = (m5.AbstractC1842t) r1
            j5.y r1 = r1.c()
            if (r1 != r0) goto L54
            goto L56
        L54:
            r0 = r1
            goto L46
        L56:
            boolean r0 = r0 instanceof m5.AbstractC1838o
            if (r0 != 0) goto L5b
            goto L5c
        L5b:
            r2 = r3
        L5c:
            r2.b(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m5.C1843u.b(r5.b, java.lang.Object):void");
    }

    public C1843u(Class cls) {
        this.f19249a = 1;
        this.f19250b = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        try {
            for (Field field : (Field[]) AccessController.doPrivileged(new b0(cls))) {
                Enum r42 = (Enum) field.get(null);
                String strName = r42.name();
                String string = r42.toString();
                InterfaceC1688b interfaceC1688b = (InterfaceC1688b) field.getAnnotation(InterfaceC1688b.class);
                if (interfaceC1688b != null) {
                    strName = interfaceC1688b.value();
                    for (String str : interfaceC1688b.alternate()) {
                        ((HashMap) this.f19250b).put(str, r42);
                    }
                }
                ((HashMap) this.f19250b).put(strName, r42);
                ((HashMap) this.c).put(string, r42);
                ((HashMap) this.d).put(r42, strName);
            }
        } catch (IllegalAccessException e10) {
            throw new AssertionError(e10);
        }
    }
}
