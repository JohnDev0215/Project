package p;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class wl0 extends xn2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ wl0(Object obj, int i, Object obj2) {
        this.a = i;
        this.c = obj;
        this.b = obj2;
    }

    @Override // p.vn2
    public final void a(wn2 wn2Var) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                iv2 iv2Var = dv2.a;
                iv2Var.M((View) obj, 1.0f);
                iv2Var.getClass();
                wn2Var.z(this);
                break;
            case 1:
                ((ArrayList) ((nb) obj).get(((yn2) this.c).e)).remove(wn2Var);
                wn2Var.z(this);
                break;
            default:
                ((wn2) obj).C();
                wn2Var.z(this);
                break;
        }
    }
}
