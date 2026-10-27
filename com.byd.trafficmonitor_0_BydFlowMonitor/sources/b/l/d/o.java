package b.l.d;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentState;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FragmentStore.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<Fragment> f2839a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap<String, n> f2840b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k f2841c;

    public void a(Fragment fragment) {
        if (this.f2839a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f2839a) {
            this.f2839a.add(fragment);
        }
        fragment.mAdded = true;
    }

    public void b() {
        this.f2840b.values().removeAll(Collections.singleton(null));
    }

    public boolean c(String str) {
        return this.f2840b.get(str) != null;
    }

    public void d(int i2) {
        for (n nVar : this.f2840b.values()) {
            if (nVar != null) {
                nVar.t(i2);
            }
        }
    }

    public void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.f2840b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (n nVar : this.f2840b.values()) {
                printWriter.print(str);
                if (nVar != null) {
                    Fragment fragmentK = nVar.k();
                    printWriter.println(fragmentK);
                    fragmentK.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f2839a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = this.f2839a.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    public Fragment f(String str) {
        n nVar = this.f2840b.get(str);
        if (nVar != null) {
            return nVar.k();
        }
        return null;
    }

    public Fragment g(int i2) {
        for (int size = this.f2839a.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f2839a.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        for (n nVar : this.f2840b.values()) {
            if (nVar != null) {
                Fragment fragmentK = nVar.k();
                if (fragmentK.mFragmentId == i2) {
                    return fragmentK;
                }
            }
        }
        return null;
    }

    public Fragment h(String str) {
        if (str != null) {
            for (int size = this.f2839a.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f2839a.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (n nVar : this.f2840b.values()) {
            if (nVar != null) {
                Fragment fragmentK = nVar.k();
                if (str.equals(fragmentK.mTag)) {
                    return fragmentK;
                }
            }
        }
        return null;
    }

    public Fragment i(String str) {
        Fragment fragmentFindFragmentByWho;
        for (n nVar : this.f2840b.values()) {
            if (nVar != null && (fragmentFindFragmentByWho = nVar.k().findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    public int j(Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup == null) {
            return -1;
        }
        int iIndexOf = this.f2839a.indexOf(fragment);
        for (int i2 = iIndexOf - 1; i2 >= 0; i2--) {
            Fragment fragment2 = this.f2839a.get(i2);
            if (fragment2.mContainer == viewGroup && (view2 = fragment2.mView) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            iIndexOf++;
            if (iIndexOf >= this.f2839a.size()) {
                return -1;
            }
            Fragment fragment3 = this.f2839a.get(iIndexOf);
            if (fragment3.mContainer == viewGroup && (view = fragment3.mView) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    public List<n> k() {
        ArrayList arrayList = new ArrayList();
        for (n nVar : this.f2840b.values()) {
            if (nVar != null) {
                arrayList.add(nVar);
            }
        }
        return arrayList;
    }

    public List<Fragment> l() {
        ArrayList arrayList = new ArrayList();
        for (n nVar : this.f2840b.values()) {
            if (nVar != null) {
                arrayList.add(nVar.k());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public n m(String str) {
        return this.f2840b.get(str);
    }

    public List<Fragment> n() {
        ArrayList arrayList;
        if (this.f2839a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f2839a) {
            arrayList = new ArrayList(this.f2839a);
        }
        return arrayList;
    }

    public k o() {
        return this.f2841c;
    }

    public void p(n nVar) {
        Fragment fragmentK = nVar.k();
        if (c(fragmentK.mWho)) {
            return;
        }
        this.f2840b.put(fragmentK.mWho, nVar);
        if (fragmentK.mRetainInstanceChangedWhileDetached) {
            if (fragmentK.mRetainInstance) {
                this.f2841c.f(fragmentK);
            } else {
                this.f2841c.n(fragmentK);
            }
            fragmentK.mRetainInstanceChangedWhileDetached = false;
        }
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + fragmentK);
        }
    }

    public void q(n nVar) {
        Fragment fragmentK = nVar.k();
        if (fragmentK.mRetainInstance) {
            this.f2841c.n(fragmentK);
        }
        if (this.f2840b.put(fragmentK.mWho, null) != null && FragmentManager.G0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragmentK);
        }
    }

    public void r() {
        Iterator<Fragment> it = this.f2839a.iterator();
        while (it.hasNext()) {
            n nVar = this.f2840b.get(it.next().mWho);
            if (nVar != null) {
                nVar.m();
            }
        }
        for (n nVar2 : this.f2840b.values()) {
            if (nVar2 != null) {
                nVar2.m();
                Fragment fragmentK = nVar2.k();
                if (fragmentK.mRemoving && !fragmentK.isInBackStack()) {
                    q(nVar2);
                }
            }
        }
    }

    public void s(Fragment fragment) {
        synchronized (this.f2839a) {
            this.f2839a.remove(fragment);
        }
        fragment.mAdded = false;
    }

    public void t() {
        this.f2840b.clear();
    }

    public void u(List<String> list) {
        this.f2839a.clear();
        if (list != null) {
            for (String str : list) {
                Fragment fragmentF = f(str);
                if (fragmentF == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragmentF);
                }
                a(fragmentF);
            }
        }
    }

    public ArrayList<FragmentState> v() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.f2840b.size());
        for (n nVar : this.f2840b.values()) {
            if (nVar != null) {
                Fragment fragmentK = nVar.k();
                FragmentState fragmentStateR = nVar.r();
                arrayList.add(fragmentStateR);
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragmentK + ": " + fragmentStateR.n);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<String> w() {
        synchronized (this.f2839a) {
            if (this.f2839a.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.f2839a.size());
            for (Fragment fragment : this.f2839a) {
                arrayList.add(fragment.mWho);
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment.mWho + "): " + fragment);
                }
            }
            return arrayList;
        }
    }

    public void x(k kVar) {
        this.f2841c = kVar;
    }
}
