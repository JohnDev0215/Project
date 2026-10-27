package defpackage;

import androidx.recyclerview.widget.GridLayoutManager;
import org.chromium.chrome.browser.tasks.tab_management.j0;

/* JADX INFO: compiled from: TabListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e13 extends GridLayoutManager.b {
    public final /* synthetic */ GridLayoutManager c;
    public final /* synthetic */ j0 d;

    public e13(j0 j0Var, GridLayoutManager gridLayoutManager) {
        this.d = j0Var;
        this.c = gridLayoutManager;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager.b
    public final int c(int i) {
        int i2 = this.d.f.get(i).a;
        if (i2 == 3 || i2 == 6 || i2 == 4) {
            return this.c.F;
        }
        return 1;
    }
}
