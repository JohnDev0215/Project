package b.c.a.f;

import a.k.n;
import android.text.TextUtils;
import com.byd.globalnotification.notification.NotifyMsgBean;
import com.byd.globalnotification.provider.MsgProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<c> f1749a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.k.n<NotifyMsgBean> f1750b;

    public class a extends n.a<a.k.n<NotifyMsgBean>> {
        public a() {
        }

        @Override // a.k.n.a
        public void d(a.k.n<NotifyMsgBean> nVar) {
            b.c.a.h.b.b("notifyMsgs onChanged");
        }

        @Override // a.k.n.a
        public void e(a.k.n<NotifyMsgBean> nVar, int i, int i2) {
            MsgProvider.a(b.c.a.h.d.b.a(), "notifyObserversPosted");
            b.c.a.h.b.b("notifyMsgs onItemRangeChanged");
        }

        @Override // a.k.n.a
        public void f(a.k.n<NotifyMsgBean> nVar, int i, int i2) {
            MsgProvider.a(b.c.a.h.d.b.a(), "notifyObserversPosted");
            b.c.a.h.b.b("notifyMsgs onItemRangeInserted,notifyMsgs:" + t.this.f1750b.size() + ",itemCount:" + i2);
        }

        @Override // a.k.n.a
        public void g(a.k.n<NotifyMsgBean> nVar, int i, int i2, int i3) {
            b.c.a.h.b.b("notifyMsgs onItemRangeMoved");
        }

        @Override // a.k.n.a
        public void h(a.k.n<NotifyMsgBean> nVar, int i, int i2) {
            if (t.this.f1750b.size() == 0) {
                MsgProvider.a(b.c.a.h.d.b.a(), "removeNotifyMsg");
            }
            StringBuilder sbD = b.a.a.a.a.d("notifyMsgs onItemRangeRemoved, size = ");
            sbD.append(nVar.size());
            b.c.a.h.b.b(sbD.toString());
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final t f1752a = new t();
    }

    public interface c {
        void a(NotifyMsgBean notifyMsgBean, NotifyMsgBean notifyMsgBean2);

        void b(NotifyMsgBean notifyMsgBean);

        void c(NotifyMsgBean notifyMsgBean);
    }

    public t() {
        a.k.j jVar = new a.k.j();
        this.f1750b = jVar;
        jVar.b(new a());
    }

    public static t a() {
        return b.f1752a;
    }

    public void c(NotifyMsgBean notifyMsgBean) {
        NotifyMsgBean next;
        Iterator<NotifyMsgBean> it = this.f1750b.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (TextUtils.equals(next.getKey(), notifyMsgBean.getKey())) {
                    break;
                }
            }
        }
        if (next != null) {
            List<c> list = b.f1752a.f1749a;
            if (list == null || list.size() <= 0) {
                this.f1750b.remove(next);
                this.f1750b.add(0, notifyMsgBean);
            } else {
                Iterator<c> it2 = b.f1752a.f1749a.iterator();
                while (it2.hasNext()) {
                    it2.next().a(notifyMsgBean, next);
                }
            }
        } else {
            this.f1750b.add(0, notifyMsgBean);
            b.c.a.h.b.a("InstanceHolder.INSTANCE.mObservers:" + b.f1752a.f1749a.size());
            Iterator<c> it3 = b.f1752a.f1749a.iterator();
            while (it3.hasNext()) {
                it3.next().c(notifyMsgBean);
            }
        }
        f.a.a.c.b().f(new NotifyMsgBean());
    }

    public void d(NotifyMsgBean notifyMsgBean) {
        int i = 0;
        while (true) {
            if (i >= this.f1750b.size()) {
                i = -1;
                break;
            } else if (this.f1750b.get(i).getKey().equals(notifyMsgBean.getKey())) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            NotifyMsgBean notifyMsgBean2 = this.f1750b.get(i);
            this.f1750b.remove(notifyMsgBean2);
            Iterator<c> it = b.f1752a.f1749a.iterator();
            while (it.hasNext()) {
                it.next().b(notifyMsgBean2);
            }
        }
    }
}
