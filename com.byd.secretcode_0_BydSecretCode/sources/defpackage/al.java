package defpackage;

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

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class al extends bl {
    public static final boolean d;
    public final TextWatcher e;
    public final View.OnFocusChangeListener f;
    public final TextInputLayout.e g;
    public final TextInputLayout.f h;

    @SuppressLint({"ClickableViewAccessibility"})
    public final TextInputLayout.g i;
    public boolean j;
    public boolean k;
    public long l;
    public StateListDrawable m;
    public hk n;
    public AccessibilityManager o;
    public ValueAnimator p;
    public ValueAnimator q;

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class a implements TextWatcher {

        /* JADX INFO: renamed from: al$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
        public class RunnableC0001a implements Runnable {
            public final /* synthetic */ AutoCompleteTextView b;

            public RunnableC0001a(AutoCompleteTextView autoCompleteTextView) {
                this.b = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.b.isPopupShowing();
                al.this.C(zIsPopupShowing);
                al.this.j = zIsPopupShowing;
            }
        }

        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            al alVar = al.this;
            AutoCompleteTextView autoCompleteTextViewX = alVar.x(alVar.a.getEditText());
            autoCompleteTextViewX.post(new RunnableC0001a(autoCompleteTextViewX));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            al.this.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class c implements View.OnFocusChangeListener {
        public c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            al.this.a.setEndIconActivated(z);
            if (z) {
                return;
            }
            al.this.C(false);
            al.this.j = false;
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class d extends TextInputLayout.e {
        public d(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, defpackage.a9
        public void g(View view, ea eaVar) {
            super.g(view, eaVar);
            if (al.this.a.getEditText().getKeyListener() == null) {
                eaVar.b0(Spinner.class.getName());
            }
            if (eaVar.L()) {
                eaVar.l0(null);
            }
        }

        @Override // defpackage.a9
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            al alVar = al.this;
            AutoCompleteTextView autoCompleteTextViewX = alVar.x(alVar.a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && al.this.o.isTouchExplorationEnabled()) {
                al.this.F(autoCompleteTextViewX);
            }
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class e implements TextInputLayout.f {
        public e() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            AutoCompleteTextView autoCompleteTextViewX = al.this.x(textInputLayout.getEditText());
            al.this.D(autoCompleteTextViewX);
            al.this.u(autoCompleteTextViewX);
            al.this.E(autoCompleteTextViewX);
            autoCompleteTextViewX.setThreshold(0);
            autoCompleteTextViewX.removeTextChangedListener(al.this.e);
            autoCompleteTextViewX.addTextChangedListener(al.this.e);
            textInputLayout.setEndIconCheckable(true);
            textInputLayout.setErrorIconDrawable((Drawable) null);
            textInputLayout.setTextInputAccessibilityDelegate(al.this.g);
            textInputLayout.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class f implements TextInputLayout.g {
        public f() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i != 3) {
                return;
            }
            autoCompleteTextView.removeTextChangedListener(al.this.e);
            if (autoCompleteTextView.getOnFocusChangeListener() == al.this.f) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            if (al.d) {
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
            al.this.F((AutoCompleteTextView) al.this.a.getEditText());
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class h implements View.OnTouchListener {
        public final /* synthetic */ AutoCompleteTextView b;

        public h(AutoCompleteTextView autoCompleteTextView) {
            this.b = autoCompleteTextView;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (al.this.B()) {
                    al.this.j = false;
                }
                al.this.F(this.b);
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
            al.this.j = true;
            al.this.l = System.currentTimeMillis();
            al.this.C(false);
        }
    }

    /* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
    public class j extends AnimatorListenerAdapter {
        public j() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            al alVar = al.this;
            alVar.c.setChecked(alVar.k);
            al.this.q.start();
        }
    }

    static {
        d = Build.VERSION.SDK_INT >= 21;
    }

    public al(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.e = new a();
        this.f = new c();
        this.g = new d(this.a);
        this.h = new e();
        this.i = new f();
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
        if (d) {
            int boxBackgroundMode = this.a.getBoxBackgroundMode();
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
        autoCompleteTextView.setOnFocusChangeListener(this.f);
        if (d) {
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
        if (d) {
            C(!this.k);
        } else {
            this.k = !this.k;
            this.c.toggle();
        }
        if (!this.k) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    @Override // defpackage.bl
    public void a() {
        float dimensionPixelOffset = this.b.getResources().getDimensionPixelOffset(R$dimen.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.b.getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.b.getResources().getDimensionPixelOffset(R$dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        hk hkVarZ = z(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        hk hkVarZ2 = z(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.n = hkVarZ;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.m = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, hkVarZ);
        this.m.addState(new int[0], hkVarZ2);
        this.a.setEndIconDrawable(u0.d(this.b, d ? R$drawable.mtrl_dropdown_arrow : R$drawable.mtrl_ic_arrow_drop_down));
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R$string.exposed_dropdown_menu_content_description));
        this.a.setEndIconOnClickListener(new g());
        this.a.e(this.h);
        this.a.f(this.i);
        A();
        t9.t0(this.c, 2);
        this.o = (AccessibilityManager) this.b.getSystemService("accessibility");
    }

    @Override // defpackage.bl
    public boolean b(int i2) {
        return i2 != 0;
    }

    @Override // defpackage.bl
    public boolean d() {
        return true;
    }

    public final void u(AutoCompleteTextView autoCompleteTextView) {
        if (autoCompleteTextView.getKeyListener() != null) {
            return;
        }
        int boxBackgroundMode = this.a.getBoxBackgroundMode();
        hk boxBackground = this.a.getBoxBackground();
        int iC = bi.c(autoCompleteTextView, R$attr.colorControlHighlight);
        int[][] iArr = {new int[]{R.attr.state_pressed}, new int[0]};
        if (boxBackgroundMode == 2) {
            w(autoCompleteTextView, iC, iArr, boxBackground);
        } else if (boxBackgroundMode == 1) {
            v(autoCompleteTextView, iC, iArr, boxBackground);
        }
    }

    public final void v(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, hk hkVar) {
        int boxBackgroundColor = this.a.getBoxBackgroundColor();
        int[] iArr2 = {bi.f(i2, boxBackgroundColor, 0.1f), boxBackgroundColor};
        if (d) {
            t9.m0(autoCompleteTextView, new RippleDrawable(new ColorStateList(iArr, iArr2), hkVar, hkVar));
            return;
        }
        hk hkVar2 = new hk(hkVar.D());
        hkVar2.X(new ColorStateList(iArr, iArr2));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hkVar, hkVar2});
        int iE = t9.E(autoCompleteTextView);
        int paddingTop = autoCompleteTextView.getPaddingTop();
        int iD = t9.D(autoCompleteTextView);
        int paddingBottom = autoCompleteTextView.getPaddingBottom();
        t9.m0(autoCompleteTextView, layerDrawable);
        t9.w0(autoCompleteTextView, iE, paddingTop, iD, paddingBottom);
    }

    public final void w(AutoCompleteTextView autoCompleteTextView, int i2, int[][] iArr, hk hkVar) {
        LayerDrawable layerDrawable;
        int iC = bi.c(autoCompleteTextView, R$attr.colorSurface);
        hk hkVar2 = new hk(hkVar.D());
        int iF = bi.f(i2, iC, 0.1f);
        hkVar2.X(new ColorStateList(iArr, new int[]{iF, 0}));
        if (d) {
            hkVar2.setTint(iC);
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{iF, iC});
            hk hkVar3 = new hk(hkVar.D());
            hkVar3.setTint(-1);
            layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, hkVar2, hkVar3), hkVar});
        } else {
            layerDrawable = new LayerDrawable(new Drawable[]{hkVar2, hkVar});
        }
        t9.m0(autoCompleteTextView, layerDrawable);
    }

    public final AutoCompleteTextView x(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public final ValueAnimator y(int i2, float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(bh.a);
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    public final hk z(float f2, float f3, float f4, int i2) {
        mk mkVarM = mk.a().D(f2).H(f2).u(f3).y(f3).m();
        hk hkVarM = hk.m(this.b, f4);
        hkVarM.setShapeAppearanceModel(mkVarM);
        hkVarM.Z(0, i2, 0, i2);
        return hkVarM;
    }
}
