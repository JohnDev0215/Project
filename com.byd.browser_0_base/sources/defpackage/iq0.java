package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: FrameMetricsStore.java */
/* JADX INFO: loaded from: classes.dex */
public final class iq0 {
    public final Object a = new Object();
    public final ArrayList<Long> b = new ArrayList<>();
    public final ArrayList<Long> c = new ArrayList<>();
    public final ArrayList<Integer> d = new ArrayList<>();
    public final HashMap<Integer, Long> e = new HashMap<>();

    public final void a() {
        int iIndexOf;
        HashMap<Integer, Long> map = this.e;
        boolean zIsEmpty = map.isEmpty();
        ArrayList<Integer> arrayList = this.d;
        ArrayList<Long> arrayList2 = this.c;
        ArrayList<Long> arrayList3 = this.b;
        if (zIsEmpty) {
            arrayList3.clear();
            arrayList2.clear();
            arrayList.clear();
            return;
        }
        Iterator<Long> it = map.values().iterator();
        long j = Long.MAX_VALUE;
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            if (jLongValue < j) {
                j = jLongValue;
            }
        }
        if (j == 0 || (iIndexOf = arrayList3.indexOf(Long.valueOf(j))) == -1) {
            return;
        }
        arrayList3.subList(0, iIndexOf).clear();
        arrayList2.subList(0, iIndexOf).clear();
        arrayList.subList(0, iIndexOf).clear();
    }
}
