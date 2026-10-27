package b.g.k;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f2514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InterfaceC0040b f2515c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: renamed from: b.g.k.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0040b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.f2513a = context;
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
        this.f2515c = null;
        this.f2514b = null;
    }

    public void i(a aVar) {
        this.f2514b = aVar;
    }

    public void j(InterfaceC0040b interfaceC0040b) {
        if (this.f2515c != null && interfaceC0040b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f2515c = interfaceC0040b;
    }

    public void k(boolean z) {
        a aVar = this.f2514b;
        if (aVar != null) {
            aVar.b(z);
        }
    }
}
