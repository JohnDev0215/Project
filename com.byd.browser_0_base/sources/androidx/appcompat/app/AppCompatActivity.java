package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppLocalesStorageHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.viewmodel.R$id;
import androidx.savedstate.a;
import defpackage.bz;
import defpackage.ds1;
import defpackage.i21;
import defpackage.n2;
import defpackage.n3;
import defpackage.rk1;
import defpackage.z7;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements AppCompatCallback, ActionBarDrawerToggle.DelegateProvider {
    public AppCompatDelegate C;

    public AppCompatActivity() {
        this.i.b.b("androidx:appcompat", new a.b() { // from class: androidx.appcompat.app.AppCompatActivity.1
            @Override // androidx.savedstate.a.b
            public final Bundle a() {
                Bundle bundle = new Bundle();
                AppCompatActivity.this.A1().t();
                return bundle;
            }
        });
        addOnContextAvailableListener(new ds1() { // from class: androidx.appcompat.app.AppCompatActivity.2
            @Override // defpackage.ds1
            public final void a() {
                AppCompatActivity appCompatActivity = AppCompatActivity.this;
                AppCompatDelegate appCompatDelegateA1 = appCompatActivity.A1();
                appCompatDelegateA1.l();
                appCompatActivity.i.b.a("androidx:appcompat");
                appCompatDelegateA1.p();
            }
        });
    }

    private void v1() {
        z7.f0(getWindow().getDecorView(), this);
        View decorView = getWindow().getDecorView();
        i21.e(decorView, "<this>");
        decorView.setTag(R$id.view_tree_view_model_store_owner, this);
        n3.g1(getWindow().getDecorView(), this);
        View decorView2 = getWindow().getDecorView();
        i21.e(decorView2, "<this>");
        decorView2.setTag(androidx.activity.R$id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    public AppCompatDelegate A1() {
        if (this.C == null) {
            AppLocalesStorageHelper.SerialExecutor serialExecutor = AppCompatDelegate.e;
            this.C = new AppCompatDelegateImpl(this, null, this, this);
        }
        return this.C;
    }

    public final ActionBar B1() {
        return A1().k();
    }

    public final void C1(Toolbar toolbar) {
        A1().E(toolbar);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v1();
        A1().d(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(A1().f(context));
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        ActionBar actionBarB1 = B1();
        if (getWindow().hasFeature(0)) {
            if (actionBarB1 == null || !actionBarB1.g()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        ActionBar actionBarB1 = B1();
        if (keyCode == 82 && actionBarB1 != null && actionBarB1.q(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public final <T extends View> T findViewById(int i) {
        return (T) A1().g(i);
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        return A1().j();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i = VectorEnabledTintResources.a;
        return super.getResources();
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        A1().m();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        A1().o(configuration);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        A1().q();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intent intentA;
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar actionBarB1 = B1();
        if (menuItem.getItemId() == 16908332 && actionBarB1 != null && (actionBarB1.j() & 4) != 0 && (intentA = rk1.a(this)) != null) {
            if (!shouldUpRecreateTask(intentA)) {
                navigateUpTo(intentA);
                return true;
            }
            ArrayList arrayList = new ArrayList();
            Intent intentA2 = rk1.a(this);
            if (intentA2 == null) {
                intentA2 = rk1.a(this);
            }
            if (intentA2 != null) {
                ComponentName component = intentA2.getComponent();
                if (component == null) {
                    component = intentA2.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent intentB = rk1.b(this, component);
                    while (intentB != null) {
                        arrayList.add(size, intentB);
                        intentB = rk1.b(this, intentB.getComponent());
                    }
                    arrayList.add(intentA2);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e);
                }
            }
            if (arrayList.isEmpty()) {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            Object obj = bz.a;
            startActivities(intentArr, null);
            try {
                int i2 = n2.b;
                finishAffinity();
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        A1().r();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        A1().s();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        A1().u();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        A1().v();
    }

    @Override // android.app.Activity
    public final void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        A1().G(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        ActionBar actionBarB1 = B1();
        if (getWindow().hasFeature(0)) {
            if (actionBarB1 == null || !actionBarB1.r()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(int i) {
        v1();
        A1().y(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        A1().F(i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        v1();
        A1().z(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v1();
        A1().A(view, layoutParams);
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public final void I0() {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public final void Y0() {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public final void j0() {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }
}
