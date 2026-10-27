package b.c.a.d;

import a.k.j;
import a.k.n;
import a.n.x;
import android.os.Handler;
import android.os.Looper;
import b.c.a.c;
import b.c.a.f.t;
import b.c.a.f.v;
import b.c.a.f.w;
import com.byd.globalnotification.notification.NotifyMsgBean;
import com.byd.globalnotification.provider.MsgProvider;
import e.a.a.f;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends x {
    public b() {
        getClass().getSimpleName();
        final w wVar = (w) this;
        b.c.a.h.b.a("NotificationViewModel onCreate");
        wVar.f1759d = new j();
        wVar.f1758c = new HashMap();
        wVar.f1760e = new c();
        wVar.f1761f = new c<>();
        wVar.g = new c();
        wVar.i = new Handler(Looper.getMainLooper());
        wVar.i.postDelayed(new v(wVar), 60000L);
        wVar.h = new f() { // from class: b.c.a.f.l
            @Override // e.a.a.f
            public final void a(e.a.a.e eVar, int i, Object obj) {
                wVar.l(eVar, i, (NotifyMsgBean) obj);
            }
        };
        wVar.j = new c();
        n<NotifyMsgBean> nVar = t.b.f1752a.f1750b;
        int size = nVar.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else {
                wVar.c(nVar.get(size));
            }
        }
        t tVar = t.b.f1752a;
        if (tVar == null) {
            throw null;
        }
        if (!tVar.f1749a.contains(wVar)) {
            t.b.f1752a.f1749a.add(wVar);
        }
        MsgProvider.a(b.c.a.h.d.b.a(), "initItems");
    }

    @Override // a.n.x
    public void d() {
        w wVar = (w) this;
        wVar.i.removeCallbacksAndMessages(null);
        t tVar = t.b.f1752a;
        if (tVar == null) {
            throw null;
        }
        List<t.c> list = tVar.f1749a;
        if (list == null) {
            return;
        }
        list.remove(wVar);
    }
}
