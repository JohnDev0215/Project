package org.chromium.chrome.browser.download;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import defpackage.bd0;
import defpackage.dd0;
import defpackage.g3;
import defpackage.n3;
import defpackage.ny;
import defpackage.oj2;
import defpackage.vg1;
import defpackage.xg1;
import defpackage.yc0;
import defpackage.z8;
import defpackage.zc0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DownloadMessageUiControllerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements a.InterfaceC0134a {
    public final Handler e;
    public final LinkedHashMap<ny, OfflineItem> f;
    public final HashSet g;
    public final HashSet h;
    public final HashSet i;
    public final HashSet j;
    public final HashMap k;
    public int l;
    public bd0 m;
    public b n;
    public final yc0 o;
    public PropertyModel p;
    public z8 q;

    /* JADX INFO: compiled from: DownloadMessageUiControllerImpl.java */
    public static class a {
        public int a;
        public int b;
        public int c;
        public int d;

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b && this.c == aVar.c && this.d == aVar.d;
        }

        public final int hashCode() {
            return (((((this.a * 31 * 31) + this.b) * 31) + this.c) * 31) + this.d;
        }
    }

    /* JADX INFO: compiled from: DownloadMessageUiControllerImpl.java */
    public static class b {
        public ny a;
        public String b;
        public String c;
        public String d;
        public int e;
        public boolean g;
        public int i;
        public int f = 0;
        public a h = new a();

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            ny nyVar = this.a;
            return (nyVar == null ? bVar.a == null : nyVar.equals(bVar.a)) && TextUtils.equals(this.b, bVar.b) && TextUtils.equals(this.c, bVar.c) && TextUtils.equals(this.d, bVar.d) && this.e == bVar.e;
        }

        public final int hashCode() {
            ny nyVar = this.a;
            int iHashCode = (nyVar == null ? 0 : nyVar.hashCode()) * 31;
            String str = this.b;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.d;
            return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.e;
        }
    }

    public e(dd0 dd0Var) {
        Handler handler = new Handler();
        this.e = handler;
        this.f = new LinkedHashMap<>();
        this.g = new HashSet();
        this.h = new HashSet();
        this.i = new HashSet();
        this.j = new HashSet();
        this.k = new HashMap();
        this.l = 0;
        this.o = dd0Var;
        handler.post(new zc0(0, this));
    }

    public static int f(OfflineItem offlineItem) {
        int i = offlineItem.A;
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 5 ? -1 : 1;
        }
        return 0;
    }

    public static boolean j(OfflineItem offlineItem) {
        if (offlineItem.i || offlineItem.j || offlineItem.G) {
            return false;
        }
        return ((n3.N0(offlineItem.e) && TextUtils.isEmpty(offlineItem.u)) || MimeUtils.canAutoOpenMimeType(offlineItem.v)) ? false : true;
    }

    public static void k(int i) {
        oj2.U(i, 6, "Download.Incognito.Message");
    }

    public final void a(Integer... numArr) {
        HashSet hashSet = new HashSet(Arrays.asList(numArr));
        ArrayList<ny> arrayList = new ArrayList();
        LinkedHashMap<ny, OfflineItem> linkedHashMap = this.f;
        for (ny nyVar : linkedHashMap.keySet()) {
            OfflineItem offlineItem = linkedHashMap.get(nyVar);
            if (offlineItem != null) {
                Iterator it = hashSet.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((Integer) it.next()).intValue() == f(offlineItem)) {
                            arrayList.add(nyVar);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        for (ny nyVar2 : arrayList) {
            linkedHashMap.remove(nyVar2);
            this.k.remove(nyVar2);
        }
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void b(List<OfflineItem> list) {
        for (OfflineItem offlineItem : list) {
            if (j(offlineItem)) {
                c(offlineItem, false, false, false);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00d9  */
    /* JADX WARN: Type inference failed for: r1v41, types: [bd0, java.lang.Runnable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(org.chromium.components.offline_items_collection.OfflineItem r18, boolean r19, boolean r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 1024
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.e.c(org.chromium.components.offline_items_collection.OfflineItem, boolean, boolean, boolean):void");
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void d(OfflineItem offlineItem, UpdateDelta updateDelta) {
        HashSet hashSet = this.j;
        boolean zContains = hashSet.contains(offlineItem.x);
        HashSet hashSet2 = this.i;
        if (zContains) {
            hashSet.remove(offlineItem.x);
            hashSet2.add(offlineItem.e);
        }
        if (offlineItem.A == 2) {
            hashSet2.remove(offlineItem.e);
        }
        if (j(offlineItem)) {
            if (updateDelta == null || updateDelta.a || offlineItem.A != 2) {
                if (offlineItem.A == 3) {
                    e(offlineItem.e);
                } else {
                    c(offlineItem, false, false, false);
                }
            }
        }
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void e(ny nyVar) {
        if (this.g.contains(nyVar)) {
            this.f.remove(nyVar);
            this.k.remove(nyVar);
            c(null, false, false, true);
        }
    }

    public final Context g() {
        return ((dd0) this.o).a.get();
    }

    public final a h() {
        a aVar = new a();
        Iterator<OfflineItem> it = this.f.values().iterator();
        while (it.hasNext()) {
            int i = it.next().A;
            if (i == 0) {
                aVar.a++;
            } else if (i == 1) {
                aVar.b++;
            } else if (i == 2) {
                aVar.d++;
            } else if (i != 3 && i == 5) {
                aVar.c++;
            }
        }
        return aVar;
    }

    public final vg1 i() {
        g3 g3Var;
        ChromeActivity chromeActivity = ((dd0) this.o).a.get();
        if (chromeActivity == null || (g3Var = chromeActivity.O) == null) {
            return null;
        }
        return xg1.a(g3Var);
    }
}
