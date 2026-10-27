package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public interface g {

    public static class a {
        public static g a() {
            return (!org.greenrobot.eventbus.r.a.c() || b() == null) ? new b() : new org.greenrobot.eventbus.r.a("EventBus");
        }

        static Object b() {
            try {
                return Looper.getMainLooper();
            } catch (RuntimeException unused) {
                return null;
            }
        }
    }

    public static class b implements g {
        @Override // org.greenrobot.eventbus.g
        public void a(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.g
        public void b(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void b(Level level, String str, Throwable th);
}
