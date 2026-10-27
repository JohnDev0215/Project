package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$id;
import androidx.fragment.app.Fragment;
import defpackage.C0173a;
import defpackage.C0175b;
import defpackage.am;
import defpackage.gq2;
import defpackage.i21;
import defpackage.in3;
import defpackage.l91;
import defpackage.om3;
import defpackage.pq2;
import defpackage.tu;
import defpackage.vi3;
import defpackage.z8;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.WeakHashMap;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SpecialEffectsController.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSpecialEffectsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SpecialEffectsController.kt\nandroidx/fragment/app/SpecialEffectsController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,710:1\n288#2,2:711\n288#2,2:713\n533#2,6:715\n*S KotlinDebug\n*F\n+ 1 SpecialEffectsController.kt\nandroidx/fragment/app/SpecialEffectsController\n*L\n69#1:711,2\n75#1:713,2\n166#1:715,6\n*E\n"})
public abstract class j {
    public final ViewGroup a;
    public final ArrayList b;
    public final ArrayList c;
    public boolean d;
    public boolean e;

    /* JADX INFO: compiled from: SpecialEffectsController.kt */
    public static final class a extends b {
        public final h h;

        /* JADX WARN: Illegal instructions before constructor call */
        public a(int i, int i2, h hVar, am amVar) {
            C0175b.q(i, "finalState");
            C0175b.q(i2, "lifecycleImpact");
            i21.e(hVar, "fragmentStateManager");
            Fragment fragment = hVar.c;
            i21.d(fragment, "fragmentStateManager.fragment");
            super(i, i2, fragment, amVar);
            this.h = hVar;
        }

        @Override // androidx.fragment.app.j.b
        public final void b() {
            super.b();
            this.h.k();
        }

        @Override // androidx.fragment.app.j.b
        public final void d() {
            int i = this.b;
            h hVar = this.h;
            if (i != 2) {
                if (i == 3) {
                    Fragment fragment = hVar.c;
                    i21.d(fragment, "fragmentStateManager.fragment");
                    fragment.y0().clearFocus();
                    return;
                }
                return;
            }
            Fragment fragment2 = hVar.c;
            i21.d(fragment2, "fragmentStateManager.fragment");
            View viewFindFocus = fragment2.f12J.findFocus();
            if (viewFindFocus != null) {
                fragment2.H().m = viewFindFocus;
            }
            View viewY0 = this.c.y0();
            if (viewY0.getParent() == null) {
                hVar.b();
                viewY0.setAlpha(0.0f);
            }
            if ((viewY0.getAlpha() == 0.0f) && viewY0.getVisibility() == 0) {
                viewY0.setVisibility(4);
            }
            Fragment.c cVar = fragment2.M;
            viewY0.setAlpha(cVar == null ? 1.0f : cVar.l);
        }
    }

