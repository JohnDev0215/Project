package x1;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.File;

/* JADX INFO: compiled from: BitmapDrawableEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements n1.l<BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r1.d f9747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n1.l<Bitmap> f9748b;

    public b(r1.d dVar, n1.l<Bitmap> lVar) {
        this.f9747a = dVar;
        this.f9748b = lVar;
    }

    @Override // n1.l
    public n1.c a(n1.i iVar) {
        return this.f9748b.a(iVar);
    }

    @Override // n1.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean b(q1.v<BitmapDrawable> vVar, File file, n1.i iVar) {
        return this.f9748b.b((Bitmap) new e(vVar.get().getBitmap(), this.f9747a), file, iVar);
    }
}
