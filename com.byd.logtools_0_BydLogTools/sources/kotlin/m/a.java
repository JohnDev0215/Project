package kotlin.m;

import java.lang.reflect.Method;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: PlatformImplementations.kt */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: kotlin.m.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PlatformImplementations.kt */
    private static final class C0094a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Method f5128a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final C0094a f5129b = new C0094a();

        /* JADX WARN: Removed duplicated region for block: B:9:0x0041  */
        static {
            /*
                kotlin.m.a$a r0 = new kotlin.m.a$a
                r0.<init>()
                kotlin.m.a.C0094a.f5129b = r0
                java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
                java.lang.reflect.Method[] r1 = r0.getMethods()
                java.lang.String r2 = "throwableClass.methods"
                kotlin.jvm.internal.i.b(r1, r2)
                int r2 = r1.length
                r3 = 0
                r4 = r3
            L15:
                if (r4 >= r2) goto L48
                r5 = r1[r4]
                java.lang.String r6 = "it"
                kotlin.jvm.internal.i.b(r5, r6)
                java.lang.String r6 = r5.getName()
                java.lang.String r7 = "addSuppressed"
                boolean r6 = kotlin.jvm.internal.i.a(r6, r7)
                if (r6 == 0) goto L41
                java.lang.Class[] r6 = r5.getParameterTypes()
                java.lang.String r7 = "it.parameterTypes"
                kotlin.jvm.internal.i.b(r6, r7)
                java.lang.Object r6 = kotlin.collections.c.r(r6)
                java.lang.Class r6 = (java.lang.Class) r6
                boolean r6 = kotlin.jvm.internal.i.a(r6, r0)
                if (r6 == 0) goto L41
                r6 = 1
                goto L42
            L41:
                r6 = r3
            L42:
                if (r6 == 0) goto L45
                goto L49
            L45:
                int r4 = r4 + 1
                goto L15
            L48:
                r5 = 0
            L49:
                kotlin.m.a.C0094a.f5128a = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.m.a.C0094a.<clinit>():void");
        }

        private C0094a() {
        }
    }

    public void a(Throwable th, Throwable th2) {
        i.c(th, "cause");
        i.c(th2, "exception");
        Method method = C0094a.f5128a;
        if (method != null) {
            method.invoke(th, th2);
        }
    }
}
