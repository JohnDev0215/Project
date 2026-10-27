package androidx.preference;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class EditTextPreferenceDialogFragment extends PreferenceDialogFragment {
    public EditText m;
    public CharSequence n;

    @Deprecated
    public EditTextPreferenceDialogFragment() {
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void b(View view) {
        super.b(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        this.m = editText;
        editText.requestFocus();
        EditText editText2 = this.m;
        if (editText2 == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText2.setText(this.n);
        EditText editText3 = this.m;
        editText3.setSelection(editText3.getText().length());
    }

    @Override // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public final void c(boolean z) {
        if (z) {
            String string = this.m.getText().toString();
            if (((EditTextPreference) a()).h(string)) {
                ((EditTextPreference) a()).Y(string);
            }
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.n = ((EditTextPreference) a()).Y;
        } else {
            this.n = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.n);
    }
}
