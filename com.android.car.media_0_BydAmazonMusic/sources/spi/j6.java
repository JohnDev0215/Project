package spi;

import java.util.logging.Level;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: classes2.dex */
public interface j6 {

    /* JADX INFO: compiled from: Logger.java */
    public static class a implements j6 {
        @Override // spi.j6
        public final void a(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // spi.j6
        public final void b(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void b(Level level, String str, Throwable th);
}
