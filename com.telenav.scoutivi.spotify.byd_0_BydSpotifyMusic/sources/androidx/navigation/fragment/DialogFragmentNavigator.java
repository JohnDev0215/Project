package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import androidx.navigation.j;
import androidx.navigation.r;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
@r.b("dialog")
public final class DialogFragmentNavigator extends r<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2660a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final FragmentManager f2661b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2662c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<String> f2663d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private m f2664e = new m() { // from class: androidx.navigation.fragment.DialogFragmentNavigator.1
        @Override // androidx.lifecycle.m
        public void d(o oVar, i.b bVar) {
            if (bVar == i.b.ON_STOP) {
                androidx.fragment.app.c cVar = (androidx.fragment.app.c) oVar;
                if (cVar.Y1().isShowing()) {
                    return;
                }
                NavHostFragment.O1(cVar).u();
            }
        }
    };

    public static class a extends j implements androidx.navigation.b {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private String f2666o;

        public a(r<? extends a> rVar) {
            super(rVar);
        }

        @Override // androidx.navigation.j
        public void p(Context context, AttributeSet attributeSet) {
            super.p(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, c.DialogFragmentNavigator);
            String string = typedArrayObtainAttributes.getString(c.DialogFragmentNavigator_android_name);
            if (string != null) {
                w(string);
            }
            typedArrayObtainAttributes.recycle();
        }

        public final String v() {
            String str = this.f2666o;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("DialogFragment class was not set");
        }

        public final a w(String str) {
            this.f2666o = str;
            return this;
        }
    }

    public DialogFragmentNavigator(Context context, FragmentManager fragmentManager) {
        this.f2660a = context;
        this.f2661b = fragmentManager;
    }

    @Override // androidx.navigation.r
    public void c(Bundle bundle) {
        if (bundle != null) {
            this.f2662c = bundle.getInt("androidx-nav-dialogfragment:navigator:count", 0);
            for (int i7 = 0; i7 < this.f2662c; i7++) {
                androidx.fragment.app.c cVar = (androidx.fragment.app.c) this.f2661b.h0("androidx-nav-fragment:navigator:dialog:" + i7);
                if (cVar != null) {
                    cVar.a().a(this.f2664e);
                } else {
                    this.f2663d.add("androidx-nav-fragment:navigator:dialog:" + i7);
                }
            }
        }
    }

    @Override // androidx.navigation.r
    public Bundle d() {
        if (this.f2662c == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("androidx-nav-dialogfragment:navigator:count", this.f2662c);
        return bundle;
    }

    @Override // androidx.navigation.r
    public boolean e() {
        if (this.f2662c == 0) {
            return false;
        }
        if (this.f2661b.K0()) {
            Log.i("DialogFragmentNavigator", "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        FragmentManager fragmentManager = this.f2661b;
        StringBuilder sb = new StringBuilder();
        sb.append("androidx-nav-fragment:navigator:dialog:");
        int i7 = this.f2662c - 1;
        this.f2662c = i7;
        sb.append(i7);
        Fragment fragmentH0 = fragmentManager.h0(sb.toString());
        if (fragmentH0 != null) {
            fragmentH0.a().c(this.f2664e);
            ((androidx.fragment.app.c) fragmentH0).Q1();
        }
        return true;
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a a() {
        return new a(this);
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public j b(a aVar, Bundle bundle, androidx.navigation.o oVar, r.a aVar2) {
        if (this.f2661b.K0()) {
            Log.i("DialogFragmentNavigator", "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String strV = aVar.v();
        if (strV.charAt(0) == '.') {
            strV = this.f2660a.getPackageName() + strV;
        }
        Fragment fragmentA = this.f2661b.q0().a(this.f2660a.getClassLoader(), strV);
        if (!androidx.fragment.app.c.class.isAssignableFrom(fragmentA.getClass())) {
            throw new IllegalArgumentException("Dialog destination " + aVar.v() + " is not an instance of DialogFragment");
        }
        androidx.fragment.app.c cVar = (androidx.fragment.app.c) fragmentA;
        cVar.C1(bundle);
        cVar.a().a(this.f2664e);
        FragmentManager fragmentManager = this.f2661b;
        StringBuilder sb = new StringBuilder();
        sb.append("androidx-nav-fragment:navigator:dialog:");
        int i7 = this.f2662c;
        this.f2662c = i7 + 1;
        sb.append(i7);
        cVar.a2(fragmentManager, sb.toString());
        return aVar;
    }

    void h(Fragment fragment) {
        if (this.f2663d.remove(fragment.U())) {
            fragment.a().a(this.f2664e);
        }
    }
}
