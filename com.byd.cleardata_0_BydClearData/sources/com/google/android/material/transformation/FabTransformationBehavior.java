package com.google.android.material.transformation;

import a.g.k.t;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.a.a.a.f;
import b.a.a.a.m.h;
import b.a.a.a.m.i;
import b.a.a.a.m.j;
import b.a.a.a.r.d;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Rect f3265c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final RectF f3266d;
    private final RectF e;
    private final int[] f;
    private float g;
    private float h;

    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f3267a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f3268b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f3269c;

        a(boolean z, View view, View view2) {
            this.f3267a = z;
            this.f3268b = view;
            this.f3269c = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f3267a) {
                return;
            }
            this.f3268b.setVisibility(4);
            this.f3269c.setAlpha(1.0f);
            this.f3269c.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f3267a) {
                this.f3268b.setVisibility(0);
                this.f3269c.setAlpha(0.0f);
                this.f3269c.setVisibility(4);
            }
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f3271a;

        b(View view) {
            this.f3271a = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f3271a.invalidate();
        }
    }

    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.a.a.r.d f3273a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Drawable f3274b;

        c(b.a.a.a.r.d dVar, Drawable drawable) {
            this.f3273a = dVar;
            this.f3274b = drawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3273a.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f3273a.setCircularRevealOverlayDrawable(this.f3274b);
        }
    }

    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.a.a.r.d f3276a;

        d(b.a.a.a.r.d dVar) {
            this.f3276a = dVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.e revealInfo = this.f3276a.getRevealInfo();
            revealInfo.f1769c = Float.MAX_VALUE;
            this.f3276a.setRevealInfo(revealInfo);
        }
    }

    protected static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f3278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public j f3279b;

        protected e() {
        }
    }

    public FabTransformationBehavior() {
        this.f3265c = new Rect();
        this.f3266d = new RectF();
        this.e = new RectF();
        this.f = new int[2];
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3265c = new Rect();
        this.f3266d = new RectF();
        this.e = new RectF();
        this.f = new int[2];
    }

    private ViewGroup K(View view) {
        View viewFindViewById = view.findViewById(f.n);
        return viewFindViewById != null ? f0(viewFindViewById) : ((view instanceof com.google.android.material.transformation.b) || (view instanceof com.google.android.material.transformation.a)) ? f0(((ViewGroup) view).getChildAt(0)) : f0(view);
    }

    private void L(View view, e eVar, i iVar, i iVar2, float f, float f2, float f3, float f4, RectF rectF) {
        float fS = S(eVar, iVar, f, f3);
        float fS2 = S(eVar, iVar2, f2, f4);
        Rect rect = this.f3265c;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.f3266d;
        rectF2.set(rect);
        RectF rectF3 = this.e;
        T(view, rectF3);
        rectF3.offset(fS, fS2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private void M(View view, RectF rectF) {
        T(view, rectF);
        rectF.offset(this.g, this.h);
    }

    private Pair<i, i> N(float f, float f2, boolean z, e eVar) {
        i iVarE;
        h hVar;
        String str;
        if (f == 0.0f || f2 == 0.0f) {
            iVarE = eVar.f3278a.e("translationXLinear");
            hVar = eVar.f3278a;
            str = "translationYLinear";
        } else if ((!z || f2 >= 0.0f) && (z || f2 <= 0.0f)) {
            iVarE = eVar.f3278a.e("translationXCurveDownwards");
            hVar = eVar.f3278a;
            str = "translationYCurveDownwards";
        } else {
            iVarE = eVar.f3278a.e("translationXCurveUpwards");
            hVar = eVar.f3278a;
            str = "translationYCurveUpwards";
        }
        return new Pair<>(iVarE, hVar.e(str));
    }

    private float O(View view, View view2, j jVar) {
        RectF rectF = this.f3266d;
        RectF rectF2 = this.e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(-Q(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private float P(View view, View view2, j jVar) {
        RectF rectF = this.f3266d;
        RectF rectF2 = this.e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(0.0f, -R(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    private float Q(View view, View view2, j jVar) {
        float fCenterX;
        float fCenterX2;
        float f;
        RectF rectF = this.f3266d;
        RectF rectF2 = this.e;
        M(view, rectF);
        T(view2, rectF2);
        int i = jVar.f1750a & 7;
        if (i == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else if (i == 3) {
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        } else {
            if (i != 5) {
                f = 0.0f;
                return f + jVar.f1751b;
            }
            fCenterX = rectF2.right;
            fCenterX2 = rectF.right;
        }
        f = fCenterX - fCenterX2;
        return f + jVar.f1751b;
    }

    private float R(View view, View view2, j jVar) {
        float fCenterY;
        float fCenterY2;
        float f;
        RectF rectF = this.f3266d;
        RectF rectF2 = this.e;
        M(view, rectF);
        T(view2, rectF2);
        int i = jVar.f1750a & 112;
        if (i == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else if (i == 48) {
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        } else {
            if (i != 80) {
                f = 0.0f;
                return f + jVar.f1752c;
            }
            fCenterY = rectF2.bottom;
            fCenterY2 = rectF.bottom;
        }
        f = fCenterY - fCenterY2;
        return f + jVar.f1752c;
    }

    private float S(e eVar, i iVar, float f, float f2) {
        long jC = iVar.c();
        long jD = iVar.d();
        i iVarE = eVar.f3278a.e("expansion");
        return b.a.a.a.m.a.a(f, f2, iVar.e().getInterpolation((((iVarE.c() + iVarE.d()) + 17) - jC) / jD));
    }

    private void T(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    private void U(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup viewGroupK;
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof b.a.a.a.r.d) && b.a.a.a.r.c.f1762a == 0) || (viewGroupK = K(view2)) == null) {
                return;
            }
            if (z) {
                if (!z2) {
                    b.a.a.a.m.d.f1737a.set(viewGroupK, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, b.a.a.a.m.d.f1737a, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, b.a.a.a.m.d.f1737a, 0.0f);
            }
            eVar.f3278a.e("contentFade").a(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void V(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof b.a.a.a.r.d) {
            b.a.a.a.r.d dVar = (b.a.a.a.r.d) view2;
            int iD0 = d0(view);
            int i = 16777215 & iD0;
            if (z) {
                if (!z2) {
                    dVar.setCircularRevealScrimColor(iD0);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(dVar, d.C0053d.f1766a, i);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(dVar, d.C0053d.f1766a, iD0);
            }
            objectAnimatorOfInt.setEvaluator(b.a.a.a.m.c.b());
            eVar.f3278a.e("color").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    private void W(View view, View view2, boolean z, e eVar, List<Animator> list) {
        float fQ = Q(view, view2, eVar.f3279b);
        float fR = R(view, view2, eVar.f3279b);
        Pair<i, i> pairN = N(fQ, fR, z, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z) {
            fQ = this.g;
        }
        fArr[0] = fQ;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z) {
            fR = this.h;
        }
        fArr2[0] = fR;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    @TargetApi(21)
    private void X(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        float fT = t.t(view2) - t.t(view);
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-fT);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fT);
        }
        eVar.f3278a.e("elevation").a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void Y(View view, View view2, boolean z, boolean z2, e eVar, float f, float f2, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorA;
        if (view2 instanceof b.a.a.a.r.d) {
            b.a.a.a.r.d dVar = (b.a.a.a.r.d) view2;
            float fO = O(view, view2, eVar.f3279b);
            float fP = P(view, view2, eVar.f3279b);
            ((FloatingActionButton) view).i(this.f3265c);
            float fWidth = this.f3265c.width() / 2.0f;
            i iVarE = eVar.f3278a.e("expansion");
            if (z) {
                if (!z2) {
                    dVar.setRevealInfo(new d.e(fO, fP, fWidth));
                }
                if (z2) {
                    fWidth = dVar.getRevealInfo().f1769c;
                }
                animatorA = b.a.a.a.r.a.a(dVar, fO, fP, b.a.a.a.w.a.b(fO, fP, 0.0f, 0.0f, f, f2));
                animatorA.addListener(new d(dVar));
                b0(view2, iVarE.c(), (int) fO, (int) fP, fWidth, list);
            } else {
                float f3 = dVar.getRevealInfo().f1769c;
                Animator animatorA2 = b.a.a.a.r.a.a(dVar, fO, fP, fWidth);
                int i = (int) fO;
                int i2 = (int) fP;
                b0(view2, iVarE.c(), i, i2, f3, list);
                a0(view2, iVarE.c(), iVarE.d(), eVar.f3278a.f(), i, i2, fWidth, list);
                animatorA = animatorA2;
            }
            iVarE.a(animatorA);
            list.add(animatorA);
            list2.add(b.a.a.a.r.a.b(dVar));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void Z(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof b.a.a.a.r.d) && (view instanceof ImageView)) {
            b.a.a.a.r.d dVar = (b.a.a.a.r.d) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z) {
                if (!z2) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, b.a.a.a.m.e.f1738a, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, b.a.a.a.m.e.f1738a, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new b(view2));
            eVar.f3278a.e("iconFade").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new c(dVar, drawable));
        }
    }

    private void a0(View view, long j, long j2, long j3, int i, int i2, float f, List<Animator> list) {
        if (Build.VERSION.SDK_INT >= 21) {
            long j4 = j + j2;
            if (j4 < j3) {
                Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
                animatorCreateCircularReveal.setStartDelay(j4);
                animatorCreateCircularReveal.setDuration(j3 - j4);
                list.add(animatorCreateCircularReveal);
            }
        }
    }

    private void b0(View view, long j, int i, int i2, float f, List<Animator> list) {
        if (Build.VERSION.SDK_INT < 21 || j <= 0) {
            return;
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f);
        animatorCreateCircularReveal.setStartDelay(0L);
        animatorCreateCircularReveal.setDuration(j);
        list.add(animatorCreateCircularReveal);
    }

    private void c0(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fQ = Q(view, view2, eVar.f3279b);
        float fR = R(view, view2, eVar.f3279b);
        Pair<i, i> pairN = N(fQ, fR, z, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        if (z) {
            if (!z2) {
                view2.setTranslationX(-fQ);
                view2.setTranslationY(-fR);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            L(view2, eVar, iVar, iVar2, -fQ, -fR, 0.0f, 0.0f, rectF);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -fQ);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -fR);
        }
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    private int d0(View view) {
        ColorStateList colorStateListP = t.p(view);
        if (colorStateListP != null) {
            return colorStateListP.getColorForState(view.getDrawableState(), colorStateListP.getDefaultColor());
        }
        return 0;
    }

    private ViewGroup f0(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet J(View view, View view2, boolean z, boolean z2) {
        e eVarE0 = e0(view2.getContext(), z);
        if (z) {
            this.g = view.getTranslationX();
            this.h = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (Build.VERSION.SDK_INT >= 21) {
            X(view, view2, z, z2, eVarE0, arrayList, arrayList2);
        }
        RectF rectF = this.f3266d;
        c0(view, view2, z, z2, eVarE0, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        W(view, view2, z, eVarE0, arrayList);
        Z(view, view2, z, z2, eVarE0, arrayList, arrayList2);
        Y(view, view2, z, z2, eVarE0, fWidth, fHeight, arrayList, arrayList2);
        V(view, view2, z, z2, eVarE0, arrayList, arrayList2);
        U(view, view2, z, z2, eVarE0, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        b.a.a.a.m.b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(z, view2, view));
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            animatorSet.addListener(arrayList2.get(i));
        }
        return animatorSet;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean e(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    protected abstract e e0(Context context, boolean z);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void g(CoordinatorLayout.f fVar) {
        if (fVar.h == 0) {
            fVar.h = 80;
        }
    }
}
