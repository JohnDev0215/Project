package androidx.databinding;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Set<Class<? extends e>> f2022a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<e> f2023b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f2024c = new CopyOnWriteArrayList();

    private boolean f() {
        boolean z6 = false;
        for (String str : this.f2024c) {
            try {
                Class<?> cls = Class.forName(str);
                if (e.class.isAssignableFrom(cls)) {
                    e((e) cls.newInstance());
                    this.f2024c.remove(str);
                    z6 = true;
                }
            } catch (ClassNotFoundException unused) {
            } catch (IllegalAccessException e7) {
                Log.e("MergedDataBinderMapper", "unable to add feature mapper for " + str, e7);
            } catch (InstantiationException e8) {
                Log.e("MergedDataBinderMapper", "unable to add feature mapper for " + str, e8);
            }
        }
        return z6;
    }

    @Override // androidx.databinding.e
    public ViewDataBinding b(f fVar, View view, int i7) {
        Iterator<e> it = this.f2023b.iterator();
        while (it.hasNext()) {
            ViewDataBinding viewDataBindingB = it.next().b(fVar, view, i7);
            if (viewDataBindingB != null) {
                return viewDataBindingB;
            }
        }
        if (f()) {
            return b(fVar, view, i7);
        }
        return null;
    }

    @Override // androidx.databinding.e
    public ViewDataBinding c(f fVar, View[] viewArr, int i7) {
        Iterator<e> it = this.f2023b.iterator();
        while (it.hasNext()) {
            ViewDataBinding viewDataBindingC = it.next().c(fVar, viewArr, i7);
            if (viewDataBindingC != null) {
                return viewDataBindingC;
            }
        }
        if (f()) {
            return c(fVar, viewArr, i7);
        }
        return null;
    }

    @Override // androidx.databinding.e
    public int d(String str) {
        Iterator<e> it = this.f2023b.iterator();
        while (it.hasNext()) {
            int iD = it.next().d(str);
            if (iD != 0) {
                return iD;
            }
        }
        if (f()) {
            return d(str);
        }
        return 0;
    }

    public void e(e eVar) {
        if (this.f2022a.add((Class<? extends e>) eVar.getClass())) {
            this.f2023b.add(eVar);
            Iterator<e> it = eVar.a().iterator();
            while (it.hasNext()) {
                e(it.next());
            }
        }
    }
}
