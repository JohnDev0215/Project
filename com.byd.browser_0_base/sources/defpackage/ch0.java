package defpackage;

import defpackage.ll;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: EmptyByteBufferPersistedTabDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ch0 implements q02 {
    @Override // defpackage.q02
    public final void a(int i, String str, j02 j02Var) {
        PostTask.d(rh3.a, new th0(j02Var, 1));
    }

    @Override // defpackage.q02
    public final p02 c(int i, String str) {
        return CriticalPersistedTabData.A.a(ByteBuffer.allocateDirect(0));
    }

    @Override // defpackage.q02
    public final void d(int i, String str, ll.b bVar) {
        bVar.onResult(CriticalPersistedTabData.A.a(ByteBuffer.allocateDirect(0)));
    }

    @Override // defpackage.q02
    public final void b(int i, String str) {
    }

    @Override // defpackage.q02
    public final void e(String str, ArrayList arrayList) {
    }

    @Override // defpackage.q02
    public final void f(int i, String str, k02 k02Var) {
    }

    @Override // defpackage.q02
    public final void g(int i, String str, k02 k02Var, Callback callback) {
    }
}
