package p;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import com.stingray.drivemodedetector.R;

/* JADX INFO: renamed from: p.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2066a extends ContextWrapper {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Configuration f20601f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f20603b;
    public LayoutInflater c;
    public Configuration d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f20604e;

    public final void a() {
        if (this.f20603b == null) {
            this.f20603b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f20603b.setTo(theme);
            }
        }
        this.f20603b.applyStyle(this.f20602a, true);
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    @Override // android.content.ContextWrapper, android.content.Context
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.content.res.Resources getResources() {
        /*
            r3 = this;
            android.content.res.Resources r0 = r3.f20604e
            if (r0 != 0) goto L32
            android.content.res.Configuration r0 = r3.d
            if (r0 == 0) goto L2c
            android.content.res.Configuration r1 = p.C2066a.f20601f
            if (r1 != 0) goto L16
            android.content.res.Configuration r1 = new android.content.res.Configuration
            r1.<init>()
            r2 = 0
            r1.fontScale = r2
            p.C2066a.f20601f = r1
        L16:
            android.content.res.Configuration r1 = p.C2066a.f20601f
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L1f
            goto L2c
        L1f:
            android.content.res.Configuration r0 = r3.d
            android.content.Context r0 = r3.createConfigurationContext(r0)
            android.content.res.Resources r0 = r0.getResources()
            r3.f20604e = r0
            goto L32
        L2c:
            android.content.res.Resources r0 = super.getResources()
            r3.f20604e = r0
        L32:
            android.content.res.Resources r3 = r3.f20604e
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p.C2066a.getResources():android.content.res.Resources");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.c == null) {
            this.c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f20603b;
        if (theme != null) {
            return theme;
        }
        if (this.f20602a == 0) {
            this.f20602a = R.style.Theme_AppCompat_Light;
        }
        a();
        return this.f20603b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i7) {
        if (this.f20602a != i7) {
            this.f20602a = i7;
            a();
        }
    }
}
