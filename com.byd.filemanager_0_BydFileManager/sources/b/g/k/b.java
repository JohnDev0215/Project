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
    private final Context f1945a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f1946b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InterfaceC0037b f1947c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: renamed from: b.g.k.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0037b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.f1945a = context;
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
        this.f1947c = null;
        this.f1946b = null;
    }

    public void i(a aVar) {
        this.f1946b = aVar;
    }

    public void j(InterfaceC0037b interfaceC0037b) {
        if (this.f1947c != null && interfaceC0037b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f1947c = interfaceC0037b;
    }

    public void k(boolean z) {
        a aVar = this.f1946b;
        if (aVar != null) {
            aVar.b(z);
        }
    }
}
