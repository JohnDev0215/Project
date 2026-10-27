package c2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import k2.j;
import n1.i;
import q1.v;
import x1.t;

/* JADX INFO: compiled from: BitmapDrawableTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e<Bitmap, BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f3577a;

    public b(Resources resources) {
        this.f3577a = (Resources) j.d(resources);
    }

    @Override // c2.e
    public v<BitmapDrawable> a(v<Bitmap> vVar, i iVar) {
        return t.f(this.f3577a, vVar);
    }
}
