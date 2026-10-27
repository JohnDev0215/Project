package o0;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f6662b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public InterfaceC0098b f6663c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void c(boolean z7);
    }

    /* JADX INFO: renamed from: o0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0098b {
        void onActionProviderVisibilityChanged(boolean z7);
    }

    public b(Context context) {
        this.f6661a = context;
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
        this.f6663c = null;
        this.f6662b = null;
    }

    public void i(a aVar) {
        this.f6662b = aVar;
    }

    public void j(InterfaceC0098b interfaceC0098b) {
        if (this.f6663c != null && interfaceC0098b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f6663c = interfaceC0098b;
    }

    public void k(boolean z7) {
        a aVar = this.f6662b;
        if (aVar != null) {
            aVar.c(z7);
        }
    }
}
