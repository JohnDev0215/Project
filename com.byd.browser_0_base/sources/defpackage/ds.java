package defpackage;

import android.content.res.Resources;
import android.view.View;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ds implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ g63 f;

    public /* synthetic */ ds(g63 g63Var, int i) {
        this.e = i;
        this.f = g63Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        g63 g63Var = this.f;
        switch (i) {
            case 0:
                b92 b92Var = g63Var.M0;
                View view = b92Var.c;
                Resources resources = view.getContext().getResources();
                int i2 = R$string.reading_list_save_pages_for_later;
                hw0 hw0Var = new hw0(resources, "IPH_ReadLaterAppMenuBookmarkThisPage", i2, i2);
                hw0Var.j = view;
                hw0Var.k = new qm0(26, b92Var);
                hw0Var.m = new zx1(6, b92Var);
                b92Var.a.a(hw0Var.a());
                break;
            default:
                n3.V0(g63Var.g, g63Var.j, true);
                break;
        }
    }
}
