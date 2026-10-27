package androidx.preference;

import android.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: compiled from: EditTextPreferenceDialogFragmentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {
    public EditText x0;
    public CharSequence y0;
    public final RunnableC0022a z0 = new RunnableC0022a();
    public long A0 = -1;

    /* JADX INFO: renamed from: androidx.preference.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EditTextPreferenceDialogFragmentCompat.java */
    public class RunnableC0022a implements Runnable {
        public RunnableC0022a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            a.this.S0();
        }
    }

    @Override // androidx.preference.c
    public final void O0(View view) {
        super.O0(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        this.x0 = editText;
        if (editText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText.requestFocus();
        this.x0.setText(this.y0);
        EditText editText2 = this.x0;
        editText2.setSelection(editText2.getText().length());
        ((EditTextPreference) N0()).getClass();
    }

    @Override // androidx.preference.c
    public final void P0(boolean z) {
        if (z) {
            String string = this.x0.getText().toString();
            EditTextPreference editTextPreference = (EditTextPreference) N0();
            if (editTextPreference.h(string)) {
                editTextPreference.Y(string);
            }
        }
    }

    @Override // androidx.preference.c
    public final void R0() {
        this.A0 = SystemClock.currentThreadTimeMillis();
        S0();
    }

    public final void S0() {
        long j = this.A0;
        if (j != -1 && j + 1000 > SystemClock.currentThreadTimeMillis()) {
            EditText editText = this.x0;
            if (editText == null || !editText.isFocused()) {
                this.A0 = -1L;
                return;
            }
            if (((InputMethodManager) this.x0.getContext().getSystemService("input_method")).showSoftInput(this.x0, 0)) {
                this.A0 = -1L;
                return;
            }
            EditText editText2 = this.x0;
            RunnableC0022a runnableC0022a = this.z0;
            editText2.removeCallbacks(runnableC0022a);
            this.x0.postDelayed(runnableC0022a, 50L);
        }
    }

    @Override // androidx.preference.c, defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle == null) {
            this.y0 = ((EditTextPreference) N0()).Y;
        } else {
            this.y0 = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.c, defpackage.r90, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.y0);
    }
}
