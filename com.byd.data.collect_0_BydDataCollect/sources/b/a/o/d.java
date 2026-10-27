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
    private int f2179a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f2180b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f2181c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f2182d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Resources f2183e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f2183e == null) {
            Configuration configuration = this.f2182d;
            if (configuration == null) {
                this.f2183e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f2183e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f2182d);
                this.f2183e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f2183e;
    }

    private void c() {
        boolean z = this.f2180b == null;
        if (z) {
            this.f2180b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f2180b.setTo(theme);
            }
        }
        d(this.f2180b, this.f2179a, z);
    }

    public void a(Configuration configuration) {
        if (this.f2183e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f2182d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f2182d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    protected void d(Resources.Theme theme, int i2, boolean z) {
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
        if (this.f2181c == null) {
            this.f2181c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f2181c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f2180b;
        if (theme != null) {
            return theme;
        }
        if (this.f2179a == 0) {
            this.f2179a = b.a.i.Theme_AppCompat_Light;
        }
        c();
        return this.f2180b;
    }

    public int getThemeResId() {
        return this.f2179a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        if (this.f2179a != i2) {
            this.f2179a = i2;
            c();
        }
    }

    public d(Context context, int i2) {
        super(context);
        this.f2179a = i2;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f2180b = theme;
    }
}
