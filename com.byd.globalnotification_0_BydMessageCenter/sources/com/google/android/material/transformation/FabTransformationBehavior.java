package com.google.android.material.transformation;

import a.b.k.l;
import a.h.k.q;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.f;
import b.d.a.a.k0.e;
import b.d.a.a.l.d;
import b.d.a.a.l.g;
import b.d.a.a.l.h;
import b.d.a.a.l.i;
import b.d.a.a.u.c;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f2501c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f2502d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f2503e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f2504f;
    public float g;
    public float h;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2505a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2506b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f2507c;

        public a(FabTransformationBehavior fabTransformationBehavior, boolean z, View view, View view2) {
            this.f2505a = z;
            this.f2506b = view;
            this.f2507c = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2505a) {
                return;
            }
            this.f2506b.setVisibility(4);
            this.f2507c.setAlpha(1.0f);
            this.f2507c.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f2505a) {
                this.f2506b.setVisibility(0);
                this.f2507c.setAlpha(0.0f);
                this.f2507c.setVisibility(4);
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g f2508a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public i f2509b;
    }

    public FabTransformationBehavior() {
        this.f2501c = new Rect();
        this.f2502d = new RectF();
        this.f2503e = new RectF();
        this.f2504f = new int[2];
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2501c = new Rect();
        this.f2502d = new RectF();
        this.f2503e = new RectF();
        this.f2504f = new int[2];
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet D(View view, View view2, boolean z, boolean z2) {
        b bVar;
        ArrayList arrayList;
        ArrayList arrayList2;
        Animator animatorO;
        h hVar;
        ArrayList arrayList3;
        ObjectAnimator objectAnimatorOfInt;
        Context context = view2.getContext();
        int i = z ? b.d.a.a.a.mtrl_fab_transformation_sheet_expand_spec : b.d.a.a.a.mtrl_fab_transformation_sheet_collapse_spec;
        b bVar2 = new b();
        bVar2.f2508a = g.b(context, i);
        bVar2.f2509b = new i(17, 0.0f, 0.0f);
        if (z) {
            this.g = view.getTranslationX();
            this.h = view.getTranslationY();
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        L(view, view2, z, z2, bVar2, arrayList4);
        RectF rectF = this.f2502d;
        M(view, view2, z, z2, bVar2, arrayList4, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        float F = F(view, view2, bVar2.f2509b);
        float fG = G(view, view2, bVar2.f2509b);
        Pair<h, h> pairE = E(F, fG, z, bVar2);
        h hVar2 = (h) pairE.first;
        h hVar3 = (h) pairE.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z) {
            F = this.g;
        }
        fArr[0] = F;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z) {
            fG = this.h;
        }
        fArr2[0] = fG;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
        hVar2.a(objectAnimatorOfFloat);
        hVar3.a(objectAnimatorOfFloat2);
        arrayList4.add(objectAnimatorOfFloat);
        arrayList4.add(objectAnimatorOfFloat2);
        boolean z3 = view2 instanceof c;
        if (z3 && (view instanceof ImageView)) {
            c cVar = (c) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null) {
                drawable.mutate();
                if (z) {
                    if (!z2) {
                        drawable.setAlpha(255);
                    }
                    objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, d.f1973b, 0);
                } else {
                    objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, d.f1973b, 255);
                }
                objectAnimatorOfInt.addUpdateListener(new b.d.a.a.k0.a(this, view2));
                bVar2.f2508a.d("iconFade").a(objectAnimatorOfInt);
                arrayList4.add(objectAnimatorOfInt);
                arrayList5.add(new b.d.a.a.k0.b(this, cVar, drawable));
            }
        }
        if (z3) {
            c cVar2 = (c) view2;
            i iVar = bVar2.f2509b;
            RectF rectF2 = this.f2502d;
            RectF rectF3 = this.f2503e;
            I(view, rectF2);
            rectF2.offset(this.g, this.h);
            I(view2, rectF3);
            rectF3.offset(-F(view, view2, iVar), 0.0f);
            float fCenterX = rectF2.centerX() - rectF3.left;
            i iVar2 = bVar2.f2509b;
            RectF rectF4 = this.f2502d;
            RectF rectF5 = this.f2503e;
            I(view, rectF4);
            rectF4.offset(this.g, this.h);
            I(view2, rectF5);
            rectF5.offset(0.0f, -G(view, view2, iVar2));
            float fCenterY = rectF4.centerY() - rectF5.top;
            ((FloatingActionButton) view).g(this.f2501c);
            float fWidth2 = this.f2501c.width() / 2.0f;
            h hVarD = bVar2.f2508a.d("expansion");
            if (z) {
                if (!z2) {
                    cVar2.setRevealInfo(new c.e(fCenterX, fCenterY, fWidth2));
                }
                if (z2) {
                    fWidth2 = cVar2.getRevealInfo().f2064c;
                }
                float fQ = l.i.q(fCenterX, fCenterY, 0.0f, 0.0f);
                float fQ2 = l.i.q(fCenterX, fCenterY, fWidth, 0.0f);
                float fQ3 = l.i.q(fCenterX, fCenterY, fWidth, fHeight);
                float fQ4 = l.i.q(fCenterX, fCenterY, 0.0f, fHeight);
                if (fQ <= fQ2 || fQ <= fQ3 || fQ <= fQ4) {
                    fQ = (fQ2 <= fQ3 || fQ2 <= fQ4) ? fQ3 > fQ4 ? fQ3 : fQ4 : fQ2;
                }
                animatorO = l.i.o(cVar2, fCenterX, fCenterY, fQ);
                animatorO.addListener(new b.d.a.a.k0.c(this, cVar2));
                long j = hVarD.f1981a;
                int i2 = (int) fCenterX;
                int i3 = (int) fCenterY;
                if (j > 0) {
                    Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view2, i2, i3, fWidth2, fWidth2);
                    animatorCreateCircularReveal.setStartDelay(0L);
                    animatorCreateCircularReveal.setDuration(j);
                    arrayList4.add(animatorCreateCircularReveal);
                }
                hVar = hVarD;
                bVar = bVar2;
                arrayList2 = arrayList4;
                arrayList = arrayList5;
            } else {
                float f2 = cVar2.getRevealInfo().f2064c;
                Animator animatorO2 = l.i.o(cVar2, fCenterX, fCenterY, fWidth2);
                long j2 = hVarD.f1981a;
                int i4 = (int) fCenterX;
                int i5 = (int) fCenterY;
                if (j2 > 0) {
                    Animator animatorCreateCircularReveal2 = ViewAnimationUtils.createCircularReveal(view2, i4, i5, f2, f2);
                    animatorCreateCircularReveal2.setStartDelay(0L);
                    animatorCreateCircularReveal2.setDuration(j2);
                    arrayList4.add(animatorCreateCircularReveal2);
                }
                long j3 = hVarD.f1981a;
                long j4 = hVarD.f1982b;
                g gVar = bVar2.f2508a;
                int i6 = gVar.f1979a.f490c;
                bVar = bVar2;
                int i7 = 0;
                long jMax = 0;
                while (i7 < i6) {
                    int i8 = i6;
                    h hVarK = gVar.f1979a.k(i7);
                    jMax = Math.max(jMax, hVarK.f1981a + hVarK.f1982b);
                    i7++;
                    i6 = i8;
                    arrayList4 = arrayList4;
                    arrayList5 = arrayList5;
                    gVar = gVar;
                }
                ArrayList arrayList6 = arrayList4;
                arrayList = arrayList5;
                long j5 = j3 + j4;
                if (j5 < jMax) {
                    Animator animatorCreateCircularReveal3 = ViewAnimationUtils.createCircularReveal(view2, i4, i5, fWidth2, fWidth2);
                    animatorCreateCircularReveal3.setStartDelay(j5);
                    animatorCreateCircularReveal3.setDuration(jMax - j5);
                    arrayList2 = arrayList6;
                    arrayList2.add(animatorCreateCircularReveal3);
                } else {
                    arrayList2 = arrayList6;
                }
                animatorO = animatorO2;
                hVar = hVarD;
            }
            hVar.a(animatorO);
            arrayList2.add(animatorO);
            arrayList3 = arrayList;
            arrayList3.add(new b.d.a.a.u.a(cVar2));
        } else {
            bVar = bVar2;
            arrayList2 = arrayList4;
            arrayList3 = arrayList5;
        }
        K(view, view2, z, z2, bVar, arrayList2);
        J(view2, z, z2, bVar, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        l.i.f0(animatorSet, arrayList2);
        animatorSet.addListener(new a(this, z, view2, view));
        int size = arrayList3.size();
        for (int i9 = 0; i9 < size; i9++) {
            animatorSet.addListener((Animator.AnimatorListener) arrayList3.get(i9));
        }
        return animatorSet;
    }

    public final Pair<h, h> E(float f2, float f3, boolean z, b bVar) {
        h hVarD;
        g gVar;
        String str;
        if (f2 == 0.0f || f3 == 0.0f) {
            hVarD = bVar.f2508a.d("translationXLinear");
            gVar = bVar.f2508a;
            str = "translationYLinear";
        } else if ((!z || f3 >= 0.0f) && (z || f3 <= 0.0f)) {
            hVarD = bVar.f2508a.d("translationXCurveDownwards");
            gVar = bVar.f2508a;
            str = "translationYCurveDownwards";
        } else {
            hVarD = bVar.f2508a.d("translationXCurveUpwards");
            gVar = bVar.f2508a;
            str = "translationYCurveUpwards";
        }
        return new Pair<>(hVarD, gVar.d(str));
    }

    public final float F(View view, View view2, i iVar) {
        float fCenterX;
        float fCenterX2;
        RectF rectF = this.f2502d;
        RectF rectF2 = this.f2503e;
        I(view, rectF);
        rectF.offset(this.g, this.h);
        I(view2, rectF2);
        float f2 = 0.0f;
        int i = iVar.f1986a & 7;
        if (i == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else {
            if (i != 3) {
                if (i == 5) {
                    fCenterX = rectF2.right;
                    fCenterX2 = rectF.right;
                }
                return f2 + iVar.f1987b;
            }
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        }
        f2 = fCenterX - fCenterX2;
        return f2 + iVar.f1987b;
    }

    public final float G(View view, View view2, i iVar) {
        float fCenterY;
        float fCenterY2;
        RectF rectF = this.f2502d;
        RectF rectF2 = this.f2503e;
        I(view, rectF);
        rectF.offset(this.g, this.h);
        I(view2, rectF2);
        float f2 = 0.0f;
        int i = iVar.f1986a & 112;
        if (i == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else {
            if (i != 48) {
                if (i == 80) {
                    fCenterY = rectF2.bottom;
                    fCenterY2 = rectF.bottom;
                }
                return f2 + iVar.f1988c;
            }
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        }
        f2 = fCenterY - fCenterY2;
        return f2 + iVar.f1988c;
    }

    public final float H(b bVar, h hVar, float f2, float f3) {
        long j = hVar.f1981a;
        long j2 = hVar.f1982b;
        h hVarD = bVar.f2508a.d("expansion");
        return b.d.a.a.l.a.a(f2, f3, hVar.b().getInterpolation((((hVarD.f1981a + hVarD.f1982b) + 17) - j) / j2));
    }

    public final void I(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f2504f);
        rectF.offsetTo(r3[0], r3[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public final void J(View view, boolean z, boolean z2, b bVar, List list) {
        ViewGroup viewGroupN;
        ObjectAnimator objectAnimatorOfFloat;
        if (view instanceof ViewGroup) {
            boolean z3 = view instanceof c;
            View viewFindViewById = view.findViewById(f.mtrl_child_content_container);
            if (viewFindViewById != null) {
                viewGroupN = N(viewFindViewById);
            } else {
                if ((view instanceof e) || (view instanceof b.d.a.a.k0.d)) {
                    view = ((ViewGroup) view).getChildAt(0);
                }
                viewGroupN = N(view);
            }
            if (viewGroupN == null) {
                return;
            }
            if (z) {
                if (!z2) {
                    b.d.a.a.l.c.f1972a.set(viewGroupN, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupN, b.d.a.a.l.c.f1972a, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupN, b.d.a.a.l.c.f1972a, 0.0f);
            }
            bVar.f2508a.d("contentFade").a(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void K(View view, View view2, boolean z, boolean z2, b bVar, List list) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof c) {
            c cVar = (c) view2;
            ColorStateList colorStateListI = q.i(view);
            int colorForState = colorStateListI != null ? colorStateListI.getColorForState(view.getDrawableState(), colorStateListI.getDefaultColor()) : 0;
            int i = 16777215 & colorForState;
            if (z) {
                if (!z2) {
                    cVar.setCircularRevealScrimColor(colorForState);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, c.d.f2061a, i);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(cVar, c.d.f2061a, colorForState);
            }
            objectAnimatorOfInt.setEvaluator(b.d.a.a.l.b.f1971a);
            bVar.f2508a.d("color").a(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    @TargetApi(21)
    public final void L(View view, View view2, boolean z, boolean z2, b bVar, List list) {
        ObjectAnimator objectAnimatorOfFloat;
        float fL = q.l(view2) - view.getElevation();
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-fL);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fL);
        }
        bVar.f2508a.d("elevation").a(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    public final void M(View view, View view2, boolean z, boolean z2, b bVar, List list, RectF rectF) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float F = F(view, view2, bVar.f2509b);
        float fG = G(view, view2, bVar.f2509b);
        Pair<h, h> pairE = E(F, fG, z, bVar);
        h hVar = (h) pairE.first;
        h hVar2 = (h) pairE.second;
        if (z) {
            if (!z2) {
                view2.setTranslationX(-F);
                view2.setTranslationY(-fG);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            float fH = H(bVar, hVar, -F, 0.0f);
            float fH2 = H(bVar, hVar2, -fG, 0.0f);
            Rect rect = this.f2501c;
            view2.getWindowVisibleDisplayFrame(rect);
            RectF rectF2 = this.f2502d;
            rectF2.set(rect);
            RectF rectF3 = this.f2503e;
            I(view2, rectF3);
            rectF3.offset(fH, fH2);
            rectF3.intersect(rectF2);
            rectF.set(rectF3);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -F);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -fG);
        }
        hVar.a(objectAnimatorOfFloat);
        hVar2.a(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    public final ViewGroup N(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void f(CoordinatorLayout.f fVar) {
        if (fVar.h == 0) {
            fVar.h = 80;
        }
    }
}
