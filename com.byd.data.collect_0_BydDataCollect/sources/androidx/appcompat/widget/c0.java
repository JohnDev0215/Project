package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f587a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<WeakReference<c0>> f588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Resources f589c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Resources.Theme f590d;

    private c0(Context context) {
        super(context);
        if (!k0.b()) {
            this.f589c = new e0(this, context.getResources());
            this.f590d = null;
            return;
        }
        k0 k0Var = new k0(this, context.getResources());
        this.f589c = k0Var;
        Resources.Theme themeNewTheme = k0Var.newTheme();
        this.f590d = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        if ((context instanceof c0) || (context.getResources() instanceof e0) || (context.getResources() instanceof k0)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || k0.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f587a) {
            ArrayList<WeakReference<c0>> arrayList = f588b;
            if (arrayList == null) {
                f588b = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<c0> weakReference = f588b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f588b.remove(size);
                    }
                }
                for (int size2 = f588b.size() - 1; size2 >= 0; size2--) {
                    WeakReference<c0> weakReference2 = f588b.get(size2);
                    c0 c0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (c0Var != null && c0Var.getBaseContext() == context) {
                        return c0Var;
                    }
                }
            }
            c0 c0Var2 = new c0(context);
            f588b.add(new WeakReference<>(c0Var2));
            return c0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f589c.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f589c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f590d;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        Resources.Theme theme = this.f590d;
        if (theme == null) {
            super.setTheme(i2);
        } else {
            theme.applyStyle(i2, true);
        }
    }
}
