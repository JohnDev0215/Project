package e.d0.i;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: compiled from: PushObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f3519a = new a();

    /* JADX INFO: compiled from: PushObserver.java */
    public class a implements k {
        @Override // e.d0.i.k
        public boolean a(int i2, List<e.d0.i.a> list) {
            return true;
        }

        @Override // e.d0.i.k
        public boolean b(int i2, List<e.d0.i.a> list, boolean z) {
            return true;
        }

        @Override // e.d0.i.k
        public void c(int i2, ErrorCode errorCode) {
        }

        @Override // e.d0.i.k
        public boolean d(int i2, f.e eVar, int i3, boolean z) throws IOException {
            eVar.skip(i3);
            return true;
        }
    }

    boolean a(int i2, List<e.d0.i.a> list);

    boolean b(int i2, List<e.d0.i.a> list, boolean z);

    void c(int i2, ErrorCode errorCode);

    boolean d(int i2, f.e eVar, int i3, boolean z) throws IOException;
}
