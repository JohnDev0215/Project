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
    private int f1466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f1467b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f1468c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f1469d;
    private Resources e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.e == null) {
            Configuration configuration = this.f1469d;
            if (configuration == null) {
                this.e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f1469d);
                this.e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.e;
    }

    private void c() {
        boolean z = this.f1467b == null;
        if (z) {
            this.f1467b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f1467b.setTo(theme);
            }
        }
        d(this.f1467b, this.f1466a, z);
    }

    public void a(Configuration configuration) {
        if (this.e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f1469d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f1469d = new Configuration(configuration);
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
        if (this.f1468c == null) {
            this.f1468c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f1468c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1467b;
        if (theme != null) {
            return theme;
        }
        if (this.f1466a == 0) {
            this.f1466a = b.a.i.e;
        }
        c();
        return this.f1467b;
    }

    public int getThemeResId() {
        return this.f1466a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f1466a != i) {
            this.f1466a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f1466a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f1467b = theme;
    }
}
