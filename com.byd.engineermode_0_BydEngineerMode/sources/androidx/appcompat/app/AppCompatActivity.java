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
import androidx.core.app.f;
import androidx.fragment.app.FragmentActivity;
import b.a.o.b;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements c, f.a {
    private d s;
    private Resources t;

    private boolean D(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    public void A(androidx.core.app.f fVar) {
    }

    @Deprecated
    public void B() {
    }

    public boolean C() {
        Intent intentF = f();
        if (intentF == null) {
            return false;
        }
        if (!F(intentF)) {
            E(intentF);
            return true;
        }
        androidx.core.app.f fVarD = androidx.core.app.f.d(this);
        y(fVarD);
        A(fVarD);
        fVarD.e();
        try {
            androidx.core.app.a.h(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void E(Intent intent) {
        androidx.core.app.d.e(this, intent);
    }

    public boolean F(Intent intent) {
        return androidx.core.app.d.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        w().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(w().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        a aVarX = x();
        if (getWindow().hasFeature(0)) {
            if (aVarX == null || !aVarX.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a aVarX = x();
        if (keyCode == 82 && aVarX != null && aVarX.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.core.app.f.a
    public Intent f() {
        return androidx.core.app.d.a(this);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) w().i(i);
    }

    @Override // androidx.appcompat.app.c
    public void g(b.a.o.b bVar) {
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return w().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.t == null && j0.b()) {
            this.t = new j0(this, super.getResources());
        }
        Resources resources = this.t;
        return resources == null ? super.getResources() : resources;
    }

    @Override // androidx.appcompat.app.c
    public void i(b.a.o.b bVar) {
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        w().o();
    }

    @Override // androidx.appcompat.app.c
    public b.a.o.b k(b.a aVar) {
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.t != null) {
            this.t.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        w().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        B();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        d dVarW = w();
        dVarW.n();
        dVarW.q(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        w().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (D(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a aVarX = x();
        if (menuItem.getItemId() != 16908332 || aVarX == null || (aVarX.j() & 4) == 0) {
            return false;
        }
        return C();
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
        w().s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        w().t();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        w().u(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        w().v();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        w().w();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        w().E(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        a aVarX = x();
        if (getWindow().hasFeature(0)) {
            if (aVarX == null || !aVarX.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        w().A(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        w().D(i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void v() {
        w().o();
    }

    public d w() {
        if (this.s == null) {
            this.s = d.g(this, this);
        }
        return this.s;
    }

    public a x() {
        return w().m();
    }

    public void y(androidx.core.app.f fVar) {
        fVar.b(this);
    }

    protected void z(int i) {
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        w().B(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        w().C(view, layoutParams);
    }
}
