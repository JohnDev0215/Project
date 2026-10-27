package a.k;

import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<Class<? extends d>> f914a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<d> f915b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<String> f916c = new CopyOnWriteArrayList();

    public e() {
        e(new b.c.a.a());
    }

    @Override // a.k.d
    public String b(int i) {
        Iterator<d> it = this.f915b.iterator();
        while (it.hasNext()) {
            String strB = it.next().b(i);
            if (strB != null) {
                return strB;
            }
        }
        if (f()) {
            return b(i);
        }
        return null;
    }

    @Override // a.k.d
    public ViewDataBinding c(f fVar, View view, int i) {
        Iterator<d> it = this.f915b.iterator();
        while (it.hasNext()) {
            ViewDataBinding viewDataBindingC = it.next().c(fVar, view, i);
            if (viewDataBindingC != null) {
                return viewDataBindingC;
            }
        }
        if (f()) {
            return c(fVar, view, i);
        }
        return null;
    }

    @Override // a.k.d
    public ViewDataBinding d(f fVar, View[] viewArr, int i) {
        Iterator<d> it = this.f915b.iterator();
        while (it.hasNext()) {
            ViewDataBinding viewDataBindingD = it.next().d(fVar, viewArr, i);
            if (viewDataBindingD != null) {
                return viewDataBindingD;
            }
        }
        if (f()) {
            return d(fVar, viewArr, i);
        }
        return null;
    }

    public void e(d dVar) {
        if (this.f914a.add((Class<? extends d>) dVar.getClass())) {
            this.f915b.add(dVar);
            Iterator<d> it = dVar.a().iterator();
            while (it.hasNext()) {
                e(it.next());
            }
        }
    }

    public final boolean f() {
        StringBuilder sb;
        boolean z = false;
        for (String str : this.f916c) {
            try {
                Class<?> cls = Class.forName(str);
                if (d.class.isAssignableFrom(cls)) {
                    e((d) cls.newInstance());
                    this.f916c.remove(str);
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            } catch (IllegalAccessException e2) {
                e = e2;
                sb = new StringBuilder();
                sb.append("unable to add feature mapper for ");
                sb.append(str);
                Log.e("MergedDataBinderMapper", sb.toString(), e);
            } catch (InstantiationException e3) {
                e = e3;
                sb = new StringBuilder();
                sb.append("unable to add feature mapper for ");
                sb.append(str);
                Log.e("MergedDataBinderMapper", sb.toString(), e);
            }
        }
        return z;
    }
}
