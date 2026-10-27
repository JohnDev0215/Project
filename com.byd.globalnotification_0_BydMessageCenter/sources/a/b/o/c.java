package a.b.o;

import a.b.i;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* JADX INFO: loaded from: classes.dex */
public class c extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f92a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f93b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f94c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f95d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f96e;

    public c() {
        super(null);
    }

    public c(Context context, int i) {
        super(context);
        this.f92a = i;
    }

    public void a(Configuration configuration) {
        if (this.f96e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f95d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f95d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final void b() {
        if (this.f93b == null) {
            this.f93b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f93b.setTo(theme);
            }
        }
        this.f93b.applyStyle(this.f92a, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.f96e == null) {
            Configuration configuration = this.f95d;
            this.f96e = configuration == null ? super.getResources() : createConfigurationContext(configuration).getResources();
        }
        return this.f96e;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f94c == null) {
            this.f94c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f94c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f93b;
        if (theme != null) {
            return theme;
        }
        if (this.f92a == 0) {
            this.f92a = i.Theme_AppCompat_Light;
        }
        b();
        return this.f93b;
    }

    public int getThemeResId() {
        return this.f92a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f92a != i) {
            this.f92a = i;
            b();
        }
    }
}
