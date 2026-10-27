package p;

import android.os.IBinder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zg1 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ wg1 e;
    public final /* synthetic */ String k;
    public final /* synthetic */ wg1 l;
    public final /* synthetic */ Object m;

    public /* synthetic */ zg1(wg1 wg1Var, wg1 wg1Var2, String str, Object obj, int i) {
        this.c = i;
        this.l = wg1Var;
        this.e = wg1Var2;
        this.k = str;
        this.m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        String str = this.k;
        Object obj = this.m;
        wg1 wg1Var = this.l;
        wg1 wg1Var2 = this.e;
        switch (i) {
            case 0:
                jg1 jg1Var = (jg1) ((bh1) wg1Var.a).m.get(wg1Var2.a());
                if (jg1Var == null) {
                    return;
                }
                IBinder iBinder = (IBinder) obj;
                ((bh1) wg1Var.a).getClass();
                HashMap map = jg1Var.e;
                if (iBinder == null) {
                    map.remove(str);
                    return;
                }
                List list = (List) map.get(str);
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (iBinder == ((pr1) it.next()).a) {
                            it.remove();
                        }
                    }
                    if (list.size() == 0) {
                        map.remove(str);
                        return;
                    }
                    return;
                }
                return;
            default:
                if (((jg1) ((bh1) wg1Var.a).m.get(wg1Var2.a())) == null) {
                    return;
                }
                bh1 bh1Var = (bh1) wg1Var.a;
                bh1Var.getClass();
                gg1 gg1Var = new gg1(str, (b52) obj, 0);
                bh1Var.b(str, gg1Var);
                if (!gg1Var.b()) {
                    throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=".concat(str));
                }
                return;
        }
    }
}
