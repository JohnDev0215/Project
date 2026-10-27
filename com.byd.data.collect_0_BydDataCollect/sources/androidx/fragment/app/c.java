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
import androidx.fragment.app.p;
import b.g.g.a;

/* JADX INFO: compiled from: FragmentAnim.java */
/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: compiled from: FragmentAnim.java */
    static class a implements a.InterfaceC0039a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f1415a;

        a(Fragment fragment) {
            this.f1415a = fragment;
        }

        @Override // b.g.g.a.InterfaceC0039a
        public void a() {
            if (this.f1415a.l() != null) {
                View viewL = this.f1415a.l();
                this.f1415a.g1(null);
                viewL.clearAnimation();
            }
            this.f1415a.h1(null);
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    static class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1416a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1417b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ p.g f1418c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.g.g.a f1419d;

        /* JADX INFO: compiled from: FragmentAnim.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f1417b.l() != null) {
                    b.this.f1417b.g1(null);
                    b bVar = b.this;
                    bVar.f1418c.a(bVar.f1417b, bVar.f1419d);
                }
            }
        }

        b(ViewGroup viewGroup, Fragment fragment, p.g gVar, b.g.g.a aVar) {
            this.f1416a = viewGroup;
            this.f1417b = fragment;
            this.f1418c = gVar;
            this.f1419d = aVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f1416a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentAnim.java */
    static class C0020c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1421a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1422b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1423c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ p.g f1424d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b.g.g.a f1425e;

        C0020c(ViewGroup viewGroup, View view, Fragment fragment, p.g gVar, b.g.g.a aVar) {
            this.f1421a = viewGroup;
            this.f1422b = view;
            this.f1423c = fragment;
            this.f1424d = gVar;
            this.f1425e = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1421a.endViewTransition(this.f1422b);
            Animator animatorM = this.f1423c.m();
            this.f1423c.h1(null);
            if (animatorM == null || this.f1421a.indexOfChild(this.f1422b) >= 0) {
                return;
            }
            this.f1424d.a(this.f1423c, this.f1425e);
        }
    }

    static void a(Fragment fragment, d dVar, p.g gVar) {
        View view = fragment.I;
        ViewGroup viewGroup = fragment.H;
        viewGroup.startViewTransition(view);
        b.g.g.a aVar = new b.g.g.a();
        aVar.c(new a(fragment));
        gVar.b(fragment, aVar);
        Animation animation = dVar.f1426a;
        if (animation != null) {
            e eVar = new e(animation, viewGroup, view);
            fragment.g1(fragment.I);
            eVar.setAnimationListener(new b(viewGroup, fragment, gVar, aVar));
            fragment.I.startAnimation(eVar);
            return;
        }
        Animator animator = dVar.f1427b;
        fragment.h1(animator);
        animator.addListener(new C0020c(viewGroup, view, fragment, gVar, aVar));
        animator.setTarget(fragment.I);
        animator.start();
    }

    static d b(Context context, androidx.fragment.app.d dVar, Fragment fragment, boolean z) {
        int iC;
        int iY = fragment.y();
        int iX = fragment.x();
        boolean z2 = false;
        fragment.k1(0);
        View viewC = dVar.c(fragment.y);
        if (viewC != null) {
            int i2 = b.k.b.visible_removing_fragment_view_tag;
            if (viewC.getTag(i2) != null) {
                viewC.setTag(i2, null);
            }
        }
        ViewGroup viewGroup = fragment.H;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationE0 = fragment.e0(iY, z, iX);
        if (animationE0 != null) {
            return new d(animationE0);
        }
        Animator animatorF0 = fragment.f0(iY, z, iX);
        if (animatorF0 != null) {
            return new d(animatorF0);
        }
        if (iX != 0) {
            boolean zEquals = "anim".equals(context.getResources().getResourceTypeName(iX));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, iX);
                    if (animationLoadAnimation != null) {
                        return new d(animationLoadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, iX);
                    if (animatorLoadAnimator != null) {
                        return new d(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(context, iX);
                    if (animationLoadAnimation2 != null) {
                        return new d(animationLoadAnimation2);
                    }
                }
            }
        }
        if (iY != 0 && (iC = c(iY, z)) >= 0) {
            return new d(AnimationUtils.loadAnimation(context, iC));
        }
        return null;
    }

    private static int c(int i2, boolean z) {
        if (i2 == 4097) {
            return z ? b.k.a.fragment_open_enter : b.k.a.fragment_open_exit;
        }
        if (i2 == 4099) {
            return z ? b.k.a.fragment_fade_enter : b.k.a.fragment_fade_exit;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z ? b.k.a.fragment_close_enter : b.k.a.fragment_close_exit;
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f1426a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f1427b;

        d(Animation animation) {
            this.f1426a = animation;
            this.f1427b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        d(Animator animator) {
            this.f1426a = null;
            this.f1427b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    private static class e extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ViewGroup f1428b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final View f1429c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1430d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1431e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f1432f;

        e(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f1432f = true;
            this.f1428b = viewGroup;
            this.f1429c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f1432f = true;
            if (this.f1430d) {
                return !this.f1431e;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f1430d = true;
                b.g.k.q.a(this.f1428b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1430d || !this.f1432f) {
                this.f1428b.endViewTransition(this.f1429c);
                this.f1431e = true;
            } else {
                this.f1432f = false;
                this.f1428b.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f2) {
            this.f1432f = true;
            if (this.f1430d) {
                return !this.f1431e;
            }
            if (!super.getTransformation(j, transformation, f2)) {
                this.f1430d = true;
                b.g.k.q.a(this.f1428b, this);
            }
            return true;
        }
    }
}
