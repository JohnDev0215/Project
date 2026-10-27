package com.google.android.material.textfield;

import a.g.k.t;
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
import b.a.a.a.b0.k;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
class d extends com.google.android.material.textfield.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final boolean f3227d;
    private final TextWatcher e;
    private final View.OnFocusChangeListener f;
    private final TextInputLayout.e g;
    private final TextInputLayout.f h;

    @SuppressLint({"ClickableViewAccessibility"})
    private final TextInputLayout.g i;
    private boolean j;
    private boolean k;
    private long l;
    private StateListDrawable m;
    private b.a.a.a.b0.g n;
    private AccessibilityManager o;
    private ValueAnimator p;
    private ValueAnimator q;

    class a implements TextWatcher {

        /* JADX INFO: renamed from: com.google.android.material.textfield.d$a$a, reason: collision with other inner class name */
        class RunnableC0115a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AutoCompleteTextView f3229b;

            RunnableC0115a(AutoCompleteTextView autoCompleteTextView) {
                this.f3229b = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f3229b.isPopupShowing();
                d.this.C(zIsPopupShowing);
                d.this.j = zIsPopupShowing;
            }
        }

        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f3240a.getEditText());
            autoCompleteTextViewX.post(new RunnableC0115a(autoCompleteTextViewX));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f3242c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    class c implements View.OnFocusChangeListener {
        c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            d.this.f3240a.setEndIconActivated(z);
            if (z) {
                return;
            }
            d.this.C(false);
            d.this.j = false;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.textfield.d$d, reason: collision with other inner class name */
    class C0116d extends TextInputLayout.e {
        C0116d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, a.g.k.a
        public void g(View view, a.g.k.c0.c cVar) {
            super.g(view, cVar);
            if (d.this.f3240a.getEditText().getKeyListener() == null) {
                cVar.X(Spinner.class.getName());
            }
            if (cVar.J()) {
                cVar.h0(null);
            }
        }

        @Override // a.g.k.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f3240a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.o.isTouchExplorationEnabled()) {
                d.this.F(autoCompleteTextViewX);
            }
        }
    }

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
            autoCompleteTextViewX.removeTextChangedListener(d.this.e);
            autoCompleteTextViewX.addTextChangedListener(d.this.e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(d.this.g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    class f implements TextInputLayout.g {
        f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i != 3) {
                return;
            }
            autoCompleteTextView.removeTextChangedListener(d.this.e);
            if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (d.f3227d) {
                autoCompleteTextView.setOnDismissListener(null);
            }
        }
    }

    class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.F((AutoCompleteTextView) d.this.f3240a.getEditText());
        }
    }

    class h implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AutoCompleteTextView f3236b;

        h(AutoCompleteTextView autoCompleteTextView) {
            this.f3236b = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.B()) {
                    d.this.j = false;
                }
                d.this.F(this.f3236b);
            }
            return false;
        }
    }

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

    class j extends AnimatorListenerAdapter {
        j() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d dVar = d.this;
            dVar.f3242c.setChecked(dVar.k);
            d.this.q.start();
        }
    }

    static {
        f3227d = Build.VERSION.SDK_INT >= 21;
    }

    d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.e = new a();
        this.f = new c();
        this.g = new C0116d(this.f3240a);
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
        Drawable drawable;
        if (f3227d) {
            int boxBackgroundMode = this.f3240a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                drawable = this.n;
            } else if (boxBackgroundMode != 1) {
                return;
            } else {
                drawable = this.m;
            }
            autoCompleteTextView.setDropDownBackgroundDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"ClickableViewAccessibility"})
    public void E(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new h(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f);
        if (f3227d) {
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
        if (f3227d) {
            C(!this.k);
        } else {
            this.k = !this.k;
            this.f3242c.toggle();
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
        int boxBackgroundMode = this.f3240a.getBoxBackgroundMode();
        b.a.a.a.b0.g boxBackground = this.f3240a.getBoxBackground();
        int iC = b.a.a.a.s.a.c(autoCompleteTextView, b.a.a.a.b.h);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            v(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    private void v(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, b.a.a.a.b0.g gVar) {
        int boxBackgroundColor = this.f3240a.getBoxBackgroundColor();
        int[] iArr2 = {b.a.a.a.s.a.f(i2, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (f3227d) {
            t.k0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), gVar, gVar));
            return;
        }
        b.a.a.a.b0.g gVar2 = new b.a.a.a.b0.g(gVar.C());
        gVar2.W(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gVar, gVar2});
        int iD = t.D(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iC = t.C(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        t.k0(autoCompleteTextView, layerDrawable);
        t.t0(autoCompleteTextView, iD, paddingTop, iC, paddingBottom);
    }

    private void w(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, b.a.a.a.b0.g gVar) {
        LayerDrawable layerDrawable;
        int iC = b.a.a.a.s.a.c(autoCompleteTextView, b.a.a.a.b.l);
        b.a.a.a.b0.g gVar2 = new b.a.a.a.b0.g(gVar.C());
        int iF = b.a.a.a.s.a.f(i2, iC, 0.1f);
        gVar2.W(new ColorStateList(iArr, new int[]{iF, 0}));
        if (f3227d) {
            gVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            b.a.a.a.b0.g gVar3 = new b.a.a.a.b0.g(gVar.C());
            gVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, gVar2, gVar3), gVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{gVar2, gVar});
        }
        t.k0(autoCompleteTextView, layerDrawable);
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
        valueAnimatorOfFloat.setInterpolator(b.a.a.a.m.a.f1732a);
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    private b.a.a.a.b0.g z(float f2, float f3, float f4, int i2) {
        k kVarM = k.a().z(f2).D(f2).r(f3).v(f3).m();
        b.a.a.a.b0.g gVarM = b.a.a.a.b0.g.m(this.f3241b, f4);
        gVarM.setShapeAppearanceModel(kVarM);
        gVarM.Y(0, i2, 0, i2);
        return gVarM;
    }

    @Override // com.google.android.material.textfield.e
    void a() {
        float dimensionPixelOffset = this.f3241b.getResources().getDimensionPixelOffset(b.a.a.a.d.v);
        float dimensionPixelOffset2 = this.f3241b.getResources().getDimensionPixelOffset(b.a.a.a.d.s);
        int dimensionPixelOffset3 = this.f3241b.getResources().getDimensionPixelOffset(b.a.a.a.d.t);
        b.a.a.a.b0.g gVarZ = z(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        b.a.a.a.b0.g gVarZ2 = z(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.n = gVarZ;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.m = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, gVarZ);
        this.m.addState(new int[0], gVarZ2);
        this.f3240a.setEndIconDrawable(a.a.k.a.a.d(this.f3241b, f3227d ? b.a.a.a.e.f1706b : b.a.a.a.e.f1707c));
        TextInputLayout textInputLayout = this.f3240a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(b.a.a.a.j.f));
        this.f3240a.setEndIconOnClickListener(new g());
        this.f3240a.e(this.h);
        this.f3240a.f(this.i);
        A();
        t.q0(this.f3242c, 2);
        this.o = (AccessibilityManager) this.f3241b.getSystemService("accessibility");
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
