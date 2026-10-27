package z1;

import android.graphics.drawable.Drawable;
import q1.v;

/* JADX INFO: compiled from: NonOwnedDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends b<Drawable> {
    private d(Drawable drawable) {
        super(drawable);
    }

    static v<Drawable> f(Drawable drawable) {
        if (drawable != null) {
            return new d(drawable);
        }
        return null;
    }

    @Override // q1.v
    public void a() {
    }

    @Override // q1.v
    public int c() {
        return Math.max(1, this.f10044e.getIntrinsicWidth() * this.f10044e.getIntrinsicHeight() * 4);
    }

    @Override // q1.v
    public Class<Drawable> e() {
        return this.f10044e.getClass();
    }
}
