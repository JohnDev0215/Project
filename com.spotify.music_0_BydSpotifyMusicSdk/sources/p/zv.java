package p;

import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.InvalidRegistrarException;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zv implements yy1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ zv(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.yy1
    public final Object get() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                String str = (String) obj;
                try {
                    Class<?> cls = Class.forName(str);
                    if (ComponentRegistrar.class.isAssignableFrom(cls)) {
                        return (ComponentRegistrar) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    }
                    throw new InvalidRegistrarException(String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
                } catch (ClassNotFoundException unused) {
                    String.format("Class %s is not an found.", str);
                    return null;
                } catch (IllegalAccessException e) {
                    throw new InvalidRegistrarException(String.format("Could not instantiate %s.", str), e);
                } catch (InstantiationException e2) {
                    throw new InvalidRegistrarException(String.format("Could not instantiate %s.", str), e2);
                } catch (NoSuchMethodException e3) {
                    throw new InvalidRegistrarException(String.format("Could not instantiate %s", str), e3);
                } catch (InvocationTargetException e4) {
                    throw new InvalidRegistrarException(String.format("Could not instantiate %s", str), e4);
                }
            case 1:
                return (ComponentRegistrar) obj;
            default:
                Object obj2 = com.google.firebase.installations.a.m;
                return new g31((ru0) obj);
        }
    }
}
