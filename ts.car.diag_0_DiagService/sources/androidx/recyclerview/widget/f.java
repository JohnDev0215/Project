package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Comparator<d> f1189a = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Comparator<d> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            return dVar.f1192a - dVar2.f1192a;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class b {
        public abstract boolean a(int i, int i2);

        public abstract boolean b(int i, int i2);

        @Nullable
        public Object c(int i, int i2) {
            return null;
        }

        public abstract int d();

        public abstract int e();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f1190a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1191b;

        c(int i) {
            int[] iArr = new int[i];
            this.f1190a = iArr;
            this.f1191b = iArr.length / 2;
        }

        int[] a() {
            return this.f1190a;
        }

        int b(int i) {
            return this.f1190a[i + this.f1191b];
        }

        void c(int i, int i2) {
            this.f1190a[i + this.f1191b] = i2;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1192a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1193b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1194c;

        d(int i, int i2, int i3) {
            this.f1192a = i;
            this.f1193b = i2;
            this.f1194c = i3;
        }

        int a() {
            return this.f1192a + this.f1194c;
        }

        int b() {
            return this.f1193b + this.f1194c;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<d> f1195a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f1196b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f1197c;
        private final b d;
        private final int e;
        private final int f;
        private final boolean g;

        e(b bVar, List<d> list, int[] iArr, int[] iArr2, boolean z) {
            this.f1195a = list;
            this.f1196b = iArr;
            this.f1197c = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(this.f1197c, 0);
            this.d = bVar;
            this.e = bVar.e();
            this.f = bVar.d();
            this.g = z;
            a();
            e();
        }

        private void a() {
            d dVar = this.f1195a.isEmpty() ? null : this.f1195a.get(0);
            if (dVar == null || dVar.f1192a != 0 || dVar.f1193b != 0) {
                this.f1195a.add(0, new d(0, 0, 0));
            }
            this.f1195a.add(new d(this.e, this.f, 0));
        }

        private void d(int i) {
            int size = this.f1195a.size();
            int iB = 0;
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = this.f1195a.get(i2);
                while (iB < dVar.f1193b) {
                    if (this.f1197c[iB] == 0 && this.d.b(i, iB)) {
                        int i3 = this.d.a(i, iB) ? 8 : 4;
                        this.f1196b[i] = (iB << 4) | i3;
                        this.f1197c[iB] = (i << 4) | i3;
                        return;
                    }
                    iB++;
                }
                iB = dVar.b();
            }
        }

        private void e() {
            for (d dVar : this.f1195a) {
                for (int i = 0; i < dVar.f1194c; i++) {
                    int i2 = dVar.f1192a + i;
                    int i3 = dVar.f1193b + i;
                    int i4 = this.d.a(i2, i3) ? 1 : 2;
                    this.f1196b[i2] = (i3 << 4) | i4;
                    this.f1197c[i3] = (i2 << 4) | i4;
                }
            }
            if (this.g) {
                f();
            }
        }

        private void f() {
            int iA = 0;
            for (d dVar : this.f1195a) {
                while (iA < dVar.f1192a) {
                    if (this.f1196b[iA] == 0) {
                        d(iA);
                    }
                    iA++;
                }
                iA = dVar.a();
            }
        }

        @Nullable
        private static C0041f g(Collection<C0041f> collection, int i, boolean z) {
            C0041f next;
            Iterator<C0041f> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.f1198a == i && next.f1200c == z) {
                    it.remove();
                    break;
                }
            }
            while (it.hasNext()) {
                C0041f next2 = it.next();
                if (z) {
                    next2.f1199b--;
                } else {
                    next2.f1199b++;
                }
            }
            return next;
        }

        public void b(@NonNull i iVar) {
            int i;
            androidx.recyclerview.widget.c cVar = iVar instanceof androidx.recyclerview.widget.c ? (androidx.recyclerview.widget.c) iVar : new androidx.recyclerview.widget.c(iVar);
            int i2 = this.e;
            ArrayDeque arrayDeque = new ArrayDeque();
            int i3 = this.e;
            int i4 = this.f;
            for (int size = this.f1195a.size() - 1; size >= 0; size--) {
                d dVar = this.f1195a.get(size);
                int iA = dVar.a();
                int iB = dVar.b();
                while (true) {
                    if (i3 <= iA) {
                        break;
                    }
                    i3--;
                    int i5 = this.f1196b[i3];
                    if ((i5 & 12) != 0) {
                        int i6 = i5 >> 4;
                        C0041f c0041fG = g(arrayDeque, i6, false);
                        if (c0041fG != null) {
                            int i7 = (i2 - c0041fG.f1199b) - 1;
                            cVar.c(i3, i7);
                            if ((i5 & 4) != 0) {
                                cVar.d(i7, 1, this.d.c(i3, i6));
                            }
                        } else {
                            arrayDeque.add(new C0041f(i3, (i2 - i3) - 1, true));
                        }
                    } else {
                        cVar.a(i3, 1);
                        i2--;
                    }
                }
                while (i4 > iB) {
                    i4--;
                    int i8 = this.f1197c[i4];
                    if ((i8 & 12) != 0) {
                        int i9 = i8 >> 4;
                        C0041f c0041fG2 = g(arrayDeque, i9, true);
                        if (c0041fG2 == null) {
                            arrayDeque.add(new C0041f(i4, i2 - i3, false));
                        } else {
                            cVar.c((i2 - c0041fG2.f1199b) - 1, i3);
                            if ((i8 & 4) != 0) {
                                cVar.d(i3, 1, this.d.c(i9, i4));
                            }
                        }
                    } else {
                        cVar.b(i3, 1);
                        i2++;
                    }
                }
                int i10 = dVar.f1192a;
                int i11 = dVar.f1193b;
                for (i = 0; i < dVar.f1194c; i++) {
                    if ((this.f1196b[i10] & 15) == 2) {
                        cVar.d(i10, 1, this.d.c(i10, i11));
                    }
                    i10++;
                    i11++;
                }
                i3 = dVar.f1192a;
                i4 = dVar.f1193b;
            }
            cVar.e();
        }

        public void c(@NonNull RecyclerView.Adapter adapter) {
            b(new androidx.recyclerview.widget.b(adapter));
        }
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private static class C0041f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1198a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1199b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1200c;

        C0041f(int i, int i2, boolean z) {
            this.f1198a = i;
            this.f1199b = i2;
            this.f1200c = z;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1201a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1202b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1203c;
        int d;

        public g() {
        }

        int a() {
            return this.d - this.f1203c;
        }

        int b() {
            return this.f1202b - this.f1201a;
        }

        public g(int i, int i2, int i3, int i4) {
            this.f1201a = i;
            this.f1202b = i2;
            this.f1203c = i3;
            this.d = i4;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1204a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1205b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1206c;
        public int d;
        public boolean e;

        h() {
        }

        int a() {
            return Math.min(this.f1206c - this.f1204a, this.d - this.f1205b);
        }

        boolean b() {
            return this.d - this.f1205b != this.f1206c - this.f1204a;
        }

        boolean c() {
            return this.d - this.f1205b > this.f1206c - this.f1204a;
        }

        @NonNull
        d d() {
            if (b()) {
                return this.e ? new d(this.f1204a, this.f1205b, a()) : c() ? new d(this.f1204a, this.f1205b + 1, a()) : new d(this.f1204a + 1, this.f1205b, a());
            }
            int i = this.f1204a;
            return new d(i, this.f1205b, this.f1206c - i);
        }
    }

    @Nullable
    private static h a(g gVar, b bVar, c cVar, c cVar2, int i) {
        int iB;
        int i2;
        int i3;
        boolean z = (gVar.b() - gVar.a()) % 2 == 0;
        int iB2 = gVar.b() - gVar.a();
        int i4 = -i;
        for (int i5 = i4; i5 <= i; i5 += 2) {
            if (i5 == i4 || (i5 != i && cVar2.b(i5 + 1) < cVar2.b(i5 - 1))) {
                iB = cVar2.b(i5 + 1);
                i2 = iB;
            } else {
                iB = cVar2.b(i5 - 1);
                i2 = iB - 1;
            }
            int i6 = gVar.d - ((gVar.f1202b - i2) - i5);
            int i7 = (i == 0 || i2 != iB) ? i6 : i6 + 1;
            while (i2 > gVar.f1201a && i6 > gVar.f1203c && bVar.b(i2 - 1, i6 - 1)) {
                i2--;
                i6--;
            }
            cVar2.c(i5, i2);
            if (z && (i3 = iB2 - i5) >= i4 && i3 <= i && cVar.b(i3) >= i2) {
                h hVar = new h();
                hVar.f1204a = i2;
                hVar.f1205b = i6;
                hVar.f1206c = iB;
                hVar.d = i7;
                hVar.e = true;
                return hVar;
            }
        }
        return null;
    }

    @NonNull
    public static e b(@NonNull b bVar) {
        return c(bVar, true);
    }

    @NonNull
    public static e c(@NonNull b bVar, boolean z) {
        int iE = bVar.e();
        int iD = bVar.d();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new g(0, iE, 0, iD));
        int i = ((((iE + iD) + 1) / 2) * 2) + 1;
        c cVar = new c(i);
        c cVar2 = new c(i);
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            g gVar = (g) arrayList2.remove(arrayList2.size() - 1);
            h hVarE = e(gVar, bVar, cVar, cVar2);
            if (hVarE != null) {
                if (hVarE.a() > 0) {
                    arrayList.add(hVarE.d());
                }
                g gVar2 = arrayList3.isEmpty() ? new g() : (g) arrayList3.remove(arrayList3.size() - 1);
                gVar2.f1201a = gVar.f1201a;
                gVar2.f1203c = gVar.f1203c;
                gVar2.f1202b = hVarE.f1204a;
                gVar2.d = hVarE.f1205b;
                arrayList2.add(gVar2);
                gVar.f1202b = gVar.f1202b;
                gVar.d = gVar.d;
                gVar.f1201a = hVarE.f1206c;
                gVar.f1203c = hVarE.d;
                arrayList2.add(gVar);
            } else {
                arrayList3.add(gVar);
            }
        }
        Collections.sort(arrayList, f1189a);
        return new e(bVar, arrayList, cVar.a(), cVar2.a(), z);
    }

    @Nullable
    private static h d(g gVar, b bVar, c cVar, c cVar2, int i) {
        int iB;
        int i2;
        int i3;
        boolean z = Math.abs(gVar.b() - gVar.a()) % 2 == 1;
        int iB2 = gVar.b() - gVar.a();
        int i4 = -i;
        for (int i5 = i4; i5 <= i; i5 += 2) {
            if (i5 == i4 || (i5 != i && cVar.b(i5 + 1) > cVar.b(i5 - 1))) {
                iB = cVar.b(i5 + 1);
                i2 = iB;
            } else {
                iB = cVar.b(i5 - 1);
                i2 = iB + 1;
            }
            int i6 = (gVar.f1203c + (i2 - gVar.f1201a)) - i5;
            int i7 = (i == 0 || i2 != iB) ? i6 : i6 - 1;
            while (i2 < gVar.f1202b && i6 < gVar.d && bVar.b(i2, i6)) {
                i2++;
                i6++;
            }
            cVar.c(i5, i2);
            if (z && (i3 = iB2 - i5) >= i4 + 1 && i3 <= i - 1 && cVar2.b(i3) <= i2) {
                h hVar = new h();
                hVar.f1204a = iB;
                hVar.f1205b = i7;
                hVar.f1206c = i2;
                hVar.d = i6;
                hVar.e = false;
                return hVar;
            }
        }
        return null;
    }

    @Nullable
    private static h e(g gVar, b bVar, c cVar, c cVar2) {
        if (gVar.b() >= 1 && gVar.a() >= 1) {
            int iB = ((gVar.b() + gVar.a()) + 1) / 2;
            cVar.c(1, gVar.f1201a);
            cVar2.c(1, gVar.f1202b);
            for (int i = 0; i < iB; i++) {
                h hVarD = d(gVar, bVar, cVar, cVar2, i);
                if (hVarD != null) {
                    return hVarD;
                }
                h hVarA = a(gVar, bVar, cVar, cVar2, i);
                if (hVarA != null) {
                    return hVarA;
                }
            }
        }
        return null;
    }
}
