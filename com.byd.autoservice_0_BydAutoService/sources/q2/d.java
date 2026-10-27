package q2;

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
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.TextInputLayout;
import l0.t;
import n2.m;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends q2.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f4578d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextWatcher f4579e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View.OnFocusChangeListener f4580f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TextInputLayout.e f4581g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final TextInputLayout.f f4582h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @SuppressLint({"ClickableViewAccessibility"})
    public final TextInputLayout.g f4583i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4584j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4585k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f4586l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public StateListDrawable f4587m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public n2.h f4588n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AccessibilityManager f4589o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ValueAnimator f4590p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ValueAnimator f4591q;

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class a implements TextWatcher {

        /* JADX INFO: renamed from: q2.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        public class RunnableC0054a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AutoCompleteTextView f4593b;

            public RunnableC0054a(AutoCompleteTextView autoCompleteTextView) {
                this.f4593b = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f4593b.isPopupShowing();
                d.this.C(zIsPopupShowing);
                d.this.f4584j = zIsPopupShowing;
            }
        }

        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f4605a.getEditText());
            autoCompleteTextViewX.post(new RunnableC0054a(autoCompleteTextViewX));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f4607c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class c implements View.OnFocusChangeListener {
        public c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z3) {
            d.this.f4605a.setEndIconActivated(z3);
            if (z3) {
                return;
            }
            d.this.C(false);
            d.this.f4584j = false;
        }
    }

    /* JADX INFO: renamed from: q2.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class C0055d extends TextInputLayout.e {
        public C0055d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            if (d.this.f4605a.getEditText().getKeyListener() == null) {
                cVar.b0(Spinner.class.getName());
            }
            if (cVar.L()) {
                cVar.l0(null);
            }
        }

        @Override // l0.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f4605a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.f4589o.isTouchExplorationEnabled()) {
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
            autoCompleteTextViewX.removeTextChangedListener(d.this.f4579e);
            autoCompleteTextViewX.addTextChangedListener(d.this.f4579e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(d.this.f4581g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class f implements TextInputLayout.g {
        public f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i3) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i3 != 3) {
                return;
            }
            autoCompleteTextView.removeTextChangedListener(d.this.f4579e);
            if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f4580f) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (d.f4578d) {
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
            d.this.F((AutoCompleteTextView) d.this.f4605a.getEditText());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class h implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AutoCompleteTextView f4601b;

        public h(AutoCompleteTextView autoCompleteTextView) {
            this.f4601b = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.B()) {
                    d.this.f4584j = false;
                }
                d.this.F(this.f4601b);
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
            d.this.f4584j = true;
            d.this.f4586l = System.currentTimeMillis();
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
            dVar.f4607c.setChecked(dVar.f4585k);
            d.this.f4591q.start();
        }
    }

    static {
        f4578d = Build.VERSION.SDK_INT >= 21;
    }

    public d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f4579e = new a();
        this.f4580f = new c();
        this.f4581g = new C0055d(this.f4605a);
        this.f4582h = new e();
        this.f4583i = new f();
        this.f4584j = false;
        this.f4585k = false;
        this.f4586l = Long.MAX_VALUE;
    }

    public final void A() {
        this.f4591q = y(67, 0.0f, 1.0f);
        ValueAnimator valueAnimatorY = y(50, 1.0f, 0.0f);
        this.f4590p = valueAnimatorY;
        valueAnimatorY.addListener(new j());
    }

    public final boolean B() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f4586l;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }

    public final void C(boolean z3) {
        if (this.f4585k != z3) {
            this.f4585k = z3;
            this.f4591q.cancel();
            this.f4590p.start();
        }
    }

    public final void D(AutoCompleteTextView autoCompleteTextView) {
        if (f4578d) {
            int boxBackgroundMode = this.f4605a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.f4588n);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.f4587m);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void E(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new h(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f4580f);
        if (f4578d) {
            autoCompleteTextView.setOnDismissListener(new i());
        }
    }

    public final void F(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            return;
        }
        if (B()) {
            this.f4584j = false;
        }
        if (this.f4584j) {
            this.f4584j = false;
            return;
        }
        if (f4578d) {
            C(!this.f4585k);
        } else {
            this.f4585k = !this.f4585k;
            this.f4607c.toggle();
        }
        if (!this.f4585k) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    @Override // q2.e
    public void a() {
        float dimensionPixelOffset = this.f4606b.getResources().getDimensionPixelOffset(R$dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.f4606b.getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f4606b.getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        n2.h hVarZ = z(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        n2.h hVarZ2 = z(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.f4588n = hVarZ;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.f4587m = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, hVarZ);
        this.f4587m.addState(new int[0], hVarZ2);
        this.f4605a.setEndIconDrawable(j.a.d(this.f4606b, f4578d ? R$drawable.mtrl_dropdown_arrow : R$drawable.mtrl_ic_arrow_drop_down));
        TextInputLayout textInputLayout = this.f4605a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R$string.exposed_dropdown_menu_content_description));
        this.f4605a.setEndIconOnClickListener(new g());
        this.f4605a.e(this.f4582h);
        this.f4605a.f(this.f4583i);
        A();
        t.t0(this.f4607c, 2);
        this.f4589o = (AccessibilityManager) this.f4606b.getSystemService("accessibility");
    }

    @Override // q2.e
    public boolean b(int i3) {
        return i3 != 0;
    }

    @Override // q2.e
    public boolean d() {
        return true;
    }

    public final void u(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() != null) {
            return;
        }
        int boxBackgroundMode = this.f4605a.getBoxBackgroundMode();
        n2.h boxBackground = this.f4605a.getBoxBackground();
        int iC = c2.a.c(autoCompleteTextView, R$attr.colorControlHighlight);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            v(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    public final void v(AutoCompleteTextView autoCompleteTextView, int i3, int[][] iArr, n2.h hVar) {
        int boxBackgroundColor = this.f4605a.getBoxBackgroundColor();
        int[] iArr2 = {c2.a.f(i3, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (f4578d) {
            t.m0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), hVar, hVar));
            return;
        }
        n2.h hVar2 = new n2.h(hVar.D());
        hVar2.X(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar, hVar2});
        int iE = t.E(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iD = t.D(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        t.m0(autoCompleteTextView, layerDrawable);
        t.w0(autoCompleteTextView, iE, paddingTop, iD, paddingBottom);
    }

    public final void w(AutoCompleteTextView autoCompleteTextView, int i3, int[][] iArr, n2.h hVar) {
        LayerDrawable layerDrawable;
        int iC = c2.a.c(autoCompleteTextView, R$attr.colorSurface);
        n2.h hVar2 = new n2.h(hVar.D());
        int iF = c2.a.f(i3, iC, 0.1f);
        hVar2.X(new ColorStateList(iArr, new int[]{iF, 0}));
        if (f4578d) {
            hVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            n2.h hVar3 = new n2.h(hVar.D());
            hVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, hVar2, hVar3), hVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar});
        }
        t.m0(autoCompleteTextView, layerDrawable);
    }

    public final AutoCompleteTextView x(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public final ValueAnimator y(int i3, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(s1.a.f4901a);
        valueAnimatorOfFloat.setDuration(i3);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    public final n2.h z(float f3, float f4, float f5, int i3) {
        m mVarM = m.a().D(f3).H(f3).u(f4).y(f4).m();
        n2.h hVarM = n2.h.m(this.f4606b, f5);
        hVarM.setShapeAppearanceModel(mVarM);
        hVarM.Z(0, i3, 0, i3);
        return hVarM;
    }
}
