package com.bumptech.glide.manager;

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
public class RequestManagerFragment extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.manager.a f3421b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final l f3422c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Set<RequestManagerFragment> f3423d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.bumptech.glide.h f3424e;
    private RequestManagerFragment f;
    private Fragment g;

    private class a implements l {
        a() {
        }

        @Override // com.bumptech.glide.manager.l
        public Set<com.bumptech.glide.h> a() {
            Set<RequestManagerFragment> setB = RequestManagerFragment.this.b();
            HashSet hashSet = new HashSet(setB.size());
            for (RequestManagerFragment requestManagerFragment : setB) {
                if (requestManagerFragment.e() != null) {
                    hashSet.add(requestManagerFragment.e());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }

    public RequestManagerFragment() {
        this(new com.bumptech.glide.manager.a());
    }

    private void a(RequestManagerFragment requestManagerFragment) {
        this.f3423d.add(requestManagerFragment);
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
        RequestManagerFragment requestManagerFragmentH = com.bumptech.glide.c.c(activity).k().h(activity);
        this.f = requestManagerFragmentH;
        if (equals(requestManagerFragmentH)) {
            return;
        }
        this.f.a(this);
    }

    private void i(RequestManagerFragment requestManagerFragment) {
        this.f3423d.remove(requestManagerFragment);
    }

    private void l() {
        RequestManagerFragment requestManagerFragment = this.f;
        if (requestManagerFragment != null) {
            requestManagerFragment.i(this);
            this.f = null;
        }
    }

    @TargetApi(17)
    Set<RequestManagerFragment> b() {
        if (equals(this.f)) {
            return Collections.unmodifiableSet(this.f3423d);
        }
        if (this.f == null || Build.VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (RequestManagerFragment requestManagerFragment : this.f.b()) {
            if (g(requestManagerFragment.getParentFragment())) {
                hashSet.add(requestManagerFragment);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    com.bumptech.glide.manager.a c() {
        return this.f3421b;
    }

    public com.bumptech.glide.h e() {
        return this.f3424e;
    }

    public l f() {
        return this.f3422c;
    }

    void j(Fragment fragment) {
        this.g = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        h(fragment.getActivity());
    }

    public void k(com.bumptech.glide.h hVar) {
        this.f3424e = hVar;
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            h(activity);
        } catch (IllegalStateException e2) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e2);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f3421b.c();
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
        this.f3421b.d();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f3421b.e();
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + d() + "}";
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(com.bumptech.glide.manager.a aVar) {
        this.f3422c = new a();
        this.f3423d = new HashSet();
        this.f3421b = aVar;
    }
}
