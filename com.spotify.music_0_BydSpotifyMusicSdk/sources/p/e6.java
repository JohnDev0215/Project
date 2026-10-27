package p;

import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class e6 implements View.OnClickListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ e6(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                i6 i6Var = (i6) obj;
                Button button = i6Var.f;
                i6Var.w.obtainMessage(1, i6Var.b).sendToTarget();
                break;
            case 1:
                dm2 dm2Var = ((Toolbar) obj).T;
                xi1 xi1Var = dm2Var != null ? dm2Var.e : null;
                if (xi1Var != null) {
                    xi1Var.collapseActionView();
                }
                break;
            case 2:
                ((MaterialCalendar) obj).H();
                break;
            case 3:
                et etVar = (et) obj;
                Editable text = etVar.a.getEditText().getText();
                if (text != null) {
                    text.clear();
                }
                TextInputLayout textInputLayout = etVar.a;
                textInputLayout.k(textInputLayout.q0, textInputLayout.s0);
                break;
            case 4:
                pb0 pb0Var = (pb0) obj;
                pb0.d(pb0Var, (AutoCompleteTextView) pb0Var.a.getEditText());
                break;
            default:
                ms1 ms1Var = (ms1) obj;
                EditText editText = ms1Var.a.getEditText();
                if (editText != null) {
                    int selectionEnd = editText.getSelectionEnd();
                    if (ms1.d(ms1Var)) {
                        editText.setTransformationMethod(null);
                    } else {
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    if (selectionEnd >= 0) {
                        editText.setSelection(selectionEnd);
                    }
                    TextInputLayout textInputLayout2 = ms1Var.a;
                    textInputLayout2.k(textInputLayout2.q0, textInputLayout2.s0);
                    break;
                }
                break;
        }
    }
}
