package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.b;

/* JADX INFO: compiled from: BitmapDynamicResource.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ve implements cf0 {
    public final int e;
    public Bitmap f;
    public final Rect g = new Rect();
    public final b<Callback<pb2>> h = new b<>();

    public ve(int i) {
        this.e = i;
    }

    @Override // defpackage.cf0
    public final void a(Callback<pb2> callback) {
        this.h.a(callback);
    }

    @Override // defpackage.cf0
    public final void b() {
        Bitmap bitmap;
        b<Callback<pb2>> bVar = this.h;
        if (bVar.isEmpty() || (bitmap = this.f) == null) {
            return;
        }
        ff0 ff0Var = new ff0(bitmap, false, this.g, a8.u(null));
        Iterator<Callback<pb2>> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.f = null;
                return;
            }
            ((Callback) aVar.next()).onResult(ff0Var);
        }
    }

    @Override // defpackage.cf0
    public final void c(Callback<pb2> callback) {
        this.h.c(callback);
    }

    public final void d(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.f = bitmap;
        this.g.set(0, 0, bitmap.getWidth(), this.f.getHeight());
    }
}
