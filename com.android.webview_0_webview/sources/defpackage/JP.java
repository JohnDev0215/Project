package defpackage;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewStructure;
import org.chromium.components.autofill.AutofillProvider;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JP extends IP {
    public JP(Context context, PN pn) {
        super(context, pn);
    }

    @Override // android.view.View
    public void autofill(SparseArray sparseArray) {
        AutofillProvider autofillProvider;
        TabImpl tabImpl = this.s;
        if (tabImpl == null || (autofillProvider = tabImpl.k) == null) {
            return;
        }
        autofillProvider.b(sparseArray);
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        AutofillProvider autofillProvider;
        TabImpl tabImpl = this.s;
        if (tabImpl == null || (autofillProvider = tabImpl.k) == null) {
            return;
        }
        autofillProvider.d(viewStructure, i);
    }
}
