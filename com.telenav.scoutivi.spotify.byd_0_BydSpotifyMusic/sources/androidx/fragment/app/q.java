package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FragmentStore.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<Fragment> f2363a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashMap<String, p> f2364b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f2365c;

    q() {
    }

    void a(Fragment fragment) {
        if (this.f2363a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f2363a) {
            this.f2363a.add(fragment);
        }
        fragment.f2121q = true;
    }

    void b() {
        this.f2364b.values().removeAll(Collections.singleton(null));
    }

    boolean c(String str) {
        return this.f2364b.get(str) != null;
    }

    void d(int i7) {
        for (p pVar : this.f2364b.values()) {
            if (pVar != null) {
                pVar.u(i7);
            }
        }
    }

    void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.f2364b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (p pVar : this.f2364b.values()) {
                printWriter.print(str);
                if (pVar != null) {
                    Fragment fragmentK = pVar.k();
                    printWriter.println(fragmentK);
                    fragmentK.g(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f2363a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i7 = 0; i7 < size; i7++) {
                Fragment fragment = this.f2363a.get(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    Fragment f(String str) {
        p pVar = this.f2364b.get(str);
        if (pVar != null) {
            return pVar.k();
        }
        return null;
    }

    Fragment g(int i7) {
        for (int size = this.f2363a.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f2363a.get(size);
            if (fragment != null && fragment.B == i7) {
                return fragment;
            }
        }
        for (p pVar : this.f2364b.values()) {
            if (pVar != null) {
                Fragment fragmentK = pVar.k();
                if (fragmentK.B == i7) {
                    return fragmentK;
                }
            }
        }
        return null;
    }

    Fragment h(String str) {
        if (str != null) {
            for (int size = this.f2363a.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f2363a.get(size);
                if (fragment != null && str.equals(fragment.D)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (p pVar : this.f2364b.values()) {
            if (pVar != null) {
                Fragment fragmentK = pVar.k();
                if (str.equals(fragmentK.D)) {
                    return fragmentK;
                }
            }
        }
        return null;
    }

    Fragment i(String str) {
        Fragment fragmentI;
        for (p pVar : this.f2364b.values()) {
            if (pVar != null && (fragmentI = pVar.k().i(str)) != null) {
                return fragmentI;
            }
        }
        return null;
    }

    int j(Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.L;
        if (viewGroup == null) {
            return -1;
        }
        int iIndexOf = this.f2363a.indexOf(fragment);
        for (int i7 = iIndexOf - 1; i7 >= 0; i7--) {
            Fragment fragment2 = this.f2363a.get(i7);
            if (fragment2.L == viewGroup && (view2 = fragment2.M) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            iIndexOf++;
            if (iIndexOf >= this.f2363a.size()) {
                return -1;
            }
            Fragment fragment3 = this.f2363a.get(iIndexOf);
            if (fragment3.L == viewGroup && (view = fragment3.M) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    List<p> k() {
        ArrayList arrayList = new ArrayList();
        for (p pVar : this.f2364b.values()) {
            if (pVar != null) {
                arrayList.add(pVar);
            }
        }
        return arrayList;
    }

    List<Fragment> l() {
        ArrayList arrayList = new ArrayList();
        for (p pVar : this.f2364b.values()) {
            if (pVar != null) {
                arrayList.add(pVar.k());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    p m(String str) {
        return this.f2364b.get(str);
    }

    List<Fragment> n() {
        ArrayList arrayList;
        if (this.f2363a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f2363a) {
            arrayList = new ArrayList(this.f2363a);
        }
        return arrayList;
    }

    m o() {
        return this.f2365c;
    }

    void p(p pVar) {
        Fragment fragmentK = pVar.k();
        if (c(fragmentK.f2115k)) {
            return;
        }
        this.f2364b.put(fragmentK.f2115k, pVar);
        if (fragmentK.H) {
            if (fragmentK.G) {
                this.f2365c.h(fragmentK);
            } else {
                this.f2365c.p(fragmentK);
            }
            fragmentK.H = false;
        }
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + fragmentK);
        }
    }

    void q(p pVar) {
        Fragment fragmentK = pVar.k();
        if (fragmentK.G) {
            this.f2365c.p(fragmentK);
        }
        if (this.f2364b.put(fragmentK.f2115k, null) != null && FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragmentK);
        }
    }

    void r() {
        Iterator<Fragment> it = this.f2363a.iterator();
        while (it.hasNext()) {
            p pVar = this.f2364b.get(it.next().f2115k);
            if (pVar != null) {
                pVar.m();
            }
        }
        for (p pVar2 : this.f2364b.values()) {
            if (pVar2 != null) {
                pVar2.m();
                Fragment fragmentK = pVar2.k();
                if (fragmentK.f2122r && !fragmentK.f0()) {
                    q(pVar2);
                }
            }
        }
    }

    void s(Fragment fragment) {
        synchronized (this.f2363a) {
            this.f2363a.remove(fragment);
        }
        fragment.f2121q = false;
    }

    void t() {
        this.f2364b.clear();
    }

    void u(List<String> list) {
        this.f2363a.clear();
        if (list != null) {
            for (String str : list) {
                Fragment fragmentF = f(str);
                if (fragmentF == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragmentF);
                }
                a(fragmentF);
            }
        }
    }

    ArrayList<FragmentState> v() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.f2364b.size());
        for (p pVar : this.f2364b.values()) {
            if (pVar != null) {
                Fragment fragmentK = pVar.k();
                FragmentState fragmentStateS = pVar.s();
                arrayList.add(fragmentStateS);
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragmentK + ": " + fragmentStateS.f2238r);
                }
            }
        }
        return arrayList;
    }

    ArrayList<String> w() {
        synchronized (this.f2363a) {
            if (this.f2363a.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.f2363a.size());
            for (Fragment fragment : this.f2363a) {
                arrayList.add(fragment.f2115k);
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment.f2115k + "): " + fragment);
                }
            }
            return arrayList;
        }
    }

    void x(m mVar) {
        this.f2365c = mVar;
    }
}
