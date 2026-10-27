package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import org.chromium.chrome.browser.autofill.e;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pm implements TextView.OnEditorActionListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ View.OnClickListener f;

    public /* synthetic */ pm(int i, View.OnClickListener onClickListener) {
        this.e = i;
        this.f = onClickListener;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        int i2 = this.e;
        View.OnClickListener onClickListener = this.f;
        switch (i2) {
            case 0:
                e eVar = (e) onClickListener;
                if (i != 6) {
                    eVar.getClass();
                } else if (!eVar.f.k(di1.l)) {
                    eVar.c(0, eVar.f);
                }
                break;
            default:
                ((RadioButtonWithEditText) onClickListener).k.clearFocus();
                break;
        }
        return false;
    }
}
