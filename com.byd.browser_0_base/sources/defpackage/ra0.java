package defpackage;

import android.content.res.Resources;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import defpackage.pa0;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: DisclosureSnackbar.java */
/* JADX INFO: loaded from: classes.dex */
public final class ra0 extends pa0 {
    public final Resources i;
    public boolean j;

    public ra0(Resources resources, p61<wp2> p61Var, ag3 ag3Var, q2 q2Var) {
        super(resources, p61Var, ag3Var, q2Var);
        this.i = resources;
    }

    @Override // defpackage.pa0
    public final tp2 b(pa0.a aVar) {
        if (this.j) {
            return null;
        }
        this.j = true;
        int i = R$string.twa_running_in_chrome;
        Resources resources = this.i;
        String string = resources.getString(i);
        String string2 = resources.getString(R$string.got_it);
        tp2 tp2VarA = tp2.a(string, aVar, 0, 33);
        tp2VarA.d = string2;
        tp2VarA.e = null;
        tp2VarA.j = TestInstrumentManagerImplCallback.SET_INSTRUMENT_THEME;
        tp2VarA.i = false;
        return tp2VarA;
    }
}
