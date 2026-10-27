package g2;

import java.lang.reflect.Constructor;

/* JADX INFO: compiled from: ThrowableDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 extends o {
    public b0(f2.h hVar, Class<?> cls) {
        super(hVar, cls, cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    @Override // g2.o, g2.t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T b(f2.a r13, java.lang.reflect.Type r14, java.lang.Object r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g2.b0.b(f2.a, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    @Override // g2.o, g2.t
    public int e() {
        return 12;
    }

    public final Throwable t(String str, Throwable th, Class<?> cls) throws Exception {
        Constructor<?> constructor = null;
        Constructor<?> constructor2 = null;
        Constructor<?> constructor3 = null;
        for (Constructor<?> constructor4 : cls.getConstructors()) {
            Class<?>[] parameterTypes = constructor4.getParameterTypes();
            if (parameterTypes.length == 0) {
                constructor3 = constructor4;
            } else if (parameterTypes.length == 1 && parameterTypes[0] == String.class) {
                constructor2 = constructor4;
            } else if (parameterTypes.length == 2 && parameterTypes[0] == String.class && parameterTypes[1] == Throwable.class) {
                constructor = constructor4;
            }
        }
        if (constructor != null) {
            return (Throwable) constructor.newInstance(str, th);
        }
        if (constructor2 != null) {
            return (Throwable) constructor2.newInstance(str);
        }
        if (constructor3 != null) {
            return (Throwable) constructor3.newInstance(new Object[0]);
        }
        return null;
    }
}
