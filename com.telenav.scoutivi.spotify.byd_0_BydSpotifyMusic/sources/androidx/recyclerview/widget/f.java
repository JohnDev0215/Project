package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DiffUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Comparator<C0029f> f3080a = new a();

    /* JADX INFO: compiled from: DiffUtil.java */
    static class a implements Comparator<C0029f> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(C0029f c0029f, C0029f c0029f2) {
            int i7 = c0029f.f3095a - c0029f2.f3095a;
            return i7 == 0 ? c0029f.f3096b - c0029f2.f3096b : i7;
        }
    }

    /* JADX INFO: compiled from: DiffUtil.java */
    public static abstract class b {
        public abstract boolean a(int i7, int i8);

        public abstract boolean b(int i7, int i8);

        public Object c(int i7, int i8) {
            return null;
        }

        public abstract int d();

        public abstract int e();
    }

    /* JADX INFO: compiled from: DiffUtil.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<C0029f> f3081a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f3082b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f3083c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final b f3084d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f3085e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final int f3086f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final boolean f3087g;

        c(b bVar, List<C0029f> list, int[] iArr, int[] iArr2, boolean z6) {
            this.f3081a = list;
            this.f3082b = iArr;
            this.f3083c = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.f3084d = bVar;
            this.f3085e = bVar.e();
            this.f3086f = bVar.d();
            this.f3087g = z6;
            a();
            h();
        }

        private void a() {
            C0029f c0029f = this.f3081a.isEmpty() ? null : this.f3081a.get(0);
            if (c0029f != null && c0029f.f3095a == 0 && c0029f.f3096b == 0) {
                return;
            }
            C0029f c0029f2 = new C0029f();
            c0029f2.f3095a = 0;
            c0029f2.f3096b = 0;
            c0029f2.f3098d = false;
            c0029f2.f3097c = 0;
            c0029f2.f3099e = false;
            this.f3081a.add(0, c0029f2);
        }

        private void b(List<d> list, k kVar, int i7, int i8, int i9) {
            if (!this.f3087g) {
                kVar.b(i7, i8);
                return;
            }
            for (int i10 = i8 - 1; i10 >= 0; i10--) {
                int[] iArr = this.f3083c;
                int i11 = i9 + i10;
                int i12 = iArr[i11] & 31;
                if (i12 == 0) {
                    kVar.b(i7, 1);
                    Iterator<d> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().f3089b++;
                    }
                } else if (i12 == 4 || i12 == 8) {
                    int i13 = iArr[i11] >> 5;
                    kVar.c(j(list, i13, true).f3089b, i7);
                    if (i12 == 4) {
                        kVar.d(i7, 1, this.f3084d.c(i13, i11));
                    }
                } else {
                    if (i12 != 16) {
                        throw new IllegalStateException("unknown flag for pos " + i11 + " " + Long.toBinaryString(i12));
                    }
                    list.add(new d(i11, i7, false));
                }
            }
        }

        private void c(List<d> list, k kVar, int i7, int i8, int i9) {
            if (!this.f3087g) {
                kVar.a(i7, i8);
                return;
            }
            for (int i10 = i8 - 1; i10 >= 0; i10--) {
                int[] iArr = this.f3082b;
                int i11 = i9 + i10;
                int i12 = iArr[i11] & 31;
                if (i12 == 0) {
                    kVar.a(i7 + i10, 1);
                    Iterator<d> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().f3089b--;
                    }
                } else if (i12 == 4 || i12 == 8) {
                    int i13 = iArr[i11] >> 5;
                    d dVarJ = j(list, i13, false);
                    kVar.c(i7 + i10, dVarJ.f3089b - 1);
                    if (i12 == 4) {
                        kVar.d(dVarJ.f3089b - 1, 1, this.f3084d.c(i11, i13));
                    }
                } else {
                    if (i12 != 16) {
                        throw new IllegalStateException("unknown flag for pos " + i11 + " " + Long.toBinaryString(i12));
                    }
                    list.add(new d(i11, i7 + i10, true));
                }
            }
        }

        private void f(int i7, int i8, int i9) {
            if (this.f3082b[i7 - 1] != 0) {
                return;
            }
            g(i7, i8, i9, false);
        }

        private boolean g(int i7, int i8, int i9, boolean z6) {
            int i10;
            int i11;
            int i12;
            if (z6) {
                i8--;
                i11 = i7;
                i10 = i8;
            } else {
                i10 = i7 - 1;
                i11 = i10;
            }
            while (i9 >= 0) {
                C0029f c0029f = this.f3081a.get(i9);
                int i13 = c0029f.f3095a;
                int i14 = c0029f.f3097c;
                int i15 = i13 + i14;
                int i16 = c0029f.f3096b + i14;
                if (z6) {
                    for (int i17 = i11 - 1; i17 >= i15; i17--) {
                        if (this.f3084d.b(i17, i10)) {
                            i12 = this.f3084d.a(i17, i10) ? 8 : 4;
                            this.f3083c[i10] = (i17 << 5) | 16;
                            this.f3082b[i17] = (i10 << 5) | i12;
                            return true;
                        }
                    }
                } else {
                    for (int i18 = i8 - 1; i18 >= i16; i18--) {
                        if (this.f3084d.b(i10, i18)) {
                            i12 = this.f3084d.a(i10, i18) ? 8 : 4;
                            int i19 = i7 - 1;
                            this.f3082b[i19] = (i18 << 5) | 16;
                            this.f3083c[i18] = (i19 << 5) | i12;
                            return true;
                        }
                    }
                }
                i11 = c0029f.f3095a;
                i8 = c0029f.f3096b;
                i9--;
            }
            return false;
        }

        private void h() {
            int i7 = this.f3085e;
            int i8 = this.f3086f;
            for (int size = this.f3081a.size() - 1; size >= 0; size--) {
                C0029f c0029f = this.f3081a.get(size);
                int i9 = c0029f.f3095a;
                int i10 = c0029f.f3097c;
                int i11 = i9 + i10;
                int i12 = c0029f.f3096b + i10;
                if (this.f3087g) {
                    while (i7 > i11) {
                        f(i7, i8, size);
                        i7--;
                    }
                    while (i8 > i12) {
                        i(i7, i8, size);
                        i8--;
                    }
                }
                for (int i13 = 0; i13 < c0029f.f3097c; i13++) {
                    int i14 = c0029f.f3095a + i13;
                    int i15 = c0029f.f3096b + i13;
                    int i16 = this.f3084d.a(i14, i15) ? 1 : 2;
                    this.f3082b[i14] = (i15 << 5) | i16;
                    this.f3083c[i15] = (i14 << 5) | i16;
                }
                i7 = c0029f.f3095a;
                i8 = c0029f.f3096b;
            }
        }

        private void i(int i7, int i8, int i9) {
            if (this.f3083c[i8 - 1] != 0) {
                return;
            }
            g(i7, i8, i9, true);
        }

        private static d j(List<d> list, int i7, boolean z6) {
            int size = list.size() - 1;
            while (size >= 0) {
                d dVar = list.get(size);
                if (dVar.f3088a == i7 && dVar.f3090c == z6) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).f3089b += z6 ? 1 : -1;
                        size++;
                    }
                    return dVar;
                }
                size--;
            }
            return null;
        }

        public void d(k kVar) {
            androidx.recyclerview.widget.c cVar = kVar instanceof androidx.recyclerview.widget.c ? (androidx.recyclerview.widget.c) kVar : new androidx.recyclerview.widget.c(kVar);
            List<d> arrayList = new ArrayList<>();
            int i7 = this.f3085e;
            int i8 = this.f3086f;
            for (int size = this.f3081a.size() - 1; size >= 0; size--) {
                C0029f c0029f = this.f3081a.get(size);
                int i9 = c0029f.f3097c;
                int i10 = c0029f.f3095a + i9;
                int i11 = c0029f.f3096b + i9;
                if (i10 < i7) {
                    c(arrayList, cVar, i10, i7 - i10, i10);
                }
                if (i11 < i8) {
                    b(arrayList, cVar, i10, i8 - i11, i11);
                }
                for (int i12 = i9 - 1; i12 >= 0; i12--) {
                    int[] iArr = this.f3082b;
                    int i13 = c0029f.f3095a;
                    if ((iArr[i13 + i12] & 31) == 2) {
                        cVar.d(i13 + i12, 1, this.f3084d.c(i13 + i12, c0029f.f3096b + i12));
                    }
                }
                i7 = c0029f.f3095a;
                i8 = c0029f.f3096b;
            }
            cVar.e();
        }

        public void e(RecyclerView.g gVar) {
            d(new androidx.recyclerview.widget.b(gVar));
        }
    }

    /* JADX INFO: compiled from: DiffUtil.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3088a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3089b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3090c;

        public d(int i7, int i8, boolean z6) {
            this.f3088a = i7;
            this.f3089b = i8;
            this.f3090c = z6;
        }
    }

    /* JADX INFO: compiled from: DiffUtil.java */
    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3091a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3092b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3093c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3094d;

        public e() {
        }

        public e(int i7, int i8, int i9, int i10) {
            this.f3091a = i7;
            this.f3092b = i8;
            this.f3093c = i9;
            this.f3094d = i10;
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DiffUtil.java */
    static class C0029f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3095a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3096b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3097c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3098d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f3099e;

        C0029f() {
        }
    }

    public static c a(b bVar) {
        return b(bVar, true);
    }

    public static c b(b bVar, boolean z6) {
        int iE = bVar.e();
        int iD = bVar.d();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new e(0, iE, 0, iD));
        int iAbs = iE + iD + Math.abs(iE - iD);
        int i7 = iAbs * 2;
        int[] iArr = new int[i7];
        int[] iArr2 = new int[i7];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            e eVar = (e) arrayList2.remove(arrayList2.size() - 1);
            C0029f c0029fC = c(bVar, eVar.f3091a, eVar.f3092b, eVar.f3093c, eVar.f3094d, iArr, iArr2, iAbs);
            if (c0029fC != null) {
                if (c0029fC.f3097c > 0) {
                    arrayList.add(c0029fC);
                }
                c0029fC.f3095a += eVar.f3091a;
                c0029fC.f3096b += eVar.f3093c;
                e eVar2 = arrayList3.isEmpty() ? new e() : (e) arrayList3.remove(arrayList3.size() - 1);
                eVar2.f3091a = eVar.f3091a;
                eVar2.f3093c = eVar.f3093c;
                if (c0029fC.f3099e) {
                    eVar2.f3092b = c0029fC.f3095a;
                    eVar2.f3094d = c0029fC.f3096b;
                } else if (c0029fC.f3098d) {
                    eVar2.f3092b = c0029fC.f3095a - 1;
                    eVar2.f3094d = c0029fC.f3096b;
                } else {
                    eVar2.f3092b = c0029fC.f3095a;
                    eVar2.f3094d = c0029fC.f3096b - 1;
                }
                arrayList2.add(eVar2);
                if (!c0029fC.f3099e) {
                    int i8 = c0029fC.f3095a;
                    int i9 = c0029fC.f3097c;
                    eVar.f3091a = i8 + i9;
                    eVar.f3093c = c0029fC.f3096b + i9;
                } else if (c0029fC.f3098d) {
                    int i10 = c0029fC.f3095a;
                    int i11 = c0029fC.f3097c;
                    eVar.f3091a = i10 + i11 + 1;
                    eVar.f3093c = c0029fC.f3096b + i11;
                } else {
                    int i12 = c0029fC.f3095a;
                    int i13 = c0029fC.f3097c;
                    eVar.f3091a = i12 + i13;
                    eVar.f3093c = c0029fC.f3096b + i13 + 1;
                }
                arrayList2.add(eVar);
            } else {
                arrayList3.add(eVar);
            }
        }
        Collections.sort(arrayList, f3080a);
        return new c(bVar, arrayList, iArr, iArr2, z6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:52:0x00cd
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:125)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:125)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static androidx.recyclerview.widget.f.C0029f c(androidx.recyclerview.widget.f.b r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.f.c(androidx.recyclerview.widget.f$b, int, int, int, int, int[], int[], int):androidx.recyclerview.widget.f$f");
    }
}
