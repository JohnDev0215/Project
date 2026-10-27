package o;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f4231a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ArrayList<WeakReference<b0>> f4232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Resources f4233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Resources.Theme f4234d;

    public b0(Context context) {
        super(context);
        if (!j0.b()) {
            this.f4233c = new d0(this, context.getResources());
            this.f4234d = null;
            return;
        }
        j0 j0Var = new j0(this, context.getResources());
        this.f4233c = j0Var;
        Resources.Theme themeNewTheme = j0Var.newTheme();
        this.f4234d = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    public static boolean a(Context context) {
        if ((context instanceof b0) || (context.getResources() instanceof d0) || (context.getResources() instanceof j0)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || j0.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f4231a) {
            ArrayList<WeakReference<b0>> arrayList = f4232b;
            if (arrayList == null) {
                f4232b = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<b0> weakReference = f4232b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f4232b.remove(size);
                    }
                }
                for (int size2 = f4232b.size() - 1; size2 >= 0; size2--) {
                    WeakReference<b0> weakReference2 = f4232b.get(size2);
                    b0 b0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (b0Var != null && b0Var.getBaseContext() == context) {
                        return b0Var;
                    }
                }
            }
            b0 b0Var2 = new b0(context);
            f4232b.add(new WeakReference<>(b0Var2));
            return b0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f4233c.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f4233c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f4234d;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i3) {
        Resources.Theme theme = this.f4234d;
        if (theme == null) {
            super.setTheme(i3);
        } else {
            theme.applyStyle(i3, true);
        }
    }
}
