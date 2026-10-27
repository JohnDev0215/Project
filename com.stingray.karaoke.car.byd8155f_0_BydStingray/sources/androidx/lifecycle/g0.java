package androidx.lifecycle;

import android.app.Application;
import b2.C0993c;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends i0 {
    public static g0 c;
    public static final C8.d d = new C8.d(17);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Application f11989b;

    public g0(Application application) {
        this.f11989b = application;
    }

    @Override // androidx.lifecycle.i0, androidx.lifecycle.h0
    public final f0 a(Class cls) {
        Application application = this.f11989b;
        if (application != null) {
            return d(cls, application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }

    @Override // androidx.lifecycle.i0, androidx.lifecycle.h0
    public final f0 c(Class cls, C0993c c0993c) {
        if (this.f11989b != null) {
            return a(cls);
        }
        Application application = (Application) c0993c.f12203a.get(d);
        if (application != null) {
            return d(cls, application);
        }
        if (AbstractC0963a.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }
        return C7.a.D(cls);
    }

    public final f0 d(Class cls, Application application) {
        if (!AbstractC0963a.class.isAssignableFrom(cls)) {
            return C7.a.D(cls);
        }
        try {
            f0 f0Var = (f0) cls.getConstructor(Application.class).newInstance(application);
            A7.m.e(f0Var, "{\n                try {\n…          }\n            }");
            return f0Var;
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("Cannot create an instance of " + cls, e10);
        } catch (InstantiationException e11) {
            throw new RuntimeException("Cannot create an instance of " + cls, e11);
        } catch (NoSuchMethodException e12) {
            throw new RuntimeException("Cannot create an instance of " + cls, e12);
        } catch (InvocationTargetException e13) {
            throw new RuntimeException("Cannot create an instance of " + cls, e13);
        }
    }
}
