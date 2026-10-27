package defpackage;

import defpackage.ll;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: MockPersistedTabDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ai1 implements q02 {
    public final HashMap a = new HashMap();

    public static String h(int i) {
        return String.format(Locale.US, "%d", Integer.valueOf(i));
    }

    @Override // defpackage.q02
    public final void a(int i, String str, j02 j02Var) {
        PostTask.d(rh3.a, new gu(this, i, 1, j02Var));
    }

    @Override // defpackage.q02
    public final void b(int i, String str) {
        this.a.remove(h(i));
    }

    @Override // defpackage.q02
    public final p02 c(int i, String str) {
        return null;
    }

    @Override // defpackage.q02
    public final void f(int i, String str, k02 k02Var) {
        k02Var.m();
        this.a.put(h(i), (ByteBuffer) k02Var.get());
    }

    @Override // defpackage.q02
    public final void g(int i, String str, k02 k02Var, Callback callback) {
        f(i, str, k02Var);
        ((x10) callback).onResult(0);
    }

    @Override // defpackage.q02
    public final void e(String str, ArrayList arrayList) {
    }

    @Override // defpackage.q02
    public final void d(int i, String str, ll.b bVar) {
    }
}
