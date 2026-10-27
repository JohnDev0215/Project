package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VectorEnabledTintResources.java */
/* JADX INFO: loaded from: classes.dex */
public class v0 extends Resources {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f1236b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<Context> f1237a;

    public v0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f1237a = new WeakReference<>(context);
    }

    public static boolean a() {
        return f1236b;
    }

    public static boolean b() {
        a();
        return false;
    }

    final Drawable c(int i7) {
        return super.getDrawable(i7);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i7) {
        Context context = this.f1237a.get();
        return context != null ? i0.g().s(context, this, i7) : super.getDrawable(i7);
    }
}
