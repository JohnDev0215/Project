package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class T extends K {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f322b;

    public T(@NonNull Context context, @NonNull Resources resources) {
        super(resources);
        this.f322b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.K, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = this.f322b.get();
        if (drawable != null && context != null) {
            J.h().x(context, i, drawable);
        }
        return drawable;
    }
}
