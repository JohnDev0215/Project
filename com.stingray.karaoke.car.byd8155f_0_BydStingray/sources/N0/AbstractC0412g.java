package N0;

import java.util.ArrayList;
import java.util.List;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: N0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0412g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0411f f4375a = new C0411f("", null, 6);

    public static final ArrayList a(int i7, int i10, List list) {
        if (i7 > i10) {
            throw new IllegalArgumentException(("start (" + i7 + ") should be less than or equal to end (" + i10 + ')').toString());
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            Object obj = list.get(i11);
            C0409d c0409d = (C0409d) obj;
            if (c(i7, i10, c0409d.f4372b, c0409d.c)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i12 = 0; i12 < size2; i12++) {
            C0409d c0409d2 = (C0409d) arrayList.get(i12);
            arrayList2.add(new C0409d(c0409d2.f4371a, Math.max(i7, c0409d2.f4372b) - i7, Math.min(i10, c0409d2.c) - i7, c0409d2.d));
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    public static final List b(C0411f c0411f, int i7, int i10) {
        List list;
        if (i7 == i10 || (list = c0411f.f4373m) == null) {
            return null;
        }
        if (i7 == 0 && i10 >= c0411f.l.length()) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            Object obj = list.get(i11);
            C0409d c0409d = (C0409d) obj;
            if (c(i7, i10, c0409d.f4372b, c0409d.c)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i12 = 0; i12 < size2; i12++) {
            C0409d c0409d2 = (C0409d) arrayList.get(i12);
            arrayList2.add(new C0409d(AbstractC2673a.v(c0409d2.f4372b, i7, i10) - i7, AbstractC2673a.v(c0409d2.c, i7, i10) - i7, c0409d2.f4371a));
        }
        return arrayList2;
    }

    public static final boolean c(int i7, int i10, int i11, int i12) {
        if (Math.max(i7, i11) < Math.min(i10, i12)) {
            return true;
        }
        if (i7 <= i11 && i12 <= i10) {
            if (i10 != i12) {
                return true;
            }
            if ((i11 == i12) == (i7 == i10)) {
                return true;
            }
        }
        if (i11 <= i7 && i10 <= i12) {
            if (i12 != i10) {
                return true;
            }
            if ((i7 == i10) == (i11 == i12)) {
                return true;
            }
        }
        return false;
    }
}
