package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import androidx.recyclerview.widget.GridLayoutManager;
import org.chromium.chrome.browser.tasks.tab_management.j0;

/* JADX INFO: compiled from: TabListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d13 implements ComponentCallbacks {
    public final /* synthetic */ GridLayoutManager e;
    public final /* synthetic */ j0 f;

    public d13(j0 j0Var, GridLayoutManager gridLayoutManager) {
        this.f = j0Var;
        this.e = gridLayoutManager;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        int i = configuration.orientation;
        GridLayoutManager gridLayoutManager = this.e;
        j0 j0Var = this.f;
        j0Var.q(gridLayoutManager);
        if (j0Var.g != 0 || j0Var.v == 0) {
            return;
        }
        j0Var.p();
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
