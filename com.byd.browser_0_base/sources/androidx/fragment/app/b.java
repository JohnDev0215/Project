package androidx.fragment.app;

import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.e;
import androidx.fragment.app.j;
import defpackage.am;
import defpackage.bq0;
import defpackage.i21;
import defpackage.in3;
import defpackage.m8;
import defpackage.om3;
import defpackage.tu;
import defpackage.vp0;
import defpackage.xp0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nDefaultSpecialEffectsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultSpecialEffectsController.kt\nandroidx/fragment/app/DefaultSpecialEffectsController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,877:1\n288#2,2:878\n533#2,6:880\n819#2:886\n847#2,2:887\n766#2:889\n857#2,2:890\n1789#2,3:892\n819#2:895\n847#2,2:896\n1855#2,2:898\n*S KotlinDebug\n*F\n+ 1 DefaultSpecialEffectsController.kt\nandroidx/fragment/app/DefaultSpecialEffectsController\n*L\n47#1:878,2\n53#1:880,6\n312#1:886\n312#1:887,2\n315#1:889\n315#1:890,2\n317#1:892,3\n629#1:895\n629#1:896,2\n632#1:898,2\n*E\n"})
public final class b extends j {

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    public static final class a extends C0009b {
        public final boolean c;
        public boolean d;
        public e.a e;

        public a(j.b bVar, am amVar, boolean z) {
            super(bVar, amVar);
            this.c = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0026  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x00f7  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.fragment.app.e.a c(android.content.Context r10) {
            /*
                Method dump skipped, instruction units count: 254
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b.a.c(android.content.Context):androidx.fragment.app.e$a");
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    public static class C0009b {
        public final j.b a;
        public final am b;

        public C0009b(j.b bVar, am amVar) {
            this.a = bVar;
            this.b = amVar;
        }

        public final void a() {
            j.b bVar = this.a;
            bVar.getClass();
            am amVar = this.b;
            i21.e(amVar, "signal");
            LinkedHashSet linkedHashSet = bVar.e;
            if (linkedHashSet.remove(amVar) && linkedHashSet.isEmpty()) {
                bVar.b();
            }
        }

        public final boolean b() {
            j.b bVar = this.a;
            View view = bVar.c.f12J;
            i21.d(view, "operation.fragment.mView");
            int iA = k.a(view);
            int i = bVar.a;
            return iA == i || !(iA == 2 || i == 2);
        }
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    public static final class c extends C0009b {
        public final Object c;
        public final boolean d;
        public final Object e;

        public c(j.b bVar, am amVar, boolean z, boolean z2) {
            Object obj;
            Object obj2;
            super(bVar, amVar);
            int i = bVar.a;
            Object obj3 = null;
            Fragment fragment = bVar.c;
            if (i == 2) {
                if (z) {
                    Fragment.c cVar = fragment.M;
                    if (cVar == null || (obj = cVar.j) == Fragment.Y) {
                    }
                } else {
                    fragment.getClass();
                }
                obj = null;
            } else {
                if (z) {
                    Fragment.c cVar2 = fragment.M;
                    if (cVar2 == null || (obj = cVar2.i) == Fragment.Y) {
                    }
                } else {
                    fragment.getClass();
                }
                obj = null;
            }
            this.c = obj;
            if (bVar.a == 2) {
                if (z) {
                    Fragment.c cVar3 = fragment.M;
                } else {
                    Fragment.c cVar4 = fragment.M;
                }
            }
            this.d = true;
            if (z2) {
                if (z) {
                    Fragment.c cVar5 = fragment.M;
                    if (cVar5 != null && (obj2 = cVar5.k) != Fragment.Y) {
                        obj3 = obj2;
                    }
                } else {
                    fragment.getClass();
                }
            }
            this.e = obj3;
        }

        public final bq0 c() {
            Object obj = this.c;
            bq0 bq0VarD = d(obj);
            Object obj2 = this.e;
            bq0 bq0VarD2 = d(obj2);
            if (bq0VarD == null || bq0VarD2 == null || bq0VarD == bq0VarD2) {
                return bq0VarD == null ? bq0VarD2 : bq0VarD;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.a.c + " returned Transition " + obj + " which uses a different Transition  type than its shared element transition " + obj2).toString());
        }

        public final bq0 d(Object obj) {
            if (obj == null) {
                return null;
            }
            xp0 xp0Var = vp0.a;
            if (xp0Var != null && (obj instanceof Transition)) {
                return xp0Var;
            }
            bq0 bq0Var = vp0.b;
            if (bq0Var != null && bq0Var.e(obj)) {
                return bq0Var;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.a.c + " is not a valid framework Transition or AndroidX Transition");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ViewGroup viewGroup) {
        super(viewGroup);
        i21.e(viewGroup, "container");
    }

    public static void i(View view, ArrayList arrayList) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isTransitionGroup()) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0) {
                i(childAt, arrayList);
            }
        }
    }

    public static void j(m8 m8Var, View view) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        String strF = om3.i.f(view);
        if (strF != null) {
            m8Var.put(strF, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    j(m8Var, childAt);
                }
            }
        }
    }

    public static void k(m8 m8Var, Collection collection) {
        Iterator it = ((m8.a) m8Var.entrySet()).iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            i21.e(entry, "entry");
            View view = (View) entry.getValue();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (!Boolean.valueOf(tu.p0(collection, om3.i.f(view))).booleanValue()) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:204:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x052d  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0535  */
    @Override // androidx.fragment.app.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.util.ArrayList r36, final boolean r37) {
        /*
            Method dump skipped, instruction units count: 1927
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b.b(java.util.ArrayList, boolean):void");
    }
}
