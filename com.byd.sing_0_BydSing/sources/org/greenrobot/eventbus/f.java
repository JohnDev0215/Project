package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.logging.Level;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: classes.dex */
public interface f {

    /* JADX INFO: compiled from: Logger.java */
    public static class a {
        public static f a() {
            return (!org.greenrobot.eventbus.q.a.c() || b() == null) ? new b() : new org.greenrobot.eventbus.q.a("EventBus");
        }

        static Object b() {
            try {
                return Looper.getMainLooper();
            } catch (RuntimeException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: Logger.java */
    public static class b implements f {
        @Override // org.greenrobot.eventbus.f
        public void a(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.f
        public void b(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void b(Level level, String str, Throwable th);
}
