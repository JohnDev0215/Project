package org.chromium.components.offline_items_collection.bridges;

import defpackage.ny;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public final class OfflineItemBridge {
    @CalledByNative
    public static ArrayList<OfflineItem> createArrayList() {
        return new ArrayList<>();
    }

    @CalledByNative
    public static OfflineItem createOfflineItemAndMaybeAddToList(ArrayList<OfflineItem> arrayList, String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3, boolean z4, long j, boolean z5, long j2, long j3, long j4, boolean z6, String str5, String str6, GURL gurl, GURL gurl2, boolean z7, String str7, int i2, int i3, int i4, boolean z8, boolean z9, long j5, long j6, long j7, int i5, long j8, boolean z10, boolean z11, boolean z12, double d) {
        OfflineItem offlineItem = new OfflineItem();
        ny nyVar = offlineItem.e;
        nyVar.a = str;
        nyVar.b = str2;
        offlineItem.f = str3;
        offlineItem.g = str4;
        offlineItem.h = i;
        offlineItem.i = z;
        offlineItem.j = z2;
        offlineItem.k = z3;
        offlineItem.l = z4;
        offlineItem.o = j;
        offlineItem.p = z5;
        offlineItem.q = j2;
        offlineItem.r = j3;
        offlineItem.s = j4;
        offlineItem.t = z6;
        offlineItem.u = str5;
        offlineItem.v = str6;
        offlineItem.w = gurl;
        offlineItem.x = gurl2;
        offlineItem.y = z7;
        offlineItem.z = str7;
        offlineItem.A = i2;
        offlineItem.H = i3;
        offlineItem.I = i4;
        offlineItem.B = z8;
        offlineItem.C = z9;
        offlineItem.D = j5;
        offlineItem.E = new OfflineItem.a(j6, j7 == -1 ? null : Long.valueOf(j7), i5);
        offlineItem.F = j8;
        offlineItem.G = z10;
        offlineItem.m = z11;
        offlineItem.n = z12;
        if (arrayList != null) {
            arrayList.add(offlineItem);
        }
        return offlineItem;
    }

    @CalledByNative
    public static UpdateDelta createUpdateDelta(boolean z, boolean z2) {
        UpdateDelta updateDelta = new UpdateDelta();
        updateDelta.a = z;
        updateDelta.b = z2;
        return updateDelta;
    }
}
