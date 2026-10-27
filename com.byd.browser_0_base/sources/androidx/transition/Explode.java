package androidx.transition;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.transition.Transition;
import defpackage.df3;
import defpackage.kt;

/* JADX INFO: loaded from: classes.dex */
public class Explode extends Visibility {
    public static final DecelerateInterpolator F = new DecelerateInterpolator();
    public static final AccelerateInterpolator G = new AccelerateInterpolator();
    public final int[] E;

    public Explode() {
        this.E = new int[2];
        this.w = new kt();
    }

    private void Q(df3 df3Var) {
        View view = df3Var.b;
        int[] iArr = this.E;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        df3Var.a.put("android:explode:screenBounds", new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    @Override // androidx.transition.Visibility
    public final Animator T(ViewGroup viewGroup, View view, df3 df3Var, df3 df3Var2) {
        if (df3Var2 == null) {
            return null;
        }
        Rect rect = (Rect) df3Var2.a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        V(viewGroup, rect, this.E);
        return i.a(view, df3Var2, rect.left, rect.top, translationX + r0[0], translationY + r0[1], translationX, translationY, F, this);
    }

    @Override // androidx.transition.Visibility
    public final Animator U(ViewGroup viewGroup, View view, df3 df3Var) {
        float f;
        float f2;
        if (df3Var == null) {
            return null;
        }
        Rect rect = (Rect) df3Var.a.get("android:explode:screenBounds");
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) df3Var.b.getTag(R$id.transition_position);
        if (iArr != null) {
            f = (r8 - rect.left) + translationX;
            f2 = (r1 - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f = translationX;
            f2 = translationY;
        }
        V(viewGroup, rect, this.E);
        return i.a(view, df3Var, i, i2, translationX, translationY, f + r1[0], f2 + r1[1], G, this);
    }

    public final void V(View view, Rect rect, int[] iArr) {
        int iCenterX;
        int iCenterY;
        int[] iArr2 = this.E;
        view.getLocationOnScreen(iArr2);
        int i = iArr2[0];
        int i2 = iArr2[1];
        Transition.c cVar = this.x;
        Rect rectA = cVar == null ? null : cVar.a();
        if (rectA == null) {
            iCenterX = Math.round(view.getTranslationX()) + (view.getWidth() / 2) + i;
            iCenterY = Math.round(view.getTranslationY()) + (view.getHeight() / 2) + i2;
        } else {
            iCenterX = rectA.centerX();
            iCenterY = rectA.centerY();
        }
        float fCenterX = rect.centerX() - iCenterX;
        float fCenterY = rect.centerY() - iCenterY;
        if (fCenterX == 0.0f && fCenterY == 0.0f) {
            fCenterX = ((float) (Math.random() * 2.0d)) - 1.0f;
            fCenterY = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float fSqrt = (float) Math.sqrt((fCenterY * fCenterY) + (fCenterX * fCenterX));
        int i3 = iCenterX - i;
        int i4 = iCenterY - i2;
        float fMax = Math.max(i3, view.getWidth() - i3);
        float fMax2 = Math.max(i4, view.getHeight() - i4);
        float fSqrt2 = (float) Math.sqrt((fMax2 * fMax2) + (fMax * fMax));
        iArr[0] = Math.round((fCenterX / fSqrt) * fSqrt2);
        iArr[1] = Math.round(fSqrt2 * (fCenterY / fSqrt));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void i(df3 df3Var) {
        Q(df3Var);
        Q(df3Var);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
        Q(df3Var);
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new int[2];
        this.w = new kt();
    }
}
