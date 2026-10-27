package defpackage;

import android.util.SparseArray;
import java.util.concurrent.ExecutionException;
import org.chromium.base.TraceEvent;
import org.chromium.ui.resources.a;

/* JADX INFO: compiled from: AsyncPreloadResourceLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public class b9 extends org.chromium.ui.resources.a {
    public final SparseArray<a> c;
    public final b d;

    /* JADX INFO: compiled from: AsyncPreloadResourceLoader.java */
    public class a extends i9<pb2> {
        public final int h;

        public a(int i) {
            this.h = i;
        }

        @Override // defpackage.i9
        public final pb2 b() {
            int i = this.h;
            b9 b9Var = b9.this;
            b9Var.getClass();
            try {
                TraceEvent.q("AsyncPreloadResourceLoader.createResource", null);
                return b9Var.d.a(i);
            } finally {
                TraceEvent.C("AsyncPreloadResourceLoader.createResource");
            }
        }

        @Override // defpackage.i9
        public final void l(pb2 pb2Var) {
            pb2 pb2Var2 = pb2Var;
            b9 b9Var = b9.this;
            SparseArray<a> sparseArray = b9Var.c;
            int i = this.h;
            if (sparseArray.get(i) == null) {
                return;
            }
            b9Var.b(i, pb2Var2);
            b9Var.c.remove(i);
        }
    }

    /* JADX INFO: compiled from: AsyncPreloadResourceLoader.java */
    public interface b {
        vs2 a(int i);
    }

    public b9(int i, a.InterfaceC0158a interfaceC0158a, b bVar) {
        super(i, interfaceC0158a);
        this.c = new SparseArray<>();
        this.d = bVar;
    }

    @Override // org.chromium.ui.resources.a
    public final void a(int i) {
        SparseArray<a> sparseArray = this.c;
        a aVar = sparseArray.get(i);
        if (aVar != null && !aVar.a(false)) {
            try {
                b(i, aVar.g());
                sparseArray.remove(i);
                return;
            } catch (InterruptedException unused) {
                b(i, null);
                return;
            } catch (ExecutionException unused2) {
                b(i, null);
                return;
            }
        }
        try {
            TraceEvent.q("AsyncPreloadResourceLoader.createResource", null);
            vs2 vs2VarA = this.d.a(i);
            TraceEvent.C("AsyncPreloadResourceLoader.createResource");
            b(i, vs2VarA);
            sparseArray.remove(i);
        } catch (Throwable th) {
            TraceEvent.C("AsyncPreloadResourceLoader.createResource");
            throw th;
        }
    }

    @Override // org.chromium.ui.resources.a
    public final void c(int i) {
        SparseArray<a> sparseArray = this.c;
        if (sparseArray.get(i) != null) {
            return;
        }
        a aVar = new a(i);
        aVar.c(i9.f);
        sparseArray.put(i, aVar);
    }
}
