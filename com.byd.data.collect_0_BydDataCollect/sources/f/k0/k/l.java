package f.k0.k;

import java.util.List;
import okio.BufferedSource;

/* JADX INFO: compiled from: PushObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f5419a = new a();

    /* JADX INFO: compiled from: PushObserver.java */
    class a implements l {
        a() {
        }

        @Override // f.k0.k.l
        public boolean a(int i2, List<c> list) {
            return true;
        }

        @Override // f.k0.k.l
        public boolean b(int i2, List<c> list, boolean z) {
            return true;
        }

        @Override // f.k0.k.l
        public void c(int i2, b bVar) {
        }

        @Override // f.k0.k.l
        public boolean d(int i2, BufferedSource bufferedSource, int i3, boolean z) {
            bufferedSource.s(i3);
            return true;
        }
    }

    boolean a(int i2, List<c> list);

    boolean b(int i2, List<c> list, boolean z);

    void c(int i2, b bVar);

    boolean d(int i2, BufferedSource bufferedSource, int i3, boolean z);
}
