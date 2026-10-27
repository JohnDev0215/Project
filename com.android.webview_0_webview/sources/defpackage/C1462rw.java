package defpackage;

import android.view.View;
import android.view.autofill.AutofillManager;
import java.lang.ref.WeakReference;
import java.util.ListIterator;

/* JADX INFO: renamed from: rw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1462rw extends AutofillManager.AutofillCallback {
    public WeakReference a;

    public C1462rw(C1572tw c1572tw) {
        this.a = new WeakReference(c1572tw);
    }

    @Override // android.view.autofill.AutofillManager.AutofillCallback
    public void onAutofillEvent(View view, int i, int i2) {
        C1572tw c1572tw = (C1572tw) this.a.get();
        if (c1572tw == null) {
            return;
        }
        c1572tw.b = i2 == 1;
        if (i2 == 1) {
            ListIterator listIterator = c1572tw.f.listIterator();
            while (listIterator.hasNext()) {
                InterfaceC1517sw interfaceC1517sw = (InterfaceC1517sw) ((WeakReference) listIterator.next()).get();
                if (interfaceC1517sw == null) {
                    listIterator.remove();
                } else {
                    C1792xw c1792xw = (C1792xw) interfaceC1517sw;
                    if (c1792xw.a.d != null) {
                        Dw dw = c1792xw.a.f;
                        long jCurrentTimeMillis = System.currentTimeMillis() - c1792xw.a.h;
                        Cw cw = dw.a;
                        if (cw != null) {
                            cw.a(2);
                            Cw cw2 = dw.a;
                            if (cw2.a == null) {
                                cw2.a = Long.valueOf(jCurrentTimeMillis);
                            }
                        }
                    }
                }
            }
        }
    }
}
