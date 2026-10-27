package a.g.k;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f376b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InterfaceC0013b f377c;

    public interface a {
    }

    /* JADX INFO: renamed from: a.g.k.b$b, reason: collision with other inner class name */
    public interface InterfaceC0013b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.f375a = context;
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
        this.f377c = null;
        this.f376b = null;
    }

    public void i(a aVar) {
        this.f376b = aVar;
    }

    public void j(InterfaceC0013b interfaceC0013b) {
        if (this.f377c != null && interfaceC0013b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f377c = interfaceC0013b;
    }
}
