package x1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: compiled from: LazyBitmapDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public final class t implements q1.v<BitmapDrawable>, q1.r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Resources f9800e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final q1.v<Bitmap> f9801g;

    private t(Resources resources, q1.v<Bitmap> vVar) {
        this.f9800e = (Resources) k2.j.d(resources);
        this.f9801g = (q1.v) k2.j.d(vVar);
    }

    public static q1.v<BitmapDrawable> f(Resources resources, q1.v<Bitmap> vVar) {
        if (vVar == null) {
            return null;
        }
        return new t(resources, vVar);
    }

    @Override // q1.v
    public void a() {
        this.f9801g.a();
    }

    @Override // q1.r
    public void b() {
        q1.v<Bitmap> vVar = this.f9801g;
        if (vVar instanceof q1.r) {
            ((q1.r) vVar).b();
        }
    }

    @Override // q1.v
    public int c() {
        return this.f9801g.c();
    }

    @Override // q1.v
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public BitmapDrawable get() {
        return new BitmapDrawable(this.f9800e, this.f9801g.get());
    }

    @Override // q1.v
    public Class<BitmapDrawable> e() {
        return BitmapDrawable.class;
    }
}