    /* JADX INFO: compiled from: SpecialEffectsController.kt */
    public /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[gq2.p(3).length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            a = iArr;
        }
    }

    public j(ViewGroup viewGroup) {
        i21.e(viewGroup, "container");
        this.a = viewGroup;
        this.b = new ArrayList();
        this.c = new ArrayList();
    }

    public static final j f(ViewGroup viewGroup, FragmentManager fragmentManager) {
        i21.e(viewGroup, "container");
        i21.e(fragmentManager, "fragmentManager");
        i21.d(fragmentManager.G(), "fragmentManager.specialEffectsControllerFactory");
        int i = R$id.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i);
        if (tag instanceof j) {
            return (j) tag;
        }
        androidx.fragment.app.b bVar = new androidx.fragment.app.b(viewGroup);
        viewGroup.setTag(i, bVar);
        return bVar;
    }

    public final void a(int i, int i2, h hVar) {
        synchronized (this.b) {
            am amVar = new am();
            Fragment fragment = hVar.c;
            i21.d(fragment, "fragmentStateManager.fragment");
            b bVarD = d(fragment);
            if (bVarD != null) {
                bVarD.c(i, i2);
                return;
            }
            a aVar = new a(i, i2, hVar, amVar);
            this.b.add(aVar);
            aVar.d.add(new l91(this, 3, aVar));
            aVar.d.add(new z8(this, 2, aVar));
            vi3 vi3Var = vi3.a;
        }
    }

    public abstract void b(ArrayList arrayList, boolean z);

    public final void c() {
        if (this.e) {
            return;
        }
        ViewGroup viewGroup = this.a;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (!om3.g.b(viewGroup)) {
            e();
            this.d = false;
            return;
        }
        synchronized (this.b) {
            if (!this.b.isEmpty()) {
                ArrayList<b> arrayListR0 = tu.r0(this.c);
                this.c.clear();
                for (b bVar : arrayListR0) {
                    bVar.a();
                    if (!bVar.g) {
                        this.c.add(bVar);
                    }
                }
                h();
                ArrayList arrayListR02 = tu.r0(this.b);
                this.b.clear();
                this.c.addAll(arrayListR02);
                Iterator it = arrayListR02.iterator();
                while (it.hasNext()) {
                    ((b) it.next()).d();
                }
                b(arrayListR02, this.d);
                this.d = false;
            }
            vi3 vi3Var = vi3.a;
        }
    }

    public final b d(Fragment fragment) {
        Object next;
        Iterator it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            b bVar = (b) next;
            if (i21.a(bVar.c, fragment) && !bVar.f) {
                break;
            }
        }
        return (b) next;
    }

    public final void e() {
        ViewGroup viewGroup = this.a;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.g.b(viewGroup);
        synchronized (this.b) {
            h();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((b) it.next()).d();
            }
            Iterator it2 = tu.r0(this.c).iterator();
            while (it2.hasNext()) {
                ((b) it2.next()).a();
            }
            Iterator it3 = tu.r0(this.b).iterator();
            while (it3.hasNext()) {
                ((b) it3.next()).a();
            }
            vi3 vi3Var = vi3.a;
        }
    }

    public final void g() {
        Object objPrevious;
        synchronized (this.b) {
            h();
            ArrayList arrayList = this.b;
            ListIterator listIterator = arrayList.listIterator(arrayList.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                }
                objPrevious = listIterator.previous();
                b bVar = (b) objPrevious;
                View view = bVar.c.f12J;
                i21.d(view, "operation.fragment.mView");
                if (bVar.a == 2 && k.a(view) != 2) {
                    break;
                }
            }
            b bVar2 = (b) objPrevious;
            Fragment fragment = bVar2 != null ? bVar2.c : null;
            if (fragment != null) {
                Fragment.c cVar = fragment.M;
            }
            this.e = false;
            vi3 vi3Var = vi3.a;
        }
    }

    public final void h() {
        for (b bVar : this.b) {
            int i = 2;
            if (bVar.b == 2) {
                int visibility = bVar.c.y0().getVisibility();
                if (visibility != 0) {
                    i = 4;
                    if (visibility != 4) {
                        if (visibility != 8) {
                            throw new IllegalArgumentException(gq2.e("Unknown visibility ", visibility));
                        }
                        i = 3;
                    }
                }
                bVar.c(i, 1);
            }
        }
    }

    /* JADX INFO: compiled from: SpecialEffectsController.kt */
    @SourceDebugExtension({"SMAP\nSpecialEffectsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SpecialEffectsController.kt\nandroidx/fragment/app/SpecialEffectsController$Operation\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,710:1\n1855#2,2:711\n*S KotlinDebug\n*F\n+ 1 SpecialEffectsController.kt\nandroidx/fragment/app/SpecialEffectsController$Operation\n*L\n607#1:711,2\n*E\n"})
    public static class b {
        public int a;
        public int b;
        public final Fragment c;
        public final ArrayList d;
        public final LinkedHashSet e;
        public boolean f;
        public boolean g;

        public b(int i, int i2, Fragment fragment, am amVar) {
            C0175b.q(i, "finalState");
            C0175b.q(i2, "lifecycleImpact");
            this.a = i;
            this.b = i2;
            this.c = fragment;
            this.d = new ArrayList();
            this.e = new LinkedHashSet();
            amVar.setOnCancelListener(new pq2(0, this));
        }

        public final void a() {
            if (this.f) {
                return;
            }
            this.f = true;
            LinkedHashSet linkedHashSet = this.e;
            if (linkedHashSet.isEmpty()) {
                b();
                return;
            }
            Iterator it = new LinkedHashSet(linkedHashSet).iterator();
            while (it.hasNext()) {
                ((am) it.next()).a();
            }
        }

        public void b() {
            if (this.g) {
                return;
            }
            this.g = true;
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }

        public final void c(int i, int i2) {
            C0175b.q(i, "finalState");
            C0175b.q(i2, "lifecycleImpact");
            if (i2 == 0) {
                throw null;
            }
            int i3 = i2 - 1;
            if (i3 == 0) {
                if (this.a != 1) {
                    this.a = i;
                }
            } else {
                if (i3 != 1) {
                    if (i3 != 2) {
                        return;
                    }
                    this.a = 1;
                    this.b = 3;
                    return;
                }
                if (this.a == 1) {
                    this.a = 2;
                    this.b = 2;
                }
            }
        }

        public final String toString() {
            StringBuilder sbN = C0175b.n("Operation {", Integer.toHexString(System.identityHashCode(this)), "} {finalState = ");
            sbN.append(gq2.o(this.a));
            sbN.append(" lifecycleImpact = ");
            sbN.append(C0173a.s(this.b));
            sbN.append(" fragment = ");
            sbN.append(this.c);
            sbN.append('}');
            return sbN.toString();
        }

        public void d() {
        }
    }
}
