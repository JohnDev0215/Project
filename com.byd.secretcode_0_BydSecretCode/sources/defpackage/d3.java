package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d3 extends ContextWrapper {
    public static final Object a = new Object();
    public static ArrayList<WeakReference<d3>> b;
    public final Resources c;
    public final Resources.Theme d;

    public d3(Context context) {
        super(context);
        if (!l3.b()) {
            this.c = new f3(this, context.getResources());
            this.d = null;
            return;
        }
        l3 l3Var = new l3(this, context.getResources());
        this.c = l3Var;
        Resources.Theme themeNewTheme = l3Var.newTheme();
        this.d = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    public static boolean a(Context context) {
        if ((context instanceof d3) || (context.getResources() instanceof f3) || (context.getResources() instanceof l3)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || l3.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (a) {
            ArrayList<WeakReference<d3>> arrayList = b;
            if (arrayList == null) {
                b = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<d3> weakReference = b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        b.remove(size);
                    }
                }
                for (int size2 = b.size() - 1; size2 >= 0; size2--) {
                    WeakReference<d3> weakReference2 = b.get(size2);
                    d3 d3Var = weakReference2 != null ? weakReference2.get() : null;
                    if (d3Var != null && d3Var.getBaseContext() == context) {
                        return d3Var;
                    }
                }
            }
            d3 d3Var2 = new d3(context);
            b.add(new WeakReference<>(d3Var2));
            return d3Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.c.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.d;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.d;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
