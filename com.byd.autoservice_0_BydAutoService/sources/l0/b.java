package l0;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f3614b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public InterfaceC0033b f3615c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z3);
    }

    /* JADX INFO: renamed from: l0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0033b {
        void onActionProviderVisibilityChanged(boolean z3);
    }

    public b(Context context) {
        this.f3613a = context;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public abstract View c();

    public View d(MenuItem menuItem) {
        return c();
    }

    public boolean e() {
        return false;
    }

    public void f(SubMenu subMenu) {
    }

    public boolean g() {
        return false;
    }

    public void h() {
        this.f3615c = null;
        this.f3614b = null;
    }

    public void i(a aVar) {
        this.f3614b = aVar;
    }

    public void j(InterfaceC0033b interfaceC0033b) {
        if (this.f3615c != null && interfaceC0033b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f3615c = interfaceC0033b;
    }

    public void k(boolean z3) {
        a aVar = this.f3614b;
        if (aVar != null) {
            aVar.b(z3);
        }
    }
}
