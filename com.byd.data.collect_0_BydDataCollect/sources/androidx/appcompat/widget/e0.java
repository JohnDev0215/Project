package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
class e0 extends x {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f607b;

    public e0(Context context, Resources resources) {
        super(resources);
        this.f607b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.x, android.content.res.Resources
    public Drawable getDrawable(int i2) {
        Drawable drawable = super.getDrawable(i2);
        Context context = this.f607b.get();
        if (drawable != null && context != null) {
            w.h().x(context, i2, drawable);
        }
        return drawable;
    }
}
