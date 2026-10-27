package a.h.k;

import a.b.o.i.j;
import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f810a;

    public interface a {
    }

    public b(Context context) {
    }

    public boolean a() {
        return true;
    }

    public View b(MenuItem menuItem) {
        return ((j.a) this).f194b.onCreateActionView();
    }

    public boolean c() {
        return false;
    }

    public void d(a aVar) {
        if (this.f810a != null) {
            StringBuilder sbD = b.a.a.a.a.d("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            sbD.append(getClass().getSimpleName());
            sbD.append(" instance while it is still in use somewhere else?");
            Log.w("ActionProvider(support)", sbD.toString());
        }
        this.f810a = aVar;
    }
}
