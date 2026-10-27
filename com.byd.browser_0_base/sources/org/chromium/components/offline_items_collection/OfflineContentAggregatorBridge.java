package org.chromium.components.offline_items_collection;

import J.N;
import android.os.Handler;
import defpackage.ny;
import defpackage.va2;
import defpackage.wq;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.components.offline_items_collection.a;

/* JADX INFO: loaded from: classes3.dex */
public class OfflineContentAggregatorBridge implements a {
    public long a;
    public final b<a.InterfaceC0134a> b;

    public OfflineContentAggregatorBridge(long j) {
        new Handler();
        this.a = j;
        this.b = new b<>();
    }

    @CalledByNative
    public static OfflineContentAggregatorBridge create(long j) {
        return new OfflineContentAggregatorBridge(j);
    }

    @CalledByNative
    public static void onShareInfoAvailable(ShareCallback shareCallback, String str, String str2, OfflineItemShareInfo offlineItemShareInfo) {
        shareCallback.a(offlineItemShareInfo);
    }

    @CalledByNative
    public static void onVisualsAvailable(VisualsCallback visualsCallback, String str, String str2, OfflineItemVisuals offlineItemVisuals) {
        visualsCallback.g(new ny(str, str2), offlineItemVisuals);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void a(ny nyVar) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.Mwk11G0z(j, this, nyVar.a, nyVar.b);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void b(Callback<ArrayList<OfflineItem>> callback) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MWgZa2II(j, this, callback);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void c(ny nyVar) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MGbhWq61(j, this, nyVar.a, nyVar.b);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void d(ny nyVar, ShareCallback shareCallback) {
        N.M8AqLjBj(this.a, this, nyVar.a, nyVar.b, shareCallback);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void e(wq wqVar, ny nyVar) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MXureVYk(j, this, wqVar.a, wqVar.b, nyVar.a, nyVar.b);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void f(ny nyVar, boolean z) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MSy1v2e$(j, this, nyVar.a, nyVar.b, z);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void g(ny nyVar, VisualsCallback visualsCallback) {
        N.MwOuZAaJ(this.a, this, nyVar.a, nyVar.b, visualsCallback);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void h(ny nyVar) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MBvrmOCy(j, this, nyVar.a, nyVar.b);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void i(ny nyVar, String str, va2 va2Var) {
        N.MnGmsa$g(this.a, this, nyVar.a, nyVar.b, str, va2Var);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void j(a.InterfaceC0134a interfaceC0134a) {
        this.b.c(interfaceC0134a);
    }

    @Override // org.chromium.components.offline_items_collection.a
    public final void k(a.InterfaceC0134a interfaceC0134a) {
        this.b.a(interfaceC0134a);
    }

    @CalledByNative
    public final void onItemRemoved(String str, String str2) {
        ny nyVar = new ny(str, str2);
        Iterator<a.InterfaceC0134a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0134a) aVar.next()).e(nyVar);
            }
        }
    }

    @CalledByNative
    public final void onItemUpdated(OfflineItem offlineItem, UpdateDelta updateDelta) {
        Iterator<a.InterfaceC0134a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0134a) aVar.next()).d(offlineItem, updateDelta);
            }
        }
    }

    @CalledByNative
    public final void onItemsAdded(ArrayList<OfflineItem> arrayList) {
        if (arrayList.size() == 0) {
            return;
        }
        Iterator<a.InterfaceC0134a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0134a) aVar.next()).b(arrayList);
            }
        }
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.a = 0L;
    }
}
