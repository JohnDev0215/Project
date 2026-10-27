package a.n;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.SavedStateHandleController;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class w extends a0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class<?>[] f1115f = {Application.class, v.class};
    public static final Class<?>[] g = {v.class};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f1116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y f1117b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bundle f1118c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f1119d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a.q.a f1120e;

    @SuppressLint({"LambdaLast"})
    public w(Application application, a.q.c cVar, Bundle bundle) {
        this.f1120e = cVar.d();
        this.f1119d = cVar.a();
        this.f1118c = bundle;
        this.f1116a = application;
        if (y.f1123b == null) {
            y.f1123b = new y(application);
        }
        this.f1117b = y.f1123b;
    }

    public static <T> Constructor<T> d(Class<T> cls, Class<?>[] clsArr) {
        for (Object obj : cls.getConstructors()) {
            Constructor<T> constructor = (Constructor<T>) obj;
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    @Override // a.n.a0, a.n.z
    public <T extends x> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) c(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // a.n.c0
    public void b(x xVar) {
        SavedStateHandleController.h(xVar, this.f1120e, this.f1119d);
    }

    @Override // a.n.a0
    public <T extends x> T c(String str, Class<T> cls) {
        boolean zIsAssignableFrom = a.class.isAssignableFrom(cls);
        Constructor constructorD = zIsAssignableFrom ? d(cls, f1115f) : d(cls, g);
        if (constructorD == null) {
            return (T) this.f1117b.a(cls);
        }
        a.q.a aVar = this.f1120e;
        g gVar = this.f1119d;
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, v.a(aVar.a(str), this.f1118c));
        savedStateHandleController.i(aVar, gVar);
        SavedStateHandleController.j(aVar, gVar);
        try {
            T t = (T) (zIsAssignableFrom ? constructorD.newInstance(this.f1116a, savedStateHandleController.f1587c) : constructorD.newInstance(savedStateHandleController.f1587c));
            t.e("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController);
            return t;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to access " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e4.getCause());
        }
    }
}
