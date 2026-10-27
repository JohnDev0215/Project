package b.d.a.a.h0;

import android.R;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import b.d.a.a.e0.g;
import b.d.a.a.e0.j;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public class h extends m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextWatcher f1909d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View.OnFocusChangeListener f1910e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextInputLayout.e f1911f;
    public final TextInputLayout.f g;

    @SuppressLint({"ClickableViewAccessibility"})
    public final TextInputLayout.g h;
    public boolean i;
    public boolean j;
    public long k;
    public StateListDrawable l;
    public b.d.a.a.e0.g m;
    public AccessibilityManager n;
    public ValueAnimator o;
    public ValueAnimator p;

    public class a extends b.d.a.a.z.j {

        /* JADX INFO: renamed from: b.d.a.a.h0.h$a$a, reason: collision with other inner class name */
        public class RunnableC0040a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AutoCompleteTextView f1913a;

            public RunnableC0040a(AutoCompleteTextView autoCompleteTextView) {
                this.f1913a = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPopupShowing = this.f1913a.isPopupShowing();
                h.g(h.this, zIsPopupShowing);
                h.this.i = zIsPopupShowing;
            }
        }

        public a() {
        }

        @Override // b.d.a.a.z.j, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            AutoCompleteTextView autoCompleteTextViewE = h.e(h.this.f1927a.getEditText());
            if (h.this.n.isTouchExplorationEnabled() && h.f(autoCompleteTextViewE) && !h.this.f1929c.hasFocus()) {
                autoCompleteTextViewE.dismissDropDown();
            }
            autoCompleteTextViewE.post(new RunnableC0040a(autoCompleteTextViewE));
        }
    }

    public class b implements View.OnFocusChangeListener {
        public b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            h.this.f1927a.setEndIconActivated(z);
            if (z) {
                return;
            }
            h.g(h.this, false);
            h.this.i = false;
        }
    }

    public class c extends TextInputLayout.e {
        public c(TextInputLayout textInputLayout) {
            super(textInputLayout);
        }

        @Override // com.google.android.material.textfield.TextInputLayout.e, a.h.k.a
        public void d(View view, a.h.k.x.b bVar) {
            super.d(view, bVar);
            if (!h.f(h.this.f1927a.getEditText())) {
                bVar.f856a.setClassName(Spinner.class.getName());
            }
            if (bVar.f856a.isShowingHintText()) {
                bVar.f856a.setHintText(null);
            }
        }

        @Override // a.h.k.a
        public void e(View view, AccessibilityEvent accessibilityEvent) {
            this.f807a.onPopulateAccessibilityEvent(view, accessibilityEvent);
            AutoCompleteTextView autoCompleteTextViewE = h.e(h.this.f1927a.getEditText());
            if (accessibilityEvent.getEventType() == 1 && h.this.n.isTouchExplorationEnabled() && !h.f(h.this.f1927a.getEditText())) {
                h.h(h.this, autoCompleteTextViewE);
            }
        }
    }

    public class d implements TextInputLayout.f {
        public d() {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0112  */
        @Override // com.google.android.material.textfield.TextInputLayout.f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(com.google.android.material.textfield.TextInputLayout r13) {
            /*
                Method dump skipped, instruction units count: 275
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: b.d.a.a.h0.h.d.a(com.google.android.material.textfield.TextInputLayout):void");
        }
    }

    public class e implements TextInputLayout.g {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AutoCompleteTextView f1919a;

            public a(AutoCompleteTextView autoCompleteTextView) {
                this.f1919a = autoCompleteTextView;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f1919a.removeTextChangedListener(h.this.f1909d);
            }
        }

        public e() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.getEditText();
            if (autoCompleteTextView == null || i != 3) {
                return;
            }
            autoCompleteTextView.post(new a(autoCompleteTextView));
            if (autoCompleteTextView.getOnFocusChangeListener() == h.this.f1910e) {
                autoCompleteTextView.setOnFocusChangeListener(null);
            }
            autoCompleteTextView.setOnTouchListener(null);
            autoCompleteTextView.setOnDismissListener(null);
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h.h(h.this, (AutoCompleteTextView) h.this.f1927a.getEditText());
        }
    }

    public h(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f1909d = new a();
        this.f1910e = new b();
        this.f1911f = new c(this.f1927a);
        this.g = new d();
        this.h = new e();
        this.i = false;
        this.j = false;
        this.k = Long.MAX_VALUE;
    }

    public static AutoCompleteTextView e(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public static boolean f(EditText editText) {
        return editText.getKeyListener() != null;
    }

    public static void g(h hVar, boolean z) {
        if (hVar.j != z) {
            hVar.j = z;
            hVar.p.cancel();
            hVar.o.start();
        }
    }

    public static void h(h hVar, AutoCompleteTextView autoCompleteTextView) {
        if (hVar == null) {
            throw null;
        }
        if (autoCompleteTextView == null) {
            return;
        }
        if (hVar.j()) {
            hVar.i = false;
        }
        if (hVar.i) {
            hVar.i = false;
            return;
        }
        boolean z = hVar.j;
        boolean z2 = !z;
        if (z != z2) {
            hVar.j = z2;
            hVar.p.cancel();
            hVar.o.start();
        }
        if (!hVar.j) {
            autoCompleteTextView.dismissDropDown();
        } else {
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.showDropDown();
        }
    }

    @Override // b.d.a.a.h0.m
    public void a() {
        float dimensionPixelOffset = this.f1928b.getResources().getDimensionPixelOffset(b.d.a.a.d.mtrl_shape_corner_size_small_component);
        float dimensionPixelOffset2 = this.f1928b.getResources().getDimensionPixelOffset(b.d.a.a.d.mtrl_exposed_dropdown_menu_popup_elevation);
        int dimensionPixelOffset3 = this.f1928b.getResources().getDimensionPixelOffset(b.d.a.a.d.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        b.d.a.a.e0.g gVarI = i(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        b.d.a.a.e0.g gVarI2 = i(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.m = gVarI;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.l = stateListDrawable;
        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, gVarI);
        this.l.addState(new int[0], gVarI2);
        this.f1927a.setEndIconDrawable(a.b.l.a.a.b(this.f1928b, b.d.a.a.e.mtrl_dropdown_arrow));
        TextInputLayout textInputLayout = this.f1927a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(b.d.a.a.i.exposed_dropdown_menu_content_description));
        this.f1927a.setEndIconOnClickListener(new f());
        this.f1927a.a(this.g);
        this.f1927a.f0.add(this.h);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(b.d.a.a.l.a.f1966a);
        valueAnimatorOfFloat.setDuration(67);
        valueAnimatorOfFloat.addUpdateListener(new i(this));
        this.p = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setInterpolator(b.d.a.a.l.a.f1966a);
        valueAnimatorOfFloat2.setDuration(50);
        valueAnimatorOfFloat2.addUpdateListener(new i(this));
        this.o = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.addListener(new l(this));
        this.n = (AccessibilityManager) this.f1928b.getSystemService("accessibility");
    }

    @Override // b.d.a.a.h0.m
    public boolean b(int i) {
        return i != 0;
    }

    @Override // b.d.a.a.h0.m
    public boolean d() {
        return true;
    }

    public final b.d.a.a.e0.g i(float f2, float f3, float f4, int i) {
        j.b bVar = new j.b();
        bVar.f1853e = new b.d.a.a.e0.a(f2);
        bVar.f1854f = new b.d.a.a.e0.a(f2);
        bVar.h = new b.d.a.a.e0.a(f3);
        bVar.g = new b.d.a.a.e0.a(f3);
        b.d.a.a.e0.j jVarA = bVar.a();
        b.d.a.a.e0.g gVarF = b.d.a.a.e0.g.f(this.f1928b, f4);
        gVarF.f1828a.f1835a = jVarA;
        gVarF.invalidateSelf();
        g.b bVar2 = gVarF.f1828a;
        if (bVar2.i == null) {
            bVar2.i = new Rect();
        }
        gVarF.f1828a.i.set(0, i, 0, i);
        gVarF.invalidateSelf();
        return gVarF;
    }

    public final boolean j() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.k;
        return jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300;
    }
}
