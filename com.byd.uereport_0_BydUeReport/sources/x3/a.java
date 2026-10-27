package x3;

/* JADX INFO: compiled from: ULog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static InterfaceC0144a f9080a;

    /* JADX INFO: renamed from: x3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ULog.java */
    public interface InterfaceC0144a {
        void a(String str, String[] strArr);

        void b(String str, String[] strArr);

        void c(String str, String[] strArr);

        void d(String str, String[] strArr);
    }

    public static void a(String str, String... strArr) {
        InterfaceC0144a interfaceC0144a = f9080a;
        if (interfaceC0144a != null) {
            interfaceC0144a.a(str, strArr);
        }
    }

    public static void b(String str, String... strArr) {
        InterfaceC0144a interfaceC0144a = f9080a;
        if (interfaceC0144a != null) {
            interfaceC0144a.c(str, strArr);
        }
    }

    public static void c(String str, String... strArr) {
        InterfaceC0144a interfaceC0144a = f9080a;
        if (interfaceC0144a != null) {
            interfaceC0144a.d(str, strArr);
        }
    }

    public static void d(InterfaceC0144a interfaceC0144a) {
        f9080a = interfaceC0144a;
    }

    public static void e(String str, String... strArr) {
        InterfaceC0144a interfaceC0144a = f9080a;
        if (interfaceC0144a != null) {
            interfaceC0144a.b(str, strArr);
        }
    }
}
