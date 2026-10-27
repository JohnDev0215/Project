package i;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f6763a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f6764b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f6765c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f6766d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Resources f6767e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f6767e == null) {
            Configuration configuration = this.f6766d;
            if (configuration == null) {
                this.f6767e = super.getResources();
            } else {
                this.f6767e = createConfigurationContext(configuration).getResources();
            }
        }
        return this.f6767e;
    }

    private void d() {
        boolean z6 = this.f6764b == null;
        if (z6) {
            this.f6764b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f6764b.setTo(theme);
            }
        }
        e(this.f6764b, this.f6763a, z6);
    }

    public void a(Configuration configuration) {
        if (this.f6767e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f6766d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f6766d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public int c() {
        return this.f6763a;
    }

    protected void e(Resources.Theme theme, int i7, boolean z6) {
        theme.applyStyle(i7, true);
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
        if (this.f6765c == null) {
            this.f6765c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f6765c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f6764b;
        if (theme != null) {
            return theme;
        }
        if (this.f6763a == 0) {
            this.f6763a = e.i.Theme_AppCompat_Light;
        }
        d();
        return this.f6764b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i7) {
        if (this.f6763a != i7) {
            this.f6763a = i7;
            d();
        }
    }

    public d(Context context, int i7) {
        super(context);
        this.f6763a = i7;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f6764b = theme;
    }
}
