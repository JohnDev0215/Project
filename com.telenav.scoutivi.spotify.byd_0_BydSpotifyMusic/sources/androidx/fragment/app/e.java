package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.fragment.app.s;
import d0.b;

/* JADX INFO: compiled from: FragmentAnim.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: compiled from: FragmentAnim.java */
    class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f2315a;

        a(Fragment fragment) {
            this.f2315a = fragment;
        }

        @Override // d0.b.a
        public void a() {
            if (this.f2315a.o() != null) {
                View viewO = this.f2315a.o();
                this.f2315a.z1(null);
                viewO.clearAnimation();
            }
            this.f2315a.B1(null);
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2316a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f2317b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ s.g f2318c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d0.b f2319d;

        /* JADX INFO: compiled from: FragmentAnim.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f2317b.o() != null) {
                    b.this.f2317b.z1(null);
                    b bVar = b.this;
                    bVar.f2318c.a(bVar.f2317b, bVar.f2319d);
                }
            }
        }

        b(ViewGroup viewGroup, Fragment fragment, s.g gVar, d0.b bVar) {
            this.f2316a = viewGroup;
            this.f2317b = fragment;
            this.f2318c = gVar;
            this.f2319d = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f2316a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2321a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2322b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f2323c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ s.g f2324d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ d0.b f2325e;

        c(ViewGroup viewGroup, View view, Fragment fragment, s.g gVar, d0.b bVar) {
            this.f2321a = viewGroup;
            this.f2322b = view;
            this.f2323c = fragment;
            this.f2324d = gVar;
            this.f2325e = bVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2321a.endViewTransition(this.f2322b);
            Animator animatorP = this.f2323c.p();
            this.f2323c.B1(null);
            if (animatorP == null || this.f2321a.indexOfChild(this.f2322b) >= 0) {
                return;
            }
            this.f2324d.a(this.f2323c, this.f2325e);
        }
    }

    static void a(Fragment fragment, d dVar, s.g gVar) {
        View view = fragment.M;
        ViewGroup viewGroup = fragment.L;
        viewGroup.startViewTransition(view);
        d0.b bVar = new d0.b();
        bVar.c(new a(fragment));
        gVar.b(fragment, bVar);
        if (dVar.f2326a != null) {
            RunnableC0022e runnableC0022e = new RunnableC0022e(dVar.f2326a, viewGroup, view);
            fragment.z1(fragment.M);
            runnableC0022e.setAnimationListener(new b(viewGroup, fragment, gVar, bVar));
            fragment.M.startAnimation(runnableC0022e);
            return;
        }
        Animator animator = dVar.f2327b;
        fragment.B1(animator);
        animator.addListener(new c(viewGroup, view, fragment, gVar, bVar));
        animator.setTarget(fragment.M);
        animator.start();
    }

    private static int b(Fragment fragment, boolean z6, boolean z7) {
        return z7 ? z6 ? fragment.J() : fragment.K() : z6 ? fragment.t() : fragment.w();
    }

    static d c(Context context, Fragment fragment, boolean z6, boolean z7) {
        int iF = fragment.F();
        int iB = b(fragment, z6, z7);
        boolean z8 = false;
        fragment.A1(0, 0, 0, 0);
        ViewGroup viewGroup = fragment.L;
        if (viewGroup != null) {
            int i7 = q0.b.visible_removing_fragment_view_tag;
            if (viewGroup.getTag(i7) != null) {
                fragment.L.setTag(i7, null);
            }
        }
        ViewGroup viewGroup2 = fragment.L;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation animationV0 = fragment.v0(iF, z6, iB);
        if (animationV0 != null) {
            return new d(animationV0);
        }
        Animator animatorW0 = fragment.w0(iF, z6, iB);
        if (animatorW0 != null) {
            return new d(animatorW0);
        }
        if (iB == 0 && iF != 0) {
            iB = d(iF, z6);
        }
        if (iB != 0) {
            boolean zEquals = "anim".equals(context.getResources().getResourceTypeName(iB));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, iB);
                    if (animationLoadAnimation != null) {
                        return new d(animationLoadAnimation);
                    }
                    z8 = true;
                } catch (Resources.NotFoundException e7) {
                    throw e7;
                } catch (RuntimeException unused) {
                }
            }
            if (!z8) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, iB);
                    if (animatorLoadAnimator != null) {
                        return new d(animatorLoadAnimator);
                    }
                } catch (RuntimeException e8) {
                    if (zEquals) {
                        throw e8;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(context, iB);
                    if (animationLoadAnimation2 != null) {
                        return new d(animationLoadAnimation2);
                    }
                }
            }
        }
        return null;
    }

    private static int d(int i7, boolean z6) {
        if (i7 == 4097) {
            return z6 ? q0.a.fragment_open_enter : q0.a.fragment_open_exit;
        }
        if (i7 == 4099) {
            return z6 ? q0.a.fragment_fade_enter : q0.a.fragment_fade_exit;
        }
        if (i7 != 8194) {
            return -1;
        }
        return z6 ? q0.a.fragment_close_enter : q0.a.fragment_close_exit;
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f2326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f2327b;

        d(Animation animation) {
            this.f2326a = animation;
            this.f2327b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        d(Animator animator) {
            this.f2326a = null;
            this.f2327b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentAnim.java */
    static class RunnableC0022e extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final ViewGroup f2328e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final View f2329g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f2330h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f2331i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private boolean f2332j;

        RunnableC0022e(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f2332j = true;
            this.f2328e = viewGroup;
            this.f2329g = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j7, Transformation transformation) {
            this.f2332j = true;
            if (this.f2330h) {
                return !this.f2331i;
            }
            if (!super.getTransformation(j7, transformation)) {
                this.f2330h = true;
                i0.s.a(this.f2328e, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2330h || !this.f2332j) {
                this.f2328e.endViewTransition(this.f2329g);
                this.f2331i = true;
            } else {
                this.f2332j = false;
                this.f2328e.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j7, Transformation transformation, float f7) {
            this.f2332j = true;
            if (this.f2330h) {
                return !this.f2331i;
            }
            if (!super.getTransformation(j7, transformation, f7)) {
                this.f2330h = true;
                i0.s.a(this.f2328e, this);
            }
            return true;
        }
    }
}
