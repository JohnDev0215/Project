package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ib implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ AutofillServerCardEditor f;

    public /* synthetic */ ib(AutofillServerCardEditor autofillServerCardEditor, int i) {
        this.e = i;
        this.f = autofillServerCardEditor;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        AutofillServerCardEditor autofillServerCardEditor = this.f;
        switch (i) {
            case 0:
                Boolean bool = (Boolean) obj;
                if (autofillServerCardEditor.l0) {
                    ab abVar = autofillServerCardEditor.j0;
                    long j = abVar.a;
                    if (j != 0) {
                        N.MgCnSGKp(j);
                        abVar.a = 0L;
                        return;
                    }
                    return;
                }
                autofillServerCardEditor.k0 = false;
                if (!bool.booleanValue()) {
                    autofillServerCardEditor.h0.setEnabled(true);
                    return;
                }
                autofillServerCardEditor.i0 = !autofillServerCardEditor.i0;
                autofillServerCardEditor.h0.setEnabled(true);
                autofillServerCardEditor.h0.setText(autofillServerCardEditor.i0 ? R$string.autofill_card_editor_virtual_card_turn_off_button_label : R$string.autofill_card_editor_virtual_card_turn_on_button_label);
                return;
            default:
                int i2 = AutofillServerCardEditor.n0;
                autofillServerCardEditor.getClass();
                if (!((Boolean) obj).booleanValue()) {
                    autofillServerCardEditor.h0.setEnabled(true);
                    return;
                }
                ab abVar2 = autofillServerCardEditor.j0;
                long instrumentId = autofillServerCardEditor.c0.getInstrumentId();
                ib ibVar = autofillServerCardEditor.m0;
                long j2 = abVar2.a;
                if (j2 == 0) {
                    throw new IllegalStateException("The native delegate was cleaned up or not initialized.");
                }
                N.MnSf6709(j2, instrumentId, ibVar);
                autofillServerCardEditor.k0 = true;
                return;
        }
    }
}
