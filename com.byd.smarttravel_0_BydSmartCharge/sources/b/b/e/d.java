package b.b.e;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import androidx.appcompat.R$style;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f648a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f649b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f650c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f651d;
    public Resources e;

    public d() {
        super(null);
    }

    public void a(Configuration configuration) {
        if (this.e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f651d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f651d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final Resources b() {
        if (this.e == null) {
            Configuration configuration = this.f651d;
            if (configuration == null) {
                this.e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f651d);
                this.e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.e;
    }

    public int c() {
        return this.f648a;
    }

    public final void d() {
        boolean z = this.f649b == null;
        if (z) {
            this.f649b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f649b.setTo(theme);
            }
        }
        e(this.f649b, this.f648a, z);
    }

    public void e(Resources.Theme theme, int i, boolean z) {
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
        if (this.f650c == null) {
            this.f650c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f650c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f649b;
        if (theme != null) {
            return theme;
        }
        if (this.f648a == 0) {
            this.f648a = R$style.Theme_AppCompat_Light;
        }
        d();
        return this.f649b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f648a != i) {
            this.f648a = i;
            d();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f648a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f649b = theme;
    }
}
