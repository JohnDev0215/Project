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
public class b0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f452a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<WeakReference<b0>> f453b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Resources f454c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Resources.Theme f455d;

    private b0(Context context) {
        super(context);
        if (!j0.b()) {
            this.f454c = new d0(this, context.getResources());
            this.f455d = null;
            return;
        }
        j0 j0Var = new j0(this, context.getResources());
        this.f454c = j0Var;
        Resources.Theme themeNewTheme = j0Var.newTheme();
        this.f455d = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        if ((context instanceof b0) || (context.getResources() instanceof d0) || (context.getResources() instanceof j0)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || j0.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f452a) {
            ArrayList<WeakReference<b0>> arrayList = f453b;
            if (arrayList == null) {
                f453b = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<b0> weakReference = f453b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f453b.remove(size);
                    }
                }
                for (int size2 = f453b.size() - 1; size2 >= 0; size2--) {
                    WeakReference<b0> weakReference2 = f453b.get(size2);
                    b0 b0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (b0Var != null && b0Var.getBaseContext() == context) {
                        return b0Var;
                    }
                }
            }
            b0 b0Var2 = new b0(context);
            f453b.add(new WeakReference<>(b0Var2));
            return b0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f454c.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f454c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f455d;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.f455d;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
