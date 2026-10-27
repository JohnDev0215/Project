package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.HashSet;
import java.util.Set;
import spi.k;
import spi.qm;
import spi.sm;

/* JADX INFO: loaded from: classes.dex */
public class SupportRequestManagerFragment extends Fragment {
    public final k a;
    public final a b;
    public final Set<SupportRequestManagerFragment> c;

    @Nullable
    public SupportRequestManagerFragment d;

    @Nullable
    public qm e;

    @Nullable
    public Fragment f;

    public class a implements sm {
        public a() {
        }

        public final String toString() {
            return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
        }
    }

    public SupportRequestManagerFragment() {
        this(new k());
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    public SupportRequestManagerFragment(@NonNull k kVar) {
        this.b = new a();
        this.c = new HashSet();
        this.a = kVar;
    }

    @Nullable
    public final Fragment b() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.f;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.bumptech.glide.manager.SupportRequestManagerFragment for r0v1 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public final void c(@androidx.annotation.NonNull android.content.Context r1, @androidx.annotation.NonNull androidx.fragment.app.FragmentManager r2) {
        /*
            r0 = this;
            r0.d()
            com.bumptech.glide.a r1 = com.bumptech.glide.a.b(r1)
            spi.rm r1 = r1.f
            com.bumptech.glide.manager.SupportRequestManagerFragment r1 = r1.e(r2)
            r0.d = r1
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L1c
            com.bumptech.glide.manager.SupportRequestManagerFragment r1 = r0.d
            java.util.Set<com.bumptech.glide.manager.SupportRequestManagerFragment> r1 = r1.c
            r1.add(r0)
        L1c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.SupportRequestManagerFragment.c(android.content.Context, androidx.fragment.app.FragmentManager):void");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<com.bumptech.glide.manager.SupportRequestManagerFragment>] */
    public final void d() {
        SupportRequestManagerFragment supportRequestManagerFragment = this.d;
        if (supportRequestManagerFragment != null) {
            supportRequestManagerFragment.c.remove(this);
            this.d = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onAttach(Context context) {
        super.onAttach(context);
        Fragment parentFragment = this;
        while (parentFragment.getParentFragment() != null) {
            parentFragment = parentFragment.getParentFragment();
        }
        FragmentManager fragmentManager = parentFragment.getFragmentManager();
        if (fragmentManager == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
            }
        } else {
            try {
                c(getContext(), fragmentManager);
            } catch (IllegalStateException e) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.a.c();
        d();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDetach() {
        super.onDetach();
        this.f = null;
        d();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        this.a.d();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        this.a.e();
    }

    @Override // androidx.fragment.app.Fragment
    public final String toString() {
        return super.toString() + "{parent=" + b() + "}";
    }
}
