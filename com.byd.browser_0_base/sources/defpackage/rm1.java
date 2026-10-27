package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.h;

/* JADX INFO: compiled from: NewTabPageLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rm1 implements TextWatcher {
    public final /* synthetic */ NewTabPageLayout e;

    public rm1(NewTabPageLayout newTabPageLayout) {
        this.e = newTabPageLayout;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (editable.length() == 0) {
            return;
        }
        NewTabPageLayout newTabPageLayout = this.e;
        ((h.b) newTabPageLayout.m).b(editable.toString(), false);
        newTabPageLayout.i.a.p(dg2.j, "");
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
