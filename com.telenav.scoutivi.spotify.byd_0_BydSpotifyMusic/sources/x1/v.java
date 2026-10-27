package x1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/* JADX INFO: compiled from: ResourceBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class v implements n1.k<Uri, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final z1.e f9808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r1.d f9809b;

    public v(z1.e eVar, r1.d dVar) {
        this.f9808a = eVar;
        this.f9809b = dVar;
    }

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q1.v<Bitmap> b(Uri uri, int i7, int i8, n1.i iVar) {
        q1.v<Drawable> vVarB = this.f9808a.b(uri, i7, i8, iVar);
        if (vVarB == null) {
            return null;
        }
        return n.a(this.f9809b, vVarB.get(), i7, i8);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri, n1.i iVar) {
        return "android.resource".equals(uri.getScheme());
    }
}
