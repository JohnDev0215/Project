package defpackage;

import android.view.View;
import org.chromium.chrome.browser.autofill.a;
import org.chromium.chrome.browser.autofill.e;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xa implements View.OnFocusChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ xa(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((a) obj).m |= z;
                break;
            case 1:
                e eVar = (e) obj;
                eVar.E = true;
                eVar.h();
                break;
            default:
                RadioButtonWithEditText radioButtonWithEditText = (RadioButtonWithEditText) obj;
                if (!z) {
                    radioButtonWithEditText.k.setCursorVisible(false);
                    t41.f.e(radioButtonWithEditText.k);
                } else {
                    int i2 = RadioButtonWithEditText.m;
                    radioButtonWithEditText.setCheckedWithNoFocusChange(true);
                    radioButtonWithEditText.k.setCursorVisible(true);
                }
                break;
        }
    }
}
