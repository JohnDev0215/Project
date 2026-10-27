package defpackage;

import android.text.TextUtils;
import defpackage.f81;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: ListUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a91 {
    public static final int[] a = {0, 2, 3, 4, 1, 6, 5, 7};

    public static boolean a(f81 f81Var) {
        ny nyVar;
        return (f81Var instanceof f81.f) && (nyVar = ((f81.f) f81Var).e.e) != null && TextUtils.equals("content_index", nyVar.a);
    }

    public static int b(f81 f81Var, wc0 wc0Var) {
        if (f81Var instanceof f81.i) {
            return 7;
        }
        if (f81Var instanceof f81.h) {
            return 8;
        }
        if (f81Var instanceof f81.g) {
            return 18;
        }
        if (f81Var instanceof f81.d) {
            return 13;
        }
        if (f81Var instanceof f81.c) {
            return 14;
        }
        if (f81Var instanceof f81.b) {
            int iM = gq2.m(((f81.b) f81Var).d);
            if (iM == 0) {
                return 15;
            }
            if (iM == 1) {
                return 16;
            }
            if (iM == 2) {
                return 17;
            }
        }
        if (!(f81Var instanceof f81.f)) {
            return 2;
        }
        f81.f fVar = (f81.f) f81Var;
        if (fVar.g) {
            return 12;
        }
        OfflineItem offlineItem = fVar.e;
        int i = offlineItem.A;
        boolean z = i == 0 || i == 6 || i == 4 || i == 1 || i == 5;
        if (wc0Var.c) {
            return z ? 1 : 2;
        }
        if (offlineItem.j) {
            int i2 = offlineItem.h;
            if (i2 == 0) {
                return 11;
            }
            if (i2 == 2) {
                return 4;
            }
        }
        int i3 = offlineItem.h;
        if (i3 == 1) {
            return z ? 9 : 3;
        }
        if (i3 != 3) {
            return z ? 1 : 2;
        }
        if (z) {
            return 10;
        }
        return fVar.f ? 6 : 5;
    }

    public static ArrayList c(Set set) {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            f81 f81Var = (f81) it.next();
            if (f81Var instanceof f81.f) {
                arrayList.add(((f81.f) f81Var).e);
            }
        }
        return arrayList;
    }
}
