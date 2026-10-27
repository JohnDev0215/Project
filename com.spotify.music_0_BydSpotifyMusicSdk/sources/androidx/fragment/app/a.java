package androidx.fragment.app;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.spotify.webapi.service.models.Search;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.Pair;
import p.ax0;
import p.bf2;
import p.by0;
import p.cq;
import p.cy0;
import p.df2;
import p.dy0;
import p.eu2;
import p.g90;
import p.kc0;
import p.kq1;
import p.lt2;
import p.nb;
import p.nv;
import p.ot2;
import p.ou;
import p.p02;
import p.rm;
import p.ry0;
import p.u4;
import p.ua;
import p.uw0;
import p.v03;
import p.v70;
import p.w70;
import p.ww0;
import p.wx0;
import p.x70;
import p.y70;
import p.z70;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public final class a extends df2 {
    public static void i(ArrayList arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (eu2.b(viewGroup)) {
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
                i(arrayList, childAt);
            }
        }
    }

    public static void j(nb nbVar, View view) {
        WeakHashMap weakHashMap = zt2.a;
        String strK = ot2.k(view);
        if (strK != null) {
            nbVar.put(strK, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    j(nbVar, childAt);
                }
            }
        }
    }

    public static void k(nb nbVar, final Collection collection) {
        Set setEntrySet = nbVar.entrySet();
        ry0 ry0Var = new ry0() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$retainMatchingViews$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                v03.j(entry, "entry");
                Collection<String> collection2 = collection;
                View view = (View) entry.getValue();
                WeakHashMap weakHashMap = zt2.a;
                return Boolean.valueOf(ou.Z(collection2, ot2.k(view)));
            }
        };
        Iterator it = setEntrySet.iterator();
        while (it.hasNext()) {
            if (!((Boolean) ry0Var.invoke(it.next())).booleanValue()) {
                it.remove();
            }
        }
    }

    @Override // p.df2
    public final void b(ArrayList arrayList, final boolean z) {
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State;
        String str;
        Object next;
        Object objPrevious;
        ArrayList<w70> arrayList2;
        ArrayList arrayList3;
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State2;
        bf2 bf2Var;
        String str2;
        bf2 bf2Var2;
        bf2 bf2Var3;
        bf2 bf2Var4;
        String str3;
        nb nbVar;
        String str4;
        View view;
        View view2;
        String str5;
        ArrayList arrayList4;
        ArrayList<String> arrayList5;
        ArrayList arrayList6;
        ArrayList arrayList7;
        ArrayList<String> arrayList8;
        dy0 dy0Var;
        Object obj;
        View view3;
        a aVar;
        Iterator it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            specialEffectsController$Operation$State = SpecialEffectsController$Operation$State.e;
            str = "operation.fragment.mView";
            if (!zHasNext) {
                next = null;
                break;
            }
            next = it.next();
            bf2 bf2Var5 = (bf2) next;
            View view4 = bf2Var5.c.M;
            v03.i(view4, "operation.fragment.mView");
            if (p02.a(view4) == specialEffectsController$Operation$State && bf2Var5.a != specialEffectsController$Operation$State) {
                break;
            }
        }
        final bf2 bf2Var6 = (bf2) next;
        ListIterator listIterator = arrayList.listIterator(arrayList.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
            bf2 bf2Var7 = (bf2) objPrevious;
            View view5 = bf2Var7.c.M;
            v03.i(view5, "operation.fragment.mView");
            if (p02.a(view5) != specialEffectsController$Operation$State && bf2Var7.a == specialEffectsController$Operation$State) {
                break;
            }
        }
        final bf2 bf2Var8 = (bf2) objPrevious;
        String str6 = "FragmentManager";
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(bf2Var6);
            Objects.toString(bf2Var8);
        }
        ArrayList arrayList9 = new ArrayList();
        ArrayList<x70> arrayList10 = new ArrayList();
        ArrayList arrayListO0 = ou.o0(arrayList);
        ww0 ww0Var = ((bf2) ou.i0(arrayList)).c;
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            uw0 uw0Var = ((bf2) it2.next()).c.P;
            uw0 uw0Var2 = ww0Var.P;
            uw0Var.b = uw0Var2.b;
            uw0Var.c = uw0Var2.c;
            uw0Var.d = uw0Var2.d;
            uw0Var.e = uw0Var2.e;
        }
        Iterator it3 = arrayList.iterator();
        while (true) {
            int i = 1;
            if (!it3.hasNext()) {
                break;
            }
            bf2 bf2Var9 = (bf2) it3.next();
            cq cqVar = new cq();
            bf2Var9.d();
            LinkedHashSet linkedHashSet = bf2Var9.e;
            linkedHashSet.add(cqVar);
            arrayList9.add(new w70(bf2Var9, cqVar, z));
            cq cqVar2 = new cq();
            bf2Var9.d();
            linkedHashSet.add(cqVar2);
            arrayList10.add(new x70(bf2Var9, cqVar2, z, !z ? bf2Var9 != bf2Var8 : bf2Var9 != bf2Var6));
            bf2Var9.d.add(new kc0(arrayListO0, bf2Var9, this, i));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList11 = new ArrayList();
        for (Object obj2 : arrayList10) {
            if (!((x70) obj2).e()) {
                arrayList11.add(obj2);
            }
        }
        ArrayList<x70> arrayList12 = new ArrayList();
        for (Object obj3 : arrayList11) {
            if (((x70) obj3).h() != null) {
                arrayList12.add(obj3);
            }
        }
        dy0 dy0Var2 = null;
        for (x70 x70Var : arrayList12) {
            dy0 dy0VarH = x70Var.h();
            if (dy0Var2 != null && dy0VarH != dy0Var2) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + ((bf2) x70Var.a).c + " returned Transition " + x70Var.c + " which uses a different Transition type than other Fragments.").toString());
            }
            dy0Var2 = dy0VarH;
        }
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State3 = SpecialEffectsController$Operation$State.k;
        ViewGroup viewGroup = this.a;
        if (dy0Var2 == null) {
            for (x70 x70Var2 : arrayList10) {
                linkedHashMap.put((bf2) x70Var2.a, Boolean.FALSE);
                x70Var2.b();
            }
            arrayList2 = arrayList9;
            bf2Var2 = bf2Var6;
            bf2Var = bf2Var8;
            str2 = "FragmentManager";
            arrayList3 = arrayListO0;
            specialEffectsController$Operation$State2 = specialEffectsController$Operation$State3;
        } else {
            View view6 = new View(viewGroup.getContext());
            Rect rect = new Rect();
            ArrayList arrayList13 = new ArrayList();
            arrayList2 = arrayList9;
            ArrayList arrayList14 = new ArrayList();
            SpecialEffectsController$Operation$State specialEffectsController$Operation$State4 = specialEffectsController$Operation$State;
            arrayList3 = arrayListO0;
            nb nbVar2 = new nb(0);
            Iterator it4 = arrayList10.iterator();
            specialEffectsController$Operation$State2 = specialEffectsController$Operation$State3;
            Object obj4 = null;
            boolean z2 = false;
            View view7 = null;
            while (it4.hasNext()) {
                Iterator it5 = it4;
                Object obj5 = ((x70) it4.next()).e;
                if (obj5 == null || bf2Var6 == null || bf2Var8 == null) {
                    str5 = str;
                    arrayList4 = arrayList10;
                    dy0Var2 = dy0Var2;
                    it4 = it5;
                    str = str5;
                    arrayList10 = arrayList4;
                } else {
                    Object objR = dy0Var2.r(dy0Var2.f(obj5));
                    ww0 ww0Var2 = bf2Var8.c;
                    str5 = str;
                    uw0 uw0Var3 = ww0Var2.P;
                    if (uw0Var3 == null || (arrayList5 = uw0Var3.g) == null) {
                        arrayList5 = new ArrayList();
                    }
                    arrayList4 = arrayList10;
                    ww0 ww0Var3 = bf2Var6.c;
                    LinkedHashMap linkedHashMap2 = linkedHashMap;
                    uw0 uw0Var4 = ww0Var3.P;
                    if (uw0Var4 == null || (arrayList6 = uw0Var4.g) == null) {
                        arrayList6 = new ArrayList();
                    }
                    View view8 = view6;
                    uw0 uw0Var5 = ww0Var3.P;
                    if (uw0Var5 == null || (arrayList7 = uw0Var5.h) == null) {
                        arrayList7 = new ArrayList();
                    }
                    Rect rect2 = rect;
                    int size = arrayList7.size();
                    dy0 dy0Var3 = dy0Var2;
                    int i2 = 0;
                    while (i2 < size) {
                        int i3 = size;
                        int iIndexOf = arrayList5.indexOf(arrayList7.get(i2));
                        if (iIndexOf != -1) {
                            arrayList5.set(iIndexOf, arrayList6.get(i2));
                        }
                        i2++;
                        size = i3;
                    }
                    uw0 uw0Var6 = ww0Var2.P;
                    if (uw0Var6 == null || (arrayList8 = uw0Var6.h) == null) {
                        arrayList8 = new ArrayList();
                    }
                    Pair pair = !z ? new Pair(null, null) : new Pair(null, null);
                    u4.z(pair.a());
                    u4.z(pair.b());
                    int size2 = arrayList5.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        nbVar2.put((String) arrayList5.get(i4), (String) arrayList8.get(i4));
                    }
                    if (Log.isLoggable("FragmentManager", 2)) {
                        for (String str7 : arrayList8) {
                        }
                        for (String str8 : arrayList5) {
                        }
                    }
                    nb nbVar3 = new nb(0);
                    View view9 = ww0Var3.M;
                    v03.i(view9, "firstOut.fragment.mView");
                    j(nbVar3, view9);
                    nbVar3.l(arrayList5);
                    nbVar2.l(nbVar3.keySet());
                    final nb nbVar4 = new nb(0);
                    View view10 = ww0Var2.M;
                    v03.i(view10, "lastIn.fragment.mView");
                    j(nbVar4, view10);
                    nbVar4.l(arrayList8);
                    nbVar4.l(nbVar2.values());
                    by0 by0Var = wx0.a;
                    for (int i5 = nbVar2.k - 1; -1 < i5; i5--) {
                        if (!nbVar4.containsKey((String) nbVar2.i(i5))) {
                            nbVar2.g(i5);
                        }
                    }
                    k(nbVar3, nbVar2.keySet());
                    k(nbVar4, nbVar2.values());
                    if (nbVar2.isEmpty()) {
                        arrayList13.clear();
                        arrayList14.clear();
                        it4 = it5;
                        str = str5;
                        arrayList10 = arrayList4;
                        linkedHashMap = linkedHashMap2;
                        view6 = view8;
                        rect = rect2;
                        dy0Var2 = dy0Var3;
                        obj4 = null;
                    } else {
                        kq1.a(viewGroup, new Runnable(bf2Var6, z, nbVar4) { // from class: p.u70
                            public final /* synthetic */ bf2 e;
                            public final /* synthetic */ nb k;

                            {
                                this.k = nbVar4;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                v03.j(this.k, "$lastInViews");
                                ww0 ww0Var4 = this.c.c;
                                ww0 ww0Var5 = this.e.c;
                                by0 by0Var2 = wx0.a;
                                v03.j(ww0Var4, "inFragment");
                                v03.j(ww0Var5, "outFragment");
                            }
                        });
                        arrayList13.addAll(nbVar3.values());
                        if (!arrayList5.isEmpty()) {
                            View view11 = (View) nbVar3.get((String) arrayList5.get(0));
                            dy0Var = dy0Var3;
                            obj = objR;
                            dy0Var.m(view11, obj);
                            view7 = view11;
                        } else {
                            dy0Var = dy0Var3;
                            obj = objR;
                        }
                        arrayList14.addAll(nbVar4.values());
                        if (!(!arrayList8.isEmpty()) || (view3 = (View) nbVar4.get((String) arrayList8.get(0))) == null) {
                            rect = rect2;
                        } else {
                            rect = rect2;
                            kq1.a(viewGroup, new kc0(dy0Var, view3, rect, 2));
                            z2 = true;
                        }
                        view6 = view8;
                        dy0Var.p(obj, view6, arrayList13);
                        dy0Var.l(obj, null, null, obj, arrayList14);
                        Boolean bool = Boolean.TRUE;
                        linkedHashMap = linkedHashMap2;
                        linkedHashMap.put(bf2Var6, bool);
                        linkedHashMap.put(bf2Var8, bool);
                        dy0Var2 = dy0Var;
                        obj4 = obj;
                        it4 = it5;
                        str = str5;
                        arrayList10 = arrayList4;
                    }
                }
            }
            String str9 = str;
            ArrayList arrayList15 = arrayList10;
            dy0 dy0Var4 = dy0Var2;
            ArrayList arrayList16 = new ArrayList();
            Iterator it6 = arrayList15.iterator();
            Object objJ = null;
            Object objJ2 = null;
            while (it6.hasNext()) {
                Iterator it7 = it6;
                x70 x70Var3 = (x70) it6.next();
                if (x70Var3.e()) {
                    nbVar = nbVar2;
                    str4 = str6;
                    linkedHashMap.put((bf2) x70Var3.a, Boolean.FALSE);
                    x70Var3.b();
                } else {
                    nbVar = nbVar2;
                    str4 = str6;
                    Object objF = dy0Var4.f(x70Var3.c);
                    bf2 bf2Var10 = (bf2) x70Var3.a;
                    boolean z3 = obj4 != null && (bf2Var10 == bf2Var6 || bf2Var10 == bf2Var8);
                    if (objF != null) {
                        bf2 bf2Var11 = bf2Var8;
                        ArrayList arrayList17 = new ArrayList();
                        Object obj6 = obj4;
                        View view12 = bf2Var10.c.M;
                        Object obj7 = objJ2;
                        String str10 = str9;
                        v03.i(view12, str10);
                        i(arrayList17, view12);
                        if (z3) {
                            if (bf2Var10 == bf2Var6) {
                                arrayList17.removeAll(ou.q0(arrayList13));
                            } else {
                                arrayList17.removeAll(ou.q0(arrayList14));
                            }
                        }
                        if (arrayList17.isEmpty()) {
                            dy0Var4.a(view6, objF);
                            view = view6;
                            str9 = str10;
                        } else {
                            dy0Var4.b(objF, arrayList17);
                            dy0Var4.l(objF, objF, arrayList17, null, null);
                            str9 = str10;
                            SpecialEffectsController$Operation$State specialEffectsController$Operation$State5 = specialEffectsController$Operation$State2;
                            if (bf2Var10.a == specialEffectsController$Operation$State5) {
                                arrayList3.remove(bf2Var10);
                                view = view6;
                                ArrayList arrayList18 = new ArrayList(arrayList17);
                                ww0 ww0Var4 = bf2Var10.c;
                                specialEffectsController$Operation$State2 = specialEffectsController$Operation$State5;
                                arrayList18.remove(ww0Var4.M);
                                dy0Var4.k(objF, ww0Var4.M, arrayList18);
                                kq1.a(viewGroup, new nv(5, arrayList17));
                            } else {
                                view = view6;
                                specialEffectsController$Operation$State2 = specialEffectsController$Operation$State5;
                            }
                        }
                        SpecialEffectsController$Operation$State specialEffectsController$Operation$State6 = specialEffectsController$Operation$State4;
                        if (bf2Var10.a == specialEffectsController$Operation$State6) {
                            arrayList16.addAll(arrayList17);
                            if (z2) {
                                dy0Var4.n(objF, rect);
                            }
                            view2 = view7;
                        } else {
                            view2 = view7;
                            dy0Var4.m(view2, objF);
                        }
                        linkedHashMap.put(bf2Var10, Boolean.TRUE);
                        if (x70Var3.d) {
                            objJ = dy0Var4.j(objJ, objF);
                            it6 = it7;
                            view7 = view2;
                            specialEffectsController$Operation$State4 = specialEffectsController$Operation$State6;
                            view6 = view;
                            nbVar2 = nbVar;
                            str6 = str4;
                            bf2Var8 = bf2Var11;
                            obj4 = obj6;
                            objJ2 = obj7;
                        } else {
                            it6 = it7;
                            view7 = view2;
                            specialEffectsController$Operation$State4 = specialEffectsController$Operation$State6;
                            view6 = view;
                            str6 = str4;
                            bf2Var8 = bf2Var11;
                            obj4 = obj6;
                            objJ2 = dy0Var4.j(obj7, objF);
                            nbVar2 = nbVar;
                        }
                    } else if (!z3) {
                        linkedHashMap.put(bf2Var10, Boolean.FALSE);
                        x70Var3.b();
                    }
                }
                it6 = it7;
                nbVar2 = nbVar;
                str6 = str4;
            }
            nb nbVar5 = nbVar2;
            bf2Var = bf2Var8;
            String str11 = str6;
            Object objI = dy0Var4.i(objJ, objJ2, obj4);
            if (objI == null) {
                bf2Var2 = bf2Var6;
                str2 = str11;
            } else {
                ArrayList<x70> arrayList19 = new ArrayList();
                for (Object obj8 : arrayList15) {
                    if (!((x70) obj8).e()) {
                        arrayList19.add(obj8);
                    }
                }
                for (x70 x70Var4 : arrayList19) {
                    Object obj9 = x70Var4.c;
                    bf2 bf2Var12 = (bf2) x70Var4.a;
                    bf2 bf2Var13 = bf2Var;
                    boolean z4 = obj4 != null && (bf2Var12 == bf2Var6 || bf2Var12 == bf2Var13);
                    if (obj9 != null || z4) {
                        WeakHashMap weakHashMap = zt2.a;
                        if (lt2.c(viewGroup)) {
                            str3 = str11;
                            ww0 ww0Var5 = ((bf2) x70Var4.a).c;
                            dy0Var4.o(objI, new ua(x70Var4, 2, bf2Var12));
                        } else {
                            str3 = str11;
                            if (Log.isLoggable(str3, 2)) {
                                Objects.toString(viewGroup);
                                Objects.toString(bf2Var12);
                            }
                            x70Var4.b();
                        }
                    } else {
                        str3 = str11;
                    }
                    str11 = str3;
                    bf2Var = bf2Var13;
                }
                str2 = str11;
                bf2 bf2Var14 = bf2Var;
                WeakHashMap weakHashMap2 = zt2.a;
                if (lt2.c(viewGroup)) {
                    wx0.a(4, arrayList16);
                    ArrayList arrayList20 = new ArrayList();
                    int size3 = arrayList14.size();
                    for (int i6 = 0; i6 < size3; i6++) {
                        View view13 = (View) arrayList14.get(i6);
                        WeakHashMap weakHashMap3 = zt2.a;
                        arrayList20.add(ot2.k(view13));
                        ot2.v(view13, null);
                    }
                    if (Log.isLoggable(str2, 2)) {
                        for (Object obj10 : arrayList13) {
                            v03.i(obj10, "sharedElementFirstOutViews");
                            View view14 = (View) obj10;
                            view14.toString();
                            ot2.k(view14);
                        }
                        for (Object obj11 : arrayList14) {
                            v03.i(obj11, "sharedElementLastInViews");
                            View view15 = (View) obj11;
                            view15.toString();
                            ot2.k(view15);
                        }
                    }
                    dy0Var4.c(viewGroup, objI);
                    int size4 = arrayList14.size();
                    ArrayList arrayList21 = new ArrayList();
                    int i7 = 0;
                    while (i7 < size4) {
                        View view16 = (View) arrayList13.get(i7);
                        WeakHashMap weakHashMap4 = zt2.a;
                        String strK = ot2.k(view16);
                        arrayList21.add(strK);
                        if (strK == null) {
                            bf2Var4 = bf2Var6;
                            bf2Var3 = bf2Var14;
                        } else {
                            bf2Var3 = bf2Var14;
                            ot2.v(view16, null);
                            nb nbVar6 = nbVar5;
                            String str12 = (String) nbVar6.get(strK);
                            nbVar5 = nbVar6;
                            int i8 = 0;
                            while (true) {
                                bf2Var4 = bf2Var6;
                                if (i8 >= size4) {
                                    break;
                                }
                                if (str12.equals(arrayList20.get(i8))) {
                                    ot2.v((View) arrayList14.get(i8), strK);
                                    break;
                                } else {
                                    i8++;
                                    bf2Var6 = bf2Var4;
                                }
                            }
                        }
                        i7++;
                        bf2Var6 = bf2Var4;
                        bf2Var14 = bf2Var3;
                    }
                    bf2Var2 = bf2Var6;
                    bf2Var = bf2Var14;
                    kq1.a(viewGroup, new cy0(size4, arrayList14, arrayList20, arrayList13, arrayList21));
                    wx0.a(0, arrayList16);
                    dy0Var4.q(obj4, arrayList13, arrayList14);
                } else {
                    bf2Var2 = bf2Var6;
                    bf2Var = bf2Var14;
                }
            }
        }
        boolean zContainsValue = linkedHashMap.containsValue(Boolean.TRUE);
        Context context = viewGroup.getContext();
        ArrayList<w70> arrayList22 = new ArrayList();
        boolean z5 = false;
        for (w70 w70Var : arrayList2) {
            if (w70Var.e()) {
                w70Var.b();
            } else {
                v03.i(context, "context");
                g90 g90VarH = w70Var.h(context);
                if (g90VarH == null) {
                    w70Var.b();
                } else {
                    Animator animator = (Animator) g90VarH.k;
                    if (animator == null) {
                        arrayList22.add(w70Var);
                    } else {
                        bf2 bf2Var15 = (bf2) w70Var.a;
                        ww0 ww0Var6 = bf2Var15.c;
                        if (v03.b(linkedHashMap.get(bf2Var15), Boolean.TRUE)) {
                            if (Log.isLoggable(str2, 2)) {
                                Objects.toString(ww0Var6);
                            }
                            w70Var.b();
                        } else {
                            SpecialEffectsController$Operation$State specialEffectsController$Operation$State7 = specialEffectsController$Operation$State2;
                            boolean z6 = bf2Var15.a == specialEffectsController$Operation$State7;
                            ArrayList arrayList23 = arrayList3;
                            if (z6) {
                                arrayList23.remove(bf2Var15);
                            }
                            View view17 = ww0Var6.M;
                            viewGroup.startViewTransition(view17);
                            LinkedHashMap linkedHashMap3 = linkedHashMap;
                            animator.addListener(new y70(this, view17, z6, bf2Var15, w70Var));
                            animator.setTarget(view17);
                            animator.start();
                            if (Log.isLoggable(str2, 2)) {
                                bf2Var15.toString();
                            }
                            ((cq) w70Var.b).b(new rm(animator, 0, bf2Var15));
                            specialEffectsController$Operation$State2 = specialEffectsController$Operation$State7;
                            arrayList3 = arrayList23;
                            linkedHashMap = linkedHashMap3;
                            z5 = true;
                        }
                    }
                }
            }
        }
        ArrayList<bf2> arrayList24 = arrayList3;
        for (w70 w70Var2 : arrayList22) {
            bf2 bf2Var16 = (bf2) w70Var2.a;
            ww0 ww0Var7 = bf2Var16.c;
            if (zContainsValue) {
                if (Log.isLoggable(str2, 2)) {
                    Objects.toString(ww0Var7);
                }
                w70Var2.b();
            } else if (z5) {
                if (Log.isLoggable(str2, 2)) {
                    Objects.toString(ww0Var7);
                }
                w70Var2.b();
            } else {
                View view18 = ww0Var7.M;
                v03.i(context, "context");
                g90 g90VarH2 = w70Var2.h(context);
                if (g90VarH2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Animation animation = (Animation) g90VarH2.e;
                if (animation == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                if (bf2Var16.a != SpecialEffectsController$Operation$State.c) {
                    view18.startAnimation(animation);
                    w70Var2.b();
                    aVar = this;
                } else {
                    viewGroup.startViewTransition(view18);
                    ax0 ax0Var = new ax0(animation, viewGroup, view18);
                    aVar = this;
                    ax0Var.setAnimationListener(new z70(view18, w70Var2, aVar, bf2Var16));
                    view18.startAnimation(ax0Var);
                    if (Log.isLoggable(str2, 2)) {
                        bf2Var16.toString();
                    }
                }
                ((cq) w70Var2.b).b(new v70(view18, aVar, w70Var2, bf2Var16));
            }
        }
        for (bf2 bf2Var17 : arrayList24) {
            View view19 = bf2Var17.c.M;
            SpecialEffectsController$Operation$State specialEffectsController$Operation$State8 = bf2Var17.a;
            v03.i(view19, Search.Type.VIEW);
            specialEffectsController$Operation$State8.a(view19);
        }
        arrayList24.clear();
        if (Log.isLoggable(str2, 2)) {
            Objects.toString(bf2Var2);
            Objects.toString(bf2Var);
        }
    }
}
