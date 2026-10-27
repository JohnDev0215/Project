package m5;

import h2.AbstractC1347d;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import o5.AbstractC2047c;
import r5.C2297a;

/* JADX INFO: loaded from: classes.dex */
public final class r extends AbstractC1838o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f19244e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Constructor f19245b;
    public final Object[] c;
    public final HashMap d;

    static {
        HashMap map = new HashMap();
        map.put(Byte.TYPE, (byte) 0);
        map.put(Short.TYPE, (short) 0);
        map.put(Integer.TYPE, 0);
        map.put(Long.TYPE, 0L);
        map.put(Float.TYPE, Float.valueOf(0.0f));
        map.put(Double.TYPE, Double.valueOf(0.0d));
        map.put(Character.TYPE, (char) 0);
        map.put(Boolean.TYPE, Boolean.FALSE);
        f19244e = map;
    }

    public r(Class cls, C1840q c1840q) {
        super(c1840q);
        this.d = new HashMap();
        h6.r rVar = AbstractC2047c.f20529a;
        Constructor constructorG = rVar.g(cls);
        this.f19245b = constructorG;
        AbstractC2047c.f(constructorG);
        String[] strArrJ = rVar.j(cls);
        for (int i7 = 0; i7 < strArrJ.length; i7++) {
            this.d.put(strArrJ[i7], Integer.valueOf(i7));
        }
        Class<?>[] parameterTypes = this.f19245b.getParameterTypes();
        this.c = new Object[parameterTypes.length];
        for (int i10 = 0; i10 < parameterTypes.length; i10++) {
            this.c[i10] = f19244e.get(parameterTypes[i10]);
        }
    }

    @Override // m5.AbstractC1838o
    public final Object c() {
        return (Object[]) this.c.clone();
    }

    @Override // m5.AbstractC1838o
    public final Object d(Object obj) {
        Object[] objArr = (Object[]) obj;
        Constructor constructor = this.f19245b;
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException e10) {
            h6.r rVar = AbstractC2047c.f20529a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.11.0). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e10);
        } catch (IllegalArgumentException | InstantiationException e11) {
            throw new RuntimeException("Failed to invoke constructor '" + AbstractC2047c.b(constructor) + "' with args " + Arrays.toString(objArr), e11);
        } catch (InvocationTargetException e12) {
            throw new RuntimeException("Failed to invoke constructor '" + AbstractC2047c.b(constructor) + "' with args " + Arrays.toString(objArr), e12.getCause());
        }
    }

    @Override // m5.AbstractC1838o
    public final void e(Object obj, C2297a c2297a, C1837n c1837n) {
        Object[] objArr = (Object[]) obj;
        HashMap map = this.d;
        String str = c1837n.c;
        Integer num = (Integer) map.get(str);
        if (num == null) {
            throw new IllegalStateException("Could not find the index in the constructor '" + AbstractC2047c.b(this.f19245b) + "' for field with name '" + str + "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
        }
        int iIntValue = num.intValue();
        Object objA = c1837n.f19237g.a(c2297a);
        if (objA != null || !c1837n.f19238h) {
            objArr[iIntValue] = objA;
        } else {
            StringBuilder sbJ = AbstractC1347d.j("null is not allowed as value for record component '", str, "' of primitive type; at path ");
            sbJ.append(c2297a.q(false));
            throw new A9.k(sbJ.toString());
        }
    }
}
