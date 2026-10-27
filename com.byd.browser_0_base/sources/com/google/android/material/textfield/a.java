package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.AccessibilityManagerTouchExplorationStateChangeListenerC0222z;
import defpackage.InterfaceC0220y;
import defpackage.cy1;
import defpackage.gq2;
import defpackage.in3;
import defpackage.om3;
import defpackage.pe0;
import defpackage.q20;
import defpackage.q83;
import defpackage.qn1;
import defpackage.rc1;
import defpackage.rw0;
import defpackage.sh0;
import defpackage.yn3;
import defpackage.zt;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: EndCompoundLayout.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class a extends LinearLayout {
    public final TextInputLayout e;
    public final FrameLayout f;
    public final CheckableImageButton g;
    public ColorStateList h;
    public PorterDuff.Mode i;
    public View.OnLongClickListener j;
    public final CheckableImageButton k;
    public final d l;
    public int m;
    public final LinkedHashSet<TextInputLayout.h> n;
    public ColorStateList o;
    public PorterDuff.Mode p;
    public View.OnLongClickListener q;
    public CharSequence r;
    public final AppCompatTextView s;
    public boolean t;
    public EditText u;
    public final AccessibilityManager v;
    public InterfaceC0220y w;
    public final C0037a x;

    /* JADX INFO: renamed from: com.google.android.material.textfield.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EndCompoundLayout.java */
    public class C0037a extends q83 {
        public C0037a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            a.this.b().a();
        }

        @Override // defpackage.q83, android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            a.this.b().b();
        }
    }

    /* JADX INFO: compiled from: EndCompoundLayout.java */
    public class b implements TextInputLayout.g {
        public b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public final void a(TextInputLayout textInputLayout) {
            a aVar = a.this;
            if (aVar.u == textInputLayout.getEditText()) {
                return;
            }
            EditText editText = aVar.u;
            C0037a c0037a = aVar.x;
            if (editText != null) {
                editText.removeTextChangedListener(c0037a);
                if (aVar.u.getOnFocusChangeListener() == aVar.b().e()) {
                    aVar.u.setOnFocusChangeListener(null);
                }
            }
            EditText editText2 = textInputLayout.getEditText();
            aVar.u = editText2;
            if (editText2 != null) {
                editText2.addTextChangedListener(c0037a);
            }
            aVar.b().m(aVar.u);
            aVar.i(aVar.b());
        }
    }

    /* JADX INFO: compiled from: EndCompoundLayout.java */
    public class c implements View.OnAttachStateChangeListener {
        public c() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            AccessibilityManager accessibilityManager;
            a aVar = a.this;
            if (aVar.w == null || (accessibilityManager = aVar.v) == null) {
                return;
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(aVar)) {
                accessibilityManager.addTouchExplorationStateChangeListener(new AccessibilityManagerTouchExplorationStateChangeListenerC0222z(aVar.w));
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            AccessibilityManager accessibilityManager;
            a aVar = a.this;
            InterfaceC0220y interfaceC0220y = aVar.w;
            if (interfaceC0220y == null || (accessibilityManager = aVar.v) == null) {
                return;
            }
            accessibilityManager.removeTouchExplorationStateChangeListener(new AccessibilityManagerTouchExplorationStateChangeListenerC0222z(interfaceC0220y));
        }
    }

    /* JADX INFO: compiled from: EndCompoundLayout.java */
    public static class d {
        public final SparseArray<sh0> a = new SparseArray<>();
        public final a b;
        public final int c;
        public final int d;

        public d(a aVar, TintTypedArray tintTypedArray) {
            this.b = aVar;
            this.c = tintTypedArray.i(R$styleable.TextInputLayout_endIconDrawable, 0);
            this.d = tintTypedArray.i(R$styleable.TextInputLayout_passwordToggleDrawable, 0);
        }
    }

    public a(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        CharSequence charSequenceK;
        super(textInputLayout.getContext());
        this.m = 0;
        this.n = new LinkedHashSet<>();
        this.x = new C0037a();
        b bVar = new b();
        this.v = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.e = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.f = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        CheckableImageButton checkableImageButtonA = a(R$id.text_input_error_icon, layoutInflaterFrom, this);
        this.g = checkableImageButtonA;
        CheckableImageButton checkableImageButtonA2 = a(R$id.text_input_end_icon, layoutInflaterFrom, frameLayout);
        this.k = checkableImageButtonA2;
        this.l = new d(this, tintTypedArray);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.s = appCompatTextView;
        int i = R$styleable.TextInputLayout_errorIconTint;
        if (tintTypedArray.l(i)) {
            this.h = rc1.b(getContext(), tintTypedArray, i);
        }
        int i2 = R$styleable.TextInputLayout_errorIconTintMode;
        if (tintTypedArray.l(i2)) {
            this.i = yn3.e(tintTypedArray.h(i2, -1), null);
        }
        int i3 = R$styleable.TextInputLayout_errorIconDrawable;
        if (tintTypedArray.l(i3)) {
            h(tintTypedArray.e(i3));
        }
        checkableImageButtonA.setContentDescription(getResources().getText(R$string.error_icon_content_description));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(checkableImageButtonA, 2);
        checkableImageButtonA.setClickable(false);
        checkableImageButtonA.setPressable(false);
        checkableImageButtonA.setFocusable(false);
        int i4 = R$styleable.TextInputLayout_passwordToggleEnabled;
        if (!tintTypedArray.l(i4)) {
            int i5 = R$styleable.TextInputLayout_endIconTint;
            if (tintTypedArray.l(i5)) {
                this.o = rc1.b(getContext(), tintTypedArray, i5);
            }
            int i6 = R$styleable.TextInputLayout_endIconTintMode;
            if (tintTypedArray.l(i6)) {
                this.p = yn3.e(tintTypedArray.h(i6, -1), null);
            }
        }
        int i7 = R$styleable.TextInputLayout_endIconMode;
        if (tintTypedArray.l(i7)) {
            f(tintTypedArray.h(i7, 0));
            int i8 = R$styleable.TextInputLayout_endIconContentDescription;
            if (tintTypedArray.l(i8) && checkableImageButtonA2.getContentDescription() != (charSequenceK = tintTypedArray.k(i8))) {
                checkableImageButtonA2.setContentDescription(charSequenceK);
            }
            checkableImageButtonA2.setCheckable(tintTypedArray.a(R$styleable.TextInputLayout_endIconCheckable, true));
        } else if (tintTypedArray.l(i4)) {
            int i9 = R$styleable.TextInputLayout_passwordToggleTint;
            if (tintTypedArray.l(i9)) {
                this.o = rc1.b(getContext(), tintTypedArray, i9);
            }
            int i10 = R$styleable.TextInputLayout_passwordToggleTintMode;
            if (tintTypedArray.l(i10)) {
                this.p = yn3.e(tintTypedArray.h(i10, -1), null);
            }
            f(tintTypedArray.a(i4, false) ? 1 : 0);
            CharSequence charSequenceK2 = tintTypedArray.k(R$styleable.TextInputLayout_passwordToggleContentDescription);
            if (checkableImageButtonA2.getContentDescription() != charSequenceK2) {
                checkableImageButtonA2.setContentDescription(charSequenceK2);
            }
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(R$id.textinput_suffix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        om3.g.e(appCompatTextView, 1);
        appCompatTextView.setTextAppearance(tintTypedArray.i(R$styleable.TextInputLayout_suffixTextAppearance, 0));
        int i11 = R$styleable.TextInputLayout_suffixTextColor;
        if (tintTypedArray.l(i11)) {
            appCompatTextView.setTextColor(tintTypedArray.b(i11));
        }
        CharSequence charSequenceK3 = tintTypedArray.k(R$styleable.TextInputLayout_suffixText);
        this.r = TextUtils.isEmpty(charSequenceK3) ? null : charSequenceK3;
        appCompatTextView.setText(charSequenceK3);
        m();
        frameLayout.addView(checkableImageButtonA2);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(checkableImageButtonA);
        textInputLayout.addOnEditTextAttachedListener(bVar);
        addOnAttachStateChangeListener(new c());
    }

    public final CheckableImageButton a(int i, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R$layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i);
        if (rc1.e(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginStart(0);
        }
        return checkableImageButton;
    }

    public void addOnEndIconChangedListener(TextInputLayout.h hVar) {
        this.n.add(hVar);
    }

    public final sh0 b() {
        sh0 q20Var;
        int i = this.m;
        d dVar = this.l;
        SparseArray<sh0> sparseArray = dVar.a;
        sh0 cy1Var = sparseArray.get(i);
        if (cy1Var == null) {
            a aVar = dVar.b;
            if (i == -1) {
                q20Var = new q20(aVar);
            } else if (i == 0) {
                q20Var = new qn1(aVar);
            } else if (i == 1) {
                cy1Var = new cy1(aVar, dVar.d);
                sparseArray.append(i, cy1Var);
            } else if (i == 2) {
                q20Var = new zt(aVar);
            } else {
                if (i != 3) {
                    throw new IllegalArgumentException(gq2.e("Invalid end icon mode: ", i));
                }
                q20Var = new pe0(aVar);
            }
            cy1Var = q20Var;
            sparseArray.append(i, cy1Var);
        }
        return cy1Var;
    }

    public final boolean c() {
        return this.f.getVisibility() == 0 && this.k.getVisibility() == 0;
    }

    public final boolean d() {
        return this.g.getVisibility() == 0;
    }

    public final void e(boolean z) {
        boolean z2;
        boolean zIsActivated;
        boolean zIsChecked;
        sh0 sh0VarB = b();
        boolean zK = sh0VarB.k();
        CheckableImageButton checkableImageButton = this.k;
        boolean z3 = true;
        if (!zK || (zIsChecked = checkableImageButton.isChecked()) == sh0VarB.l()) {
            z2 = false;
        } else {
            checkableImageButton.setChecked(!zIsChecked);
            z2 = true;
        }
        if (!(sh0VarB instanceof pe0) || (zIsActivated = checkableImageButton.isActivated()) == sh0VarB.j()) {
            z3 = z2;
        } else {
            checkableImageButton.setActivated(!zIsActivated);
        }
        if (z || z3) {
            rw0.b(this.e, checkableImageButton, this.o);
        }
    }

    public final void f(int i) {
        if (this.m == i) {
            return;
        }
        sh0 sh0VarB = b();
        InterfaceC0220y interfaceC0220y = this.w;
        AccessibilityManager accessibilityManager = this.v;
        if (interfaceC0220y != null && accessibilityManager != null) {
            accessibilityManager.removeTouchExplorationStateChangeListener(new AccessibilityManagerTouchExplorationStateChangeListenerC0222z(interfaceC0220y));
        }
        this.w = null;
        sh0VarB.s();
        this.m = i;
        Iterator<TextInputLayout.h> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        g(i != 0);
        sh0 sh0VarB2 = b();
        int iD = this.l.c;
        if (iD == 0) {
            iD = sh0VarB2.d();
        }
        Drawable drawableA = iD != 0 ? AppCompatResources.a(getContext(), iD) : null;
        CheckableImageButton checkableImageButton = this.k;
        checkableImageButton.setImageDrawable(drawableA);
        TextInputLayout textInputLayout = this.e;
        if (drawableA != null) {
            rw0.a(textInputLayout, checkableImageButton, this.o, this.p);
            rw0.b(textInputLayout, checkableImageButton, this.o);
        }
        int iC = sh0VarB2.c();
        CharSequence text = iC != 0 ? getResources().getText(iC) : null;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
        checkableImageButton.setCheckable(sh0VarB2.k());
        if (!sh0VarB2.i(textInputLayout.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i);
        }
        sh0VarB2.r();
        InterfaceC0220y interfaceC0220yH = sh0VarB2.h();
        this.w = interfaceC0220yH;
        if (interfaceC0220yH != null && accessibilityManager != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(this)) {
                accessibilityManager.addTouchExplorationStateChangeListener(new AccessibilityManagerTouchExplorationStateChangeListenerC0222z(this.w));
            }
        }
        setEndIconOnClickListener(sh0VarB2.f());
        EditText editText = this.u;
        if (editText != null) {
            sh0VarB2.m(editText);
            i(sh0VarB2);
        }
        rw0.a(textInputLayout, checkableImageButton, this.o, this.p);
        e(true);
    }

    public final void g(boolean z) {
        if (c() != z) {
            this.k.setVisibility(z ? 0 : 8);
            j();
            l();
            this.e.p();
        }
    }

    public final void h(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.g;
        checkableImageButton.setImageDrawable(drawable);
        k();
        rw0.a(this.e, checkableImageButton, this.h, this.i);
    }

    public final void i(sh0 sh0Var) {
        if (this.u == null) {
            return;
        }
        if (sh0Var.e() != null) {
            this.u.setOnFocusChangeListener(sh0Var.e());
        }
        if (sh0Var.g() != null) {
            this.k.setOnFocusChangeListener(sh0Var.g());
        }
    }

    public final void j() {
        this.f.setVisibility((this.k.getVisibility() != 0 || d()) ? 8 : 0);
        setVisibility(c() || d() || ((this.r == null || this.t) ? '\b' : (char) 0) == 0 ? 0 : 8);
    }

    public final void k() {
        CheckableImageButton checkableImageButton = this.g;
        Drawable drawable = checkableImageButton.getDrawable();
        TextInputLayout textInputLayout = this.e;
        checkableImageButton.setVisibility(drawable != null && textInputLayout.n.k && textInputLayout.m() ? 0 : 8);
        j();
        l();
        if (this.m != 0) {
            return;
        }
        textInputLayout.p();
    }

    public final void l() {
        int iD;
        TextInputLayout textInputLayout = this.e;
        if (textInputLayout.h == null) {
            return;
        }
        if (c() || d()) {
            iD = 0;
        } else {
            EditText editText = textInputLayout.h;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            iD = om3.e.d(editText);
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.material_input_text_to_prefix_suffix_padding);
        int paddingTop = textInputLayout.h.getPaddingTop();
        int paddingBottom = textInputLayout.h.getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap2 = om3.a;
        om3.e.i(this.s, dimensionPixelSize, paddingTop, iD, paddingBottom);
    }

    public final void m() {
        AppCompatTextView appCompatTextView = this.s;
        int visibility = appCompatTextView.getVisibility();
        int i = (this.r == null || this.t) ? 8 : 0;
        if (visibility != i) {
            b().p(i == 0);
        }
        j();
        appCompatTextView.setVisibility(i);
        this.e.p();
    }

    public void removeOnEndIconChangedListener(TextInputLayout.h hVar) {
        this.n.remove(hVar);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.q;
        CheckableImageButton checkableImageButton = this.k;
        checkableImageButton.setOnClickListener(onClickListener);
        rw0.c(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.q = onLongClickListener;
        CheckableImageButton checkableImageButton = this.k;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        rw0.c(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        View.OnLongClickListener onLongClickListener = this.j;
        CheckableImageButton checkableImageButton = this.g;
        checkableImageButton.setOnClickListener(onClickListener);
        rw0.c(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.j = onLongClickListener;
        CheckableImageButton checkableImageButton = this.g;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        rw0.c(checkableImageButton, onLongClickListener);
    }
}
