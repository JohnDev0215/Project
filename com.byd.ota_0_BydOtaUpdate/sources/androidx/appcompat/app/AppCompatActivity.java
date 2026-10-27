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
import androidx.fragment.app.FragmentActivity;
import b.b.k.c;
import b.b.k.d;
import b.b.p.b;
import b.b.q.j0;
import b.f.e.a;
import b.f.e.j;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements c, j.a {
    public d P3;
    public Resources Q3;

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
        j jVarD = j.d(this);
        x(jVarD);
        z(jVarD);
        jVarD.e();
        try {
            a.g(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public final boolean C(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    public void D(Intent intent) {
        b.f.e.d.e(this, intent);
    }

    public boolean E(Intent intent) {
        return b.f.e.d.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(v().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        b.b.k.a aVarW = w();
        if (getWindow().hasFeature(0)) {
            if (aVarW == null || !aVarW.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        b.b.k.a aVarW = w();
        if (keyCode == 82 && aVarW != null && aVarW.q(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // b.f.e.j.a
    public Intent f() {
        return b.f.e.d.a(this);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i2) {
        return (T) v().i(i2);
    }

    @Override // b.b.k.c
    public void g(b bVar) {
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return v().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.Q3 == null && j0.b()) {
            this.Q3 = new j0(this, super.getResources());
        }
        Resources resources = this.Q3;
        return resources == null ? super.getResources() : resources;
    }

    @Override // b.b.k.c
    public void i(b bVar) {
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        v().o();
    }

    @Override // b.b.k.c
    public b j(b.a aVar) {
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.Q3 != null) {
            this.Q3.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        v().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        A();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        d dVarV = v();
        dVarV.n();
        dVarV.q(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        v().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (C(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        b.b.k.a aVarW = w();
        if (menuItem.getItemId() != 16908332 || aVarW == null || (aVarW.j() & 4) == 0) {
            return false;
        }
        return B();
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
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        v().s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        v().t();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        v().u(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        v().v();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        v().w();
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        v().E(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        b.b.k.a aVarW = w();
        if (getWindow().hasFeature(0)) {
            if (aVarW == null || !aVarW.r()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i2) {
        v().A(i2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        super.setTheme(i2);
        v().D(i2);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void u() {
        v().o();
    }

    public d v() {
        if (this.P3 == null) {
            this.P3 = d.g(this, this);
        }
        return this.P3;
    }

    public b.b.k.a w() {
        return v().m();
    }

    public void x(j jVar) {
        jVar.b(this);
    }

    public void y(int i2) {
    }

    public void z(j jVar) {
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
