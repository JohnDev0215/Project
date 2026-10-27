package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<Fragment> f708a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashMap<String, p> f709b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f710c;

    q() {
    }

    void a(@NonNull Fragment fragment) {
        if (this.f708a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f708a) {
            this.f708a.add(fragment);
        }
        fragment.n = true;
    }

    void b() {
        this.f709b.values().removeAll(Collections.singleton(null));
    }

    boolean c(@NonNull String str) {
        return this.f709b.get(str) != null;
    }

    void d(int i) {
        for (p pVar : this.f709b.values()) {
            if (pVar != null) {
                pVar.r(i);
            }
        }
    }

    void e(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        String str2 = str + "    ";
        if (!this.f709b.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments:");
            for (p pVar : this.f709b.values()) {
                printWriter.print(str);
                if (pVar != null) {
                    Fragment fragmentJ = pVar.j();
                    printWriter.println(fragmentJ);
                    fragmentJ.f(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f708a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size; i++) {
                Fragment fragment = this.f708a.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    @Nullable
    Fragment f(@NonNull String str) {
        p pVar = this.f709b.get(str);
        if (pVar != null) {
            return pVar.j();
        }
        return null;
    }

    @Nullable
    Fragment g(@IdRes int i) {
        for (int size = this.f708a.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f708a.get(size);
            if (fragment != null && fragment.y == i) {
                return fragment;
            }
        }
        for (p pVar : this.f709b.values()) {
            if (pVar != null) {
                Fragment fragmentJ = pVar.j();
                if (fragmentJ.y == i) {
                    return fragmentJ;
                }
            }
        }
        return null;
    }

    @Nullable
    Fragment h(@Nullable String str) {
        if (str != null) {
            for (int size = this.f708a.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f708a.get(size);
                if (fragment != null && str.equals(fragment.A)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (p pVar : this.f709b.values()) {
            if (pVar != null) {
                Fragment fragmentJ = pVar.j();
                if (str.equals(fragmentJ.A)) {
                    return fragmentJ;
                }
            }
        }
        return null;
    }

    @Nullable
    Fragment i(@NonNull String str) {
        Fragment fragmentI;
        for (p pVar : this.f709b.values()) {
            if (pVar != null && (fragmentI = pVar.j().i(str)) != null) {
                return fragmentI;
            }
        }
        return null;
    }

    int j(@NonNull Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.I;
        if (viewGroup == null) {
            return -1;
        }
        int iIndexOf = this.f708a.indexOf(fragment);
        for (int i = iIndexOf - 1; i >= 0; i--) {
            Fragment fragment2 = this.f708a.get(i);
            if (fragment2.I == viewGroup && (view2 = fragment2.J) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            iIndexOf++;
            if (iIndexOf >= this.f708a.size()) {
                return -1;
            }
            Fragment fragment3 = this.f708a.get(iIndexOf);
            if (fragment3.I == viewGroup && (view = fragment3.J) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    @NonNull
    List<p> k() {
        ArrayList arrayList = new ArrayList();
        for (p pVar : this.f709b.values()) {
            if (pVar != null) {
                arrayList.add(pVar);
            }
        }
        return arrayList;
    }

    @NonNull
    List<Fragment> l() {
        ArrayList arrayList = new ArrayList();
        for (p pVar : this.f709b.values()) {
            if (pVar != null) {
                arrayList.add(pVar.j());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    @Nullable
    p m(@NonNull String str) {
        return this.f709b.get(str);
    }

    @NonNull
    List<Fragment> n() {
        ArrayList arrayList;
        if (this.f708a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f708a) {
            arrayList = new ArrayList(this.f708a);
        }
        return arrayList;
    }

    m o() {
        return this.f710c;
    }

    void p(@NonNull p pVar) {
        Fragment fragmentJ = pVar.j();
        if (c(fragmentJ.h)) {
            return;
        }
        this.f709b.put(fragmentJ.h, pVar);
        if (fragmentJ.E) {
            if (fragmentJ.D) {
                this.f710c.e(fragmentJ);
            } else {
                this.f710c.m(fragmentJ);
            }
            fragmentJ.E = false;
        }
        if (FragmentManager.D0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + fragmentJ);
        }
    }

    void q(@NonNull p pVar) {
        Fragment fragmentJ = pVar.j();
        if (fragmentJ.D) {
            this.f710c.m(fragmentJ);
        }
        if (this.f709b.put(fragmentJ.h, null) != null && FragmentManager.D0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragmentJ);
        }
    }

    void r() {
        Iterator<Fragment> it = this.f708a.iterator();
        while (it.hasNext()) {
            p pVar = this.f709b.get(it.next().h);
            if (pVar != null) {
                pVar.k();
            }
        }
        for (p pVar2 : this.f709b.values()) {
            if (pVar2 != null) {
                pVar2.k();
                Fragment fragmentJ = pVar2.j();
                if (fragmentJ.o && !fragmentJ.Q()) {
                    q(pVar2);
                }
            }
        }
    }

    void s(@NonNull Fragment fragment) {
        synchronized (this.f708a) {
            this.f708a.remove(fragment);
        }
        fragment.n = false;
    }

    void t() {
        this.f709b.clear();
    }

    void u(@Nullable List<String> list) {
        this.f708a.clear();
        if (list != null) {
            for (String str : list) {
                Fragment fragmentF = f(str);
                if (fragmentF == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (FragmentManager.D0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragmentF);
                }
                a(fragmentF);
            }
        }
    }

    @NonNull
    ArrayList<FragmentState> v() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.f709b.size());
        for (p pVar : this.f709b.values()) {
            if (pVar != null) {
                Fragment fragmentJ = pVar.j();
                FragmentState fragmentStateP = pVar.p();
                arrayList.add(fragmentStateP);
                if (FragmentManager.D0(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragmentJ + ": " + fragmentStateP.o);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    ArrayList<String> w() {
        synchronized (this.f708a) {
            if (this.f708a.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.f708a.size());
            for (Fragment fragment : this.f708a) {
                arrayList.add(fragment.h);
                if (FragmentManager.D0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment.h + "): " + fragment);
                }
            }
            return arrayList;
        }
    }

    void x(@NonNull m mVar) {
        this.f710c = mVar;
    }
}
