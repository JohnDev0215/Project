package com.bumptech.glide.o;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class o extends Fragment {
    private final com.bumptech.glide.o.a Z;
    private final m a0;
    private final Set<o> b0;
    private o c0;
    private com.bumptech.glide.k d0;
    private Fragment e0;

    private class a implements m {
        a() {
        }

        @Override // com.bumptech.glide.o.m
        public Set<com.bumptech.glide.k> a() {
            Set<o> setA1 = o.this.A1();
            HashSet hashSet = new HashSet(setA1.size());
            for (o oVar : setA1) {
                if (oVar.D1() != null) {
                    hashSet.add(oVar.D1());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + o.this + "}";
        }
    }

    public o() {
        this(new com.bumptech.glide.o.a());
    }

    @SuppressLint({"ValidFragment"})
    public o(com.bumptech.glide.o.a aVar) {
        this.a0 = new a();
        this.b0 = new HashSet();
        this.Z = aVar;
    }

    private Fragment C1() {
        Fragment fragmentJ = J();
        return fragmentJ != null ? fragmentJ : this.e0;
    }

    private static androidx.fragment.app.i F1(Fragment fragment) {
        while (fragment.J() != null) {
            fragment = fragment.J();
        }
        return fragment.D();
    }

    private boolean G1(Fragment fragment) {
        Fragment fragmentC1 = C1();
        while (true) {
            Fragment fragmentJ = fragment.J();
            if (fragmentJ == null) {
                return false;
            }
            if (fragmentJ.equals(fragmentC1)) {
                return true;
            }
            fragment = fragment.J();
        }
    }

    private void H1(Context context, androidx.fragment.app.i iVar) {
        L1();
        o oVarJ = com.bumptech.glide.c.c(context).k().j(context, iVar);
        this.c0 = oVarJ;
        if (equals(oVarJ)) {
            return;
        }
        this.c0.z1(this);
    }

    private void I1(o oVar) {
        this.b0.remove(oVar);
    }

    private void L1() {
        o oVar = this.c0;
        if (oVar != null) {
            oVar.I1(this);
            this.c0 = null;
        }
    }

    private void z1(o oVar) {
        this.b0.add(oVar);
    }

    Set<o> A1() {
        o oVar = this.c0;
        if (oVar == null) {
            return Collections.emptySet();
        }
        if (equals(oVar)) {
            return Collections.unmodifiableSet(this.b0);
        }
        HashSet hashSet = new HashSet();
        for (o oVar2 : this.c0.A1()) {
            if (G1(oVar2.C1())) {
                hashSet.add(oVar2);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    com.bumptech.glide.o.a B1() {
        return this.Z;
    }

    public com.bumptech.glide.k D1() {
        return this.d0;
    }

    public m E1() {
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
        androidx.fragment.app.i iVarF1;
        this.e0 = fragment;
        if (fragment == null || fragment.y() == null || (iVarF1 = F1(fragment)) == null) {
            return;
        }
        H1(fragment.y(), iVarF1);
    }

    public void K1(com.bumptech.glide.k kVar) {
        this.d0 = kVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        androidx.fragment.app.i iVarF1 = F1(this);
        if (iVarF1 == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
            }
        } else {
            try {
                H1(y(), iVarF1);
            } catch (IllegalStateException e) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e);
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
}
