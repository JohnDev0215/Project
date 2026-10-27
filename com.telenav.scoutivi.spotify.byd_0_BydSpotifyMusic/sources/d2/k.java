package d2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: RequestManagerFragment.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class k extends Fragment {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d2.a f5626e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final m f5627g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Set<k> f5628h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.bumptech.glide.k f5629i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private k f5630j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Fragment f5631k;

    /* JADX INFO: compiled from: RequestManagerFragment.java */
    private class a implements m {
        a() {
        }

        @Override // d2.m
        public Set<com.bumptech.glide.k> a() {
            Set<k> setB = k.this.b();
            HashSet hashSet = new HashSet(setB.size());
            for (k kVar : setB) {
                if (kVar.e() != null) {
                    hashSet.add(kVar.e());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + k.this + "}";
        }
    }

    public k() {
        this(new d2.a());
    }

    private void a(k kVar) {
        this.f5628h.add(kVar);
    }

    @TargetApi(17)
    private Fragment d() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.f5631k;
    }

    @TargetApi(17)
    private boolean g(Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (true) {
            Fragment parentFragment2 = fragment.getParentFragment();
            if (parentFragment2 == null) {
                return false;
            }
            if (parentFragment2.equals(parentFragment)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    private void h(Activity activity) {
        l();
        k kVarO = com.bumptech.glide.c.c(activity).k().o(activity);
        this.f5630j = kVarO;
        if (equals(kVarO)) {
            return;
        }
        this.f5630j.a(this);
    }

    private void i(k kVar) {
        this.f5628h.remove(kVar);
    }

    private void l() {
        k kVar = this.f5630j;
        if (kVar != null) {
            kVar.i(this);
            this.f5630j = null;
        }
    }

    @TargetApi(17)
    Set<k> b() {
        if (equals(this.f5630j)) {
            return Collections.unmodifiableSet(this.f5628h);
        }
        if (this.f5630j == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (k kVar : this.f5630j.b()) {
            if (g(kVar.getParentFragment())) {
                hashSet.add(kVar);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    d2.a c() {
        return this.f5626e;
    }

    public com.bumptech.glide.k e() {
        return this.f5629i;
    }

    public m f() {
        return this.f5627g;
    }

    void j(Fragment fragment) {
        this.f5631k = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        h(fragment.getActivity());
    }

    public void k(com.bumptech.glide.k kVar) {
        this.f5629i = kVar;
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            h(activity);
        } catch (IllegalStateException e7) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e7);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f5626e.c();
        l();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        l();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.f5626e.d();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f5626e.e();
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + d() + "}";
    }

    @SuppressLint({"ValidFragment"})
    k(d2.a aVar) {
        this.f5627g = new a();
        this.f5628h = new HashSet();
        this.f5626e = aVar;
    }
}
