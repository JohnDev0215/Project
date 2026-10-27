package b.c.a.f;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.SparseArray;
import android.widget.RemoteViews;
import b.c.a.f.t;
import com.byd.globalnotification.R;
import com.byd.globalnotification.notification.NotifyMsgBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class w extends b.c.a.d.b implements t.c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, List<NotifyMsgBean>> f1758c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a.k.n<NotifyMsgBean> f1759d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.c.a.c f1760e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.c.a.c<Integer> f1761f;
    public b.c.a.c g;
    public e.a.a.f<NotifyMsgBean> h;
    public Handler i;
    public b.c.a.c j;
    public int k;
    public b.c.a.h.c.b<NotifyMsgBean> l = new b.c.a.h.c.b<>(new b.c.a.h.c.c() { // from class: b.c.a.f.p
        @Override // b.c.a.h.c.c
        public final void a(Object obj) {
            u.a((NotifyMsgBean) obj);
        }
    });
    public b.c.a.h.c.b m = new b.c.a.h.c.b(new b.c.a.h.c.a() { // from class: b.c.a.f.k
        @Override // b.c.a.h.c.a
        public final void call() {
            this.f1740a.h();
        }
    });
    public b.c.a.h.c.b n = new b.c.a.h.c.b(new b.c.a.h.c.a() { // from class: b.c.a.f.n
        @Override // b.c.a.h.c.a
        public final void call() {
            this.f1743a.i();
        }
    });
    public b.c.a.h.c.b<NotifyMsgBean> o = new b.c.a.h.c.b<>(new b.c.a.h.c.c() { // from class: b.c.a.f.m
        @Override // b.c.a.h.c.c
        public final void a(Object obj) {
            this.f1742a.k((NotifyMsgBean) obj);
        }
    });

    public static void j(NotifyMsgBean notifyMsgBean) {
        notifyMsgBean.isExpand.j(!r1.f924b);
    }

    public static /* synthetic */ void n(NotifyMsgBean notifyMsgBean) {
        notifyMsgBean.setMultiple(true);
        notifyMsgBean.isExpand.j(false);
    }

    public static /* synthetic */ boolean o(NotifyMsgBean notifyMsgBean) {
        return !"com.byd.uereport".equals(notifyMsgBean.getPkgName());
    }

    @Override // b.c.a.f.t.c
    public void a(NotifyMsgBean notifyMsgBean, NotifyMsgBean notifyMsgBean2) {
        a.k.m progress;
        int type = notifyMsgBean.getType();
        if (type == 0) {
            notifyMsgBean2.timeString.j(notifyMsgBean.timeString.f925b);
            notifyMsgBean2.title.j(notifyMsgBean.title.f925b);
            notifyMsgBean2.content.j(notifyMsgBean.content.f925b);
            notifyMsgBean2.setProgressMax(notifyMsgBean.getProgressMax().f926b);
            progress = notifyMsgBean.getProgress();
        } else {
            if (type != 1) {
                if (type != 2) {
                    return;
                }
                a.k.l<RemoteViews> lVar = notifyMsgBean2.contentView;
                if (lVar != null) {
                    lVar.j(notifyMsgBean.contentView.f925b);
                }
                a.k.l<RemoteViews> lVar2 = notifyMsgBean2.bigContentView;
                if (lVar2 != null) {
                    lVar2.j(notifyMsgBean.bigContentView.f925b);
                    return;
                }
                return;
            }
            notifyMsgBean2.title.j(notifyMsgBean.title.f925b);
            notifyMsgBean2.content.j(notifyMsgBean.content.f925b);
            notifyMsgBean2.setProgressMax(notifyMsgBean.getProgressMax().f926b);
            progress = notifyMsgBean.getProgress();
        }
        notifyMsgBean2.setProgress(progress.f926b);
    }

    @Override // b.c.a.f.t.c
    public void b(NotifyMsgBean notifyMsgBean) {
        r(notifyMsgBean, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // b.c.a.f.t.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(com.byd.globalnotification.notification.NotifyMsgBean r9) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.f.w.c(com.byd.globalnotification.notification.NotifyMsgBean):void");
    }

    public void f() {
        Iterator<Map.Entry<String, List<NotifyMsgBean>>> it = this.f1758c.entrySet().iterator();
        while (it.hasNext()) {
            List<NotifyMsgBean> value = it.next().getValue();
            if (value != null && value.size() > 0 && value.get(0).isExpand.f924b) {
                value.get(0).isExpand.j(false);
                if (value.size() > 1) {
                    for (int i = 1; i < value.size(); i++) {
                        this.f1759d.remove(value.get(i));
                    }
                }
            }
        }
    }

    public String g(NotifyMsgBean notifyMsgBean) {
        String pkgName = notifyMsgBean.getPkgName();
        if (b.c.a.h.d.a.a(pkgName)) {
            return "";
        }
        try {
            PackageManager packageManager = b.c.a.h.d.b.a().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(pkgName, 0);
            return packageInfo == null ? null : packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public /* synthetic */ void i() {
        this.j.j();
    }

    public void k(NotifyMsgBean notifyMsgBean) {
        List<NotifyMsgBean> list = this.f1758c.get(notifyMsgBean.getPkgName());
        if (list == null) {
            return;
        }
        if (notifyMsgBean.isExpand.f924b) {
            for (int i = 1; i < list.size(); i++) {
                this.f1759d.remove(list.get(i));
            }
        } else {
            int iIndexOf = this.f1759d.indexOf(notifyMsgBean);
            if (list.size() > 1) {
                this.f1759d.addAll(iIndexOf + 1, list.subList(1, list.size()));
            }
        }
        list.forEach(new Consumer() { // from class: b.c.a.f.h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                w.j((NotifyMsgBean) obj);
            }
        });
    }

    public void l(e.a.a.e eVar, int i, NotifyMsgBean notifyMsgBean) {
        int i2 = notifyMsgBean.contentView.f925b != null ? R.layout.item_custom_wrap : notifyMsgBean.bigContentView.f925b != null ? R.layout.item_custom_wrap_big : R.layout.item_notify_msg;
        eVar.f2529b = 1;
        eVar.f2530c = i2;
        if (eVar.f2531d == null) {
            eVar.f2531d = new SparseArray<>(1);
        }
        eVar.f2531d.put(2, this);
    }

    public /* synthetic */ void m(NotifyMsgBean notifyMsgBean) {
        this.f1759d.remove(notifyMsgBean);
    }

    public void p() {
        if (this.f1759d.size() > 0) {
            Iterator<NotifyMsgBean> it = this.f1759d.iterator();
            while (it.hasNext()) {
                it.next().refreshTimeStringForSysMsg();
            }
        }
    }

    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public void h() {
        if (this.f1759d.size() > 0) {
            if (!"com.byd.uereport".equals(this.f1759d.get(0).getPkgName())) {
                this.f1759d.removeIf(new Predicate() { // from class: b.c.a.f.j
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return w.o((NotifyMsgBean) obj);
                    }
                });
                this.g.h(null);
                t.b.f1752a.f1750b.clear();
                this.f1758c.clear();
                u.f1756d.cancelAllNotifications();
                return;
            }
            if (this.f1759d.size() > 1) {
                NotifyMsgBean notifyMsgBean = this.f1759d.get(0);
                this.f1759d.removeIf(new Predicate() { // from class: b.c.a.f.j
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return w.o((NotifyMsgBean) obj);
                    }
                });
                this.g.h(null);
                t.b.f1752a.f1750b.clear();
                this.f1758c.clear();
                ArrayList arrayList = new ArrayList();
                arrayList.add(notifyMsgBean);
                this.f1758c.put(notifyMsgBean.getPkgName(), arrayList);
                t.b.f1752a.f1750b.add(notifyMsgBean);
            }
        }
    }

    public final void r(NotifyMsgBean notifyMsgBean, boolean z) {
        b.c.a.c<Integer> cVar;
        List<NotifyMsgBean> list = this.f1758c.get(notifyMsgBean.getPkgName());
        if (list != null && list.contains(notifyMsgBean)) {
            NotifyMsgBean notifyMsgBean2 = list.get(0);
            if (!z) {
                list.remove(notifyMsgBean);
            } else if (notifyMsgBean2.isExpand.f924b) {
                list.remove(notifyMsgBean);
                if (list.size() > 0) {
                    list.get(0).isExpand.j(true);
                }
            } else {
                Iterator<NotifyMsgBean> it = list.iterator();
                while (it.hasNext()) {
                    t.b.f1752a.f1750b.remove(it.next());
                }
                list.clear();
            }
            if (list.size() == 1) {
                list.get(0).setMultiple(false);
            }
        }
        int iIndexOf = this.f1759d.indexOf(notifyMsgBean);
        this.f1759d.remove(notifyMsgBean);
        if (!z && !notifyMsgBean.isExpand.f924b && list != null && list.size() > 0 && !this.f1759d.contains(list.get(0)) && iIndexOf > -1) {
            this.f1759d.add(iIndexOf, list.get(0));
        }
        if (this.f1759d.size() > iIndexOf) {
            cVar = this.f1761f;
        } else {
            if (this.f1759d.size() != iIndexOf) {
                return;
            }
            cVar = this.f1761f;
            iIndexOf--;
        }
        cVar.i(Integer.valueOf(iIndexOf));
    }

    public void s(NotifyMsgBean notifyMsgBean) {
        b.c.a.h.b.b("removeNotifyMsgFromItemAnimation");
        b.c.a.h.b.a("removeNotifyMsgFromItemAnimation, notification = " + notifyMsgBean.getPkgName() + " : " + notifyMsgBean.getKey());
        r(notifyMsgBean, true);
        t.b.f1752a.f1750b.remove(notifyMsgBean);
        u.f1756d.cancelNotification(notifyMsgBean.getKey());
    }
}
