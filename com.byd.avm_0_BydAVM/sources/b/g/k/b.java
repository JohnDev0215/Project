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
    private final Context f1711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f1712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InterfaceC0039b f1713c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: renamed from: b.g.k.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0039b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.f1711a = context;
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
        this.f1713c = null;
        this.f1712b = null;
    }

    public void i(a aVar) {
        this.f1712b = aVar;
    }

    public void j(InterfaceC0039b interfaceC0039b) {
        if (this.f1713c != null && interfaceC0039b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f1713c = interfaceC0039b;
    }

    public void k(boolean z) {
        a aVar = this.f1712b;
        if (aVar != null) {
            aVar.b(z);
        }
    }
}
