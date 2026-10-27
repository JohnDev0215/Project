package a5;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.LinearInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stingray.drivemodedetector.R;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class o extends p {
    public final k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f11160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f11161f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f11162g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f11163h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f11164i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f11165j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f11166k;
    public StateListDrawable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Y4.g f11167m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AccessibilityManager f11168n;
    public ValueAnimator o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ValueAnimator f11169p;

    public o(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.d = new k(this, 0);
        this.f11160e = new b(this, 1);
        this.f11161f = new l(this, textInputLayout);
        this.f11162g = new c(this, 1);
        this.f11163h = new d(this, 1);
        this.f11164i = false;
        this.f11165j = false;
        this.f11166k = Long.MAX_VALUE;
    }

    public static void d(o oVar, AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            oVar.getClass();
            return;
        }
        oVar.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() - oVar.f11166k;
        if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
            oVar.f11164i = false;
        }
        if (oVar.f11164i) {
            oVar.f11164i = false;
            return;
        }
        oVar.g(!oVar.f11165j);
        if (!oVar.f11165j) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    public static boolean f(EditText editText) {
        return editText.getKeyListener() != null;
    }

    @Override // a5.p
    public final void a() {
        int i7 = 1;
        Context context = this.f11171b;
        float dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = context.getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        Y4.g gVarE = e(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        Y4.g gVarE2 = e(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.f11167m = gVarE;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.l = stateListDrawable;
        stateListDrawable.addState(new int[]{android.R.attr.state_above_anchor}, gVarE);
        this.l.addState(new int[0], gVarE2);
        Drawable drawableK = h6.f.k(context, R.drawable.mtrl_dropdown_arrow);
        TextInputLayout textInputLayout = this.f11170a;
        textInputLayout.setEndIconDrawable(drawableK);
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.exposed_dropdown_menu_content_description));
        textInputLayout.setEndIconOnClickListener(new e(this, i7));
        LinkedHashSet linkedHashSet = textInputLayout.f12926p0;
        c cVar = this.f11162g;
        linkedHashSet.add(cVar);
        if (textInputLayout.f12925p != null) {
            cVar.a(textInputLayout);
        }
        textInputLayout.f12934t0.add(this.f11163h);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        LinearInterpolator linearInterpolator = L4.a.f4007a;
        valueAnimatorOfFloat.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat.setDuration(67);
        valueAnimatorOfFloat.addUpdateListener(new P4.b(this, i7));
        this.f11169p = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat2.setDuration(50);
        valueAnimatorOfFloat2.addUpdateListener(new P4.b(this, i7));
        this.o = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.addListener(new N4.a(this, 1));
        this.f11168n = (AccessibilityManager) context.getSystemService("accessibility");
    }

    @Override // a5.p
    public final boolean b(int i7) {
        return i7 != 0;
    }

    public final Y4.g e(float f10, float f11, float f12, int i7) {
        int i10 = 0;
        Y4.i iVar = new Y4.i();
        Y4.i iVar2 = new Y4.i();
        Y4.i iVar3 = new Y4.i();
        Y4.i iVar4 = new Y4.i();
        Y4.e eVar = new Y4.e(i10);
        Y4.e eVar2 = new Y4.e(i10);
        Y4.e eVar3 = new Y4.e(i10);
        Y4.e eVar4 = new Y4.e(i10);
        Y4.a aVar = new Y4.a(f10);
        Y4.a aVar2 = new Y4.a(f10);
        Y4.a aVar3 = new Y4.a(f11);
        Y4.a aVar4 = new Y4.a(f11);
        Y4.k kVar = new Y4.k();
        kVar.f10748a = iVar;
        kVar.f10749b = iVar2;
        kVar.c = iVar3;
        kVar.d = iVar4;
        kVar.f10750e = aVar;
        kVar.f10751f = aVar2;
        kVar.f10752g = aVar4;
        kVar.f10753h = aVar3;
        kVar.f10754i = eVar;
        kVar.f10755j = eVar2;
        kVar.f10756k = eVar3;
        kVar.l = eVar4;
        Paint paint = Y4.g.f10720F;
        String simpleName = Y4.g.class.getSimpleName();
        Context context = this.f11171b;
        int iU = P8.n.U(R.attr.colorSurface, context, simpleName);
        Y4.g gVar = new Y4.g();
        gVar.f(context);
        gVar.h(ColorStateList.valueOf(iU));
        gVar.g(f12);
        gVar.setShapeAppearanceModel(kVar);
        Y4.f fVar = gVar.l;
        if (fVar.f10712g == null) {
            fVar.f10712g = new Rect();
        }
        gVar.l.f10712g.set(0, i7, 0, i7);
        gVar.invalidateSelf();
        return gVar;
    }

    public final void g(boolean z6) {
        if (this.f11165j != z6) {
            this.f11165j = z6;
            this.f11169p.cancel();
            this.o.start();
        }
    }
}
