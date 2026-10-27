package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class p0 extends ContextWrapper {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f1169c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ArrayList<WeakReference<p0>> f1170d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f1171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources.Theme f1172b;

    private p0(Context context) {
        super(context);
        if (!v0.b()) {
            this.f1171a = new r0(this, context.getResources());
            this.f1172b = null;
            return;
        }
        v0 v0Var = new v0(this, context.getResources());
        this.f1171a = v0Var;
        Resources.Theme themeNewTheme = v0Var.newTheme();
        this.f1172b = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        return ((context instanceof p0) || (context.getResources() instanceof r0) || (context.getResources() instanceof v0) || !v0.b()) ? false : true;
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f1169c) {
            ArrayList<WeakReference<p0>> arrayList = f1170d;
            if (arrayList == null) {
                f1170d = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<p0> weakReference = f1170d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f1170d.remove(size);
                    }
                }
                for (int size2 = f1170d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<p0> weakReference2 = f1170d.get(size2);
                    p0 p0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (p0Var != null && p0Var.getBaseContext() == context) {
                        return p0Var;
                    }
                }
            }
            p0 p0Var2 = new p0(context);
            f1170d.add(new WeakReference<>(p0Var2));
            return p0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f1171a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f1171a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1172b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i7) {
        Resources.Theme theme = this.f1172b;
        if (theme == null) {
            super.setTheme(i7);
        } else {
            theme.applyStyle(i7, true);
        }
    }
}
