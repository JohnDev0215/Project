package p;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.LinearInterpolator;
import android.widget.AutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.spotify.music.R;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class pb0 extends be0 {
    public final lb0 e;
    public final ra2 f;
    public final mb0 g;
    public final at h;
    public final bt i;
    public boolean j;
    public boolean k;
    public long l;
    public StateListDrawable m;
    public qf1 n;
    public AccessibilityManager o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ValueAnimator f166p;
    public ValueAnimator q;

    public pb0(TextInputLayout textInputLayout, int i) {
        super(textInputLayout, i);
        this.e = new lb0(this, 0);
        this.f = new ra2(2, this);
        this.g = new mb0(this, textInputLayout);
        this.h = new at(this, 1);
        this.i = new bt(this, 1);
        this.j = false;
        this.k = false;
        this.l = Long.MAX_VALUE;
    }

    public static void d(pb0 pb0Var, AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            pb0Var.getClass();
            return;
        }
        pb0Var.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() - pb0Var.l;
        if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
            pb0Var.j = false;
        }
        if (pb0Var.j) {
            pb0Var.j = false;
            return;
        }
        pb0Var.f(!pb0Var.k);
        if (!pb0Var.k) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    @Override // p.be0
    public final void a() {
        Context context = this.b;
        float dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        qf1 qf1VarE = e(dimensionPixelOffset3, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2);
        qf1 qf1VarE2 = e(dimensionPixelOffset3, 0.0f, dimensionPixelOffset, dimensionPixelOffset2);
        this.n = qf1VarE;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.m = stateListDrawable;
        stateListDrawable.addState(new int[]{android.R.attr.state_above_anchor}, qf1VarE);
        this.m.addState(new int[0], qf1VarE2);
        int i = this.d;
        if (i == 0) {
            i = R.drawable.mtrl_dropdown_arrow;
        }
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setEndIconDrawable(i);
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.exposed_dropdown_menu_content_description));
        textInputLayout.setEndIconOnClickListener(new e6(4, this));
        LinkedHashSet linkedHashSet = textInputLayout.n0;
        at atVar = this.h;
        linkedHashSet.add(atVar);
        if (textInputLayout.m != null) {
            atVar.a(textInputLayout);
        }
        textInputLayout.r0.add(this.i);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        LinearInterpolator linearInterpolator = e7.a;
        valueAnimatorOfFloat.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat.setDuration(67);
        int i2 = 1;
        valueAnimatorOfFloat.addUpdateListener(new jn(i2, this));
        this.q = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat2.setDuration(50);
        valueAnimatorOfFloat2.addUpdateListener(new jn(i2, this));
        this.f166p = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.addListener(new u3(5, this));
        this.o = (AccessibilityManager) context.getSystemService("accessibility");
    }

    @Override // p.be0
    public final boolean b(int i) {
        return i != 0;
    }

    public final qf1 e(int i, float f, float f2, float f3) {
        q52 q52Var = new q52();
        q52 q52Var2 = new q52();
        q52 q52Var3 = new q52();
        q52 q52Var4 = new q52();
        zb0 zb0Var = new zb0();
        zb0 zb0Var2 = new zb0();
        zb0 zb0Var3 = new zb0();
        zb0 zb0Var4 = new zb0();
        y0 y0Var = new y0(f);
        y0 y0Var2 = new y0(f);
        y0 y0Var3 = new y0(f2);
        y0 y0Var4 = new y0(f2);
        oc2 oc2Var = new oc2();
        oc2Var.a = q52Var;
        oc2Var.b = q52Var2;
        oc2Var.c = q52Var3;
        oc2Var.d = q52Var4;
        oc2Var.e = y0Var;
        oc2Var.f = y0Var2;
        oc2Var.g = y0Var4;
        oc2Var.h = y0Var3;
        oc2Var.i = zb0Var;
        oc2Var.j = zb0Var2;
        oc2Var.k = zb0Var3;
        oc2Var.l = zb0Var4;
        Paint paint = qf1.E;
        String simpleName = qf1.class.getSimpleName();
        Context context = this.b;
        int iO = he1.O(R.attr.colorSurface, context, simpleName);
        qf1 qf1Var = new qf1();
        qf1Var.i(context);
        qf1Var.k(ColorStateList.valueOf(iO));
        qf1Var.j(f3);
        qf1Var.setShapeAppearanceModel(oc2Var);
        pf1 pf1Var = qf1Var.c;
        if (pf1Var.h == null) {
            pf1Var.h = new Rect();
        }
        qf1Var.c.h.set(0, i, 0, i);
        qf1Var.invalidateSelf();
        return qf1Var;
    }

    public final void f(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.q.cancel();
            this.f166p.start();
        }
    }
}
