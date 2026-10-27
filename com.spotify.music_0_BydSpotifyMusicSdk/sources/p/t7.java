package p;

import android.R;
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
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class t7 extends zw0 implements w7 {
    public s8 F;

    public t7() {
        this.m.b.b("androidx:appcompat", new r7(this));
        n(new s7(this));
    }

    @Override // android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v();
        s8 s8Var = (s8) u();
        s8Var.u();
        ((ViewGroup) s8Var.I.findViewById(R.id.content)).addView(view, layoutParams);
        s8Var.u.a(s8Var.t.getCallback());
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b2  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void attachBaseContext(android.content.Context r12) {
        /*
            Method dump skipped, instruction units count: 548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.t7.attachBaseContext(android.content.Context):void");
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        ((s8) u()).y();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // p.wv, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        ((s8) u()).y();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // p.w7
    public final void f() {
    }

    @Override // android.app.Activity
    public final View findViewById(int i) {
        s8 s8Var = (s8) u();
        s8Var.u();
        return s8Var.t.findViewById(i);
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        s8 s8Var = (s8) u();
        if (s8Var.x == null) {
            s8Var.y();
            ow2 ow2Var = s8Var.w;
            s8Var.x = new lh2(ow2Var != null ? ow2Var.I0() : s8Var.s);
        }
        return s8Var.x;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i = ws2.a;
        return super.getResources();
    }

    @Override // p.w7
    public final void i() {
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        s8 s8Var = (s8) u();
        if (s8Var.w != null) {
            s8Var.y();
            s8Var.w.getClass();
            s8Var.z(0);
        }
    }

    @Override // p.w7
    public final void k() {
    }

    @Override // androidx.activity.a, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        s8 s8Var = (s8) u();
        if (s8Var.N && s8Var.H) {
            s8Var.y();
            ow2 ow2Var = s8Var.w;
            if (ow2Var != null) {
                ow2Var.L0(ow2Var.i.getResources().getBoolean(com.spotify.music.R.bool.abc_action_bar_embed_tabs));
            }
        }
        u8 u8VarA = u8.a();
        Context context = s8Var.s;
        synchronized (u8VarA) {
            h42 h42Var = u8VarA.a;
            synchronized (h42Var) {
                wd1 wd1Var = (wd1) h42Var.b.get(context);
                if (wd1Var != null) {
                    wd1Var.a();
                }
            }
        }
        s8Var.Z = new Configuration(s8Var.s.getResources().getConfiguration());
        s8Var.l(false, false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }

    @Override // p.zw0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        u().c();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // p.zw0, androidx.activity.a, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intent intentN;
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        s8 s8Var = (s8) u();
        s8Var.y();
        ow2 ow2Var = s8Var.w;
        if (menuItem.getItemId() == 16908332 && ow2Var != null && (((hm2) ow2Var.m).b & 4) != 0 && (intentN = ln1.n(this)) != null) {
            if (!km1.c(this, intentN)) {
                km1.b(this, intentN);
                return true;
            }
            ArrayList arrayList = new ArrayList();
            Intent intentN2 = ln1.n(this);
            if (intentN2 == null) {
                intentN2 = ln1.n(this);
            }
            if (intentN2 != null) {
                ComponentName component = intentN2.getComponent();
                if (component == null) {
                    component = intentN2.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent intentO = ln1.o(this, component);
                    while (intentO != null) {
                        arrayList.add(size, intentO);
                        intentO = ln1.o(this, intentO.getComponent());
                    }
                    arrayList.add(intentN2);
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
            Object obj = sz.a;
            nz.a(this, intentArr, null);
            try {
                int i2 = o4.b;
                n4.a(this);
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

    @Override // androidx.activity.a, android.app.Activity, android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((s8) u()).u();
    }

    @Override // p.zw0, android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        s8 s8Var = (s8) u();
        s8Var.y();
        ow2 ow2Var = s8Var.w;
        if (ow2Var != null) {
            ow2Var.B = true;
        }
    }

    @Override // p.zw0, android.app.Activity
    public void onStart() {
        super.onStart();
        ((s8) u()).l(true, false);
    }

    @Override // p.zw0, android.app.Activity
    public void onStop() {
        super.onStop();
        s8 s8Var = (s8) u();
        s8Var.y();
        ow2 ow2Var = s8Var.w;
        if (ow2Var != null) {
            ow2Var.B = false;
            vu2 vu2Var = ow2Var.A;
            if (vu2Var != null) {
                vu2Var.a();
            }
        }
    }

    @Override // android.app.Activity
    public final void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        u().k(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        ((s8) u()).y();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i) {
        v();
        u().g(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        ((s8) u()).b0 = i;
    }

    public final e8 u() {
        if (this.F == null) {
            va vaVar = e8.c;
            this.F = new s8(this, null, this, this);
        }
        return this.F;
    }

    public final void v() {
        l13.l(getWindow().getDecorView(), this);
        View decorView = getWindow().getDecorView();
        v03.j(decorView, "<this>");
        decorView.setTag(com.spotify.music.R.id.view_tree_view_model_store_owner, this);
        l13.m(getWindow().getDecorView(), this);
        View decorView2 = getWindow().getDecorView();
        v03.j(decorView2, "<this>");
        decorView2.setTag(com.spotify.music.R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // androidx.activity.a, android.app.Activity
    public void setContentView(View view) {
        v();
        u().i(view);
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        v();
        u().j(view, layoutParams);
    }
}
