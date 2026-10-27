package a.b.k;

import a.b.k.l;
import a.b.o.a;
import a.b.p.n0;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class i extends a.l.d.e implements j, a.h.c.d {
    public k n;
    public Resources o;

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        q().c(view, layoutParams);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void attachBaseContext(android.content.Context r9) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.i.attachBaseContext(android.content.Context):void");
    }

    @Override // a.b.k.j
    public void b(a.b.o.a aVar) {
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        r();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // a.h.c.b, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        r();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // a.b.k.j
    public void f(a.b.o.a aVar) {
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        l lVar = (l) q();
        lVar.A();
        return (T) lVar.f22e.findViewById(i);
    }

    @Override // a.b.k.j
    public a.b.o.a g(a.InterfaceC0001a interfaceC0001a) {
        return null;
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        l lVar = (l) q();
        if (lVar.i == null) {
            lVar.G();
            e eVar = lVar.h;
            lVar.i = new a.b.o.f(eVar != null ? eVar.d() : lVar.f21d);
        }
        return lVar.i;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.o;
        Resources resources2 = this.o;
        return resources2 == null ? super.getResources() : resources2;
    }

    @Override // a.h.c.d
    public Intent i() {
        return l.i.C(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        q().h();
    }

    @Override // a.l.d.e, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.o != null) {
            this.o.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        l lVar = (l) q();
        if (lVar.z && lVar.t) {
            lVar.G();
            e eVar = lVar.h;
            if (eVar != null) {
                eVar.e(configuration);
            }
        }
        a.b.p.j jVarA = a.b.p.j.a();
        Context context = lVar.f21d;
        synchronized (jVarA) {
            n0 n0Var = jVarA.f292a;
            synchronized (n0Var) {
                a.e.e<WeakReference<Drawable.ConstantState>> eVar2 = n0Var.f323d.get(context);
                if (eVar2 != null) {
                    eVar2.a();
                }
            }
        }
        lVar.r(false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
    }

    @Override // a.l.d.e, androidx.activity.ComponentActivity, a.h.c.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        k kVarQ = q();
        kVarQ.g();
        kVarQ.i(bundle);
        super.onCreate(bundle);
    }

    @Override // a.l.d.e, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        q().j();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // a.l.d.e, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intent intentC;
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        e eVarR = r();
        if (menuItem.getItemId() == 16908332 && eVarR != null && (eVarR.c() & 4) != 0 && (intentC = l.i.C(this)) != null) {
            if (!shouldUpRecreateTask(intentC)) {
                navigateUpTo(intentC);
                return true;
            }
            ArrayList arrayList = new ArrayList();
            Intent intentI = i();
            if (intentI == null) {
                intentI = l.i.C(this);
            }
            if (intentI != null) {
                ComponentName component = intentI.getComponent();
                if (component == null) {
                    component = intentI.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                while (true) {
                    try {
                        Intent intentD = l.i.D(this, component);
                        if (intentD == null) {
                            break;
                        }
                        arrayList.add(size, intentD);
                        component = intentD.getComponent();
                    } catch (PackageManager.NameNotFoundException e2) {
                        Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                        throw new IllegalArgumentException(e2);
                    }
                }
                arrayList.add(intentI);
            }
            t();
            if (arrayList.isEmpty()) {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            a.h.d.a.c(this, intentArr, null);
            try {
                a.h.c.a.d(this);
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // a.l.d.e, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((l) q()).A();
    }

    @Override // a.l.d.e, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        l lVar = (l) q();
        lVar.G();
        e eVar = lVar.h;
        if (eVar != null) {
            eVar.h(true);
        }
    }

    @Override // a.l.d.e, androidx.activity.ComponentActivity, a.h.c.b, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (((l) q()) == null) {
            throw null;
        }
    }

    @Override // a.l.d.e, android.app.Activity
    public void onStart() {
        super.onStart();
        l lVar = (l) q();
        lVar.K = true;
        lVar.q();
    }

    @Override // a.l.d.e, android.app.Activity
    public void onStop() {
        super.onStop();
        l lVar = (l) q();
        lVar.K = false;
        lVar.G();
        e eVar = lVar.h;
        if (eVar != null) {
            eVar.h(false);
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        q().p(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        r();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // a.l.d.e
    public void p() {
        q().h();
    }

    public k q() {
        if (this.n == null) {
            this.n = k.d(this, this);
        }
        return this.n;
    }

    public e r() {
        l lVar = (l) q();
        lVar.G();
        return lVar.h;
    }

    public void s() {
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        q().m(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        q().n(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        q().o(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        ((l) q()).N = i;
    }

    public void t() {
    }
}
