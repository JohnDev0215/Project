package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class a1 extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f907a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f908b;

    public a1(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f908b = new WeakReference<>(context);
    }

    public static boolean a() {
        return f907a;
    }

    public static boolean b() {
        return a() && Build.VERSION.SDK_INT <= 20;
    }

    final Drawable c(int i) {
        return super.getDrawable(i);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Context context = this.f908b.get();
        return context != null ? l0.h().t(context, this, i) : super.getDrawable(i);
    }
}
