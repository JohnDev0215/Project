package okhttp3.internal.platform.android;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class CloseGuard {
    public static final Companion d = new Companion(0);
    public final Method a;
    public final Method b;
    public final Method c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public CloseGuard(Method method, Method method2, Method method3) {
        this.a = method;
        this.b = method2;
        this.c = method3;
    }
}
