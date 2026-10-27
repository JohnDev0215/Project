package h6;

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
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.TextInputLayout;
import e6.m;
import o0.t;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends h6.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f5380d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextWatcher f5381e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View.OnFocusChangeListener f5382f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TextInputLayout.e f5383g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final TextInputLayout.f f5384h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @SuppressLint({"ClickableViewAccessibility"})
    public final TextInputLayout.g f5385i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5386j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f5387k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f5388l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public StateListDrawable f5389m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e6.h f5390n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AccessibilityManager f5391o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ValueAnimator f5392p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ValueAnimator f5393q;

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class a implements TextWatcher {

        /* JADX INFO: renamed from: h6.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        public class RunnableC0055a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ AutoCompleteTextView f5395d;

            public RunnableC0055a(AutoCompleteTextView autoCompleteTextView) {
                this.f5395d = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f5395d.isPopupShowing();
                d.this.C(zIsPopupShowing);
                d.this.f5386j = zIsPopupShowing;
            }
        }

        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f5407a.getEditText());
            autoCompleteTextViewX.post(new RunnableC0055a(autoCompleteTextViewX));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            d.this.f5409c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class c implements View.OnFocusChangeListener {
        public c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z7) {
            d.this.f5407a.setEndIconActivated(z7);
            if (z7) {
                return;
            }
            d.this.C(false);
            d.this.f5386j = false;
        }
    }

    /* JADX INFO: renamed from: h6.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class C0056d extends TextInputLayout.e {
        public C0056d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            if (d.this.f5407a.getEditText().getKeyListener() == null) {
                cVar.c0(Spinner.class.getName());
            }
            if (cVar.M()) {
                cVar.m0(null);
            }
        }

        @Override // o0.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            d dVar = d.this;
            AutoCompleteTextView autoCompleteTextViewX = dVar.x(dVar.f5407a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && d.this.f5391o.isTouchExplorationEnabled()) {
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
            autoCompleteTextViewX.removeTextChangedListener(d.this.f5381e);
            autoCompleteTextViewX.addTextChangedListener(d.this.f5381e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(d.this.f5383g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class f implements TextInputLayout.g {
        public f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i8) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i8 != 3) {
                return;
            }
            autoCompleteTextView.removeTextChangedListener(d.this.f5381e);
            if (autoCompleteTextView.getOnFocusChangeListener() == d.this.f5382f) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (d.f5380d) {
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
            d.this.F((AutoCompleteTextView) d.this.f5407a.getEditText());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class h implements View.OnTouchListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ AutoCompleteTextView f5403d;

        public h(AutoCompleteTextView autoCompleteTextView) {
            this.f5403d = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (d.this.B()) {
                    d.this.f5386j = false;
                }
                d.this.F(this.f5403d);
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
            d.this.f5386j = true;
            d.this.f5388l = System.currentTimeMillis();
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
            dVar.f5409c.setChecked(dVar.f5387k);
            d.this.f5393q.start();
        }
    }

    static {
        f5380d = Build.VERSION.SDK_INT >= 21;
    }

    public d(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f5381e = new a();
        this.f5382f = new c();
        this.f5383g = new C0056d(this.f5407a);
        this.f5384h = new e();
        this.f5385i = new f();
        this.f5386j = false;
        this.f5387k = false;
        this.f5388l = RecyclerView.FOREVER_NS;
    }

    public final void A() {
        this.f5393q = y(67, 0.0f, 1.0f);
        ValueAnimator valueAnimatorY = y(50, 1.0f, 0.0f);
        this.f5392p = valueAnimatorY;
        valueAnimatorY.addListener(new j());
    }

    public final boolean B() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f5388l;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }

    public final void C(boolean z7) {
        if (this.f5387k != z7) {
            this.f5387k = z7;
            this.f5393q.cancel();
            this.f5392p.start();
        }
    }

    public final void D(AutoCompleteTextView autoCompleteTextView) {
        if (f5380d) {
            int boxBackgroundMode = this.f5407a.getBoxBackgroundMode();
            if (boxBackgroundMode == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.f5390n);
            } else if (boxBackgroundMode == 1) {
                autoCompleteTextView.setDropDownBackgroundDrawable(this.f5389m);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void E(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnTouchListener(new h(autoCompleteTextView));
        autoCompleteTextView.setOnFocusChangeListener(this.f5382f);
        if (f5380d) {
            autoCompleteTextView.setOnDismissListener(new i());
        }
    }

    public final void F(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView == null) {
            return;
        }
        if (B()) {
            this.f5386j = false;
        }
        if (this.f5386j) {
            this.f5386j = false;
            return;
        }
        if (f5380d) {
            C(!this.f5387k);
        } else {
            this.f5387k = !this.f5387k;
            this.f5409c.toggle();
        }
        if (!this.f5387k) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    @Override // h6.e
    public void a() {
        float dimensionPixelOffset = this.f5408b.getResources().getDimensionPixelOffset(R$dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.f5408b.getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f5408b.getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        e6.h hVarZ = z(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        e6.h hVarZ2 = z(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.f5390n = hVarZ;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.f5389m = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, hVarZ);
        this.f5389m.addState(new int[0], hVarZ2);
        this.f5407a.setEndIconDrawable(m.a.d(this.f5408b, f5380d ? R$drawable.mtrl_dropdown_arrow : R$drawable.mtrl_ic_arrow_drop_down));
        TextInputLayout textInputLayout = this.f5407a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R$string.exposed_dropdown_menu_content_description));
        this.f5407a.setEndIconOnClickListener(new g());
        this.f5407a.e(this.f5384h);
        this.f5407a.f(this.f5385i);
        A();
        t.v0(this.f5409c, 2);
        this.f5391o = (AccessibilityManager) this.f5408b.getSystemService("accessibility");
    }

    @Override // h6.e
    public boolean b(int i8) {
        return i8 != 0;
    }

    @Override // h6.e
    public boolean d() {
        return true;
    }

    public final void u(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() != null) {
            return;
        }
        int boxBackgroundMode = this.f5407a.getBoxBackgroundMode();
        e6.h boxBackground = this.f5407a.getBoxBackground();
        int iC = t5.a.c(autoCompleteTextView, R$attr.colorControlHighlight);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            v(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    public final void v(AutoCompleteTextView autoCompleteTextView, int i8, int[][] iArr, e6.h hVar) {
        int boxBackgroundColor = this.f5407a.getBoxBackgroundColor();
        int[] iArr2 = {t5.a.f(i8, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (f5380d) {
            t.o0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), hVar, hVar));
            return;
        }
        e6.h hVar2 = new e6.h(hVar.D());
        hVar2.X(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar, hVar2});
        int iF = t.F(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iE = t.E(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        t.o0(autoCompleteTextView, layerDrawable);
        t.z0(autoCompleteTextView, iF, paddingTop, iE, paddingBottom);
    }

    public final void w(AutoCompleteTextView autoCompleteTextView, int i8, int[][] iArr, e6.h hVar) {
        LayerDrawable layerDrawable;
        int iC = t5.a.c(autoCompleteTextView, R$attr.colorSurface);
        e6.h hVar2 = new e6.h(hVar.D());
        int iF = t5.a.f(i8, iC, 0.1f);
        hVar2.X(new ColorStateList(iArr, new int[]{iF, 0}));
        if (f5380d) {
            hVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            e6.h hVar3 = new e6.h(hVar.D());
            hVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, hVar2, hVar3), hVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar});
        }
        t.o0(autoCompleteTextView, layerDrawable);
    }

    public final AutoCompleteTextView x(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public final ValueAnimator y(int i8, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(j5.a.f5578a);
        valueAnimatorOfFloat.setDuration(i8);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    public final e6.h z(float f8, float f9, float f10, int i8) {
        m mVarM = m.a().D(f8).H(f8).u(f9).y(f9).m();
        e6.h hVarM = e6.h.m(this.f5408b, f10);
        hVarM.setShapeAppearanceModel(mVarM);
        hVarM.Z(0, i8, 0, i8);
        return hVarM;
    }
}
