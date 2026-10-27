package j7;

import android.os.Looper;
import java.util.logging.Level;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: classes2.dex */
public interface f {

    /* JADX INFO: compiled from: Logger.java */
    public static class a {
        public static f a() {
            return (!k7.a.c() || b() == null) ? new b() : new k7.a("EventBus");
        }

        public static Object b() {
            try {
                return Looper.getMainLooper();
            } catch (RuntimeException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: Logger.java */
    public static class b implements f {
        @Override // j7.f
        public void a(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // j7.f
        public void b(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void b(Level level, String str, Throwable th);
}
