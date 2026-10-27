package a5;

import android.text.Editable;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class k extends U4.j {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f11156m;

    public /* synthetic */ k(p pVar, int i7) {
        this.l = i7;
        this.f11156m = pVar;
    }

    @Override // U4.j, android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        switch (this.l) {
            case 0:
                o oVar = (o) this.f11156m;
                EditText editText = oVar.f11170a.getEditText();
                if (!(editText instanceof AutoCompleteTextView)) {
                    throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
                }
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
                if (oVar.f11168n.isTouchExplorationEnabled() && o.f(autoCompleteTextView) && !oVar.c.hasFocus()) {
                    autoCompleteTextView.dismissDropDown();
                }
                autoCompleteTextView.post(new h5.t(11, this, autoCompleteTextView, false));
                return;
            default:
                return;
        }
    }

    @Override // U4.j, android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        switch (this.l) {
            case 1:
                ((s) this.f11156m).c.setChecked(!s.d(r0));
                break;
        }
    }
}
