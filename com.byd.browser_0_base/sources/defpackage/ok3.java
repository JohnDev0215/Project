package defpackage;

import android.view.View;
import android.view.Window;
import defpackage.wm3;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.bottomsheet.f;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ok3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;
    public final /* synthetic */ Object j;

    public /* synthetic */ ok3(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
        this.j = obj5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.j;
        Object obj2 = this.i;
        Object obj3 = this.h;
        Object obj4 = this.g;
        Object obj5 = this.f;
        switch (i) {
            case 0:
                String str = (String) obj5;
                xe3 xe3Var = (xe3) obj4;
                gw0 gw0Var = (gw0) obj3;
                wm3.a aVar = (wm3.a) obj2;
                View view = (View) obj;
                if (str != null) {
                    xe3Var.dismissed(str);
                }
                gw0Var.k.run();
                if (aVar != null) {
                    wm3.a(view);
                }
                break;
            default:
                f.o((f) obj5, (Callback) obj4, (Window) obj3, (t41) obj2, (nw2) obj);
                break;
        }
    }
}
