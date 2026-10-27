package h6;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends h6.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextWatcher f5365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View.OnFocusChangeListener f5366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextInputLayout.f f5367f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TextInputLayout.g f5368g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AnimatorSet f5369h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ValueAnimator f5370i;

    /* JADX INFO: renamed from: h6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class C0054a implements TextWatcher {
        public C0054a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (a.this.f5407a.getSuffixText() != null) {
                return;
            }
            a.this.i(a.l(editable));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class b implements View.OnFocusChangeListener {
        public b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z7) {
            a.this.i((TextUtils.isEmpty(((EditText) view).getText()) ^ true) && z7);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class c implements TextInputLayout.f {
        public c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.f
        public void a(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(editText.hasFocus() && a.l(editText.getText()));
            textInputLayout.setEndIconCheckable(false);
            editText.setOnFocusChangeListener(a.this.f5366e);
            editText.removeTextChangedListener(a.this.f5365d);
            editText.addTextChangedListener(a.this.f5365d);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class d implements TextInputLayout.g {
        public d() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i8) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i8 != 2) {
                return;
            }
            editText.removeTextChangedListener(a.this.f5365d);
            if (editText.getOnFocusChangeListener() == a.this.f5366e) {
                editText.setOnFocusChangeListener(null);
            }
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Editable text = a.this.f5407a.getEditText().getText();
            if (text != null) {
                text.clear();
            }
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class f extends AnimatorListenerAdapter {
        public f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.f5407a.setEndIconVisible(true);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class g extends AnimatorListenerAdapter {
        public g() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f5407a.setEndIconVisible(false);
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class h implements ValueAnimator.AnimatorUpdateListener {
        public h() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.f5409c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
    public class i implements ValueAnimator.AnimatorUpdateListener {
        public i() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            a.this.f5409c.setScaleX(fFloatValue);
            a.this.f5409c.setScaleY(fFloatValue);
        }
    }

    public a(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f5365d = new C0054a();
        this.f5366e = new b();
        this.f5367f = new c();
        this.f5368g = new d();
    }

    public static boolean l(Editable editable) {
        return editable.length() > 0;
    }

    @Override // h6.e
    public void a() {
        this.f5407a.setEndIconDrawable(m.a.d(this.f5408b, R$drawable.mtrl_ic_cancel));
        TextInputLayout textInputLayout = this.f5407a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R$string.clear_text_end_icon_content_description));
        this.f5407a.setEndIconOnClickListener(new e());
        this.f5407a.e(this.f5367f);
        this.f5407a.f(this.f5368g);
        m();
    }

    @Override // h6.e
    public void c(boolean z7) {
        if (this.f5407a.getSuffixText() == null) {
            return;
        }
        i(z7);
    }

    public final void i(boolean z7) {
        boolean z8 = this.f5407a.J() == z7;
        if (z7) {
            this.f5370i.cancel();
            this.f5369h.start();
            if (z8) {
                this.f5369h.end();
                return;
            }
            return;
        }
        this.f5369h.cancel();
        this.f5370i.start();
        if (z8) {
            this.f5370i.end();
        }
    }

    public final ValueAnimator j(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(j5.a.f5578a);
        valueAnimatorOfFloat.setDuration(100L);
        valueAnimatorOfFloat.addUpdateListener(new h());
        return valueAnimatorOfFloat;
    }

    public final ValueAnimator k() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(j5.a.f5581d);
        valueAnimatorOfFloat.setDuration(150L);
        valueAnimatorOfFloat.addUpdateListener(new i());
        return valueAnimatorOfFloat;
    }

    public final void m() {
        ValueAnimator valueAnimatorK = k();
        ValueAnimator valueAnimatorJ = j(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f5369h = animatorSet;
        animatorSet.playTogether(valueAnimatorK, valueAnimatorJ);
        this.f5369h.addListener(new f());
        ValueAnimator valueAnimatorJ2 = j(1.0f, 0.0f);
        this.f5370i = valueAnimatorJ2;
        valueAnimatorJ2.addListener(new g());
    }
}
