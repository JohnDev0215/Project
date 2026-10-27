package defpackage;

import android.view.View;
import org.chromium.chrome.browser.autofill.AutofillEditorBase;

/* JADX INFO: compiled from: AutofillEditorBase.java */
/* JADX INFO: loaded from: classes.dex */
public final class wa implements View.OnClickListener {
    public final /* synthetic */ AutofillEditorBase e;

    public wa(AutofillEditorBase autofillEditorBase) {
        this.e = autofillEditorBase;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AutofillEditorBase autofillEditorBase = this.e;
        if (autofillEditorBase.K0()) {
            autofillEditorBase.D().finish();
        }
    }
}
