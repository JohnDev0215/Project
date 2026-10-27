package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
class d0 extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f582b;

    public d0(Context context, Resources resources) {
        super(resources);
        this.f582b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.w, android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Drawable drawable = super.getDrawable(i);
        Context context = this.f582b.get();
        if (drawable != null && context != null) {
            v.h().x(context, i, drawable);
        }
        return drawable;
    }
}
