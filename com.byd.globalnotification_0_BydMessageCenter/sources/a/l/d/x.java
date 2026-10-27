package a.l.d;

import a.n.g;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f1056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Fragment f1057b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1058c = -1;

    public x(q qVar, Fragment fragment) {
        this.f1056a = qVar;
        this.f1057b = fragment;
    }

    public x(q qVar, Fragment fragment, w wVar) {
        this.f1056a = qVar;
        this.f1057b = fragment;
        fragment.f1555c = null;
        fragment.q = 0;
        fragment.n = false;
        fragment.k = false;
        Fragment fragment2 = fragment.g;
        fragment.h = fragment2 != null ? fragment2.f1557e : null;
        Fragment fragment3 = this.f1057b;
        fragment3.g = null;
        Bundle bundle = wVar.m;
        fragment3.f1554b = bundle == null ? new Bundle() : bundle;
    }

    public x(q qVar, ClassLoader classLoader, n nVar, w wVar) {
        this.f1056a = qVar;
        this.f1057b = nVar.a(classLoader, wVar.f1050a);
        Bundle bundle = wVar.j;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        this.f1057b.J(wVar.j);
        Fragment fragment = this.f1057b;
        fragment.f1557e = wVar.f1051b;
        fragment.m = wVar.f1052c;
        fragment.o = true;
        fragment.v = wVar.f1053d;
        fragment.w = wVar.f1054e;
        fragment.x = wVar.f1055f;
        fragment.A = wVar.g;
        fragment.l = wVar.h;
        fragment.z = wVar.i;
        fragment.y = wVar.k;
        fragment.O = g.b.values()[wVar.l];
        Bundle bundle2 = wVar.m;
        if (bundle2 != null) {
            this.f1057b.f1554b = bundle2;
        } else {
            this.f1057b.f1554b = new Bundle();
        }
        if (r.J(2)) {
            StringBuilder sbD = b.a.a.a.a.d("Instantiated fragment ");
            sbD.append(this.f1057b);
            Log.v("FragmentManager", sbD.toString());
        }
    }

    public void a(ClassLoader classLoader) {
        Bundle bundle = this.f1057b.f1554b;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f1057b;
        fragment.f1555c = fragment.f1554b.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f1057b;
        fragment2.h = fragment2.f1554b.getString("android:target_state");
        Fragment fragment3 = this.f1057b;
        if (fragment3.h != null) {
            fragment3.i = fragment3.f1554b.getInt("android:target_req_state", 0);
        }
        Fragment fragment4 = this.f1057b;
        Boolean bool = fragment4.f1556d;
        if (bool != null) {
            fragment4.H = bool.booleanValue();
            this.f1057b.f1556d = null;
        } else {
            fragment4.H = fragment4.f1554b.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment5 = this.f1057b;
        if (fragment5.H) {
            return;
        }
        fragment5.G = true;
    }

    public void b() {
        if (this.f1057b.F == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f1057b.F.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f1057b.f1555c = sparseArray;
        }
    }
}
