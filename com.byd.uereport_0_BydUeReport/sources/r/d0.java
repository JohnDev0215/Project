package r;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference<Context> f7504b;

    public d0(Context context, Resources resources) {
        super(resources);
        this.f7504b = new WeakReference<>(context);
    }

    @Override // r.w, android.content.res.Resources
    public Drawable getDrawable(int i8) throws Resources.NotFoundException {
        Drawable drawable = super.getDrawable(i8);
        Context context = this.f7504b.get();
        if (drawable != null && context != null) {
            v.h().x(context, i8, drawable);
        }
        return drawable;
    }
}
