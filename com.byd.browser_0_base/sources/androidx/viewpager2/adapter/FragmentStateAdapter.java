package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.g;
import androidx.fragment.app.h;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import defpackage.C0175b;
import defpackage.b71;
import defpackage.dq0;
import defpackage.in3;
import defpackage.om3;
import defpackage.ps2;
import defpackage.q8;
import defpackage.qp0;
import defpackage.rp0;
import defpackage.sa1;
import defpackage.sp0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.e<dq0> implements ps2 {
    public final androidx.lifecycle.d h;
    public final FragmentManager i;
    public final sa1<Fragment> j;
    public final sa1<Fragment.SavedState> k;
    public final sa1<Integer> l;
    public c m;
    public final b n;
    public boolean o;
    public boolean p;

    public static abstract class a extends RecyclerView.g {
        public a(int i) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public abstract void a();

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void b() {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void c(int i, int i2, Object obj) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void d(int i, int i2) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void e(int i, int i2) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void f(int i, int i2) {
            a();
        }
    }

    public static class b {
        public final CopyOnWriteArrayList a = new CopyOnWriteArrayList();

        public static void b(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((d.b) it.next()).a();
            }
        }

        public final ArrayList a() {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((d) it.next()).getClass();
                arrayList.add(d.a);
            }
            return arrayList;
        }
    }

    public class c {
        public androidx.viewpager2.adapter.a a;
        public androidx.viewpager2.adapter.b b;
        public f c;
        public ViewPager2 d;
        public long e = -1;

        public c() {
        }

        public static ViewPager2 a(RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        public final void b(boolean z) {
            int currentItem;
            Fragment fragmentG;
            b bVar;
            FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
            if (!fragmentStateAdapter.i.L() && this.d.getScrollState() == 0) {
                sa1<Fragment> sa1Var = fragmentStateAdapter.j;
                if ((sa1Var.m() == 0) || fragmentStateAdapter.k() == 0 || (currentItem = this.d.getCurrentItem()) >= fragmentStateAdapter.k()) {
                    return;
                }
                long j = currentItem;
                if ((j != this.e || z) && (fragmentG = sa1Var.g(j)) != null && fragmentG.V()) {
                    this.e = j;
                    FragmentManager fragmentManager = fragmentStateAdapter.i;
                    fragmentManager.getClass();
                    androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
                    ArrayList<List> arrayList = new ArrayList();
                    Fragment fragment = null;
                    int i = 0;
                    while (true) {
                        int iM = sa1Var.m();
                        bVar = fragmentStateAdapter.n;
                        if (i >= iM) {
                            break;
                        }
                        long j2 = sa1Var.j(i);
                        Fragment fragmentN = sa1Var.n(i);
                        if (fragmentN.V()) {
                            if (j2 != this.e) {
                                aVar.l(fragmentN, d.b.STARTED);
                                arrayList.add(bVar.a());
                            } else {
                                fragment = fragmentN;
                            }
                            boolean z2 = j2 == this.e;
                            if (fragmentN.G != z2) {
                                fragmentN.G = z2;
                                if (fragmentN.F && fragmentN.V() && !fragmentN.W()) {
                                    fragmentN.w.M();
                                }
                            }
                        }
                        i++;
                    }
                    if (fragment != null) {
                        aVar.l(fragment, d.b.RESUMED);
                        arrayList.add(bVar.a());
                    }
                    if (aVar.a.isEmpty()) {
                        return;
                    }
                    aVar.i();
                    Collections.reverse(arrayList);
                    for (List list : arrayList) {
                        bVar.getClass();
                        b.b(list);
                    }
                }
            }
        }
    }

    public FragmentStateAdapter(FragmentActivity fragmentActivity) {
        this(fragmentActivity.x1(), fragmentActivity.j1());
    }

    public static void D(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public final boolean E(long j) {
        return j >= 0 && j < ((long) k());
    }

    public abstract Fragment F(int i);

    public final void G() {
        sa1<Fragment> sa1Var;
        sa1<Integer> sa1Var2;
        Fragment fragmentG;
        View view;
        if (!this.p || this.i.L()) {
            return;
        }
        q8 q8Var = new q8(0);
        int i = 0;
        while (true) {
            sa1Var = this.j;
            int iM = sa1Var.m();
            sa1Var2 = this.l;
            if (i >= iM) {
                break;
            }
            long j = sa1Var.j(i);
            if (!E(j)) {
                q8Var.add(Long.valueOf(j));
                sa1Var2.l(j);
            }
            i++;
        }
        if (!this.o) {
            this.p = false;
            for (int i2 = 0; i2 < sa1Var.m(); i2++) {
                long j2 = sa1Var.j(i2);
                boolean z = true;
                if (!(sa1Var2.i(j2) >= 0) && ((fragmentG = sa1Var.g(j2)) == null || (view = fragmentG.f12J) == null || view.getParent() == null)) {
                    z = false;
                }
                if (!z) {
                    q8Var.add(Long.valueOf(j2));
                }
            }
        }
        q8.a aVar = new q8.a();
        while (aVar.hasNext()) {
            J(((Long) aVar.next()).longValue());
        }
    }

    public final Long H(int i) {
        Long lValueOf = null;
        int i2 = 0;
        while (true) {
            sa1<Integer> sa1Var = this.l;
            if (i2 >= sa1Var.m()) {
                return lValueOf;
            }
            if (sa1Var.n(i2).intValue() == i) {
                if (lValueOf != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                lValueOf = Long.valueOf(sa1Var.j(i2));
            }
            i2++;
        }
    }

    public final void I(final dq0 dq0Var) {
        Fragment fragmentG = this.j.g(dq0Var.i);
        if (fragmentG == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout frameLayout = (FrameLayout) dq0Var.e;
        View view = fragmentG.f12J;
        if (!fragmentG.V() && view != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        boolean zV = fragmentG.V();
        FragmentManager fragmentManager = this.i;
        if (zV && view == null) {
            fragmentManager.m.a.add(new g.a(new rp0(this, fragmentG, frameLayout)));
            return;
        }
        if (fragmentG.V() && view.getParent() != null) {
            if (view.getParent() != frameLayout) {
                D(view, frameLayout);
                return;
            }
            return;
        }
        if (fragmentG.V()) {
            D(view, frameLayout);
            return;
        }
        if (fragmentManager.L()) {
            if (fragmentManager.H) {
                return;
            }
            this.h.a(new f() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                @Override // androidx.lifecycle.f
                public final void a(b71 b71Var, d.a aVar) {
                    FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
                    if (fragmentStateAdapter.i.L()) {
                        return;
                    }
                    b71Var.j1().b(this);
                    dq0 dq0Var2 = dq0Var;
                    FrameLayout frameLayout2 = (FrameLayout) dq0Var2.e;
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    if (om3.g.b(frameLayout2)) {
                        fragmentStateAdapter.I(dq0Var2);
                    }
                }
            });
            return;
        }
        fragmentManager.m.a.add(new g.a(new rp0(this, fragmentG, frameLayout)));
        b bVar = this.n;
        bVar.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = bVar.a.iterator();
        while (it.hasNext()) {
            ((d) it.next()).getClass();
            arrayList.add(d.a);
        }
        try {
            if (fragmentG.G) {
                fragmentG.G = false;
                if (fragmentG.F && fragmentG.V() && !fragmentG.W()) {
                    fragmentG.w.M();
                }
            }
            fragmentManager.getClass();
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
            aVar.d(0, fragmentG, "f" + dq0Var.i, 1);
            aVar.l(fragmentG, d.b.STARTED);
            aVar.i();
            this.m.b(false);
        } finally {
            b.b(arrayList);
        }
    }

    public final void J(long j) {
        ViewParent parent;
        sa1<Fragment> sa1Var = this.j;
        Fragment fragmentG = sa1Var.g(j);
        if (fragmentG == null) {
            return;
        }
        View view = fragmentG.f12J;
        if (view != null && (parent = view.getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        boolean zE = E(j);
        sa1<Fragment.SavedState> sa1Var2 = this.k;
        if (!zE) {
            sa1Var2.l(j);
        }
        if (!fragmentG.V()) {
            sa1Var.l(j);
            return;
        }
        FragmentManager fragmentManager = this.i;
        if (fragmentManager.L()) {
            this.p = true;
            return;
        }
        boolean zV = fragmentG.V();
        d.a aVar = d.a;
        b bVar = this.n;
        if (zV && E(j)) {
            bVar.getClass();
            ArrayList arrayList = new ArrayList();
            Iterator it = bVar.a.iterator();
            while (it.hasNext()) {
                ((d) it.next()).getClass();
                arrayList.add(aVar);
            }
            fragmentManager.getClass();
            h hVar = (h) ((HashMap) fragmentManager.c.f).get(fragmentG.i);
            if (hVar != null) {
                Fragment fragment = hVar.c;
                if (fragment.equals(fragmentG)) {
                    Fragment.SavedState savedState = fragment.e > -1 ? new Fragment.SavedState(hVar.o()) : null;
                    b.b(arrayList);
                    sa1Var2.k(j, savedState);
                }
            }
            fragmentManager.b0(new IllegalStateException(C0175b.i("Fragment ", fragmentG, " is not currently in the FragmentManager")));
            throw null;
        }
        bVar.getClass();
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = bVar.a.iterator();
        while (it2.hasNext()) {
            ((d) it2.next()).getClass();
            arrayList2.add(aVar);
        }
        try {
            fragmentManager.getClass();
            androidx.fragment.app.a aVar2 = new androidx.fragment.app.a(fragmentManager);
            aVar2.k(fragmentG);
            aVar2.i();
            sa1Var.l(j);
        } finally {
            b.b(arrayList2);
        }
    }

    @Override // defpackage.ps2
    public final Bundle a() {
        sa1<Fragment> sa1Var = this.j;
        int iM = sa1Var.m();
        sa1<Fragment.SavedState> sa1Var2 = this.k;
        Bundle bundle = new Bundle(sa1Var2.m() + iM);
        for (int i = 0; i < sa1Var.m(); i++) {
            long j = sa1Var.j(i);
            Fragment fragmentG = sa1Var.g(j);
            if (fragmentG != null && fragmentG.V()) {
                String str = "f#" + j;
                FragmentManager fragmentManager = this.i;
                fragmentManager.getClass();
                if (fragmentG.v != fragmentManager) {
                    fragmentManager.b0(new IllegalStateException(C0175b.i("Fragment ", fragmentG, " is not currently in the FragmentManager")));
                    throw null;
                }
                bundle.putString(str, fragmentG.i);
            }
        }
        for (int i2 = 0; i2 < sa1Var2.m(); i2++) {
            long j2 = sa1Var2.j(i2);
            if (E(j2)) {
                bundle.putParcelable("s#" + j2, sa1Var2.g(j2));
            }
        }
        return bundle;
    }

    @Override // defpackage.ps2
    public final void c(Parcelable parcelable) {
        sa1<Fragment.SavedState> sa1Var = this.k;
        if (sa1Var.m() == 0) {
            sa1<Fragment> sa1Var2 = this.j;
            if (sa1Var2.m() == 0) {
                Bundle bundle = (Bundle) parcelable;
                if (bundle.getClassLoader() == null) {
                    bundle.setClassLoader(getClass().getClassLoader());
                }
                for (String str : bundle.keySet()) {
                    if (str.startsWith("f#") && str.length() > 2) {
                        long j = Long.parseLong(str.substring(2));
                        FragmentManager fragmentManager = this.i;
                        fragmentManager.getClass();
                        String string = bundle.getString(str);
                        Fragment fragment = null;
                        if (string != null) {
                            Fragment fragmentZ = fragmentManager.z(string);
                            if (fragmentZ == null) {
                                fragmentManager.b0(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
                                throw null;
                            }
                            fragment = fragmentZ;
                        }
                        sa1Var2.k(j, fragment);
                    } else {
                        if (!(str.startsWith("s#") && str.length() > 2)) {
                            throw new IllegalArgumentException("Unexpected key in savedState: ".concat(str));
                        }
                        long j2 = Long.parseLong(str.substring(2));
                        Fragment.SavedState savedState = (Fragment.SavedState) bundle.getParcelable(str);
                        if (E(j2)) {
                            sa1Var.k(j2, savedState);
                        }
                    }
                }
                if (sa1Var2.m() == 0) {
                    return;
                }
                this.p = true;
                this.o = true;
                G();
                final Handler handler = new Handler(Looper.getMainLooper());
                final sp0 sp0Var = new sp0(this);
                this.h.a(new f() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
                    @Override // androidx.lifecycle.f
                    public final void a(b71 b71Var, d.a aVar) {
                        if (aVar == d.a.ON_DESTROY) {
                            handler.removeCallbacks(sp0Var);
                            b71Var.j1().b(this);
                        }
                    }
                });
                handler.postDelayed(sp0Var, 10000L);
                return;
            }
        }
        throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final long l(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void s(RecyclerView recyclerView) {
        if (!(this.m == null)) {
            throw new IllegalArgumentException();
        }
        final c cVar = new c();
        this.m = cVar;
        cVar.d = c.a(recyclerView);
        androidx.viewpager2.adapter.a aVar = new androidx.viewpager2.adapter.a(cVar);
        cVar.a = aVar;
        cVar.d.g.a.add(aVar);
        androidx.viewpager2.adapter.b bVar = new androidx.viewpager2.adapter.b(cVar);
        cVar.b = bVar;
        A(bVar);
        f fVar = new f() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter$FragmentMaxLifecycleEnforcer$3
            @Override // androidx.lifecycle.f
            public final void a(b71 b71Var, d.a aVar2) {
                cVar.b(false);
            }
        };
        cVar.c = fVar;
        this.h.a(fVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        Bundle bundle;
        dq0 dq0Var = (dq0) a0Var;
        long j = dq0Var.i;
        FrameLayout frameLayout = (FrameLayout) dq0Var.e;
        int id = frameLayout.getId();
        Long lH = H(id);
        sa1<Integer> sa1Var = this.l;
        if (lH != null && lH.longValue() != j) {
            J(lH.longValue());
            sa1Var.l(lH.longValue());
        }
        sa1Var.k(j, Integer.valueOf(id));
        long j2 = i;
        sa1<Fragment> sa1Var2 = this.j;
        if (!(sa1Var2.i(j2) >= 0)) {
            Fragment fragmentF = F(i);
            Fragment.SavedState savedStateG = this.k.g(j2);
            if (fragmentF.v != null) {
                throw new IllegalStateException("Fragment already added");
            }
            if (savedStateG == null || (bundle = savedStateG.e) == null) {
                bundle = null;
            }
            fragmentF.f = bundle;
            sa1Var2.k(j2, fragmentF);
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.b(frameLayout)) {
            if (frameLayout.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            frameLayout.addOnLayoutChangeListener(new qp0(this, frameLayout, dq0Var));
        }
        G();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        int i2 = dq0.y;
        FrameLayout frameLayout = new FrameLayout(recyclerView.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        frameLayout.setId(om3.e.a());
        frameLayout.setSaveEnabled(false);
        return new dq0(frameLayout);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void w(RecyclerView recyclerView) {
        c cVar = this.m;
        cVar.getClass();
        ViewPager2 viewPager2A = c.a(recyclerView);
        viewPager2A.g.a.remove(cVar.a);
        androidx.viewpager2.adapter.b bVar = cVar.b;
        FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
        fragmentStateAdapter.C(bVar);
        fragmentStateAdapter.h.b(cVar.c);
        cVar.d = null;
        this.m = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final /* bridge */ /* synthetic */ boolean x(RecyclerView.a0 a0Var) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void y(RecyclerView.a0 a0Var) {
        I((dq0) a0Var);
        G();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void z(RecyclerView.a0 a0Var) {
        Long lH = H(((FrameLayout) ((dq0) a0Var).e).getId());
        if (lH != null) {
            J(lH.longValue());
            this.l.l(lH.longValue());
        }
    }

    public FragmentStateAdapter(FragmentManager fragmentManager, androidx.lifecycle.d dVar) {
        this.j = new sa1<>();
        this.k = new sa1<>();
        this.l = new sa1<>();
        this.n = new b();
        this.o = false;
        this.p = false;
        this.i = fragmentManager;
        this.h = dVar;
        B(true);
    }

    public static abstract class d {
        public static final a a = new a();

        public interface b {
            void a();
        }

        public class a implements b {
            @Override // androidx.viewpager2.adapter.FragmentStateAdapter.d.b
            public final void a() {
            }
        }
    }
}
