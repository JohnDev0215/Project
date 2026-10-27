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
import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.core.os.b;
import androidx.fragment.R$anim;
import androidx.fragment.R$id;
import androidx.fragment.app.s;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f681a;

        a(Fragment fragment) {
            this.f681a = fragment;
        }

        @Override // androidx.core.os.b.a
        public void a() {
            if (this.f681a.n() != null) {
                View viewN = this.f681a.n();
                this.f681a.i1(null);
                viewN.clearAnimation();
            }
            this.f681a.j1(null);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f682a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f683b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ s.g f684c;
        final /* synthetic */ androidx.core.os.b d;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f683b.n() != null) {
                    b.this.f683b.i1(null);
                    b bVar = b.this;
                    bVar.f684c.a(bVar.f683b, bVar.d);
                }
            }
        }

        b(ViewGroup viewGroup, Fragment fragment, s.g gVar, androidx.core.os.b bVar) {
            this.f682a = viewGroup;
            this.f683b = fragment;
            this.f684c = gVar;
            this.d = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f682a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f686a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f687b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f688c;
        final /* synthetic */ s.g d;
        final /* synthetic */ androidx.core.os.b e;

        c(ViewGroup viewGroup, View view, Fragment fragment, s.g gVar, androidx.core.os.b bVar) {
            this.f686a = viewGroup;
            this.f687b = view;
            this.f688c = fragment;
            this.d = gVar;
            this.e = bVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f686a.endViewTransition(this.f687b);
            Animator animatorO = this.f688c.o();
            this.f688c.j1(null);
            if (animatorO == null || this.f686a.indexOfChild(this.f687b) >= 0) {
                return;
            }
            this.d.a(this.f688c, this.e);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class f extends AnimationSet {
        f(@NonNull Animation animation) {
            super(false);
            addAnimation(animation);
        }
    }

    static void a(@NonNull Fragment fragment, @NonNull C0023d c0023d, @NonNull s.g gVar) {
        View view = fragment.J;
        ViewGroup viewGroup = fragment.I;
        viewGroup.startViewTransition(view);
        androidx.core.os.b bVar = new androidx.core.os.b();
        bVar.c(new a(fragment));
        gVar.b(fragment, bVar);
        if (c0023d.f689a != null) {
            e eVar = new e(c0023d.f689a, viewGroup, view);
            fragment.i1(fragment.J);
            eVar.setAnimationListener(new b(viewGroup, fragment, gVar, bVar));
            fragment.J.startAnimation(eVar);
            return;
        }
        Animator animator = c0023d.f690b;
        fragment.j1(animator);
        animator.addListener(new c(viewGroup, view, fragment, gVar, bVar));
        animator.setTarget(fragment.J);
        animator.start();
    }

    static C0023d b(@NonNull Context context, @NonNull Fragment fragment, boolean z) {
        int iC;
        int iZ = fragment.z();
        int iY = fragment.y();
        boolean z2 = false;
        fragment.m1(0);
        ViewGroup viewGroup = fragment.I;
        if (viewGroup != null && viewGroup.getTag(R$id.visible_removing_fragment_view_tag) != null) {
            fragment.I.setTag(R$id.visible_removing_fragment_view_tag, null);
        }
        ViewGroup viewGroup2 = fragment.I;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation animationE0 = fragment.e0(iZ, z, iY);
        if (animationE0 != null) {
            return new C0023d(animationE0);
        }
        Animator animatorF0 = fragment.f0(iZ, z, iY);
        if (animatorF0 != null) {
            return new C0023d(animatorF0);
        }
        if (iY != 0) {
            boolean zEquals = "anim".equals(context.getResources().getResourceTypeName(iY));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, iY);
                    if (animationLoadAnimation != null) {
                        return new C0023d(animationLoadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, iY);
                    if (animatorLoadAnimator != null) {
                        return new C0023d(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(context, iY);
                    if (animationLoadAnimation2 != null) {
                        return new C0023d(animationLoadAnimation2);
                    }
                }
            }
        }
        if (iZ != 0 && (iC = c(iZ, z)) >= 0) {
            return new C0023d(AnimationUtils.loadAnimation(context, iC));
        }
        return null;
    }

    @AnimRes
    private static int c(int i, boolean z) {
        if (i == 4097) {
            return z ? R$anim.fragment_open_enter : R$anim.fragment_open_exit;
        }
        if (i == 4099) {
            return z ? R$anim.fragment_fade_enter : R$anim.fragment_fade_exit;
        }
        if (i != 8194) {
            return -1;
        }
        return z ? R$anim.fragment_close_enter : R$anim.fragment_close_exit;
    }

    /* JADX INFO: renamed from: androidx.fragment.app.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    static class C0023d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f689a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f690b;

        C0023d(Animation animation) {
            this.f689a = animation;
            this.f690b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        C0023d(Animator animator) {
            this.f689a = null;
            this.f690b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class e extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f691c;
        private final View d;
        private boolean e;
        private boolean f;
        private boolean g;

        e(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.g = true;
            this.f691c = viewGroup;
            this.d = view;
            addAnimation(animation);
            this.f691c.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation) {
            this.g = true;
            if (this.e) {
                return !this.f;
            }
            if (!super.getTransformation(j, transformation)) {
                this.e = true;
                androidx.core.view.q.a(this.f691c, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.e || !this.g) {
                this.f691c.endViewTransition(this.d);
                this.f = true;
            } else {
                this.g = false;
                this.f691c.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation, float f) {
            this.g = true;
            if (this.e) {
                return !this.f;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.e = true;
                androidx.core.view.q.a(this.f691c, this);
            }
            return true;
        }
    }
}
