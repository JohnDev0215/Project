package defpackage;

import android.os.Looper;
import java.util.logging.Level;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: classes.dex */
public interface em {

    /* JADX INFO: compiled from: Logger.java */
    public static class a {
        public static em a() {
            return (!pm.c() || b() == null) ? new b() : new pm("EventBus");
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
    public static class b implements em {
        @Override // defpackage.em
        public void a(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // defpackage.em
        public void b(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void b(Level level, String str, Throwable th);
}
