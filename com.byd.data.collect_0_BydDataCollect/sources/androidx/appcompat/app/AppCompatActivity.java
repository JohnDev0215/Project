package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.k0;
import androidx.core.app.g;
import androidx.fragment.app.FragmentActivity;
import b.a.o.b;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements c, g.a {
    private d mDelegate;
    private Resources mResources;

    private boolean I(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void A() {
        B().o();
    }

    public d B() {
        if (this.mDelegate == null) {
            this.mDelegate = d.g(this, this);
        }
        return this.mDelegate;
    }

    public a C() {
        return B().m();
    }

    public void D(androidx.core.app.g gVar) {
        gVar.c(this);
    }

    protected void E(int i2) {
    }

    public void F(androidx.core.app.g gVar) {
    }

    @Deprecated
    public void G() {
    }

    public boolean H() {
        Intent intentH = h();
        if (intentH == null) {
            return false;
        }
        if (!L(intentH)) {
            K(intentH);
            return true;
        }
        androidx.core.app.g gVarE = androidx.core.app.g.e(this);
        D(gVarE);
        F(gVarE);
        gVarE.f();
        try {
            androidx.core.app.a.h(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void J(Toolbar toolbar) {
        B().D(toolbar);
    }

    public void K(Intent intent) {
        androidx.core.app.e.e(this, intent);
    }

    public boolean L(Intent intent) {
        return androidx.core.app.e.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        B().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(B().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        a aVarC = C();
        if (getWindow().hasFeature(0)) {
            if (aVarC == null || !aVarC.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a aVarC = C();
        if (keyCode == 82 && aVarC != null && aVarC.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i2) {
        return (T) B().i(i2);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return B().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.mResources == null && k0.b()) {
            this.mResources = new k0(this, super.getResources());
        }
        Resources resources = this.mResources;
        return resources == null ? super.getResources() : resources;
    }

    @Override // androidx.core.app.g.a
    public Intent h() {
        return androidx.core.app.e.a(this);
    }

    @Override // androidx.appcompat.app.c
    public void i(b.a.o.b bVar) {
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        B().o();
    }

    @Override // androidx.appcompat.app.c
    public void n(b.a.o.b bVar) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        B().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        G();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        d dVarB = B();
        dVarB.n();
        dVarB.q(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        B().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (I(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        a aVarC = C();
        if (menuItem.getItemId() != 16908332 || aVarC == null || (aVarC.j() & 4) == 0) {
            return false;
        }
        return H();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        return super.onMenuOpened(i2, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        B().s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        B().t();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        B().u(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        B().v();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        B().w();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        B().F(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        a aVarC = C();
        if (getWindow().hasFeature(0)) {
            if (aVarC == null || !aVarC.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.appcompat.app.c
    public b.a.o.b p(b.a aVar) {
        return null;
    }

    @Override // android.app.Activity
    public void setContentView(int i2) {
        B().A(i2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        super.setTheme(i2);
        B().E(i2);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        B().B(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        B().C(view, layoutParams);
    }
}
