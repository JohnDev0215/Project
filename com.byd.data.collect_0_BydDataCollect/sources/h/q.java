package h;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import h.e;
import h.h;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final q f5653a = e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f5654b;

    /* JADX INFO: compiled from: Platform.java */
    static final class a extends q {

        /* JADX INFO: renamed from: h.q$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Platform.java */
        static class ExecutorC0108a implements Executor {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Handler f5655a = new Handler(Looper.getMainLooper());

            ExecutorC0108a() {
            }

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                this.f5655a.post(runnable);
            }
        }

        a() {
            super(Build.VERSION.SDK_INT >= 24);
        }

        @Override // h.q
        public Executor b() {
            return new ExecutorC0108a();
        }
    }

    q(boolean z) {
        this.f5654b = z;
    }

    private static q e() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new a();
            }
        } catch (ClassNotFoundException unused) {
        }
        return new q(true);
    }

    static q f() {
        return f5653a;
    }

    List<? extends e.a> a(@Nullable Executor executor) {
        i iVar = new i(executor);
        return this.f5654b ? Arrays.asList(g.f5550a, iVar) : Collections.singletonList(iVar);
    }

    @Nullable
    Executor b() {
        return null;
    }

    List<? extends h.a> c() {
        return this.f5654b ? Collections.singletonList(o.f5606a) : Collections.emptyList();
    }

    int d() {
        return this.f5654b ? 1 : 0;
    }

    @Nullable
    Object g(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws NoSuchMethodException {
        Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
        declaredConstructor.setAccessible(true);
        return ((MethodHandles.Lookup) declaredConstructor.newInstance(cls, -1)).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }

    boolean h(Method method) {
        return this.f5654b && method.isDefault();
    }
}
