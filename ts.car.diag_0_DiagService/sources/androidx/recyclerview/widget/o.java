package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class o {

    @VisibleForTesting
    final b.a.g<RecyclerView.A, a> mLayoutHolderMap = new b.a.g<>();

    @VisibleForTesting
    final b.a.e<RecyclerView.A> mOldChangedHolders = new b.a.e<>();

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {
        static androidx.core.d.c<a> d = new androidx.core.d.d(20);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1221a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        RecyclerView.ItemAnimator.c f1222b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        RecyclerView.ItemAnimator.c f1223c;

        private a() {
        }

        static void a() {
            while (d.b() != null) {
            }
        }

        static a b() {
            a aVarB = d.b();
            return aVarB == null ? new a() : aVarB;
        }

        static void c(a aVar) {
            aVar.f1221a = 0;
            aVar.f1222b = null;
            aVar.f1223c = null;
            d.a(aVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        void a(RecyclerView.A a2);

        void b(RecyclerView.A a2, @Nullable RecyclerView.ItemAnimator.c cVar, RecyclerView.ItemAnimator.c cVar2);

        void c(RecyclerView.A a2, @NonNull RecyclerView.ItemAnimator.c cVar, @Nullable RecyclerView.ItemAnimator.c cVar2);

        void d(RecyclerView.A a2, @NonNull RecyclerView.ItemAnimator.c cVar, @NonNull RecyclerView.ItemAnimator.c cVar2);
    }

    o() {
    }

    private RecyclerView.ItemAnimator.c l(RecyclerView.A a2, int i) {
        a aVarL;
        RecyclerView.ItemAnimator.c cVar;
        int iF = this.mLayoutHolderMap.f(a2);
        if (iF >= 0 && (aVarL = this.mLayoutHolderMap.l(iF)) != null) {
            int i2 = aVarL.f1221a;
            if ((i2 & i) != 0) {
                aVarL.f1221a = (~i) & i2;
                if (i == 4) {
                    cVar = aVarL.f1222b;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarL.f1223c;
                }
                if ((aVarL.f1221a & 12) == 0) {
                    this.mLayoutHolderMap.j(iF);
                    a.c(aVarL);
                }
                return cVar;
            }
        }
        return null;
    }

    void a(RecyclerView.A a2, RecyclerView.ItemAnimator.c cVar) {
        a aVarB = this.mLayoutHolderMap.get(a2);
        if (aVarB == null) {
            aVarB = a.b();
            this.mLayoutHolderMap.put(a2, aVarB);
        }
        aVarB.f1221a |= 2;
        aVarB.f1222b = cVar;
    }

    void b(RecyclerView.A a2) {
        a aVarB = this.mLayoutHolderMap.get(a2);
        if (aVarB == null) {
            aVarB = a.b();
            this.mLayoutHolderMap.put(a2, aVarB);
        }
        aVarB.f1221a |= 1;
    }

    void c(long j, RecyclerView.A a2) {
        this.mOldChangedHolders.h(j, a2);
    }

    void d(RecyclerView.A a2, RecyclerView.ItemAnimator.c cVar) {
        a aVarB = this.mLayoutHolderMap.get(a2);
        if (aVarB == null) {
            aVarB = a.b();
            this.mLayoutHolderMap.put(a2, aVarB);
        }
        aVarB.f1223c = cVar;
        aVarB.f1221a |= 8;
    }

    void e(RecyclerView.A a2, RecyclerView.ItemAnimator.c cVar) {
        a aVarB = this.mLayoutHolderMap.get(a2);
        if (aVarB == null) {
            aVarB = a.b();
            this.mLayoutHolderMap.put(a2, aVarB);
        }
        aVarB.f1222b = cVar;
        aVarB.f1221a |= 4;
    }

    void f() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.b();
    }

    RecyclerView.A g(long j) {
        return this.mOldChangedHolders.e(j);
    }

    boolean h(RecyclerView.A a2) {
        a aVar = this.mLayoutHolderMap.get(a2);
        return (aVar == null || (aVar.f1221a & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.A a2) {
        a aVar = this.mLayoutHolderMap.get(a2);
        return (aVar == null || (aVar.f1221a & 4) == 0) ? false : true;
    }

    void j() {
        a.a();
    }

    public void k(RecyclerView.A a2) {
        p(a2);
    }

    @Nullable
    RecyclerView.ItemAnimator.c m(RecyclerView.A a2) {
        return l(a2, 8);
    }

    @Nullable
    RecyclerView.ItemAnimator.c n(RecyclerView.A a2) {
        return l(a2, 4);
    }

    void o(b bVar) {
        for (int size = this.mLayoutHolderMap.size() - 1; size >= 0; size--) {
            RecyclerView.A aI = this.mLayoutHolderMap.i(size);
            a aVarJ = this.mLayoutHolderMap.j(size);
            int i = aVarJ.f1221a;
            if ((i & 3) == 3) {
                bVar.a(aI);
            } else if ((i & 1) != 0) {
                RecyclerView.ItemAnimator.c cVar = aVarJ.f1222b;
                if (cVar == null) {
                    bVar.a(aI);
                } else {
                    bVar.c(aI, cVar, aVarJ.f1223c);
                }
            } else if ((i & 14) == 14) {
                bVar.b(aI, aVarJ.f1222b, aVarJ.f1223c);
            } else if ((i & 12) == 12) {
                bVar.d(aI, aVarJ.f1222b, aVarJ.f1223c);
            } else if ((i & 4) != 0) {
                bVar.c(aI, aVarJ.f1222b, null);
            } else if ((i & 8) != 0) {
                bVar.b(aI, aVarJ.f1222b, aVarJ.f1223c);
            }
            a.c(aVarJ);
        }
    }

    void p(RecyclerView.A a2) {
        a aVar = this.mLayoutHolderMap.get(a2);
        if (aVar == null) {
            return;
        }
        aVar.f1221a &= -2;
    }

    void q(RecyclerView.A a2) {
        int iK = this.mOldChangedHolders.k() - 1;
        while (true) {
            if (iK < 0) {
                break;
            }
            if (a2 == this.mOldChangedHolders.l(iK)) {
                this.mOldChangedHolders.j(iK);
                break;
            }
            iK--;
        }
        a aVarRemove = this.mLayoutHolderMap.remove(a2);
        if (aVarRemove != null) {
            a.c(aVarRemove);
        }
    }
}
