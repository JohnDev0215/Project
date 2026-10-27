package p6;

import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: Options.kt */
/* JADX INFO: loaded from: classes.dex */
public final class r extends h5.b<h> implements RandomAccess {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f8889i = new a(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final h[] f8890g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int[] f8891h;

    /* JADX INFO: compiled from: Options.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        private final void a(long j7, e eVar, int i7, List<? extends h> list, int i8, int i9, List<Integer> list2) {
            int i10;
            int i11;
            int i12;
            int i13;
            e eVar2;
            int i14 = i7;
            if (!(i8 < i9)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int i15 = i8; i15 < i9; i15++) {
                if (!(list.get(i15).r() >= i14)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            h hVar = list.get(i8);
            h hVar2 = list.get(i9 - 1);
            int i16 = -1;
            if (i14 == hVar.r()) {
                int iIntValue = list2.get(i8).intValue();
                int i17 = i8 + 1;
                h hVar3 = list.get(i17);
                i10 = i17;
                i11 = iIntValue;
                hVar = hVar3;
            } else {
                i10 = i8;
                i11 = -1;
            }
            if (hVar.d(i14) == hVar2.d(i14)) {
                int iMin = Math.min(hVar.r(), hVar2.r());
                int i18 = 0;
                for (int i19 = i14; i19 < iMin && hVar.d(i19) == hVar2.d(i19); i19++) {
                    i18++;
                }
                long jC = j7 + c(eVar) + ((long) 2) + ((long) i18) + 1;
                eVar.y(-i18);
                eVar.y(i11);
                int i20 = i14 + i18;
                while (i14 < i20) {
                    eVar.y(hVar.d(i14) & 255);
                    i14++;
                }
                if (i10 + 1 == i9) {
                    if (!(i20 == list.get(i10).r())) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    eVar.y(list2.get(i10).intValue());
                    return;
                } else {
                    e eVar3 = new e();
                    eVar.y(((int) (c(eVar3) + jC)) * (-1));
                    a(jC, eVar3, i20, list, i10, i9, list2);
                    eVar.q0(eVar3);
                    return;
                }
            }
            int i21 = 1;
            for (int i22 = i10 + 1; i22 < i9; i22++) {
                if (list.get(i22 - 1).d(i14) != list.get(i22).d(i14)) {
                    i21++;
                }
            }
            long jC2 = j7 + c(eVar) + ((long) 2) + ((long) (i21 * 2));
            eVar.y(i21);
            eVar.y(i11);
            for (int i23 = i10; i23 < i9; i23++) {
                byte bD = list.get(i23).d(i14);
                if (i23 == i10 || bD != list.get(i23 - 1).d(i14)) {
                    eVar.y(bD & 255);
                }
            }
            e eVar4 = new e();
            while (i10 < i9) {
                byte bD2 = list.get(i10).d(i14);
                int i24 = i10 + 1;
                int i25 = i24;
                while (true) {
                    if (i25 >= i9) {
                        i12 = i9;
                        break;
                    } else {
                        if (bD2 != list.get(i25).d(i14)) {
                            i12 = i25;
                            break;
                        }
                        i25++;
                    }
                }
                if (i24 == i12 && i14 + 1 == list.get(i10).r()) {
                    eVar.y(list2.get(i10).intValue());
                    i13 = i12;
                    eVar2 = eVar4;
                } else {
                    eVar.y(((int) (jC2 + c(eVar4))) * i16);
                    i13 = i12;
                    eVar2 = eVar4;
                    a(jC2, eVar4, i14 + 1, list, i10, i12, list2);
                }
                eVar4 = eVar2;
                i10 = i13;
                i16 = -1;
            }
            eVar.q0(eVar4);
        }

        static /* synthetic */ void b(a aVar, long j7, e eVar, int i7, List list, int i8, int i9, List list2, int i10, Object obj) {
            aVar.a((i10 & 1) != 0 ? 0L : j7, eVar, (i10 & 4) != 0 ? 0 : i7, list, (i10 & 16) != 0 ? 0 : i8, (i10 & 32) != 0 ? list.size() : i9, list2);
        }

        private final long c(e eVar) {
            return eVar.j0() / ((long) 4);
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x00f1, code lost:
        
            continue;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final p6.r d(p6.h... r17) {
            /*
                Method dump skipped, instruction units count: 328
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p6.r.a.d(p6.h[]):p6.r");
        }
    }

    public /* synthetic */ r(h[] hVarArr, int[] iArr, kotlin.jvm.internal.g gVar) {
        this(hVarArr, iArr);
    }

    @Override // h5.a
    public int c() {
        return this.f8890g.length;
    }

    @Override // h5.a, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof h) {
            return d((h) obj);
        }
        return false;
    }

    public /* bridge */ boolean d(h hVar) {
        return super.contains(hVar);
    }

    @Override // h5.b, java.util.List
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public h get(int i7) {
        return this.f8890g[i7];
    }

    public /* bridge */ int f(h hVar) {
        return super.indexOf(hVar);
    }

    public /* bridge */ int g(h hVar) {
        return super.lastIndexOf(hVar);
    }

    @Override // h5.b, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof h) {
            return f((h) obj);
        }
        return -1;
    }

    @Override // h5.b, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof h) {
            return g((h) obj);
        }
        return -1;
    }

    private r(h[] hVarArr, int[] iArr) {
        this.f8890g = hVarArr;
        this.f8891h = iArr;
    }
}
