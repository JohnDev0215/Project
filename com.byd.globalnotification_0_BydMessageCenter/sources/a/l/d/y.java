package a.l.d;

import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<Fragment> f1059a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap<String, x> f1060b = new HashMap<>();

    public void a(Fragment fragment) {
        if (this.f1059a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f1059a) {
            this.f1059a.add(fragment);
        }
        fragment.k = true;
    }

    public void b() {
        this.f1060b.values().removeAll(Collections.singleton(null));
    }

    public boolean c(String str) {
        return this.f1060b.containsKey(str);
    }

    public void d(int i) {
        Iterator<Fragment> it = this.f1059a.iterator();
        while (it.hasNext()) {
            x xVar = this.f1060b.get(it.next().f1557e);
            if (xVar != null) {
                xVar.f1058c = i;
            }
        }
        for (x xVar2 : this.f1060b.values()) {
            if (xVar2 != null) {
                xVar2.f1058c = i;
            }
        }
    }

    public Fragment e(String str) {
        x xVar = this.f1060b.get(str);
        if (xVar != null) {
            return xVar.f1057b;
        }
        return null;
    }

    public List<Fragment> f() {
        ArrayList arrayList = new ArrayList();
        Iterator<x> it = this.f1060b.values().iterator();
        while (it.hasNext()) {
            x next = it.next();
            arrayList.add(next != null ? next.f1057b : null);
        }
        return arrayList;
    }

    public List<Fragment> g() {
        ArrayList arrayList;
        if (this.f1059a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1059a) {
            arrayList = new ArrayList(this.f1059a);
        }
        return arrayList;
    }

    public void h(Fragment fragment) {
        synchronized (this.f1059a) {
            this.f1059a.remove(fragment);
        }
        fragment.k = false;
    }
}
