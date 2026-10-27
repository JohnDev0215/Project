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
    private final Context f2668a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f2669b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InterfaceC0046b f2670c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: renamed from: b.g.k.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0046b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.f2668a = context;
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
        this.f2670c = null;
        this.f2669b = null;
    }

    public void i(a aVar) {
        this.f2669b = aVar;
    }

    public void j(InterfaceC0046b interfaceC0046b) {
        if (this.f2670c != null && interfaceC0046b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f2670c = interfaceC0046b;
    }

    public void k(boolean z) {
        a aVar = this.f2669b;
        if (aVar != null) {
            aVar.b(z);
        }
    }
}
