package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class s0 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f1017a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<WeakReference<s0>> f1018b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Resources f1019c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Resources.Theme f1020d;

    private s0(Context context) {
        super(context);
        if (!a1.b()) {
            this.f1019c = new u0(this, context.getResources());
            this.f1020d = null;
            return;
        }
        a1 a1Var = new a1(this, context.getResources());
        this.f1019c = a1Var;
        Resources.Theme themeNewTheme = a1Var.newTheme();
        this.f1020d = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        if ((context instanceof s0) || (context.getResources() instanceof u0) || (context.getResources() instanceof a1)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || a1.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f1017a) {
            ArrayList<WeakReference<s0>> arrayList = f1018b;
            if (arrayList == null) {
                f1018b = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<s0> weakReference = f1018b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f1018b.remove(size);
                    }
                }
                for (int size2 = f1018b.size() - 1; size2 >= 0; size2--) {
                    WeakReference<s0> weakReference2 = f1018b.get(size2);
                    s0 s0Var = weakReference2 != null ? weakReference2.get() : null;
                    if (s0Var != null && s0Var.getBaseContext() == context) {
                        return s0Var;
                    }
                }
            }
            s0 s0Var2 = new s0(context);
            f1018b.add(new WeakReference<>(s0Var2));
            return s0Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f1019c.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f1019c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1020d;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.f1020d;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
