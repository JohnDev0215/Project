package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.widget.directwriting.IDirectWritingService;
import androidx.lifecycle.d;
import defpackage.a71;
import defpackage.b71;
import defpackage.d71;
import defpackage.g8;
import defpackage.gq2;
import defpackage.i21;
import defpackage.kd2;
import defpackage.kk0;
import defpackage.t70;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: LifecycleRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public class g extends d {
    public final boolean a;
    public kk0<a71, a> b;
    public d.b c;
    public final WeakReference<b71> d;
    public int e;
    public boolean f;
    public boolean g;
    public final ArrayList<d.b> h;

    /* JADX INFO: compiled from: LifecycleRegistry.kt */
    public static final class a {
        public d.b a;
        public final f b;

        public a(a71 a71Var, d.b bVar) {
            f reflectiveGenericLifecycleObserver;
            i21.b(a71Var);
            HashMap map = d71.a;
            boolean z = a71Var instanceof f;
            boolean z2 = a71Var instanceof t70;
            if (z && z2) {
                reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((t70) a71Var, (f) a71Var);
            } else if (z2) {
                reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((t70) a71Var, null);
            } else if (z) {
                reflectiveGenericLifecycleObserver = (f) a71Var;
            } else {
                Class<?> cls = a71Var.getClass();
                if (d71.c(cls) == 2) {
                    Object obj = d71.b.get(cls);
                    i21.b(obj);
                    List list = (List) obj;
                    if (list.size() == 1) {
                        reflectiveGenericLifecycleObserver = new SingleGeneratedAdapterObserver(d71.a((Constructor) list.get(0), a71Var));
                    } else {
                        int size = list.size();
                        b[] bVarArr = new b[size];
                        for (int i = 0; i < size; i++) {
                            bVarArr[i] = d71.a((Constructor) list.get(i), a71Var);
                        }
                        reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(bVarArr);
                    }
                } else {
                    reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(a71Var);
                }
            }
            this.b = reflectiveGenericLifecycleObserver;
            this.a = bVar;
        }

        public final void a(b71 b71Var, d.a aVar) {
            d.b bVarA = aVar.a();
            d.b bVar = this.a;
            i21.e(bVar, "state1");
            if (bVarA.compareTo(bVar) < 0) {
                bVar = bVarA;
            }
            this.a = bVar;
            this.b.a(b71Var, aVar);
            this.a = bVarA;
        }
    }

    public g(b71 b71Var) {
        i21.e(b71Var, "provider");
        this.a = true;
        this.b = new kk0<>();
        this.c = d.b.INITIALIZED;
        this.h = new ArrayList<>();
        this.d = new WeakReference<>(b71Var);
    }

    @Override // androidx.lifecycle.d
    public void a(a71 a71Var) {
        b71 b71Var;
        i21.e(a71Var, "observer");
        d("addObserver");
        d.b bVar = this.c;
        d.b bVar2 = d.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = d.b.INITIALIZED;
        }
        a aVar = new a(a71Var, bVar2);
        if (this.b.c(a71Var, aVar) == null && (b71Var = this.d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            d.b bVarC = c(a71Var);
            this.e++;
            while (aVar.a.compareTo(bVarC) < 0 && this.b.i.containsKey(a71Var)) {
                d.b bVar3 = aVar.a;
                ArrayList<d.b> arrayList = this.h;
                arrayList.add(bVar3);
                d.a.C0011a c0011a = d.a.Companion;
                d.b bVar4 = aVar.a;
                c0011a.getClass();
                d.a aVarA = d.a.C0011a.a(bVar4);
                if (aVarA == null) {
                    throw new IllegalStateException("no event up from " + aVar.a);
                }
                aVar.a(b71Var, aVarA);
                arrayList.remove(arrayList.size() - 1);
                bVarC = c(a71Var);
            }
            if (!z) {
                h();
            }
            this.e--;
        }
    }

    @Override // androidx.lifecycle.d
    public final void b(a71 a71Var) {
        i21.e(a71Var, "observer");
        d("removeObserver");
        this.b.b(a71Var);
    }

    public final d.b c(a71 a71Var) {
        a aVar;
        kk0<a71, a> kk0Var = this.b;
        kd2.c<a71, a> cVar = kk0Var.i.containsKey(a71Var) ? kk0Var.i.get(a71Var).h : null;
        d.b bVar = (cVar == null || (aVar = cVar.f) == null) ? null : aVar.a;
        ArrayList<d.b> arrayList = this.h;
        d.b bVar2 = arrayList.isEmpty() ^ true ? arrayList.get(arrayList.size() - 1) : null;
        d.b bVar3 = this.c;
        i21.e(bVar3, "state1");
        if (bVar == null || bVar.compareTo(bVar3) >= 0) {
            bVar = bVar3;
        }
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    @SuppressLint({"RestrictedApi"})
    public final void d(String str) {
        if (this.a) {
            g8.J().k.getClass();
            if (!(Looper.getMainLooper().getThread() == Thread.currentThread())) {
                throw new IllegalStateException(gq2.g("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void e(d.a aVar) {
        i21.e(aVar, IDirectWritingService.KEY_BUNDLE_EVENT);
        d("handleLifecycleEvent");
        f(aVar.a());
    }

    public final void f(d.b bVar) {
        d.b bVar2 = this.c;
        if (bVar2 == bVar) {
            return;
        }
        d.b bVar3 = d.b.INITIALIZED;
        d.b bVar4 = d.b.DESTROYED;
        if (!((bVar2 == bVar3 && bVar == bVar4) ? false : true)) {
            throw new IllegalStateException(("no event down from " + this.c + " in component " + this.d.get()).toString());
        }
        this.c = bVar;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        h();
        this.f = false;
        if (this.c == bVar4) {
            this.b = new kk0<>();
        }
    }

    public final void g() {
        d.b bVar = d.b.CREATED;
        d("setCurrentState");
        f(bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.g.h():void");
    }
}
