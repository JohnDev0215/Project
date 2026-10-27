package androidx.core.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.mediarouter.app.MediaRouteActionProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActionProvider {
    public final Context a;
    public a b;
    public b c;

    public interface a {
    }

    public interface b {
    }

    public ActionProvider(Context context) {
        this.a = context;
    }

    public boolean a() {
        return this instanceof ShareActionProvider;
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

    public boolean g() {
        return this instanceof MediaRouteActionProvider;
    }

    public void h(b bVar) {
        if (this.c != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.c = bVar;
    }

    public void f(SubMenuBuilder subMenuBuilder) {
    }
}
