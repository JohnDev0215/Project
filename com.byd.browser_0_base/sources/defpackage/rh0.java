package defpackage;

import defpackage.ll;
import defpackage.mm0;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: EncryptedFilePersistedTabDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rh0 extends mm0 {

    /* JADX INFO: compiled from: EncryptedFilePersistedTabDataStorage.java */
    public class a<U extends p02> extends mm0.d<U> {
        public final /* synthetic */ rh0 g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(rh0 rh0Var, int i, String str, ll.b bVar) {
            super(rh0Var, i, str, bVar);
            CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
            this.g = rh0Var;
        }

        @Override // mm0.d, mm0.g
        /* JADX INFO: renamed from: d */
        public final ui2 a() throws Throwable {
            return ((CriticalPersistedTabData.a) this.e).a(this.g.new b(this.a, this.b, null).a());
        }
    }

    /* JADX INFO: compiled from: EncryptedFilePersistedTabDataStorage.java */
    public class b extends mm0.e {
        public b(int i, String str, j02 j02Var) {
            super(i, str, j02Var);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 14, insn: 0x0131: MOVE (r7 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]) (LINE:306), block:B:62:0x0131 */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0129 A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x012b  */
        /* JADX WARN: Type inference failed for: r14v1 */
        /* JADX WARN: Type inference failed for: r14v10, types: [java.io.Closeable, java.io.DataInputStream] */
        /* JADX WARN: Type inference failed for: r14v2 */
        /* JADX WARN: Type inference failed for: r14v3 */
        /* JADX WARN: Type inference failed for: r14v4, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r14v5, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r14v6 */
        /* JADX WARN: Type inference failed for: r14v7 */
        /* JADX WARN: Type inference failed for: r14v8 */
        /* JADX WARN: Type inference failed for: r14v9 */
        /* JADX WARN: Type inference failed for: r15v0 */
        /* JADX WARN: Type inference failed for: r15v1 */
        /* JADX WARN: Type inference failed for: r15v10, types: [byte[]] */
        /* JADX WARN: Type inference failed for: r15v11 */
        /* JADX WARN: Type inference failed for: r15v12 */
        /* JADX WARN: Type inference failed for: r15v13 */
        /* JADX WARN: Type inference failed for: r15v14 */
        /* JADX WARN: Type inference failed for: r15v2 */
        /* JADX WARN: Type inference failed for: r15v3 */
        /* JADX WARN: Type inference failed for: r15v4 */
        /* JADX WARN: Type inference failed for: r15v5, types: [byte[]] */
        /* JADX WARN: Type inference failed for: r15v6 */
        /* JADX WARN: Type inference failed for: r15v7 */
        /* JADX WARN: Type inference failed for: r15v8 */
        @Override // mm0.e, mm0.g
        /* JADX INFO: renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.nio.ByteBuffer a() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 338
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rh0.b.a():java.nio.ByteBuffer");
        }
    }

    /* JADX INFO: compiled from: EncryptedFilePersistedTabDataStorage.java */
    public class c extends mm0.f {
        public c(int i, String str, k02 k02Var, Callback callback) {
            super(i, str, k02Var, callback);
        }

        @Override // mm0.f, mm0.g
        public final /* bridge */ /* synthetic */ Void a() throws Throwable {
            d();
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x011a  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0137  */
        @Override // mm0.f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void d() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 321
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rh0.c.d():void");
        }
    }

    @Override // defpackage.mm0, defpackage.q02
    public final void a(int i, String str, j02 j02Var) {
        h(new b(i, str, j02Var));
    }

    @Override // defpackage.mm0, defpackage.q02
    public final p02 c(int i, String str) {
        CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
        mm0.i(i, str);
        return aVar.a(new b(i, str, null).a());
    }

    @Override // defpackage.mm0, defpackage.q02
    public final void d(int i, String str, ll.b bVar) {
        CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
        h(new a(this, i, str, bVar));
    }

    @Override // defpackage.mm0, defpackage.q02
    public final void f(int i, String str, k02 k02Var) {
        g(i, str, k02Var, mm0.g);
    }

    @Override // defpackage.mm0, defpackage.q02
    public final void g(int i, String str, k02 k02Var, Callback callback) {
        h(new c(i, str, k02Var, callback));
    }
}
