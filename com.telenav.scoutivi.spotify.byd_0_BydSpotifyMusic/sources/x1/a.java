package x1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: compiled from: BitmapDrawableDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a<DataType> implements n1.k<DataType, BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n1.k<DataType, Bitmap> f9737a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f9738b;

    public a(Resources resources, n1.k<DataType, Bitmap> kVar) {
        this.f9738b = (Resources) k2.j.d(resources);
        this.f9737a = (n1.k) k2.j.d(kVar);
    }

    @Override // n1.k
    public boolean a(DataType datatype, n1.i iVar) {
        return this.f9737a.a(datatype, iVar);
    }

    @Override // n1.k
    public q1.v<BitmapDrawable> b(DataType datatype, int i7, int i8, n1.i iVar) {
        return t.f(this.f9738b, this.f9737a.b(datatype, i7, i8, iVar));
    }
}
