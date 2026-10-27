package m;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import androidx.appcompat.R$style;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3732a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f3733b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f3734c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f3735d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f3736e;

    public d() {
        super(null);
    }

    public void a(Configuration configuration) {
        if (this.f3736e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f3735d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f3735d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final Resources b() {
        if (this.f3736e == null) {
            Configuration configuration = this.f3735d;
            if (configuration == null) {
                this.f3736e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f3736e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f3735d);
                this.f3736e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f3736e;
    }

    public final void c() {
        boolean z3 = this.f3733b == null;
        if (z3) {
            this.f3733b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f3733b.setTo(theme);
            }
        }
        d(this.f3733b, this.f3732a, z3);
    }

    public void d(Resources.Theme theme, int i3, boolean z3) {
        theme.applyStyle(i3, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return b();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f3734c == null) {
            this.f3734c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f3734c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f3733b;
        if (theme != null) {
            return theme;
        }
        if (this.f3732a == 0) {
            this.f3732a = R$style.Theme_AppCompat_Light;
        }
        c();
        return this.f3733b;
    }

    public int getThemeResId() {
        return this.f3732a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i3) {
        if (this.f3732a != i3) {
            this.f3732a = i3;
            c();
        }
    }

    public d(Context context, int i3) {
        super(context);
        this.f3732a = i3;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f3733b = theme;
    }
}
