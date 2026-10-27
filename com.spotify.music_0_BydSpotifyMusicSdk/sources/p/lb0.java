package p;

import android.text.Editable;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class lb0 extends hk2 {
    public final /* synthetic */ int c;
    public final /* synthetic */ be0 e;

    public /* synthetic */ lb0(be0 be0Var, int i) {
        this.c = i;
        this.e = be0Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        switch (this.c) {
            case 0:
                pb0 pb0Var = (pb0) this.e;
                EditText editText = pb0Var.a.getEditText();
                if (!(editText instanceof AutoCompleteTextView)) {
                    throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
                }
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
                if (pb0Var.o.isTouchExplorationEnabled() && autoCompleteTextView.getKeyListener() != null && !pb0Var.c.hasFocus()) {
                    autoCompleteTextView.dismissDropDown();
                }
                autoCompleteTextView.post(new yf1(this, 20, autoCompleteTextView));
                return;
            default:
                return;
        }
    }

    @Override // p.hk2, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        switch (this.c) {
            case 1:
                ((ms1) this.e).c.setChecked(!ms1.d(r1));
                break;
        }
    }
}
