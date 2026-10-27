package b.b.q;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference<Context> f1714b;

    public d0(Context context, Resources resources) {
        super(resources);
        this.f1714b = new WeakReference<>(context);
    }

    @Override // b.b.q.w, android.content.res.Resources
    public Drawable getDrawable(int i2) throws Resources.NotFoundException {
        Drawable drawable = super.getDrawable(i2);
        Context context = this.f1714b.get();
        if (drawable != null && context != null) {
            v.h().x(context, i2, drawable);
        }
        return drawable;
    }
}
