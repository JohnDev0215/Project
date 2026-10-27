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

    private boolean M(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void E() {
        F().o();
    }

    public d F() {
        if (this.s == null) {
            this.s = d.g(this, this);
        }
        return this.s;
    }

    public a G() {
        return F().m();
    }

    public void H(androidx.core.app.f fVar) {
        fVar.b(this);
    }

    protected void I(int i) {
    }

    public void J(androidx.core.app.f fVar) {
    }

    @Deprecated
    public void K() {
    }

    public boolean L() {
        Intent intentJ = j();
        if (intentJ == null) {
            return false;
        }
        if (!O(intentJ)) {
            N(intentJ);
            return true;
        }
        androidx.core.app.f fVarD = androidx.core.app.f.d(this);
        H(fVarD);
        J(fVarD);
        fVarD.e();
        try {
            androidx.core.app.a.h(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void N(Intent intent) {
        androidx.core.app.d.e(this, intent);
    }

    public boolean O(Intent intent) {
        return androidx.core.app.d.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        F().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(F().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        a aVarG = G();
        if (getWindow().hasFeature(0)) {
            if (aVarG == null || !aVarG.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a aVarG = G();
        if (keyCode == 82 && aVarG != null && aVarG.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) F().i(i);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return F().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.t == null && j0.b()) {
            this.t = new j0(this, super.getResources());
        }
        Resources resources = this.t;
        return resources == null ? super.getResources() : resources;
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        F().o();
    }

    @Override // androidx.core.app.f.a
    public Intent j() {
        return androidx.core.app.d.a(this);
    }

    @Override // androidx.appcompat.app.c
    public void m(b.a.o.b bVar) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.t != null) {
            this.t.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        F().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        K();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        d dVarF = F();
        dVarF.n();
        dVarF.q(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        F().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (M(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a aVarG = G();
        if (menuItem.getItemId() != 16908332 || aVarG == null || (aVarG.j() & 4) == 0) {
            return false;
        }
        return L();
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
        F().s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        F().t();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        F().u(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        F().v();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        F().w();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        F().E(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        a aVarG = G();
        if (getWindow().hasFeature(0)) {
            if (aVarG == null || !aVarG.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.appcompat.app.c
    public void p(b.a.o.b bVar) {
    }

    @Override // androidx.appcompat.app.c
    public b.a.o.b q(b.a aVar) {
        return null;
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        F().A(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        F().D(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        F().B(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        F().C(view, layoutParams);
    }
}
