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
    private int f2171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f2172b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f2173c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f2174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Resources f2175e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f2175e == null) {
            Configuration configuration = this.f2174d;
            if (configuration == null) {
                this.f2175e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f2175e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f2174d);
                this.f2175e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f2175e;
    }

    private void c() {
        boolean z = this.f2172b == null;
        if (z) {
            this.f2172b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f2172b.setTo(theme);
            }
        }
        d(this.f2172b, this.f2171a, z);
    }

    public void a(Configuration configuration) {
        if (this.f2175e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f2174d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f2174d = new Configuration(configuration);
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
        if (this.f2173c == null) {
            this.f2173c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f2173c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f2172b;
        if (theme != null) {
            return theme;
        }
        if (this.f2171a == 0) {
            this.f2171a = b.a.i.f2119e;
        }
        c();
        return this.f2172b;
    }

    public int getThemeResId() {
        return this.f2171a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f2171a != i) {
            this.f2171a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f2171a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f2172b = theme;
    }
}
