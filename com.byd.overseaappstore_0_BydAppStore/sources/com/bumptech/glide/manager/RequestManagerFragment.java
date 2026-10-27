package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;
import spi.k;
import spi.qm;
import spi.sm;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class RequestManagerFragment extends Fragment {
    public final k a;
    public final a b;
    public final Set<RequestManagerFragment> c;

    @Nullable
    public qm d;

    @Nullable
    public RequestManagerFragment e;

    @Nullable
    public Fragment f;

    public class a implements sm {
        public a() {
        }

        public final String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }

    public RequestManagerFragment() {
        this(new k());
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    public RequestManagerFragment(@NonNull k kVar) {
        this.b = new a();
        this.c = new HashSet();
        this.a = kVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.bumptech.glide.manager.RequestManagerFragment for r1v1 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public final void a(@androidx.annotation.NonNull android.app.Activity r2) {
        /*
            r1 = this;
            r1.b()
            com.bumptech.glide.a r0 = com.bumptech.glide.a.b(r2)
            spi.rm r0 = r0.f
            java.util.Objects.requireNonNull(r0)
            android.app.FragmentManager r2 = r2.getFragmentManager()
            com.bumptech.glide.manager.RequestManagerFragment r2 = r0.d(r2)
            r1.e = r2
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L23
            com.bumptech.glide.manager.RequestManagerFragment r2 = r1.e
            java.util.Set<com.bumptech.glide.manager.RequestManagerFragment> r2 = r2.c
            r2.add(r1)
        L23:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerFragment.a(android.app.Activity):void");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<com.bumptech.glide.manager.RequestManagerFragment>] */
    public final void b() {
        RequestManagerFragment requestManagerFragment = this.e;
        if (requestManagerFragment != null) {
            requestManagerFragment.c.remove(this);
            this.e = null;
        }
    }

    @Override // android.app.Fragment
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            a(activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.a.c();
        b();
    }

    @Override // android.app.Fragment
    public final void onDetach() {
        super.onDetach();
        b();
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.a.d();
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.a.e();
    }

    @Override // android.app.Fragment
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{parent=");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            parentFragment = this.f;
        }
        sb.append(parentFragment);
        sb.append("}");
        return sb.toString();
    }
}
