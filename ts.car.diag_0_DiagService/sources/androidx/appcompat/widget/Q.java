package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class Q extends ContextWrapper {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f298c = new Object();
    private static ArrayList<WeakReference<Q>> d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources.Theme f300b;

    private Q(@NonNull Context context) {
        super(context);
        if (!Z.b()) {
            this.f299a = new T(this, context.getResources());
            this.f300b = null;
            return;
        }
        Z z = new Z(this, context.getResources());
        this.f299a = z;
        Resources.Theme themeNewTheme = z.newTheme();
        this.f300b = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    private static boolean a(@NonNull Context context) {
        if ((context instanceof Q) || (context.getResources() instanceof T) || (context.getResources() instanceof Z)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || Z.b();
    }

    public static Context b(@NonNull Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f298c) {
            if (d == null) {
                d = new ArrayList<>();
            } else {
                for (int size = d.size() - 1; size >= 0; size--) {
                    WeakReference<Q> weakReference = d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        d.remove(size);
                    }
                }
                for (int size2 = d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<Q> weakReference2 = d.get(size2);
                    Q q = weakReference2 != null ? weakReference2.get() : null;
                    if (q != null && q.getBaseContext() == context) {
                        return q;
                    }
                }
            }
            Q q2 = new Q(context);
            d.add(new WeakReference<>(q2));
            return q2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f299a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f299a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f300b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.f300b;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
