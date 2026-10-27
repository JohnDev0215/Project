package a5;

import F1.C0243b;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public class u extends C0243b {
    public final TextInputLayout d;

    public u(TextInputLayout textInputLayout) {
        this.d = textInputLayout;
    }

    @Override // F1.C0243b
    public void d(View view, G1.e eVar) {
        View.AccessibilityDelegate accessibilityDelegate = this.f2324a;
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        TextInputLayout textInputLayout = this.d;
        EditText editText = textInputLayout.getEditText();
        CharSequence text = editText != null ? editText.getText() : null;
        CharSequence hint = textInputLayout.getHint();
        CharSequence error = textInputLayout.getError();
        CharSequence placeholderText = textInputLayout.getPlaceholderText();
        int counterMaxLength = textInputLayout.getCounterMaxLength();
        CharSequence counterOverflowDescription = textInputLayout.getCounterOverflowDescription();
        boolean zIsEmpty = TextUtils.isEmpty(text);
        boolean zIsEmpty2 = TextUtils.isEmpty(hint);
        boolean z6 = textInputLayout.f12895Q0;
        boolean zIsEmpty3 = TextUtils.isEmpty(error);
        boolean z10 = (zIsEmpty3 && TextUtils.isEmpty(counterOverflowDescription)) ? false : true;
        String string = !zIsEmpty2 ? hint.toString() : "";
        if (!zIsEmpty) {
            eVar.k(text);
        } else if (!TextUtils.isEmpty(string)) {
            eVar.k(string);
            if (!z6 && placeholderText != null) {
                eVar.k(string + ", " + ((Object) placeholderText));
            }
        } else if (placeholderText != null) {
            eVar.k(placeholderText);
        }
        if (!TextUtils.isEmpty(string)) {
            accessibilityNodeInfo.setHintText(string);
            accessibilityNodeInfo.setShowingHintText(zIsEmpty);
        }
        if (text == null || text.length() != counterMaxLength) {
            counterMaxLength = -1;
        }
        accessibilityNodeInfo.setMaxTextLength(counterMaxLength);
        if (z10) {
            if (zIsEmpty3) {
                error = counterOverflowDescription;
            }
            accessibilityNodeInfo.setError(error);
        }
        if (editText != null) {
            editText.setLabelFor(R.id.textinput_helper_text);
        }
    }
}
