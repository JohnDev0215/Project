package androidx.databinding;

import android.util.Log;
import android.view.View;
import b.j.d;
import b.j.e;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<Class<? extends d>> f345a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<d> f346b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<String> f347c = new CopyOnWriteArrayList();

    @Override // b.j.d
    public ViewDataBinding b(e eVar, View view, int i) {
        Iterator<d> it = this.f346b.iterator();
        while (it.hasNext()) {
            ViewDataBinding viewDataBindingB = it.next().b(eVar, view, i);
            if (viewDataBindingB != null) {
                return viewDataBindingB;
            }
        }
        if (e()) {
            return b(eVar, view, i);
        }
        return null;
    }

    @Override // b.j.d
    public ViewDataBinding c(e eVar, View[] viewArr, int i) {
        Iterator<d> it = this.f346b.iterator();
        while (it.hasNext()) {
            ViewDataBinding viewDataBindingC = it.next().c(eVar, viewArr, i);
            if (viewDataBindingC != null) {
                return viewDataBindingC;
            }
        }
        if (e()) {
            return c(eVar, viewArr, i);
        }
        return null;
    }

    public void d(d dVar) {
        if (this.f345a.add((Class<? extends d>) dVar.getClass())) {
            this.f346b.add(dVar);
            Iterator<d> it = dVar.a().iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
    }

    public final boolean e() {
        boolean z = false;
        for (String str : this.f347c) {
            try {
                Class<?> cls = Class.forName(str);
                if (d.class.isAssignableFrom(cls)) {
                    d((d) cls.newInstance());
                    this.f347c.remove(str);
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            } catch (IllegalAccessException e) {
                Log.e("MergedDataBinderMapper", "unable to add feature mapper for " + str, e);
            } catch (InstantiationException e2) {
                Log.e("MergedDataBinderMapper", "unable to add feature mapper for " + str, e2);
            }
        }
        return z;
    }
}
