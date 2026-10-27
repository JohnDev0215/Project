package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VectorEnabledTintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class k0 extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f666a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f667b;

    public k0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f667b = new WeakReference<>(context);
    }

    public static boolean a() {
        return f666a;
    }

    public static boolean b() {
        return a() && Build.VERSION.SDK_INT <= 20;
    }

    final Drawable c(int i2) {
        return super.getDrawable(i2);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i2) {
        Context context = this.f667b.get();
        return context != null ? w.h().t(context, this, i2) : super.getDrawable(i2);
    }
}
