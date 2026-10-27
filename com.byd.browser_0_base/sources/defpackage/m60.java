package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import defpackage.g60;
import defpackage.t81;
import java.io.Closeable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DateOrderedListMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class m60 implements vb {
    public final Handler e = new Handler();
    public final org.chromium.components.offline_items_collection.a f;
    public final pk0 g;
    public final f h;
    public final g81 i;
    public final g60.b j;
    public final e k;
    public final sq1 l;
    public final r81 m;
    public final t93 n;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<f81> o;
    public final wc0 p;
    public final k80 q;
    public final eh3 r;
    public final lg2 s;
    public final aq1<Boolean> t;

    /* JADX INFO: compiled from: DateOrderedListMediator.java */
    public class a implements Closeable {
        public a() {
            m60.this.i.h.l(t81.m, false);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            m60.this.e.post(new zc0(1, this));
        }
    }

    /* JADX INFO: compiled from: DateOrderedListMediator.java */
    public static class b implements qq1 {
        public Boolean e;
        public final g60.a f;
        public final pq1 g;

        public b(lg2 lg2Var, tc0 tc0Var) {
            this.g = lg2Var;
            this.f = tc0Var;
            new Handler().post(new l10(9, this));
        }

        public final void a() {
            Boolean boolValueOf = Boolean.valueOf(this.g.f.isEmpty());
            if (boolValueOf.equals(this.e)) {
                return;
            }
            this.e = boolValueOf;
            boolean zBooleanValue = boolValueOf.booleanValue();
            ac3 ac3Var = ((tc0) this.f).a.d;
            if (ac3Var == null) {
                return;
            }
            ac3Var.h.setSearchEnabled(!zBooleanValue);
        }

        @Override // defpackage.qq1
        public final void b(Collection<OfflineItem> collection) {
            a();
        }

        @Override // defpackage.qq1
        public final void c() {
            a();
        }

        @Override // defpackage.qq1
        public final void d(Collection<OfflineItem> collection) {
            a();
        }

        @Override // defpackage.qq1
        public final void e(OfflineItem offlineItem, OfflineItem offlineItem2) {
            a();
        }
    }

    /* JADX INFO: compiled from: DateOrderedListMediator.java */
    public class c implements a.InterfaceC0128a<f81> {
        public final org.chromium.components.browser_ui.widget.selectable_list.a<f81> e;

        public c(org.chromium.components.browser_ui.widget.selectable_list.a<f81> aVar) {
            this.e = aVar;
            aVar.a(this);
        }

        @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
        public final void f(ArrayList arrayList) {
            int i = 0;
            while (true) {
                m60 m60Var = m60.this;
                int size = m60Var.i.size();
                g81 g81Var = m60Var.i;
                org.chromium.components.browser_ui.widget.selectable_list.a<f81> aVar = this.e;
                if (i >= size) {
                    g81Var.g.d();
                    boolean zE = aVar.e();
                    g81Var.h.l(t81.x, zE);
                    m60Var.t.r(Boolean.valueOf(zE));
                    return;
                }
                f81 f81Var = g81Var.get(i);
                boolean zD = aVar.d(f81Var);
                f81Var.c = zD && !f81Var.b;
                f81Var.b = zD;
                g81Var.f.set(i, f81Var);
                g81Var.q(i, 1, null);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: DateOrderedListMediator.java */
    @FunctionalInterface
    public interface d {
    }

    /* JADX INFO: compiled from: DateOrderedListMediator.java */
    @FunctionalInterface
    public interface e {
    }

    /* JADX INFO: compiled from: DateOrderedListMediator.java */
    @FunctionalInterface
    public interface f {
    }

    public m60(org.chromium.components.offline_items_collection.a aVar, rk0 rk0Var, gx gxVar, pq2 pq2Var, c01 c01Var, org.chromium.components.browser_ui.widget.selectable_list.a aVar2, wc0 wc0Var, tc0 tc0Var, g81 g81Var, na0 na0Var) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.t = aq1Var;
        this.f = aVar;
        this.g = rk0Var;
        this.h = gxVar;
        this.i = g81Var;
        this.j = pq2Var;
        this.k = c01Var;
        this.o = aVar2;
        this.p = wc0Var;
        sq1 sq1Var = new sq1(aVar);
        this.l = sq1Var;
        OTRProfileID oTRProfileID = OTRProfileID.b;
        final int i = 1;
        final int i2 = 0;
        o21 o21Var = new o21(new cq1(wc0Var.a != null, sq1Var));
        k80 k80Var = new k80(o21Var);
        this.q = k80Var;
        lg2 lg2Var = new lg2(k80Var);
        this.s = lg2Var;
        eh3 eh3Var = new eh3(lg2Var);
        this.r = eh3Var;
        um0 um0Var = new um0(wc0Var);
        r81 r81Var = new r81(wc0Var, um0Var, new n60(eh3Var, g81Var, um0Var), g81Var);
        this.m = r81Var;
        new tq1(wc0Var, o21Var);
        lg2Var.a(new b(lg2Var, tc0Var));
        this.n = new t93(na0Var);
        new c(aVar2);
        PropertyModel propertyModel = g81Var.h;
        propertyModel.l(t81.m, true);
        propertyModel.p(t81.n, new Callback(this) { // from class: h60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i2;
                m60 m60Var = this.f;
                switch (i3) {
                    case 0:
                        OfflineItem offlineItem = (OfflineItem) obj;
                        m60Var.getClass();
                        oj2.V(0);
                        wq wqVar = new wq(0);
                        OTRProfileID oTRProfileID2 = m60Var.p.a;
                        OTRProfileID oTRProfileID3 = OTRProfileID.b;
                        wqVar.b = oTRProfileID2 != null;
                        m60Var.f.e(wqVar, offlineItem.e);
                        break;
                    default:
                        m60Var.getClass();
                        oj2.V(1);
                        m60Var.f.f(((OfflineItem) obj).e, true);
                        break;
                }
            }
        });
        propertyModel.p(t81.o, new Callback(this) { // from class: i60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i2;
                m60 m60Var = this.f;
                switch (i3) {
                    case 0:
                        m60Var.getClass();
                        oj2.V(2);
                        m60Var.f.h(((OfflineItem) obj).e);
                        break;
                    case 1:
                        m60Var.getClass();
                        oj2.V(5);
                        m60Var.d(Collections.singletonList((OfflineItem) obj));
                        break;
                    default:
                        OfflineItem offlineItem = (OfflineItem) obj;
                        m60Var.getClass();
                        oj2.V(7);
                        String str = offlineItem.f;
                        z70 z70Var = new z70(m60Var, 6, offlineItem);
                        g60 g60Var = (g60) ((c01) m60Var.k).f;
                        g60Var.getClass();
                        ke0 ke0Var = new ke0(12, z70Var);
                        wa2 wa2Var = g60Var.d;
                        wa2Var.f = ke0Var;
                        wa2Var.c = str;
                        wa2Var.d = str;
                        wa2Var.e = 0;
                        wa2Var.g = 0;
                        wa2Var.a(1, 3);
                        break;
                }
            }
        });
        propertyModel.p(t81.p, new Callback(this) { // from class: h60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i;
                m60 m60Var = this.f;
                switch (i3) {
                    case 0:
                        OfflineItem offlineItem = (OfflineItem) obj;
                        m60Var.getClass();
                        oj2.V(0);
                        wq wqVar = new wq(0);
                        OTRProfileID oTRProfileID2 = m60Var.p.a;
                        OTRProfileID oTRProfileID3 = OTRProfileID.b;
                        wqVar.b = oTRProfileID2 != null;
                        m60Var.f.e(wqVar, offlineItem.e);
                        break;
                    default:
                        m60Var.getClass();
                        oj2.V(1);
                        m60Var.f.f(((OfflineItem) obj).e, true);
                        break;
                }
            }
        });
        propertyModel.p(t81.q, new xr(16, this));
        propertyModel.p(t81.r, new Callback(this) { // from class: j60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i2;
                m60 m60Var = this.f;
                switch (i3) {
                    case 0:
                        m60Var.getClass();
                        oj2.V(4);
                        m60Var.e(CollectionUtil.e((OfflineItem) obj));
                        break;
                    default:
                        m60Var.o.i((f81) obj);
                        break;
                }
            }
        });
        propertyModel.p(t81.s, new Callback(this) { // from class: i60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i;
                m60 m60Var = this.f;
                switch (i3) {
                    case 0:
                        m60Var.getClass();
                        oj2.V(2);
                        m60Var.f.h(((OfflineItem) obj).e);
                        break;
                    case 1:
                        m60Var.getClass();
                        oj2.V(5);
                        m60Var.d(Collections.singletonList((OfflineItem) obj));
                        break;
                    default:
                        OfflineItem offlineItem = (OfflineItem) obj;
                        m60Var.getClass();
                        oj2.V(7);
                        String str = offlineItem.f;
                        z70 z70Var = new z70(m60Var, 6, offlineItem);
                        g60 g60Var = (g60) ((c01) m60Var.k).f;
                        g60Var.getClass();
                        ke0 ke0Var = new ke0(12, z70Var);
                        wa2 wa2Var = g60Var.d;
                        wa2Var.f = ke0Var;
                        wa2Var.c = str;
                        wa2Var.d = str;
                        wa2Var.e = 0;
                        wa2Var.g = 0;
                        wa2Var.a(1, 3);
                        break;
                }
            }
        });
        propertyModel.p(t81.u, new t81.b() { // from class: k60
            @Override // t81.b
            public final Runnable a(OfflineItem offlineItem, int i3, int i4, zo1 zo1Var) {
                m60 m60Var = this.a;
                m60Var.getClass();
                int i5 = vh3.a;
                int i6 = offlineItem.h;
                boolean z = true;
                if (i6 != 0 && i6 != 1 && i6 != 2 && i6 != 3) {
                    z = false;
                }
                if (!z || i3 == 0 || i4 == 0) {
                    m60Var.e.post(new l91(zo1Var, 14, offlineItem));
                    return new qe3(8);
                }
                org.chromium.components.offline_items_collection.a aVar3 = m60Var.f;
                m60Var.p.getClass();
                u93 u93Var = new u93(aVar3, offlineItem, i3, i4, zo1Var);
                t93 t93Var = m60Var.n;
                t93Var.getClass();
                Object obj = ThreadUtils.a;
                if (!TextUtils.isEmpty(u93Var.c())) {
                    if (t93Var.c.b(u93Var.c()) != null) {
                        u93Var.c();
                        u93Var.a(null);
                    } else {
                        Bitmap bitmapA = t93Var.a(i3, u93Var.c());
                        if (bitmapA != null) {
                            u93Var.c();
                            u93Var.a(bitmapA);
                        } else {
                            t93Var.d.offer(u93Var);
                            PostTask.c(rh3.a, new eu1(26, t93Var));
                        }
                    }
                }
                return new a9(m60Var, 5, u93Var);
            }
        });
        propertyModel.p(t81.v, new t81.a() { // from class: l60
            @Override // t81.a
            public final void a(int i3, String str, n62 n62Var) {
                m60 m60Var = this.a;
                m60Var.getClass();
                ty2 ty2Var = new ty2(n62Var, 1);
                rk0 rk0Var2 = (rk0) m60Var.g;
                Bitmap bitmap = rk0Var2.c.a.get(str);
                if (bitmap != null) {
                    ty2Var.onResult(bitmap);
                } else {
                    rk0Var2.b.a(rk0Var2.a, str, i3, new qk0(rk0Var2, 0, str, ty2Var));
                }
            }
        });
        propertyModel.p(t81.w, new Callback(this) { // from class: j60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i3 = i;
                m60 m60Var = this.f;
                switch (i3) {
                    case 0:
                        m60Var.getClass();
                        oj2.V(4);
                        m60Var.e(CollectionUtil.e((OfflineItem) obj));
                        break;
                    default:
                        m60Var.o.i((f81) obj);
                        break;
                }
            }
        });
        final int i3 = 2;
        propertyModel.p(t81.t, new Callback(this) { // from class: i60
            public final /* synthetic */ m60 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i32 = i3;
                m60 m60Var = this.f;
                switch (i32) {
                    case 0:
                        m60Var.getClass();
                        oj2.V(2);
                        m60Var.f.h(((OfflineItem) obj).e);
                        break;
                    case 1:
                        m60Var.getClass();
                        oj2.V(5);
                        m60Var.d(Collections.singletonList((OfflineItem) obj));
                        break;
                    default:
                        OfflineItem offlineItem = (OfflineItem) obj;
                        m60Var.getClass();
                        oj2.V(7);
                        String str = offlineItem.f;
                        z70 z70Var = new z70(m60Var, 6, offlineItem);
                        g60 g60Var = (g60) ((c01) m60Var.k).f;
                        g60Var.getClass();
                        ke0 ke0Var = new ke0(12, z70Var);
                        wa2 wa2Var = g60Var.d;
                        wa2Var.f = ke0Var;
                        wa2Var.c = str;
                        wa2Var.d = str;
                        wa2Var.e = 0;
                        wa2Var.g = 0;
                        wa2Var.a(1, 3);
                        break;
                }
            }
        });
        propertyModel.p(t81.y, new aw(27, r81Var));
        propertyModel.p(t81.z, new so(26, r81Var));
        aq1Var.r(Boolean.valueOf(aVar2.e()));
    }

    public final void d(List<OfflineItem> list) {
        Collection<OfflineItem> collectionH = this.l.h();
        HashSet hashSet = new HashSet();
        for (OfflineItem offlineItem : list) {
            if (!TextUtils.isEmpty(offlineItem.u)) {
                hashSet.add(offlineItem.u);
            }
        }
        HashSet hashSet2 = new HashSet(list);
        for (OfflineItem offlineItem2 : collectionH) {
            if (hashSet.contains(offlineItem2.u)) {
                hashSet2.add(offlineItem2);
            }
        }
        k80 k80Var = this.q;
        k80Var.getClass();
        Iterator it = hashSet2.iterator();
        while (it.hasNext()) {
            k80Var.h.add(((OfflineItem) it.next()).e);
        }
        k80Var.k();
        xm1 xm1Var = new xm1(this, 2, hashSet2);
        j80 j80Var = (j80) ((pq2) this.j).f;
        j80Var.getClass();
        tp2 tp2VarA = tp2.a(n3.z0(list), j80Var.b, 0, 13);
        tp2VarA.d = xz.a.getString(R$string.undo);
        tp2VarA.e = xm1Var;
        Context context = xz.a;
        tp2VarA.c = list.size() == 1 ? context.getString(R$string.delete_message) : context.getString(R$string.undo_bar_multiple_downloads_delete_message);
        String strZ0 = n3.z0(list);
        Context context2 = xz.a;
        tp2VarA.f = list.size() == 1 ? context2.getString(R$string.undo_bar_delete_restore_accessibility_message, strZ0) : context2.getString(R$string.undo_bar_multiple_downloads_delete_restore_accessibility_message, strZ0);
        j80Var.a.c(tp2VarA);
    }

    public final void e(AbstractCollection abstractCollection) {
        Iterator it = abstractCollection.iterator();
        while (it.hasNext()) {
            OfflineItem offlineItem = (OfflineItem) it.next();
            if (offlineItem.h == 0) {
                n3.X0("OfflinePages.Sharing.SharePageFromDownloadHome");
            }
            oj2.U(z7.l(offlineItem).intValue(), 8, "Android.DownloadManager.Share.FileTypes");
        }
        oj2.W(abstractCollection.size(), 1, 20, 20, "Android.DownloadManager.Share.Count");
        ShareHelper.c(0);
        ArrayList arrayList = new ArrayList();
        Iterator it2 = abstractCollection.iterator();
        while (it2.hasNext()) {
            OfflineItem offlineItem2 = (OfflineItem) it2.next();
            this.f.d(offlineItem2.e, new a80(this, arrayList, offlineItem2, abstractCollection));
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.t;
    }

    @Override // defpackage.vb
    public final int o() {
        int i;
        org.chromium.components.browser_ui.widget.selectable_list.a<f81> aVar = this.o;
        if (aVar.e()) {
            aVar.b();
            i = 1;
        } else {
            i = 0;
        }
        return i ^ 1;
    }
}
