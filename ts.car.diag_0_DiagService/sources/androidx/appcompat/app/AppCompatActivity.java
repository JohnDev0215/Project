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
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.c.b;
import androidx.appcompat.widget.Z;
import androidx.core.app.h;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.r;
import androidx.lifecycle.s;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements b, h.a {
    private AppCompatDelegate o;
    private Resources p;

    private boolean C(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    private void l() {
        r.a(getWindow().getDecorView(), this);
        s.a(getWindow().getDecorView(), this);
        androidx.savedstate.c.a(getWindow().getDecorView(), this);
    }

    @Deprecated
    public void A() {
    }

    public boolean B() {
        Intent intentF = f();
        if (intentF == null) {
            return false;
        }
        if (!E(intentF)) {
            D(intentF);
            return true;
        }
        androidx.core.app.h hVarD = androidx.core.app.h.d(this);
        x(hVarD);
        z(hVarD);
        hVarD.e();
        try {
            androidx.core.app.a.i(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void D(@NonNull Intent intent) {
        androidx.core.app.f.e(this, intent);
    }

    public boolean E(@NonNull Intent intent) {
        return androidx.core.app.f.f(this, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        l();
        v().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(v().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        ActionBar actionBarW = w();
        if (getWindow().hasFeature(0)) {
            if (actionBarW == null || !actionBarW.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        ActionBar actionBarW = w();
        if (keyCode == 82 && actionBarW != null && actionBarW.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.core.app.h.a
    @Nullable
    public Intent f() {
        return androidx.core.app.f.a(this);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(@IdRes int i) {
        return (T) v().i(i);
    }

    @Override // androidx.appcompat.app.b
    @CallSuper
    public void g(@NonNull androidx.appcompat.c.b bVar) {
    }

    @Override // android.app.Activity
    @NonNull
    public MenuInflater getMenuInflater() {
        return v().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.p == null && Z.b()) {
            this.p = new Z(this, super.getResources());
        }
        Resources resources = this.p;
        return resources == null ? super.getResources() : resources;
    }

    @Override // androidx.appcompat.app.b
    @CallSuper
    public void i(@NonNull androidx.appcompat.c.b bVar) {
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        v().o();
    }

    @Override // androidx.appcompat.app.b
    @Nullable
    public androidx.appcompat.c.b j(@NonNull b.a aVar) {
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.p != null) {
            this.p.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        v().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        A();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        AppCompatDelegate appCompatDelegateV = v();
        appCompatDelegateV.n();
        appCompatDelegateV.q(bundle);
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
    public final boolean onMenuItemSelected(int i, @NonNull MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar actionBarW = w();
        if (menuItem.getItemId() != 16908332 || actionBarW == null || (actionBarW.j() & 4) == 0) {
            return false;
        }
        return B();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, @NonNull Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        v().s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        v().t();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
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
        ActionBar actionBarW = w();
        if (getWindow().hasFeature(0)) {
            if (actionBarW == null || !actionBarW.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(@LayoutRes int i) {
        l();
        v().A(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(@StyleRes int i) {
        super.setTheme(i);
        v().D(i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void u() {
        v().o();
    }

    @NonNull
    public AppCompatDelegate v() {
        if (this.o == null) {
            this.o = AppCompatDelegate.g(this, this);
        }
        return this.o;
    }

    @Nullable
    public ActionBar w() {
        return v().m();
    }

    public void x(@NonNull androidx.core.app.h hVar) {
        hVar.b(this);
    }

    protected void y(int i) {
    }

    public void z(@NonNull androidx.core.app.h hVar) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        l();
        v().B(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        l();
        v().C(view, layoutParams);
    }
}
