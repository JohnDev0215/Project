package androidx.appcompat.app;

import a.a.n.b;
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
import androidx.appcompat.widget.a1;
import androidx.core.app.g;

/* JADX INFO: loaded from: classes.dex */
public class c extends androidx.fragment.app.d implements d, g.a {
    private e s;
    private Resources t;

    private boolean N(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Override // androidx.fragment.app.d
    public void F() {
        G().o();
    }

    public e G() {
        if (this.s == null) {
            this.s = e.g(this, this);
        }
        return this.s;
    }

    public a H() {
        return G().m();
    }

    public void I(androidx.core.app.g gVar) {
        gVar.b(this);
    }

    protected void J(int i) {
    }

    public void K(androidx.core.app.g gVar) {
    }

    @Deprecated
    public void L() {
    }

    public boolean M() {
        Intent intentJ = j();
        if (intentJ == null) {
            return false;
        }
        if (!P(intentJ)) {
            O(intentJ);
            return true;
        }
        androidx.core.app.g gVarD = androidx.core.app.g.d(this);
        I(gVarD);
        K(gVarD);
        gVarD.e();
        try {
            androidx.core.app.a.h(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void O(Intent intent) {
        androidx.core.app.e.e(this, intent);
    }

    public boolean P(Intent intent) {
        return androidx.core.app.e.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        G().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(G().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        a aVarH = H();
        if (getWindow().hasFeature(0)) {
            if (aVarH == null || !aVarH.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.d, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a aVarH = H();
        if (keyCode == 82 && aVarH != null && aVarH.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) G().i(i);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return G().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.t == null && a1.b()) {
            this.t = new a1(this, super.getResources());
        }
        Resources resources = this.t;
        return resources == null ? super.getResources() : resources;
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        G().o();
    }

    @Override // androidx.core.app.g.a
    public Intent j() {
        return androidx.core.app.e.a(this);
    }

    @Override // androidx.appcompat.app.d
    public void l(a.a.n.b bVar) {
    }

    @Override // androidx.fragment.app.d, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.t != null) {
            this.t.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        G().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        L();
    }

    @Override // androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        e eVarG = G();
        eVarG.n();
        eVarG.q(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        G().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (N(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.d, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a aVarH = H();
        if (menuItem.getItemId() != 16908332 || aVarH == null || (aVarH.j() & 4) == 0) {
            return false;
        }
        return M();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.fragment.app.d, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        G().s(bundle);
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        G().t();
    }

    @Override // androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        G().u(bundle);
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onStart() {
        super.onStart();
        G().v();
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onStop() {
        super.onStop();
        G().w();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        G().E(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        a aVarH = H();
        if (getWindow().hasFeature(0)) {
            if (aVarH == null || !aVarH.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.appcompat.app.d
    public void p(a.a.n.b bVar) {
    }

    @Override // androidx.appcompat.app.d
    public a.a.n.b q(b.a aVar) {
        return null;
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        G().A(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        G().B(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        G().C(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        G().D(i);
    }
}
