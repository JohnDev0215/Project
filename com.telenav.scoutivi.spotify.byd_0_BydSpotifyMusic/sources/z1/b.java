package z1;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import k2.j;
import q1.r;
import q1.v;

/* JADX INFO: compiled from: DrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends Drawable> implements v<T>, r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final T f10044e;

    public b(T t6) {
        this.f10044e = (T) j.d(t6);
    }

    public void b() {
        T t6 = this.f10044e;
        if (t6 instanceof BitmapDrawable) {
            ((BitmapDrawable) t6).getBitmap().prepareToDraw();
        } else if (t6 instanceof b2.c) {
            ((b2.c) t6).e().prepareToDraw();
        }
    }

    @Override // q1.v
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final T get() {
        Drawable.ConstantState constantState = this.f10044e.getConstantState();
        return constantState == null ? this.f10044e : (T) constantState.newDrawable();
    }
}
