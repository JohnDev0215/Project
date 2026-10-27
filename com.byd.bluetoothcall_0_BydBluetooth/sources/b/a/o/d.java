package b.a.o;

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
    private int f2043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f2044b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f2045c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f2046d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Resources f2047e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f2047e == null) {
            Configuration configuration = this.f2046d;
            if (configuration == null) {
                this.f2047e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f2047e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f2046d);
                this.f2047e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f2047e;
    }

    private void c() {
        boolean z = this.f2044b == null;
        if (z) {
            this.f2044b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f2044b.setTo(theme);
            }
        }
        d(this.f2044b, this.f2043a, z);
    }

    public void a(Configuration configuration) {
        if (this.f2047e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f2046d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f2046d = new Configuration(configuration);
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
        if (this.f2045c == null) {
            this.f2045c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f2045c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f2044b;
        if (theme != null) {
            return theme;
        }
        if (this.f2043a == 0) {
            this.f2043a = b.a.i.f2003e;
        }
        c();
        return this.f2044b;
    }

    public int getThemeResId() {
        return this.f2043a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f2043a != i) {
            this.f2043a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f2043a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f2044b = theme;
    }
}
