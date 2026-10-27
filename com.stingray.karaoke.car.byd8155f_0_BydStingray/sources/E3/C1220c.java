package e3;

import android.text.SpannableStringBuilder;
import android.util.Pair;
import g2.C1288a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: renamed from: e3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1220c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14386b;
    public final boolean c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f14387e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1224g f14388f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String[] f14389g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f14390h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f14391i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C1220c f14392j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final HashMap f14393k;
    public final HashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f14394m;

    public C1220c(String str, String str2, long j3, long j10, C1224g c1224g, String[] strArr, String str3, String str4, C1220c c1220c) {
        this.f14385a = str;
        this.f14386b = str2;
        this.f14391i = str4;
        this.f14388f = c1224g;
        this.f14389g = strArr;
        this.c = str2 != null;
        this.d = j3;
        this.f14387e = j10;
        str3.getClass();
        this.f14390h = str3;
        this.f14392j = c1220c;
        this.f14393k = new HashMap();
        this.l = new HashMap();
    }

    public static C1220c a(String str) {
        return new C1220c(null, str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " "), -9223372036854775807L, -9223372036854775807L, null, null, "", null, null);
    }

    public static SpannableStringBuilder e(String str, TreeMap treeMap) {
        if (!treeMap.containsKey(str)) {
            C1288a c1288a = new C1288a();
            c1288a.f14756a = new SpannableStringBuilder();
            treeMap.put(str, c1288a);
        }
        CharSequence charSequence = ((C1288a) treeMap.get(str)).f14756a;
        charSequence.getClass();
        return (SpannableStringBuilder) charSequence;
    }

    public final C1220c b(int i7) {
        ArrayList arrayList = this.f14394m;
        if (arrayList != null) {
            return (C1220c) arrayList.get(i7);
        }
        throw new IndexOutOfBoundsException();
    }

    public final int c() {
        ArrayList arrayList = this.f14394m;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public final void d(TreeSet treeSet, boolean z6) {
        String str = this.f14385a;
        boolean zEquals = "p".equals(str);
        boolean zEquals2 = "div".equals(str);
        if (z6 || zEquals || (zEquals2 && this.f14391i != null)) {
            long j3 = this.d;
            if (j3 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j3));
            }
            long j10 = this.f14387e;
            if (j10 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j10));
            }
        }
        if (this.f14394m == null) {
            return;
        }
        for (int i7 = 0; i7 < this.f14394m.size(); i7++) {
            ((C1220c) this.f14394m.get(i7)).d(treeSet, z6 || zEquals);
        }
    }

    public final boolean f(long j3) {
        long j10 = this.d;
        long j11 = this.f14387e;
        return (j10 == -9223372036854775807L && j11 == -9223372036854775807L) || (j10 <= j3 && j11 == -9223372036854775807L) || ((j10 == -9223372036854775807L && j3 < j11) || (j10 <= j3 && j3 < j11));
    }

    public final void g(long j3, String str, ArrayList arrayList) {
        String str2;
        String str3 = this.f14390h;
        if (!"".equals(str3)) {
            str = str3;
        }
        if (f(j3) && "div".equals(this.f14385a) && (str2 = this.f14391i) != null) {
            arrayList.add(new Pair(str, str2));
            return;
        }
        for (int i7 = 0; i7 < c(); i7++) {
            b(i7).g(j3, str, arrayList);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:142:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(long r20, java.util.Map r22, java.util.HashMap r23, java.lang.String r24, java.util.TreeMap r25) {
        /*
            Method dump skipped, instruction units count: 744
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e3.C1220c.h(long, java.util.Map, java.util.HashMap, java.lang.String, java.util.TreeMap):void");
    }

    public final void i(long j3, boolean z6, String str, TreeMap treeMap) {
        HashMap map = this.f14393k;
        map.clear();
        HashMap map2 = this.l;
        map2.clear();
        String str2 = this.f14385a;
        if ("metadata".equals(str2)) {
            return;
        }
        String str3 = this.f14390h;
        String str4 = "".equals(str3) ? str : str3;
        if (this.c && z6) {
            SpannableStringBuilder spannableStringBuilderE = e(str4, treeMap);
            String str5 = this.f14386b;
            str5.getClass();
            spannableStringBuilderE.append((CharSequence) str5);
            return;
        }
        if ("br".equals(str2) && z6) {
            e(str4, treeMap).append('\n');
            return;
        }
        if (f(j3)) {
            for (Map.Entry entry : treeMap.entrySet()) {
                String str6 = (String) entry.getKey();
                CharSequence charSequence = ((C1288a) entry.getValue()).f14756a;
                charSequence.getClass();
                map.put(str6, Integer.valueOf(charSequence.length()));
            }
            boolean zEquals = "p".equals(str2);
            for (int i7 = 0; i7 < c(); i7++) {
                b(i7).i(j3, z6 || zEquals, str4, treeMap);
            }
            if (zEquals) {
                SpannableStringBuilder spannableStringBuilderE2 = e(str4, treeMap);
                int length = spannableStringBuilderE2.length() - 1;
                while (length >= 0 && spannableStringBuilderE2.charAt(length) == ' ') {
                    length--;
                }
                if (length >= 0 && spannableStringBuilderE2.charAt(length) != '\n') {
                    spannableStringBuilderE2.append('\n');
                }
            }
            for (Map.Entry entry2 : treeMap.entrySet()) {
                String str7 = (String) entry2.getKey();
                CharSequence charSequence2 = ((C1288a) entry2.getValue()).f14756a;
                charSequence2.getClass();
                map2.put(str7, Integer.valueOf(charSequence2.length()));
            }
        }
    }
}
