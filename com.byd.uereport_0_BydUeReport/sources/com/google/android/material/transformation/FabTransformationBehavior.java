package com.google.android.material.transformation;

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
import com.bydux.api.common.views.skin.entity.SkinAttr;
import com.google.android.material.R$id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import j5.h;
import j5.i;
import j5.j;
import java.util.ArrayList;
import java.util.List;
import o0.t;
import s5.c;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f4173c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f4174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f4175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f4176f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4177g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4178h;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f4179d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f4180e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f4181f;

        public a(boolean z7, View view, View view2) {
            this.f4179d = z7;
            this.f4180e = view;
            this.f4181f = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f4179d) {
                return;
            }
            this.f4180e.setVisibility(4);
            this.f4181f.setAlpha(1.0f);
            this.f4181f.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f4179d) {
                this.f4180e.setVisibility(0);
                this.f4181f.setAlpha(0.0f);
                this.f4181f.setVisibility(4);
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f4183d;

        public b(View view) {
            this.f4183d = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f4183d.invalidate();
        }
    }

    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ s5.c f4185d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Drawable f4186e;

        public c(s5.c cVar, Drawable drawable) {
            this.f4185d = cVar;
            this.f4186e = drawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f4185d.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f4185d.setCircularRevealOverlayDrawable(this.f4186e);
        }
    }

    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ s5.c f4188d;

        public d(s5.c cVar) {
            this.f4188d = cVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c.e revealInfo = this.f4188d.getRevealInfo();
            revealInfo.f7919c = Float.MAX_VALUE;
            this.f4188d.setRevealInfo(revealInfo);
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f4190a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public j f4191b;
    }

    public FabTransformationBehavior() {
        this.f4173c = new Rect();
        this.f4174d = new RectF();
        this.f4175e = new RectF();
        this.f4176f = new int[2];
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet J(View view, View view2, boolean z7, boolean z8) {
        e eVarE0 = e0(view2.getContext(), z7);
        if (z7) {
            this.f4177g = view.getTranslationX();
            this.f4178h = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (Build.VERSION.SDK_INT >= 21) {
            X(view, view2, z7, z8, eVarE0, arrayList, arrayList2);
        }
        RectF rectF = this.f4174d;
        c0(view, view2, z7, z8, eVarE0, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        W(view, view2, z7, eVarE0, arrayList);
        Z(view, view2, z7, z8, eVarE0, arrayList, arrayList2);
        Y(view, view2, z7, z8, eVarE0, fWidth, fHeight, arrayList, arrayList2);
        V(view, view2, z7, z8, eVarE0, arrayList, arrayList2);
        U(view, view2, z7, z8, eVarE0, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        j5.b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(z7, view2, view));
        int size = arrayList2.size();
        for (int i8 = 0; i8 < size; i8++) {
            animatorSet.addListener(arrayList2.get(i8));
        }
        return animatorSet;
    }

    public final ViewGroup K(View view) {
        View viewFindViewById = view.findViewById(R$id.mtrl_child_content_container);
        return viewFindViewById != null ? f0(viewFindViewById) : ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) ? f0(((ViewGroup) view).getChildAt(0)) : f0(view);
    }

    public final void L(View view, e eVar, i iVar, i iVar2, float f8, float f9, float f10, float f11, RectF rectF) {
        float fS = S(eVar, iVar, f8, f10);
        float fS2 = S(eVar, iVar2, f9, f11);
        Rect rect = this.f4173c;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.f4174d;
        rectF2.set(rect);
        RectF rectF3 = this.f4175e;
        T(view, rectF3);
        rectF3.offset(fS, fS2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    public final void M(View view, RectF rectF) {
        T(view, rectF);
        rectF.offset(this.f4177g, this.f4178h);
    }

    public final Pair<i, i> N(float f8, float f9, boolean z7, e eVar) {
        i iVarH;
        i iVarH2;
        if (f8 == 0.0f || f9 == 0.0f) {
            iVarH = eVar.f4190a.h("translationXLinear");
            iVarH2 = eVar.f4190a.h("translationYLinear");
        } else if ((!z7 || f9 >= 0.0f) && (z7 || f9 <= 0.0f)) {
            iVarH = eVar.f4190a.h("translationXCurveDownwards");
            iVarH2 = eVar.f4190a.h("translationYCurveDownwards");
        } else {
            iVarH = eVar.f4190a.h("translationXCurveUpwards");
            iVarH2 = eVar.f4190a.h("translationYCurveUpwards");
        }
        return new Pair<>(iVarH, iVarH2);
    }

    public final float O(View view, View view2, j jVar) {
        RectF rectF = this.f4174d;
        RectF rectF2 = this.f4175e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(-Q(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    public final float P(View view, View view2, j jVar) {
        RectF rectF = this.f4174d;
        RectF rectF2 = this.f4175e;
        M(view, rectF);
        T(view2, rectF2);
        rectF2.offset(0.0f, -R(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    public final float Q(View view, View view2, j jVar) {
        float fCenterX;
        float fCenterX2;
        float f8;
        RectF rectF = this.f4174d;
        RectF rectF2 = this.f4175e;
        M(view, rectF);
        T(view2, rectF2);
        int i8 = jVar.f5598a & 7;
        if (i8 == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else if (i8 == 3) {
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        } else {
            if (i8 != 5) {
                f8 = 0.0f;
                return f8 + jVar.f5599b;
            }
            fCenterX = rectF2.right;
            fCenterX2 = rectF.right;
        }
        f8 = fCenterX - fCenterX2;
        return f8 + jVar.f5599b;
    }

    public final float R(View view, View view2, j jVar) {
        float fCenterY;
        float fCenterY2;
        float f8;
        RectF rectF = this.f4174d;
        RectF rectF2 = this.f4175e;
        M(view, rectF);
        T(view2, rectF2);
        int i8 = jVar.f5598a & 112;
        if (i8 == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else if (i8 == 48) {
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        } else {
            if (i8 != 80) {
                f8 = 0.0f;
                return f8 + jVar.f5600c;
            }
            fCenterY = rectF2.bottom;
            fCenterY2 = rectF.bottom;
        }
        f8 = fCenterY - fCenterY2;
        return f8 + jVar.f5600c;
    }

    public final float S(e eVar, i iVar, float f8, float f9) {
        long jC = iVar.c();
        long jD = iVar.d();
        i iVarH = eVar.f4190a.h("expansion");
        return j5.a.a(f8, f9, iVar.e().getInterpolation((((iVarH.c() + iVarH.d()) + 17) - jC) / jD));
    }

    public final void T(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f4176f);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public final void U(View view, View view2, boolean z7, boolean z8, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup viewGroupK;
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof s5.c) && s5.b.f7903a == 0) || (viewGroupK = K(view2)) == null) {
                return;
            }
            if (z7) {
                if (!z8) {
                    j5.d.f5584a.set(viewGroupK, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, j5.d.f5584a, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupK, j5.d.f5584a, 0.0f);
            }
            eVar.f4190a.h("contentFade").a(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V(View view, View view2, boolean z7, boolean z8, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof s5.c) {
            s5.c cVar = (s5.c) view2;
            int iD0 = d0(view);
            int i8 = 16777215 & iD0;
            if (z7) {
                if (!z8) {
                    cVar.setCircularRevealScrimColor(iD0);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, c.d.f7916a, i8);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, c.d.f7916a, iD0);
            }
            objectAnimatorOfInt.setEvaluator(j5.c.b());
            eVar.f4190a.h(SkinAttr.RES_TYPE_NAME_COLOR).a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    public final void W(View view, View view2, boolean z7, e eVar, List<Animator> list) {
        float fQ = Q(view, view2, eVar.f4191b);
        float fR = R(view, view2, eVar.f4191b);
        Pair<i, i> pairN = N(fQ, fR, z7, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z7) {
            fQ = this.f4177g;
        }
        fArr[0] = fQ;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z7) {
            fR = this.f4178h;
        }
        fArr2[0] = fR;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
        iVar.a(objectAnimatorOfFloat);
        iVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    @TargetApi(21)
    public final void X(View view, View view2, boolean z7, boolean z8, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        float fV = t.v(view2) - t.v(view);
        if (z7) {
            if (!z8) {
                view2.setTranslationZ(-fV);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fV);
        }
        eVar.f4190a.h("elevation").a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y(View view, View view2, boolean z7, boolean z8, e eVar, float f8, float f9, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorA;
        if (view2 instanceof s5.c) {
            s5.c cVar = (s5.c) view2;
            float fO = O(view, view2, eVar.f4191b);
            float fP = P(view, view2, eVar.f4191b);
            ((FloatingActionButton) view).i(this.f4173c);
            float fWidth = this.f4173c.width() / 2.0f;
            i iVarH = eVar.f4190a.h("expansion");
            if (z7) {
                if (!z8) {
                    cVar.setRevealInfo(new c.e(fO, fP, fWidth));
                }
                if (z8) {
                    fWidth = cVar.getRevealInfo().f7919c;
                }
                animatorA = s5.a.a(cVar, fO, fP, a6.a.b(fO, fP, 0.0f, 0.0f, f8, f9));
                animatorA.addListener(new d(cVar));
                b0(view2, iVarH.c(), (int) fO, (int) fP, fWidth, list);
            } else {
                float f10 = cVar.getRevealInfo().f7919c;
                Animator animatorA2 = s5.a.a(cVar, fO, fP, fWidth);
                int i8 = (int) fO;
                int i9 = (int) fP;
                b0(view2, iVarH.c(), i8, i9, f10, list);
                a0(view2, iVarH.c(), iVarH.d(), eVar.f4190a.i(), i8, i9, fWidth, list);
                animatorA = animatorA2;
            }
            iVarH.a(animatorA);
            list.add(animatorA);
            list2.add(s5.a.b(cVar));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z(View view, View view2, boolean z7, boolean z8, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof s5.c) && (view instanceof ImageView)) {
            s5.c cVar = (s5.c) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z7) {
                if (!z8) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, j5.e.f5585a, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, j5.e.f5585a, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new b(view2));
            eVar.f4190a.h("iconFade").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new c(cVar, drawable));
        }
    }

    public final void a0(View view, long j8, long j9, long j10, int i8, int i9, float f8, List<Animator> list) {
        if (Build.VERSION.SDK_INT >= 21) {
            long j11 = j8 + j9;
            if (j11 < j10) {
                Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i8, i9, f8, f8);
                animatorCreateCircularReveal.setStartDelay(j11);
                animatorCreateCircularReveal.setDuration(j10 - j11);
                list.add(animatorCreateCircularReveal);
            }
        }
    }

    public final void b0(View view, long j8, int i8, int i9, float f8, List<Animator> list) {
        if (Build.VERSION.SDK_INT < 21 || j8 <= 0) {
            return;
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i8, i9, f8, f8);
        animatorCreateCircularReveal.setStartDelay(0L);
        animatorCreateCircularReveal.setDuration(j8);
        list.add(animatorCreateCircularReveal);
    }

    public final void c0(View view, View view2, boolean z7, boolean z8, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fQ = Q(view, view2, eVar.f4191b);
        float fR = R(view, view2, eVar.f4191b);
        Pair<i, i> pairN = N(fQ, fR, z7, eVar);
        i iVar = (i) pairN.first;
        i iVar2 = (i) pairN.second;
        if (z7) {
            if (!z8) {
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

    public final int d0(View view) {
        ColorStateList colorStateListR = t.r(view);
        if (colorStateListR != null) {
            return colorStateListR.getColorForState(view.getDrawableState(), colorStateListR.getDefaultColor());
        }
        return 0;
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

    public abstract e e0(Context context, boolean z7);

    public final ViewGroup f0(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void g(CoordinatorLayout.f fVar) {
        if (fVar.f1193h == 0) {
            fVar.f1193h = 80;
        }
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4173c = new Rect();
        this.f4174d = new RectF();
        this.f4175e = new RectF();
        this.f4176f = new int[2];
    }
}
