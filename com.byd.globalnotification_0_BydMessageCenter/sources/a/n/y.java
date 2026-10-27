package a.n;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class y extends b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static y f1123b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Application f1124a;

    public y(Application application) {
        this.f1124a = application;
    }

    @Override // a.n.b0, a.n.z
    public <T extends x> T a(Class<T> cls) {
        if (!a.class.isAssignableFrom(cls)) {
            return (T) super.a(cls);
        }
        try {
            return cls.getConstructor(Application.class).newInstance(this.f1124a);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot create an instance of " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Cannot create an instance of " + cls, e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("Cannot create an instance of " + cls, e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException("Cannot create an instance of " + cls, e5);
        }
    }
}
