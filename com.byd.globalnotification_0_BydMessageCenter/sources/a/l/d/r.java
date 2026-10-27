package a.l.d;

import a.b.k.l;
import a.h.g.a;
import a.l.d.g0;
import a.l.d.v;
import a.l.d.z;
import a.n.g;
import a.o.a.b;
import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcher.LifecycleOnBackPressedCancellable;
import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public ArrayList<Fragment> A;
    public ArrayList<f> B;
    public v C;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1026b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<a.l.d.a> f1028d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<Fragment> f1029e;
    public OnBackPressedDispatcher g;
    public o<?> n;
    public k o;
    public Fragment p;
    public Fragment q;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public ArrayList<a.l.d.a> y;
    public ArrayList<Boolean> z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<e> f1025a = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y f1027c = new y();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p f1030f = new p(this);
    public final a.a.b h = new a(false);
    public final AtomicInteger i = new AtomicInteger();
    public ConcurrentHashMap<Fragment, HashSet<a.h.g.a>> j = new ConcurrentHashMap<>();
    public final g0.a k = new b();
    public final q l = new q(this);
    public int m = -1;
    public n r = null;
    public n s = new c();
    public Runnable D = new d();

    public class a extends a.a.b {
        public a(boolean z) {
            super(z);
        }
    }

    public class b implements g0.a {
        public b() {
        }

        public void a(Fragment fragment, a.h.g.a aVar) {
            boolean z;
            synchronized (aVar) {
                z = aVar.f728a;
            }
            if (z) {
                return;
            }
            r rVar = r.this;
            HashSet<a.h.g.a> hashSet = rVar.j.get(fragment);
            if (hashSet != null && hashSet.remove(aVar) && hashSet.isEmpty()) {
                rVar.j.remove(fragment);
                if (fragment.f1553a < 3) {
                    rVar.h(fragment);
                    rVar.Q(fragment, fragment.s());
                }
            }
        }

        public void b(Fragment fragment, a.h.g.a aVar) {
            r rVar = r.this;
            if (rVar.j.get(fragment) == null) {
                rVar.j.put(fragment, new HashSet<>());
            }
            rVar.j.get(fragment).add(aVar);
        }
    }

    public class c extends n {
        public c() {
        }

        @Override // a.l.d.n
        public Fragment a(ClassLoader classLoader, String str) {
            o<?> oVar = r.this.n;
            Context context = oVar.f1018b;
            if (oVar != null) {
                return Fragment.u(context, str, null);
            }
            throw null;
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r.this.A(true);
        }
    }

    public interface e {
        boolean a(ArrayList<a.l.d.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    public static class f implements Fragment.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f1035a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a.l.d.a f1036b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1037c;

        public f(a.l.d.a aVar, boolean z) {
            this.f1035a = z;
            this.f1036b = aVar;
        }

        public void a() {
            boolean z = this.f1037c > 0;
            for (Fragment fragment : this.f1036b.q.f1027c.g()) {
                fragment.M(null);
                if (z) {
                    Fragment.b bVar = fragment.I;
                    if (bVar == null ? false : bVar.p) {
                        fragment.O();
                    }
                }
            }
            a.l.d.a aVar = this.f1036b;
            aVar.q.g(aVar, this.f1035a, !z, true);
        }
    }

    public static boolean J(int i) {
        return Log.isLoggable("FragmentManager", i);
    }

    public boolean A(boolean z) {
        boolean zA;
        z(z);
        boolean z2 = false;
        while (true) {
            ArrayList<a.l.d.a> arrayList = this.y;
            ArrayList<Boolean> arrayList2 = this.z;
            synchronized (this.f1025a) {
                if (this.f1025a.isEmpty()) {
                    zA = false;
                } else {
                    int size = this.f1025a.size();
                    zA = false;
                    for (int i = 0; i < size; i++) {
                        zA |= this.f1025a.get(i).a(arrayList, arrayList2);
                    }
                    this.f1025a.clear();
                    this.n.f1019c.removeCallbacks(this.D);
                }
            }
            if (!zA) {
                e0();
                w();
                this.f1027c.b();
                return z2;
            }
            this.f1026b = true;
            try {
                U(this.y, this.z);
                f();
                z2 = true;
            } catch (Throwable th) {
                f();
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0156  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void B(java.util.ArrayList<a.l.d.a> r21, java.util.ArrayList<java.lang.Boolean> r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 742
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.l.d.r.B(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    public final void C(ArrayList<a.l.d.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<f> arrayList3 = this.B;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i = 0;
        while (i < size) {
            f fVar = this.B.get(i);
            if (arrayList == null || fVar.f1035a || (iIndexOf2 = arrayList.indexOf(fVar.f1036b)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf2).booleanValue()) {
                if ((fVar.f1037c == 0) || (arrayList != null && fVar.f1036b.i(arrayList, 0, arrayList.size()))) {
                    this.B.remove(i);
                    i--;
                    size--;
                    if (arrayList == null || fVar.f1035a || (iIndexOf = arrayList.indexOf(fVar.f1036b)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf).booleanValue()) {
                        fVar.a();
                    }
                }
                i++;
            } else {
                this.B.remove(i);
                i--;
                size--;
            }
            a.l.d.a aVar = fVar.f1036b;
            aVar.q.g(aVar, fVar.f1035a, false, false);
            i++;
        }
    }

    public Fragment D(String str) {
        return this.f1027c.e(str);
    }

    public Fragment E(int i) {
        y yVar = this.f1027c;
        int size = yVar.f1059a.size();
        while (true) {
            size--;
            if (size < 0) {
                for (x xVar : yVar.f1060b.values()) {
                    if (xVar != null) {
                        Fragment fragment = xVar.f1057b;
                        if (fragment.v == i) {
                            return fragment;
                        }
                    }
                }
                return null;
            }
            Fragment fragment2 = yVar.f1059a.get(size);
            if (fragment2 != null && fragment2.v == i) {
                return fragment2;
            }
        }
    }

    public Fragment F(String str) {
        Fragment fragmentH;
        for (x xVar : this.f1027c.f1060b.values()) {
            if (xVar != null && (fragmentH = xVar.f1057b.h(str)) != null) {
                return fragmentH;
            }
        }
        return null;
    }

    public final ViewGroup G(Fragment fragment) {
        if (fragment.w > 0 && this.o.f()) {
            View viewB = this.o.b(fragment.w);
            if (viewB instanceof ViewGroup) {
                return (ViewGroup) viewB;
            }
        }
        return null;
    }

    public n H() {
        n nVar = this.r;
        if (nVar != null) {
            return nVar;
        }
        Fragment fragment = this.p;
        return fragment != null ? fragment.r.H() : this.s;
    }

    public void I(Fragment fragment) {
        if (J(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.y) {
            return;
        }
        fragment.y = true;
        fragment.K = true ^ fragment.K;
        b0(fragment);
    }

    public final boolean K(Fragment fragment) {
        boolean z;
        r rVar = fragment.t;
        Iterator it = ((ArrayList) rVar.f1027c.f()).iterator();
        boolean zK = false;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Fragment fragment2 = (Fragment) it.next();
            if (fragment2 != null) {
                zK = rVar.K(fragment2);
            }
            if (zK) {
                z = true;
                break;
            }
        }
        return z;
    }

    public boolean L(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        r rVar = fragment.r;
        return fragment.equals(rVar.q) && L(rVar.p);
    }

    public boolean M() {
        return this.u || this.v;
    }

    public void N(Fragment fragment) {
        boolean z;
        String str;
        if (this.f1027c.c(fragment.f1557e)) {
            return;
        }
        x xVar = new x(this.l, fragment);
        xVar.a(this.n.f1018b.getClassLoader());
        this.f1027c.f1060b.put(xVar.f1057b.f1557e, xVar);
        if (fragment.B) {
            if (!fragment.A) {
                V(fragment);
            } else if (!M()) {
                v vVar = this.C;
                if (vVar.f1046c.containsKey(fragment.f1557e)) {
                    z = false;
                } else {
                    vVar.f1046c.put(fragment.f1557e, fragment);
                    z = true;
                }
                if (z && J(2)) {
                    str = "Updating retained Fragments: Added " + fragment;
                    Log.v("FragmentManager", str);
                }
            } else if (J(2)) {
                str = "Ignoring addRetainedFragment as the state is already saved";
                Log.v("FragmentManager", str);
            }
            fragment.B = false;
        }
        xVar.f1058c = this.m;
        if (J(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    public void O(Fragment fragment) {
        Animator animator;
        if (!this.f1027c.c(fragment.f1557e)) {
            if (J(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.m + "since it is not added to " + this);
                return;
            }
            return;
        }
        Q(fragment, this.m);
        View view = fragment.F;
        if (view != null) {
            y yVar = this.f1027c;
            Fragment fragment2 = null;
            if (yVar == null) {
                throw null;
            }
            ViewGroup viewGroup = fragment.E;
            if (viewGroup != null && view != null) {
                int iIndexOf = yVar.f1059a.indexOf(fragment);
                while (true) {
                    iIndexOf--;
                    if (iIndexOf < 0) {
                        break;
                    }
                    Fragment fragment3 = yVar.f1059a.get(iIndexOf);
                    if (fragment3.E == viewGroup && fragment3.F != null) {
                        fragment2 = fragment3;
                        break;
                    }
                }
            }
            if (fragment2 != null) {
                View view2 = fragment2.F;
                ViewGroup viewGroup2 = fragment.E;
                int iIndexOfChild = viewGroup2.indexOfChild(view2);
                int iIndexOfChild2 = viewGroup2.indexOfChild(fragment.F);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup2.removeViewAt(iIndexOfChild2);
                    viewGroup2.addView(fragment.F, iIndexOfChild);
                }
            }
            if (fragment.J && fragment.E != null) {
                float f2 = fragment.L;
                if (f2 > 0.0f) {
                    fragment.F.setAlpha(f2);
                }
                fragment.L = 0.0f;
                fragment.J = false;
                i iVarT = l.i.T(this.n.f1018b, this.o, fragment, true);
                if (iVarT != null) {
                    Animation animation = iVarT.f995a;
                    if (animation != null) {
                        fragment.F.startAnimation(animation);
                    } else {
                        iVarT.f996b.setTarget(fragment.F);
                        iVarT.f996b.start();
                    }
                }
            }
        }
        if (fragment.K) {
            if (fragment.F != null) {
                i iVarT2 = l.i.T(this.n.f1018b, this.o, fragment, !fragment.y);
                if (iVarT2 == null || (animator = iVarT2.f996b) == null) {
                    if (iVarT2 != null) {
                        fragment.F.startAnimation(iVarT2.f995a);
                        iVarT2.f995a.start();
                    }
                    fragment.F.setVisibility((!fragment.y || fragment.v()) ? 0 : 8);
                    if (fragment.v()) {
                        fragment.K(false);
                    }
                } else {
                    animator.setTarget(fragment.F);
                    if (!fragment.y) {
                        fragment.F.setVisibility(0);
                    } else if (fragment.v()) {
                        fragment.K(false);
                    } else {
                        ViewGroup viewGroup3 = fragment.E;
                        View view3 = fragment.F;
                        viewGroup3.startViewTransition(view3);
                        iVarT2.f996b.addListener(new s(this, viewGroup3, view3, fragment));
                    }
                    iVarT2.f996b.start();
                }
            }
            if (fragment.k && K(fragment)) {
                this.t = true;
            }
            fragment.K = false;
        }
    }

    public void P(int i, boolean z) {
        o<?> oVar;
        if (this.n == null && i != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.m) {
            this.m = i;
            Iterator<Fragment> it = this.f1027c.g().iterator();
            while (it.hasNext()) {
                O(it.next());
            }
            for (Fragment fragment : (ArrayList) this.f1027c.f()) {
                if (fragment != null && !fragment.J) {
                    O(fragment);
                }
            }
            d0();
            if (this.t && (oVar = this.n) != null && this.m == 4) {
                a.l.d.e.this.p();
                this.t = false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x076f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0232  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Q(androidx.fragment.app.Fragment r14, int r15) {
        /*
            Method dump skipped, instruction units count: 2118
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.l.d.r.Q(androidx.fragment.app.Fragment, int):void");
    }

    public void R() {
        this.u = false;
        this.v = false;
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                fragment.t.R();
            }
        }
    }

    public boolean S() {
        int size;
        boolean z = false;
        A(false);
        z(true);
        Fragment fragment = this.q;
        if (fragment != null && fragment.k().S()) {
            return true;
        }
        ArrayList<a.l.d.a> arrayList = this.y;
        ArrayList<Boolean> arrayList2 = this.z;
        ArrayList<a.l.d.a> arrayList3 = this.f1028d;
        if (arrayList3 != null && (size = arrayList3.size() - 1) >= 0) {
            arrayList.add(this.f1028d.remove(size));
            arrayList2.add(Boolean.TRUE);
            z = true;
        }
        if (z) {
            this.f1026b = true;
            try {
                U(this.y, this.z);
            } finally {
                f();
            }
        }
        e0();
        w();
        this.f1027c.b();
        return z;
    }

    public void T(Fragment fragment) {
        if (J(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.q);
        }
        boolean z = !fragment.w();
        if (!fragment.z || z) {
            this.f1027c.h(fragment);
            if (K(fragment)) {
                this.t = true;
            }
            fragment.l = true;
            b0(fragment);
        }
    }

    public final void U(ArrayList<a.l.d.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        C(arrayList, arrayList2);
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            if (!arrayList.get(i).o) {
                if (i2 != i) {
                    B(arrayList, arrayList2, i2, i);
                }
                i2 = i + 1;
                if (arrayList2.get(i).booleanValue()) {
                    while (i2 < size && arrayList2.get(i2).booleanValue() && !arrayList.get(i2).o) {
                        i2++;
                    }
                }
                B(arrayList, arrayList2, i, i2);
                i = i2 - 1;
            }
            i++;
        }
        if (i2 != size) {
            B(arrayList, arrayList2, i2, size);
        }
    }

    public void V(Fragment fragment) {
        if (M()) {
            if (J(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.C.f1046c.remove(fragment.f1557e) != null) && J(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    public void W(Parcelable parcelable) {
        x xVar;
        if (parcelable == null) {
            return;
        }
        u uVar = (u) parcelable;
        if (uVar.f1041a == null) {
            return;
        }
        this.f1027c.f1060b.clear();
        for (w wVar : uVar.f1041a) {
            if (wVar != null) {
                Fragment fragment = this.C.f1046c.get(wVar.f1051b);
                if (fragment != null) {
                    if (J(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
                    }
                    xVar = new x(this.l, fragment, wVar);
                } else {
                    xVar = new x(this.l, this.n.f1018b.getClassLoader(), H(), wVar);
                }
                Fragment fragment2 = xVar.f1057b;
                fragment2.r = this;
                if (J(2)) {
                    StringBuilder sbD = b.a.a.a.a.d("restoreSaveState: active (");
                    sbD.append(fragment2.f1557e);
                    sbD.append("): ");
                    sbD.append(fragment2);
                    Log.v("FragmentManager", sbD.toString());
                }
                xVar.a(this.n.f1018b.getClassLoader());
                this.f1027c.f1060b.put(xVar.f1057b.f1557e, xVar);
                xVar.f1058c = this.m;
            }
        }
        for (Fragment fragment3 : this.C.f1046c.values()) {
            if (!this.f1027c.c(fragment3.f1557e)) {
                if (J(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment3 + " that was not found in the set of active Fragments " + uVar.f1041a);
                }
                Q(fragment3, 1);
                fragment3.l = true;
                Q(fragment3, -1);
            }
        }
        y yVar = this.f1027c;
        ArrayList<String> arrayList = uVar.f1042b;
        yVar.f1059a.clear();
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragmentE = yVar.e(str);
                if (fragmentE == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (J(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragmentE);
                }
                yVar.a(fragmentE);
            }
        }
        if (uVar.f1043c != null) {
            this.f1028d = new ArrayList<>(uVar.f1043c.length);
            int i = 0;
            while (true) {
                a.l.d.b[] bVarArr = uVar.f1043c;
                if (i >= bVarArr.length) {
                    break;
                }
                a.l.d.b bVar = bVarArr[i];
                if (bVar == null) {
                    throw null;
                }
                a.l.d.a aVar = new a.l.d.a(this);
                int i2 = 0;
                int i3 = 0;
                while (i2 < bVar.f934a.length) {
                    z.a aVar2 = new z.a();
                    int i4 = i2 + 1;
                    aVar2.f1067a = bVar.f934a[i2];
                    if (J(2)) {
                        Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i3 + " base fragment #" + bVar.f934a[i4]);
                    }
                    String str2 = bVar.f935b.get(i3);
                    aVar2.f1068b = str2 != null ? this.f1027c.e(str2) : null;
                    aVar2.g = g.b.values()[bVar.f936c[i3]];
                    aVar2.h = g.b.values()[bVar.f937d[i3]];
                    int[] iArr = bVar.f934a;
                    int i5 = i4 + 1;
                    int i6 = iArr[i4];
                    aVar2.f1069c = i6;
                    int i7 = i5 + 1;
                    int i8 = iArr[i5];
                    aVar2.f1070d = i8;
                    int i9 = i7 + 1;
                    int i10 = iArr[i7];
                    aVar2.f1071e = i10;
                    int i11 = iArr[i9];
                    aVar2.f1072f = i11;
                    aVar.f1062b = i6;
                    aVar.f1063c = i8;
                    aVar.f1064d = i10;
                    aVar.f1065e = i11;
                    aVar.b(aVar2);
                    i3++;
                    i2 = i9 + 1;
                }
                aVar.f1066f = bVar.f938e;
                aVar.h = bVar.f939f;
                aVar.r = bVar.g;
                aVar.g = true;
                aVar.i = bVar.h;
                aVar.j = bVar.i;
                aVar.k = bVar.j;
                aVar.l = bVar.k;
                aVar.m = bVar.l;
                aVar.n = bVar.m;
                aVar.o = bVar.n;
                aVar.d(1);
                if (J(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i + " (index " + aVar.r + "): " + aVar);
                    PrintWriter printWriter = new PrintWriter(new a.h.j.b("FragmentManager"));
                    aVar.e("  ", printWriter, false);
                    printWriter.close();
                }
                this.f1028d.add(aVar);
                i++;
            }
        } else {
            this.f1028d = null;
        }
        this.i.set(uVar.f1044d);
        String str3 = uVar.f1045e;
        if (str3 != null) {
            Fragment fragmentE2 = this.f1027c.e(str3);
            this.q = fragmentE2;
            s(fragmentE2);
        }
    }

    public Parcelable X() {
        ArrayList<String> arrayList;
        int size;
        if (this.B != null) {
            while (!this.B.isEmpty()) {
                this.B.remove(0).a();
            }
        }
        y();
        A(true);
        this.u = true;
        y yVar = this.f1027c;
        a.l.d.b[] bVarArr = null;
        if (yVar == null) {
            throw null;
        }
        ArrayList<w> arrayList2 = new ArrayList<>(yVar.f1060b.size());
        for (x xVar : yVar.f1060b.values()) {
            if (xVar != null) {
                Fragment fragment = xVar.f1057b;
                w wVar = new w(fragment);
                if (xVar.f1057b.f1553a <= -1 || wVar.m != null) {
                    wVar.m = xVar.f1057b.f1554b;
                } else {
                    Bundle bundle = new Bundle();
                    Fragment fragment2 = xVar.f1057b;
                    fragment2.B(bundle);
                    fragment2.T.b(bundle);
                    Parcelable parcelableX = fragment2.t.X();
                    if (parcelableX != null) {
                        bundle.putParcelable("android:support:fragments", parcelableX);
                    }
                    xVar.f1056a.j(xVar.f1057b, bundle, false);
                    if (bundle.isEmpty()) {
                        bundle = null;
                    }
                    if (xVar.f1057b.F != null) {
                        xVar.b();
                    }
                    if (xVar.f1057b.f1555c != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putSparseParcelableArray("android:view_state", xVar.f1057b.f1555c);
                    }
                    if (!xVar.f1057b.H) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putBoolean("android:user_visible_hint", xVar.f1057b.H);
                    }
                    wVar.m = bundle;
                    if (xVar.f1057b.h != null) {
                        if (bundle == null) {
                            wVar.m = new Bundle();
                        }
                        wVar.m.putString("android:target_state", xVar.f1057b.h);
                        int i = xVar.f1057b.i;
                        if (i != 0) {
                            wVar.m.putInt("android:target_req_state", i);
                        }
                    }
                }
                arrayList2.add(wVar);
                if (J(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + wVar.m);
                }
            }
        }
        if (arrayList2.isEmpty()) {
            if (J(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        y yVar2 = this.f1027c;
        synchronized (yVar2.f1059a) {
            if (yVar2.f1059a.isEmpty()) {
                arrayList = null;
            } else {
                arrayList = new ArrayList<>(yVar2.f1059a.size());
                for (Fragment fragment3 : yVar2.f1059a) {
                    arrayList.add(fragment3.f1557e);
                    if (J(2)) {
                        Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.f1557e + "): " + fragment3);
                    }
                }
            }
        }
        ArrayList<a.l.d.a> arrayList3 = this.f1028d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            bVarArr = new a.l.d.b[size];
            for (int i2 = 0; i2 < size; i2++) {
                bVarArr[i2] = new a.l.d.b(this.f1028d.get(i2));
                if (J(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f1028d.get(i2));
                }
            }
        }
        u uVar = new u();
        uVar.f1041a = arrayList2;
        uVar.f1042b = arrayList;
        uVar.f1043c = bVarArr;
        uVar.f1044d = this.i.get();
        Fragment fragment4 = this.q;
        if (fragment4 != null) {
            uVar.f1045e = fragment4.f1557e;
        }
        return uVar;
    }

    public void Y(Fragment fragment, boolean z) {
        ViewGroup viewGroupG = G(fragment);
        if (viewGroupG == null || !(viewGroupG instanceof l)) {
            return;
        }
        ((l) viewGroupG).setDrawDisappearingViewsLast(!z);
    }

    public void Z(Fragment fragment, g.b bVar) {
        if (fragment.equals(D(fragment.f1557e)) && (fragment.s == null || fragment.r == this)) {
            fragment.O = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void a(a.e.c<Fragment> cVar) {
        int i = this.m;
        if (i < 1) {
            return;
        }
        int iMin = Math.min(i, 3);
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment.f1553a < iMin) {
                Q(fragment, iMin);
                if (fragment.F != null && !fragment.y && fragment.J) {
                    cVar.add(fragment);
                }
            }
        }
    }

    public void a0(Fragment fragment) {
        if (fragment == null || (fragment.equals(D(fragment.f1557e)) && (fragment.s == null || fragment.r == this))) {
            Fragment fragment2 = this.q;
            this.q = fragment;
            s(fragment2);
            s(this.q);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void b(Fragment fragment) {
        if (J(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        N(fragment);
        if (fragment.z) {
            return;
        }
        this.f1027c.a(fragment);
        fragment.l = false;
        if (fragment.F == null) {
            fragment.K = false;
        }
        if (K(fragment)) {
            this.t = true;
        }
    }

    public final void b0(Fragment fragment) {
        ViewGroup viewGroupG = G(fragment);
        if (viewGroupG != null) {
            if (viewGroupG.getTag(a.l.b.visible_removing_fragment_view_tag) == null) {
                viewGroupG.setTag(a.l.b.visible_removing_fragment_view_tag, fragment);
            }
            ((Fragment) viewGroupG.getTag(a.l.b.visible_removing_fragment_view_tag)).L(fragment.o());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c(o<?> oVar, k kVar, Fragment fragment) {
        if (this.n != null) {
            throw new IllegalStateException("Already attached");
        }
        this.n = oVar;
        this.o = kVar;
        this.p = fragment;
        if (fragment != null) {
            e0();
        }
        if (oVar instanceof a.a.c) {
            a.a.c cVar = (a.a.c) oVar;
            this.g = cVar.c();
            a.n.k kVar2 = cVar;
            if (fragment != null) {
                kVar2 = fragment;
            }
            OnBackPressedDispatcher onBackPressedDispatcher = this.g;
            a.a.b bVar = this.h;
            if (onBackPressedDispatcher == null) {
                throw null;
            }
            a.n.g gVarA = kVar2.a();
            if (((a.n.l) gVarA).f1094b != g.b.DESTROYED) {
                bVar.f1b.add(onBackPressedDispatcher.new LifecycleOnBackPressedCancellable(gVarA, bVar));
            }
        }
        if (fragment != null) {
            v vVar = fragment.r.C;
            v vVar2 = vVar.f1047d.get(fragment.f1557e);
            if (vVar2 == null) {
                vVar2 = new v(vVar.f1049f);
                vVar.f1047d.put(fragment.f1557e, vVar2);
            }
            this.C = vVar2;
            return;
        }
        if (!(oVar instanceof a.n.e0)) {
            this.C = new v(false);
            return;
        }
        a.n.d0 d0VarE = ((a.n.e0) oVar).e();
        Object obj = v.i;
        String canonicalName = v.class.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        String strC = b.a.a.a.a.c("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
        a.n.x xVarC = d0VarE.f1085a.get(strC);
        if (!v.class.isInstance(xVarC)) {
            xVarC = obj instanceof a.n.a0 ? ((a.n.a0) obj).c(strC, v.class) : ((v.a) obj).a(v.class);
            a.n.x xVarPut = d0VarE.f1085a.put(strC, xVarC);
            if (xVarPut != null) {
                xVarPut.d();
            }
        } else if (obj instanceof a.n.c0) {
            ((a.n.c0) obj).b(xVarC);
        }
        this.C = (v) xVarC;
    }

    public void c0(Fragment fragment) {
        if (J(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.y) {
            fragment.y = false;
            fragment.K = !fragment.K;
        }
    }

    public void d(Fragment fragment) {
        if (J(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.z) {
            fragment.z = false;
            if (fragment.k) {
                return;
            }
            this.f1027c.a(fragment);
            if (J(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (K(fragment)) {
                this.t = true;
            }
        }
    }

    public final void d0() {
        for (Fragment fragment : (ArrayList) this.f1027c.f()) {
            if (fragment != null && fragment.G) {
                if (this.f1026b) {
                    this.x = true;
                } else {
                    fragment.G = false;
                    Q(fragment, this.m);
                }
            }
        }
    }

    public final void e(Fragment fragment) {
        HashSet<a.h.g.a> hashSet = this.j.get(fragment);
        if (hashSet != null) {
            for (a.h.g.a aVar : hashSet) {
                synchronized (aVar) {
                    if (!aVar.f728a) {
                        aVar.f728a = true;
                        aVar.f730c = true;
                        a.InterfaceC0012a interfaceC0012a = aVar.f729b;
                        if (interfaceC0012a != null) {
                            try {
                                ((a.l.d.f) interfaceC0012a).a();
                            } catch (Throwable th) {
                                synchronized (aVar) {
                                    aVar.f730c = false;
                                    aVar.notifyAll();
                                    throw th;
                                }
                            }
                        }
                        synchronized (aVar) {
                            aVar.f730c = false;
                            aVar.notifyAll();
                        }
                    }
                }
            }
            hashSet.clear();
            h(fragment);
            this.j.remove(fragment);
        }
    }

    public final void e0() {
        synchronized (this.f1025a) {
            if (!this.f1025a.isEmpty()) {
                this.h.f0a = true;
                return;
            }
            a.a.b bVar = this.h;
            ArrayList<a.l.d.a> arrayList = this.f1028d;
            bVar.f0a = (arrayList != null ? arrayList.size() : 0) > 0 && L(this.p);
        }
    }

    public final void f() {
        this.f1026b = false;
        this.z.clear();
        this.y.clear();
    }

    public void g(a.l.d.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.g(z3);
        } else {
            aVar.f();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            g0.p(this, arrayList, arrayList2, 0, 1, true, this.k);
        }
        if (z3) {
            P(this.m, true);
        }
        for (Fragment fragment : (ArrayList) this.f1027c.f()) {
            if (fragment != null && fragment.F != null && fragment.J && aVar.h(fragment.w)) {
                float f2 = fragment.L;
                if (f2 > 0.0f) {
                    fragment.F.setAlpha(f2);
                }
                if (z3) {
                    fragment.L = 0.0f;
                } else {
                    fragment.L = -1.0f;
                    fragment.J = false;
                }
            }
        }
    }

    public final void h(Fragment fragment) {
        fragment.t.v(1);
        if (fragment.F != null) {
            fragment.Q.f1015a.d(g.a.ON_DESTROY);
        }
        fragment.f1553a = 1;
        fragment.D = false;
        fragment.D = true;
        b.c cVar = ((a.o.a.b) a.o.a.a.b(fragment)).f1126b;
        int i = cVar.f1128c.i();
        for (int i2 = 0; i2 < i; i2++) {
            a.n.k kVar = cVar.f1128c.j(i2).k;
        }
        fragment.p = false;
        this.l.n(fragment, false);
        fragment.E = null;
        fragment.F = null;
        fragment.Q = null;
        fragment.R.h(null);
        fragment.n = false;
    }

    public void i(Fragment fragment) {
        if (J(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.z) {
            return;
        }
        fragment.z = true;
        if (fragment.k) {
            if (J(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f1027c.h(fragment);
            if (K(fragment)) {
                this.t = true;
            }
            b0(fragment);
        }
    }

    public void j(Configuration configuration) {
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                fragment.D = true;
                fragment.t.j(configuration);
            }
        }
    }

    public boolean k(MenuItem menuItem) {
        if (this.m < 1) {
            return false;
        }
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                if (!fragment.y && fragment.t.k(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void l() {
        this.u = false;
        this.v = false;
        v(1);
    }

    public boolean m(Menu menu, MenuInflater menuInflater) {
        if (this.m < 1) {
            return false;
        }
        boolean z = false;
        ArrayList<Fragment> arrayList = null;
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                if (!fragment.y ? fragment.t.m(menu, menuInflater) | false : false) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(fragment);
                    z = true;
                }
            }
        }
        if (this.f1029e != null) {
            for (int i = 0; i < this.f1029e.size(); i++) {
                Fragment fragment2 = this.f1029e.get(i);
                if ((arrayList == null || !arrayList.contains(fragment2)) && fragment2 == null) {
                    throw null;
                }
            }
        }
        this.f1029e = arrayList;
        return z;
    }

    public void n() {
        this.w = true;
        A(true);
        y();
        v(-1);
        this.n = null;
        this.o = null;
        this.p = null;
        if (this.g != null) {
            Iterator<a.a.a> it = this.h.f1b.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.g = null;
        }
    }

    public void o() {
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                fragment.E();
            }
        }
    }

    public void p(boolean z) {
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                fragment.t.p(z);
            }
        }
    }

    public boolean q(MenuItem menuItem) {
        if (this.m < 1) {
            return false;
        }
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                if (!fragment.y && fragment.t.q(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void r(Menu menu) {
        if (this.m < 1) {
            return;
        }
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null && !fragment.y) {
                fragment.t.r(menu);
            }
        }
    }

    public final void s(Fragment fragment) {
        if (fragment == null || !fragment.equals(D(fragment.f1557e))) {
            return;
        }
        boolean zL = fragment.r.L(fragment);
        Boolean bool = fragment.j;
        if (bool == null || bool.booleanValue() != zL) {
            fragment.j = Boolean.valueOf(zL);
            r rVar = fragment.t;
            rVar.e0();
            rVar.s(rVar.q);
        }
    }

    public void t(boolean z) {
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                fragment.t.t(z);
            }
        }
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.p;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            obj = this.p;
        } else {
            sb.append(this.n.getClass().getSimpleName());
            sb.append("{");
            obj = this.n;
        }
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    public boolean u(Menu menu) {
        if (this.m < 1) {
            return false;
        }
        boolean z = false;
        for (Fragment fragment : this.f1027c.g()) {
            if (fragment != null) {
                if (!fragment.y ? fragment.t.u(menu) | false : false) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void v(int i) {
        try {
            this.f1026b = true;
            this.f1027c.d(i);
            P(i, false);
            this.f1026b = false;
            A(true);
        } catch (Throwable th) {
            this.f1026b = false;
            throw th;
        }
    }

    public final void w() {
        if (this.x) {
            this.x = false;
            d0();
        }
    }

    public void x(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String strC = b.a.a.a.a.c(str, "    ");
        y yVar = this.f1027c;
        if (yVar == null) {
            throw null;
        }
        String strC2 = b.a.a.a.a.c(str, "    ");
        if (!yVar.f1060b.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments:");
            for (x xVar : yVar.f1060b.values()) {
                printWriter.print(str);
                if (xVar != null) {
                    Fragment fragment = xVar.f1057b;
                    printWriter.println(fragment);
                    fragment.f(strC2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = yVar.f1059a.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size3; i++) {
                Fragment fragment2 = yVar.f1059a.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.f1029e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment3 = this.f1029e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<a.l.d.a> arrayList2 = this.f1028d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                a.l.d.a aVar = this.f1028d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.e(strC, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.f1025a) {
            int size4 = this.f1025a.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size4; i4++) {
                    Object obj = (e) this.f1025a.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.n);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.o);
        if (this.p != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.p);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.m);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.u);
        printWriter.print(" mStopped=");
        printWriter.print(this.v);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.w);
        if (this.t) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.t);
        }
    }

    public final void y() {
        if (this.j.isEmpty()) {
            return;
        }
        for (Fragment fragment : this.j.keySet()) {
            e(fragment);
            Q(fragment, fragment.s());
        }
    }

    public final void z(boolean z) {
        if (this.f1026b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.n == null) {
            if (!this.w) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.n.f1019c.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z && M()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.y == null) {
            this.y = new ArrayList<>();
            this.z = new ArrayList<>();
        }
        this.f1026b = true;
        try {
            C(null, null);
        } finally {
            this.f1026b = false;
        }
    }
}
