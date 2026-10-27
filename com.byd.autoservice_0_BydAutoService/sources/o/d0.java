package o;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference<Context> f4245b;

    public d0(Context context, Resources resources) {
        super(resources);
        this.f4245b = new WeakReference<>(context);
    }

    @Override // o.w, android.content.res.Resources
    public Drawable getDrawable(int i3) throws Resources.NotFoundException {
        Drawable drawable = super.getDrawable(i3);
        Context context = this.f4245b.get();
        if (drawable != null && context != null) {
            v.h().x(context, i3, drawable);
        }
        return drawable;
    }
}
