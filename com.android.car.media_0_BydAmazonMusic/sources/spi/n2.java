package spi;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: ConstructorConstructor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n2 implements b8<Object> {
    public final ac a;
    public final /* synthetic */ Class b;
    public final /* synthetic */ Type c;

    public n2(o2 o2Var, Class cls, Type type) {
        ac zbVar;
        this.b = cls;
        this.c = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            zbVar = new wb(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    zbVar = new xb(declaredMethod2, iIntValue);
                } catch (Exception unused2) {
                    zbVar = new zb();
                }
            } catch (Exception unused3) {
                Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod3.setAccessible(true);
                zbVar = new yb(declaredMethod3);
            }
        }
        this.a = zbVar;
    }

    @Override // spi.b8
    public final Object a() {
        try {
            return this.a.b(this.b);
        } catch (Exception e) {
            StringBuilder sbK = c.k("Unable to invoke no-args constructor for ");
            sbK.append(this.c);
            sbK.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
            throw new RuntimeException(sbK.toString(), e);
        }
    }
}
