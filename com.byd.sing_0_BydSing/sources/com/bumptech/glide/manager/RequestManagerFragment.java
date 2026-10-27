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
    private final com.bumptech.glide.manager.a f3195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final l f3196c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Set<RequestManagerFragment> f3197d;
    private com.bumptech.glide.f e;
    private RequestManagerFragment f;
    private Fragment g;

    private class a implements l {
        a() {
        }

        @Override // com.bumptech.glide.manager.l
        public Set<com.bumptech.glide.f> a() {
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
        this.f3197d.add(requestManagerFragment);
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
        RequestManagerFragment requestManagerFragmentH = com.bumptech.glide.b.c(activity).k().h(activity);
        this.f = requestManagerFragmentH;
        if (equals(requestManagerFragmentH)) {
            return;
        }
        this.f.a(this);
    }

    private void i(RequestManagerFragment requestManagerFragment) {
        this.f3197d.remove(requestManagerFragment);
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
            return Collections.unmodifiableSet(this.f3197d);
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
        return this.f3195b;
    }

    public com.bumptech.glide.f e() {
        return this.e;
    }

    public l f() {
        return this.f3196c;
    }

    void j(Fragment fragment) {
        this.g = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        h(fragment.getActivity());
    }

    public void k(com.bumptech.glide.f fVar) {
        this.e = fVar;
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
        this.f3195b.c();
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
        this.f3195b.d();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f3195b.e();
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + d() + "}";
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(com.bumptech.glide.manager.a aVar) {
        this.f3196c = new a();
        this.f3197d = new HashSet();
        this.f3195b = aVar;
    }
}
