package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import androidx.navigation.i;
import androidx.navigation.n;
import androidx.navigation.q;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
@q.b("dialog")
public final class DialogFragmentNavigator extends q<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1669a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f1670b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1671c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<String> f1672d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private e f1673e = new e() { // from class: androidx.navigation.fragment.DialogFragmentNavigator.1
        @Override // androidx.lifecycle.e
        public void d(g gVar, d.a aVar) {
            if (aVar == d.a.ON_STOP) {
                androidx.fragment.app.b bVar = (androidx.fragment.app.b) gVar;
                if (bVar.w1().isShowing()) {
                    return;
                }
                NavHostFragment.q1(bVar).q();
            }
        }
    };

    public static class a extends i implements androidx.navigation.b {
        private String k;

        public a(q<? extends a> qVar) {
            super(qVar);
        }

        @Override // androidx.navigation.i
        public void m(Context context, AttributeSet attributeSet) {
            super.m(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, c.DialogFragmentNavigator);
            String string = typedArrayObtainAttributes.getString(c.DialogFragmentNavigator_android_name);
            if (string != null) {
                t(string);
            }
            typedArrayObtainAttributes.recycle();
        }

        public final String s() {
            String str = this.k;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("DialogFragment class was not set");
        }

        public final a t(String str) {
            this.k = str;
            return this;
        }
    }

    public DialogFragmentNavigator(Context context, j jVar) {
        this.f1669a = context;
        this.f1670b = jVar;
    }

    @Override // androidx.navigation.q
    public void c(Bundle bundle) {
        if (bundle != null) {
            this.f1671c = bundle.getInt("androidx-nav-dialogfragment:navigator:count", 0);
            for (int i2 = 0; i2 < this.f1671c; i2++) {
                androidx.fragment.app.b bVar = (androidx.fragment.app.b) this.f1670b.Y("androidx-nav-fragment:navigator:dialog:" + i2);
                if (bVar != null) {
                    bVar.a().a(this.f1673e);
                } else {
                    this.f1672d.add("androidx-nav-fragment:navigator:dialog:" + i2);
                }
            }
        }
    }

    @Override // androidx.navigation.q
    public Bundle d() {
        if (this.f1671c == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("androidx-nav-dialogfragment:navigator:count", this.f1671c);
        return bundle;
    }

    @Override // androidx.navigation.q
    public boolean e() {
        if (this.f1671c == 0) {
            return false;
        }
        if (this.f1670b.u0()) {
            Log.i("DialogFragmentNavigator", "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        j jVar = this.f1670b;
        StringBuilder sb = new StringBuilder();
        sb.append("androidx-nav-fragment:navigator:dialog:");
        int i2 = this.f1671c - 1;
        this.f1671c = i2;
        sb.append(i2);
        Fragment fragmentY = jVar.Y(sb.toString());
        if (fragmentY != null) {
            fragmentY.a().c(this.f1673e);
            ((androidx.fragment.app.b) fragmentY).r1();
        }
        return true;
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a a() {
        return new a(this);
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public i b(a aVar, Bundle bundle, n nVar, q.a aVar2) {
        if (this.f1670b.u0()) {
            Log.i("DialogFragmentNavigator", "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String strS = aVar.s();
        if (strS.charAt(0) == '.') {
            strS = this.f1669a.getPackageName() + strS;
        }
        Fragment fragmentA = this.f1670b.f0().a(this.f1669a.getClassLoader(), strS);
        if (!androidx.fragment.app.b.class.isAssignableFrom(fragmentA.getClass())) {
            throw new IllegalArgumentException("Dialog destination " + aVar.s() + " is not an instance of DialogFragment");
        }
        androidx.fragment.app.b bVar = (androidx.fragment.app.b) fragmentA;
        bVar.i1(bundle);
        bVar.a().a(this.f1673e);
        j jVar = this.f1670b;
        StringBuilder sb = new StringBuilder();
        sb.append("androidx-nav-fragment:navigator:dialog:");
        int i2 = this.f1671c;
        this.f1671c = i2 + 1;
        sb.append(i2);
        bVar.y1(jVar, sb.toString());
        return aVar;
    }

    void h(Fragment fragment) {
        if (this.f1672d.remove(fragment.J())) {
            fragment.a().a(this.f1673e);
        }
    }
}
