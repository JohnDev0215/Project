package androidx.appcompat.c;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import androidx.annotation.StyleRes;
import androidx.appcompat.R$style;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f167a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f168b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f169c;
    private Configuration d;
    private Resources e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.e == null) {
            Configuration configuration = this.d;
            if (configuration == null) {
                this.e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.d);
                this.e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.e;
    }

    private void c() {
        boolean z = this.f168b == null;
        if (z) {
            this.f168b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f168b.setTo(theme);
            }
        }
        d(this.f168b, this.f167a, z);
    }

    public void a(Configuration configuration) {
        if (this.e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.d = new Configuration(configuration);
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
        if (this.f169c == null) {
            this.f169c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f169c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f168b;
        if (theme != null) {
            return theme;
        }
        if (this.f167a == 0) {
            this.f167a = R$style.Theme_AppCompat_Light;
        }
        c();
        return this.f168b;
    }

    public int getThemeResId() {
        return this.f167a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f167a != i) {
            this.f167a = i;
            c();
        }
    }

    public d(Context context, @StyleRes int i) {
        super(context);
        this.f167a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f168b = theme;
    }
}
