package com.bumptech.glide.o;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class k extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.o.a f2465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final m f2466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Set<k> f2467d;
    private com.bumptech.glide.k e;
    private k f;
    private Fragment g;

    private class a implements m {
        a() {
        }

        @Override // com.bumptech.glide.o.m
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
        this(new com.bumptech.glide.o.a());
    }

    @SuppressLint({"ValidFragment"})
    k(com.bumptech.glide.o.a aVar) {
        this.f2466c = new a();
        this.f2467d = new HashSet();
        this.f2465b = aVar;
    }

    private void a(k kVar) {
        this.f2467d.add(kVar);
    }

    @TargetApi(17)
    private Fragment d() {
        Fragment parentFragment = Build.VERSION.SDK_INT >= 17 ? getParentFragment() : null;
        return parentFragment != null ? parentFragment : this.g;
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
        k kVarH = com.bumptech.glide.c.c(activity).k().h(activity);
        this.f = kVarH;
        if (equals(kVarH)) {
            return;
        }
        this.f.a(this);
    }

    private void i(k kVar) {
        this.f2467d.remove(kVar);
    }

    private void l() {
        k kVar = this.f;
        if (kVar != null) {
            kVar.i(this);
            this.f = null;
        }
    }

    @TargetApi(17)
    Set<k> b() {
        if (equals(this.f)) {
            return Collections.unmodifiableSet(this.f2467d);
        }
        if (this.f == null || Build.VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (k kVar : this.f.b()) {
            if (g(kVar.getParentFragment())) {
                hashSet.add(kVar);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    com.bumptech.glide.o.a c() {
        return this.f2465b;
    }

    public com.bumptech.glide.k e() {
        return this.e;
    }

    public m f() {
        return this.f2466c;
    }

    void j(Fragment fragment) {
        this.g = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        h(fragment.getActivity());
    }

    public void k(com.bumptech.glide.k kVar) {
        this.e = kVar;
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            h(activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f2465b.c();
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
        this.f2465b.d();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f2465b.e();
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + d() + "}";
    }
}
