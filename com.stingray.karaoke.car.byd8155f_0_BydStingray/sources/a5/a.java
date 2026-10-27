package a5;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class a implements TextWatcher {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11136m;

    public /* synthetic */ a(Object obj, int i7) {
        this.l = i7;
        this.f11136m = obj;
    }

    private final void a(int i7, int i10, int i11, CharSequence charSequence) {
    }

    private final void b(int i7, int i10, int i11, CharSequence charSequence) {
    }

    private final void c(int i7, int i10, int i11, CharSequence charSequence) {
    }

    private final void d(int i7, int i10, int i11, CharSequence charSequence) {
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        switch (this.l) {
            case 0:
                h hVar = (h) this.f11136m;
                if (hVar.f11170a.getSuffixText() == null) {
                    hVar.d(hVar.f11170a.hasFocus() && editable.length() > 0);
                    break;
                }
                break;
            default:
                TextInputLayout textInputLayout = (TextInputLayout) this.f11136m;
                textInputLayout.s(!textInputLayout.f12907W0, false);
                if (textInputLayout.f12935u) {
                    textInputLayout.n(editable.length());
                }
                if (textInputLayout.B) {
                    textInputLayout.t(editable.length());
                }
                break;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        int i12 = this.l;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        int i12 = this.l;
    }
}
