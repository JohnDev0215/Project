package defpackage;

import android.view.View;
import org.chromium.chrome.browser.autofill.a;
import org.chromium.chrome.browser.autofill.e;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class je0 implements View.OnFocusChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ je0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                pe0 pe0Var = (pe0) obj;
                pe0Var.i = z;
                pe0Var.q();
                if (!z) {
                    pe0Var.t(false);
                    pe0Var.j = false;
                }
                break;
            case 1:
                ((a) obj).n |= z;
                break;
            case 2:
                e eVar = (e) obj;
                eVar.C = true;
                eVar.h();
                break;
            default:
                eg egVar = (eg) obj;
                if (!z) {
                    egVar.getClass();
                } else {
                    egVar.i.j();
                }
                break;
        }
    }
}
