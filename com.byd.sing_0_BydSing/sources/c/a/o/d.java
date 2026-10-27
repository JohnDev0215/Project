package c.a.o;

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
    private int f2033a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f2034b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f2035c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f2036d;
    private Resources e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.e == null) {
            Configuration configuration = this.f2036d;
            if (configuration == null) {
                this.e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f2036d);
                this.e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.e;
    }

    private void c() {
        boolean z = this.f2034b == null;
        if (z) {
            this.f2034b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f2034b.setTo(theme);
            }
        }
        d(this.f2034b, this.f2033a, z);
    }

    public void a(Configuration configuration) {
        if (this.e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f2036d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f2036d = new Configuration(configuration);
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
        if (this.f2035c == null) {
            this.f2035c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f2035c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f2034b;
        if (theme != null) {
            return theme;
        }
        if (this.f2033a == 0) {
            this.f2033a = c.a.i.e;
        }
        c();
        return this.f2034b;
    }

    public int getThemeResId() {
        return this.f2033a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f2033a != i) {
            this.f2033a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f2033a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f2034b = theme;
    }
}
