package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.v0;
import androidx.lifecycle.f0;
import androidx.lifecycle.g0;
import androidx.savedstate.SavedStateRegistry;
import i.b;
import w.i;

/* JADX INFO: compiled from: AppCompatActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends androidx.fragment.app.d implements d, i.a {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private e f505v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private Resources f506w;

    /* JADX INFO: compiled from: AppCompatActivity.java */
    class a implements SavedStateRegistry.b {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.b
        public Bundle a() {
            Bundle bundle = new Bundle();
            c.this.D().u(bundle);
            return bundle;
        }
    }

    /* JADX INFO: compiled from: AppCompatActivity.java */
    class b implements c.b {
        b() {
        }

        @Override // c.b
        public void a(Context context) {
            e eVarD = c.this.D();
            eVarD.n();
            eVarD.q(c.this.d().a("androidx:appcompat"));
        }
    }

    public c() {
        F();
    }

    private void F() {
        d().d("androidx:appcompat", new a());
        o(new b());
    }

    private boolean L(KeyEvent keyEvent) {
        return false;
    }

    private void q() {
        f0.a(getWindow().getDecorView(), this);
        g0.a(getWindow().getDecorView(), this);
        androidx.savedstate.d.a(getWindow().getDecorView(), this);
    }

    @Override // androidx.fragment.app.d
    public void C() {
        D().o();
    }

    public e D() {
        if (this.f505v == null) {
            this.f505v = e.g(this, this);
        }
        return this.f505v;
    }

    public androidx.appcompat.app.a E() {
        return D().m();
    }

    public void G(w.i iVar) {
        iVar.e(this);
    }

    protected void H(int i7) {
    }

    public void I(w.i iVar) {
    }

    @Deprecated
    public void J() {
    }

    public boolean K() {
        Intent intentI = i();
        if (intentI == null) {
            return false;
        }
        if (!N(intentI)) {
            M(intentI);
            return true;
        }
        w.i iVarG = w.i.g(this);
        G(iVarG);
        I(iVarG);
        iVarG.j();
        try {
            w.a.e(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void M(Intent intent) {
        w.g.e(this, intent);
    }

    public boolean N(Intent intent) {
        return w.g.f(this, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        q();
        D().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(D().f(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        androidx.appcompat.app.a aVarE = E();
        if (getWindow().hasFeature(0)) {
            if (aVarE == null || !aVarE.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // w.f, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        androidx.appcompat.app.a aVarE = E();
        if (keyCode == 82 && aVarE != null && aVarE.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.appcompat.app.d
    public void e(i.b bVar) {
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i7) {
        return (T) D().i(i7);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return D().l();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.f506w == null && v0.b()) {
            this.f506w = new v0(this, super.getResources());
        }
        Resources resources = this.f506w;
        return resources == null ? super.getResources() : resources;
    }

    @Override // w.i.a
    public Intent i() {
        return w.g.a(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        D().o();
    }

    @Override // androidx.appcompat.app.d
    public void j(i.b bVar) {
    }

    @Override // androidx.appcompat.app.d
    public i.b l(b.a aVar) {
        return null;
    }

    @Override // androidx.fragment.app.d, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f506w != null) {
            this.f506w.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        D().p(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        J();
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        D().r();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (L(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i7, keyEvent);
    }

    @Override // androidx.fragment.app.d, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        if (super.onMenuItemSelected(i7, menuItem)) {
            return true;
        }
        androidx.appcompat.app.a aVarE = E();
        if (menuItem.getItemId() != 16908332 || aVarE == null || (aVarE.j() & 4) == 0) {
            return false;
        }
        return K();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i7, Menu menu) {
        return super.onMenuOpened(i7, menu);
    }

    @Override // androidx.fragment.app.d, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i7, Menu menu) {
        super.onPanelClosed(i7, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        D().s(bundle);
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        D().t();
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onStart() {
        super.onStart();
        D().v();
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onStop() {
        super.onStop();
        D().w();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i7) {
        super.onTitleChanged(charSequence, i7);
        D().E(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        androidx.appcompat.app.a aVarE = E();
        if (getWindow().hasFeature(0)) {
            if (aVarE == null || !aVarE.q()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i7) {
        q();
        D().A(i7);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i7) {
        super.setTheme(i7);
        D().D(i7);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        q();
        D().B(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        q();
        D().C(view, layoutParams);
    }
}
