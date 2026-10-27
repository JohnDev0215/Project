package f.b0.i;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: compiled from: PushObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f5257a = new a();

    /* JADX INFO: compiled from: PushObserver.java */
    public static class a implements k {
        @Override // f.b0.i.k
        public boolean a(int i2, List<f.b0.i.a> list) {
            return true;
        }

        @Override // f.b0.i.k
        public boolean b(int i2, List<f.b0.i.a> list, boolean z) {
            return true;
        }

        @Override // f.b0.i.k
        public void c(int i2, ErrorCode errorCode) {
        }

        @Override // f.b0.i.k
        public boolean d(int i2, g.e eVar, int i3, boolean z) throws IOException {
            eVar.o(i3);
            return true;
        }
    }

    boolean a(int i2, List<f.b0.i.a> list);

    boolean b(int i2, List<f.b0.i.a> list, boolean z);

    void c(int i2, ErrorCode errorCode);

    boolean d(int i2, g.e eVar, int i3, boolean z) throws IOException;
}
