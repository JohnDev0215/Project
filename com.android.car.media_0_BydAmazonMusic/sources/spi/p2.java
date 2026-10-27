package spi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: ConstructorConstructor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p2 implements b8<Object> {
    public final /* synthetic */ Constructor a;

    public p2(o2 o2Var, Constructor constructor) {
        this.a = constructor;
    }

    @Override // spi.b8
    public final Object a() {
        try {
            return this.a.newInstance(null);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            StringBuilder sbK = c.k("Failed to invoke ");
            sbK.append(this.a);
            sbK.append(" with no args");
            throw new RuntimeException(sbK.toString(), e2);
        } catch (InvocationTargetException e3) {
            StringBuilder sbK2 = c.k("Failed to invoke ");
            sbK2.append(this.a);
            sbK2.append(" with no args");
            throw new RuntimeException(sbK2.toString(), e3.getTargetException());
        }
    }
}
