package b.a.d.b;

/* JADX INFO: compiled from: ULog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static InterfaceC0023a f321a;

    /* JADX INFO: renamed from: b.a.d.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ULog.java */
    public interface InterfaceC0023a {
        void a(String str, String[] strArr);

        void b(String str, String[] strArr);

        void c(String str, String[] strArr);

        void d(String str, String[] strArr);

        void e(String str, String[] strArr);
    }

    public static void a(String str, String... strArr) {
        InterfaceC0023a interfaceC0023a = f321a;
        if (interfaceC0023a != null) {
            interfaceC0023a.a(str, strArr);
        }
    }

    public static void b(Object obj, String str, String... strArr) {
        if ("DEBUG_TAG".equals(obj)) {
            a(str, strArr);
        }
    }

    public static void c(String str, String... strArr) {
        InterfaceC0023a interfaceC0023a = f321a;
        if (interfaceC0023a != null) {
            interfaceC0023a.c(str, strArr);
        }
    }

    public static void d(String str, String... strArr) {
        InterfaceC0023a interfaceC0023a = f321a;
        if (interfaceC0023a != null) {
            interfaceC0023a.e(str, strArr);
        }
    }

    public static void e(String str, String... strArr) {
        InterfaceC0023a interfaceC0023a = f321a;
        if (interfaceC0023a != null) {
            interfaceC0023a.d(str, strArr);
        }
    }

    public static void f(String str, String... strArr) {
        InterfaceC0023a interfaceC0023a = f321a;
        if (interfaceC0023a != null) {
            interfaceC0023a.b(str, strArr);
        }
    }
}
