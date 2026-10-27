package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import o2.j;

/* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
class a extends com.google.android.material.textfield.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TextWatcher f4848d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final View.OnFocusChangeListener f4849e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final TextInputLayout.f f4850f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final TextInputLayout.g f4851g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private AnimatorSet f4852h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ValueAnimator f4853i;

    /* JADX INFO: renamed from: com.google.android.material.textfield.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class C0052a implements TextWatcher {
        C0052a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (a.this.f4894a.getSuffixText() != null) {
                return;
            }
            a aVar = a.this;
            aVar.i(aVar.f4894a.hasFocus() && a.l(editable));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z6) {
            a.this.i((TextUtils.isEmpty(((EditText) view).getText()) ^ true) && z6);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class c implements TextInputLayout.f {
        c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(editText.hasFocus() && a.l(editText.getText()));
            textInputLayout.setEndIconCheckable(false);
            editText.setOnFocusChangeListener(a.this.f4849e);
            editText.removeTextChangedListener(a.this.f4848d);
            editText.addTextChangedListener(a.this.f4848d);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class d implements TextInputLayout.g {

        /* JADX INFO: renamed from: com.google.android.material.textfield.a$d$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
        class RunnableC0053a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ EditText f4858e;

            RunnableC0053a(EditText editText) {
                this.f4858e = editText;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4858e.removeTextChangedListener(a.this.f4848d);
            }
        }

        d() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i7) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i7 != 2) {
                return;
            }
            editText.post(new RunnableC0053a(editText));
            if (editText.getOnFocusChangeListener() == a.this.f4849e) {
                editText.setOnFocusChangeListener(null);
            }
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Editable text = a.this.f4894a.getEditText().getText();
            if (text != null) {
                text.clear();
            }
            a.this.f4894a.V();
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class f extends AnimatorListenerAdapter {
        f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.f4894a.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class g extends AnimatorListenerAdapter {
        g() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f4894a.setEndIconVisible(false);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class h implements ValueAnimator.AnimatorUpdateListener {
        h() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.f4896c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    class i implements ValueAnimator.AnimatorUpdateListener {
        i() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            a.this.f4896c.setScaleX(fFloatValue);
            a.this.f4896c.setScaleY(fFloatValue);
        }
    }

    a(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f4848d = new C0052a();
        this.f4849e = new b();
        this.f4850f = new c();
        this.f4851g = new d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z6) {
        boolean z7 = this.f4894a.K() == z6;
        if (z6 && !this.f4852h.isRunning()) {
            this.f4853i.cancel();
            this.f4852h.start();
            if (z7) {
                this.f4852h.end();
                return;
            }
            return;
        }
        if (z6) {
            return;
        }
        this.f4852h.cancel();
        this.f4853i.start();
        if (z7) {
            this.f4853i.end();
        }
    }

    private ValueAnimator j(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(p2.a.f8820a);
        valueAnimatorOfFloat.setDuration(100L);
        valueAnimatorOfFloat.addUpdateListener(new h());
        return valueAnimatorOfFloat;
    }

    private ValueAnimator k() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(p2.a.f8823d);
        valueAnimatorOfFloat.setDuration(150L);
        valueAnimatorOfFloat.addUpdateListener(new i());
        return valueAnimatorOfFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean l(Editable editable) {
        return editable.length() > 0;
    }

    private void m() {
        ValueAnimator valueAnimatorK = k();
        ValueAnimator valueAnimatorJ = j(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f4852h = animatorSet;
        animatorSet.playTogether(valueAnimatorK, valueAnimatorJ);
        this.f4852h.addListener(new f());
        ValueAnimator valueAnimatorJ2 = j(1.0f, 0.0f);
        this.f4853i = valueAnimatorJ2;
        valueAnimatorJ2.addListener(new g());
    }

    @Override // com.google.android.material.textfield.e
    void a() {
        this.f4894a.setEndIconDrawable(f.a.b(this.f4895b, o2.e.mtrl_ic_cancel));
        TextInputLayout textInputLayout = this.f4894a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(j.clear_text_end_icon_content_description));
        this.f4894a.setEndIconOnClickListener(new e());
        this.f4894a.e(this.f4850f);
        this.f4894a.f(this.f4851g);
        m();
    }

    @Override // com.google.android.material.textfield.e
    void c(boolean z6) {
        if (this.f4894a.getSuffixText() == null) {
            return;
        }
        i(z6);
    }
}
