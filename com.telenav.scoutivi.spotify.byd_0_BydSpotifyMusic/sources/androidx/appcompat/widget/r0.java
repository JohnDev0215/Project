package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
class r0 extends j0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f1181b;

    public r0(Context context, Resources resources) {
        super(resources);
        this.f1181b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.j0, android.content.res.Resources
    public Drawable getDrawable(int i7) {
        Drawable drawable = super.getDrawable(i7);
        Context context = this.f1181b.get();
        if (drawable != null && context != null) {
            i0.g().w(context, i7, drawable);
        }
        return drawable;
    }
}
