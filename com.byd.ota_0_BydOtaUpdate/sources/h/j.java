package h;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import h.c;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f5799a = c();

    /* JADX INFO: compiled from: Platform.java */
    public static class a extends j {

        /* JADX INFO: renamed from: h.j$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Platform.java */
        public static class ExecutorC0102a implements Executor {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final Handler f5800a = new Handler(Looper.getMainLooper());

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                this.f5800a.post(runnable);
            }
        }

        @Override // h.j
        public c.a a(@Nullable Executor executor) {
            if (executor != null) {
                return new g(executor);
            }
            throw new AssertionError();
        }

        @Override // h.j
        public Executor b() {
            return new ExecutorC0102a();
        }
    }

    /* JADX INFO: compiled from: Platform.java */
    @IgnoreJRERequirement
    public static class b extends j {
        @Override // h.j
        public Object e(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
            Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            declaredConstructor.setAccessible(true);
            return ((MethodHandles.Lookup) declaredConstructor.newInstance(cls, -1)).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }

        @Override // h.j
        public boolean f(Method method) {
            return method.isDefault();
        }
    }

    public static j c() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new a();
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            Class.forName("java.util.Optional");
            return new b();
        } catch (ClassNotFoundException unused2) {
            return new j();
        }
    }

    public static j d() {
        return f5799a;
    }

    public c.a a(@Nullable Executor executor) {
        return executor != null ? new g(executor) : f.f5752a;
    }

    @Nullable
    public Executor b() {
        return null;
    }

    @Nullable
    public Object e(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }

    public boolean f(Method method) {
        return false;
    }
}
