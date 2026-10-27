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
    private com.bumptech.glide.h d0;
    private Fragment e0;

    /* JADX INFO: compiled from: SupportRequestManagerFragment.java */
    private class a implements l {
        a() {
        }

        @Override // com.bumptech.glide.manager.l
        public Set<com.bumptech.glide.h> a() {
            Set<n> setA1 = n.this.A1();
            HashSet hashSet = new HashSet(setA1.size());
            for (n nVar : setA1) {
                if (nVar.D1() != null) {
                    hashSet.add(nVar.D1());
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

    private Fragment C1() {
        Fragment fragmentI = I();
        return fragmentI != null ? fragmentI : this.e0;
    }

    private static androidx.fragment.app.g F1(Fragment fragment) {
        while (fragment.I() != null) {
            fragment = fragment.I();
        }
        return fragment.C();
    }

    private boolean G1(Fragment fragment) {
        Fragment fragmentC1 = C1();
        while (true) {
            Fragment fragmentI = fragment.I();
            if (fragmentI == null) {
                return false;
            }
            if (fragmentI.equals(fragmentC1)) {
                return true;
            }
            fragment = fragment.I();
        }
    }

    private void H1(Context context, androidx.fragment.app.g gVar) {
        L1();
        n nVarJ = com.bumptech.glide.c.c(context).k().j(context, gVar);
        this.c0 = nVarJ;
        if (equals(nVarJ)) {
            return;
        }
        this.c0.z1(this);
    }

    private void I1(n nVar) {
        this.b0.remove(nVar);
    }

    private void L1() {
        n nVar = this.c0;
        if (nVar != null) {
            nVar.I1(this);
            this.c0 = null;
        }
    }

    private void z1(n nVar) {
        this.b0.add(nVar);
    }

    Set<n> A1() {
        n nVar = this.c0;
        if (nVar == null) {
            return Collections.emptySet();
        }
        if (equals(nVar)) {
            return Collections.unmodifiableSet(this.b0);
        }
        HashSet hashSet = new HashSet();
        for (n nVar2 : this.c0.A1()) {
            if (G1(nVar2.C1())) {
                hashSet.add(nVar2);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    com.bumptech.glide.manager.a B1() {
        return this.Z;
    }

    public com.bumptech.glide.h D1() {
        return this.d0;
    }

    public l E1() {
        return this.a0;
    }

    @Override // androidx.fragment.app.Fragment
    public void H0() {
        super.H0();
        this.Z.d();
    }

    @Override // androidx.fragment.app.Fragment
    public void I0() {
        super.I0();
        this.Z.e();
    }

    void J1(Fragment fragment) {
        androidx.fragment.app.g gVarF1;
        this.e0 = fragment;
        if (fragment == null || fragment.x() == null || (gVarF1 = F1(fragment)) == null) {
            return;
        }
        H1(fragment.x(), gVarF1);
    }

    public void K1(com.bumptech.glide.h hVar) {
        this.d0 = hVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        androidx.fragment.app.g gVarF1 = F1(this);
        if (gVarF1 == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
            }
        } else {
            try {
                H1(x(), gVarF1);
            } catch (IllegalStateException e2) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e2);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        super.p0();
        this.Z.c();
        L1();
    }

    @Override // androidx.fragment.app.Fragment
    public void s0() {
        super.s0();
        this.e0 = null;
        L1();
    }

    @Override // androidx.fragment.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + C1() + "}";
    }

    @SuppressLint({"ValidFragment"})
    public n(com.bumptech.glide.manager.a aVar) {
        this.a0 = new a();
        this.b0 = new HashSet();
        this.Z = aVar;
    }
}
