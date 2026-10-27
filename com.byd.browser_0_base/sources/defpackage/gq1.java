package defpackage;

import defpackage.dy2;
import java.util.HashMap;
import java.util.List;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.VisualsCallback;
import org.chromium.components.offline_items_collection.a;

/* JADX INFO: compiled from: OfflineContentAggregatorNotificationBridgeUi.java */
/* JADX INFO: loaded from: classes.dex */
public final class gq1 implements kd0, a.InterfaceC0134a, VisualsCallback {
    public static final OfflineItemVisuals i = new OfflineItemVisuals();
    public final a e;
    public final hd0 f;
    public final HashMap<ny, OfflineItem> g = new HashMap<>();
    public final HashMap<ny, OfflineItemVisuals> h = new HashMap<>();

    public gq1(a aVar, hd0 hd0Var) {
        this.e = aVar;
        this.f = hd0Var;
        aVar.k(this);
    }

    @Override // defpackage.kd0
    public final void a(ny nyVar, OTRProfileID oTRProfileID) {
        this.e.h(nyVar);
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void b(List<OfflineItem> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            i(list.get(i2), null);
        }
    }

    @Override // defpackage.kd0
    public final void c(ny nyVar, OTRProfileID oTRProfileID) {
        this.e.a(nyVar);
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void d(OfflineItem offlineItem, UpdateDelta updateDelta) {
        i(offlineItem, updateDelta);
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void e(ny nyVar) {
        this.g.remove(nyVar);
        this.h.remove(nyVar);
        ((dy2) this.f).c(nyVar);
    }

    @Override // org.chromium.components.offline_items_collection.VisualsCallback
    public final void g(ny nyVar, OfflineItemVisuals offlineItemVisuals) {
        int i2;
        OfflineItem offlineItemRemove = this.g.remove(nyVar);
        if (offlineItemRemove == null) {
            return;
        }
        if (offlineItemVisuals == null) {
            offlineItemVisuals = i;
        }
        boolean z = false;
        if (!offlineItemRemove.n && ((i2 = offlineItemRemove.A) == 0 || i2 == 1 || i2 == 2 || i2 == 4 || i2 == 6)) {
            z = true;
        }
        if (z) {
            this.h.put(nyVar, offlineItemVisuals);
        }
        j(offlineItemRemove, offlineItemVisuals);
    }

    @Override // defpackage.kd0
    public final void h(ny nyVar, DownloadItem downloadItem, boolean z) {
        this.e.f(nyVar, z);
    }

    public final void i(OfflineItem offlineItem, UpdateDelta updateDelta) {
        int i2;
        int i3;
        boolean z = false;
        if ((offlineItem.A != 2 || updateDelta == null || updateDelta.a || updateDelta.b) ? false : true) {
            return;
        }
        HashMap<ny, OfflineItemVisuals> map = this.h;
        if (updateDelta != null && updateDelta.b) {
            map.remove(offlineItem.e);
        }
        boolean z2 = !offlineItem.n && ((i3 = offlineItem.A) == 0 || i3 == 1 || i3 == 2 || i3 == 4 || i3 == 5 || i3 == 6);
        HashMap<ny, OfflineItem> map2 = this.g;
        if (!z2) {
            map2.remove(offlineItem.e);
            map.remove(offlineItem.e);
        } else if (!map.containsKey(offlineItem.e)) {
            boolean z3 = !map2.containsKey(offlineItem.e);
            map2.put(offlineItem.e, offlineItem);
            if (z3) {
                this.e.g(offlineItem.e, this);
                return;
            }
            return;
        }
        j(offlineItem, map.get(offlineItem.e));
        if (!offlineItem.n && ((i2 = offlineItem.A) == 0 || i2 == 1 || i2 == 2 || i2 == 4 || i2 == 6)) {
            z = true;
        }
        if (z) {
            return;
        }
        map.remove(offlineItem.e);
    }

    public final void j(OfflineItem offlineItem, OfflineItemVisuals offlineItemVisuals) {
        if (offlineItem.j) {
            return;
        }
        int i2 = offlineItem.A;
        int i3 = 3;
        hd0 hd0Var = this.f;
        if (i2 == 3) {
            ((dy2) hd0Var).c(offlineItem.e);
            return;
        }
        vd1.j.E();
        int i4 = offlineItem.A;
        if (i4 == 2) {
            i3 = 1;
        } else if (i4 == 3) {
            i3 = 2;
        } else if (i4 != 4 && i4 != 5) {
            i3 = 0;
        }
        DownloadInfo.a aVar = new DownloadInfo.a();
        ny nyVar = offlineItem.e;
        aVar.z = nyVar;
        aVar.m = nyVar.b;
        aVar.e = offlineItem.f;
        aVar.g = offlineItem.u;
        aVar.f = offlineItem.g;
        aVar.B = offlineItem.i;
        aVar.x = offlineItem.s;
        aVar.A = offlineItem.t;
        aVar.c = offlineItem.v;
        aVar.a = offlineItem.w;
        aVar.i = offlineItem.x;
        aVar.c(OTRProfileID.a(offlineItem.z));
        aVar.w = i3;
        aVar.s = offlineItem.A == 6;
        aVar.r = offlineItem.B;
        aVar.j = offlineItem.D;
        aVar.k = offlineItem.o;
        aVar.p = offlineItem.E;
        aVar.q = offlineItem.F;
        aVar.y = offlineItem.G;
        aVar.C = offlineItem.k;
        aVar.D = offlineItemVisuals == null ? null : offlineItemVisuals.a;
        aVar.E = offlineItem.I;
        aVar.F = offlineItem.H;
        aVar.G = offlineItem.l;
        DownloadInfo downloadInfo = new DownloadInfo(aVar);
        int i5 = offlineItem.A;
        if (i5 == 0) {
            dy2 dy2Var = (dy2) hd0Var;
            dy2Var.getClass();
            dy2Var.a(new dy2.a(0, downloadInfo, 1));
            return;
        }
        if (i5 == 1) {
            dy2 dy2Var2 = (dy2) hd0Var;
            dy2Var2.getClass();
            dy2Var2.a(new dy2.a(1, downloadInfo, 0));
            return;
        }
        if (i5 == 2) {
            ((dy2) hd0Var).e(downloadInfo, -1L, false, offlineItem.t);
            return;
        }
        if (i5 != 4) {
            if (i5 == 5) {
                ((dy2) hd0Var).d(downloadInfo);
                return;
            } else {
                if (i5 != 6) {
                    return;
                }
                dy2 dy2Var3 = (dy2) hd0Var;
                dy2Var3.getClass();
                dy2Var3.a(new dy2.a(1, downloadInfo, 0));
                return;
            }
        }
        boolean z = !n3.N0(offlineItem.e);
        int i6 = offlineItem.I;
        dy2 dy2Var4 = (dy2) hd0Var;
        dy2Var4.getClass();
        dy2.a aVar2 = new dy2.a(4, downloadInfo, 0);
        aVar2.f = z;
        aVar2.g = i6;
        dy2Var4.a(aVar2);
    }

    @Override // defpackage.kd0
    public final void f() {
    }
}
