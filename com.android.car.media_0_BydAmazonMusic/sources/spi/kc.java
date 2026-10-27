package spi;

/* JADX INFO: compiled from: UxrPivotFilter.java */
/* JADX INFO: loaded from: classes.dex */
public interface kc {
    public static final a a = new a();

    /* JADX INFO: compiled from: UxrPivotFilter.java */
    public class a implements kc {
        public int b;

        @Override // spi.kc
        public final void a(int i, int i2) {
            this.b = i;
        }

        @Override // spi.kc
        public final int b() {
            return this.b;
        }

        @Override // spi.kc
        public final void c() {
        }

        @Override // spi.kc
        public final int d(int i) {
            return i;
        }

        @Override // spi.kc
        public final void e(int i) {
        }

        @Override // spi.kc
        public final int f(int i) {
            return i;
        }
    }

    void a(int i, int i2);

    int b();

    void c();

    int d(int i);

    void e(int i);

    int f(int i);
}
