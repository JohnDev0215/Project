package d2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: SupportRequestManagerFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class o extends Fragment {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private final d2.a f5645g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private final m f5646h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private final Set<o> f5647i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private o f5648j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private com.bumptech.glide.k f5649k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private Fragment f5650l0;

    /* JADX INFO: compiled from: SupportRequestManagerFragment.java */
    private class a implements m {
        a() {
        }

        @Override // d2.m
        public Set<com.bumptech.glide.k> a() {
            Set<o> setO1 = o.this.O1();
            HashSet hashSet = new HashSet(setO1.size());
            for (o oVar : setO1) {
                if (oVar.R1() != null) {
                    hashSet.add(oVar.R1());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + o.this + "}";
        }
    }

    public o() {
        this(new d2.a());
    }

    private void N1(o oVar) {
        this.f5647i0.add(oVar);
    }

    private Fragment Q1() {
        Fragment fragmentG = G();
        return fragmentG != null ? fragmentG : this.f5650l0;
    }

    private static FragmentManager T1(Fragment fragment) {
        while (fragment.G() != null) {
            fragment = fragment.G();
        }
        return fragment.A();
    }

    private boolean U1(Fragment fragment) {
        Fragment fragmentQ1 = Q1();
        while (true) {
            Fragment fragmentG = fragment.G();
            if (fragmentG == null) {
                return false;
            }
            if (fragmentG.equals(fragmentQ1)) {
                return true;
            }
            fragment = fragment.G();
        }
    }

    private void V1(Context context, FragmentManager fragmentManager) {
        Z1();
        o oVarQ = com.bumptech.glide.c.c(context).k().q(context, fragmentManager);
        this.f5648j0 = oVarQ;
        if (equals(oVarQ)) {
            return;
        }
        this.f5648j0.N1(this);
    }

    private void W1(o oVar) {
        this.f5647i0.remove(oVar);
    }

    private void Z1() {
        o oVar = this.f5648j0;
        if (oVar != null) {
            oVar.W1(this);
            this.f5648j0 = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void C0() {
        super.C0();
        this.f5650l0 = null;
        Z1();
    }

    Set<o> O1() {
        o oVar = this.f5648j0;
        if (oVar == null) {
            return Collections.emptySet();
        }
        if (equals(oVar)) {
            return Collections.unmodifiableSet(this.f5647i0);
        }
        HashSet hashSet = new HashSet();
        for (o oVar2 : this.f5648j0.O1()) {
            if (U1(oVar2.Q1())) {
                hashSet.add(oVar2);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    d2.a P1() {
        return this.f5645g0;
    }

    @Override // androidx.fragment.app.Fragment
    public void R0() {
        super.R0();
        this.f5645g0.d();
    }

    public com.bumptech.glide.k R1() {
        return this.f5649k0;
    }

    @Override // androidx.fragment.app.Fragment
    public void S0() {
        super.S0();
        this.f5645g0.e();
    }

    public m S1() {
        return this.f5646h0;
    }

    void X1(Fragment fragment) {
        FragmentManager fragmentManagerT1;
        this.f5650l0 = fragment;
        if (fragment == null || fragment.s() == null || (fragmentManagerT1 = T1(fragment)) == null) {
            return;
        }
        V1(fragment.s(), fragmentManagerT1);
    }

    public void Y1(com.bumptech.glide.k kVar) {
        this.f5649k0 = kVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        FragmentManager fragmentManagerT1 = T1(this);
        if (fragmentManagerT1 == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
            }
        } else {
            try {
                V1(s(), fragmentManagerT1);
            } catch (IllegalStateException e7) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e7);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + Q1() + "}";
    }

    @Override // androidx.fragment.app.Fragment
    public void z0() {
        super.z0();
        this.f5645g0.c();
        Z1();
    }

    @SuppressLint({"ValidFragment"})
    public o(d2.a aVar) {
        this.f5646h0 = new a();
        this.f5647i0 = new HashSet();
        this.f5645g0 = aVar;
    }
}
