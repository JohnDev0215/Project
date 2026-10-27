package com.byd.theme;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.auto_photo.PhotoApp;
import com.byd.auto_photo.PhotoBaseApp;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;
import com.byd.ui.BaseActivity;
import com.byd.util.GearboxUtil;
import com.byd.util.e;
import com.byd.util.w;
import com.byd.util.x;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ThemeActivity extends BaseActivity {
    protected View f;
    protected PhotoBaseApp g;
    private String h;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    protected int d = -1;
    protected int e = -1;
    private int i = -1;
    private final Point j = new Point();
    protected final Point k = new Point();

    private void C(int i) {
        if (this.d == i) {
            return;
        }
        this.d = i;
        if (i != 0) {
            setTheme(i);
        }
        N();
    }

    private void D() {
        if (this.n) {
            if (!this.m) {
                w.c("no check gear p this platform");
                return;
            }
            if (!GearboxUtil.k()) {
                GearboxUtil.i(this.g).l(this);
            }
            GearboxUtil.i(this.g).s();
            if (checkSelfPermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) != 0) {
                GearboxUtil.i(this.g).n(3);
            }
        }
    }

    private void I() {
        getWindowManager().getDefaultDisplay().getRealSize(this.k);
    }

    private void M() {
        if (this.o == 0 || this.p == 0) {
            if (x.l(this)) {
                this.p = -1;
                this.o = -1;
                w.c("initBarHeight-->softInput show");
            } else {
                I();
                Window window = getWindow();
                Rect rect = new Rect();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                w.c("real size : " + this.k + " visible rect : " + rect);
                this.p = this.k.y - rect.bottom;
                this.o = rect.top;
            }
            if (this.p <= 0) {
                Resources resources = getResources();
                this.p = resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
            }
            if (this.o <= 0) {
                Resources resources2 = getResources();
                this.o = resources2.getDimensionPixelSize(resources2.getIdentifier("status_bar_height", "dimen", "android"));
            }
        }
    }

    private boolean O(int[] iArr) {
        if (iArr.length == 0) {
            w.c("grantResult length is 0");
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    protected int E() {
        return 0;
    }

    protected int F() {
        return 0;
    }

    protected Drawable G() {
        return new ColorDrawable(e.l(this, R.attr.bg_color));
    }

    protected int H() {
        if (this.p == 0) {
            M();
        }
        w.c("navigation_bar_height : " + this.p);
        return this.p;
    }

    protected int J() {
        if (this.o == 0) {
            M();
        }
        w.c("status_bar_height : " + this.o);
        return this.o;
    }

    protected int K(boolean z) {
        if (a.f1178a.intValue() >= 35) {
            return z ? R.style.AppThemeNight : R.style.AppThemeLight;
        }
        return 0;
    }

    protected boolean L(Context context, String[] strArr) {
        if (Build.VERSION.SDK_INT < 23 || strArr.length == 0) {
            return true;
        }
        for (String str : strArr) {
            if (context.checkSelfPermission(str) != 0) {
                w.c("photo app permission not grant : " + str);
                return false;
            }
        }
        return true;
    }

    protected abstract void N();

    protected final boolean P(Configuration configuration) {
        return (this.h == null || configuration.locale.toLanguageTag().equals(this.h)) ? false : true;
    }

    protected final boolean Q(Configuration configuration) {
        int i = this.i;
        if (i != -1) {
            if (i == configuration.orientation) {
                Point point = this.j;
                if (point.x != configuration.screenWidthDp || point.y != configuration.screenHeightDp) {
                }
            }
            return true;
        }
        return false;
    }

    protected void R() {
    }

    protected void S() {
        Configuration configuration = getResources().getConfiguration();
        this.e = configuration.uiMode;
        w.c("uiMode:" + this.e);
        this.h = configuration.locale.toLanguageTag();
        this.i = configuration.orientation;
        this.j.set(configuration.screenWidthDp, configuration.screenHeightDp);
        this.l = configuration.getLayoutDirection();
        C(K((this.e & 48) == 32));
        e.c(this);
        D();
    }

    protected void T(Configuration configuration) {
        U(configuration);
    }

    protected abstract void U(Configuration configuration);

    protected void V(boolean z) {
        w.c("==onUIModeChanged==" + z);
        int iK = K(z);
        w.c("currentTheme:" + this.d + " newTheme:" + iK);
        C(iK);
        X(z);
    }

    protected String[] W() {
        return null;
    }

    protected void X(boolean z) {
        Intent intent = new Intent("theme_changed");
        intent.putExtra("uiModeNight", z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    protected void Y() {
        this.f.setPadding(E(), J(), F(), H());
    }

    protected void Z(int i) {
        a0(i, false);
    }

    protected void a0(int i, boolean z) {
        ((PhotoApp) getApplication()).g(this, i, z);
    }

    protected void b0(boolean z, boolean z2) {
        c0(z, z2, true);
    }

    protected void c0(boolean z, boolean z2, boolean z3) {
        View viewFindViewById;
        int iK = K(z);
        setTheme(iK);
        if (this.d != -1) {
            this.d = iK;
        }
        if (z3 && (viewFindViewById = findViewById(android.R.id.content)) != null) {
            viewFindViewById.setBackground(e.p(this, R.attr.bg_content));
        }
        if (z2) {
            X(z);
        }
    }

    protected final boolean d0(Configuration configuration) {
        int i = this.e;
        return (i == -1 || i == configuration.uiMode) ? false : true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        w.c("ThemeActivity onConfigurationChanged :" + this.e + " " + configuration.uiMode);
        super.onConfigurationChanged(configuration);
        if (d0(configuration)) {
            V((configuration.uiMode & 48) == 32);
            this.e = configuration.uiMode;
        }
        if (P(configuration)) {
            this.h = configuration.locale.toLanguageTag();
            if (this.l != configuration.getLayoutDirection()) {
                w.c("LayoutDirection change!!");
                this.l = configuration.getLayoutDirection();
                U(configuration);
            } else {
                T(configuration);
            }
        }
        if (Q(configuration)) {
            w.c("currentOrientation : " + this.i + " newOrientation : " + configuration.orientation + " currentScreenSize : " + this.j + " newScreenSize :" + configuration.screenWidthDp + "x" + configuration.screenHeightDp);
            this.i = configuration.orientation;
            this.j.set(configuration.screenWidthDp, configuration.screenHeightDp);
            I();
            U(configuration);
        }
    }

    @Override // com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        requestWindowFeature(1);
        PhotoBaseApp photoBaseApp = (PhotoBaseApp) getApplication();
        this.g = photoBaseApp;
        photoBaseApp.a(this);
        Drawable drawableG = G();
        if (drawableG != null) {
            drawableG.setDither(true);
            getWindow().setBackgroundDrawable(drawableG);
        }
        super.onCreate(bundle);
        this.m = getResources().getBoolean(R.bool.oversea_check_p_gear);
        this.n = e.G(this.g);
        this.e = getResources().getConfiguration().uiMode;
        String[] strArrW = W();
        if (strArrW == null || L(this, strArrW)) {
            S();
        } else {
            requestPermissions(strArrW, 100);
        }
    }

    @Override // com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.g.f(this);
        this.e = -1;
        this.h = null;
        this.i = -1;
        super.onDestroy();
        if (this.n && this.g.d() && GearboxUtil.k()) {
            GearboxUtil.i(this.g).o();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (strArr == null || strArr.length == 0 || iArr == null || iArr.length == 0) {
            w.c("photo app -> onRequestPermissionsResult : no grant results");
            return;
        }
        w.c("photo app -> onRequestPermissionsResult : " + i + " " + strArr.length + " " + iArr.length);
        if (i == 100) {
            if (O(iArr)) {
                S();
                return;
            }
            R();
            for (String str : strArr) {
                if (checkSelfPermission(str) != 0) {
                    w.c("photo app -> permission not grant : " + str);
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.n && this.m && GearboxUtil.k()) {
            GearboxUtil.i(this.g).l(this);
        }
    }

    @Override // com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.n && this.m && GearboxUtil.k()) {
            GearboxUtil.i(this.g).m(this);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        View viewFindViewById;
        super.setContentView(i);
        M();
        if (a.f1178a.intValue() < 35 || (viewFindViewById = findViewById(android.R.id.content)) == null) {
            return;
        }
        this.f = viewFindViewById;
        Drawable drawableP = e.p(this, R.attr.bg_content);
        if (drawableP != null) {
            drawableP.setDither(true);
            viewFindViewById.setBackgroundDrawable(drawableP);
        }
        Y();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        getApplication().setTheme(i);
    }
}
