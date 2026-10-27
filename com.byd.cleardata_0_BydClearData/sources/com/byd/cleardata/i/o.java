package com.byd.cleardata.i;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class o {

    public static class a implements Comparator<com.byd.cleardata.d.d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f2721b;

        public a(String str) {
            this.f2721b = str;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(com.byd.cleardata.d.d dVar, com.byd.cleardata.d.d dVar2) {
            if (dVar == null || dVar2 == null) {
                return 0;
            }
            String strC = dVar.C();
            String strC2 = dVar2.C();
            int iIndexOf = strC.toLowerCase().indexOf(this.f2721b);
            int iIndexOf2 = strC2.toLowerCase().indexOf(this.f2721b);
            return iIndexOf != iIndexOf2 ? Integer.compare(iIndexOf, iIndexOf2) : Integer.compare(strC.length(), strC2.length());
        }
    }

    public static class b implements Comparator<com.byd.cleardata.d.d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2722b;

        public b(int i) {
            this.f2722b = i;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(com.byd.cleardata.d.d dVar, com.byd.cleardata.d.d dVar2) {
            if (dVar == null || dVar2 == null) {
                return 0;
            }
            return o.a(dVar, dVar2, this.f2722b);
        }
    }

    public static int a(com.byd.cleardata.d.d dVar, com.byd.cleardata.d.d dVar2, int i) {
        if (dVar == null || dVar2 == null) {
            return 0;
        }
        if (i == 0) {
            return b(dVar, dVar2);
        }
        if (i == 1) {
            if (!dVar.N() || !dVar2.N()) {
                return (dVar.N() || dVar2.N()) ? dVar.N() ? 1 : -1 : b(dVar, dVar2);
            }
            long jB = dVar.B();
            long jB2 = dVar2.B();
            return jB == jB2 ? b(dVar, dVar2) : Long.compare(jB2, jB);
        }
        if (i == 2) {
            long jA = dVar.A();
            long jA2 = dVar2.A();
            return jA == jA2 ? b(dVar, dVar2) : Long.compare(jA2, jA);
        }
        if (i != 3) {
            return 0;
        }
        int iOrdinal = dVar.G().ordinal();
        int iOrdinal2 = dVar2.G().ordinal();
        return iOrdinal == iOrdinal2 ? b(dVar, dVar2) : Integer.compare(iOrdinal, iOrdinal2);
    }

    private static int b(com.byd.cleardata.d.d dVar, com.byd.cleardata.d.d dVar2) {
        if (dVar == null || dVar2 == null) {
            return 0;
        }
        return c(dVar.C(), dVar2.C());
    }

    private static int c(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        return Collator.getInstance(Locale.CHINA).compare(str.toLowerCase(), str2.toLowerCase());
    }

    public static void d(int i, List<com.byd.cleardata.d.d> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        list.sort(new b(i));
    }

    public static void e(List<com.byd.cleardata.d.d> list) {
        d(com.byd.cleardata.b.h().p(), list);
    }
}
