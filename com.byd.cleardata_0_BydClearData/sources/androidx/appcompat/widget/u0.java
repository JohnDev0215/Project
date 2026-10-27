package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class u0 extends m0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f1027b;

    public u0(Context context, Resources resources) {
        super(resources);
        this.f1027b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.m0, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = this.f1027b.get();
        if (drawable != null && context != null) {
            l0.h().x(context, i, drawable);
        }
        return drawable;
    }
}
