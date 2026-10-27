package c.b.a.a.l0;

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
import b.h.m.t;
import c.b.a.a.i0.m;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c.b.a.a.l0.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f3965d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextWatcher f3966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View.OnFocusChangeListener f3967f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TextInputLayout.e f3968g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final TextInputLayout.f f3969h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @SuppressLint({"ClickableViewAccessibility"})
    public final TextInputLayout.g f3970i;
    public boolean j;
    public boolean k;
    public long l;
    public StateListDrawable m;
    public c.b.a.a.i0.h n;
    public AccessibilityManager o;
    public ValueAnimator p;
    public ValueAnimator q;

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class a implements TextWatcher {

        /* JADX INFO: renamed from: c.b.a.a.l0.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        public class RunnableC0086a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AutoCompleteTextView f3972b;

            public RunnableC0086a(AutoCompleteTextView autoCompleteTextView) {
                this.f3972b = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f3972b.isPopupShowing();
                d.this.C(zIsPopupShowing);
                d.this.j = zIsPopupShowing;
            }
        }

        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f3984a.getEditText());
            autoCompleteTextViewX.post(new RunnableC0086a(autoCompleteTextViewX));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f3986c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class c implements View.OnFocusChangeListener {
        public c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            d.this.f3984a.setEndIconActivated(z);
            if (z) {
                return;
            }
            d.this.C(false);
            d.this.j = false;
        }
    }

    /* JADX INFO: renamed from: c.b.a.a.l0.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class C0087d extends TextInputLayout.e {
        public C0087d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            super.g(view, cVar);
            if (d.this.f3984a.getEditText().getKeyListener() == null) {
                cVar.c0(Spinner.class.getName());
            }
            if (cVar.M()) {
                cVar.m0(null);
            }
        }

        @Override // b.h.m.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f3984a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.o.isTouchExplorationEnabled()) {
                d.this.F(autoCompleteTextViewX);
            }
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class e implements TextInputLayout.f {
        public e() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            AutoCompleteTextView autoCompleteTextViewX = d.this.x(textInputLayout.getEditText());
            d.this.D(autoCompleteTextViewX);
            d.this.u(autoCompleteTextViewX);
            d.this.E(autoCompleteTextViewX);
            autoCompleteTextViewX.setThreshold(0);
            autoCompleteTextViewX.removeTextChangedListener(d.this.f3966e);
            autoCompleteTextViewX.addTextChangedListener(d.this.f3966e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(d.this.f3968g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class f implements TextInputLayout.g {
        public f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i2) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i2 != 3) {
                return;
            }
            autoCompleteTextView.removeTextChangedListener(d.this.f3966e);
            if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f3967f) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (d.f3965d) {
                autoCompleteTextView.setOnDismissListener(null);
            }
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.F((AutoCompleteTextView) d.this.f3984a.getEditText());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class h implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AutoCompleteTextView f3980b;

        public h(AutoCompleteTextView autoCompleteTextView) {
            this.f3980b = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.B()) {
                    d.this.j = false;
                }
                d.this.F(this.f3980b);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class i implements AutoCompleteTextView.OnDismissListener {
        public i() {
        }

        @Override // android.widget.AutoCompleteTextView.OnDismissListener
        public void onDismiss() {
            d.this.j = true;
            d.this.l = System.currentTimeMillis();
            d.this.C(false);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class j extends AnimatorListenerAdapter {
        public j() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d dVar = d.this;
            dVar.f3986c.setChecked(dVar.k);
            d.this.q.start();
        }
    }

    static {
        f3965d = Build.VERSION.SDK_INT >= 21;
    }

    public d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f3966e = new a();
        this.f3967f = new c();
        this.f3968g = new C0087d(this.f3984a);
        this.f3969h = new e();
        this.f3970i = new f();
        this.j = false;
        this.k = false;
        this.l = Long.MAX_VALUE;
    }

    public final void A() {
        this.q = y(67, 0.0f, 1.0f);
        ValueAnimator valueAnimatorY = y(50, 1.0f, 0.0f);
        this.p = valueAnimatorY;
        valueAnimatorY.addListener(new j());
    }

    public final boolean B() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.l;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }

    public final void C(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.q.cancel();
            this.p.start();
        }
    }

    public final void D(AutoCompleteTextView autoCompleteTextView) {
        if (f3965d) {
            int boxBackgroundMode = this.f3984a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.n);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.m);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void E(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new h(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f3967f);
        if (f3965d) {
            autoCompleteTextView.setOnDismissListener(new i());
        }
    }

    public final void F(AutoCompleteTextView autoCompleteTextView) {
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
        if (f3965d) {
            C(!this.k);
        } else {
            this.k = !this.k;
            this.f3986c.toggle();
        }
        if (!this.k) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    @Override // c.b.a.a.l0.e
    public void a() {
        float dimensionPixelOffset = this.f3985b.getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.f3985b.getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f3985b.getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        c.b.a.a.i0.h hVarZ = z(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        c.b.a.a.i0.h hVarZ2 = z(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.n = hVarZ;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.m = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, hVarZ);
        this.m.addState(new int[0], hVarZ2);
        this.f3984a.setEndIconDrawable(b.b.l.a.a.d(this.f3985b, f3965d ? c.b.a.a.e.mtrl_dropdown_arrow : c.b.a.a.e.mtrl_ic_arrow_drop_down));
        TextInputLayout textInputLayout = this.f3984a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(c.b.a.a.j.exposed_dropdown_menu_content_description));
        this.f3984a.setEndIconOnClickListener(new g());
        this.f3984a.e(this.f3969h);
        this.f3984a.f(this.f3970i);
        A();
        t.u0(this.f3986c, 2);
        this.o = (AccessibilityManager) this.f3985b.getSystemService("accessibility");
    }

    @Override // c.b.a.a.l0.e
    public boolean b(int i2) {
        return i2 != 0;
    }

    @Override // c.b.a.a.l0.e
    public boolean d() {
        return true;
    }

    public final void u(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() != null) {
            return;
        }
        int boxBackgroundMode = this.f3984a.getBoxBackgroundMode();
        c.b.a.a.i0.h boxBackground = this.f3984a.getBoxBackground();
        int iC = c.b.a.a.w.a.c(autoCompleteTextView, c.b.a.a.b.colorControlHighlight);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            v(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    public final void v(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, c.b.a.a.i0.h hVar) {
        int boxBackgroundColor = this.f3984a.getBoxBackgroundColor();
        int[] iArr2 = {c.b.a.a.w.a.f(i2, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (f3965d) {
            t.n0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), hVar, hVar));
            return;
        }
        c.b.a.a.i0.h hVar2 = new c.b.a.a.i0.h(hVar.D());
        hVar2.X(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar, hVar2});
        int iE = t.E(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iD = t.D(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        t.n0(autoCompleteTextView, layerDrawable);
        t.y0(autoCompleteTextView, iE, paddingTop, iD, paddingBottom);
    }

    public final void w(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, c.b.a.a.i0.h hVar) {
        LayerDrawable layerDrawable;
        int iC = c.b.a.a.w.a.c(autoCompleteTextView, c.b.a.a.b.colorSurface);
        c.b.a.a.i0.h hVar2 = new c.b.a.a.i0.h(hVar.D());
        int iF = c.b.a.a.w.a.f(i2, iC, 0.1f);
        hVar2.X(new ColorStateList(iArr, new int[]{iF, 0}));
        if (f3965d) {
            hVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            c.b.a.a.i0.h hVar3 = new c.b.a.a.i0.h(hVar.D());
            hVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, hVar2, hVar3), hVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar});
        }
        t.n0(autoCompleteTextView, layerDrawable);
    }

    public final AutoCompleteTextView x(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public final ValueAnimator y(int i2, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(c.b.a.a.m.a.f4008a);
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    public final c.b.a.a.i0.h z(float f2, float f3, float f4, int i2) {
        m mVarM = m.a().D(f2).H(f2).u(f3).y(f3).m();
        c.b.a.a.i0.h hVarM = c.b.a.a.i0.h.m(this.f3985b, f4);
        hVarM.setShapeAppearanceModel(mVarM);
        hVarM.Z(0, i2, 0, i2);
        return hVarM;
    }
}
