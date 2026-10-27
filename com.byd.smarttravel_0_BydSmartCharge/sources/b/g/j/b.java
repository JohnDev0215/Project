package b.g.j;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1094a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1095b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public InterfaceC0023b f1096c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: renamed from: b.g.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0023b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.f1094a = context;
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
        this.f1096c = null;
        this.f1095b = null;
    }

    public void i(a aVar) {
        this.f1095b = aVar;
    }

    public void j(InterfaceC0023b interfaceC0023b) {
        if (this.f1096c != null && interfaceC0023b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f1096c = interfaceC0023b;
    }

    public void k(boolean z) {
        a aVar = this.f1095b;
        if (aVar != null) {
            aVar.b(z);
        }
    }
}
