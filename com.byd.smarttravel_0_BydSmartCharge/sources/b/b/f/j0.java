package b.b.f;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VectorEnabledTintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class j0 extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f798a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference<Context> f799b;

    public j0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f799b = new WeakReference<>(context);
    }

    public static boolean a() {
        return f798a;
    }

    public static boolean b() {
        return a() && Build.VERSION.SDK_INT <= 20;
    }

    public final Drawable c(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Context context = this.f799b.get();
        return context != null ? v.h().t(context, this, i) : super.getDrawable(i);
    }
}
