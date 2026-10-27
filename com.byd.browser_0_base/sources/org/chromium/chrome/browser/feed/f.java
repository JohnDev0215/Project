package org.chromium.chrome.browser.feed;

import android.view.View;
import defpackage.c81;
import defpackage.d81;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NtpListContentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements c81 {
    public final ArrayList<b> a = new ArrayList<>();
    public final ArrayList<d81> b = new ArrayList<>();
    public final HashMap c = new HashMap();
    public int d;

    /* JADX INFO: compiled from: NtpListContentManager.java */
    public static class a extends b {
        public a(String str) {
            super(str);
        }

        @Override // org.chromium.chrome.browser.feed.f.b
        public final boolean a() {
            return false;
        }
    }

    /* JADX INFO: compiled from: NtpListContentManager.java */
    public static abstract class b {
        public final String a;

        public b(String str) {
            this.a = str;
        }

        public abstract boolean a();
    }

    public final void a(int i, List<b> list) {
        this.a.addAll(i, list);
        Iterator<d81> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().g(i, list.size());
        }
    }

    public final int b(String str) {
        int i = 0;
        while (true) {
            ArrayList<b> arrayList = this.a;
            if (i >= arrayList.size()) {
                return -1;
            }
            if (arrayList.get(i).a.equals(str)) {
                return i;
            }
            i++;
        }
    }

    public final b c(int i) {
        return this.a.get(i);
    }

    public final int d() {
        return this.a.size();
    }

    public final boolean e(ArrayList arrayList, int i, int i2) {
        int i3;
        ArrayList<d81> arrayList2;
        ArrayList<b> arrayList3;
        int i4;
        int i5;
        int i6;
        HashSet hashSet = new HashSet();
        int i7 = 0;
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            hashSet.add(((b) arrayList.get(i8)).a);
        }
        HashMap map = new HashMap();
        int i9 = i;
        while (true) {
            i3 = i + i2;
            if (i9 >= i3) {
                break;
            }
            b bVarC = c(i9);
            map.put(bVarC.a, bVarC);
            i9++;
        }
        int i10 = i3 - 1;
        boolean z = false;
        while (true) {
            arrayList2 = this.b;
            arrayList3 = this.a;
            if (i10 < i) {
                break;
            }
            int i11 = i10;
            while (i11 >= i) {
                String str = c(i11).a;
                if (hashSet.contains(str)) {
                    break;
                }
                map.remove(str);
                i11--;
            }
            if (i11 != i10) {
                int i12 = i11 + 1;
                int i13 = i10 - i11;
                arrayList3.subList(i12, i12 + i13).clear();
                Iterator<d81> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().h(i12, i13);
                }
                z = true;
                i10 = i11;
            } else {
                i10--;
            }
        }
        while (i7 < arrayList.size()) {
            b bVar = (b) arrayList.get(i7);
            if (map.containsKey(bVar.a)) {
                int iB = b(bVar.a);
                int i14 = i7 + i;
                if (iB != i14) {
                    if (iB < i14) {
                        i4 = -1;
                        i6 = iB;
                        i5 = i14;
                    } else if (iB > i14) {
                        i4 = 1;
                        i5 = iB;
                        i6 = i14;
                    }
                    Collections.rotate(arrayList3.subList(i6, i5 + 1), i4);
                    Iterator<d81> it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        it2.next().e(iB, i14);
                    }
                }
                i7++;
                z = true;
            } else {
                int i15 = i7 + 1;
                while (i15 < arrayList.size() && !map.containsKey(((b) arrayList.get(i15)).a)) {
                    i15++;
                }
                a(i7 + i, arrayList.subList(i7, i15));
                z = true;
                i7 = i15;
            }
        }
        return z;
    }

    /* JADX INFO: compiled from: NtpListContentManager.java */
    public static class c extends b {
        public View b;
        public final int c;
        public int d;
        public final int e;

        public c(int i, View view, String str) {
            super(str);
            this.b = view;
            this.e = i;
        }

        @Override // org.chromium.chrome.browser.feed.f.b
        public final boolean a() {
            return true;
        }

        public c(int i, int i2, String str) {
            super(str);
            this.c = i2;
            this.e = i;
        }
    }
}
