package p;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class pa2 implements TextWatcher {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ pa2(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                break;
            case 1:
                et etVar = (et) obj;
                if (etVar.a.getSuffixText() == null) {
                    etVar.e(et.d(etVar));
                    break;
                }
                break;
            default:
                TextInputLayout textInputLayout = (TextInputLayout) obj;
                textInputLayout.s(!textInputLayout.U0, false);
                if (textInputLayout.r) {
                    textInputLayout.n(editable.length());
                }
                if (textInputLayout.y) {
                    textInputLayout.t(editable.length());
                }
                break;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        switch (this.c) {
            case 0:
                SearchView searchView = (SearchView) this.e;
                Editable text = searchView.x.getText();
                searchView.g0 = text;
                boolean zIsEmpty = TextUtils.isEmpty(text);
                searchView.x(!zIsEmpty);
                int i4 = 8;
                if (searchView.f0 && !searchView.V && zIsEmpty) {
                    searchView.C.setVisibility(8);
                    i4 = 0;
                }
                searchView.E.setVisibility(i4);
                searchView.t();
                searchView.w();
                charSequence.toString();
                searchView.getClass();
                break;
        }
    }
}
