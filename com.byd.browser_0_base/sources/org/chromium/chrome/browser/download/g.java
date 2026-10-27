package org.chromium.chrome.browser.download;

import defpackage.C0173a;
import defpackage.gq2;
import defpackage.ny;
import java.util.ArrayList;
import java.util.HashSet;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* JADX INFO: compiled from: DownloadSharedPreferenceHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public final ArrayList a = new ArrayList();
    public final org.chromium.base.b<b> b = new org.chromium.base.b<>();
    public final SharedPreferencesManager c;

    /* JADX INFO: compiled from: DownloadSharedPreferenceHelper.java */
    public static class a {
        public static final g a = new g();
    }

    /* JADX INFO: compiled from: DownloadSharedPreferenceHelper.java */
    public interface b {
        void b();
    }

    public g() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.c = sharedPreferencesManager;
        if (sharedPreferencesManager.contains("PendingDownloadNotifications")) {
            HashSet hashSet = DownloadManagerService.r;
            for (String str : new HashSet(sharedPreferencesManager.h("PendingDownloadNotifications"))) {
                if (f.b(str).a > 0) {
                    this.a.add(f.b(str));
                }
            }
        }
    }

    public final f a(ny nyVar) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return null;
            }
            if (((f) arrayList.get(i)).f.equals(nyVar)) {
                return (f) arrayList.get(i);
            }
            i++;
        }
    }

    public final void b(boolean z) {
        HashSet hashSet = new HashSet();
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                DownloadManagerService.o(this.c, "PendingDownloadNotifications", hashSet, z);
                return;
            }
            f fVar = (f) arrayList.get(i);
            StringBuilder sbJ = C0173a.j("7," + fVar.a + ",");
            ny nyVar = fVar.f;
            StringBuilder sbJ2 = C0173a.j(gq2.i(C0173a.j(gq2.i(sbJ, nyVar.a, ",")), nyVar.b, ","));
            OTRProfileID oTRProfileID = fVar.b;
            String str = "1";
            StringBuilder sbJ3 = C0173a.j(gq2.i(C0173a.j(gq2.i(C0173a.j(gq2.i(sbJ2, oTRProfileID != null ? OTRProfileID.serialize(oTRProfileID) : "", ",")), fVar.c ? "1" : "0", ",")), fVar.e ? "1" : "0", ","));
            if (!fVar.g) {
                str = "0";
            }
            StringBuilder sbJ4 = C0173a.j(gq2.i(sbJ3, str, ","));
            sbJ4.append(fVar.d);
            hashSet.add(sbJ4.toString());
            i++;
        }
    }
}
