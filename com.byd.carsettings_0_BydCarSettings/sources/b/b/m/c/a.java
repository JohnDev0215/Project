package b.b.m.c;

/* JADX INFO: compiled from: ULog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static InterfaceC0085a f2097a;

    /* JADX INFO: renamed from: b.b.m.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ULog.java */
    public interface InterfaceC0085a {
        void a(String str, String[] strArr);

        void b(String str, String[] strArr);

        void c(String str, String[] strArr);

        void d(String str, String[] strArr);

        void e(String str, String[] strArr);
    }

    public static void a(String str, String... strArr) {
        InterfaceC0085a interfaceC0085a = f2097a;
        if (interfaceC0085a != null) {
            interfaceC0085a.a(str, strArr);
        }
    }

    public static void b(Object obj, String str, String... strArr) {
        if ("DEBUG_TAG".equals(obj)) {
            a(str, strArr);
        }
    }

    public static void c(String str, String... strArr) {
        InterfaceC0085a interfaceC0085a = f2097a;
        if (interfaceC0085a != null) {
            interfaceC0085a.c(str, strArr);
        }
    }

    public static void d(String str, String... strArr) {
        InterfaceC0085a interfaceC0085a = f2097a;
        if (interfaceC0085a != null) {
            interfaceC0085a.e(str, strArr);
        }
    }

    public static void e(InterfaceC0085a interfaceC0085a) {
        f2097a = interfaceC0085a;
    }

    public static void f(String str, String... strArr) {
        InterfaceC0085a interfaceC0085a = f2097a;
        if (interfaceC0085a != null) {
            interfaceC0085a.d(str, strArr);
        }
    }

    public static void g(String str, String... strArr) {
        InterfaceC0085a interfaceC0085a = f2097a;
        if (interfaceC0085a != null) {
            interfaceC0085a.b(str, strArr);
        }
    }
}
