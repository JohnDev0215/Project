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
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import b.g.k.t;
import c.b.a.a.w.m;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
class d extends com.google.android.material.textfield.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final boolean f4056d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final TextWatcher f4057e;
    private final View.OnFocusChangeListener f;
    private final TextInputLayout.e g;
    private final TextInputLayout.f h;

    @SuppressLint({"ClickableViewAccessibility"})
    private final TextInputLayout.g i;
    private boolean j;
    private boolean k;
    private long l;
    private StateListDrawable m;
    private c.b.a.a.w.h n;
    private AccessibilityManager o;
    private ValueAnimator p;
    private ValueAnimator q;

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class a implements TextWatcher {

        /* JADX INFO: renamed from: com.google.android.material.textfield.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        class RunnableC0103a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AutoCompleteTextView f4059b;

            RunnableC0103a(AutoCompleteTextView autoCompleteTextView) {
                this.f4059b = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f4059b.isPopupShowing();
                d.this.C(zIsPopupShowing);
                d.this.j = zIsPopupShowing;
            }
        }

        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f4071a.getEditText());
            autoCompleteTextViewX.post(new RunnableC0103a(autoCompleteTextViewX));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f4073c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class c implements View.OnFocusChangeListener {
        c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            d.this.f4071a.setEndIconActivated(z);
            if (z) {
                return;
            }
            d.this.C(false);
            d.this.j = false;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.textfield.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class C0104d extends TextInputLayout.e {
        C0104d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            if (d.this.f4071a.getEditText().getKeyListener() == null) {
                cVar.c0(Spinner.class.getName());
            }
            if (cVar.M()) {
                cVar.m0(null);
            }
        }

        @Override // b.g.k.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f4071a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.o.isTouchExplorationEnabled()) {
                d.this.F(autoCompleteTextViewX);
            }
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class e implements TextInputLayout.f {
        e() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            AutoCompleteTextView autoCompleteTextViewX = d.this.x(textInputLayout.getEditText());
            d.this.D(autoCompleteTextViewX);
            d.this.u(autoCompleteTextViewX);
            d.this.E(autoCompleteTextViewX);
            autoCompleteTextViewX.setThreshold(0);
            autoCompleteTextViewX.removeTextChangedListener(d.this.f4057e);
            autoCompleteTextViewX.addTextChangedListener(d.this.f4057e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(d.this.g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class f implements TextInputLayout.g {
        f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i != 3) {
                return;
            }
            autoCompleteTextView.removeTextChangedListener(d.this.f4057e);
            if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (d.f4056d) {
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
            d.this.F((AutoCompleteTextView) d.this.f4071a.getEditText());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class h implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AutoCompleteTextView f4067b;

        h(AutoCompleteTextView autoCompleteTextView) {
            this.f4067b = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.B()) {
                    d.this.j = false;
                }
                d.this.F(this.f4067b);
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
            d.this.j = true;
            d.this.l = System.currentTimeMillis();
            d.this.C(false);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    class j extends AnimatorListenerAdapter {
        j() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d dVar = d.this;
            dVar.f4073c.setChecked(dVar.k);
            d.this.q.start();
        }
    }

    static {
        f4056d = Build.VERSION.SDK_INT >= 21;
    }

    d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f4057e = new a();
        this.f = new c();
        this.g = new C0104d(this.f4071a);
        this.h = new e();
        this.i = new f();
        this.j = false;
        this.k = false;
        this.l = Long.MAX_VALUE;
    }

    private void A() {
        this.q = y(67, 0.0f, 1.0f);
        ValueAnimator valueAnimatorY = y(50, 1.0f, 0.0f);
        this.p = valueAnimatorY;
        valueAnimatorY.addListener(new j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean B() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.l;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.q.cancel();
            this.p.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(AutoCompleteTextView autoCompleteTextView) {
        if (f4056d) {
            int boxBackgroundMode = this.f4071a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.n);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.m);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"ClickableViewAccessibility"})
    public void E(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new h(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f);
        if (f4056d) {
            autoCompleteTextView.setOnDismissListener(new i());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            return;
        }
        if (B()) {
            this.j = false;
        }
        if (this.j) {
            this.j = false;
            return;
        }
        if (f4056d) {
            C(!this.k);
        } else {
            this.k = !this.k;
            this.f4073c.toggle();
        }
        if (!this.k) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() != null) {
            return;
        }
        int boxBackgroundMode = this.f4071a.getBoxBackgroundMode();
        c.b.a.a.w.h boxBackground = this.f4071a.getBoxBackground();
        int iC = c.b.a.a.o.a.c(autoCompleteTextView, c.b.a.a.b.k);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            v(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    private void v(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, c.b.a.a.w.h hVar) {
        int boxBackgroundColor = this.f4071a.getBoxBackgroundColor();
        int[] iArr2 = {c.b.a.a.o.a.f(i2, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (f4056d) {
            t.n0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), hVar, hVar));
            return;
        }
        c.b.a.a.w.h hVar2 = new c.b.a.a.w.h(hVar.D());
        hVar2.X(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar, hVar2});
        int iE = t.E(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iD = t.D(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        t.n0(autoCompleteTextView, layerDrawable);
        t.y0(autoCompleteTextView, iE, paddingTop, iD, paddingBottom);
    }

    private void w(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, c.b.a.a.w.h hVar) {
        LayerDrawable layerDrawable;
        int iC = c.b.a.a.o.a.c(autoCompleteTextView, c.b.a.a.b.p);
        c.b.a.a.w.h hVar2 = new c.b.a.a.w.h(hVar.D());
        int iF = c.b.a.a.o.a.f(i2, iC, 0.1f);
        hVar2.X(new ColorStateList(iArr, new int[]{iF, 0}));
        if (f4056d) {
            hVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            c.b.a.a.w.h hVar3 = new c.b.a.a.w.h(hVar.D());
            hVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, hVar2, hVar3), hVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar});
        }
        t.n0(autoCompleteTextView, layerDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutoCompleteTextView x(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private ValueAnimator y(int i2, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(c.b.a.a.m.a.f2691a);
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    private c.b.a.a.w.h z(float f2, float f3, float f4, int i2) {
        m mVarM = m.a().D(f2).H(f2).u(f3).y(f3).m();
        c.b.a.a.w.h hVarM = c.b.a.a.w.h.m(this.f4072b, f4);
        hVarM.setShapeAppearanceModel(mVarM);
        hVarM.Z(0, i2, 0, i2);
        return hVarM;
    }

    @Override // com.google.android.material.textfield.e
    void a() {
        float dimensionPixelOffset = this.f4072b.getResources().getDimensionPixelOffset(c.b.a.a.d.I);
        float dimensionPixelOffset2 = this.f4072b.getResources().getDimensionPixelOffset(c.b.a.a.d.E);
        int dimensionPixelOffset3 = this.f4072b.getResources().getDimensionPixelOffset(c.b.a.a.d.F);
        c.b.a.a.w.h hVarZ = z(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        c.b.a.a.w.h hVarZ2 = z(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.n = hVarZ;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.m = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, hVarZ);
        this.m.addState(new int[0], hVarZ2);
        this.f4071a.setEndIconDrawable(b.a.k.a.a.d(this.f4072b, f4056d ? c.b.a.a.e.f2660c : c.b.a.a.e.f2661d));
        TextInputLayout textInputLayout = this.f4071a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(c.b.a.a.j.f));
        this.f4071a.setEndIconOnClickListener(new g());
        this.f4071a.e(this.h);
        this.f4071a.f(this.i);
        A();
        t.u0(this.f4073c, 2);
        this.o = (AccessibilityManager) this.f4072b.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.e
    boolean b(int i2) {
        return i2 != 0;
    }

    @Override // com.google.android.material.textfield.e
    boolean d() {
        return true;
    }
}
