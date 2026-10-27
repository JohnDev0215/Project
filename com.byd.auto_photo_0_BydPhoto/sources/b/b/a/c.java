package b.b.a;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    public interface a {
        void a(Context context, String[] strArr, String str, File file, b.b.a.d dVar);
    }

    public interface b {
        String a(String str);

        String b(String str);

        void c(String str);

        String[] d();

        void e(String str);
    }

    /* JADX INFO: renamed from: b.b.a.c$c, reason: collision with other inner class name */
    public interface InterfaceC0021c {
        void a(Throwable th);

        void b();
    }

    public interface d {
        void a(String str);
    }

    public static void a(Context context, String str) {
        b(context, str, null, null);
    }

    public static void b(Context context, String str, String str2, InterfaceC0021c interfaceC0021c) {
        new b.b.a.d().f(context, str, str2, interfaceC0021c);
    }
}
