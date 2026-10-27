package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import org.chromium.chrome.browser.tasks.tab_management.m;

/* JADX INFO: compiled from: TabGridDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pz2 implements TextWatcher {
    public final /* synthetic */ m e;

    public pz2(m mVar) {
        this.e = mVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        m mVar = this.e;
        if (mVar.w) {
            mVar.x = editable.toString();
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
