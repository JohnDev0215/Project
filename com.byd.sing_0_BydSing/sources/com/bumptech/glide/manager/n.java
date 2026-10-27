package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: SupportRequestManagerFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends Fragment {
    private final com.bumptech.glide.manager.a Z;
    private final l a0;
    private final Set<n> b0;
    private n c0;
    private com.bumptech.glide.f d0;
    private Fragment e0;

    /* JADX INFO: compiled from: SupportRequestManagerFragment.java */
    private class a implements l {
        a() {
        }

        @Override // com.bumptech.glide.manager.l
        public Set<com.bumptech.glide.f> a() {
            Set<n> setN1 = n.this.n1();
            HashSet hashSet = new HashSet(setN1.size());
            for (n nVar : setN1) {
                if (nVar.q1() != null) {
                    hashSet.add(nVar.q1());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + n.this + "}";
        }
    }

    public n() {
        this(new com.bumptech.glide.manager.a());
    }

    private void m1(n nVar) {
        this.b0.add(nVar);
    }

    private Fragment p1() {
        Fragment fragmentA = A();
        return fragmentA != null ? fragmentA : this.e0;
    }

    private static androidx.fragment.app.g s1(Fragment fragment) {
        while (fragment.A() != null) {
            fragment = fragment.A();
        }
        return fragment.u();
    }

    private boolean t1(Fragment fragment) {
        Fragment fragmentP1 = p1();
        while (true) {
            Fragment fragmentA = fragment.A();
            if (fragmentA == null) {
                return false;
            }
            if (fragmentA.equals(fragmentP1)) {
                return true;
            }
            fragment = fragment.A();
        }
    }

    private void u1(Context context, androidx.fragment.app.g gVar) {
        y1();
        n nVarJ = com.bumptech.glide.b.c(context).k().j(context, gVar);
        this.c0 = nVarJ;
        if (equals(nVarJ)) {
            return;
        }
        this.c0.m1(this);
    }

    private void v1(n nVar) {
        this.b0.remove(nVar);
    }

    private void y1() {
        n nVar = this.c0;
        if (nVar != null) {
            nVar.v1(this);
            this.c0 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void X(Context context) {
        super.X(context);
        androidx.fragment.app.g gVarS1 = s1(this);
        if (gVarS1 == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
            }
        } else {
            try {
                u1(p(), gVarS1);
            } catch (IllegalStateException e) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void f0() {
        super.f0();
        this.Z.c();
        y1();
    }

    @Override // androidx.fragment.app.Fragment
    public void i0() {
        super.i0();
        this.e0 = null;
        y1();
    }

    Set<n> n1() {
        n nVar = this.c0;
        if (nVar == null) {
            return Collections.emptySet();
        }
        if (equals(nVar)) {
            return Collections.unmodifiableSet(this.b0);
        }
        HashSet hashSet = new HashSet();
        for (n nVar2 : this.c0.n1()) {
            if (t1(nVar2.p1())) {
                hashSet.add(nVar2);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    com.bumptech.glide.manager.a o1() {
        return this.Z;
    }

    public com.bumptech.glide.f q1() {
        return this.d0;
    }

    public l r1() {
        return this.a0;
    }

    @Override // androidx.fragment.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + p1() + "}";
    }

    void w1(Fragment fragment) {
        androidx.fragment.app.g gVarS1;
        this.e0 = fragment;
        if (fragment == null || fragment.p() == null || (gVarS1 = s1(fragment)) == null) {
            return;
        }
        u1(fragment.p(), gVarS1);
    }

    @Override // androidx.fragment.app.Fragment
    public void x0() {
        super.x0();
        this.Z.d();
    }

    public void x1(com.bumptech.glide.f fVar) {
        this.d0 = fVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void y0() {
        super.y0();
        this.Z.e();
    }

    @SuppressLint({"ValidFragment"})
    public n(com.bumptech.glide.manager.a aVar) {
        this.a0 = new a();
        this.b0 = new HashSet();
        this.Z = aVar;
    }
}
