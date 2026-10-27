package a.a.n;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;

/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f67a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f68b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f69c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f70d;
    private Resources e;

    public d() {
        super(null);
    }

    public d(Context context, int i) {
        super(context);
        this.f67a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f68b = theme;
    }

    private Resources b() {
        Resources resources;
        if (this.e == null) {
            Configuration configuration = this.f70d;
            if (configuration == null) {
                resources = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                resources = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources2 = super.getResources();
                Configuration configuration2 = new Configuration(resources2.getConfiguration());
                configuration2.updateFrom(this.f70d);
                this.e = new Resources(resources2.getAssets(), resources2.getDisplayMetrics(), configuration2);
            }
            this.e = resources;
        }
        return this.e;
    }

    private void c() {
        boolean z = this.f68b == null;
        if (z) {
            this.f68b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f68b.setTo(theme);
            }
        }
        d(this.f68b, this.f67a, z);
    }

    public void a(Configuration configuration) {
        if (this.e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f70d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f70d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    protected void d(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
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
        if (this.f69c == null) {
            this.f69c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f69c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f68b;
        if (theme != null) {
            return theme;
        }
        if (this.f67a == 0) {
            this.f67a = a.a.i.f32d;
        }
        c();
        return this.f68b;
    }

    public int getThemeResId() {
        return this.f67a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f67a != i) {
            this.f67a = i;
            c();
        }
    }
}
