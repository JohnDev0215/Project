package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import org.chromium.chrome.browser.autofill.b;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class za implements TextView.OnEditorActionListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ za(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        int i2 = this.e;
        Object obj = this.f;
        switch (i2) {
            case 0:
                b bVar = (b) obj;
                if (i != 6) {
                    bVar.getClass();
                } else if (bVar.j.getText().toString().trim().length() != 0) {
                    bVar.c(0, bVar.e);
                }
                break;
            default:
                ja1 ja1Var = (ja1) obj;
                if (i != 6) {
                    ja1Var.getClass();
                } else {
                    ja1Var.b.e().performClick();
                }
                break;
        }
        return false;
    }
}
