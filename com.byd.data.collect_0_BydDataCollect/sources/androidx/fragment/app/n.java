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
class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<Fragment> f1480a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashMap<String, m> f1481b = new HashMap<>();

    n() {
    }

    void a(Fragment fragment) {
        if (this.f1480a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f1480a) {
            this.f1480a.add(fragment);
        }
        fragment.m = true;
    }

    void b() {
        this.f1481b.values().removeAll(Collections.singleton(null));
    }

    boolean c(String str) {
        return this.f1481b.containsKey(str);
    }

    void d(int i2) {
        Iterator<Fragment> it = this.f1480a.iterator();
        while (it.hasNext()) {
            m mVar = this.f1481b.get(it.next().f1366g);
            if (mVar != null) {
                mVar.q(i2);
            }
        }
        for (m mVar2 : this.f1481b.values()) {
            if (mVar2 != null) {
                mVar2.q(i2);
            }
        }
    }

    void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.f1481b.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments:");
            for (m mVar : this.f1481b.values()) {
                printWriter.print(str);
                if (mVar != null) {
                    Fragment fragmentI = mVar.i();
                    printWriter.println(fragmentI);
                    fragmentI.c(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f1480a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = this.f1480a.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    Fragment f(String str) {
        m mVar = this.f1481b.get(str);
        if (mVar != null) {
            return mVar.i();
        }
        return null;
    }

    Fragment g(int i2) {
        for (int size = this.f1480a.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1480a.get(size);
            if (fragment != null && fragment.x == i2) {
                return fragment;
            }
        }
        for (m mVar : this.f1481b.values()) {
            if (mVar != null) {
                Fragment fragmentI = mVar.i();
                if (fragmentI.x == i2) {
                    return fragmentI;
                }
            }
        }
        return null;
    }

    Fragment h(String str) {
        if (str != null) {
            for (int size = this.f1480a.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f1480a.get(size);
                if (fragment != null && str.equals(fragment.z)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (m mVar : this.f1481b.values()) {
            if (mVar != null) {
                Fragment fragmentI = mVar.i();
                if (str.equals(fragmentI.z)) {
                    return fragmentI;
                }
            }
        }
        return null;
    }

    Fragment i(String str) {
        Fragment fragmentG;
        for (m mVar : this.f1481b.values()) {
            if (mVar != null && (fragmentG = mVar.i().g(str)) != null) {
                return fragmentG;
            }
        }
        return null;
    }

    Fragment j(Fragment fragment) {
        ViewGroup viewGroup = fragment.H;
        View view = fragment.I;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.f1480a.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.f1480a.get(iIndexOf);
                if (fragment2.H == viewGroup && fragment2.I != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    List<Fragment> k() {
        ArrayList arrayList = new ArrayList();
        for (m mVar : this.f1481b.values()) {
            if (mVar != null) {
                arrayList.add(mVar.i());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    m l(String str) {
        return this.f1481b.get(str);
    }

    List<Fragment> m() {
        ArrayList arrayList;
        if (this.f1480a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1480a) {
            arrayList = new ArrayList(this.f1480a);
        }
        return arrayList;
    }

    void n(m mVar) {
        this.f1481b.put(mVar.i().f1366g, mVar);
    }

    void o(m mVar) {
        Fragment fragmentI = mVar.i();
        for (m mVar2 : this.f1481b.values()) {
            if (mVar2 != null) {
                Fragment fragmentI2 = mVar2.i();
                if (fragmentI.f1366g.equals(fragmentI2.j)) {
                    fragmentI2.f1368i = fragmentI;
                    fragmentI2.j = null;
                }
            }
        }
        this.f1481b.put(fragmentI.f1366g, null);
        String str = fragmentI.j;
        if (str != null) {
            fragmentI.f1368i = f(str);
        }
    }

    void p(Fragment fragment) {
        synchronized (this.f1480a) {
            this.f1480a.remove(fragment);
        }
        fragment.m = false;
    }

    void q() {
        this.f1481b.clear();
    }

    void r(List<String> list) {
        this.f1480a.clear();
        if (list != null) {
            for (String str : list) {
                Fragment fragmentF = f(str);
                if (fragmentF == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (j.q0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragmentF);
                }
                a(fragmentF);
            }
        }
    }

    ArrayList<FragmentState> s() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.f1481b.size());
        for (m mVar : this.f1481b.values()) {
            if (mVar != null) {
                Fragment fragmentI = mVar.i();
                FragmentState fragmentStateO = mVar.o();
                arrayList.add(fragmentStateO);
                if (j.q0(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragmentI + ": " + fragmentStateO.n);
                }
            }
        }
        return arrayList;
    }

    ArrayList<String> t() {
        synchronized (this.f1480a) {
            if (this.f1480a.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.f1480a.size());
            for (Fragment fragment : this.f1480a) {
                arrayList.add(fragment.f1366g);
                if (j.q0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment.f1366g + "): " + fragment);
                }
            }
            return arrayList;
        }
    }
}
