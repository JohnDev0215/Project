package i0;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6830a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f6831b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InterfaceC0084b f6832c;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
    }

    /* JADX INFO: renamed from: i0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0084b {
        void onActionProviderVisibilityChanged(boolean z6);
    }

    public b(Context context) {
        this.f6830a = context;
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
        this.f6832c = null;
        this.f6831b = null;
    }

    public void i(a aVar) {
        this.f6831b = aVar;
    }

    public void j(InterfaceC0084b interfaceC0084b) {
        if (this.f6832c != null && interfaceC0084b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f6832c = interfaceC0084b;
    }
}
