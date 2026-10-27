package p;

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
    public int f6898a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f6899b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f6900c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f6901d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f6902e;

    public d() {
        super(null);
    }

    public void a(Configuration configuration) {
        if (this.f6902e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f6901d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f6901d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final Resources b() {
        if (this.f6902e == null) {
            Configuration configuration = this.f6901d;
            if (configuration == null) {
                this.f6902e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f6902e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.f6901d);
                this.f6902e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f6902e;
    }

    public final void c() {
        boolean z7 = this.f6899b == null;
        if (z7) {
            this.f6899b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f6899b.setTo(theme);
            }
        }
        d(this.f6899b, this.f6898a, z7);
    }

    public void d(Resources.Theme theme, int i8, boolean z7) {
        theme.applyStyle(i8, true);
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
        if (this.f6900c == null) {
            this.f6900c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f6900c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f6899b;
        if (theme != null) {
            return theme;
        }
        if (this.f6898a == 0) {
            this.f6898a = R$style.Theme_AppCompat_Light;
        }
        c();
        return this.f6899b;
    }

    public int getThemeResId() {
        return this.f6898a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i8) {
        if (this.f6898a != i8) {
            this.f6898a = i8;
            c();
        }
    }

    public d(Context context, int i8) {
        super(context);
        this.f6898a = i8;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f6899b = theme;
    }
}
