package b.b.p;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f870b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f871c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f872d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f873e;

    public d() {
        super(null);
    }

    public void a(Configuration configuration) {
        if (this.f873e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f872d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f872d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final Resources b() {
        if (this.f873e == null) {
            Configuration configuration = this.f872d;
            if (configuration == null) {
                this.f873e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f873e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f872d);
                this.f873e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f873e;
    }

    public final void c() {
        boolean z = this.f870b == null;
        if (z) {
            this.f870b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f870b.setTo(theme);
            }
        }
        d(this.f870b, this.f869a, z);
    }

    public void d(Resources.Theme theme, int i2, boolean z) {
        theme.applyStyle(i2, true);
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
        if (this.f871c == null) {
            this.f871c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f871c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f870b;
        if (theme != null) {
            return theme;
        }
        if (this.f869a == 0) {
            this.f869a = b.b.i.f770d;
        }
        c();
        return this.f870b;
    }

    public int getThemeResId() {
        return this.f869a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        if (this.f869a != i2) {
            this.f869a = i2;
            c();
        }
    }

    public d(Context context, int i2) {
        super(context);
        this.f869a = i2;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f870b = theme;
    }
}
