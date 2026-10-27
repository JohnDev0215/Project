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
    private int f1661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f1662b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f1663c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f1664d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Resources f1665e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f1665e == null) {
            Configuration configuration = this.f1664d;
            if (configuration == null) {
                this.f1665e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f1665e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f1664d);
                this.f1665e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f1665e;
    }

    private void c() {
        boolean z = this.f1662b == null;
        if (z) {
            this.f1662b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f1662b.setTo(theme);
            }
        }
        d(this.f1662b, this.f1661a, z);
    }

    public void a(Configuration configuration) {
        if (this.f1665e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f1664d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f1664d = new Configuration(configuration);
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
        if (this.f1663c == null) {
            this.f1663c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f1663c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1662b;
        if (theme != null) {
            return theme;
        }
        if (this.f1661a == 0) {
            this.f1661a = b.a.i.f1621e;
        }
        c();
        return this.f1662b;
    }

    public int getThemeResId() {
        return this.f1661a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f1661a != i) {
            this.f1661a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f1661a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f1662b = theme;
    }
}
