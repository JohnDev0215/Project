package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VectorEnabledTintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class j0 extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f456a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f457b;

    public j0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f457b = new WeakReference<>(context);
    }

    public static boolean a() {
        return f456a;
    }

    public static boolean b() {
        return a() && Build.VERSION.SDK_INT <= 20;
    }

    final Drawable c(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Context context = this.f457b.get();
        return context != null ? v.h().t(context, this, i) : super.getDrawable(i);
    }
}
