package org.chromium.chrome.browser.ntp;

import J.N;
import defpackage.g03;
import defpackage.p13;
import defpackage.s92;
import defpackage.t92;
import defpackage.u92;
import defpackage.v92;
import defpackage.xz;
import defpackage.y13;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class RecentlyClosedBridge {
    public long a;
    public final y13 b;
    public Runnable c;

    public RecentlyClosedBridge(Profile profile, y13 y13Var) {
        this.a = N.Mlookj5S(this, profile);
        this.b = y13Var;
    }

    @CalledByNative
    public static void addBulkEventToEntries(List<t92> list, int i, long j, String[] strArr, String[] strArr2, int[] iArr, long[] jArr, String[] strArr3, GURL[] gurlArr, String[] strArr4) {
        s92 s92Var = new s92(i, j);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            s92Var.d.put(strArr[i2], strArr2[i2]);
        }
        ArrayList arrayList = s92Var.c;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int i4 = iArr[i3];
            long j2 = jArr[i3];
            String str = strArr3[i3];
            GURL gurl = gurlArr[i3];
            String str2 = strArr4[i3];
            arrayList.add(new v92(j2, i4, str, gurl));
        }
        list.add(s92Var);
    }

    @CalledByNative
    public static void addGroupToEntries(List<t92> list, int i, long j, String str, int[] iArr, long[] jArr, String[] strArr, GURL[] gurlArr, String[] strArr2) {
        u92 u92Var = new u92(i, str, j);
        ArrayList arrayList = u92Var.d;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            long j2 = jArr[i2];
            String str2 = strArr[i2];
            GURL gurl = gurlArr[i2];
            String str3 = strArr2[i2];
            arrayList.add(new v92(j2, i3, str2, gurl));
        }
        list.add(u92Var);
    }

    @CalledByNative
    public static void addTabToEntries(List<t92> list, int i, long j, String str, GURL gurl, String str2) {
        list.add(new v92(j, i, str, gurl));
    }

    @CalledByNative
    public final void onUpdated() {
        Runnable runnable = this.c;
        if (runnable != null) {
            runnable.run();
        }
    }

    @CalledByNative
    public final void restoreTabGroup(TabModel tabModel, int i, String str, int[] iArr) {
        if (iArr.length < 1) {
            return;
        }
        p13 p13VarD = this.b.u().d(tabModel.isIncognito());
        if (p13VarD instanceof g03) {
            g03 g03Var = (g03) p13VarD;
            for (int i2 : iArr) {
                if (i2 != i) {
                    g03Var.S(i2, i, false);
                }
            }
            if (str == null || str.isEmpty()) {
                return;
            }
            xz.a.getSharedPreferences("tab_group_titles", 0).edit().putString(String.valueOf(i), str).apply();
        }
    }
}
