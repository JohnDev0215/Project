package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.e;
import retrofit2.h;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final o f5288a = e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f5289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final Constructor<MethodHandles.Lookup> f5290c;

    /* JADX INFO: compiled from: Platform.java */
    static final class a extends o {

        /* JADX INFO: renamed from: retrofit2.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Platform.java */
        static final class ExecutorC0100a implements Executor {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Handler f5291a = new Handler(Looper.getMainLooper());

            ExecutorC0100a() {
            }

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                this.f5291a.post(runnable);
            }
        }

        a() {
            super(Build.VERSION.SDK_INT >= 24);
        }

        @Override // retrofit2.o
        public Executor b() {
            return new ExecutorC0100a();
        }

        @Override // retrofit2.o
        @Nullable
        Object g(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            if (Build.VERSION.SDK_INT >= 26) {
                return super.g(method, cls, obj, objArr);
            }
            throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
        }
    }

    o(boolean z) {
        this.f5289b = z;
        Constructor<MethodHandles.Lookup> declaredConstructor = null;
        if (z) {
            try {
                declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                declaredConstructor.setAccessible(true);
            } catch (NoClassDefFoundError | NoSuchMethodException unused) {
            }
        }
        this.f5290c = declaredConstructor;
    }

    private static o e() {
        return "Dalvik".equals(System.getProperty("java.vm.name")) ? new a() : new o(true);
    }

    static o f() {
        return f5288a;
    }

    List<? extends e.a> a(@Nullable Executor executor) {
        i iVar = new i(executor);
        return this.f5289b ? Arrays.asList(g.f5201a, iVar) : Collections.singletonList(iVar);
    }

    @Nullable
    Executor b() {
        return null;
    }

    List<? extends h.a> c() {
        return this.f5289b ? Collections.singletonList(m.f5241a) : Collections.emptyList();
    }

    int d() {
        return this.f5289b ? 1 : 0;
    }

    @Nullable
    @IgnoreJRERequirement
    Object g(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        Constructor<MethodHandles.Lookup> constructor = this.f5290c;
        return (constructor != null ? constructor.newInstance(cls, -1) : MethodHandles.lookup()).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }

    @IgnoreJRERequirement
    boolean h(Method method) {
        return this.f5289b && method.isDefault();
    }
}
