package c2;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import n1.i;
import q1.v;

/* JADX INFO: compiled from: DrawableBytesTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements e<Drawable, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r1.d f3578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e<Bitmap, byte[]> f3579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e<b2.c, byte[]> f3580c;

    public c(r1.d dVar, e<Bitmap, byte[]> eVar, e<b2.c, byte[]> eVar2) {
        this.f3578a = dVar;
        this.f3579b = eVar;
        this.f3580c = eVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static v<b2.c> b(v<Drawable> vVar) {
        return vVar;
    }

    @Override // c2.e
    public v<byte[]> a(v<Drawable> vVar, i iVar) {
        Drawable drawable = vVar.get();
        if (drawable instanceof BitmapDrawable) {
            return this.f3579b.a(x1.e.f(((BitmapDrawable) drawable).getBitmap(), this.f3578a), iVar);
        }
        if (drawable instanceof b2.c) {
            return this.f3580c.a(b(vVar), iVar);
        }
        return null;
    }
}
