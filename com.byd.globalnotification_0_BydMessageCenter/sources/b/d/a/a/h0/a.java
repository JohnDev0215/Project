package b.d.a.a.h0;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public class a extends m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextWatcher f1895d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View.OnFocusChangeListener f1896e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextInputLayout.f f1897f;
    public final TextInputLayout.g g;
    public AnimatorSet h;
    public ValueAnimator i;

    /* JADX INFO: renamed from: b.d.a.a.h0.a$a, reason: collision with other inner class name */
    public class C0038a implements TextWatcher {
        public C0038a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
        @Override // android.text.TextWatcher
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void afterTextChanged(android.text.Editable r4) {
            /*
                r3 = this;
                b.d.a.a.h0.a r0 = b.d.a.a.h0.a.this
                com.google.android.material.textfield.TextInputLayout r0 = r0.f1927a
                java.lang.CharSequence r0 = r0.getSuffixText()
                if (r0 == 0) goto Lb
                return
            Lb:
                b.d.a.a.h0.a r3 = b.d.a.a.h0.a.this
                com.google.android.material.textfield.TextInputLayout r0 = r3.f1927a
                boolean r0 = r0.hasFocus()
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L23
                int r4 = r4.length()
                if (r4 <= 0) goto L1f
                r4 = r1
                goto L20
            L1f:
                r4 = r2
            L20:
                if (r4 == 0) goto L23
                goto L24
            L23:
                r1 = r2
            L24:
                r3.e(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: b.d.a.a.h0.a.C0038a.afterTextChanged(android.text.Editable):void");
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b implements View.OnFocusChangeListener {
        public b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            a.this.e((TextUtils.isEmpty(((EditText) view).getText()) ^ true) && z);
        }
    }

    public class c implements TextInputLayout.f {
        public c() {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
        @Override // com.google.android.material.textfield.TextInputLayout.f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(com.google.android.material.textfield.TextInputLayout r5) {
            /*
                r4 = this;
                android.widget.EditText r0 = r5.getEditText()
                boolean r1 = r0.hasFocus()
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L1c
                android.text.Editable r1 = r0.getText()
                int r1 = r1.length()
                if (r1 <= 0) goto L18
                r1 = r2
                goto L19
            L18:
                r1 = r3
            L19:
                if (r1 == 0) goto L1c
                goto L1d
            L1c:
                r2 = r3
            L1d:
                r5.setEndIconVisible(r2)
                r5.setEndIconCheckable(r3)
                b.d.a.a.h0.a r5 = b.d.a.a.h0.a.this
                android.view.View$OnFocusChangeListener r5 = r5.f1896e
                r0.setOnFocusChangeListener(r5)
                b.d.a.a.h0.a r5 = b.d.a.a.h0.a.this
                android.text.TextWatcher r5 = r5.f1895d
                r0.removeTextChangedListener(r5)
                b.d.a.a.h0.a r4 = b.d.a.a.h0.a.this
                android.text.TextWatcher r4 = r4.f1895d
                r0.addTextChangedListener(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: b.d.a.a.h0.a.c.a(com.google.android.material.textfield.TextInputLayout):void");
        }
    }

    public class d implements TextInputLayout.g {

        /* JADX INFO: renamed from: b.d.a.a.h0.a$d$a, reason: collision with other inner class name */
        public class RunnableC0039a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ EditText f1902a;

            public RunnableC0039a(EditText editText) {
                this.f1902a = editText;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f1902a.removeTextChangedListener(a.this.f1895d);
            }
        }

        public d() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.g
        public void a(TextInputLayout textInputLayout, int i) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i != 2) {
                return;
            }
            editText.post(new RunnableC0039a(editText));
            if (editText.getOnFocusChangeListener() == a.this.f1896e) {
                editText.setOnFocusChangeListener(null);
            }
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Editable text = a.this.f1927a.getEditText().getText();
            if (text != null) {
                text.clear();
            }
            a.this.f1927a.o();
        }
    }

    public a(TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f1895d = new C0038a();
        this.f1896e = new b();
        this.f1897f = new c();
        this.g = new d();
    }

    @Override // b.d.a.a.h0.m
    public void a() {
        this.f1927a.setEndIconDrawable(a.b.l.a.a.b(this.f1928b, b.d.a.a.e.mtrl_ic_cancel));
        TextInputLayout textInputLayout = this.f1927a;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(b.d.a.a.i.clear_text_end_icon_content_description));
        this.f1927a.setEndIconOnClickListener(new e());
        this.f1927a.a(this.f1897f);
        this.f1927a.f0.add(this.g);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(b.d.a.a.l.a.f1969d);
        valueAnimatorOfFloat.setDuration(150L);
        valueAnimatorOfFloat.addUpdateListener(new b.d.a.a.h0.e(this));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setInterpolator(b.d.a.a.l.a.f1966a);
        valueAnimatorOfFloat2.setDuration(100L);
        valueAnimatorOfFloat2.addUpdateListener(new b.d.a.a.h0.d(this));
        AnimatorSet animatorSet = new AnimatorSet();
        this.h = animatorSet;
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        this.h.addListener(new b.d.a.a.h0.b(this));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat3.setInterpolator(b.d.a.a.l.a.f1966a);
        valueAnimatorOfFloat3.setDuration(100L);
        valueAnimatorOfFloat3.addUpdateListener(new b.d.a.a.h0.d(this));
        this.i = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.addListener(new b.d.a.a.h0.c(this));
    }

    @Override // b.d.a.a.h0.m
    public void c(boolean z) {
        if (this.f1927a.getSuffixText() == null) {
            return;
        }
        e(z);
    }

    public final void e(boolean z) {
        boolean z2 = this.f1927a.k() == z;
        if (z && !this.h.isRunning()) {
            this.i.cancel();
            this.h.start();
            if (z2) {
                this.h.end();
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        this.h.cancel();
        this.i.start();
        if (z2) {
            this.i.end();
        }
    }
}
