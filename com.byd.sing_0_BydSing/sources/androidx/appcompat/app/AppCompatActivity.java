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
import androidx.appcompat.widget.j0;
import androidx.core.app.k;
import androidx.fragment.app.FragmentActivity;
import c.a.o.b;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements c, k.a {
    private d s;
    private Resources t;

    private boolean C(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Deprecated
    public void A() {
    }

    public boolean B() {
        Intent intentH = h();
        if (intentH == null) {
            return false;
        }
        if (!E(intentH)) {
            D(intentH);
            return true;
        }
        k kVarD = k.d(this);
        x(kVarD);
        z(kVarD);
        kVarD.e();
        try {
            androidx.core.app.a.i(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void D(Intent intent) {
        androidx.core.app.d.e(this, intent);
    }

    public boolean E(Intent intent) {
        return androidx.core.app.d.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(v().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        a aVarW = w();
        if (getWindow().hasFeature(0)) {
            if (aVarW == null || !aVarW.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a aVarW = w();
        if (keyCode == 82 && aVarW != null && aVarW.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.appcompat.app.c
    public void f(c.a.o.b bVar) {
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) v().i(i);
    }

    @Override // androidx.appcompat.app.c
    public void g(c.a.o.b bVar) {
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return v().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.t == null && j0.b()) {
            this.t = new j0(this, super.getResources());
        }
        Resources resources = this.t;
        return resources == null ? super.getResources() : resources;
    }

    @Override // androidx.core.app.k.a
    public Intent h() {
        return androidx.core.app.d.a(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        v().o();
    }

    @Override // androidx.appcompat.app.c
    public c.a.o.b j(b.a aVar) {
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.t != null) {
            this.t.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        v().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        A();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        d dVarV = v();
        dVarV.n();
        dVarV.q(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        v().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (C(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a aVarW = w();
        if (menuItem.getItemId() != 16908332 || aVarW == null || (aVarW.j() & 4) == 0) {
            return false;
        }
        return B();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        v().s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        v().t();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        v().u(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        v().v();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        v().w();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        v().E(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        a aVarW = w();
        if (getWindow().hasFeature(0)) {
            if (aVarW == null || !aVarW.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        v().A(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        v().D(i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void u() {
        v().o();
    }

    public d v() {
        if (this.s == null) {
            this.s = d.g(this, this);
        }
        return this.s;
    }

    public a w() {
        return v().m();
    }

    public void x(k kVar) {
        kVar.b(this);
    }

    protected void y(int i) {
    }

    public void z(k kVar) {
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        v().B(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v().C(view, layoutParams);
    }
}
