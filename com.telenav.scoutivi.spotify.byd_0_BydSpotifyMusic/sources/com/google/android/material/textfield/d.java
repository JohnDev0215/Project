package com.google.android.material.textfield;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.material.textfield.TextInputLayout;
import e3.k;
import i0.u;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
class d extends com.google.android.material.textfield.e {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final boolean f4865q = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TextWatcher f4866d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final View.OnFocusChangeListener f4867e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final TextInputLayout.e f4868f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final TextInputLayout.f f4869g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @SuppressLint({"ClickableViewAccessibility"})
    private final TextInputLayout.g f4870h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f4871i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f4872j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f4873k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private StateListDrawable f4874l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private e3.g f4875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private AccessibilityManager f4876n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private ValueAnimator f4877o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ValueAnimator f4878p;

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class a extends com.google.android.material.internal.i {

        /* JADX INFO: renamed from: com.google.android.material.textfield.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        class RunnableC0054a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ AutoCompleteTextView f4880e;

            RunnableC0054a(AutoCompleteTextView autoCompleteTextView) {
                this.f4880e = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f4880e.isPopupShowing();
                d.this.E(zIsPopupShowing);
                d.this.f4871i = zIsPopupShowing;
            }
        }

        a() {
        }

        @Override // com.google.android.material.internal.i, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            AutoCompleteTextView autoCompleteTextViewY = d.y(d.this.f4894a.getEditText());
            if (d.this.f4876n.isTouchExplorationEnabled() && d.D(autoCompleteTextViewY) && !d.this.f4896c.hasFocus()) {
                autoCompleteTextViewY.dismissDropDown();
            }
            autoCompleteTextViewY.post(new RunnableC0054a(autoCompleteTextViewY));
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f4896c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class c implements View.OnFocusChangeListener {
        c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z6) {
            d.this.f4894a.setEndIconActivated(z6);
            if (z6) {
                return;
            }
            d.this.E(false);
            d.this.f4871i = false;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.textfield.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class C0055d extends TextInputLayout.e {
        C0055d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            if (!d.D(d.this.f4894a.getEditText())) {
                cVar.P(Spinner.class.getName());
            }
            if (cVar.D()) {
                cVar.Z(null);
            }
        }

        @Override // i0.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            AutoCompleteTextView autoCompleteTextViewY = d.y(d.this.f4894a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.f4876n.isTouchExplorationEnabled() && !d.D(d.this.f4894a.getEditText())) {
                d.this.H(autoCompleteTextViewY);
            }
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class e implements TextInputLayout.f {
        e() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            AutoCompleteTextView autoCompleteTextViewY = d.y(textInputLayout.getEditText());
            d.this.F(autoCompleteTextViewY);
            d.this.v(autoCompleteTextViewY);
            d.this.G(autoCompleteTextViewY);
            autoCompleteTextViewY.setThreshold(0);
            autoCompleteTextViewY.removeTextChangedListener(d.this.f4866d);
            autoCompleteTextViewY.addTextChangedListener(d.this.f4866d);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            if (!d.D(autoCompleteTextViewY)) {
                u.t0(d.this.f4896c, 2);
            }
            textInputLayout.setTextInputAccessibilityDelegate(d.this.f4868f);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class f implements TextInputLayout.g {

        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ AutoCompleteTextView f4887e;

            a(AutoCompleteTextView autoCompleteTextView) {
                this.f4887e = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4887e.removeTextChangedListener(d.this.f4866d);
            }
        }

        f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i7) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i7 != 3) {
                return;
            }
            autoCompleteTextView.post(new a(autoCompleteTextView));
            if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f4867e) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (d.f4865q) {
                autoCompleteTextView.setOnDismissListener(null);
            }
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.H((AutoCompleteTextView) d.this.f4894a.getEditText());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class h implements View.OnTouchListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AutoCompleteTextView f4890e;

        h(AutoCompleteTextView autoCompleteTextView) {
            this.f4890e = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.C()) {
                    d.this.f4871i = false;
                }
                d.this.H(this.f4890e);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class i implements AutoCompleteTextView.OnDismissListener {
        i() {
        }

        @Override // android.widget.AutoCompleteTextView.OnDismissListener
        public void onDismiss() {
            d.this.f4871i = true;
            d.this.f4873k = System.currentTimeMillis();
            d.this.E(false);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class j extends AnimatorListenerAdapter {
        j() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d dVar = d.this;
            dVar.f4896c.setChecked(dVar.f4872j);
            d.this.f4878p.start();
        }
    }

    d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f4866d = new a();
        this.f4867e = new c();
        this.f4868f = new C0055d(this.f4894a);
        this.f4869g = new e();
        this.f4870h = new f();
        this.f4871i = false;
        this.f4872j = false;
        this.f4873k = Long.MAX_VALUE;
    }

    private e3.g A(float f7, float f8, float f9, int i7) {
        k kVarM = k.a().A(f7).E(f7).s(f8).w(f8).m();
        e3.g gVarM = e3.g.m(this.f4895b, f9);
        gVarM.setShapeAppearanceModel(kVarM);
        gVarM.Z(0, i7, 0, i7);
        return gVarM;
    }

    private void B() {
        this.f4878p = z(67, 0.0f, 1.0f);
        ValueAnimator valueAnimatorZ = z(50, 1.0f, 0.0f);
        this.f4877o = valueAnimatorZ;
        valueAnimatorZ.addListener(new j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean C() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f4873k;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean D(EditText editText) {
        return editText.getKeyListener() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(boolean z6) {
        if (this.f4872j != z6) {
            this.f4872j = z6;
            this.f4878p.cancel();
            this.f4877o.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(AutoCompleteTextView autoCompleteTextView) {
        if (f4865q) {
            int boxBackgroundMode = this.f4894a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.f4875m);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.f4874l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"ClickableViewAccessibility"})
    public void G(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new h(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f4867e);
        if (f4865q) {
            autoCompleteTextView.setOnDismissListener(new i());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            return;
        }
        if (C()) {
            this.f4871i = false;
        }
        if (this.f4871i) {
            this.f4871i = false;
            return;
        }
        if (f4865q) {
            E(!this.f4872j);
        } else {
            this.f4872j = !this.f4872j;
            this.f4896c.toggle();
        }
        if (!this.f4872j) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(AutoCompleteTextView autoCompleteTextView) {
        if (D(autoCompleteTextView)) {
            return;
        }
        int boxBackgroundMode = this.f4894a.getBoxBackgroundMode();
        e3.g boxBackground = this.f4894a.getBoxBackground();
        int iC = u2.a.c(autoCompleteTextView, o2.b.colorControlHighlight);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            x(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    private void w(AutoCompleteTextView autoCompleteTextView, int i7, int[][] iArr, e3.g gVar) {
        int boxBackgroundColor = this.f4894a.getBoxBackgroundColor();
        int[] iArr2 = {u2.a.f(i7, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (f4865q) {
            u.n0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), gVar, gVar));
            return;
        }
        e3.g gVar2 = new e3.g(gVar.C());
        gVar2.X(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gVar, gVar2});
        int iE = u.E(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iD = u.D(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        u.n0(autoCompleteTextView, layerDrawable);
        u.w0(autoCompleteTextView, iE, paddingTop, iD, paddingBottom);
    }

    private void x(AutoCompleteTextView autoCompleteTextView, int i7, int[][] iArr, e3.g gVar) {
        LayerDrawable layerDrawable;
        int iC = u2.a.c(autoCompleteTextView, o2.b.colorSurface);
        e3.g gVar2 = new e3.g(gVar.C());
        int iF = u2.a.f(i7, iC, 0.1f);
        gVar2.X(new ColorStateList(iArr, new int[]{iF, 0}));
        if (f4865q) {
            gVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            e3.g gVar3 = new e3.g(gVar.C());
            gVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, gVar2, gVar3), gVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{gVar2, gVar});
        }
        u.n0(autoCompleteTextView, layerDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AutoCompleteTextView y(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private ValueAnimator z(int i7, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(p2.a.f8820a);
        valueAnimatorOfFloat.setDuration(i7);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    @Override // com.google.android.material.textfield.e
    void a() {
        float dimensionPixelOffset = this.f4895b.getResources().getDimensionPixelOffset(o2.d.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.f4895b.getResources().getDimensionPixelOffset(o2.d.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f4895b.getResources().getDimensionPixelOffset(o2.d.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        e3.g gVarA = A(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        e3.g gVarA2 = A(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.f4875m = gVarA;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.f4874l = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, gVarA);
        this.f4874l.addState(new int[0], gVarA2);
        this.f4894a.setEndIconDrawable(f.a.b(this.f4895b, f4865q ? o2.e.mtrl_dropdown_arrow : o2.e.mtrl_ic_arrow_drop_down));
        TextInputLayout textInputLayout = this.f4894a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(o2.j.exposed_dropdown_menu_content_description));
        this.f4894a.setEndIconOnClickListener(new g());
        this.f4894a.e(this.f4869g);
        this.f4894a.f(this.f4870h);
        B();
        this.f4876n = (AccessibilityManager) this.f4895b.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.e
    boolean b(int i7) {
        return i7 != 0;
    }

    @Override // com.google.android.material.textfield.e
    boolean d() {
        return true;
    }
}
