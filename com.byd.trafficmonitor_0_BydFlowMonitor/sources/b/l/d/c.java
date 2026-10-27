package b.l.d;

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
import androidx.fragment.app.Fragment;
import b.h.i.a;
import b.l.d.q;

/* JADX INFO: compiled from: FragmentAnim.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: compiled from: FragmentAnim.java */
    public class a implements a.InterfaceC0036a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Fragment f2792a;

        public a(Fragment fragment) {
            this.f2792a = fragment;
        }

        @Override // b.h.i.a.InterfaceC0036a
        public void a() {
            if (this.f2792a.getAnimatingAway() != null) {
                View animatingAway = this.f2792a.getAnimatingAway();
                this.f2792a.setAnimatingAway(null);
                animatingAway.clearAnimation();
            }
            this.f2792a.setAnimator(null);
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    public class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f2793a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f2794b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ q.g f2795c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.h.i.a f2796d;

        /* JADX INFO: compiled from: FragmentAnim.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f2794b.getAnimatingAway() != null) {
                    b.this.f2794b.setAnimatingAway(null);
                    b bVar = b.this;
                    bVar.f2795c.a(bVar.f2794b, bVar.f2796d);
                }
            }
        }

        public b(ViewGroup viewGroup, Fragment fragment, q.g gVar, b.h.i.a aVar) {
            this.f2793a = viewGroup;
            this.f2794b = fragment;
            this.f2795c = gVar;
            this.f2796d = aVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f2793a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: renamed from: b.l.d.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentAnim.java */
    public class C0052c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f2798a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2799b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f2800c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ q.g f2801d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ b.h.i.a f2802e;

        public C0052c(ViewGroup viewGroup, View view, Fragment fragment, q.g gVar, b.h.i.a aVar) {
            this.f2798a = viewGroup;
            this.f2799b = view;
            this.f2800c = fragment;
            this.f2801d = gVar;
            this.f2802e = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2798a.endViewTransition(this.f2799b);
            Animator animator2 = this.f2800c.getAnimator();
            this.f2800c.setAnimator(null);
            if (animator2 == null || this.f2798a.indexOfChild(this.f2799b) >= 0) {
                return;
            }
            this.f2801d.a(this.f2800c, this.f2802e);
        }
    }

    public static void a(Fragment fragment, d dVar, q.g gVar) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        b.h.i.a aVar = new b.h.i.a();
        aVar.c(new a(fragment));
        gVar.b(fragment, aVar);
        Animation animation = dVar.f2803a;
        if (animation != null) {
            e eVar = new e(animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            eVar.setAnimationListener(new b(viewGroup, fragment, gVar, aVar));
            fragment.mView.startAnimation(eVar);
            return;
        }
        Animator animator = dVar.f2804b;
        fragment.setAnimator(animator);
        animator.addListener(new C0052c(viewGroup, view, fragment, gVar, aVar));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    public static int b(Fragment fragment, boolean z, boolean z2) {
        return z2 ? z ? fragment.getPopEnterAnim() : fragment.getPopExitAnim() : z ? fragment.getEnterAnim() : fragment.getExitAnim();
    }

    public static d c(Context context, Fragment fragment, boolean z, boolean z2) {
        int nextTransition = fragment.getNextTransition();
        int iB = b(fragment, z, z2);
        boolean z3 = false;
        fragment.setAnimations(0, 0, 0, 0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            int i2 = b.l.b.visible_removing_fragment_view_tag;
            if (viewGroup.getTag(i2) != null) {
                fragment.mContainer.setTag(i2, null);
            }
        }
        ViewGroup viewGroup2 = fragment.mContainer;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(nextTransition, z, iB);
        if (animationOnCreateAnimation != null) {
            return new d(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(nextTransition, z, iB);
        if (animatorOnCreateAnimator != null) {
            return new d(animatorOnCreateAnimator);
        }
        if (iB == 0 && nextTransition != 0) {
            iB = d(nextTransition, z);
        }
        if (iB != 0) {
            boolean zEquals = "anim".equals(context.getResources().getResourceTypeName(iB));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, iB);
                    if (animationLoadAnimation != null) {
                        return new d(animationLoadAnimation);
                    }
                    z3 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z3) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, iB);
                    if (animatorLoadAnimator != null) {
                        return new d(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
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

    public static int d(int i2, boolean z) {
        if (i2 == 4097) {
            return z ? b.l.a.fragment_open_enter : b.l.a.fragment_open_exit;
        }
        if (i2 == 4099) {
            return z ? b.l.a.fragment_fade_enter : b.l.a.fragment_fade_exit;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z ? b.l.a.fragment_close_enter : b.l.a.fragment_close_exit;
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f2803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f2804b;

        public d(Animation animation) {
            this.f2803a = animation;
            this.f2804b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public d(Animator animator) {
            this.f2803a = null;
            this.f2804b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: compiled from: FragmentAnim.java */
    public static class e extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ViewGroup f2805b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final View f2806c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2807d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2808e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2809f;

        public e(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f2809f = true;
            this.f2805b = viewGroup;
            this.f2806c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f2809f = true;
            if (this.f2807d) {
                return !this.f2808e;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f2807d = true;
                b.h.m.q.a(this.f2805b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2807d || !this.f2809f) {
                this.f2805b.endViewTransition(this.f2806c);
                this.f2808e = true;
            } else {
                this.f2809f = false;
                this.f2805b.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f2) {
            this.f2809f = true;
            if (this.f2807d) {
                return !this.f2808e;
            }
            if (!super.getTransformation(j, transformation, f2)) {
                this.f2807d = true;
                b.h.m.q.a(this.f2805b, this);
            }
            return true;
        }
    }
}
